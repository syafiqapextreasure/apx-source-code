/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.template;

import com.kmlink.ilmu.cataloging.template.Cataloging;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SQLStatement {
    private Connection connection = DBConnection.getConnection();

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    public List<Cataloging> getAllTpl() throws SQLException {
        ArrayList<Cataloging> ctlist = new ArrayList<Cataloging>();
        ArrayList ctlist1 = new ArrayList();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Object stmt1 = null;
        Object rs1 = null;
        try {
            try {
                String query = " SELECT * FROM CTTMSTR";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Method[] methods;
                    Cataloging ct = new Cataloging();
                    ct.setTempId(rs.getInt("CT15SEQNO"));
                    ct.setTempName(rs.getString("CT15TNAME"));
                    ct.setStatus1(rs.getString("CT15STAT"));
                    ctlist.add(ct);
                    Class<SQLStatement> personClass = SQLStatement.class;
                    Method[] methodArray = methods = personClass.getDeclaredMethods();
                    int n = methods.length;
                    int n2 = 0;
                    while (n2 < n) {
                        Method method = methodArray[n2];
                        System.out.println(method.getName());
                        ++n2;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return ctlist;
    }

    public List<Cataloging> chkExist() throws SQLException {
        ArrayList<Cataloging> ctlist1 = new ArrayList<Cataloging>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "Select CT15SEQNo from CTTMSTR MT where exists (select CTMSTR from CTTPL OT where OT.CTMSTR = MT.CT15SEQNo)";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                System.out.println(query);
                while (rs.next()) {
                    Cataloging ct1 = new Cataloging();
                    ct1.setId(rs.getInt("CT15SEQNo"));
                    ctlist1.add(ct1);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return ctlist1;
    }

    public List<Cataloging> notExist() throws SQLException {
        ArrayList<Cataloging> ctlist1 = new ArrayList<Cataloging>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "Select CT15SEQNo from CTTMSTR MT where not exists (select CTMSTR from CTTPL OT where OT.CTMSTR = MT.CT15SEQNo)";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Cataloging ct1 = new Cataloging();
                    ct1.setId(rs.getInt("CT15SEQNo"));
                    System.out.println(rs.getInt("CT15SEQNo"));
                    ctlist1.add(ct1);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return ctlist1;
    }

    public List<Cataloging> getTplId() throws SQLException {
        ArrayList<Cataloging> list = new ArrayList<Cataloging>();
        try {
            String query = " Select max(CT15SEQNO)+1 from CTTMSTR";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Cataloging cat = new Cataloging();
                cat.setId(rs.getInt(1));
                list.add(cat);
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return list;
    }

    public List<Cataloging> getTemplate() throws SQLException {
        ArrayList<Cataloging> list = new ArrayList<Cataloging>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT * FROM CTTMSTR WHERE CT15STAT='A'";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Cataloging cat = new Cataloging();
                    cat.setTempId(rs.getInt("CT15SEQNO"));
                    cat.setTempName(rs.getString("CT15TNAME"));
                    list.add(cat);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return list;
    }

    public List<Cataloging> getTags() {
        ArrayList<Cataloging> list = new ArrayList<Cataloging>();
        try {
            String query = "SELECT GL17MARC, GL17TAG, GL17DESC, GL17MANDA, GL17REPEAT FROM GLTAGP WHERE GL17MARC='U'";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Cataloging cat = new Cataloging();
                cat.setMarc(rs.getString(1));
                cat.setTags(rs.getString(2));
                cat.setTagName(rs.getString(3));
                cat.setManda(rs.getString(4));
                cat.setRpt(rs.getString(5));
                list.add(cat);
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return list;
    }

    public List<Cataloging> getAllAcc() {
        ArrayList<Cataloging> acclist = new ArrayList<Cataloging>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT CT03DOCNO, CT03MATNO, CT03ICAT FROM CTDOCM where CT03STAT='F'";
            System.out.println(query);
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Cataloging ct = new Cataloging();
                ct.setDocNo(rs.getString("CT03DOCNO"));
                ct.setAccNo(rs.getString("CT03MATNO"));
                ct.setAccItemCat(rs.getString("CT03ICAT"));
                acclist.add(ct);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return acclist;
    }

    public Cataloging getTplByID(int seqNo) {
        Cataloging ct = new Cataloging();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("select * from CTTMSTR where CT15SEQNO=?");
            preparedStatement.setInt(1, seqNo);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ct.setId(rs.getInt("CT15SEQNO"));
                ct.setTempName(rs.getString("CT15TNAME"));
                ct.setStatus1(rs.getString("CT15STAT"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ct;
    }

    public Cataloging getTplInfo(int id) {
        Cataloging ct = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT CTTPLID, CTTPLN, CTTPLTAG, GL18INDILVL, CTTPLINDI1, CTTPLINDI2, GL18DESC1, CTTPLSUBF from CTTPL, GLMINDI where CTTPLTAG= GL18TAG and CTTPLID=" + id);
                while (rs.next()) {
                    ct = new Cataloging();
                    ct.setId(rs.getInt(1));
                    ct.setTempName(rs.getString(2));
                    ct.setTags(rs.getString(3));
                    ct.setIndiLvl(rs.getString(4));
                    ct.setInd1(rs.getString(5));
                    ct.setInd2(rs.getString(6));
                    ct.setIndiDesc(rs.getString(7));
                    ct.setSubfields(rs.getString(8));
                }
            }
            catch (SQLException sQLException) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
        return ct;
    }

    public Cataloging getTplInfo1(int id) {
        Cataloging ct = null;
        Cataloging ct1 = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Distinct [TPLID],[TPLNAME],[TAG],[CTTPLSUBF],[SUBFDESC] FROM [ILMU2015].[dbo].[BibRcrd] where [TPLID]=" + id);
                while (rs.next()) {
                    ct1 = new Cataloging();
                    ct1.setId(rs.getInt(1));
                    ct1.setTempName(rs.getString(2));
                    ct1.setTags(rs.getString(3));
                    ct1.setSubfields(rs.getString(4));
                    ct1.setSubfDesc(rs.getString(5));
                }
            }
            catch (SQLException sQLException) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
        return ct;
    }

    public void addTpl(Cataloging e) {
        Connection con = null;
        Statement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                int[] tplid = e.getTplId();
                String[] tplName = e.getTempName1();
                String[] status = e.getStatus();
                int i = 0;
                while (i < tplName.length) {
                    pstmt = con.prepareStatement("INSERT INTO CTTMSTR(CT15SEQNO, CT15TNAME, CT15STAT) VALUES(?,?,?)");
                    pstmt.setInt(1, tplid[i]);
                    pstmt.setString(2, tplName[i]);
                    pstmt.setString(3, status[i]);
                    pstmt.execute();
                    ++i;
                }
            }
            catch (SQLException sQLException) {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
    }

    public void addNew(Cataloging e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("INSERT INTO CTTPL(CTTPLN, CTTPLTAG, CTTPLINDI1, CTTPLINDI2, CTTPLSUBF, CTMSTR) VALUES(?,?,?,?,?,?)");
                pstmt.setString(1, e.getTempName());
                pstmt.setString(2, e.getTags());
                pstmt.setString(3, e.getInd1());
                pstmt.setString(4, e.getInd2());
                pstmt.setString(5, e.getSubfields());
                pstmt.setInt(6, e.getTempId());
                pstmt.execute();
            }
            catch (SQLException sQLException) {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
    }

    public void updateTpl(Cataloging e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE CTTMSTR SET CT15TNAME=?, CT15STAT=? WHERE CT15SEQNO=?");
                pstmt.setString(1, e.getTempName());
                pstmt.setString(2, e.getStatus1());
                pstmt.setInt(3, e.getId());
                pstmt.executeUpdate();
            }
            catch (SQLException sQLException) {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
    }

    public void update(Cataloging e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE CTTPL SET CTTPLINDI1=?, CTTPLINDI2=?, CTTPLSUBF=? WHERE CTTPLID=?");
                pstmt.setString(1, e.getInd1());
                pstmt.setString(2, e.getInd2());
                pstmt.setString(3, e.getSubfields());
                pstmt.setInt(4, e.getId());
                pstmt.executeUpdate();
            }
            catch (SQLException sQLException) {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
    }

    public void updateRelease(Cataloging e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE CTDOCM SET CT03STAT='A', CT03RFCDATE=CONVERT(VARCHAR(10), GETDATE(), 112) WHERE CT03DOCNO=?");
                pstmt.setString(1, e.getDocNo());
                System.out.println("ss");
                pstmt.executeUpdate();
            }
            catch (SQLException sQLException) {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
    }

    public void updateCIResv(Cataloging e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE CIRESV SET CI03NSTAT='A', CI03NDATE=CONVERT(VARCHAR(10), GETDATE(), 112), CI03DOCNO=?, CI03DDATE=? WHERE CI03MATNO=?");
                System.out.println("psp" + e.getDelayDate());
                pstmt.setString(1, e.getDocNo());
                pstmt.setString(2, e.getDelayDate());
                pstmt.setString(3, e.getAccNo());
                pstmt.executeUpdate();
                System.out.println("ss");
                pstmt.executeUpdate();
            }
            catch (SQLException sQLException) {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
    }

    public void delTpl(int id) {
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                stmt.execute("UPDATE CTTMSTR SET CT15STAT = replace(CT15STAT, 'A', 'I') WHERE CT15SEQNO=" + String.valueOf(id));
            }
            catch (SQLException sQLException) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
    }

    public void delBibId(int id) {
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                stmt.execute("DELETE FROM CTTPL WHERE CTTPLID=" + String.valueOf(id));
            }
            catch (SQLException sQLException) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
    }
}
