/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.PDF;

import com.kmlink.ilmu.shared.global.DateFormatter;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class renewalLetter {
    private String PATRONID = null;
    private String PATRONNAME = null;
    private String ADDR1 = null;
    private String ADDR2 = null;
    private String ADDR3 = null;
    private String MEMEXPIRYDATE = null;

    public String getPATRONID() {
        return Handler.ifIsNull(this.PATRONID);
    }

    public String getPATRONNAME() {
        return Handler.ifIsNull(this.PATRONNAME);
    }

    public String getADDR1() {
        return Handler.ifIsNull(this.ADDR1);
    }

    public String getADDR2() {
        return Handler.ifIsNull(this.ADDR2);
    }

    public String getADDR3() {
        return Handler.ifIsNull(this.ADDR3);
    }

    public String getMEMEXPIRYDATE() {
        return Handler.ifIsNull(this.MEMEXPIRYDATE);
    }

    public renewalLetter(String PATRONID, String PATRONNAME, String ADDR1, String ADDR2, String ADDR3, String MEMEXPIRYDATE) {
        this.PATRONID = PATRONID;
        this.PATRONNAME = PATRONNAME;
        this.ADDR1 = ADDR1;
        this.ADDR2 = ADDR2;
        this.ADDR3 = ADDR3;
        this.MEMEXPIRYDATE = MEMEXPIRYDATE;
    }

    public static renewalLetter GetPatronInfo(String id) throws SQLException {
        renewalLetter GetPatronInfo;
        block25: {
            GetPatronInfo = null;
            String sql = "SELECT GL14PATR, GL14NAME, GL14DESC, GL14MAILFLAG, GL14ADD1, GL14ADD2, GL14ADD3, GL14CODE, GL14TOWN, GL14HTEL, GL14ADD21, GL14ADD22, GL14ADD23, GL14CODE2, GL14TOWN2, GL14HTEL2, GL14OFFADD1, GL14OFFADD2, GL14OFFADD3, GL14OFFCODE, GL14OFFTOWN, GL14OTEL, GL14EXPDATE FROM GLPATR WHERE GL14PATR = '" + id + "'";
            System.out.println(sql);
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (!rs.next()) {
                        String rsId = "";
                        String rsName = "";
                        String rsAdd1 = "";
                        String rsAdd2 = "";
                        String rsAdd3 = "";
                        String reExpdate = "";
                        GetPatronInfo = new renewalLetter(rsId, rsName, rsAdd1, rsAdd2, rsAdd3, reExpdate);
                        break block25;
                    }
                    do {
                        String rsAdd3;
                        String rsAdd2;
                        String rsAdd1;
                        String mailflag;
                        String rsId = Handler.ifIsNull(rs.getString("GL14PATR"));
                        String rsName = Handler.ifIsNull(rs.getString("GL14NAME"));
                        String reExpdate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14EXPDATE")));
                        switch (mailflag = Handler.ifIsNull(rs.getString("GL14MAILFLAG"))) {
                            case "S": {
                                rsAdd1 = Handler.ifIsNull(rs.getString("GL14ADD21"));
                                rsAdd2 = Handler.ifIsNull(rs.getString("GL14ADD21"));
                                rsAdd3 = Handler.ifIsNull(rs.getString("GL14ADD21"));
                                break;
                            }
                            case "O": {
                                rsAdd1 = Handler.ifIsNull(rs.getString("GL14OFFADD1"));
                                rsAdd2 = Handler.ifIsNull(rs.getString("GL14OFFADD2"));
                                rsAdd3 = Handler.ifIsNull(rs.getString("GL14OFFADD3"));
                                break;
                            }
                            default: {
                                rsAdd1 = Handler.ifIsNull(rs.getString("GL14ADD1"));
                                rsAdd2 = Handler.ifIsNull(rs.getString("GL14ADD2"));
                                rsAdd3 = Handler.ifIsNull(rs.getString("GL14ADD3"));
                            }
                        }
                        GetPatronInfo = new renewalLetter(rsId, rsName, rsAdd1, rsAdd2, rsAdd3, reExpdate);
                    } while (rs.next());
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                    try {
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return GetPatronInfo;
    }
}
