/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.global;

import com.ilmu.foundation.global.Encrypter;
import com.ilmu.foundation.global.Foundation;
import com.ilmu.foundation.global.libInfo;
import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GlobalSQLStatement {
    private static final String String = null;
    private Connection con = DBConnection.getConnection();

    public Foundation getCourse(String GL12COURSE) {
        Foundation emp = null;
        Object lib = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLCOUR WHERE GL12COURSE='" + GL12COURSE + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL12COURSE(rs.getString(1));
                    emp.setGL12DESC(Handler.ifIsNull(rs.getString(2)));
                    emp.setGL12TUTOR(Handler.ifIsNull(rs.getString(3)));
                    emp.setGL12DATERE(Handler.ifIsNull(rs.getString(4)));
                    emp.setGL12RECBY(Handler.ifIsNull(rs.getString(5)));
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
        return emp;
    }

    public void updateCourse(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLCOUR SET GL12DESC=?, GL12TUTOR=? WHERE GL12COURSE=?");
                pstmt.setString(1, e.getGL12DESC());
                pstmt.setString(2, e.getGL12TUTOR());
                pstmt.setString(3, e.getGL12COURSE());
                System.out.println("UPDATE GLCOUR SET GL12DESC='" + e.getGL12DESC() + "', GL12TUTOR='" + e.getGL12TUTOR() + "' WHERE GL12COURSE='" + e.getGL12COURSE() + "'");
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

    public Foundation getBrnchCode(String GL71BRNC, String CODE) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(" SELECT GL71BRNC, GL71DESC, GL71DISPLAY, GL71ADD1, GL71ADD2, GL71ADD3, GL71TOWN, GL71POSCODE, CODE, DESCRIPTION, GL71HTEL, GL71IPADD  FROM GLBRNC LEFT JOIN FNDCODE ON CODE = GL71TOWN AND FCODE = 'M' AND CODE = '" + CODE + "' WHERE GL71BRNC= '" + GL71BRNC + "' ");
                System.out.println("SELECT GL71BRNC, GL71DESC, GL71DISPLAY, GL71ADD1, GL71ADD2, GL71ADD3, GL71TOWN, GL71POSCODE, CODE, DESCRIPTION FROM GLBRNC LEFT JOIN FNDCODE ON CODE = GL71TOWN AND FCODE = 'M' AND CODE = '" + CODE + "' WHERE GL71BRNC= '" + GL71BRNC + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL71BRNC(rs.getString(1));
                    emp.setGL71DESC(rs.getString(2));
                    emp.setGL71DISPLAY(Handler.ifIsNull(rs.getString(3)));
                    emp.setGL71ADD1(Handler.ifIsNull(rs.getString(4)));
                    emp.setGL71ADD2(Handler.ifIsNull(rs.getString(5)));
                    emp.setGL71ADD3(Handler.ifIsNull(rs.getString(6)));
                    emp.setGL71TOWN(Handler.ifIsNull(rs.getString("GL71TOWN")));
                    emp.setGL71POSCODE(Handler.ifIsNull(rs.getString("GL71POSCODE")));
                    emp.setCODE(Handler.ifIsNull(rs.getString(9)));
                    emp.setDESCRIPTION(Handler.ifIsNull(rs.getString(10)));
                    emp.setHtel(Handler.ifIsNull(rs.getString("GL71HTEL")));
                    emp.setIPADD(Handler.ifIsNull(rs.getString("GL71IPADD")));
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
        return emp;
    }

    public void updateBrnch(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLBRNC SET GL71DESC=?, GL71DISPLAY=?, GL71ADD1=?, GL71ADD2=?, GL71ADD3=?, GL71TOWN=?,  GL71POSCODE=?, GL71HTEL =?, GL71IPADD=?  WHERE GL71BRNC=?");
                pstmt.setString(1, e.getGL71DESC());
                pstmt.setString(2, e.getGL71DISPLAY());
                pstmt.setString(3, e.getGL71ADD1());
                pstmt.setString(4, e.getGL71ADD2());
                pstmt.setString(5, e.getGL71ADD3());
                pstmt.setString(6, e.getGL71TOWN());
                pstmt.setString(7, e.getGL71POSCODE());
                pstmt.setString(8, e.getHtel());
                pstmt.setString(9, e.getIPADD());
                pstmt.setString(10, e.getGL71BRNC());
                System.out.println("ffdd" + pstmt);
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

    public Foundation getCodeTable(String CODE) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM FNDCODE WHERE CODE='" + CODE + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setFCODE(rs.getString(1));
                    emp.setFNAME(rs.getString(2));
                    emp.setCODE(rs.getString(3));
                    emp.setDESCRIPTION(rs.getString(4));
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
        return emp;
    }

    public void updateGlobal(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE FNDCODE SET DESCRIPTION=? WHERE CODE=? AND FCODE=?");
                pstmt.setString(1, e.getDESCRIPTION());
                pstmt.setString(2, e.getCODE());
                pstmt.setString(3, e.getFCODE());
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

    public Foundation getDeptCode(String GL11DEPT) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GL11DEPT, GL11NAME, GL11HEAD, GL11ADD1, GL11ADD2, GL11ADD3, GL11POSCODE, GL11TOWN, GL11TEL, GL11FAX, GL11STAFF, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE = GL11TOWN) AS DESCRIPTION, (SELECT GL14NAME FROM GLPATR WHERE GL14PATR = GL11HEAD) AS HEADNAME FROM GLDEPT WHERE GL11DEPT='" + GL11DEPT + "'");
                System.out.println("SELECT GL11DEPT, GL11NAME, GL11HEAD, GL11ADD1, GL11ADD2, GL11ADD3, GL11POSCODE, GL11TOWN, GL11TEL, GL11FAX, GL11STAFF, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE = GL11TOWN) AS DESCRIPTION FROM GLDEPT WHERE GL11DEPT='" + GL11DEPT + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL11DEPT(rs.getString(1));
                    emp.setGL11NAME(rs.getString(2));
                    emp.setGL11HEAD(Handler.ifIsNull(rs.getString(3)));
                    emp.setGL11ADD1(Handler.ifIsNull(rs.getString(4)));
                    emp.setGL11ADD2(Handler.ifIsNull(rs.getString(5)));
                    emp.setGL11ADD3(Handler.ifIsNull(rs.getString(6)));
                    emp.setGL11POSCODE(Handler.ifIsNull(rs.getString(7)));
                    emp.setGL11TOWN(Handler.ifIsNull(rs.getString(8)));
                    emp.setGL11TEL(Handler.ifIsNull(rs.getString(9)));
                    emp.setGL11FAX(Handler.ifIsNull(rs.getString(10)));
                    emp.setGL11STAFF(Integer.parseInt(rs.getString(11)));
                    emp.setDESCRIPTION(Handler.ifIsNull(rs.getString(12)));
                    emp.setHEADNAME(Handler.ifIsNull(rs.getString(13)));
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
        return emp;
    }

    public void updateDeptCode(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLDEPT SET GL11NAME=?, GL11HEAD=?, GL11ADD1=?, GL11ADD2=?, GL11ADD3 = ?, GL11POSCODE=?, GL11TOWN=?, GL11TEL=?, GL11FAX=?, GL11STAFF=? WHERE GL11DEPT=?");
                pstmt.setString(1, e.getGL11NAME());
                pstmt.setString(2, e.getGL11HEAD());
                pstmt.setString(3, e.getGL11ADD1());
                pstmt.setString(4, e.getGL11ADD2());
                pstmt.setString(5, e.getGL11ADD3());
                pstmt.setString(6, e.getGL11POSCODE());
                pstmt.setString(7, e.getGL11TOWN());
                pstmt.setString(8, e.getGL11TEL());
                pstmt.setString(9, e.getGL11FAX());
                pstmt.setInt(10, e.getGL11STAFF());
                pstmt.setString(11, e.getGL11DEPT());
                pstmt.executeUpdate();
                System.out.println("UPDATE GLDEPT SET GL11NAME='" + e.getGL11NAME() + "', " + "GL11HEAD='" + e.getGL11HEAD() + "', GL11ADD1='" + e.getGL11ADD1() + "', " + "GL11ADD2='" + e.getGL11ADD2() + "', GL11ADD3 = '" + e.getGL11ADD3() + "', " + "GL11POSCODE='" + e.getGL11POSCODE() + "', GL11TOWN='" + e.getGL11TOWN() + "', " + "GL11TEL='" + e.getGL11TEL() + "', GL11FAX='" + e.getGL11FAX() + "', " + "GL11STAFF='" + e.getGL11STAFF() + "' WHERE GL11DEPT='" + e.getGL11DEPT() + "'");
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

    public Foundation getGenSubj(String GL54SUBJSTA, String GL54SUBJEND) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLSUBJ WHERE GL54SUBJSTA='" + GL54SUBJSTA + "'" + " AND GL54SUBJEND = '" + GL54SUBJEND + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL54SUBJSTA(rs.getString(1));
                    emp.setGL54SUBJEND(rs.getString(2));
                    emp.setGL54MARK(rs.getString(3));
                    emp.setGL54DESC(rs.getString(4));
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
        return emp;
    }

    public void updateGenSubj(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLSUBJ SET GL54MARK=?, GL54DESC=? WHERE GL54SUBJSTA=? AND GL54SUBJEND=?");
                System.out.println("UPDATE GLSUBJ SET GL54MARK='" + e.getGL54MARK() + "', GL54DESC='" + e.getGL54DESC() + "' WHERE GL54SUBJSTA='" + e.getGL54SUBJSTA() + "' AND GL54SUBJEND='" + e.getGL54SUBJEND() + "'");
                pstmt.setString(1, e.getGL54MARK());
                pstmt.setString(2, e.getGL54DESC());
                pstmt.setString(3, e.getGL54SUBJSTA());
                pstmt.setString(4, e.getGL54SUBJEND());
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

    public Foundation getSerial(String GL49FREQ) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLFREQ WHERE GL49FREQ='" + GL49FREQ + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL49FREQ(rs.getString(1));
                    emp.setGL49DESC(rs.getString(2));
                    emp.setGL49TIME(rs.getString(3));
                    emp.setGL49ALERT(rs.getString(4));
                    emp.setGL49PTYPE(rs.getString(5));
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
        return emp;
    }

    public void updateSerial(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLFREQ SET GL49DESC=?, GL49TIME=?, GL49ALERT=?, GL49PTYPE=? WHERE GL49FREQ=?");
                pstmt.setString(1, e.getGL49DESC());
                pstmt.setString(2, e.getGL49TIME());
                pstmt.setString(3, e.getGL49ALERT());
                pstmt.setString(4, e.getGL49PTYPE());
                pstmt.setString(5, e.getGL49FREQ());
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

    public Foundation getItemCat(String GL10ICAT) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLICAT WHERE GL10ICAT='" + GL10ICAT + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL10ICAT(rs.getString(1));
                    emp.setGL10DESC(rs.getString(2));
                    emp.setGL10DISPLAY(rs.getString(7));
                    emp.setGL10ELIG(rs.getString(9));
                    emp.setGL10UNIT(rs.getString(3));
                    emp.setGL10RESERVEC(rs.getString(8));
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
        return emp;
    }

    public void updateItemCat(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLICAT SET GL10DESC=?, GL10DISPLAY=?, GL10ELIG=?, GL10UNIT=?, GL10RESERVEC=? WHERE GL10ICAT=?");
                pstmt.setString(1, e.getGL10DESC());
                pstmt.setString(2, e.getGL10DISPLAY());
                pstmt.setString(3, e.getGL10ELIG());
                pstmt.setString(4, e.getGL10UNIT());
                pstmt.setString(5, e.getGL10RESERVEC());
                pstmt.setString(6, e.getGL10ICAT());
                pstmt.executeUpdate();
                System.out.println("UPDATE GLICAT SET GL10DESC='" + e.getGL10DESC() + "', GL10DISPLAY='" + e.getGL10DISPLAY() + "', GL10ELIG='" + e.getGL10ELIG() + "', GL10UNIT='" + e.getGL10UNIT() + "', GL10RESERVEC='" + e.getGL10RESERVEC() + "' WHERE GL10ICAT='" + e.getGL10ICAT() + "'");
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

    public Foundation getVendor(String GL39CODE) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLVEND WHERE GL39CODE='" + GL39CODE + "'");
                System.out.println("SELECT * FROM GLVEND WHERE GL39CODE='" + GL39CODE + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL39CODE(rs.getString(1));
                    System.out.println(java.lang.String.valueOf(rs.getString(1)) + " GL39CODE");
                    emp.setGL39PUB(rs.getString(23));
                    System.out.println(java.lang.String.valueOf(rs.getString(23)) + " GL39PUB");
                    emp.setGL39SUPPLIER(rs.getString(22));
                    System.out.println(java.lang.String.valueOf(rs.getString(22)) + " GL39SUPPLIER");
                    emp.setGL39BINDER(rs.getString(21));
                    System.out.println(java.lang.String.valueOf(rs.getString(21)) + " GL39BINDER");
                    emp.setGL39NAME(rs.getString(2));
                    System.out.println(java.lang.String.valueOf(rs.getString(2)) + " GL39NAME");
                    emp.setGL39PERINC(rs.getString(8));
                    System.out.println(java.lang.String.valueOf(rs.getString(8)) + " GL39PERINC");
                    emp.setGL39DESIG(rs.getString(9));
                    System.out.println(java.lang.String.valueOf(rs.getString(9)) + " GL39DESIG");
                    emp.setGL39ADD1(rs.getString(3));
                    System.out.println(java.lang.String.valueOf(rs.getString(3)) + " GL39ADD1");
                    emp.setGL39ADD2(rs.getString(4));
                    System.out.println(java.lang.String.valueOf(rs.getString(4)) + " GL39ADD2");
                    emp.setGL39ADD3(rs.getString(5));
                    System.out.println(java.lang.String.valueOf(rs.getString(5)) + " GL39ADD3");
                    emp.setGL39PCODE(rs.getString(19));
                    System.out.println(java.lang.String.valueOf(rs.getString(19)) + " GL39PCODE");
                    emp.setGL39IPADD(rs.getString(20));
                    System.out.println(java.lang.String.valueOf(rs.getString(20)) + " GL39IPADD");
                    emp.setGL39TELNO(rs.getString(6));
                    System.out.println(java.lang.String.valueOf(rs.getString(6)) + " GL39TELNO");
                    emp.setGL39FAX(rs.getString(7));
                    System.out.println(java.lang.String.valueOf(rs.getString(7)) + " GL39FAX");
                    emp.setGL39CONTNO(rs.getString(10));
                    System.out.println(java.lang.String.valueOf(rs.getString(10)) + " GL39CONTNO");
                    emp.setGL39FUND(rs.getString(32));
                    System.out.println(java.lang.String.valueOf(rs.getString(32)) + " GL39FUND");
                    emp.setGL39CBDATE(DateFormatter.DBToDisplayFormat(rs.getString(12)));
                    System.out.println(java.lang.String.valueOf(DateFormatter.DBToDisplayFormat(rs.getString(12))) + " GL39CBDATE");
                    emp.setGL39CEDATE(DateFormatter.DBToDisplayFormat(rs.getString(13)));
                    System.out.println(java.lang.String.valueOf(DateFormatter.DBToDisplayFormat(rs.getString(13))) + " GL39CEDATE");
                    emp.setGL39USERNAME(rs.getString(29));
                    System.out.println(java.lang.String.valueOf(rs.getString(29)) + " GL39USERNAME");
                    emp.setGL39PASSWORD(rs.getString(30));
                    System.out.println(java.lang.String.valueOf(rs.getString(30)) + " GL39PASSWORD");
                    emp.setGL39ACCNO(rs.getString(17));
                    System.out.println(java.lang.String.valueOf(rs.getString(17)) + " GL39ACCNO");
                    emp.setGL39BANK(rs.getString(33));
                    System.out.println(java.lang.String.valueOf(rs.getString(33)) + " GL39BANK");
                    emp.setGL39EMAIL(rs.getString(31));
                    System.out.println(java.lang.String.valueOf(rs.getString(31)) + " GL39EMAIL");
                    emp.setGL39STATUS(rs.getString(28));
                    System.out.println(java.lang.String.valueOf(rs.getString(28)) + " GL39STATUS");
                    emp.setGL39REMARK(rs.getString(16));
                    System.out.println(java.lang.String.valueOf(rs.getString(16)) + " GL39REMARK");
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
        return emp;
    }

    public void updateVendor(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLVEND SET GL39NAME=?, GL39ADD1=?, GL39ADD2=?, GL39ADD3=?,GL39TELNO=?,GL39FAX=?,GL39PERINC=?, GL39DESIG=?, GL39CONTNO=?,GL39CBDATE=?, GL39CEDATE=?, GL39REMARK=?, GL39ACCNO=?,GL39PCODE=?,GL39IPADD=?, GL39BINDER=?, GL39SUPPLIER=?,GL39PUB=?, GL39INDI=?, GL39USERNAME=?,GL39PASSWORD=?, GL39EMAIL=?,GL39BANK=? WHERE GL39CODE=?");
                System.out.println("UPDATE GLVEND SET GL39NAME='" + e.getGL39NAME() + "', GL39ADD1='" + e.getGL39ADD1() + "', GL39ADD2='" + e.getGL39ADD2() + "', GL39ADD3='" + e.getGL39ADD3() + "',GL39TELNO='" + e.getGL39TELNO() + "',GL39FAX='" + e.getGL39FAX() + "'," + "GL39PERINC='" + e.getGL39PERINC() + "', GL39DESIG='" + e.getGL39DESIG() + "', GL39CONTNO='" + e.getGL39CONTNO() + "',GL39CBDATE='" + e.getGL39CBDATE() + "', GL39CEDATE='" + e.getGL39CEDATE() + "', GL39REMARK='" + e.getGL39REMARK() + "', GL39ACCNO='" + e.getGL39ACCNO() + "',GL39PCODE='" + e.getGL39PCODE() + "'," + "GL39IPADD='" + e.getGL39IPADD() + "', GL39BINDER='" + e.getGL39BINDER() + "', GL39SUPPLIER='" + e.getGL39SUPPLIER() + "'," + "GL39PUB='" + e.getGL39PUB() + "', GL39INDI='" + e.getGL39INDI() + "', GL39USERNAME='" + e.getGL39USERNAME() + "',GL39PASSWORD='" + e.getGL39PASSWORD() + "', GL39EMAIL='" + e.getGL39EMAIL() + "',GL39BANK='" + e.getGL39BANK() + "' " + "WHERE GL39CODE='" + e.getGL39CODE() + "'");
                pstmt.setString(1, e.getGL39NAME());
                pstmt.setString(2, e.getGL39ADD1());
                pstmt.setString(3, e.getGL39ADD2());
                pstmt.setString(4, e.getGL39ADD3());
                pstmt.setString(5, e.getGL39TELNO());
                pstmt.setString(6, e.getGL39FAX());
                pstmt.setString(7, e.getGL39PERINC());
                pstmt.setString(8, e.getGL39DESIG());
                pstmt.setString(9, e.getGL39CONTNO());
                pstmt.setString(10, e.getGL39CBDATE());
                pstmt.setString(11, e.getGL39CEDATE());
                pstmt.setString(12, e.getGL39REMARK());
                pstmt.setString(13, e.getGL39ACCNO());
                pstmt.setString(14, e.getGL39PCODE());
                pstmt.setString(15, e.getGL39IPADD());
                pstmt.setString(16, e.getGL39BINDER());
                pstmt.setString(17, e.getGL39SUPPLIER());
                pstmt.setString(18, e.getGL39PUB());
                pstmt.setString(19, e.getGL39INDI());
                pstmt.setString(20, e.getGL39USERNAME());
                pstmt.setString(21, e.getGL39PASSWORD());
                pstmt.setString(22, e.getGL39EMAIL());
                pstmt.setString(23, e.getGL39BANK());
                pstmt.setString(24, e.getGL39CODE());
                System.out.println(pstmt + "jjjjjjjjjj");
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

    public List<Foundation> getCategory() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL07CATE, GL07DESC FROM GLCATE";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL07CATE(rs.getString(1));
                    fnd.setGL07DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public Foundation getPatCat(String GL07CATE) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GL07CATE, GL07DESC, GL07POPDB, GL07EMAIL,GL07SCHAR, GL07RATER, GL07ARTXN, GL07MODEM, GL07BRFORC, GL07DCFORC,GL07ELIG, GL07MAXFINE, GL07FINELIMIT, GL07ILLOUT, GL07MAXRESV,GL07MAXACCT, GL07ALLOWOVD, GL07ALLOWRSV, GL07RENEWFEE, GL07RENEWGRC  FROM GLCATE WHERE GL07CATE='" + GL07CATE + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL07CATE(rs.getString("GL07CATE"));
                    emp.setGL07DESC(rs.getString("GL07DESC"));
                    emp.setGL07POPDB(rs.getString("GL07POPDB"));
                    emp.setGL07EMAIL(rs.getString("GL07EMAIL"));
                    emp.setGL07SCHAR(rs.getString("GL07SCHAR"));
                    emp.setGL07RATER(rs.getString("GL07RATER"));
                    emp.setGL07ARTXN(rs.getString("GL07ARTXN"));
                    emp.setGL07MODEM(rs.getString("GL07MODEM"));
                    emp.setGL07BRFORC(rs.getString("GL07BRFORC"));
                    emp.setGL07DCFORC(rs.getString("GL07DCFORC"));
                    emp.setGL07ELIG(rs.getString("GL07ELIG"));
                    emp.setGL07MAXFINE(rs.getString("GL07MAXFINE"));
                    emp.setGL07FINELIMIT(rs.getString("GL07FINELIMIT"));
                    emp.setGL07ILLOUT(rs.getString("GL07ILLOUT"));
                    emp.setGL07MAXRESV(rs.getString("GL07MAXRESV"));
                    emp.setGL07MAXACCT(rs.getString("GL07MAXACCT"));
                    emp.setGL07ALLOWOVD(rs.getString("GL07ALLOWOVD"));
                    emp.setGL07ALLOWRSV(rs.getString("GL07ALLOWRSV"));
                    emp.setGL07RENEWFEE(rs.getString("GL07RENEWFEE"));
                    emp.setGL07RENEWGRC(rs.getString("GL07RENEWGRC"));
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
        return emp;
    }

    public void updatePatCat(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLCATE SET GL07DESC=?, GL07ELIG=?,GL07MAXFINE=?,GL07FINELIMIT=?,GL07ILLOUT=?, GL07MAXRESV=?, GL07ALLOWOVD=?,GL07ALLOWRSV=?, GL07MAXACCT=?,GL07POPDB=?,GL07RATER=?, GL07EMAIL=?,GL07MODEM=?,GL07SCHAR=?,GL07BRFORC=?,GL07ARTXN=?,GL07DCFORC=?, GL07RENEWFEE=?, GL07RENEWGRC=?WHERE GL07CATE=?");
                System.out.println("UPDATE GLCATE SET GL07DESC='" + e.getGL07DESC() + "'," + "GL07ELIG='" + e.getGL07ELIG() + "',GL07MAXFINE='" + e.getGL07MAXFINE() + "',GL07FINELIMIT='" + e.getGL07FINELIMIT() + "',GL07ILLOUT='" + e.getGL07ILLOUT() + "', GL07MAXRESV='" + e.getGL07MAXRESV() + "'," + "GL07ALLOWOVD='" + e.getGL07ALLOWOVD() + "',GL07ALLOWRSV='" + e.getGL07ALLOWRSV() + "', GL07MAXACCT='" + e.getGL07MAXACCT() + "',GL07POPDB='" + e.getGL07POPDB() + "',GL07RATER='" + e.getGL07RATER() + "'," + "GL07EMAIL='" + e.getGL07EMAIL() + "',GL07MODEM='" + e.getGL07MODEM() + "',GL07SCHAR='" + e.getGL07SCHAR() + "',GL07BRFORC='" + e.getGL07BRFORC() + "',GL07ARTXN='" + e.getGL07ARTXN() + "',GL07DCFORC='" + e.getGL07DCFORC() + "',GL07RENEWFEE='" + e.getGL07RENEWFEE() + "',GL07RENEWGRC='" + e.getGL07RENEWGRC() + "' WHERE GL07CATE='" + e.getGL07CATE() + "'");
                pstmt.setString(1, e.getGL07DESC());
                pstmt.setString(2, e.getGL07ELIG());
                pstmt.setString(3, e.getGL07MAXFINE());
                pstmt.setString(4, e.getGL07FINELIMIT());
                pstmt.setString(5, e.getGL07ILLOUT());
                pstmt.setString(6, e.getGL07MAXRESV());
                pstmt.setString(7, e.getGL07ALLOWOVD());
                pstmt.setString(8, e.getGL07ALLOWRSV());
                pstmt.setString(9, e.getGL07MAXACCT());
                pstmt.setString(10, e.getGL07POPDB());
                pstmt.setString(11, e.getGL07RATER());
                pstmt.setString(12, e.getGL07EMAIL());
                pstmt.setString(13, e.getGL07MODEM());
                pstmt.setString(14, e.getGL07SCHAR());
                pstmt.setString(15, e.getGL07BRFORC());
                pstmt.setString(16, e.getGL07ARTXN());
                pstmt.setString(17, e.getGL07DCFORC());
                pstmt.setString(18, e.getGL07RENEWFEE());
                pstmt.setString(19, e.getGL07RENEWGRC());
                pstmt.setString(20, e.getGL07CATE());
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

    public Foundation getLoca(String GL05LOCA) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GL05BRNC, GL05LOCA, GL05DESC, GL05DISPLAY, GL05SUBJECT, GL05MATCAP, GL05LAYOUT, GL05IPADD, GL05NOSERVER, GL05LNPRT, GL05NOTER, GL05LJPRT, GL05NOPC, GL05DMPRT, GL05MODEM, GL05MMEDIA, GL05SDI, GL05SDDS, GL05JARING, GL05CDROM, GL05IRL, GL05NST, GL71BRNC, GL71DESC  FROM GLLOCA LEFT JOIN GLBRNC ON GL71BRNC = GL05BRNC WHERE GL05LOCA='" + GL05LOCA + "'");
                System.out.println("SELECT GL05BRNC, GL05LOCA, GL05DESC, GL05DISPLAY, GL05SUBJECT, GL05MATCAP, GL05LAYOUT, GL05IPADD, GL05NOSERVER, GL05LNPRT, GL05NOTER, GL05LJPRT, GL05NOPC, GL05DMPRT, GL05MODEM, GL05MMEDIA, GL05SDI, GL05SDDS, GL05JARING, GL05CDROM, GL05IRL, GL05NST, GL71BRNC, GL71DESC  FROM GLLOCA LEFT JOIN GLBRNC ON GL71BRNC = GL05BRNC WHERE GL05LOCA='" + GL05LOCA + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL05BRNC(rs.getString(1));
                    emp.setGL05LOCA(rs.getString(2));
                    emp.setGL05DESC(rs.getString(3));
                    emp.setGL05DISPLAY(rs.getString(4));
                    emp.setGL05SUBJECT(rs.getString(5));
                    emp.setGL05MATCAP(rs.getString(6));
                    emp.setGL05LAYOUT(rs.getString(7));
                    emp.setGL05IPADD(rs.getString(8));
                    emp.setGL05NOSERVER(rs.getString(9));
                    emp.setGL05LNPRT(rs.getString(10));
                    emp.setGL05NOTER(rs.getString(11));
                    emp.setGL05LJPRT(rs.getString(12));
                    emp.setGL05NOPC(rs.getString(13));
                    emp.setGL05DMPRT(rs.getString(14));
                    emp.setGL05MODEM(rs.getString(15));
                    emp.setGL05MMEDIA(rs.getString(16));
                    emp.setGL05SDI(rs.getString(17));
                    emp.setGL05SDDS(rs.getString(18));
                    emp.setGL05JARING(rs.getString(19));
                    emp.setGL05CDROM(rs.getString(20));
                    emp.setGL05IRL(rs.getString(21));
                    emp.setGL05NST(rs.getString(22));
                    emp.setGL71BRNC(rs.getString(23));
                    emp.setGL71DESC(rs.getString(24));
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
        return emp;
    }

    public void updateLoca(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLLOCA SET GL05DESC=?, GL05DISPLAY=?, GL05SUBJECT=?, GL05MATCAP=?, GL05LAYOUT=?, GL05IPADD=?, GL05NOSERVER=?, GL05LNPRT=?,GL05NOTER=?, GL05LJPRT=?, GL05NOPC=?, GL05DMPRT=?, GL05MODEM=?,GL05MMEDIA=?, GL05SDI=?, GL05SDDS=?, GL05JARING=?, GL05CDROM=?, GL05IRL=?, GL05NST=?, GL05BRNC=? WHERE GL05LOCA=?");
                pstmt.setString(1, e.getGL05DESC());
                pstmt.setString(2, e.getGL05DISPLAY());
                pstmt.setString(3, e.getGL05SUBJECT());
                pstmt.setString(4, e.getGL05MATCAP());
                pstmt.setString(5, e.getGL05LAYOUT());
                pstmt.setString(6, e.getGL05IPADD());
                pstmt.setString(7, e.getGL05NOSERVER());
                pstmt.setString(8, e.getGL05LNPRT());
                pstmt.setString(9, e.getGL05NOTER());
                pstmt.setString(10, e.getGL05LJPRT());
                pstmt.setString(11, e.getGL05NOPC());
                pstmt.setString(12, e.getGL05DMPRT());
                pstmt.setString(13, e.getGL05MODEM());
                pstmt.setString(14, e.getGL05MMEDIA());
                pstmt.setString(15, e.getGL05SDI());
                pstmt.setString(16, e.getGL05SDDS());
                pstmt.setString(17, e.getGL05JARING());
                pstmt.setString(18, e.getGL05CDROM());
                pstmt.setString(19, e.getGL05IRL());
                pstmt.setString(20, e.getGL05NST());
                pstmt.setString(21, e.getGL05BRNC());
                pstmt.setString(22, e.getGL05LOCA());
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

    public Foundation getPatStatus(String GL08STAT) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLSTAT WHERE GL08STAT='" + GL08STAT + "'");
                System.out.println("QUERYSTAUTSSELECT * FROM GLSTAT WHERE GL08STAT='" + GL08STAT + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL08STAT(rs.getString(1));
                    emp.setGL08DESC(rs.getString(2));
                    emp.setGL08ACTION1(rs.getString(3));
                    emp.setGL08ACTION2(rs.getString(4));
                    emp.setGL08ACTION3(rs.getString(5));
                    emp.setGL08ACTION4(rs.getString(6));
                    emp.setGL08ACTION5(rs.getString(7));
                    emp.setGL08ACTION6(rs.getString(8));
                    emp.setGL08ACTION7(rs.getString(9));
                    emp.setGL08ACTION8(rs.getString(10));
                    emp.setGL08ACTION9(rs.getString(11));
                    emp.setGL08ACTION10(rs.getString(12));
                    emp.setGL08ACTION11(rs.getString(13));
                    emp.setGL08ACTION12(rs.getString(14));
                    emp.setGL08ACTION13(rs.getString(15));
                    emp.setGL08ACTION14(rs.getString(16));
                    emp.setGL08ACTION15(rs.getString(17));
                    emp.setGL08ACTION16(rs.getString(18));
                    emp.setGL08ACTION17(rs.getString(19));
                    emp.setGL08ACTION18(rs.getString(20));
                    emp.setGL08ACTION19(rs.getString(21));
                    emp.setGL08ACTION20(rs.getString(22));
                    System.out.println("DIS" + rs.getString(4));
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
        return emp;
    }

    public void updatePatStat(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLSTAT SET GL08DESC=?, GL08ACTION1=?, GL08ACTION2=?, GL08ACTION3=?, GL08ACTION4=?, GL08ACTION5=?, GL08ACTION6=?, GL08ACTION7=?,GL08ACTION8=?, GL08ACTION9=?, GL08ACTION10=?, GL08ACTION11=?, GL08ACTION12=?,GL08ACTION13=?, GL08ACTION14=?, GL08ACTION15=?, GL08ACTION16=?, GL08ACTION17=?, GL08ACTION18=?, GL08ACTION19=?, GL08ACTION20=? WHERE GL08STAT=?");
                pstmt.setString(1, e.getGL08DESC());
                pstmt.setString(2, e.getGL08ACTION1());
                pstmt.setString(3, e.getGL08ACTION2());
                pstmt.setString(4, e.getGL08ACTION3());
                pstmt.setString(5, e.getGL08ACTION4());
                pstmt.setString(6, e.getGL08ACTION5());
                pstmt.setString(7, e.getGL08ACTION6());
                pstmt.setString(8, e.getGL08ACTION7());
                pstmt.setString(9, e.getGL08ACTION8());
                pstmt.setString(10, e.getGL08ACTION9());
                pstmt.setString(11, e.getGL08ACTION10());
                pstmt.setString(12, e.getGL08ACTION11());
                pstmt.setString(13, e.getGL08ACTION12());
                pstmt.setString(14, e.getGL08ACTION13());
                pstmt.setString(15, e.getGL08ACTION14());
                pstmt.setString(16, e.getGL08ACTION15());
                pstmt.setString(17, e.getGL08ACTION16());
                pstmt.setString(18, e.getGL08ACTION17());
                pstmt.setString(19, e.getGL08ACTION18());
                pstmt.setString(20, e.getGL08ACTION19());
                pstmt.setString(21, e.getGL08ACTION20());
                pstmt.setString(22, e.getGL08STAT());
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

    public Foundation getLibInfo() {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLLIBR");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL28NAME(rs.getString(1));
                    emp.setGL28ADD1(rs.getString(2));
                    emp.setGL28ADD2(rs.getString(3));
                    emp.setGL28ADD3(rs.getString(4));
                    emp.setGL28POSCODE(rs.getString(5));
                    emp.setGL28TOWN(rs.getString(6));
                    emp.setGL28TEL(rs.getString(7));
                    emp.setGL28FAX(Handler.ifIsNull(rs.getString(8)));
                    emp.setGL28HEADLIB(rs.getString(9));
                    emp.setGL28OPACLIMIT(rs.getString(10));
                    emp.setGL28MSGDELAY(rs.getString(11));
                    emp.setGL28ORGNAME(rs.getString(12));
                    emp.setGL28LIBHEAD(rs.getString(13));
                    emp.setGL28LIBHEADEXT(rs.getString(14));
                    emp.setGL28CATHEAD(rs.getString(15));
                    emp.setGL28CATEXT(Handler.ifIsNull(rs.getString(16)));
                    emp.setGL28IRSHEAD(Handler.ifIsNull(rs.getString(17)));
                    emp.setGL28IRSEXT(Handler.ifIsNull(rs.getString(18)));
                    emp.setGL28CIRHEAD(Handler.ifIsNull(rs.getString(19)));
                    emp.setGL28CIREXT(rs.getString(20));
                    emp.setGL28ACQHEAD(rs.getString(21));
                    emp.setGL28ACQEXT(Handler.ifIsNull(rs.getString(22)));
                    emp.setGL28SERHEAD(rs.getString(23));
                    emp.setGL28SEREXT(Handler.ifIsNull(rs.getString(24)));
                    emp.setGL28FINHEAD(Handler.ifIsNull(rs.getString(25)));
                    emp.setGL28FINEXT(Handler.ifIsNull(rs.getString(26)));
                    emp.setGL28PROHEAD(Handler.ifIsNull(rs.getString(27)));
                    emp.setGL28PROEXT(Handler.ifIsNull(rs.getString(28)));
                    emp.setGL28OPACHISTORY(rs.getString(29));
                    emp.setGL28INXTRASH(Handler.ifIsNull(rs.getString(30)));
                    emp.setGL28MARCTYPE(rs.getString(31));
                    emp.setGL28KEYWFLG(rs.getString(32));
                    emp.setGL28LIBWEEKEND(rs.getString(33));
                    emp.setGL28LOGO(Handler.ifIsNull(rs.getString(34)));
                    emp.setGL28SERIALNO(rs.getString(35));
                    emp.setGL28RESVTIME(rs.getString(36));
                    emp.setGL28ACQCLAIMS1(Handler.ifIsNull(rs.getString(37)));
                    emp.setGL28ACQCLAIM2(Handler.ifIsNull(rs.getString(38)));
                    emp.setGL28ACQCLAIM3(Handler.ifIsNull(rs.getString(39)));
                    emp.setGL28IRSMARCTYPE(rs.getString(40));
                    emp.setGL28CIRCPURGEDAYS(rs.getString(41));
                    emp.setGL28CIRCPURGEGRACE(rs.getString(42));
                    emp.setGL28FUND(Handler.ifIsNull(rs.getString(43)));
                    emp.setGL28ORDERSPAN(Handler.ifIsNull(rs.getString(44)));
                    emp.setGL28IRSFLAG(Handler.ifIsNull(rs.getString(45)));
                    emp.setGL28NOTICE1(rs.getString(46));
                    emp.setGL28NOTICE2(rs.getString(47));
                    emp.setGL28NOTICE3(rs.getString(48));
                    emp.setGL28NOTICE4(rs.getString(49));
                    emp.setGL28ACQBPRINT(rs.getString(50));
                    emp.setGL28SERBPRINT(rs.getString(51));
                    emp.setGL28MSGTIMEOUT(rs.getString(52));
                    emp.setGL28TIMESCOST(rs.getString(53));
                    emp.setGL28RCOST(rs.getString(54));
                    emp.setGL28LOADALL(rs.getString(55));
                    emp.setGL28NEWACOND(rs.getString(56));
                    emp.setGL28NEWADDATE(rs.getString(57));
                    emp.setGL28NEWADOCS(rs.getString(58));
                    emp.setGL28NEWAICAT(rs.getString(59));
                    emp.setGL28NEWALOCA(rs.getString(60));
                    emp.setGL28NEWASMD(rs.getString(61));
                    emp.setGL28NEWAUDATE(rs.getString(62));
                    emp.setGL28SERRPSUM(rs.getString(63));
                    emp.setGL28SERRPDET(rs.getString(64));
                    emp.setGL28LIBSYMBOL(Handler.ifIsNull(rs.getString(65)));
                    emp.setGL28CIRCMODE(Handler.ifIsNull(rs.getString(66)));
                    emp.setGL28NEWALCRMAT(rs.getString(67));
                    emp.setGL28BRNC(rs.getString(68));
                    emp.setGL28GSTID(rs.getString(69));
                    emp.setGL28LIBNAME(rs.getString(70));
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
        return emp;
    }

    public void updateLibInfo(libInfo libinfo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLLIBR SET GL28ORGNAME = ?, GL28LIBSYMBOL = ?,  GL28BRNC = ?, GL28ADD1 = ?, GL28ADD2 =?, GL28ADD3 = ?, GL28POSCODE = ?, GL28TOWN = ?, GL28TEL = ?, GL28FAX = ?, GL28HEADLIB = ?, GL28LIBHEADEXT = ?, GL28PROHEAD = ?, GL28PROEXT = ?, GL28LOGO = ?, GL28ACQHEAD = ?, GL28ACQCLAIMS1 = ?, GL28ACQCLAIMS2 = ?, GL28ACQCLAIMS3 = ?, GL28ORDERSPAN = ?,GL28ACQBPRINT = ?, GL28CATHEAD = ?, GL28CATEXT = ?, GL28MARCTYPE = ?, GL28INXTRASH = ?, GL28CIRHEAD = ?, GL28CIREXT = ?, GL28LIBWEEKEND = ?, GL28RESVTIME = ?, GL28RCOST = ?, GL28TIMESCOST = ?, GL28CIRCMODE = ?, GL28OPACLIMIT = ?, GL28SERHEAD = ?, GL28SEREXT = ?, GL28SERBPRINT = ?, GL28IRSHEAD = ?, GL28IRSEXT = ?, GL28IRSMARCTYPE = ?, GL28IRSFLAG = ?, GL28FINHEAD = ?, GL28FINEXT = ?, GL28FUND = ? WHERE GL28NAME=?");
                pstmt.setString(1, libinfo.getGL28ORGNAME());
                pstmt.setString(2, libinfo.getGL28LIBSYMBOL());
                pstmt.setString(3, libinfo.getGL28BRNC());
                pstmt.setString(4, libinfo.getGL28ADD1());
                pstmt.setString(5, libinfo.getGL28ADD2());
                pstmt.setString(6, libinfo.getGL28ADD3());
                pstmt.setString(7, libinfo.getGL28POSCODE());
                pstmt.setString(8, libinfo.getGL28TOWN());
                pstmt.setString(9, libinfo.getGL28TEL());
                pstmt.setString(10, libinfo.getGL28FAX());
                pstmt.setString(11, libinfo.getGL28HEADLIB());
                pstmt.setString(12, libinfo.getGL28LIBHEADEXT());
                pstmt.setString(13, libinfo.getGL28PROHEAD());
                pstmt.setString(14, libinfo.getGL28PROEXT());
                pstmt.setString(15, libinfo.getGL28LOGO());
                pstmt.setString(16, libinfo.getGL28ACQHEAD());
                pstmt.setString(17, libinfo.getGL28ACQCLAIMS1());
                pstmt.setString(18, libinfo.getGL28ACQCLAIMS2());
                pstmt.setString(19, libinfo.getGL28ACQCLAIMS3());
                pstmt.setString(20, libinfo.getGL28ORDERSPAN());
                pstmt.setString(21, libinfo.getGL28ACQBPRINT());
                pstmt.setString(22, libinfo.getGL28CATHEAD());
                pstmt.setString(23, libinfo.getGL28CATEXT());
                pstmt.setString(24, libinfo.getGL28MARCTYPE());
                pstmt.setString(25, libinfo.getGL28INXTRASH());
                pstmt.setString(26, libinfo.getGL28CIRHEAD());
                pstmt.setString(27, libinfo.getGL28CIREXT());
                pstmt.setString(28, libinfo.getGL28LIBWEEKEND());
                pstmt.setString(29, libinfo.getGL28RESVTIME());
                pstmt.setString(30, libinfo.getGL28RCOST());
                pstmt.setString(30, libinfo.getGL28TIMESCOST());
                pstmt.setString(31, libinfo.getGL28CIRCMODE());
                pstmt.setString(32, libinfo.getGL28OPACLIMIT());
                pstmt.setString(33, libinfo.getGL28SERHEAD());
                pstmt.setString(34, libinfo.getGL28SEREXT());
                pstmt.setString(35, libinfo.getGL28SERBPRINT());
                pstmt.setString(36, libinfo.getGL28IRSHEAD());
                pstmt.setString(37, libinfo.getGL28IRSEXT());
                pstmt.setString(38, libinfo.getGL28IRSMARCTYPE());
                pstmt.setString(39, libinfo.getGL28IRSFLAG());
                pstmt.setString(40, libinfo.getGL28FINHEAD());
                pstmt.setString(41, libinfo.getGL28FINEXT());
                pstmt.setString(42, libinfo.getGL28FUND());
                pstmt.setString(43, libinfo.getGL28NAME());
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

    public void updateLibInfo(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLLOCA SET GL05DESC=?, GL05DISPLAY=?, GL05SUBJECT=?, GL05MATCAP=?, GL05LAYOUT=?, GL05IPADD=?, GL05NOSERVER=?, GL05LNPRT=?,GL05NOTER=?, GL05LJPRT=?, GL05NOPC=?, GL05DMPRT=?, GL05MODEM=?,GL05MMEDIA=?, GL05SDI=?, GL05SDDS=?, GL05JARING=?, GL05CDROM=?, GL05IRL=?, GL05NST=?, GL05BRNC=? WHERE GL05LOCA=?");
                pstmt.setString(1, e.getGL05DESC());
                pstmt.setString(2, e.getGL05DISPLAY());
                pstmt.setString(3, e.getGL05SUBJECT());
                pstmt.setString(4, e.getGL05MATCAP());
                pstmt.setString(5, e.getGL05LAYOUT());
                pstmt.setString(6, e.getGL05IPADD());
                pstmt.setString(7, e.getGL05NOSERVER());
                pstmt.setString(8, e.getGL05LNPRT());
                pstmt.setString(9, e.getGL05NOTER());
                pstmt.setString(10, e.getGL05LJPRT());
                pstmt.setString(11, e.getGL05NOPC());
                pstmt.setString(12, e.getGL05DMPRT());
                pstmt.setString(13, e.getGL05MODEM());
                pstmt.setString(14, e.getGL05MMEDIA());
                pstmt.setString(15, e.getGL05SDI());
                pstmt.setString(16, e.getGL05SDDS());
                pstmt.setString(17, e.getGL05JARING());
                pstmt.setString(18, e.getGL05CDROM());
                pstmt.setString(19, e.getGL05IRL());
                pstmt.setString(20, e.getGL05NST());
                pstmt.setString(21, e.getGL05BRNC());
                pstmt.setString(22, e.getGL05LOCA());
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

    public Foundation getTagP(String GL17MARC, String GL17TAG) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GL17MARC,GL17TAG,GL17DESC,GL17TRUC,GL17TABNAME,GL17ACRO, GL17GRID, GL17LEN, GL17IDXLANG, GL17DEFAULT, GL17REMARK, GL17REPEAT,GL17AUTFLAG, GL17MANDA, GL17CPFLAG, GL17IDXFLAG, GL17PARAMIPS, GL17KEYWORD,GL17MEDIA, GL17STOP,GL18INDILVL,GL18INDI FROM GLTAGP LEFT JOIN GLMINDI ON GL17MARC = GL18MARC AND GL17TAG = GL18TAG WHERE GL17TAG='" + GL17TAG + "' AND GL17MARC='" + GL17MARC + "'");
                System.out.println("SELECT GL17MARC,GL17TAG,GL17DESC,GL17TRUC,GL17TABNAME,GL17ACRO, GL17GRID, GL17LEN, GL17IDXLANG, GL17DEFAULT, GL17REMARK, GL17REPEAT,GL17AUTFLAG, GL17MANDA, GL17CPFLAG, GL17IDXFLAG, GL17PARAMIPS, GL17KEYWORD,GL17MEDIA, GL17STOP,GL18INDILVL,GL18INDI FROM GLTAGP LEFT JOIN GLMINDI ON GL17MARC = GL18MARC AND GL17TAG = GL18TAG WHERE GL17TAG='" + GL17TAG + "' AND GL17MARC='" + GL17MARC + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL17MARC(rs.getString(1));
                    emp.setGL17TAG(rs.getString(2));
                    emp.setGL17DESC(rs.getString(3));
                    emp.setGL17TRUC(rs.getString(4));
                    emp.setGL17TABNAME(rs.getString(5));
                    emp.setGL17ACRO(rs.getString(6));
                    emp.setGL17GRID(rs.getString(7));
                    emp.setGL17LEN(rs.getString(8));
                    emp.setGL17IDXLANG(rs.getString(9));
                    emp.setGL17DEFAULT(rs.getString(10));
                    emp.setGL17REMARK(rs.getString(11));
                    emp.setGL17REPEAT(rs.getString(12));
                    emp.setGL17AUTFLAG(rs.getString(13));
                    emp.setGL17MANDA(rs.getString(14));
                    emp.setGL17CPFLAG(rs.getString(15));
                    emp.setGL17IDXFLAG(rs.getString(16));
                    emp.setGL17PARAMIPS(rs.getString(17));
                    emp.setGL17KEYWORD(rs.getString(18));
                    emp.setGL17MEDIA(rs.getString(19));
                    emp.setGL17STOP(rs.getString(20));
                    emp.setGL18INDILVL(rs.getString(21));
                    emp.setGL18INDI(rs.getString(22));
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
        return emp;
    }

    public Foundation getPatDetail(String GL14PATR) throws IOException {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        Object stmt2 = null;
        try {
            try {
                String GL14NAMETITLE = "";
                String GL14RACE = "";
                String GL14RELIGION = "";
                String GL14DESC = "";
                String GL14TOWN = "";
                String GL14TOWN2 = "";
                String GL14OFFTOWN = "";
                String bloblength = "0";
                con = DBConnection.getConnection();
                DBConnection dbtype = new DBConnection();
                stmt = con.createStatement();
                ResultSet rs2 = stmt.executeQuery("SELECT GL14NAMETITLE, GL14RACE, GL14RELIGION, GL14DESC, GL14TOWN, GL14TOWN2, GL14OFFTOWN FROM GLPATR WHERE GL14PATR='" + GL14PATR + "'");
                System.out.println("SELECT GL14NAMETITLE, GL14RACE, GL14RELIGION, GL14DESC, GL14TOWN, GL14TOWN2, GL14OFFTOWN FROM GLPATR WHERE GL14PATR='" + GL14PATR + "'" + " lll");
                while (rs2.next()) {
                    GL14NAMETITLE = Handler.ifIsNull(rs2.getString(1));
                    GL14RACE = Handler.ifIsNull(rs2.getString(2));
                    GL14RELIGION = Handler.ifIsNull(rs2.getString(3));
                    GL14DESC = Handler.ifIsNull(rs2.getString(4));
                    GL14TOWN = Handler.ifIsNull(rs2.getString(5));
                    GL14TOWN2 = Handler.ifIsNull(rs2.getString(6));
                    GL14OFFTOWN = Handler.ifIsNull(rs2.getString(7));
                }
                System.out.println(java.lang.String.valueOf(GL14NAMETITLE) + " GL14NAMETITLE");
                System.out.println(java.lang.String.valueOf(GL14RACE) + " GL14RACE");
                System.out.println(java.lang.String.valueOf(GL14RELIGION) + " GL14RELIGION");
                System.out.println(java.lang.String.valueOf(GL14DESC) + " GL14DESC");
                System.out.println(java.lang.String.valueOf(GL14TOWN) + " GL14TOWN");
                System.out.println(java.lang.String.valueOf(GL14TOWN) + " GL14TOWN");
                ResultSet rs = stmt.executeQuery("SELECT GL14PATR, GL14PASW, GL14GRID, GL14NAME, GL14ABBR, GL14OLDIC, GL14NEWIC, GL14CATE, GL14DEPT, GL14COURSE, GL14STAT, GL14RELID, GL14ADD1, GL14ADD2, GL14ADD3,  GL14CODE, GL14TOWN, GL14HTEL, GL14OTEL, GL14FAX, GL14IPADD, GL14DOB, GL14SEX, GL14RACE,  GL14DESC, GL14MEMDATE, GL14EXPDATE, GL14MEMFEE, GL14DEPOSIT, GL14RECEIPT, GL14IMAGE, GL14FINEOUT,  GL14FINECOL, GL14LOSTBOK, GL14SUSPEND, GL14BORDATE, GL14BORYEAR, GL14LTDATE, GL14LTYEAR, GL14LASTBOR, GL14LASTRET, GL14LOGIN, GL14REMARK, GL14USID, GL14DUEF, GL14COLOR, GL14RELIGION, GL14EMPLOYEE,  GL14DATEJOIN, GL14STAFFLEVEL, GL14REGISTER, GL14SUPERVISOR, GL14BRNC, GL14OFFADD1, GL14OFFADD2,  GL14OFFADD3, GL14NAMETITLE, GL14MAILFLAG, GL14OFFCODE, GL14OFFTOWN, GL14BPRINT, GL14ADD21, GL14ADD22, GL14ADD23, GL14CODE2, GL14TOWN2, GL14HTEL2, GL14HTELX, GL14SECURE, GL14PBAR, GL14SNOTICE, GL14PARENTID, GL14MTEL, GL14RMVD, GL14DEFMODE, GL14IMG, GL02GRP, GL02NAME, GL07CATE, GL07DESC, GL08STAT, GL08DESC, GL71BRNC, GL71DESC, GL13RACE, GL13DESC, GL67RELIG, GL67DESC, GL69DESG, GL69DESC, GL11DEPT, GL11NAME, GL12COURSE, GL12DESC, GL15TOWN, GL15DESC, GL64CODE,GL64TITLE, GL14DATEREC, GL14RECBY,  (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'I' AND CODE = '" + GL14NAMETITLE + "') AS DESTITLE," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'J' AND CODE = '" + GL14RACE + "') AS DESRACE," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'K' AND CODE = '" + GL14RELIGION + "') AS DESRELIGION," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'D' AND CODE = '" + GL14DESC + "') AS DESDESC," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE = '" + GL14TOWN + "') AS TOWNR1," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE = '" + GL14TOWN2 + "') AS TOWNR2," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE = '" + GL14OFFTOWN + "') AS TOWNROFF," + "(SELECT GL15TOWN FROM GLTOWN WHERE GL15TOWN=GL14TOWN2) AS TOWN, (SELECT GL15DESC FROM GLTOWN WHERE GL15TOWN=GL14TOWN2) AS TOWNDESC, " + " (SELECT GL15TOWN FROM GLTOWN WHERE GL15TOWN=GL14OFFTOWN) AS TOWNOFF, (SELECT GL15DESC FROM GLTOWN WHERE GL15TOWN=GL14OFFTOWN) AS TOWNOFFTDESC" + " FROM GLPATR " + " LEFT JOIN GLGRMA ON GL14GRID = GL02GRP" + " LEFT JOIN GLCATE ON GL14CATE = GL07CATE " + " LEFT JOIN GLSTAT ON GL14STAT = GL08STAT " + " LEFT JOIN GLBRNC ON GL14BRNC = GL71BRNC " + " LEFT JOIN GLRACE ON GL14RACE = GL13RACE " + " LEFT JOIN GLRELIG ON GL14RELIGION = GL67RELIG " + " LEFT JOIN GLDESG ON GL14DESC = GL69DESG " + " LEFT JOIN GLDEPT ON GL14DEPT = GL11DEPT " + " LEFT JOIN GLCOUR ON GL14COURSE = GL12COURSE" + " LEFT JOIN GLTITLE ON GL64CODE = GL14NAMETITLE" + " LEFT JOIN GLTOWN ON GL14TOWN = GL15TOWN WHERE GL14PATR='" + GL14PATR + "'");
                System.out.println(java.lang.String.valueOf(GL14DESC) + " GL14DESC");
                System.out.println("SELECT GL14PATR, GL14PASW, GL14GRID, GL14NAME, GL14ABBR, GL14OLDIC, GL14NEWIC, GL14CATE, GL14DEPT, GL14COURSE, GL14STAT, GL14RELID, GL14ADD1, GL14ADD2, GL14ADD3,  GL14CODE, GL14TOWN, GL14HTEL, GL14OTEL, GL14FAX, GL14IPADD, GL14DOB, GL14SEX, GL14RACE,  GL14DESC, GL14MEMDATE, GL14EXPDATE, GL14MEMFEE, GL14DEPOSIT, GL14RECEIPT, GL14IMAGE, GL14FINEOUT,  GL14FINECOL, GL14LOSTBOK, GL14SUSPEND, GL14BORDATE, GL14BORYEAR, GL14LTDATE, GL14LTYEAR, GL14LASTBOR, GL14LASTRET, GL14LOGIN, GL14REMARK, GL14USID, GL14DUEF, GL14COLOR, GL14RELIGION, GL14EMPLOYEE,  GL14DATEJOIN, GL14STAFFLEVEL, GL14REGISTER, GL14SUPERVISOR, GL14BRNC, GL14OFFADD1, GL14OFFADD2,  GL14OFFADD3, GL14NAMETITLE, GL14MAILFLAG, GL14OFFCODE, GL14OFFTOWN, GL14BPRINT, GL14ADD21, GL14ADD22, GL14ADD23, GL14CODE2, GL14TOWN2, GL14HTEL2, GL14HTELX, GL14SECURE, GL14PBAR, GL14SNOTICE, GL14PARENTID, GL14MTEL, GL14RMVD, GL14DEFMODE, GL14IMG, GL02GRP, GL02NAME, GL07CATE, GL07DESC, GL08STAT, GL08DESC, GL71BRNC, GL71DESC, GL13RACE, GL13DESC, GL67RELIG, GL67DESC, GL69DESG, GL69DESC, GL11DEPT, GL11NAME, GL12COURSE, GL12DESC, GL15TOWN, GL15DESC, GL64CODE,GL64TITLE, GL14DATEREC, GL14RECBY,  (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'I' AND CODE = '" + GL14NAMETITLE + "') AS DESTITLE," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'J' AND CODE = '" + GL14RACE + "') AS DESRACE," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'K' AND CODE = '" + GL14RELIGION + "') AS DESRELIGION," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'D' AND CODE = '" + GL14DESC + "') AS DESDESC," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE = '" + GL14TOWN + "') AS TOWNR1," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE = '" + GL14TOWN2 + "') AS TOWNR2," + " (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE = '" + GL14OFFTOWN + "') AS TOWNROFF," + "(SELECT GL15TOWN FROM GLTOWN WHERE GL15TOWN=GL14TOWN2) AS TOWN, (SELECT GL15DESC FROM GLTOWN WHERE GL15TOWN=GL14TOWN2) AS TOWNDESC, " + " (SELECT GL15TOWN FROM GLTOWN WHERE GL15TOWN=GL14OFFTOWN) AS TOWNOFF, (SELECT GL15DESC FROM GLTOWN WHERE GL15TOWN=GL14OFFTOWN) AS TOWNOFFTDESC" + " FROM GLPATR " + " LEFT JOIN GLGRMA ON GL14GRID = GL02GRP" + " LEFT JOIN GLCATE ON GL14CATE = GL07CATE " + " LEFT JOIN GLSTAT ON GL14STAT = GL08STAT " + " LEFT JOIN GLBRNC ON GL14BRNC = GL71BRNC " + " LEFT JOIN GLRACE ON GL14RACE = GL13RACE " + " LEFT JOIN GLRELIG ON GL14RELIGION = GL67RELIG " + " LEFT JOIN GLDESG ON GL14DESC = GL69DESG " + " LEFT JOIN GLDEPT ON GL14DEPT = GL11DEPT " + " LEFT JOIN GLCOUR ON GL14COURSE = GL12COURSE" + " LEFT JOIN GLTITLE ON GL64CODE = GL14NAMETITLE" + " LEFT JOIN GLTOWN ON GL14TOWN = GL15TOWN WHERE GL14PATR='" + GL14PATR + "'");
                String imgLen = "";
                while (rs.next()) {
                    emp = new Foundation();
                    Blob blob = rs.getBlob(76);
                    System.out.println(blob + "GL14IMG");
                    if (blob == null) {
                        emp.setGL14IMG(rs.getString("GL14IMG"));
                    } else {
                        System.out.println("AAAA");
                        long blobLength = blob.length();
                        System.out.println("BBBB" + blobLength);
                        if (blobLength != 0L) {
                            int pos = 1;
                            int len = 10;
                            byte[] bytes = blob.getBytes(pos, len);
                            InputStream is = blob.getBinaryStream();
                            int b = is.read();
                            emp.setGL14IMG(java.lang.String.valueOf(b));
                        }
                    }
                    System.out.println(java.lang.String.valueOf(rs.getString(22)) + "= DOB");
                    emp.setGL14PATR(rs.getString(1));
                    String password = Handler.ifIsNull(rs.getString(2));
                    String getLibName = Encrypter.encrypt(password);
                    emp.setGL14PASW(getLibName);
                    emp.setGL14GRID(rs.getString(3));
                    emp.setGL14NAME(rs.getString(4));
                    emp.setGL14ABBR(rs.getString(5));
                    emp.setGL14OLDIC(rs.getString(6));
                    emp.setGL14NEWIC(Handler.ifIsNull(rs.getString(7)));
                    emp.setGL14CATE(Handler.ifIsNull(rs.getString(8)));
                    emp.setGL14DEPT(Handler.ifIsNull(rs.getString(9)));
                    emp.setGL14COURSE(Handler.ifIsNull(rs.getString(10)));
                    emp.setGL14STAT(Handler.ifIsNull(rs.getString(11)));
                    emp.setGL14RELID(Handler.ifIsNull(rs.getString(12)));
                    System.out.println(java.lang.String.valueOf(Handler.ifIsNull(rs.getString(12))) + "= GL14RELID");
                    emp.setGL14ADD1(rs.getString(13));
                    emp.setGL14ADD2(rs.getString(14));
                    emp.setGL14ADD3(rs.getString(15));
                    emp.setGL14CODE(rs.getString(16));
                    emp.setGL14TOWN(Handler.ifIsNull(rs.getString(17)));
                    emp.setGL14HTEL(rs.getString(18));
                    emp.setGL14OTEL(rs.getString(19));
                    emp.setGL14FAX(Handler.ifIsNull(rs.getString(20)));
                    emp.setGL14IPADD(Handler.ifIsNull(rs.getString(21)));
                    emp.setGL14DOB(DateFormatter.DBToDisplayFormat(rs.getString(22)));
                    emp.setGL14SEX(rs.getString(23));
                    emp.setGL14RACE(Handler.ifIsNull(rs.getString(24)));
                    emp.setGL14DESC(Handler.ifIsNull(rs.getString(25)));
                    emp.setGL14MEMDATE(DateFormatter.DBToDisplayFormat(rs.getString(26)));
                    emp.setGL14EXPDATE(DateFormatter.DBToDisplayFormat(rs.getString(27)));
                    emp.setGL14MEMFEE(rs.getString(28));
                    emp.setGL14DEPOSIT(rs.getString(29));
                    emp.setGL14RECEIPT(rs.getString(30));
                    emp.setGL14IMAGE(rs.getString(31));
                    emp.setGL14FINEOUT(rs.getString(32));
                    emp.setGL14FINECOL(rs.getString(33));
                    emp.setGL14LOSTBOK(rs.getString(34));
                    emp.setGL14SUSPEND(rs.getString(35));
                    emp.setGL14BORDATE(rs.getString(36));
                    emp.setGL14BORYEAR(rs.getString(37));
                    emp.setGL14LTDATE(rs.getString(38));
                    emp.setGL14LTYEAR(rs.getString(39));
                    emp.setGL14LASTBOR(rs.getString(40));
                    emp.setGL14LASTRET(rs.getString(41));
                    emp.setGL14LOGIN(rs.getString(42));
                    emp.setGL14REMARK(rs.getString(43));
                    emp.setGL14USID(rs.getString(44));
                    emp.setGL14DUEF(rs.getString(45));
                    emp.setGL14COLOR(rs.getString(46));
                    emp.setGL14RELIGION(Handler.ifIsNull(rs.getString(47)));
                    emp.setGL14EMPLOYEE(rs.getString(48));
                    emp.setGL14DATEJOIN(DateFormatter.DBToDisplayFormat(rs.getString(49)));
                    emp.setGL14STAFFLEVEL(rs.getString(50));
                    emp.setGL14REGISTER(rs.getString(51));
                    emp.setGL14SUPERVISOR(rs.getString(52));
                    emp.setGL14BRNC(Handler.ifIsNull(rs.getString(53)));
                    emp.setGL14OFFADD1(rs.getString(54));
                    emp.setGL14OFFADD2(rs.getString(55));
                    emp.setGL14OFFADD3(rs.getString(56));
                    emp.setGL14NAMETITLE(Handler.ifIsNull(rs.getString(57)));
                    emp.setGL14MAILFLAG(rs.getString(58));
                    emp.setGL14OFFCODE(rs.getString(59));
                    emp.setGL14OFFTOWN(Handler.ifIsNull(rs.getString("TOWNOFF")));
                    emp.setGL14BPRINT(rs.getString(61));
                    emp.setGL14ADD21(rs.getString(62));
                    emp.setGL14ADD22(rs.getString(63));
                    emp.setGL14ADD23(rs.getString(64));
                    emp.setGL14CODE2(rs.getString(65));
                    emp.setGL14TOWN2(Handler.ifIsNull(rs.getString("TOWN")));
                    emp.setGL14HTEL2(rs.getString(67));
                    emp.setGL14HTELX(rs.getString(68));
                    emp.setGL14SECURE(rs.getString(69));
                    emp.setGL14PBAR(rs.getString(70));
                    emp.setGL14SNOTICE(rs.getString(71));
                    emp.setGL14PARENTID(rs.getString(72));
                    emp.setGL14MTEL(rs.getString(73));
                    emp.setGL14RMVD(rs.getString(74));
                    emp.setGL14DEFMODE(rs.getString(75));
                    emp.setGL02GRP(rs.getString(77));
                    emp.setGL02NAME(rs.getString(78));
                    emp.setGL07CATE(rs.getString(79));
                    emp.setGL07DESC(Handler.ifIsNull(rs.getString(80)));
                    emp.setGL08STAT(rs.getString(81));
                    emp.setGL08DESC(Handler.ifIsNull(rs.getString(82)));
                    emp.setGL71BRNC(rs.getString(83));
                    emp.setGL71DESC(Handler.ifIsNull(rs.getString(84)));
                    emp.setGL13RACE(rs.getString(85));
                    emp.setGL13DESC(rs.getString(86));
                    emp.setGL67RELIG(rs.getString(87));
                    emp.setGL67DESC(rs.getString(88));
                    emp.setGL69DESG(rs.getString(89));
                    emp.setGL69DESC(rs.getString(90));
                    emp.setGL11DEPT(Handler.ifIsNull(rs.getString(91)));
                    emp.setGL11NAME(Handler.ifIsNull(rs.getString(92)));
                    emp.setGL12COURSE(Handler.ifIsNull(rs.getString(93)));
                    emp.setGL12DESC(Handler.ifIsNull(rs.getString(94)));
                    emp.setGL15TOWN(Handler.ifIsNull(rs.getString(95)));
                    emp.setGL15DESC(Handler.ifIsNull(rs.getString(96)));
                    emp.setGL64CODE(Handler.ifIsNull(rs.getString(97)));
                    emp.setGL64TITLE(Handler.ifIsNull(rs.getString(98)));
                    emp.setdaterec(DateFormatter.DBToDisplayFormat(rs.getString(99)));
                    emp.setrecby(Handler.ifIsNull(rs.getString(100)));
                    emp.setDESCRIPTIONTITLE(Handler.ifIsNull(rs.getString("DESTITLE")));
                    emp.setDESCRIPTIONRACE(Handler.ifIsNull(rs.getString("DESRACE")));
                    emp.setDESCRIPTIONRELIGION(Handler.ifIsNull(rs.getString("DESRELIGION")));
                    emp.setDESCRIPTIONDESC(Handler.ifIsNull(rs.getString("DESDESC")));
                    emp.setDESCRIPTIONTOWNR1(Handler.ifIsNull(rs.getString("TOWNR1")));
                    emp.setDESCRIPTIONTOWNR2(Handler.ifIsNull(rs.getString("TOWNR2")));
                    emp.setDESCRIPTIONTOWNROFF(Handler.ifIsNull(rs.getString("TOWNROFF")));
                    emp.setGL15DESC2(Handler.ifIsNull(rs.getString("TOWNDESC")));
                    emp.setGL15DESC3(Handler.ifIsNull(rs.getString("TOWNOFFTDESC")));
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
        return emp;
    }

    public Foundation getSMDCode(String GL47SMD) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLSMD WHERE GL47SMD='" + GL47SMD + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL47SMD(rs.getString(1));
                    emp.setGL47DESC(rs.getString(2));
                    emp.setGL47DISPLAY(Handler.ifIsNull(rs.getString(6)));
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
        return emp;
    }

    public void updateSMD(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLSMD SET GL47DESC=?, GL47DISPLAY=? WHERE GL47SMD=?");
                pstmt.setString(1, e.getGL47DESC());
                pstmt.setString(2, e.getGL47DISPLAY());
                pstmt.setString(3, e.getGL47SMD());
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

    public Foundation getCurrency(String GL24FOREX) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLFORX WHERE GL24FOREX='" + GL24FOREX + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL24FOREX(rs.getString(1));
                    emp.setGL24DESC(Handler.ifIsNull(rs.getString(2)));
                    emp.setGL24PRATE(Handler.ifIsNull(rs.getString(3)));
                    emp.setGL24BRATE(Handler.ifIsNull(rs.getString(4)));
                    emp.setGL24PDATE(DateFormatter.DBToDisplayFormat(rs.getString(5)));
                    emp.setGL24BDATE(DateFormatter.DBToDisplayFormat(rs.getString(6)));
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
        return emp;
    }

    public void updateCurrency(Foundation e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLFORX SET GL24DESC=?, GL24PRATE=?, GL24BRATE=?, GL24PDATE=?, GL24BDATE=? WHERE GL24FOREX=?");
                System.out.println("UPDATE GLFORX SET GL24DESC='" + e.getGL24DESC() + "', GL24PRATE='" + e.getGL24PRATE() + "', GL24BRATE='" + e.getGL24BRATE() + "', GL24PDATE='" + e.getGL24PDATE() + "', GL24BDATE='" + e.getGL24BDATE() + "' WHERE GL24FOREX='" + e.getGL24FOREX() + "'");
                pstmt.setString(1, e.getGL24DESC());
                pstmt.setString(2, e.getGL24PRATE());
                pstmt.setString(3, e.getGL24BRATE());
                pstmt.setString(4, e.getGL24PDATE());
                pstmt.setString(5, e.getGL24BDATE());
                pstmt.setString(6, e.getGL24FOREX());
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

    public Foundation getPatronElig(String GL27CATE, String GL27ICAT, String GL27SMD, String GL27BRNC) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GL27CATE,GL27ICAT,GL27SMD,GL27BRNC,GL27PLOAN, GL27ELIG, GL27PTYPE, GL27RENEW, GL27GRACE1, GL27GRACE2, GL27GRACE3, GL27MAXFINE, GL27INCFINE, GL27DATEREC, GL27RECBY, GL27LASTDATE, GL27ALLOWOVD, GL27TIMESCOST, GL27RSTR, GL27PRCFEES, GL27FGRACE1, GL27FGRACE2, GL27FGRACE3, GL27ALLOWRSV, GL27BKMULTP, GL27BKGRACE, GL07CATE, GL07DESC, GL10ICAT, GL10DESC, GL47SMD, GL47DESC GL71BRNC, GL71DESC FROM GLELIG LEFT JOIN GLCATE ON GL07CATE = GL27CATE  LEFT JOIN GLICAT ON GL10ICAT = GL27ICAT  LEFT JOIN GLSMD ON GL47SMD = GL27SMD  LEFT JOIN GLBRNC ON GL71BRNC = GL27BRNC WHERE GL27CATE='" + GL27CATE + "' AND " + " GL27ICAT='" + GL27ICAT + "' AND GL27SMD='" + GL27SMD + "' AND GL27BRNC='" + GL27BRNC + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL27CATE(rs.getString(1));
                    emp.setGL27ICAT(rs.getString(2));
                    emp.setGL27SMD(rs.getString(3));
                    emp.setGL27BRNC(rs.getString(4));
                    emp.setGL27PLOAN(rs.getString(5));
                    emp.setGL27ELIG(rs.getString(6));
                    emp.setGL27PTYPE(rs.getString(7));
                    emp.setGL27RENEW(rs.getString(8));
                    emp.setGL27GRACE1(rs.getString(9));
                    emp.setGL27GRACE2(rs.getString(10));
                    emp.setGL27GRACE3(rs.getString(11));
                    emp.setGL27MAXFINE(rs.getString(12));
                    emp.setGL27INCFINE(rs.getString(13));
                    emp.setGL27DATEREC(rs.getString(14));
                    emp.setGL27RECBY(rs.getString(15));
                    emp.setGL27LASTDATE(rs.getString(16));
                    emp.setGL27ALLOWOVD(rs.getString(17));
                    emp.setGL27TIMESCOST(rs.getString(18));
                    emp.setGL27RSTR(rs.getString(19));
                    emp.setGL27PRCFEES(rs.getString(20));
                    emp.setGL27FGRACE1(rs.getString(21));
                    emp.setGL27FGRACE2(rs.getString(22));
                    emp.setGL27FGRACE3(rs.getString(23));
                    emp.setGL27ALLOWRSV(rs.getString(24));
                    emp.setGL27BKMULTP(rs.getString(25));
                    emp.setGL27BKGRACE(rs.getString(26));
                    emp.setGL07CATE(rs.getString(27));
                    emp.setGL07DESC(Handler.ifIsNull(rs.getString(28)));
                    emp.setGL10ICAT(rs.getString(29));
                    emp.setGL10DESC(rs.getString(30));
                    emp.setGL47SMD(rs.getString(31));
                    emp.setGL47DESC(rs.getString(32));
                    emp.setGL71BRNC(rs.getString(33));
                    emp.setGL71DESC(rs.getString(34));
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
        return emp;
    }

    public Foundation getPatron(String GL14PATR) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GL14PATR, GL14NAME FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + GL14PATR + "')");
                System.out.println("SELECT GL14PATR, GL14NAME FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + GL14PATR + "')");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL14PATR(rs.getString(1));
                    emp.setGL14NAME(rs.getString(2));
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
        return emp;
    }

    public List<Foundation> getTown() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'M'";
                System.out.println("QUERYTOWN :" + query);
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(Handler.ifIsNull(rs.getString(1)));
                    fnd.setDESCRIPTION(Handler.ifIsNull(rs.getString(2)));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getTownNotIn(String town) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' AND CODE NOT IN ('" + town + "')";
                System.out.println("QUERY NOT TOWN :" + query);
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(Handler.ifIsNull(rs.getString(1)));
                    fnd.setDESCRIPTION(Handler.ifIsNull(rs.getString(2)));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    rs.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                try {
                    con.close();
                }
                catch (Exception exception) {}
            }
        }
        finally {
            try {
                rs.close();
            }
            catch (Exception exception) {}
            try {
                con.close();
            }
            catch (Exception exception) {}
        }
        return list;
    }

    public List<Foundation> getGroupId(String id) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL02GRP, GL02NAME FROM GLGRMA WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR LEFT JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + id + "'))";
                System.out.println("groupID" + query);
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL02GRP(rs.getString(1));
                    fnd.setGL02NAME(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getGroupIdNotIN(String id, String group) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL02GRP, GL02NAME FROM GLGRMA WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR LEFT JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + id + "'))" + "AND GL02GRP NOT IN ('" + group + "')";
                System.out.println("groupID" + query);
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL02GRP(rs.getString(1));
                    fnd.setGL02NAME(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getGroupId() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL02GRP, GL02NAME FROM GLGRMA";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL02GRP(rs.getString(1));
                    fnd.setGL02NAME(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public static List<Foundation> getAcclevel(String id) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL02ACL FROM GLGRMA JOIN GLPATR ON GL14GRID = GL02GRP WHERE GL14PATR = '" + id + "'";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setAcclevel(rs.getString("GL02ACL"));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getGroupIdNotIn(String GL14GRID) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL02GRP, GL02NAME, GL02GRP FROM GLGRMA WHERE GL02ACL > (SELECT GL02ACL FROM GLGRMA WHERE GL02GRP='" + GL14GRID + "') " + "AND GL02GRP NOT IN ('" + GL14GRID + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL02GRP(rs.getString(1));
                    fnd.setGL02NAME(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getCate() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT GL07CATE, GL07DESC FROM GLCATE";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL07CATE(rs.getString(1));
                    fnd.setGL07DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Foundation> getCateNotIn(String GL14CATE) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL07CATE, GL07DESC FROM GLCATE WHERE GL07CATE NOT IN ('" + GL14CATE + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL07CATE(rs.getString(1));
                    fnd.setGL07DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getStat() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL08STAT, GL08DESC FROM GLSTAT";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL08STAT(rs.getString(1));
                    fnd.setGL08DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getStatNotIn(String GL14STAT) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL08STAT, GL08DESC FROM GLSTAT WHERE GL08STAT NOT IN ('" + GL14STAT + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL08STAT(rs.getString(1));
                    fnd.setGL08DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getRace() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'J'";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString(1));
                    fnd.setDESCRIPTION(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getRaceNotIn(String GL14RACE) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'J' AND CODE NOT IN ('" + GL14RACE + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString(1));
                    fnd.setDESCRIPTION(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getCourse() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL12COURSE, GL12DESC FROM GLCOUR";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL12COURSE(rs.getString(1));
                    fnd.setGL12DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getCourseNotIn(String GL14COURSE) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL12COURSE, GL12DESC FROM GLCOUR WHERE GL12COURSE NOT IN ('" + GL14COURSE + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL12COURSE(rs.getString(1));
                    fnd.setGL12DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getNameTitle() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'I'";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString(1));
                    fnd.setDESCRIPTION(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getNameTitleNotIn(String GL14NAMETITLE) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'I' AND CODE NOT IN ('" + GL14NAMETITLE + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString(1));
                    fnd.setDESCRIPTION(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getReli() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'K'";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString(1));
                    fnd.setDESCRIPTION(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getReliNotIn(String GL14RELIGION) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'K' AND CODE NOT IN ('" + GL14RELIGION + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                System.out.println(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString(1));
                    fnd.setDESCRIPTION(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    rs.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                try {
                    stmt.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                try {
                    con.close();
                }
                catch (Exception exception) {}
            }
        }
        finally {
            try {
                rs.close();
            }
            catch (Exception exception) {}
            try {
                stmt.close();
            }
            catch (Exception exception) {}
            try {
                con.close();
            }
            catch (Exception exception) {}
        }
        return list;
    }

    public List<Foundation> getDesg() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'D'";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString(1));
                    fnd.setDESCRIPTION(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getDesgNotIn(String GL14DESC) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'D' AND CODE NOT IN('" + GL14DESC + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString(1));
                    fnd.setDESCRIPTION(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    rs.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                try {
                    stmt.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                try {
                    con.close();
                }
                catch (Exception exception) {}
            }
        }
        finally {
            try {
                rs.close();
            }
            catch (Exception exception) {}
            try {
                stmt.close();
            }
            catch (Exception exception) {}
            try {
                con.close();
            }
            catch (Exception exception) {}
        }
        return list;
    }

    public List<Foundation> getDept() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL11DEPT, GL11NAME FROM GLDEPT";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL11DEPT(rs.getString(1));
                    fnd.setGL11NAME(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getDeptNotIn(String GL14DEPT) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL11DEPT, GL11NAME FROM GLDEPT WHERE GL11DEPT NOT IN ('" + GL14DEPT + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL11DEPT(rs.getString(1));
                    fnd.setGL11NAME(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getBranch() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL71BRNC, GL71DESC FROM GLBRNC";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL71BRNC(rs.getString(1));
                    fnd.setGL71DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Foundation> getBranchNotIn(String GL14BRNC) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL71BRNC, GL71DESC FROM GLBRNC WHERE GL71BRNC NOT IN ('" + GL14BRNC + "')";
                System.out.println(query);
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL71BRNC(rs.getString(1));
                    fnd.setGL71DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getBranchVal(String BRNC) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL71BRNC, GL71DESC FROM GLBRNC WHERE GL71BRNC = '" + BRNC + "'";
                System.out.println(query);
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL71BRNC(rs.getString(1));
                    fnd.setGL71DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getICat() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL10ICAT, GL10DESC FROM GLICAT";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL10ICAT(rs.getString(1));
                    fnd.setGL10DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Foundation> getSMD() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL47SMD, GL47DESC FROM GLSMD";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL47SMD(rs.getString(1));
                    fnd.setGL47DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Foundation getMARCDESC(String DESC) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GL16MARC, GL16DESC FROM GLMARC WHERE GL16MARC='" + DESC + "'");
                System.out.println("SELECT GL16MARC, GL16DESC FROM GLMARC WHERE GL16MARC='" + DESC + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL16MARC(rs.getString(1));
                    emp.setGL16DESC(rs.getString(2));
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
        return emp;
    }

    public List<Foundation> getMARC() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL16MARC, GL16DESC FROM GLMARC";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL16MARC(rs.getString(1));
                    fnd.setGL16DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getMARCNOTIN(String GL16MARC) throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL16MARC, GL16DESC FROM GLMARC WHERE GL16MARC NOT IN ('" + GL16MARC + "')";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL16MARC(rs.getString(1));
                    fnd.setGL16DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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

    public List<Foundation> getSATELLITE() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = 'SATELLITE'";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setVALUE99(rs.getString(1));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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
}
