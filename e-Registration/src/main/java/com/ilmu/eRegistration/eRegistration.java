/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.eRegistration;

import com.ilmu.global.DateTime;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.util.HashMap;

public class eRegistration {
    public static boolean CreateFundAccountChart(String namaAhli, String gelaran, String newICval, String inputDOB, String alamat, String alamat2, String alamat3, String negeri, String poskod, String alamatSurat, String alamatSurat2, String alamatSurat3, String negeriSurat, String poskodSurat, String warganegara, String phone, String houseno, String kaum, String kad, String emailAhli, String sex) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        System.out.println("date:" + DateTime.getTodaySystemDate());
        if (gelaran != "") {
            valueStr.put("GL14NAMETITLE", gelaran);
        }
        if (kaum != "") {
            valueStr.put("GL14RACE", kaum);
        }
        valueStr.put("GL14PATR", newICval);
        valueStr.put("GL14NAME", namaAhli);
        valueStr.put("GL14NEWIC", newICval);
        valueStr.put("GL14DOB", inputDOB);
        valueStr.put("GL14ADD1", alamat);
        valueStr.put("GL14ADD2", alamat2);
        valueStr.put("GL14ADD3", alamat3);
        valueStr.put("GL14TOWN", negeri);
        valueStr.put("GL14CODE", poskod);
        valueStr.put("GL14ADD21", alamatSurat);
        valueStr.put("GL14ADD22", alamatSurat2);
        valueStr.put("GL14ADD23", alamatSurat3);
        valueStr.put("GL14TOWN", negeriSurat);
        valueStr.put("GL14CODE2", poskodSurat);
        valueStr.put("GL14COLOR", warganegara);
        valueStr.put("GL14MTEL", phone);
        valueStr.put("GL14HTEL", houseno);
        valueStr.put("GL14SNOTICE", kad);
        valueStr.put("GL14IPADD", emailAhli);
        valueStr.put("GL14STAT", "X");
        valueStr.put("GL14GRID", "99");
        valueStr.put("GL14SEX", sex);
        valueStr.put("GL14RMVD", "Y");
        valueStr.put("GL14DATEREC", DateTime.getTodaySystemDate());
        valueStr.put("GL14MEMDATE", DateTime.getTodaySystemDate());
        valueStr.put("GL14RECBY", "EREGISTER");
        String query = QueryBuilder.createInsertQuery("GLPATR", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }
}
