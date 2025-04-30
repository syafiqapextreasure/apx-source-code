<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.json.JSONArray"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.*"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QuerySMD"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryPicklistItemCategory"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryPicklistSMD"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryPicklistBranch"%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryAcession"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.book_spine"%>
<%@ include file="/include/header.jsp" %>

<%
	List<item_category>itemCategory = null;
	QueryPicklistItemCategory queryPicklistItemCategory = new QueryPicklistItemCategory();
	itemCategory = queryPicklistItemCategory.loadPicklistItemCategory();
	
	List<smd>picklistSMD  = null;
	QueryPicklistSMD queryPicklistSMD = new QueryPicklistSMD();
	picklistSMD = queryPicklistSMD.loadPicklistSMD();
	
	List<branch>picklistBranch  = null;
	QueryPicklistBranch queryPicklistBranch = new QueryPicklistBranch();
	picklistBranch = queryPicklistBranch.loadPicklistBranch();
	
	QueryAcession queryAcession= new QueryAcession();
	book_spine book_spine = new book_spine();
	
	String json  = "{\"sheet\":{\"size\":\"9\",\"width\":\"2480\",\"height\":\"3508\",\"left\":\"100\",\"top\":\"20\"},\"label\":{\"width\":\"517\",\"height\":\"439\",\"rows\":\"9\",\"cols\":\"3\"},\"fields\":{\"field1\":\"Organization\",\"field2\":\"CallNo\",\"field3\":\"OrigCallNo\",\"field4\":\"Barcode\",\"field5\":\"QRcode\",\"field6\":\"AccessionNo\",\"field7\":\"CopyNo\",\"field8\":\"Volume\",\"field9\":\"ItemCategory\",\"field10\":\"SMD\",\"field11\":\"Branch\",\"field12\":\"Title\"},\"Organization\":{\"top\":\"0\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"CallNo\":{\"top\":\"10\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"OrigCallNo\":{\"top\":\"20\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"Barcode\":{\"top\":\"30\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"AccessionNo\":{\"top\":\"40\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"CopyNo\":{\"top\":\"50\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"Volume\":{\"top\":\"60\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"ItemCategory\":{\"top\":\"70\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"SMD\":{\"top\":\"80\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"Location\":{\"top\":\"90\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"Branch\":{\"top\":\"100\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"Author\":{\"top\":\"110\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"Title\":{\"top\":\"110\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"},\"QRcode\":{\"top\":\"110\",\"left\":\"30\",\"width\":\"517\",\"fontName\":\"Arial\",\"fontSize\":\"11\",\"fontBold\":\"1\",\"fontItalic\":\"0\",\"fontUnderline\":\"0\"}}";
	JSONObject style = new JSONObject(json);
	
	HashMap<String,String> result = new HashMap<String,String>();	
	result.put("top","top");
	result.put("left","left");
	result.put("width","width");
	result.put("fontName", "font-family");
	result.put("fontSize", "font-size");
	result.put("fontBold", "font-weight");
	result.put("fontItalic","font-style");
	result.put("fontUnderline","text-decoration");
	
	JSONObject fields = style.getJSONObject("fields");
	JSONObject sheet = style.getJSONObject("sheet");
	JSONObject label = style.getJSONObject("label");
	Iterator<String> keys = fields.keys();
	Iterator<String> others = sheet.keys();
	Iterator<String> labelz = label.keys();
	List<String>fieldsList = new ArrayList<String>();
	List<String>cssList = new ArrayList<String>();
	
	while( keys.hasNext()){
		 String key = (String)keys.next();
		    if ( !fields.get(key).toString().isEmpty()) {
		    	fieldsList.add(fields.getString(key));
		    }
	}
	String sheetcss = ".sheet {";
	while( others.hasNext()){
		 String key = (String)others.next();
		    if ( !sheet.get(key).toString().isEmpty()) {
		    	sheetcss  = sheetcss + key+":"+sheet.getString(key)+";";
		    }
	}
	sheetcss += "}";
	cssList.add(sheetcss);
	
	String labelcss = ".label {";
	while( labelz.hasNext()){
		 String key = (String)labelz.next();
		    if ( !label.get(key).toString().isEmpty()) {
		    	labelcss  = labelcss + key+":"+label.getString(key)+";";
		    }
	}
	sheetcss += "}";
	cssList.add(sheetcss);
	
	for(String classname : fieldsList){
		JSONObject test = style.getJSONObject(classname);
		Iterator<String> keyz = test.keys();
		String css = "."+classname+" {";
		while( keyz.hasNext()) {
			String key = (String)keyz.next();
			 css  = css + result.get(key)+":"+test.getString(key)+";";
		}
		css += "}";
		cssList.add(css);
		
	}

%>