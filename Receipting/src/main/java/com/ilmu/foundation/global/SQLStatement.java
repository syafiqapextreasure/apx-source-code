/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.global;

import com.ilmu.foundation.global.Branch;
import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLStatement {
    private Connection con;

    public Foundation getTotalDocs(String controlNo) {
        Foundation ct = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select Count(CT03DOCNO) as TotalDocNo, CT03DOCNO, CT03STAT from CTDOCM WHERE CT03MATNO = '" + controlNo + "'GROUP BY CT03DOCNO, CT03STAT");
                System.out.println(rs);
                while (rs.next()) {
                    ct = new Foundation();
                    ct.setTotalDocNo(rs.getInt("TotalDocNo"));
                    ct.setTotalDoc(rs.getString("CT03DOCNO"));
                    ct.setTotalStat(rs.getString("CT03STAT"));
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

    public Foundation getCourse(String GL12COURSE) {
        Foundation emp = null;
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
                    emp.setGL12DESC(rs.getString(2));
                    emp.setGL12TUTOR(rs.getString(3));
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

    public Branch getBrnchCode(String GL71BRNC) {
        Branch emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLBRNC WHERE GL71BRNC='" + GL71BRNC + "'");
                while (rs.next()) {
                    emp = new Branch();
                    emp.setGL71BRNC(rs.getString(1));
                    emp.setGL71DESC(rs.getString(2));
                    emp.setGL71DISPLAY(rs.getString(3));
                    emp.setGL71ADD1(rs.getString(6));
                    emp.setGL71ADD2(rs.getString(7));
                    emp.setGL71ADD3(rs.getString(8));
                    emp.setGL71TOWN(rs.getString(10));
                    emp.setGL71POSCODE(rs.getString(9));
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

    public void updateBrnch(Branch e) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement("UPDATE GLBRNC SET GL71DESC=?, GL71DISPLAY=?, GL71ADD1=?, GL71ADD2=?, GL71ADD3=?, GL71TOWN=?, GL71POSCODE=? WHERE GL71BRNC=?");
                pstmt.setString(1, e.getGL71DESC());
                pstmt.setString(2, e.getGL71DISPLAY());
                pstmt.setString(3, e.getGL71ADD1());
                pstmt.setString(4, e.getGL71ADD2());
                pstmt.setString(5, e.getGL71ADD3());
                pstmt.setString(6, e.getGL71TOWN());
                pstmt.setString(7, e.getGL71POSCODE());
                pstmt.setString(8, e.getGL71BRNC());
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

    public Foundation getGlobal(String CODE, String FCODE) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM FNDCODE WHERE CODE='" + CODE + "' AND FCODE='" + FCODE + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setFCODE(rs.getString(1));
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

    public Foundation getDepartment(String GL11DEPT) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLDEPT WHERE GL11DEPT='" + GL11DEPT + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL11DEPT(rs.getString(1));
                    emp.setGL11NAME(rs.getString(2));
                    emp.setGL11HEAD(rs.getString(3));
                    emp.setGL11ADD1(rs.getString(4));
                    emp.setGL11ADD2(rs.getString(5));
                    emp.setGL11ADD3(rs.getString(6));
                    emp.setGL11POSCODE(rs.getString(7));
                    emp.setGL11TOWN(rs.getString(8));
                    emp.setGL11TEL(rs.getString(9));
                    emp.setGL11FAX(rs.getString(10));
                    emp.setGL11STAFF(rs.getString(11));
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
                pstmt = con.prepareStatement("UPDATE GLDEPT SET GL11NAME=?, GL11HEAD=?, GL11ADD1=?, GL11ADD2=?, GL11ADD3=?, GL11POSCODE=?, GL11TOWN=?, GL11TEL=?, GL11FAX=?, GL11STAFF=? WHERE GL11DEPT=?");
                pstmt.setString(1, e.getGL11NAME());
                pstmt.setString(2, e.getGL11HEAD());
                pstmt.setString(3, e.getGL11ADD1());
                pstmt.setString(4, e.getGL11ADD2());
                pstmt.setString(5, e.getGL11ADD3());
                pstmt.setString(6, e.getGL11POSCODE());
                pstmt.setString(7, e.getGL11TOWN());
                pstmt.setString(8, e.getGL11TEL());
                pstmt.setString(9, e.getGL11FAX());
                pstmt.setString(10, e.getGL11STAFF());
                pstmt.setString(11, e.getGL11DEPT());
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

    public Foundation getGenSubj(String GL54SUBJSTA) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLSUBJ WHERE GL54SUBJSTA='" + GL54SUBJSTA + "'");
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
                pstmt = con.prepareStatement("UPDATE GLSUBJ SET GL54SUBJEND=?, GL54MARK=?, GL54DESC=? WHERE GL54SUBJSTA=?");
                pstmt.setString(1, e.getGL54SUBJEND());
                pstmt.setString(2, e.getGL54MARK());
                pstmt.setString(3, e.getGL54DESC());
                pstmt.setString(4, e.getGL54SUBJSTA());
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
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL39CODE(rs.getString(1));
                    emp.setGL39PUB(rs.getString(23));
                    emp.setGL39SUPPLIER(rs.getString(22));
                    emp.setGL39BINDER(rs.getString(21));
                    emp.setGL39NAME(rs.getString(2));
                    emp.setGL39PERINC(rs.getString(8));
                    emp.setGL39DESIG(rs.getString(9));
                    emp.setGL39ADD1(rs.getString(3));
                    emp.setGL39ADD2(rs.getString(4));
                    emp.setGL39ADD3(rs.getString(5));
                    emp.setGL39PCODE(rs.getString(19));
                    emp.setGL39IPADD(rs.getString(20));
                    emp.setGL39TELNO(rs.getString(6));
                    emp.setGL39FAX(rs.getString(7));
                    emp.setGL39CONTNO(rs.getString(10));
                    emp.setGL39FUND(rs.getString(32));
                    emp.setGL39CBDATE(rs.getString(12));
                    emp.setGL39CEDATE(rs.getString(13));
                    emp.setGL39USERNAME(rs.getString(29));
                    emp.setGL39PASSWORD(rs.getString(30));
                    emp.setGL39ACCNO(rs.getString(17));
                    emp.setGL39BANK(rs.getString(33));
                    emp.setGL39EMAIL(rs.getString(31));
                    emp.setGL39STATUS(rs.getString(28));
                    emp.setGL39REMARK(rs.getString(16));
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
                pstmt = con.prepareStatement("UPDATE GLVEND SET GL39NAME=?, GL39PUB=?, GL39SUPPLIER=?, GL39BINDER=?, GL39PERINC=?, GL39DESIG=?, GL39ADD1=?, GL39ADD2=?,GL39ADD3=?, GL39PCODE=?, GL39IPADD=?, GL39TELNO=?, GL39FAX=?,GL39CONTNO=?, GL39FUND=?, GL39CBDATE=?, GL39CEDATE=?, GL39USERNAME=?, GL39PASSWORD=?, GL39ACCNO=?, GL39BANK=?, GL39EMAIL=?, GL39STATUS=?,GL39REMARK=? WHERE GL39CODE=?");
                pstmt.setString(1, e.getGL39NAME());
                pstmt.setString(2, e.getGL39PUB());
                pstmt.setString(3, e.getGL39SUPPLIER());
                pstmt.setString(4, e.getGL39BINDER());
                pstmt.setString(5, e.getGL39PERINC());
                pstmt.setString(6, e.getGL39DESIG());
                pstmt.setString(7, e.getGL39ADD1());
                pstmt.setString(8, e.getGL39ADD2());
                pstmt.setString(9, e.getGL39ADD3());
                pstmt.setString(10, e.getGL39PCODE());
                pstmt.setString(11, e.getGL39IPADD());
                pstmt.setString(12, e.getGL39TELNO());
                pstmt.setString(13, e.getGL39FAX());
                pstmt.setString(14, e.getGL39CONTNO());
                pstmt.setString(15, e.getGL39FUND());
                pstmt.setString(16, e.getGL39CBDATE());
                pstmt.setString(17, e.getGL39CEDATE());
                pstmt.setString(18, e.getGL39USERNAME());
                pstmt.setString(19, e.getGL39PASSWORD());
                pstmt.setString(20, e.getGL39ACCNO());
                pstmt.setString(21, e.getGL39BANK());
                pstmt.setString(22, e.getGL39EMAIL());
                pstmt.setString(23, e.getGL39STATUS());
                pstmt.setString(24, e.getGL39REMARK());
                pstmt.setString(25, e.getGL39CODE());
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
                con.close();
            }
        }
        finally {
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLCATE WHERE GL07CATE='" + GL07CATE + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL07CATE(rs.getString(1));
                    emp.setGL07DESC(rs.getString(2));
                    emp.setGL07POPDB(rs.getString(3));
                    emp.setGL07EMAIL(rs.getString(4));
                    emp.setGL07SCHAR(rs.getString(5));
                    emp.setGL07RATER(rs.getString(6));
                    emp.setGL07ARTXN(rs.getString(7));
                    emp.setGL07MODEM(rs.getString(8));
                    emp.setGL07BRFORC(rs.getString(9));
                    emp.setGL07DCFORC(rs.getString(10));
                    emp.setGL07ELIG(rs.getString(14));
                    emp.setGL07MAXFINE(rs.getString(15));
                    emp.setGL07FINELIMIT(rs.getString(16));
                    emp.setGL07ILLOUT(rs.getString(17));
                    emp.setGL07MAXRESV(rs.getString(18));
                    emp.setGL07MAXACCT(rs.getString(19));
                    emp.setGL07ALLOWOVD(rs.getString(20));
                    emp.setGL07ALLOWRSV(rs.getString(21));
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
                pstmt = con.prepareStatement("UPDATE GLVEND SET GL39NAME=?, GL39PUB=?, GL39SUPPLIER=?, GL39BINDER=?, GL39PERINC=?, GL39DESIG=?, GL39ADD1=?, GL39ADD2=?,GL39ADD3=?, GL39PCODE=?, GL39IPADD=?, GL39TELNO=?, GL39FAX=?,GL39CONTNO=?, GL39FUND=?, GL39CBDATE=?, GL39CEDATE=?, GL39USERNAME=?, GL39PASSWORD=?, GL39ACCNO=?, GL39BANK=?, GL39EMAIL=?, GL39STATUS=?,GL39REMARK=? WHERE GL39CODE=?");
                pstmt.setString(1, e.getGL39NAME());
                pstmt.setString(2, e.getGL39PUB());
                pstmt.setString(3, e.getGL39SUPPLIER());
                pstmt.setString(4, e.getGL39BINDER());
                pstmt.setString(5, e.getGL39PERINC());
                pstmt.setString(6, e.getGL39DESIG());
                pstmt.setString(7, e.getGL39ADD1());
                pstmt.setString(8, e.getGL39ADD2());
                pstmt.setString(9, e.getGL39ADD3());
                pstmt.setString(10, e.getGL39PCODE());
                pstmt.setString(11, e.getGL39IPADD());
                pstmt.setString(12, e.getGL39TELNO());
                pstmt.setString(13, e.getGL39FAX());
                pstmt.setString(14, e.getGL39CONTNO());
                pstmt.setString(15, e.getGL39FUND());
                pstmt.setString(16, e.getGL39CBDATE());
                pstmt.setString(17, e.getGL39CEDATE());
                pstmt.setString(18, e.getGL39USERNAME());
                pstmt.setString(19, e.getGL39PASSWORD());
                pstmt.setString(20, e.getGL39ACCNO());
                pstmt.setString(21, e.getGL39BANK());
                pstmt.setString(22, e.getGL39EMAIL());
                pstmt.setString(23, e.getGL39STATUS());
                pstmt.setString(24, e.getGL39REMARK());
                pstmt.setString(25, e.getGL39CODE());
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM GLLOCA WHERE GL05LOCA='" + GL05LOCA + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL05BRNC(rs.getString(25));
                    emp.setGL05LOCA(rs.getString(1));
                    emp.setGL05DESC(rs.getString(2));
                    emp.setGL05DISPLAY(rs.getString(24));
                    emp.setGL05SUBJECT(rs.getString(3));
                    emp.setGL05MATCAP(rs.getString(5));
                    emp.setGL05LAYOUT(rs.getString(20));
                    emp.setGL05IPADD(rs.getString(4));
                    emp.setGL05NOSERVER(rs.getString(6));
                    emp.setGL05LNPRT(rs.getString(9));
                    emp.setGL05NOTER(rs.getString(7));
                    emp.setGL05LJPRT(rs.getString(10));
                    emp.setGL05NOPC(rs.getString(8));
                    emp.setGL05DMPRT(rs.getString(11));
                    emp.setGL05MODEM(rs.getString(12));
                    emp.setGL05MMEDIA(rs.getString(13));
                    emp.setGL05SDI(rs.getString(15));
                    emp.setGL05SDDS(rs.getString(16));
                    emp.setGL05JARING(rs.getString(18));
                    emp.setGL05CDROM(rs.getString(14));
                    emp.setGL05IRL(rs.getString(17));
                    emp.setGL05NST(rs.getString(19));
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
                    emp.setGL47DISPLAY(rs.getString(3));
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
                    emp.setGL24DESC(rs.getString(2));
                    emp.setGL24PRATE(rs.getString(3));
                    emp.setGL24BRATE(rs.getString(4));
                    emp.setGL24PDATE(rs.getString(5));
                    emp.setGL24BDATE(rs.getString(6));
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

    public Foundation getPatron(String GL14PATR) {
        Foundation emp = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT GL14PATR, GL14NAME FROM GLPATR WHERE GL14PATR='" + GL14PATR + "'");
                while (rs.next()) {
                    emp = new Foundation();
                    emp.setGL14PATR(rs.getString(1));
                    emp.setGL14NAME(rs.getString(4));
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
                String query = "SELECT GL15TOWN, GL15DESC FROM GLTOWN";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL15TOWN(rs.getString(1));
                    fnd.setGL15DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                con.close();
            }
        }
        finally {
            con.close();
        }
        return list;
    }

    public List<Foundation> getLocation() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL05LOCA, GL05DESC FROM GLLOCA";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL05LOCA(rs.getString(1));
                    fnd.setGL05DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                con.close();
            }
        }
        finally {
            con.close();
        }
        return list;
    }

    public List<Foundation> getItemCat() throws SQLException {
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
                con.close();
            }
        }
        finally {
            con.close();
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
                String query = "SELECT GL47SMD, GL47DESC FROM [GLSMD]";
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
                con.close();
            }
        }
        finally {
            con.close();
        }
        return list;
    }

    public List<Foundation> getCurrencyCode() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL24FOREX, GL24BRATE, GL24DESC FROM [GLFORX]";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL24FOREX(rs.getString(1));
                    fnd.setGL24BRATE(rs.getString(2));
                    fnd.setGL24DESC(rs.getString(3));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                con.close();
            }
        }
        finally {
            con.close();
        }
        return list;
    }

    public List<Foundation> getCondition() throws SQLException {
        ArrayList<Foundation> list = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL06COND, GL06DESC FROM [GLCOND]";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL06COND(rs.getString(1));
                    fnd.setGL06DESC(rs.getString(2));
                    list.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                con.close();
            }
        }
        finally {
            con.close();
        }
        return list;
    }
}
