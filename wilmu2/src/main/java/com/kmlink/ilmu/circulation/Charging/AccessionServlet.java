/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.Charging.Charging;
import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.circulation.Charging.Patron;
import com.kmlink.ilmu.circulation.Global.DateTime;
import com.kmlink.ilmu.circulation.Global.Handler;
import com.kmlink.ilmu.circulation.Global.ISBD;
import com.kmlink.ilmu.circulation.sql.CirculationSQL;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AccessionServlet"})
public class AccessionServlet
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private String msRetAccessionNo;
    private String msRetStatus = "";
    private String msRetItemCat;
    private String msRetMatNo;
    private String msRetSMD;
    private String msRetLocation;
    private String msRetPatron;
    private String msRetCondition;
    private String msRetDueDate;
    private String msNoCircByPatron;
    private String msNoCircByPatronByItem;
    private String msRetItemBranch;
    private String msRetDocNo;
    String msRawDateDue;
    String msRawTimeDue;
    String msRawDateCharged;
    String msRawTimeCharged;
    private Patron patr;
    int msLoanPeriod;
    String msPatronCategory;
    String msPatronBranch;
    String msPtype;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String CT03DOCNO = request.getParameter("name");
        String GL14PATR = request.getParameter("patronid");
        boolean count = false;
        String matno = "";
        String msBookTitle = "";
        try {
            con = DBConnection.getConnection();
            Charging chr = new Charging();
            String action = request.getParameter("action");
            chr.executeCharging(CT03DOCNO, GL14PATR, action);
            String output = chr.getErrMessage();
            String newDue = null;
            if (action.equals("overrideMemExp")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                String expDate = request.getParameter("expDate");
                Date date = dateFormat.parse(expDate);
                cal.setTime(date);
                cal.add(5, -1);
                Patron patr = new Patron(GL14PATR);
                this.msPatronBranch = patr.getMsPatronBranch();
                int holiday = this.isWorkingDayorHoliday(cal, this.msPatronBranch);
                while (holiday > 0) {
                    cal.add(5, -1);
                    holiday = this.isWorkingDayorHoliday(cal, this.msPatronBranch);
                }
                newDue = dateFormat.format(cal.getTime());
                output = null;
            }
            if (output != null && !output.isEmpty()) {
                output = chr.getErrMessage();
            } else {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + CT03DOCNO + "'" + "AND CT03LOCA = GL05LOCA");
                System.out.println(stmt);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    matno = rs.getString("CT03MATNO");
                    this.msRetSMD = rs.getString("CT03SMD");
                    this.msRetItemBranch = rs.getString("GL05BRNC");
                }
                PreparedStatement stmt2 = con.prepareStatement("SELECT CT05SRAW FROM CTTITL,CTPONT WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO = '" + matno + "'");
                List<ISBD> isbd = ISBD.getPunctuation("245");
                ResultSet rsObj = stmt2.executeQuery();
                while (rsObj.next()) {
                    msBookTitle = Handler.getSubfield(rsObj.getString("CT05SRAW").trim(), isbd);
                }
                if (matno != "" && msBookTitle != "") {
                    this.patr = new Patron(GL14PATR);
                    this.msRetItemCat = this.RetrieveAccessionDetails(CT03DOCNO, GL14PATR);
                    this.msPatronCategory = this.patr.getMsPatronCategory();
                    this.msPatronBranch = this.patr.getMsPatronBranch();
                    this.checkItemEligibility(this.msPatronCategory, this.msRetItemBranch, this.msRetSMD, this.msRetItemCat);
                    if (this.msLoanPeriod == 0) {
                        output = "025";
                    } else {
                        this.chargingdate_V1();
                        if (!action.equals("overrideMemExp")) {
                            newDue = GeneralUtility.StrToDate(this.msRawDateDue);
                        }
                        output = String.valueOf(CT03DOCNO) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(this.msRawDateCharged) + "\n" + GeneralUtility.StrToTime2(this.msRawTimeCharged) + "\n" + newDue + "\n" + this.msRawTimeDue;
                    }
                }
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF8");
            response.getWriter().println(output);
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String RetrieveAccessionDetails(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "'";
        System.out.println("Accession" + sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msRetItemCat = rsObj.getString("CT03ICAT");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.msRetItemCat;
    }

    public void RetrieveAccessionDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03MATNOCT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msRetItemCat = rsObj.getString("CT03ICAT");
                    this.msRetAccessionNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                    this.msRetStatus = String.valueOf(rsObj.getString("CT03STAT"));
                    this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                    this.msRetMatNo = String.valueOf(rsObj.getString("CT03MATNO"));
                    this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                    this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                    this.msRetPatron = String.valueOf(rsObj.getString("CT03PATR"));
                    this.msRetCondition = String.valueOf(rsObj.getString("CT03DDATE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int checkItemEligibility(String msPatronCategory, String msPatronBranch, String msRetSMD, String msRetItemCat) {
        try {
            try {
                String sql = "SELECT GL27PLOAN, GL27ELIG, GL27PTYPE,GL27ALLOWOVD FROM GLELIG WHERE GL27ICAT = '" + msRetItemCat + "'" + "AND GL27SMD = '" + msRetSMD + "'" + "AND GL27CATE = '" + msPatronCategory + "'" + "AND GL27BRNC = '" + this.msRetItemBranch + "'";
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                System.out.println(sql);
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msLoanPeriod = rsObj.getInt("GL27PLOAN");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.msLoanPeriod;
    }

    public int numberOfHolidays(String currentdate, String duedate) {
        System.out.println("In Holiday");
        int holiday = 0;
        try {
            try {
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + GeneralUtility.DatetoStr(currentdate) + "'AND '" + GeneralUtility.DatetoStr(duedate) + "'";
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    holiday = Integer.parseInt(rsObj.getString("Holiday"));
                }
            }
            catch (SQLException sQLException) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return holiday;
    }

    public int isHoliday(Date date, Calendar cal) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String startDate = dateFormat.format(date).toString();
        String duedate = dateFormat.format(date2).toString();
        System.out.println("Duedate in working day method " + duedate);
        try {
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE (GL30DATE BETWEEN '" + startDate + "'AND '" + duedate + "') and GL30BRNC='" + this.msRetItemBranch + "'";
            conn = DBConnection.getConnection();
            Statement st = conn.createStatement(1004, 1007);
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                rows = Integer.parseInt(rsObj.getString("Holiday"));
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return rows;
    }

    public void chargingdate() throws SQLException, ParseException {
        boolean holidaycount = false;
        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        this.msPatronBranch = this.patr.getMsPatronBranch();
        this.msPtype = this.getPtype(this.msPatronCategory, this.msRetItemBranch);
        this.msLoanPeriod = this.checkItemEligibility(this.msPatronCategory, this.msRetItemBranch, this.msRetSMD, this.msRetItemCat);
        cal.add(5, this.msLoanPeriod);
        date = cal.getTime();
        if (this.msPtype.equals("H")) {
            String time;
            this.msRawTimeDue = time = this.getTime(this.msLoanPeriod, now, localDate);
        } else {
            Locale locale = Locale.US;
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(locale);
            this.msRawTimeDue = now.format(formatterTime);
        }
        if (!this.msPtype.equals("H")) {
            String duedate = dateFormat.format(date).toString();
            String duetime = timeFormat.format(date).toString();
            duedate = this.calDueDate(date);
            System.out.println("DueDateDates" + duedate);
            date = cal.getTime();
            int isholiday = 0;
            isholiday = this.isWorkingDayorHoliday(cal, this.msRetItemBranch);
            System.out.println("Isholiday : " + isholiday);
            while (isholiday > 0) {
                cal.add(5, 1);
                isholiday = this.isWorkingDayorHoliday(cal, this.msRetItemBranch);
                System.out.println(isholiday);
            }
            date = cal.getTime();
            duedate = dateFormat.format(date).toString();
            duetime = timeFormat.format(date).toString();
            System.out.println("Due Date after including number of holidays" + duedate);
            this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
        }
        this.msRawDateCharged = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeCharged = GeneralUtility.TimetoStr(currentTime);
        System.out.println(String.valueOf(this.msRawDateDue) + this.msRawTimeDue + this.msRawDateCharged);
    }

    public void chargingdate_V1() throws SQLException, ParseException {
        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        this.msPatronBranch = this.patr.getMsPatronBranch();
        this.msPtype = this.getPtype(this.msPatronCategory, this.msRetItemBranch);
        this.msLoanPeriod = this.checkItemEligibility(this.msPatronCategory, this.msRetItemBranch, this.msRetSMD, this.msRetItemCat);
        cal.add(5, this.msLoanPeriod);
        date = cal.getTime();
        if (this.msPtype.equals("H")) {
            String time;
            this.msRawTimeDue = time = this.getTime_V1(this.msLoanPeriod, now, localDate);
        } else {
            Locale locale = Locale.US;
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(locale);
            this.msRawTimeDue = now.format(formatterTime);
        }
        if (!this.msPtype.equals("H")) {
            String duedate = dateFormat.format(date).toString();
            String duetime = timeFormat.format(date).toString();
            duedate = this.calDueDate(date);
            date = cal.getTime();
            boolean count = false;
            int isholiday = 0;
            isholiday = this.isWorkingDayorHoliday(cal, this.msRetItemBranch);
            while (isholiday > 0) {
                cal.add(5, 1);
                isholiday = this.isWorkingDayorHoliday(cal, this.msRetItemBranch);
            }
            date = cal.getTime();
            duedate = dateFormat.format(date).toString();
            duetime = timeFormat.format(date).toString();
            this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
        }
        this.msRawDateCharged = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeCharged = GeneralUtility.TimetoStr(currentTime);
    }

    private String calDueDate(Date date) {
        block18: {
            Calendar cal = Calendar.getInstance();
            Date today = new Date();
            DBConnection db = new DBConnection();
            String inclusive = "";
            if (DBConnection.getDBType().equals("oracle")) {
                inclusive = "SELECT GL28CIRCMODE FROM GLLIBR WHERE rownum = 1";
            } else if (DBConnection.getDBType().equals("mssql")) {
                inclusive = "SELECT TOP 1 GL28CIRCMODE FROM GLLIBR";
            } else if (DBConnection.getDBType().equals("mysql")) {
                inclusive = "SELECT GL28CIRCMODE FROM GLLIBR WHERE LIMIT 1";
            }
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(inclusive);
                    Charging chr = new Charging();
                    while (rsObj.next()) {
                        System.out.println("Exclude: " + rsObj.getString("GL28CIRCMODE"));
                        if (!rsObj.getString("GL28CIRCMODE").equals("E")) continue;
                        System.out.println("Exclude");
                        int totalHoliday = chr.isHoliday(today, date, this.msRetItemBranch);
                        System.out.println("Exclude" + totalHoliday);
                        while (totalHoliday > 0) {
                            today = date = cal.getTime();
                            cal.add(5, totalHoliday);
                            date = cal.getTime();
                            totalHoliday = chr.isHoliday(today, date, this.msRetItemBranch);
                        }
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block18;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return date.toString();
    }

    private String getPtype(String cate, String icat) {
        String ptype = "";
        try {
            try {
                String sql2 = "SELECT GL27PTYPE FROM GLELIG WHERE GL27CATE ='" + this.msPatronCategory + "' and GL27ICAT='" + this.msRetItemCat + "' and GL27SMD='" + this.msRetSMD + "' and GL27BRNC='" + this.msRetItemBranch + "'";
                System.out.println("Test" + sql2);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    ptype = rsObj.getString("GL27PTYPE");
                }
            }
            catch (SQLException sQLException) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ptype;
    }

    private String getTime(int loanPeriod, LocalDateTime currentTime, LocalDate localDate) throws ParseException {
        block14: {
            String timing = "";
            try {
                try {
                    Locale locale = Locale.US;
                    DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
                    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(locale);
                    String day = localDate.format(formatterOutput);
                    day = Handler.getDay(day);
                    String libEnd = CirculationSQL.getMsLibClose(this.msRetItemBranch, day);
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(libEnd);
                    if (!rsObj.next()) break block14;
                    String libClose = DateTime.convertLibraryTiming(rsObj.getString("GL37STOP"));
                    libClose = String.valueOf(DateTime.getSysTodayDate()) + "T" + libClose;
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DateTimeFormatter formatForTime = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime closeTime = LocalDateTime.parse(libClose);
                    currentTime = currentTime.plus(loanPeriod, ChronoUnit.HOURS);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Calendar cal = Calendar.getInstance();
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.clear();
                    calendar.set(currentTime.getYear(), currentTime.getMonthValue() - 1, currentTime.getDayOfMonth());
                    if (currentTime.isAfter(closeTime)) {
                        Duration duration = Duration.between(currentTime, closeTime);
                        calendar.add(5, 1);
                        String duedate = this.calDueDate(calendar.getTime());
                        date = calendar.getTime();
                        int isholiday = 0;
                        isholiday = this.isWorkingDayorHoliday(calendar, this.msRetItemBranch);
                        while (isholiday > 0) {
                            calendar.add(5, 1);
                            isholiday = this.isWorkingDayorHoliday(calendar, this.msRetItemBranch);
                        }
                        date = calendar.getTime();
                        duedate = dateFormat.format(date).toString();
                        this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
                        LocalDateTime openTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        day = openTime.format(formatterOutput);
                        day = Handler.getDay(day);
                        String libStart = CirculationSQL.getMsLibOpen(this.msRetItemBranch, day);
                        Connection conn1 = DBConnection.getConnection();
                        Statement stmt1 = conn1.createStatement();
                        ResultSet rsObj1 = stmt1.executeQuery(libStart);
                        String libOpen = "";
                        if (rsObj1.next()) {
                            libOpen = DateTime.convertLibraryTiming(rsObj1.getString("GL37START"));
                        }
                        libOpen = String.valueOf(currentTime.format(formatterDate)) + libOpen;
                        openTime = LocalDateTime.parse(libOpen);
                        currentTime = openTime.plus(duration.abs());
                        this.msRawTimeDue = currentTime.format(formatForTime);
                        break block14;
                    }
                    date = cal.getTime();
                    String duedate = dateFormat.format(date).toString();
                    this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
                    this.msRawTimeDue = currentTime.format(formatForTime);
                }
                catch (SQLException sQLException) {
                    try {
                        conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.msRawTimeDue;
    }

    private String getTime_V1(int loanPeriod, LocalDateTime currentTime, LocalDate localDate) throws ParseException {
        block12: {
            try {
                try {
                    Locale locale = Locale.US;
                    DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
                    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(locale);
                    String day = localDate.format(formatterOutput);
                    day = Handler.getDay(day);
                    Calendar calendar1 = Calendar.getInstance();
                    boolean isHoliday = false;
                    isHoliday = this.isWorkingDayorHoliday_V1(calendar1, this.msRetItemBranch);
                    String libEnd = CirculationSQL.getMsLibClose(this.msRetItemBranch, day);
                    int count = 0;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(libEnd);
                    if (!rsObj.next()) break block12;
                    String libClose = DateTime.convertLibraryTiming(rsObj.getString("GL37STOP"));
                    libClose = String.valueOf(DateTime.getSysTodayDate()) + "T" + libClose;
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DateTimeFormatter formatForTime = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime closeTime = LocalDateTime.parse(libClose);
                    LocalDateTime addEligTimeToCurrentTime = currentTime.plus(loanPeriod, ChronoUnit.HOURS);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Calendar cal = Calendar.getInstance();
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.clear();
                    calendar.set(currentTime.getYear(), currentTime.getMonthValue() - 1, currentTime.getDayOfMonth());
                    if (addEligTimeToCurrentTime.isAfter(closeTime)) {
                        Duration duration = Duration.between(addEligTimeToCurrentTime, closeTime);
                        int isholiday = 0;
                        isholiday = this.isWorkingDayorHoliday_v1(calendar, this.msRetItemBranch, count);
                        String duedate = this.calDueDate(calendar.getTime());
                        cal.add(5, isholiday);
                        date = cal.getTime();
                        duedate = dateFormat.format(date).toString();
                        this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
                        LocalDateTime openTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        day = openTime.format(formatterOutput);
                        day = Handler.getDay(day);
                        String libStart = CirculationSQL.getMsLibOpen_V1(this.msRetItemBranch, day);
                        String libOpen = DateTime.convertLibraryTiming(libStart);
                        libOpen = String.valueOf(openTime.format(formatterDate)) + "T" + libOpen;
                        openTime = LocalDateTime.parse(libOpen);
                        currentTime = openTime.plus(duration.abs());
                        this.msRawTimeDue = currentTime.format(formatForTime);
                        break block12;
                    }
                    date = cal.getTime();
                    String duedate = dateFormat.format(date).toString();
                    this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
                    this.msRawTimeDue = currentTime.plusHours(loanPeriod).format(formatForTime);
                }
                catch (SQLException sQLException) {
                    try {
                        conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.msRawTimeDue;
    }

    private int isWorkingDayorHoliday(Calendar cal, String msRetItemBranch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        try {
            String sql2 = "SELECT Count(*) as count FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
            conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                rows = Integer.parseInt(rsObj.getString("count"));
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return rows;
    }

    private int isWorkingDayorHoliday_v1(Calendar cal, String msRetItemBranch, int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        int incrementDay = 1;
        Date nextDay = new Date();
        cal.add(5, incrementDay);
        nextDay = cal.getTime();
        String duedate = dateFormat.format(nextDay).toString();
        boolean next = false;
        boolean stop = false;
        boolean holiday = false;
        boolean libClose = false;
        try {
            while (!stop) {
                String sql2 = "SELECT Count(*) as count FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
                conn = DBConnection.getConnection();
                Statement st = conn.createStatement();
                rsObj = st.executeQuery(sql2);
                System.out.println(sql2);
                next = rsObj.next();
                if (next) {
                    int result = Integer.parseInt(rsObj.getString("count"));
                    holiday = result > 0;
                    Locale locale = Locale.US;
                    DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
                    LocalDateTime openTime = nextDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    String day = openTime.format(formatterOutput);
                    day = Handler.getDay(day);
                    String sql3 = "SELECT GL37START FROM GLTIME WHERE GL37BRNC='" + msRetItemBranch + "' AND GL37DAY='" + day + "'";
                    String libStart = "";
                    try {
                        rsObj = st.executeQuery(sql3);
                        System.out.println(sql3);
                        if (rsObj.next()) {
                            libStart = rsObj.getString("GL37START");
                        }
                        libClose = libStart == "";
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                if (!holiday && !libClose) {
                    ++count;
                    break;
                }
                ++count;
                cal.add(5, incrementDay);
                nextDay = cal.getTime();
                duedate = dateFormat.format(nextDay).toString();
                stop = false;
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return count;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private int isWorkingDayorHoliday_v2(Calendar cal, String msRetItemBranch, int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date2 = new Date();
        date2 = cal.getTime();
        int incrementDay = 1;
        Date nextDay = new Date();
        cal.add(5, incrementDay);
        nextDay = cal.getTime();
        String duedate = dateFormat.format(nextDay).toString();
        boolean next = false;
        boolean checkLibClose = true;
        try {
            String sql2 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
            conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            rsObj = st.executeQuery(sql2);
            block4: while (true) {
                if (!checkLibClose) {
                    return count;
                }
                while (true) {
                    if (!(next = rsObj.next())) {
                        Locale locale = Locale.US;
                        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
                        Date date = new Date();
                        date = cal.getTime();
                        LocalDateTime openTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        String day = openTime.format(formatterOutput);
                        day = Handler.getDay(day);
                        String dueDate = dateFormat.format(date).toString();
                        String libStart = CirculationSQL.getMsLibOpen_V1(msRetItemBranch, String.valueOf(Integer.parseInt(day)));
                        int addDayIfLibClose = Integer.parseInt(day);
                        if (libStart == "") break;
                        ++count;
                        checkLibClose = false;
                        continue block4;
                    }
                    ++count;
                    cal.add(5, incrementDay);
                    nextDay = cal.getTime();
                    String duedate1 = dateFormat.format(nextDay).toString();
                    String sql3 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate1) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
                    rsObj = st.executeQuery(sql3);
                    System.out.println(sql3);
                }
                checkLibClose = true;
                ++count;
                cal.add(5, incrementDay);
                nextDay = cal.getTime();
                String duedate1 = dateFormat.format(nextDay).toString();
                String sql4 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate1) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
                rsObj = st.executeQuery(sql4);
                System.out.println(sql4);
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
                return count;
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return count;
    }

    private boolean checkLibIfClose(Calendar cal, String msRetItemBranch, int count) {
        Locale locale = Locale.US;
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        boolean incrementDay = true;
        Date date = new Date();
        date = cal.getTime();
        LocalDateTime openTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String day = openTime.format(formatterOutput);
        day = Handler.getDay(day);
        String duedate = dateFormat.format(date).toString();
        int dayIncrementLibClose = 0;
        String libStart = CirculationSQL.getMsLibOpen_V1(msRetItemBranch, String.valueOf(Integer.parseInt(day) + 1));
        int addDayIfLibClose = Integer.parseInt(day);
        if (libStart == "") {
            ++dayIncrementLibClose;
            return true;
        }
        return false;
    }

    private boolean isWorkingDayorHoliday_V1(Calendar cal, String msRetItemBranch) {
        int rows = 0;
        boolean isholiday = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        int incrementDay = 1;
        Date nextDay = new Date();
        cal.add(5, incrementDay);
        nextDay = cal.getTime();
        String duedate = dateFormat.format(nextDay).toString();
        try {
            String sql2 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
            conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                ++rows;
            }
            if (rows >= 1) {
                isholiday = true;
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return isholiday;
    }
}
