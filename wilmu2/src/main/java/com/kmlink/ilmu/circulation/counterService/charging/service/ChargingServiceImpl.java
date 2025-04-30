/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.JdbcTemplate
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.circulation.counterService.charging.service;

import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.circulation.Charging.ILL;
import com.kmlink.ilmu.circulation.Charging.Item;
import com.kmlink.ilmu.circulation.Global.Handler;
import com.kmlink.ilmu.circulation.Global.ISBD;
import com.kmlink.ilmu.circulation.counterService.charging.dao.AccessionDao;
import com.kmlink.ilmu.circulation.counterService.charging.dao.PatronDao;
import com.kmlink.ilmu.circulation.counterService.charging.model.Accession;
import com.kmlink.ilmu.circulation.counterService.charging.model.Charging;
import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;
import com.kmlink.ilmu.circulation.counterService.charging.service.ChargingService;
import com.kmlink.ilmu.circulation.sql.CirculationSQL;
import com.kmlink.ilmu.circulation.sql.ItemSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ChargingServiceImpl
implements ChargingService {
    private String patronStatus;
    private String patronCategory;
    private String patronExpDate;
    private String patronBranch;
    private String patronEligibility;
    private String msDocno;
    private String msNoCircByPatronByItem;
    private String msBorrowerName;
    private String msBorrowerID;
    private String matNo;
    private String itemStatus;
    private String itemCate;
    private String itemBranch;
    private String itemSMD;
    private String itemLocation;
    private String itemStatCode;
    private double msFineLimit;
    private int msLoanPeriod;
    private String msPtype;
    private String msAllowOvd;
    private String msRawDateCharged;
    private String msRawTimeCharged;
    private String msRawDateDue;
    private String msRawTimeDue;
    private String errmessage;
    private String printMessage;
    int GL27ELIG = 0;
    String output;
    String details;
    String status;
    int GL07ELIG = 0;
    String limit;
    private JdbcTemplate jdbcTemplate;
    PatronDao patronDao;
    AccessionDao accessionDao;
    Patron patron = new Patron();
    Accession accession = new Accession();
    Charging charging = new Charging();

    public ChargingServiceImpl(DataSource dataSoruce) {
        this.jdbcTemplate = new JdbcTemplate(dataSoruce);
    }

    @Override
    public Charging validateCharging(String accessionNo, String patronId, String action) {
        boolean count = false;
        this.patron = this.patronDao.findPatronById(patronId);
        this.patronStatus = this.patron.getPatronStatus();
        this.patronCategory = this.patron.getPatronCate();
        this.patronExpDate = this.patron.getPatronExpDate();
        this.patronBranch = this.patron.getPatronBranch();
        this.itemStatus = this.accession.getStatus();
        this.itemCate = this.accession.getItemCat();
        this.itemLocation = this.accession.getItemBranch();
        this.itemSMD = this.accession.getSMD();
        this.matNo = this.accession.getMatNo();
        String msBookTitle = "";
        try {
            Charging chr = new Charging();
            this.executeCharging(accessionNo, patronId, action);
            this.charging.setAccessionNo(accessionNo);
            String newDue = null;
            if (action.equals("overrideMemExp")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                cal.add(5, -1);
                int holiday = this.isWorkingDayorHoliday(cal, this.patronBranch);
                while (holiday > 0) {
                    cal.add(5, -1);
                    holiday = this.isWorkingDayorHoliday(cal, this.patronBranch);
                }
                newDue = dateFormat.format(cal.getTime());
                this.output = null;
            }
            if (this.output != null && !this.output.isEmpty()) {
                this.output = chr.getChargingErrorMsg();
            } else {
                String query = "SELECT * FROM CTDOCM, GLLOCA WHERE accessionNo =? AND CT03LOCA = GL05LOCA";
                String query1 = "SELECT CT05SRAW FROM CTTITL,CTPONT \r\n WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO =?";
                List<ISBD> isbd = ISBD.getPunctuation("245");
                msBookTitle = (String)this.jdbcTemplate.queryForObject(query1, new Object[]{this.matNo}, String.class);
                if (this.matNo != "" && msBookTitle != "") {
                    this.checkItemEligibility(this.patronCategory, this.itemBranch, this.itemSMD, this.itemCate);
                    if (this.msLoanPeriod == 0) {
                        this.output = "025";
                    } else {
                        System.out.println("Testis");
                        this.chargingdate();
                        if (!action.equals("overrideMemExp")) {
                            newDue = GeneralUtility.StrToDate(this.msRawDateDue);
                        }
                        this.output = String.valueOf(accessionNo) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(this.msRawDateCharged) + "\n" + GeneralUtility.StrToTime2(this.msRawTimeCharged) + "\n" + newDue + "\n" + this.msRawTimeDue;
                    }
                } else {
                    this.output = "174";
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.charging;
    }

    public boolean executeCharging(String accessionNo, String msPatronID, String chkMaxLoan) {
        int GL27ELIG = 0;
        int patronEligibility = 0;
        boolean patrCanChargeAccession = false;
        boolean bSucessful = false;
        Date date = new Date();
        try {
            if (this.checkAccessionIfExist(accessionNo)) {
                if (this.GetCircStatus(accessionNo).equals("A") || this.GetCircStatus(accessionNo).equals("H")) {
                    patrCanChargeAccession = this.CanPatronBorrowbyAcession(accessionNo, this.patronCategory);
                    if (patrCanChargeAccession) {
                        if (this.accession.getStatus().equals("H")) {
                            if (this.checkReservationStatus(msPatronID, accessionNo)) {
                                bSucessful = true;
                            } else {
                                this.printMessage = "This item is reserved by another patron";
                                this.errmessage = "023, " + Item.getReservedPatron(accessionNo);
                                bSucessful = false;
                            }
                        } else {
                            bSucessful = true;
                        }
                    }
                    System.out.println("Datess");
                } else {
                    String duedate = this.getCirculatedPatrDetails(accessionNo);
                    if (this.itemStatCode.equals("C") && this.msBorrowerID.equalsIgnoreCase(msPatronID)) {
                        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date due = sourceFormat.parse(GeneralUtility.StrToDate(duedate));
                        Date today = new Date();
                        this.errmessage = due.compareTo(date) < 0 ? "086," + this.msBorrowerName + ",@patronName" : "087," + this.msBorrowerName + ",@patronName";
                        System.out.println("Error" + this.errmessage);
                    } else if (this.status.trim().equals("Incoming Request")) {
                        System.out.println("Request Invalid");
                        bSucessful = true;
                    } else {
                        this.errmessage = "066," + this.status + ",@status";
                        System.out.println("Request Invalid1");
                        bSucessful = false;
                    }
                }
            } else {
                this.printMessage = "This item is not available for ciculation (Item status) :" + this.itemStatus;
                this.errmessage = "020";
                bSucessful = false;
            }
            if (bSucessful) {
                GL27ELIG = this.checkItemEligibility(this.patronCategory, this.itemLocation);
                if (GL27ELIG > 0) {
                    if (this.getCircSattelite()) {
                        if (this.itemLocation.equals(this.getPatrSatelitteBrnc(msPatronID))) {
                            bSucessful = true;
                        } else {
                            System.out.println("line 446 T1: " + this.itemLocation + Item.getPatrSatelitteBrnc(msPatronID));
                            bSucessful = false;
                            this.errmessage = "085";
                        }
                    } else {
                        bSucessful = true;
                    }
                    if (bSucessful) {
                        if (this.msAllowOvd.equals("Y") || this.patron.getPatronItemOverdue() == 0) {
                            bSucessful = chkMaxLoan.equals("chkPatrCateMaxLoan") ? this.checkMaxLoan_v1("chkPatrCateMaxLoan", msPatronID, GL27ELIG, accessionNo, patronEligibility, this.itemLocation) : (chkMaxLoan.equals("chkItemCategoryMaxLoan") ? this.checkMaxLoan_v1("chkItemCategoryMaxLoan", msPatronID, GL27ELIG, accessionNo, patronEligibility, this.itemLocation) : this.checkMaxLoan_v1("chkEligMaxLoan", msPatronID, GL27ELIG, accessionNo, patronEligibility, this.itemLocation));
                        } else {
                            bSucessful = false;
                            this.printMessage = "This patron is not eligible to borrow any item in this item category";
                            System.out.println("This patron is not eligible to borrow any item in this item category");
                            this.errmessage = "046";
                        }
                    }
                } else {
                    bSucessful = false;
                    this.printMessage = "This patron is not eligible to borrow any item in this item category";
                    System.out.println("This patron is not eligible to borrow any item in this item category");
                    this.errmessage = "039";
                }
                if (bSucessful && this.chkILLModExist()) {
                    if (this.patronCategory.equals(ILL.getILLFlag())) {
                        if (ILL.getchkILLAccession(accessionNo)) {
                            if (ILL.getAccessionStat(accessionNo).equals("G")) {
                                if (msPatronID.compareToIgnoreCase(ILL.getPatrID(accessionNo)) != 0) {
                                    bSucessful = false;
                                    this.printMessage = "Document is reserved to @patron";
                                    this.errmessage = "160," + ILL.getPatrID(accessionNo) + ",@patron";
                                } else {
                                    bSucessful = true;
                                }
                            } else {
                                bSucessful = true;
                            }
                        } else {
                            bSucessful = false;
                            this.printMessage = "Accession is not reserved for ILL body. Please perform ILL incoming request.";
                            this.errmessage = "159";
                        }
                    }
                } else {
                    bSucessful = true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    private int isWorkingDayorHoliday(Calendar cal, String itemBranch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        String query = "SELECT Count(*) as count FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC =?";
        rows = Integer.parseInt((String)this.jdbcTemplate.queryForObject(query, new Object[]{itemBranch}, String.class));
        return rows;
    }

    public void chargingdate() throws SQLException, ParseException {
        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        Date newDueDate = null;
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        this.charging.setChargeDate(currentdate);
        this.charging.setChargeTime(currentTime);
        this.msPtype = this.getPtype(this.patronCategory, this.itemBranch);
        this.msLoanPeriod = this.checkItemEligibility(this.patronCategory, this.itemBranch, this.itemSMD, this.itemCate);
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
            newDueDate = this.calDueDate1(date);
            duedate = dateFormat.format(newDueDate).toString();
            duetime = timeFormat.format(newDueDate).toString();
            this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
        }
        this.msRawDateCharged = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeCharged = GeneralUtility.TimetoStr(currentTime);
        this.charging.setDueDate(GeneralUtility.DatetoStr(currentdate));
        this.charging.setDueTime(GeneralUtility.TimetoStr(currentTime));
    }

    public int checkItemEligibility(String patronCategory, String patronBranch) {
        this.msLoanPeriod = 0;
        this.msAllowOvd = "";
        String query = "SELECT GL27PLOAN, GL27ELIG, GL27PTYPE,GL27ALLOWOVD FROM GLELIG WHERE GL27ICAT =?\r\n AND GL27SMD =? AND GL27CATE =? AND GL27BRNC =?";
        System.out.println(query);
        this.charging = (Charging)this.jdbcTemplate.queryForObject(query, new Object[]{this.itemCate, this.itemSMD, patronCategory, this.itemBranch}, (RowMapper)new RowMapper<Charging>(){

            public Charging mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChargingServiceImpl.this.GL27ELIG = rs.getInt("GL27ELIG");
                ChargingServiceImpl.this.msLoanPeriod = rs.getInt("GL27PLOAN");
                ChargingServiceImpl.this.msAllowOvd = rs.getString("GL27ALLOWOVD");
                return ChargingServiceImpl.this.charging;
            }
        });
        return this.GL27ELIG;
    }

    public int checkItemEligibility(String patronCategory, String patronBranch, String itemSMD, String itemCate) {
        String query = "SELECT GL27PLOAN FROM GLELIG WHERE GL27ICAT = ? \r\n AND GL27SMD =? AND GL27CATE =? \r\n AND GL27BRNC =?";
        System.out.println(query);
        this.msLoanPeriod = Integer.parseInt((String)this.jdbcTemplate.queryForObject(query, new Object[]{itemCate, itemSMD, patronCategory, this.itemBranch}, String.class));
        return this.msLoanPeriod;
    }

    public int checkPatronEligibility() {
        String query = "SELECT * from GLCATE where GL07CATE=?";
        System.out.println(query);
        this.charging = (Charging)this.jdbcTemplate.queryForObject(query, new Object[]{this.patronCategory}, (RowMapper)new RowMapper<Charging>(){

            public Charging mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChargingServiceImpl.this.GL07ELIG = rs.getInt("GL07ELIG");
                ChargingServiceImpl.this.limit = rs.getString("GL07FINELIMIT");
                if (ChargingServiceImpl.this.limit == null) {
                    ChargingServiceImpl.this.msFineLimit = 0.0;
                } else {
                    ChargingServiceImpl.this.msFineLimit = Double.parseDouble(rs.getString("GL07FINELIMIT"));
                }
                return ChargingServiceImpl.this.charging;
            }
        });
        return this.GL07ELIG;
    }

    public boolean RetrieveAccessionDetail(String accessionNo) {
        boolean exist = false;
        String query = "SELECT * FROM CTDOCM, GLLOCA WHERE CT03LOCA=GL05LOCA AND accessionNo =?";
        this.accession = (Accession)this.jdbcTemplate.queryForObject(query, new Object[]{accessionNo}, (RowMapper)new RowMapper<Accession>(){

            public Accession mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChargingServiceImpl.this.accession.setDocNo(rs.getString("accessionNo"));
                ChargingServiceImpl.this.accession.setStatus(rs.getString("CT03STAT"));
                ChargingServiceImpl.this.accession.setItemCat(rs.getString("CT03ICAT"));
                ChargingServiceImpl.this.accession.setMatNo(rs.getString("CT03MATNO"));
                ChargingServiceImpl.this.accession.setSMD(rs.getString("CT03SMD"));
                ChargingServiceImpl.this.accession.setLocation(rs.getString("CT03LOCA"));
                ChargingServiceImpl.this.accession.setPatron(rs.getString("CT03PATR"));
                ChargingServiceImpl.this.accession.setCondition(rs.getString("CT03DDATE"));
                ChargingServiceImpl.this.accession.setItemBranch(rs.getString("GL05BRNC"));
                return ChargingServiceImpl.this.accession;
            }
        });
        return exist;
    }

    public boolean checkAccessionIfExist(String accessionNo) {
        boolean exist = false;
        String query = "SELECT COUNT(*) FROM CTDOCM, GLLOCA WHERE CT03LOCA=GL05LOCA AND CT03DOCNO =?";
        int count = (Integer)this.jdbcTemplate.queryForObject(query, new Object[]{accessionNo}, Integer.class);
        if (count > 0) {
            exist = true;
        }
        return exist;
    }

    private String getPtype(String cate, String icat) {
        String ptype = "";
        String query = "SELECT GL27PTYPE FROM GLELIG WHERE GL27CATE =? and GL27ICAT=?\r\nand GL27SMD=? and GL27BRNC=?";
        System.out.println(query);
        ptype = (String)this.jdbcTemplate.queryForObject(query, new Object[]{this.patronCategory, this.itemCate, this.itemSMD, this.itemBranch}, String.class);
        return ptype;
    }

    public String getItemStatus(String accessionNo) {
        String query = "Select DESCRIPTION, CODE from CTDOCM, FNDCODE where CODE=CT03STAT and accessionNo=? AND FCODE='E'";
        System.out.println(query);
        this.accession = (Accession)this.jdbcTemplate.queryForObject(query, new Object[]{accessionNo}, (RowMapper)new RowMapper<Accession>(){

            public Accession mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChargingServiceImpl.this.itemStatCode = rs.getString("CODE");
                ChargingServiceImpl.this.status = rs.getString("DESCRIPTION");
                return ChargingServiceImpl.this.accession;
            }
        });
        return this.status;
    }

    public String getCirculatedPatrDetails(String accessionNo) {
        String query = "Select GL14PATR, GL14NAME, CI02DDATE from CICIRC, GLPATR where CI02PATR=GL14PATR and CI02FLAG = 'C' and CI02DOCNO =?";
        System.out.println(query);
        Patron result = (Patron)this.jdbcTemplate.queryForObject(query, new Object[]{accessionNo}, (RowMapper)new RowMapper<Patron>(){

            public Patron mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChargingServiceImpl.this.msBorrowerName = rs.getString("GL14NAME");
                ChargingServiceImpl.this.msBorrowerID = rs.getString("GL14PATR");
                ChargingServiceImpl.this.details = rs.getString("CI02DDATE");
                return null;
            }
        });
        return this.details;
    }

    public String GetCircStatus(String vsItemID) {
        String itemStatus = null;
        String query = "SELECT CT03STAT FROM CTDOCM WHERE CT03DOCNO =?";
        itemStatus = (String)this.jdbcTemplate.queryForObject(query, new Object[]{vsItemID}, String.class);
        return itemStatus;
    }

    public boolean CanPatronBorrowbyAcession(String accessionNo, String patronCategory) {
        boolean validate = false;
        String query = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03DOCNO=? \r\n AND  GL27CATE =? AND GL27ICAT = CT03ICAT \r\n AND GL27SMD = CT03SMD AND CT03LOCA = GL05LOCA AND GL27BRNC = GL05BRNC \r\n AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
        System.out.println(query);
        if (Integer.parseInt((String)this.jdbcTemplate.queryForObject(query, new Object[]{accessionNo, patronCategory}, String.class)) == 1) {
            validate = true;
        }
        return validate;
    }

    private Date calDueDate1(Date date) {
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        Date dueDate = null;
        String inclusive = "";
        String cirMode = "";
        String query = "SELECT TOP 1 GL28CIRCMODE FROM GLLIBR";
        cirMode = (String)this.jdbcTemplate.queryForObject(query, new Object[0], String.class);
        if (cirMode.equals("E")) {
            int totalHoliday = this.isHoliday(today, date, this.itemBranch);
            if (totalHoliday > 0) {
                cal.setTime(date);
                cal.add(5, totalHoliday);
                dueDate = cal.getTime();
            } else {
                dueDate = date;
            }
        } else {
            dueDate = date;
        }
        return dueDate;
    }

    private String getTime_V1(int loanPeriod, LocalDateTime currentTime, LocalDate localDate) throws ParseException {
        Locale locale = Locale.US;
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(locale);
        String day = localDate.format(formatterOutput);
        day = Handler.getDay(day);
        Calendar calendar1 = Calendar.getInstance();
        boolean isHoliday = false;
        isHoliday = this.isWorkingDayorHoliday_V1(calendar1, this.itemBranch);
        String libEnd = CirculationSQL.getMsLibClose(this.itemBranch, day);
        boolean count = false;
        return null;
    }

    private int isWorkingDayorHoliday_v1(Calendar cal, String itemBranch, int count) {
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
        while (!stop) {
            String query1 = "SELECT Count(*) as count FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC =?";
            System.out.println(query1);
            int result = Integer.parseInt((String)this.jdbcTemplate.queryForObject(query1, new Object[]{itemBranch}, String.class));
            holiday = result > 0;
            Locale locale = Locale.US;
            DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
            LocalDateTime openTime = nextDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            String day = openTime.format(formatterOutput);
            day = Handler.getDay(day);
            String query2 = "SELECT GL37START FROM GLTIME WHERE GL37BRNC=? AND GL37DAY='" + day + "'";
            String libStart = "";
            libStart = (String)this.jdbcTemplate.queryForObject(query2, new Object[]{itemBranch}, String.class);
            System.out.println(query2);
            libClose = libStart == "";
        }
        return 0;
    }

    public boolean checkMaxLoan_v1(String action, String msPatronID, int GL27ELIG, String accessionNo, int patronEligibility, String itemLocation) throws SQLException, ParseException {
        System.out.println("line 1743 checkMaxLoan_v1 GL27ELIG: " + GL27ELIG + " patronEligibility: " + patronEligibility);
        boolean bSucessful = true;
        switch (action) {
            case "chkEligMaxLoan": {
                bSucessful = this.chkEligMaxLoan_v1(action, msPatronID, GL27ELIG, accessionNo, patronEligibility);
                if (this.chkItemCateMaxLoan(action, msPatronID, GL27ELIG, accessionNo, patronEligibility, itemLocation)) {
                    bSucessful = true;
                    break;
                }
                bSucessful = false;
                break;
            }
            case "chkPatrCateMaxLoan": {
                if (this.chkPatrCateMaxLoan(action, msPatronID, GL27ELIG, accessionNo, patronEligibility)) {
                    if (this.chkItemCateMaxLoan(action, msPatronID, GL27ELIG, accessionNo, patronEligibility)) {
                        bSucessful = true;
                        break;
                    }
                    bSucessful = false;
                    break;
                }
                bSucessful = false;
                break;
            }
            case "chkItemCategoryMaxLoan": {
                bSucessful = this.chkItemCateMaxLoan(action, msPatronID, GL27ELIG, accessionNo, patronEligibility);
            }
            default: {
                System.out.println("Invalid grade");
            }
        }
        return bSucessful;
    }

    public boolean checkReservationStatus(String msPatronID, String accessionNo) {
        boolean reservationstatus = false;
        String sql = ItemSQL.getMsChkResvStat(msPatronID, accessionNo);
        return reservationstatus;
    }

    private boolean isWorkingDayorHoliday_V1(Calendar cal, String itemBranch) {
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
        String query = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC =?";
        System.out.println(query);
        rows = Integer.parseInt((String)this.jdbcTemplate.queryForObject(query, new Object[]{itemBranch}, String.class));
        if (rows >= 1) {
            isholiday = true;
        }
        return isholiday;
    }

    private String calDueDate(Date date) {
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        Object dueDate = null;
        String inclusive = "";
        String cirMode = "";
        return null;
    }

    public boolean chkEligMaxLoan_v1(String action, String patronId, int GL27ELIG, String accessionNo, int patronEligibility) {
        boolean bSucessful = false;
        String msNoCirc = this.patron.getNoCircByPatron();
        String totalNoItemCirculation = this.getTotalNoItemCirculation(patronId);
        if (patronEligibility == 0) {
            bSucessful = true;
        } else if (Integer.parseInt(msNoCirc) < patronEligibility && Integer.parseInt(totalNoItemCirculation) < patronEligibility) {
            bSucessful = true;
        } else {
            bSucessful = false;
            this.printMessage = "This patron has exceeded the charging count limit for this item category";
            this.errmessage = "026";
        }
        return bSucessful;
    }

    public boolean chkItemCateMaxLoan(String action, String msPatronID, int GL27ELIG, String accessionNo, int patronEligibility, String itemLocation) throws SQLException, ParseException {
        boolean bSucessful = false;
        String msNoCircByPatronByItemCate = this.getMsNoCircByPatronByItemCate(msPatronID, this.itemSMD, itemLocation);
        patronEligibility = this.checkItemCategory(accessionNo);
        if (GL27ELIG > Integer.parseInt(msNoCircByPatronByItemCate)) {
            this.chargingdate();
            SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date memExp = sourceFormat.parse(GeneralUtility.StrToDate(this.patronExpDate));
            Date dueDate = sourceFormat.parse(GeneralUtility.StrToDate(this.msRawDateDue));
            if (memExp.compareTo(dueDate) > 0) {
                bSucessful = true;
            } else {
                bSucessful = false;
                this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
                System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
                this.errmessage = "068";
            }
        } else {
            bSucessful = false;
            this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
            System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
            this.errmessage = "038";
        }
        return bSucessful;
    }

    public boolean chkPatrCateMaxLoan(String action, String msPatronID, int GL27ELIG, String accessionNo, int patronEligibility) {
        boolean bSucessful = false;
        String msNoCircByPatronCate = this.getMsNoCircByPatronCate(msPatronID);
        if (patronEligibility > Integer.parseInt(msNoCircByPatronCate) || patronEligibility == 0) {
            bSucessful = true;
        } else {
            bSucessful = false;
            this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
            System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
            this.errmessage = "026";
        }
        return bSucessful;
    }

    public boolean chkItemCateMaxLoan(String action, String msPatronID, int GL27ELIG, String accessionNo, int patronEligibility) throws SQLException, ParseException {
        boolean bSucessful = false;
        String msNoCircByPatronByItemCate = this.getMsNoCircByPatronByItemCate(msPatronID, this.itemSMD);
        patronEligibility = this.checkItemCategory(accessionNo);
        if (patronEligibility > Integer.parseInt(msNoCircByPatronByItemCate) || patronEligibility == 0) {
            this.chargingdate();
            SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date memExp = sourceFormat.parse(this.patronExpDate);
            Date dueDate = sourceFormat.parse(GeneralUtility.StrToDate(this.msRawDateDue));
            if (memExp.compareTo(dueDate) > 0) {
                bSucessful = true;
            } else {
                bSucessful = false;
                this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
                System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
                this.errmessage = "068";
            }
        } else {
            bSucessful = false;
            this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
            System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
            this.errmessage = "084," + this.itemCate + ",@itemcate";
        }
        return bSucessful;
    }

    private String getTotalNoItemCirculation(String msPatronID) {
        String query = "SELECT COUNT(*) as TOTALITEMCIRCULATING FROM CICIRC WHERE CI02PATR =? AND CI02FLAG = 'C' ";
        String totalNumberItemCirculating = "";
        totalNumberItemCirculating = (String)this.jdbcTemplate.queryForObject(query, new Object[]{msPatronID}, String.class);
        if (totalNumberItemCirculating.equals(null)) {
            totalNumberItemCirculating = "0";
        }
        return totalNumberItemCirculating;
    }

    public int checkItemCategory(String accessionNo) {
        String query = "Select GL10ELIG from GLICAT, CTDOCM where GL10ICAT = CT03ICAT and CT03DOCNO =?";
        int elig = 0;
        System.out.println(query);
        if ((String)this.jdbcTemplate.queryForObject(query, new Object[]{accessionNo}, String.class) == null) {
            elig = 0;
        }
        return elig;
    }

    public String getMsNoCircByPatronByItemCate(String patronId, String itemSMD, String itemBranch) {
        String query = "SELECT COUNT(*) AS CIRCULATEDNOITEM FROM CICIRC, CTDOCM , GLLOCA WHERE CT03DOCNO = CI02DOCNO AND CI02PATR=?\r\n AND CT03LOCA = GL05LOCA AND GL05BRNC =?  AND CT03ICAT =? AND CT03SMD =? \r\nAND CI02FLAG='C'";
        this.msNoCircByPatronByItem = (String)this.jdbcTemplate.queryForObject(query, new Object[]{patronId, itemSMD, itemBranch, itemSMD}, String.class);
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronCate(String msGL14PATR) {
        String query = "Select COUNT(*) as CIRCULATEDNOITEM from GLPATR, CICIRC where GL14PATR=CI02PATR and CI02FLAG = 'C' and GL14CATE='" + this.patronCategory + "' and GL14PATR=?";
        System.out.println(query);
        this.msNoCircByPatronByItem = (String)this.jdbcTemplate.queryForObject(query, new Object[]{msGL14PATR}, String.class);
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronByItemCate(String msPatronID, String itemSMD) {
        String query = "select COUNT(CT03ICAT) As CIRCULATEDNOITEM from CTDOCM,CICIRC where accessionNo=CI02DOCNO AND CI02FLAG='C' AND CI02PATR='" + msPatronID.trim() + "'" + " and CT03ICAT='" + this.itemCate + "' and CT03SMD='" + itemSMD + "'";
        System.out.println(query);
        this.msNoCircByPatronByItem = (String)this.jdbcTemplate.queryForObject(query, new Object[]{msPatronID, itemSMD}, String.class);
        return this.msNoCircByPatronByItem;
    }

    public int isHoliday(Date today, Date duedate, String itemBranch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        String startDate = dateFormat.format(today).toString();
        String duedates = dateFormat.format(duedate).toString();
        String query = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE (GL30DATE> '" + startDate + "'AND GL30DATE<='" + duedates + "') and GL30BRNC=?";
        rows = (Integer)this.jdbcTemplate.queryForObject(query, new Object[]{itemBranch}, Integer.class);
        return rows;
    }

    public boolean getCircSattelite() {
        boolean exist = false;
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC= 'CIRBYLOCATION'";
        if (((String)this.jdbcTemplate.queryForObject(query, new Object[0], String.class)).equals("Y")) {
            return true;
        }
        return exist;
    }

    private String getPatrSatelitteBrnc(String msPatronID) {
        String sql = "SELECT GL05BRNC FROM GLPATR,GLLOCA WHERE GL14PATR= '" + msPatronID + "' AND GL05BRNC=GL14BRNC";
        return null;
    }

    public boolean chkILLModExist() {
        boolean exist = false;
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='ILLMODULE'";
        try {
            if (((String)this.jdbcTemplate.queryForObject(query, new Object[0], String.class)).equals("Y")) {
                exist = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            exist = false;
        }
        return exist;
    }
}
