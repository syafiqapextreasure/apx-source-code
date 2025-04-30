/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.LibInfo;

import com.ilmu.foundation.global.Encrypter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibInfo {
    private String name;
    private String add1;
    private String add2;
    private String add3;
    private String poscode;
    private String town;
    private String tel;
    private String fax;
    private String headlib;
    private String opaclimit;
    private String msgdelay;
    private String orgname;
    private String libhead;
    private String libheadext;
    private String cathead;
    private String catext;
    private String irshead;
    private String irsext;
    private String cirhead;
    private String cirext;
    private String acqhead;
    private String acqext;
    private String serhead;
    private String serext;
    private String finhead;
    private String finext;
    private String prohead;
    private String proext;
    private String opachistory;
    private String inxtrash;
    private String marctype;
    private String keywflg;
    private String libweekend;
    private String logo;
    private String serialno;
    private String resvtime;
    private String acqclaims1;
    private String acqclaim2;
    private String acqclaim3;
    private String irsmarctype;
    private String circpurgedays;
    private String circpurgegrace;
    private String fund;
    private String orderspan;
    private String irsflag;
    private String notice1;
    private String notice2;
    private String notice3;
    private String notice4;
    private String acqbprint;
    private String serbprint;
    private String msgtimeout;
    private String timescost;
    private String rcost;
    private String loadall;
    private String newacond;
    private String newaddate;
    private String newadocs;
    private String newaicat;
    private String newaloca;
    private String newasmd;
    private String newaudate;
    private String libsymbol;
    private String serrpsum;
    private String serrpdet;
    private String circmode;
    private String newalcrmat;
    private String brnc;

    public String getname() {
        return Handler.ifIsNull(this.name);
    }

    public String getadd1() {
        return Handler.ifIsNull(this.add1);
    }

    public String getadd2() {
        return Handler.ifIsNull(this.add2);
    }

    public String getadd3() {
        return Handler.ifIsNull(this.add3);
    }

    public String getposcode() {
        return Handler.ifIsNull(this.poscode);
    }

    public String gettown() {
        return Handler.ifIsNull(this.town);
    }

    public String gettel() {
        return Handler.ifIsNull(this.tel);
    }

    public String getfax() {
        return Handler.ifIsNull(this.fax);
    }

    public String getheadlib() {
        return Handler.ifIsNull(this.headlib);
    }

    public String getopaclimit() {
        return Handler.ifIsNull(this.opaclimit);
    }

    public String getmsgdelay() {
        return Handler.ifIsNull(this.msgdelay);
    }

    public String getorgname() {
        return Handler.ifIsNull(this.orgname);
    }

    public String getlibhead() {
        return Handler.ifIsNull(this.libhead);
    }

    public String getlibheadext() {
        return Handler.ifIsNull(this.libheadext);
    }

    public String getcathead() {
        return Handler.ifIsNull(this.cathead);
    }

    public String getcatext() {
        return Handler.ifIsNull(this.catext);
    }

    public String getirshead() {
        return Handler.ifIsNull(this.irshead);
    }

    public String getirsext() {
        return Handler.ifIsNull(this.irsext);
    }

    public String getcirhead() {
        return Handler.ifIsNull(this.cirhead);
    }

    public String getcirext() {
        return Handler.ifIsNull(this.cirext);
    }

    public String getacqhead() {
        return Handler.ifIsNull(this.acqhead);
    }

    public String getacqext() {
        return Handler.ifIsNull(this.acqext);
    }

    public String getserhead() {
        return Handler.ifIsNull(this.serhead);
    }

    public String getserext() {
        return Handler.ifIsNull(this.serext);
    }

    public String getfinhead() {
        return Handler.ifIsNull(this.finhead);
    }

    public String getfinext() {
        return Handler.ifIsNull(this.finext);
    }

    public String getprohead() {
        return Handler.ifIsNull(this.prohead);
    }

    public String getproext() {
        return Handler.ifIsNull(this.proext);
    }

    public String getinxtrash() {
        return Handler.ifIsNull(this.inxtrash);
    }

    public String getopachistory() {
        return Handler.ifIsNull(this.opachistory);
    }

    public String getmarctype() {
        return Handler.ifIsNull(this.marctype);
    }

    public String getkeywflg() {
        return Handler.ifIsNull(this.keywflg);
    }

    public String getlibweekend() {
        return Handler.ifIsNull(this.libweekend);
    }

    public String getlogo() {
        return Handler.ifIsNull(this.logo);
    }

    public String getserialno() {
        return Handler.ifIsNull(this.serialno);
    }

    public String getresvtime() {
        return Handler.ifIsNull(this.resvtime);
    }

    public String getacqclaims1() {
        return Handler.ifIsNull(this.acqclaims1);
    }

    public String getacqclaim2() {
        return Handler.ifIsNull(this.acqclaim2);
    }

    public String getacqclaim3() {
        return Handler.ifIsNull(this.acqclaim3);
    }

    public String getirsmarctype() {
        return Handler.ifIsNull(this.irsmarctype);
    }

    public String getcircpurgedays() {
        return Handler.ifIsNull(this.circpurgedays);
    }

    public String getcircpurgegrace() {
        return Handler.ifIsNull(this.circpurgegrace);
    }

    public String getfund() {
        return Handler.ifIsNull(this.fund);
    }

    public String getorderspan() {
        return Handler.ifIsNull(this.orderspan);
    }

    public String getirsflag() {
        return Handler.ifIsNull(this.irsflag);
    }

    public String getnotice1() {
        return Handler.ifIsNull(this.notice1);
    }

    public String getnotice2() {
        return Handler.ifIsNull(this.notice2);
    }

    public String getnotice3() {
        return Handler.ifIsNull(this.notice3);
    }

    public String getnotice4() {
        return Handler.ifIsNull(this.notice4);
    }

    public String getacqbprint() {
        return Handler.ifIsNull(this.acqbprint);
    }

    public String getserbprint() {
        return Handler.ifIsNull(this.serbprint);
    }

    public String getmsgtimeout() {
        return Handler.ifIsNull(this.msgtimeout);
    }

    public String gettimescost() {
        return Handler.ifIsNull(this.timescost);
    }

    public String getrcost() {
        return Handler.ifIsNull(this.rcost);
    }

    public String getloadall() {
        return Handler.ifIsNull(this.loadall);
    }

    public String getnewacond() {
        return Handler.ifIsNull(this.newacond);
    }

    public String getnewaddate() {
        return Handler.ifIsNull(this.newaddate);
    }

    public String getnewadocs() {
        return Handler.ifIsNull(this.newadocs);
    }

    public String getnewaicat() {
        return Handler.ifIsNull(this.newaicat);
    }

    public String getnewaloca() {
        return Handler.ifIsNull(this.newaloca);
    }

    public String getnewasmd() {
        return Handler.ifIsNull(this.newasmd);
    }

    public String getnewaudate() {
        return Handler.ifIsNull(this.newaudate);
    }

    public String getlibsymbol() {
        return Handler.ifIsNull(this.libsymbol);
    }

    public String getserrpsum() {
        return Handler.ifIsNull(this.serrpsum);
    }

    public String getserrpdet() {
        return Handler.ifIsNull(this.serrpdet);
    }

    public String getcircmode() {
        return Handler.ifIsNull(this.circmode);
    }

    public String getnewalcrmat() {
        return Handler.ifIsNull(this.newalcrmat);
    }

    public String getbrnc() {
        return Handler.ifIsNull(this.brnc);
    }

    public LibInfo(String name, String orgname, String libsymbol, String brnc, String add1, String add2, String add3, String poscode, String town, String tel, String fax, String libhead, String libheadext, String prohead, String proext, String logo, String acqhead, String acqext, String acqclaims1, String acqclaim2, String acqclaim3, String orderspan, String acqbprint, String cathead, String catext, String marctype, String inxtrash, String cirhead, String cirext, String libweekend, String resvtime, String rcost, String timescost, String circmode, String opaclimit, String loadall, String newaudate, String newalcrmat, String msgtimeout, String opachistory, String newaddate, String newaloca, String newaicat, String newacond, String newasmd, String newadocs, String serhead, String serext, String serbprint, String irshead, String irsext, String irsmarctype, String irsflag, String finhead, String finext, String fund) {
        this.name = name;
        this.orgname = orgname;
        this.libsymbol = libsymbol;
        this.brnc = brnc;
        this.add1 = add1;
        this.add2 = add2;
        this.add3 = add3;
        this.poscode = poscode;
        this.town = town;
        this.tel = tel;
        this.fax = fax;
        this.libhead = libhead;
        this.libheadext = libheadext;
        this.prohead = prohead;
        this.proext = proext;
        this.logo = logo;
        this.acqhead = acqhead;
        this.acqext = acqext;
        this.acqclaims1 = acqclaims1;
        this.acqclaim2 = acqclaim2;
        this.acqclaim3 = acqclaim3;
        this.orderspan = orderspan;
        this.acqbprint = acqbprint;
        this.cathead = cathead;
        this.catext = catext;
        this.marctype = marctype;
        this.inxtrash = inxtrash;
        this.cirhead = cirhead;
        this.cirext = cirext;
        this.libweekend = libweekend;
        this.resvtime = resvtime;
        this.rcost = rcost;
        this.timescost = timescost;
        this.circmode = circmode;
        this.opaclimit = opaclimit;
        this.loadall = loadall;
        this.newaudate = newaudate;
        this.newalcrmat = newalcrmat;
        this.msgtimeout = msgtimeout;
        this.opachistory = opachistory;
        this.newaddate = newaddate;
        this.newaloca = newaloca;
        this.newaicat = newaicat;
        this.newacond = newacond;
        this.newasmd = newasmd;
        this.newadocs = newadocs;
        this.serhead = serhead;
        this.serext = serext;
        this.serbprint = serbprint;
        this.irshead = irshead;
        this.irsext = irsext;
        this.irsmarctype = irsmarctype;
        this.irsflag = irsflag;
        this.finhead = finhead;
        this.finext = finext;
        this.fund = fund;
    }

    public static List<LibInfo> detailLibr() {
        ArrayList<LibInfo> list = new ArrayList<LibInfo>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (dbtype.getDBType().equals("mssql")) {
            System.out.println("sql here");
            query = "SELECT TOP 1 GL28NAME, GL28ORGNAME, GL28LIBSYMBOL, GL28BRNC, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, GL28TOWN, GL28TEL, GL28FAX, GL28LIBHEAD, GL28LIBHEADEXT, GL28PROHEAD, GL28PROEXT, GL28LOGO, GL28ACQHEAD, GL28ACQEXT, GL28ACQCLAIMS1, GL28ACQCLAIM2, GL28ACQCLAIM3, GL28ORDERSPAN, GL28ACQBPRINT, GL28CATHEAD, GL28CATEXT, GL28MARCTYPE, GL28INXTRASH, GL28CIRHEAD, GL28CIREXT,GL28LIBWEEKEND, GL28RESVTIME, GL28RCOST, GL28TIMESCOST, GL28CIRCMODE, GL28OPACLIMIT, GL28LOADALL, GL28NEWAUDATE, GL28NEWALCRMAT, GL28MSGTIMEOUT, GL28OPACHISTORY, GL28NEWADDATE, GL28NEWALOCA, GL28NEWAICAT, GL28NEWACOND, GL28NEWASMD, GL28NEWADOCS, GL28SERHEAD, GL28SEREXT, GL28SERBPRINT, GL28IRSHEAD, GL28IRSEXT, GL28IRSMARCTYPE, GL28IRSFLAG, GL28FINHEAD, GL28FINEXT, GL28FUND FROM GLLIBR ";
        } else if (dbtype.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            query = "SELECT GL28NAME, GL28ORGNAME, GL28LIBSYMBOL, GL28BRNC, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, GL28TOWN, GL28TEL, GL28FAX, GL28LIBHEAD, GL28LIBHEADEXT, GL28PROHEAD, GL28PROEXT, GL28LOGO, GL28ACQHEAD, GL28ACQEXT, GL28ACQCLAIMS1, GL28ACQCLAIM2, GL28ACQCLAIM3, GL28ORDERSPAN, GL28ACQBPRINT, GL28CATHEAD, GL28CATEXT, GL28MARCTYPE, GL28INXTRASH, GL28CIRHEAD, GL28CIREXT,GL28LIBWEEKEND, GL28RESVTIME, GL28RCOST, GL28TIMESCOST, GL28CIRCMODE, GL28OPACLIMIT, GL28LOADALL, GL28NEWAUDATE, GL28NEWALCRMAT, GL28MSGTIMEOUT, GL28OPACHISTORY, GL28NEWADDATE, GL28ORDERSPAN, GL28NEWALOCA, GL28NEWAICAT, GL28NEWACOND, GL28NEWASMD, GL28NEWADOCS, GL28SERHEAD, GL28SEREXT, GL28SERBPRINT, GL28IRSHEAD, GL28IRSEXT, GL28IRSMARCTYPE, GL28IRSFLAG, GL28FINHEAD, GL28FINEXT, GL28FUND FROM GLLIBR WHERE rownum = 1";
        } else if (dbtype.getDBType().equals("mysql")) {
            System.out.println("MYSQL here");
            query = "SELECT GL28NAME, GL28ORGNAME, GL28LIBSYMBOL, GL28BRNC, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, GL28TOWN, GL28TEL, GL28FAX, GL28LIBHEAD, GL28LIBHEADEXT, GL28PROHEAD, GL28PROEXT, GL28LOGO, GL28ACQHEAD, GL28ACQEXT, GL28ACQCLAIMS1, GL28ACQCLAIM2, GL28ACQCLAIM3, GL28ORDERSPAN, GL28ACQBPRINT, GL28CATHEAD, GL28CATEXT, GL28MARCTYPE, GL28INXTRASH, GL28CIRHEAD, GL28CIREXT,GL28LIBWEEKEND, GL28RESVTIME, GL28RCOST, GL28TIMESCOST, GL28CIRCMODE, GL28OPACLIMIT, GL28LOADALL, GL28NEWAUDATE, GL28NEWALCRMAT, GL28MSGTIMEOUT, GL28OPACHISTORY, GL28NEWADDATE, GL28NEWALOCA, GL28NEWAICAT, GL28NEWACOND, GL28NEWASMD, GL28NEWADOCS, GL28SERHEAD, GL28SEREXT, GL28SERBPRINT, GL28IRSHEAD, GL28IRSEXT, GL28IRSMARCTYPE, GL28IRSFLAG, GL28FINHEAD, GL28FINEXT, GL28FUND FROM GLLIBR LIMIT 1";
        }
        System.out.println("query detailLibr : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String sLibName = Handler.ifIsNull(rs.getString("GL28NAME"));
                    String getLibName = Encrypter.encrypt(sLibName);
                    LibInfo loadtabledetail = new LibInfo(getLibName, Handler.ifIsNull(rs.getString("GL28ORGNAME")), Handler.ifIsNull(rs.getString("GL28LIBSYMBOL")), Handler.ifIsNull(rs.getString("GL28BRNC")), Handler.ifIsNull(rs.getString("GL28ADD1")), Handler.ifIsNull(rs.getString("GL28ADD2")), Handler.ifIsNull(rs.getString("GL28ADD3")), Handler.ifIsNull(rs.getString("GL28POSCODE")), Handler.ifIsNull(rs.getString("GL28TOWN")), Handler.ifIsNull(rs.getString("GL28TEL")), Handler.ifIsNull(rs.getString("GL28FAX")), Handler.ifIsNull(rs.getString("GL28LIBHEAD")), Handler.ifIsNull(rs.getString("GL28LIBHEADEXT")), Handler.ifIsNull(rs.getString("GL28PROHEAD")), Handler.ifIsNull(rs.getString("GL28PROEXT")), Handler.ifIsNull(rs.getString("GL28LOGO")), Handler.ifIsNull(rs.getString("GL28ACQHEAD")), Handler.ifIsNull(rs.getString("GL28ACQEXT")), Handler.ifIsNull(rs.getString("GL28ACQCLAIMS1")), Handler.ifIsNull(rs.getString("GL28ACQCLAIM2")), Handler.ifIsNull(rs.getString("GL28ACQCLAIM3")), Handler.ifIsNull(rs.getString("GL28ORDERSPAN")), Handler.ifIsNull(rs.getString("GL28ACQBPRINT")), Handler.ifIsNull(rs.getString("GL28CATHEAD")), Handler.ifIsNull(rs.getString("GL28CATEXT")), Handler.ifIsNull(rs.getString("GL28MARCTYPE")), Handler.ifIsNull(rs.getString("GL28INXTRASH")), Handler.ifIsNull(rs.getString("GL28CIRHEAD")), Handler.ifIsNull(rs.getString("GL28CIREXT")), Handler.ifIsNull(rs.getString("GL28LIBWEEKEND")), Handler.ifIsNull(rs.getString("GL28RESVTIME")), Handler.ifIsNull(rs.getString("GL28RCOST")), Handler.ifIsNull(rs.getString("GL28TIMESCOST")), Handler.ifIsNull(rs.getString("GL28CIRCMODE")), Handler.ifIsNull(rs.getString("GL28OPACLIMIT")), Handler.ifIsNull(rs.getString("GL28LOADALL")), Handler.ifIsNull(rs.getString("GL28NEWAUDATE")), Handler.ifIsNull(rs.getString("GL28NEWALCRMAT")), Handler.ifIsNull(rs.getString("GL28MSGTIMEOUT")), Handler.ifIsNull(rs.getString("GL28OPACHISTORY")), Handler.ifIsNull(rs.getString("GL28NEWADDATE")), Handler.ifIsNull(rs.getString("GL28NEWALOCA")), Handler.ifIsNull(rs.getString("GL28NEWAICAT")), Handler.ifIsNull(rs.getString("GL28NEWACOND")), Handler.ifIsNull(rs.getString("GL28NEWASMD")), Handler.ifIsNull(rs.getString("GL28NEWADOCS")), Handler.ifIsNull(rs.getString("GL28SERHEAD")), Handler.ifIsNull(rs.getString("GL28SEREXT")), Handler.ifIsNull(rs.getString("GL28SERBPRINT")), Handler.ifIsNull(rs.getString("GL28IRSHEAD")), Handler.ifIsNull(rs.getString("GL28IRSEXT")), Handler.ifIsNull(rs.getString("GL28IRSMARCTYPE")), Handler.ifIsNull(rs.getString("GL28IRSFLAG")), Handler.ifIsNull(rs.getString("GL28FINHEAD")), Handler.ifIsNull(rs.getString("GL28FINEXT")), Handler.ifIsNull(rs.getString("GL28FUND")));
                    list.add(loadtabledetail);
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
        return list;
    }

    public static boolean addLibInfo(String libName, String orgName, String libSymbol, String brncCode, String add1, String add2, String add3, String postcode, String town, String tel, String fax, String headLib, String headLibExt, String profficer, String profficerExt, String acqhead, String acqext, String gp1, String gp2, String gp3, String orderspan, String acqprint, String cathead, String catext, String marctype, String itt, String cirhead, String cirext, String libweekend, String resvtime, String rcost, String timecost, String circmode, String opaclimit, String loadAll, String newEnable, String newInc, String opacmsgtime, String sercHistory, String serHead, String serext, String serbprint, String irsHead, String irsext, String irstype, String irsflag, String finHead, String finext, String fund, String DateConfiguration, String Locationlist, String ItemCategorylist, String Conditionlist, String SMDlist, String Statuslist, String filePart) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("GL28NAME", Encrypter.encrypt(libName));
        valueStr.put("GL28ORGNAME", orgName);
        valueStr.put("GL28LIBSYMBOL", libSymbol);
        valueStr.put("GL28BRNC", brncCode);
        valueStr.put("GL28ADD1", add1);
        valueStr.put("GL28ADD2", add2);
        valueStr.put("GL28ADD3", add3);
        valueStr.put("GL28POSCODE", postcode);
        valueStr.put("GL28TOWN", town);
        valueStr.put("GL28TEL", tel);
        valueStr.put("GL28FAX", fax);
        valueStr.put("GL28LIBHEAD", headLib);
        valueStr.put("GL28LIBHEADEXT", headLibExt);
        valueStr.put("GL28PROHEAD", profficer);
        valueStr.put("GL28PROEXT", profficerExt);
        valueStr.put("GL28ACQHEAD", acqhead);
        valueStr.put("GL28ACQEXT", acqext);
        valueStr.put("GL28ACQCLAIMS1", gp1);
        valueStr.put("GL28ACQCLAIM2", gp2);
        valueStr.put("GL28ACQCLAIM3", gp3);
        valueStr.put("GL28ORDERSPAN", orderspan);
        valueStr.put("GL28ACQBPRINT", acqprint);
        valueStr.put("GL28CATHEAD", cathead);
        valueStr.put("GL28CATEXT", catext);
        valueStr.put("GL28MARCTYPE", marctype);
        valueStr.put("GL28INXTRASH", itt);
        valueStr.put("GL28CIRHEAD", cirhead);
        valueStr.put("GL28CIREXT", cirext);
        valueStr.put("GL28LIBWEEKEND", libweekend);
        valueStr.put("GL28RESVTIME", resvtime);
        valueStr.put("GL28RCOST", rcost);
        valueStr.put("GL28TIMESCOST", timecost);
        valueStr.put("GL28CIRCMODE", circmode);
        valueStr.put("GL28OPACLIMIT", opaclimit);
        valueStr.put("GL28LOADALL", loadAll);
        valueStr.put("GL28NEWAUDATE", newEnable);
        valueStr.put("GL28NEWALCRMAT", newInc);
        valueStr.put("GL28MSGTIMEOUT", opacmsgtime);
        valueStr.put("GL28OPACHISTORY", sercHistory);
        valueStr.put("GL28NEWADDATE", DateConfiguration);
        valueStr.put("GL28NEWALOCA", Locationlist.replaceAll("'", "''"));
        valueStr.put("GL28NEWAICAT", ItemCategorylist.replaceAll("'", "''"));
        valueStr.put("GL28NEWACOND", Conditionlist.replaceAll("'", "''"));
        valueStr.put("GL28NEWASMD", SMDlist.replaceAll("'", "''"));
        valueStr.put("GL28NEWADOCS", Statuslist.replaceAll("'", "''"));
        valueStr.put("GL28SERHEAD", serHead);
        valueStr.put("GL28SEREXT", serext);
        valueStr.put("GL28SERBPRINT", serbprint);
        valueStr.put("GL28IRSHEAD", irsHead);
        valueStr.put("GL28IRSEXT", irsext);
        valueStr.put("GL28IRSMARCTYPE", irstype);
        valueStr.put("GL28IRSFLAG", irsflag);
        valueStr.put("GL28FINHEAD", finHead);
        valueStr.put("GL28FINEXT", finext);
        valueStr.put("GL28FUND", fund);
        String query = QueryBuilder.createInsertQuery("GLLIBR", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean editLibInfo(String libName, String orgName, String libSymbol, String brncCode, String add1, String add2, String add3, String postcode, String town, String tel, String fax, String headLib, String headLibExt, String profficer, String profficerExt, String acqhead, String acqext, String gp1, String gp2, String gp3, String orderspan, String acqprint, String cathead, String catext, String marctype, String itt, String cirhead, String cirext, String libweekend, String resvtime, String rcost, String timecost, String circmode, String opaclimit, String loadAll, String newEnable, String newInc, String opacmsgtime, String sercHistory, String serHead, String serext, String serbprint, String irsHead, String irsext, String irstype, String irsflag, String finHead, String finext, String fund, String DateConfiguration, String Locationlist, String ItemCategorylist, String Conditionlist, String SMDlist, String Statuslist, String filePart) throws IOException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        String convertLibName = Encrypter.encrypt(libName);
        condition.put("GL28ORGNAME", orgName);
        valueStr.put("GL28LIBSYMBOL", libSymbol);
        valueStr.put("GL28BRNC", brncCode);
        valueStr.put("GL28ADD1", add1);
        valueStr.put("GL28ADD2", add2);
        valueStr.put("GL28ADD3", add3);
        valueStr.put("GL28POSCODE", postcode);
        valueStr.put("GL28TOWN", town);
        valueStr.put("GL28TEL", tel);
        valueStr.put("GL28FAX", fax);
        valueStr.put("GL28LIBHEAD", headLib);
        valueStr.put("GL28LIBHEADEXT", headLibExt);
        valueStr.put("GL28PROHEAD", profficer);
        valueStr.put("GL28PROEXT", profficerExt);
        valueStr.put("GL28ACQHEAD", acqhead);
        valueStr.put("GL28ACQEXT", acqext);
        valueStr.put("GL28ACQCLAIMS1", gp1);
        valueStr.put("GL28ACQCLAIM2", gp2);
        valueStr.put("GL28ACQCLAIM3", gp3);
        valueStr.put("GL28ORDERSPAN", orderspan);
        valueStr.put("GL28ACQBPRINT", acqprint);
        valueStr.put("GL28CATHEAD", cathead);
        valueStr.put("GL28CATEXT", catext);
        valueStr.put("GL28MARCTYPE", marctype);
        valueStr.put("GL28INXTRASH", itt);
        valueStr.put("GL28CIRHEAD", cirhead);
        valueStr.put("GL28CIREXT", cirext);
        valueStr.put("GL28LIBWEEKEND", libweekend);
        valueStr.put("GL28RESVTIME", resvtime);
        valueStr.put("GL28RCOST", rcost);
        valueStr.put("GL28TIMESCOST", timecost);
        valueStr.put("GL28CIRCMODE", circmode);
        valueStr.put("GL28OPACLIMIT", opaclimit);
        valueStr.put("GL28LOADALL", loadAll);
        valueStr.put("GL28NEWAUDATE", newEnable);
        valueStr.put("GL28NEWALCRMAT", newInc);
        valueStr.put("GL28MSGTIMEOUT", opacmsgtime);
        valueStr.put("GL28OPACHISTORY", sercHistory);
        valueStr.put("GL28NEWADDATE", DateConfiguration);
        valueStr.put("GL28NEWALOCA", Locationlist.replaceAll("'", "''"));
        valueStr.put("GL28NEWAICAT", ItemCategorylist.replaceAll("'", "''"));
        valueStr.put("GL28NEWACOND", Conditionlist.replaceAll("'", "''"));
        valueStr.put("GL28NEWASMD", SMDlist.replaceAll("'", "''"));
        valueStr.put("GL28NEWADOCS", Statuslist.replaceAll("'", "''"));
        valueStr.put("GL28SERHEAD", serHead);
        valueStr.put("GL28SEREXT", serext);
        valueStr.put("GL28SERBPRINT", serbprint);
        valueStr.put("GL28IRSHEAD", irsHead);
        valueStr.put("GL28IRSEXT", irsext);
        valueStr.put("GL28IRSMARCTYPE", irstype);
        valueStr.put("GL28IRSFLAG", irsflag);
        valueStr.put("GL28FINHEAD", finHead);
        valueStr.put("GL28FINEXT", finext);
        valueStr.put("GL28FUND", fund);
        String query = QueryBuilder.createUpdateQuery("GLLIBR", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
