<%@page import="java.util.*,javax.sql.*"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryCatalogDate"%>
<%-- <%@ page import="com.kmlink.ilmu.parable.parable_beta.DBConnect"%> --%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryAcession"%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryCallNumber"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.book_spine"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.Config_Print"%>

<%
	String number1 = null;
	String number2 = null;
	String catalog_date1 = null;
	String catalog_date2 = null;
	String call_number = null;
	List<book_spine> listData =new ArrayList<book_spine>();
	List<String>cssList = new ArrayList<String>();
	String[] selectedValue = null;
	
	String printStyle = (String)request.getParameter("printStyle");
	String customStyle = (String)request.getParameter("customStyle");
	String splitStyle = (String)request.getParameter("splitStyle");
	String styletype = (String)request.getParameter("styletype");
	int number = Integer.parseInt(request.getParameter("noData"));

	String position = request.getParameter("position");
	if(number!= 1){
		for(int i = 1; i <= number-1 ;i++){
			book_spine book_spinex = new book_spine();
			//book_spinex = "";
			listData.add(book_spinex);
		}
	}
	
/* 	if(request.getParameterValues("selectedValue[]")!= null && request.getParameterValues("selectedValue[]").length > 0){
		selectedValue = request.getParameterValues("selectedValue[]");
		for(int i = 0; i <= selectedValue.length - 1 ;i++){
			System.out.println(selectedValue[i]);
			QueryAcession queryAcession= new QueryAcession();
			book_spine book_spinex = new book_spine();
			book_spinex = queryAcession.loadAcession(selectedValue[i]);
			//boolean update = queryAcession.updateCT03SPINESTATUS(selectedValue[i]);
			System.out.println("SPineLbl" + new JSONObject(book_spinex).toString());
			listData.add(book_spinex);
		}
	}
	 */
	if(request.getParameterValues("selectedValue[]")!= null && request.getParameterValues("selectedValue[]").length >0){
		selectedValue = request.getParameterValues("selectedValue[]");
		System.out.println("ASWADHANDSOME: " + selectedValue.length);
		if(selectedValue.length == 1){
			QueryAcession queryAcession= new QueryAcession();
			book_spine book_spinex = new book_spine();
			book_spinex = queryAcession.loadAcession(selectedValue[0]);
			book_spinex = queryAcession.loadAcession(selectedValue[0].trim());
		
			listData.add(book_spinex);
		}else{
			for(int i = 0; i <= selectedValue.length - 1 ;i++){
				QueryAcession queryAcession= new QueryAcession();
				book_spine book_spinex = new book_spine();
				book_spinex = queryAcession.loadAcession(selectedValue[i]);
				System.out.println("book_spinex: " + book_spinex.toString());
				//boolean update = queryAcession.updateCT03SPINESTATUS(selectedValue[i]);
	//			book_spinex = queryAcession.loadAcession(selectedValue[i].trim());
				listData.add(book_spinex);
			}
		}
	}
	List <Config_Print> spineLbl = null;
/* 
	for(int i=0 ;i<=listData.size(); i++){
		listData
	} */
	
	spineLbl = Config_Print.LblMstr(splitStyle);
/* 	if(splitStyle.equalsIgnoreCase("SPINELCLBL")){
		spineLbl = Config_Print.LblMstr("SPINELCLBL");
	}else{
		spineLbl = Config_Print.LblMstr("SPINEDDCLBL");
	} */
	
	String lCols, lRows;
	lCols = lRows = null;
	
	String width = "width:";
	String height = "height:";
	String top = "margin-top:";
	String left = "margin-left:";	
	
	String sheetCss = ".page{";
	String lblCss = ".spine-label{";
	String dataCss = null;
	String field = null;
	String printSpine = "N";
	String dSeqno = null;
	List <Config_Print> patrProperty = null;
	
	for(Config_Print i : spineLbl){
		if((i.getLBL01FIELD()).equals("SHEET")){
			sheetCss += width;
			sheetCss += i.getLBL01WIDTH()+ "cm";
			sheetCss += ";";
			sheetCss += height;
			sheetCss += i.getLBL01HEIGHT()+ "cm";
			sheetCss += ";";
			sheetCss += "padding-top:";
			sheetCss += i.getLBL01TOP() + "cm";
			sheetCss += ";";
			sheetCss += "padding-left:";
			sheetCss += i.getLBL01LEFT() + "cm";
			sheetCss += "}";
		}else if((i.getLBL01FIELD()).equals("LABEL")){
			lblCss += width;
			lblCss += i.getLBL01WIDTH() + "cm";
			lblCss += ";";
			lblCss += height;
			lblCss += i.getLBL01HEIGHT() + "cm";
			lblCss += ";";
			lblCss += "padding-top:";
			lblCss += i.getLBL01TOP() + "cm";
			lblCss += ";";
			lblCss += "padding-left:";
			lblCss += i.getLBL01LEFT() + "cm";
			lblCss += "}";	
			lCols = i.getLBL01COLS();
			lRows = i.getLBL01ROWS();
		}else if((i.getLBL01MTYPE().equals("P")) && i.getLBL01PRINT().equals("Y")){

			field = i.getLBL01FIELD();
			dSeqno = i.getLBL01SEQNO();
			if(field.equals("Cutter1")){
				printSpine = "Y";
			}
			 patrProperty = Config_Print.LblProperty(splitStyle, dSeqno);
				
			 if(dataCss!=null){
					dataCss += "." + i.getLBL01FIELD() + "{";
				}else{
					dataCss = "." + i.getLBL01FIELD() + "{";
				}
				
				for(Config_Print property : patrProperty){
					if(dSeqno.equals(property.getLBL02SEQNO())){
			
						dataCss +=property.getLB02PROPERTY()+":";
						dataCss += property.getLBL02VALUE();
						dataCss += ";";			

					}
				}
		
				
				if(!i.getLBL01FIELD().equals("Location")){
					dataCss += "position:absolute;";
				}
				
				dataCss +="}";
		 }
		
	}
	
	cssList.add(sheetCss);
	cssList.add(lblCss);
	cssList.add(dataCss);
	
%>