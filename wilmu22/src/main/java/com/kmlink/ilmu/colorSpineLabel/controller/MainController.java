/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  org.apache.commons.lang3.StringUtils
 *  org.krysalis.barcode4j.impl.code39.Code39Bean
 *  org.krysalis.barcode4j.output.CanvasProvider
 *  org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Controller
 *  org.springframework.validation.BindingResult
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.servlet.ModelAndView
 */
package com.kmlink.ilmu.colorSpineLabel.controller;

import com.google.gson.Gson;
import com.kmlink.ilmu.colorSpineLabel.dao.RecordsDao;
import com.kmlink.ilmu.colorSpineLabel.model.Records;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.CanvasProvider;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @Autowired
    private RecordsDao recordsDao;

    public String formatrgb(String colcode) {
        if (colcode.matches(".*\\d.*")) {
            String a = StringUtils.substringBetween((String)colcode, (String)"r", (String)"g");
            String b = StringUtils.substringBetween((String)colcode, (String)"g", (String)"b");
            String[] arrstr = colcode.split("b");
            String rgb = "rgb(" + a + "," + b + "," + arrstr[1] + ")";
            return rgb;
        }
        return colcode;
    }

    public static String rawToDisplayFormat(String raw) {
        String[] rawArray = raw.split("\\s+");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(0);
                if (splitData != null && splitData != "") {
                    result.append(splitData);
                    if (i != rawArray.length) {
                        result.append(" ");
                    }
                }
                ++i;
            }
        }
        return result.toString();
    }

    public String generateCode39BarcodeImage(String barcodeText) {
        Code39Bean barcodeGenerator = new Code39Bean();
        barcodeGenerator.setBarHeight(4.0);
        barcodeGenerator.setFontSize(0.0);
        barcodeGenerator.setFontName("Arial");
        barcodeGenerator.setModuleWidth(0.16);
        barcodeGenerator.setQuietZone(2.5);
        barcodeGenerator.doQuietZone(true);
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(160, 12, false, 0);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            barcodeGenerator.generateBarcode((CanvasProvider)canvas, barcodeText);
            canvas.finish();
            BufferedImage barcodeImage = canvas.getBufferedImage();
            ImageIO.write((RenderedImage)barcodeImage, "png", output);
        }
        catch (Exception barcodeImage) {
            // empty catch block
        }
        String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
        return imageAsBase64;
    }

    @RequestMapping(value={"/"}, method={RequestMethod.GET})
    public String viewHome() {
        return "home";
    }

    @RequestMapping(value={"/home"}, method={RequestMethod.GET})
    public String home() {
        return "home";
    }

    public void createLabel(List<Records> listTable, ModelAndView model, String tableId, String accessionNo) {
        try {
            for (Records list1 : listTable) {
                String label1location = list1.getLocation();
                String label1classno = list1.getSpineRow();
                String callNo = list1.getCallNo();
                String label1LocationDisplay = list1.getLocationDisplay();
                model.addObject("label" + tableId + "location", (Object)label1LocationDisplay);
                List<Records> location1 = this.recordsDao.getAllColour(label1location);
                for (Records loc1 : location1) {
                    String label1colcode = loc1.getColourCode();
                    model.addObject("label" + tableId + "colcode", (Object)this.formatrgb(label1colcode));
                }
                model.addObject("label" + tableId + "callno1", (Object)label1classno);
                List<Records> table1colour1 = this.recordsDao.findColour(label1classno.substring(0, 1));
                for (Records label1 : table1colour1) {
                    String label1colour1 = label1.getColourCode2();
                    model.addObject("label" + tableId + "colour1", (Object)this.formatrgb(label1colour1));
                }
                List<Records> table1colour2 = this.recordsDao.findColour(label1classno.substring(1, 2));
                for (Records label2 : table1colour2) {
                    String label1colour2 = label2.getColourCode2();
                    model.addObject("label" + tableId + "colour2", (Object)this.formatrgb(label1colour2));
                }
                List<Records> table1colour3 = this.recordsDao.findColour(label1classno.substring(2, 3));
                for (Records label3 : table1colour3) {
                    String label1colour3 = label3.getColourCode2();
                    model.addObject("label" + tableId + "colour3", (Object)this.formatrgb(label1colour3));
                }
                String checking = "";
                if (!label1classno.matches("[\\d][\\d][\\d][A-Z]")) {
                    checking = "on";
                    List<Records> table1colour4 = this.recordsDao.findColour(label1classno.substring(3, 4));
                    for (Records label4 : table1colour4) {
                        String label1colour4 = label4.getColourCode2();
                        model.addObject("label" + tableId + "colour4", (Object)this.formatrgb(label1colour4));
                    }
                } else {
                    String label1colour4 = "white";
                    model.addObject(tableId + "colour4", (Object)this.formatrgb(label1colour4));
                }
                model.addObject("checking" + tableId, (Object)checking);
                List<Records> table1colour5 = this.recordsDao.findItemClass(list1.getItemCategory());
                for (Records label5 : table1colour5) {
                    String label1itemclass = label5.getItemClass();
                    model.addObject("label" + tableId + "itemclass", (Object)label1itemclass);
                    String label1colour5 = label5.getColourCode2();
                    model.addObject("label" + tableId + "colour5", (Object)this.formatrgb(label1colour5));
                }
                model.addObject("label" + tableId + "AuthMark", (Object)list1.getClassLabel());
                model.addObject("label" + tableId + "CallNo", (Object)list1.getCutterLabel());
                String status1 = "nonfiction";
                String barcode1 = this.generateCode39BarcodeImage(accessionNo);
                model.addObject("barcode" + tableId, (Object)barcode1);
                model.addObject("status" + tableId, (Object)status1);
                model.addObject("accessionNo" + tableId, (Object)accessionNo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("listTable" + tableId, listTable);
    }

    public void createLabel_ori(List<Records> listTable, ModelAndView model, String tableId, String accessionNo) {
        for (Records list1 : listTable) {
            String label1colour4;
            String label1colour42;
            List<Records> table1colour4;
            String label1colour3;
            List<Records> table1colour3;
            String label1colour32;
            Iterator<Records> label1colour2;
            List<Records> table1colour2;
            Iterator<Records> label1colour1;
            List<Records> table1colour1;
            String label1callno;
            String callNo;
            String authMark;
            String[] classno;
            String label1classno;
            String label1colcode;
            List<Records> location1;
            String label1LocationDisplay;
            String label1location;
            if (Character.isDigit(list1.getClassNo().charAt(0))) {
                label1location = list1.getLocation();
                label1LocationDisplay = list1.getLocationDisplay();
                model.addObject("label" + tableId + "location", (Object)label1LocationDisplay);
                location1 = this.recordsDao.getAllColour(label1location);
                for (Records loc1 : location1) {
                    label1colcode = loc1.getColourCode();
                    model.addObject("label" + tableId + "colcode", (Object)this.formatrgb(label1colcode));
                }
                label1classno = list1.getClassNo();
                model.addObject("label" + tableId + "classno", (Object)label1classno);
                classno = label1classno.split("\\s+");
                if (classno.length >= 3) {
                    authMark = String.valueOf(classno[0]) + "." + classno[1];
                    callNo = classno[2];
                    model.addObject("label" + tableId + "AuthMark", (Object)authMark);
                    model.addObject("label" + tableId + "CallNo", (Object)callNo);
                } else if (classno.length == 2) {
                    authMark = classno[0];
                    callNo = classno[1];
                    model.addObject("label" + tableId + "AuthMark", (Object)authMark);
                    model.addObject("label" + tableId + "CallNo", (Object)callNo);
                } else {
                    authMark = classno[0];
                    callNo = ".";
                    model.addObject("label" + tableId + "AuthMark", (Object)authMark);
                    model.addObject("label" + tableId + "CallNo", (Object)callNo);
                }
                label1callno = list1.getCallNo();
                model.addObject("label" + tableId + "callno", (Object)label1callno);
                table1colour1 = this.recordsDao.findColour(list1.getClassNo().substring(0, 1));
                for (Records label1 : table1colour1) {
                    label1colour1 = label1.getColourCode2();
                    model.addObject("label" + tableId + "colour1", (Object)this.formatrgb((String)((Object)label1colour1)));
                }
                table1colour2 = this.recordsDao.findColour(list1.getClassNo().substring(1, 2));
                for (Records label2 : table1colour2) {
                    label1colour2 = label2.getColourCode2();
                    model.addObject("label" + tableId + "colour2", (Object)this.formatrgb((String)((Object)label1colour2)));
                }
                if (list1.getClassNo().length() == 2) {
                    label1colour32 = "white";
                    model.addObject("label" + tableId + "colour3", (Object)this.formatrgb(label1colour32));
                } else {
                    table1colour3 = this.recordsDao.findColour(list1.getClassNo().substring(2, 3));
                    for (Records label3 : table1colour3) {
                        label1colour3 = label3.getColourCode2();
                        model.addObject("label" + tableId + "colour3", (Object)this.formatrgb(label1colour3));
                    }
                }
                if (list1.getClassNo().length() > 3) {
                    table1colour4 = this.recordsDao.findColour(list1.getClassNo().substring(4, 5));
                    for (Records label4 : table1colour4) {
                        label1colour42 = label4.getColourCode2();
                        model.addObject("label" + tableId + "colour4", (Object)this.formatrgb(label1colour42));
                    }
                } else {
                    label1colour4 = "white";
                    model.addObject("label" + tableId + "colour4", (Object)this.formatrgb(label1colour4));
                }
                List<Records> table1colour5 = this.recordsDao.findItemClass(list1.getItemCategory());
                label1colour2 = table1colour5.iterator();
                while (label1colour2.hasNext()) {
                    Records label5 = label1colour2.next();
                    String label1itemclass = label5.getItemClass();
                    model.addObject("label" + tableId + "itemclass", (Object)label1itemclass);
                    String label1colour5 = label5.getColourCode2();
                    model.addObject("label" + tableId + "colour5", (Object)this.formatrgb(label1colour5));
                }
                String status1 = "nonfiction";
                String barcode1 = this.generateCode39BarcodeImage(accessionNo);
                model.addObject("barcode" + tableId, (Object)barcode1);
                model.addObject("status" + tableId, (Object)status1);
                model.addObject("accessionNo" + tableId, (Object)accessionNo);
                continue;
            }
            label1location = list1.getLocation();
            label1LocationDisplay = list1.getLocationDisplay();
            model.addObject("label" + tableId + "location", (Object)label1LocationDisplay);
            location1 = this.recordsDao.getAllColour(label1location);
            for (Records loc1 : location1) {
                label1colcode = loc1.getColourCode();
                model.addObject("label" + tableId + "colcode", (Object)this.formatrgb(label1colcode));
            }
            label1classno = list1.getClassNo();
            model.addObject("label" + tableId + "classno", (Object)label1classno);
            classno = label1classno.split("\\s+");
            if (classno.length == 2) {
                authMark = classno[0];
                callNo = classno[1];
                model.addObject("label" + tableId + "AuthMark", (Object)authMark);
                model.addObject("label" + tableId + "CallNo", (Object)callNo);
            } else {
                authMark = classno[0];
                callNo = ".";
                model.addObject("label" + tableId + "AuthMark", (Object)authMark);
                model.addObject("label" + tableId + "CallNo", (Object)callNo);
            }
            label1callno = list1.getCallNo();
            model.addObject("label" + tableId + "callno", (Object)label1callno);
            table1colour1 = this.recordsDao.findColour(list1.getClassNo().substring(0, 1));
            for (Records label1 : table1colour1) {
                label1colour1 = label1.getColourCode2();
                model.addObject("label" + tableId + "colour1", (Object)this.formatrgb((String)((Object)label1colour1)));
            }
            table1colour2 = this.recordsDao.findColour(list1.getClassNo().substring(1, 2));
            for (Records label2 : table1colour2) {
                label1colour2 = label2.getColourCode2();
                model.addObject("label" + tableId + "colour2", (Object)this.formatrgb((String)((Object)label1colour2)));
            }
            if (list1.getClassNo().length() == 2) {
                label1colour32 = "white";
                model.addObject("label" + tableId + "colour3", (Object)this.formatrgb(label1colour32));
            } else {
                table1colour3 = this.recordsDao.findColour(list1.getClassNo().substring(2, 3));
                for (Records label3 : table1colour3) {
                    label1colour3 = label3.getColourCode2();
                    model.addObject("label" + tableId + "colour3", (Object)this.formatrgb(label1colour3));
                }
            }
            if (list1.getClassNo().length() > 3) {
                table1colour4 = this.recordsDao.findCutterColour(list1.getClassNo().substring(4, 5));
                for (Records label4 : table1colour4) {
                    label1colour42 = label4.getColourCode2();
                    model.addObject("label" + tableId + "colour4", (Object)this.formatrgb(label1colour42));
                }
            } else {
                label1colour4 = "white";
                model.addObject("label" + tableId + "colour4", (Object)this.formatrgb(label1colour4));
            }
            String status1 = "fiction";
            String barcode1 = this.generateCode39BarcodeImage(accessionNo);
            model.addObject("barcode" + tableId, (Object)barcode1);
            model.addObject("status" + tableId, (Object)status1);
            model.addObject("accessionNo" + tableId, (Object)accessionNo);
        }
        model.addObject("listTable" + tableId, listTable);
    }

    @RequestMapping(value={"/printParam"}, method={RequestMethod.POST})
    public ModelAndView printParam(@RequestParam(value="table1", required=false, defaultValue="0") String table1, @RequestParam(value="table2", required=false, defaultValue="0") String table2, @RequestParam(value="table3", required=false, defaultValue="0") String table3, @RequestParam(value="table4", required=false, defaultValue="0") String table4, @RequestParam(value="table5", required=false, defaultValue="0") String table5, @RequestParam(value="table6", required=false, defaultValue="0") String table6, @RequestParam(value="table7", required=false, defaultValue="0") String table7, @RequestParam(value="table8", required=false, defaultValue="0") String table8, @RequestParam(value="table9", required=false, defaultValue="0") String table9, @RequestParam(value="table10", required=false, defaultValue="0") String table10, @RequestParam(value="textLabel", required=false) String textLabel, @RequestParam(value="logoLink", required=false) String logoLink, @RequestParam(value="selectBranch", required=false, defaultValue="%") String selectBranch, ModelAndView model, BindingResult result) throws IOException {
        List<Records> listTable1 = this.recordsDao.retrieveByAccessionNo(table1, selectBranch);
        this.createLabel(listTable1, model, "1", table1);
        List<Records> listTable2 = this.recordsDao.retrieveByAccessionNo(table2, selectBranch);
        this.createLabel(listTable2, model, "2", table2);
        List<Records> listTable3 = this.recordsDao.retrieveByAccessionNo(table3, selectBranch);
        this.createLabel(listTable3, model, "3", table3);
        List<Records> listTable4 = this.recordsDao.retrieveByAccessionNo(table4, selectBranch);
        this.createLabel(listTable4, model, "4", table4);
        List<Records> listTable5 = this.recordsDao.retrieveByAccessionNo(table5, selectBranch);
        this.createLabel(listTable5, model, "5", table5);
        List<Records> listTable6 = this.recordsDao.retrieveByAccessionNo(table6, selectBranch);
        this.createLabel(listTable6, model, "6", table6);
        List<Records> listTable7 = this.recordsDao.retrieveByAccessionNo(table7, selectBranch);
        this.createLabel(listTable7, model, "7", table7);
        List<Records> listTable8 = this.recordsDao.retrieveByAccessionNo(table8, selectBranch);
        this.createLabel(listTable8, model, "8", table8);
        List<Records> listTable9 = this.recordsDao.retrieveByAccessionNo(table9, selectBranch);
        this.createLabel(listTable9, model, "9", table9);
        List<Records> listTable10 = this.recordsDao.retrieveByAccessionNo(table10, selectBranch);
        this.createLabel(listTable10, model, "10", table10);
        model.addObject("table1", (Object)table1);
        model.addObject("table2", (Object)table2);
        model.addObject("table3", (Object)table3);
        model.addObject("table4", (Object)table4);
        model.addObject("table5", (Object)table5);
        model.addObject("table6", (Object)table6);
        model.addObject("table7", (Object)table7);
        model.addObject("table8", (Object)table8);
        model.addObject("table9", (Object)table9);
        model.addObject("table10", (Object)table10);
        model.addObject("textLabel", (Object)textLabel);
        model.addObject("logoLink", (Object)logoLink);
        model.setViewName("/module/colorSpineLabel/outputLabel");
        return model;
    }

    @RequestMapping(value={"/byTitle"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView byTitle(@RequestParam(value="accessionNo", required=false) String accessionNo, @RequestParam(value="codeList", required=false) String codeList, ModelAndView model, Integer id) throws IOException {
        model.addObject("codeList", (Object)codeList);
        model.addObject("accessionNo", (Object)accessionNo);
        List<Records> listBranch = this.recordsDao.filterByBranch();
        model.addObject("listBranch", listBranch);
        if (accessionNo == "" || accessionNo == "") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        if (codeList == "accessionNo") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        model.addObject("message", (Object)id);
        model.setViewName("/module/colorSpineLabel/byTitle");
        return model;
    }

    @RequestMapping(value={"/byControlNum"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView byControlNum(@RequestParam(value="accessionNo", required=false) String accessionNo, @RequestParam(value="codeList", required=false) String codeList, ModelAndView model, Integer id) throws IOException {
        model.addObject("codeList", (Object)codeList);
        model.addObject("accessionNo", (Object)accessionNo);
        List<Records> listBranch = this.recordsDao.filterByBranch();
        model.addObject("listBranch", listBranch);
        if (accessionNo == "" || accessionNo == "") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        if (codeList == "accessionNo") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        model.addObject("message", (Object)id);
        model.setViewName("/module/colorSpineLabel/byControlNum");
        return model;
    }

    @RequestMapping(value={"/byCallNum"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView byCallNum(@RequestParam(value="accessionNo", required=false) String accessionNo, @RequestParam(value="codeList", required=false) String codeList, ModelAndView model, Integer id) throws IOException {
        model.addObject("codeList", (Object)codeList);
        model.addObject("accessionNo", (Object)accessionNo);
        List<Records> listBranch = this.recordsDao.filterByBranch();
        model.addObject("listBranch", listBranch);
        if (accessionNo == "" || accessionNo == "") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        if (codeList == "accessionNo") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        model.addObject("message", (Object)id);
        model.setViewName("/module/colorSpineLabel/byCallNum");
        return model;
    }

    @RequestMapping(value={"/byAccNum"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView byAccNum(@RequestParam(value="accessionNo", required=false) String accessionNo, @RequestParam(value="codeList", required=false) String codeList, ModelAndView model, Integer id) throws IOException {
        model.addObject("codeList", (Object)codeList);
        model.addObject("accessionNo", (Object)accessionNo);
        List<Records> listBranch = this.recordsDao.filterByBranch();
        model.addObject("listBranch", listBranch);
        if (accessionNo == "" || accessionNo == "") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        if (codeList == "accessionNo") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        model.addObject("message", (Object)id);
        model.setViewName("/module/colorSpineLabel/byAccNum");
        return model;
    }

    @RequestMapping(value={"/byAccDateRange"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView byAccDateRange(@RequestParam(value="accessionNo", required=false) String accessionNo, @RequestParam(value="codeList", required=false) String codeList, ModelAndView model, Integer id) throws IOException {
        model.addObject("codeList", (Object)codeList);
        model.addObject("accessionNo", (Object)accessionNo);
        List<Records> listBranch = this.recordsDao.filterByBranch();
        model.addObject("listBranch", listBranch);
        if (accessionNo == "" || accessionNo == "") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        if (codeList == "accessionNo") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        model.addObject("message", (Object)id);
        model.setViewName("/module/colorSpineLabel/byAccDateRange");
        return model;
    }

    @RequestMapping(value={"/byAccRange"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView byAccRange(@RequestParam(value="accessionNo", required=false) String accessionNo, @RequestParam(value="codeList", required=false) String codeList, ModelAndView model, Integer id) throws IOException {
        model.addObject("codeList", (Object)codeList);
        model.addObject("accessionNo", (Object)accessionNo);
        List<Records> listBranch = this.recordsDao.filterByBranch();
        model.addObject("listBranch", listBranch);
        if (accessionNo == "" || accessionNo == "") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        if (codeList == "accessionNo") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        model.addObject("message", (Object)id);
        model.setViewName("/module/colorSpineLabel/byAccRange");
        return model;
    }

    @RequestMapping(value={"/byItemCategory"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView itemCategory(@RequestParam(value="accessionNo", required=false) String accessionNo, @RequestParam(value="codeList", required=false) String codeList, ModelAndView model, Integer id) throws IOException {
        model.addObject("codeList", (Object)codeList);
        model.addObject("accessionNo", (Object)accessionNo);
        List<Records> listItemCategory = this.recordsDao.pickListItemCategory();
        model.addObject("listItemCategory", listItemCategory);
        List<Records> listBranch = this.recordsDao.filterByBranch();
        model.addObject("listBranch", listBranch);
        model.addObject("message", (Object)id);
        if (accessionNo == "" || accessionNo == "") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        if (codeList == "itemCategory") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        model.addObject("message", (Object)id);
        model.setViewName("/module/colorSpineLabel/byItemCategory");
        return model;
    }

    @RequestMapping(value={"/bySMD"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView bySMD(@RequestParam(value="accessionNo", required=false) String accessionNo, @RequestParam(value="codeList", required=false) String codeList, ModelAndView model, Integer id) throws IOException {
        model.addObject("codeList", (Object)codeList);
        model.addObject("accessionNo", (Object)accessionNo);
        List<Records> listSMD = this.recordsDao.pickListSMD();
        model.addObject("listSMD", listSMD);
        List<Records> listBranch = this.recordsDao.filterByBranch();
        model.addObject("listBranch", listBranch);
        model.addObject("message", (Object)id);
        if (accessionNo == "" || accessionNo == "") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        if (codeList == "smd") {
            model.addObject("msg", (Object)"Invalid range");
        } else {
            model.addObject("msg", (Object)"Not Null");
        }
        model.addObject("message", (Object)id);
        model.setViewName("/module/colorSpineLabel/bySMD");
        return model;
    }

    @RequestMapping(value={"/searchResult"}, method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView searchResult(@RequestParam(value="inputVal", required=false) String inputVal, @RequestParam(value="inputVal2", required=false) String inputVal2, @RequestParam(value="codeList", required=true) String codeList, @RequestParam(value="selectBranch", required=false) String selectBranch, @RequestParam(value="selectItemCategory", required=false) String selectItemCategory, @RequestParam(value="selectSMD", required=false) String selectSMD, ModelAndView model) throws IOException {
        switch (codeList) {
            case "": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("inputVal", (Object)inputVal);
                model.addObject("selectBranch", (Object)selectBranch);
                model.addObject("msg", (Object)"Error, no keyword provided");
                break;
            }
            case "accessionNo": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("inputVal", (Object)inputVal);
                model.addObject("selectBranch", (Object)selectBranch);
                List<Records> listRecords = this.recordsDao.retrieveByAccessionNo(inputVal, selectBranch);
                for (Records records : listRecords) {
                }
                model.addObject("listRecords", listRecords);
                break;
            }
            case "accessionRange": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("inputVal", (Object)inputVal);
                model.addObject("inputVal2", (Object)inputVal2);
                model.addObject("selectBranch", (Object)selectBranch);
                List<Records> listRecords = this.recordsDao.retrieveByAccessionRange(inputVal, inputVal2, selectBranch);
                model.addObject("listRecords", listRecords);
                break;
            }
            case "callNo": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("inputVal", (Object)inputVal);
                model.addObject("selectBranch", (Object)selectBranch);
                List<Records> listRecords = this.recordsDao.retrieveByCallNo(inputVal, selectBranch);
                model.addObject("listRecords", listRecords);
                String json = new Gson().toJson(listRecords);
                model.addObject("json", (Object)json);
                break;
            }
            case "accessionDateRange": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("inputVal", (Object)inputVal);
                model.addObject("inputVal2", (Object)inputVal2);
                model.addObject("selectBranch", (Object)selectBranch);
                List<Records> listRecords = this.recordsDao.retrieveByAccessionDateRange(inputVal, inputVal2, selectBranch);
                model.addObject("listRecords", listRecords);
                break;
            }
            case "controlNo": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("inputVal", (Object)inputVal);
                model.addObject("selectBranch", (Object)selectBranch);
                List<Records> listRecords = this.recordsDao.retrieveByControlNo(inputVal, selectBranch);
                model.addObject("listRecords", listRecords);
                break;
            }
            case "title": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("inputVal", (Object)inputVal);
                model.addObject("selectBranch", (Object)selectBranch);
                List<Records> listRecords = this.recordsDao.retrieveByTitle(inputVal, selectBranch);
                model.addObject("listRecords", listRecords);
                break;
            }
            case "itemCategory": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("selectItemCategory", (Object)selectItemCategory);
                model.addObject("selectBranch", (Object)selectBranch);
                List<Records> listRecords = this.recordsDao.retrieveByItemCategory(selectItemCategory, selectBranch);
                model.addObject("listRecords", listRecords);
                break;
            }
            case "smd": {
                model.addObject("codeList", (Object)codeList);
                model.addObject("selectSMD", (Object)selectSMD);
                model.addObject("selectBranch", (Object)selectBranch);
                List<Records> listRecords = this.recordsDao.retrieveBySMD(selectSMD, selectBranch);
                model.addObject("listRecords", listRecords);
            }
        }
        model.setViewName("/module/colorSpineLabel/searchResult");
        return model;
    }
}
