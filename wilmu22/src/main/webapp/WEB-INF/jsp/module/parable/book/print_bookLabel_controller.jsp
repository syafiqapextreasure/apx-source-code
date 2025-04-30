<%-- <%@page import="org.junit.internal.runners.model.EachTestNotifier"%> --%>
<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@page import="java.util.*,javax.sql.*"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.QueryCatalogDate"%>
<%-- <%@ page import="com.kmlink.ilmu.parable.parable_beta.DBConnect"%> --%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryAcession"%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryCallNumber"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.book_spine"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.Config_Print"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.PatronDetails"%>

<%
	String number1 = null;
	String number2 = null;
	String catalog_date1 = null;
	String catalog_date2 = null;
	String call_number = null;
	List<book_spine> listData =new ArrayList<book_spine>();
	List <String> accDetails = null;
	String[] selectedValue = null;
	
	String tplName = request.getParameter("tplName");
	String printStyle = (String)request.getParameter("printStyle");
	String splitStyle = (String)request.getParameter("splitStyle");
	String styletype = (String)request.getParameter("styletype");
	String customStyle = (String)request.getParameter("customStyle");
	int number = Integer.parseInt(request.getParameter("noData"));
	
	System.out.println("splitStyle" + splitStyle + "styletype" + styletype);
	if(number!= 1){
		for(int i = 1; i <= number-1 ;i++){
			book_spine book_spinex = new book_spine();
			//book_spinex = "";
			listData.add(book_spinex);
		}
	}
	
	if(request.getParameterValues("selectedValue[]")!= null && request.getParameterValues("selectedValue[]").length >0){
		selectedValue = request.getParameterValues("selectedValue[]");
		
		if(selectedValue.length == 1){
			QueryAcession queryAcession= new QueryAcession();
			book_spine book_spinex = new book_spine();

			book_spinex = queryAcession.loadAcession(selectedValue[0]); 
			
			listData.add(book_spinex);
		}else{
			for(int i = 0; i <= selectedValue.length - 1 ;i++){
				book_spine book_spinex = new book_spine();
				QueryAcession queryAcession= new QueryAcession();
		//		book_spinex = queryAcession.loadAcession(selectedValue[i].trim());
				book_spinex = queryAcession.loadAcession(selectedValue[i]);
				//boolean update = queryAcession.updateCT03BLESTATUS(selectedValue[i]);
				listData.add(book_spinex);
			}
		}
	}
	 String json = (String)request.getParameter("customStyle");
	 
	HashMap<String,String> result = new HashMap<String,String>();	
	result.put("top","top");
	result.put("left","left");
	result.put("width","width");
	result.put("fontName", "font-family");
	result.put("fontSize", "font-size");
	result.put("fontBold", "font-weight");
	result.put("fontItalic","font-style");
	result.put("fontUnderline","text-decoration");
	
	
	List<String>fieldsList = new ArrayList<String>();
	List<String>cssList = new ArrayList<String>();
	List<String>lblData = new ArrayList<String>();
	List<String>listCol = new ArrayList<String>();
	List<String>listTbl = new ArrayList<String>();
	List <Config_Print> patrProperty = null;
	List<String>listlabel = new ArrayList<String>();
	List<String>listspine = new ArrayList<String>();
	List<String> format = new ArrayList<String>();
	List<String> spinelbl = new ArrayList<String>();

	List <Config_Print> spineLbl = null;
	spineLbl = Config_Print.LblMstr(splitStyle);
	String lCols, lRows;
	lCols = lRows = null;
	
	String width = "width:";
	String height = "height:";
	String top = "top:";
	String left = "left:";	
	
	String sWidth, sHeight, sTop, sLeft, sRows, sCols, colN, 
	mType, dSeqno, print;
	
	sWidth= sHeight= sTop= sLeft= sRows= sCols = colN = 
	mType = dSeqno = print = null;
	
	String qrWidth, qrHeight, barcWidth, barcHeight, barcFormat;
	qrWidth = qrHeight = barcWidth= barcHeight= barcFormat= null;
	
	System.out.println(barcFormat );
	String sheetCss = ".page{";
	String prints = "@page{";
	String spineCss = ".book-label{";
	String dataCss = null;
	String field = null;
	String printSpine = "N";
	
	for(Config_Print i : spineLbl){
		
		if((i.getLBL01FIELD()).equals("SHEET")){
			sheetCss += width;
			sheetCss += i.getLBL01WIDTH() + "cm";
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
			System.out.println(sheetCss);
			
			prints += width;
			prints += i.getLBL01WIDTH() + "cm";
			prints += ";";
			prints += height;
			prints += i.getLBL01HEIGHT()+ "cm";
			prints += ";";
			prints += "padding-top:";
			prints += i.getLBL01TOP() + "cm";
			prints += ";";
			prints += "padding-left:";
			prints += i.getLBL01LEFT() + "cm";
			prints += "}"; 
			System.out.println(prints);
			
//		}else if((i.getLBL01MTYPE().equals("D")||i.getLBL01MTYPE().equals("P")) && i.getLBL01PRINT().equals("Y")){
		}else if((i.getLBL01MTYPE().equals("D")|| i.getLBL01MTYPE().equals("L")||i.getLBL01MTYPE().equals("P")) && i.getLBL01PRINT().equals("Y")){			 
			 mType = i.getLBL01MTYPE();
			print = i.getLBL01PRINT();
			dSeqno = i.getLBL01SEQNO();
			field = i.getLBL01FIELD();
			
			if(field.equals("Cutter1")){
				printSpine = "Y";
			}
			if((i.getLBL01COLN()!=null) && i.getLBL01MTYPE().equals("D")){
				 String column = i.getLBL01TBLN() + "." + i.getLBL01COLN() + " as " + i.getLBL01TBLN()+"_" + i.getLBL01COLN() ;
				if((i.getLBL01TBLN()!=null)){
					 listCol.add(column);
					lblData.add(field);
				}

			}
	
			 patrProperty = Config_Print.LblProperty(splitStyle, dSeqno);
			 if(dataCss!=null){
					dataCss += "." + i.getLBL01FIELD() + "{";
				}else{
					dataCss = "." + i.getLBL01FIELD() + "{";
				}
				
				for(Config_Print property : patrProperty){
					if(dSeqno.equals(property.getLBL02SEQNO())){
					if((i.getLBL01FIELD()).equals("Qrcode")){
						if((property.getLB02PROPERTY()).equals("WIDTH")){
							qrWidth = property.getLBL02VALUE();
						}
						else if((property.getLB02PROPERTY()).equals("HEIGHT")){
							qrHeight = property.getLBL02VALUE();
						}else{
							dataCss +=property.getLB02PROPERTY()+":";
							dataCss += property.getLBL02VALUE();
							dataCss += ";";	
						}
					}else if((i.getLBL01FIELD()).equals("Barcode")){
						
						if((property.getLB02PROPERTY()).equals("WIDTH")){
							barcWidth= property.getLBL02VALUE();
							
						}else if((property.getLB02PROPERTY()).equals("HEIGHT")){
							
							barcHeight = property.getLBL02VALUE();
							
						}else if((property.getLB02PROPERTY()).equals("FORMAT")){
							barcFormat = property.getLBL02VALUE();
						}else{
							dataCss +=property.getLB02PROPERTY()+":";
							dataCss += property.getLBL02VALUE();
							dataCss += ";";		
						}
					}else{
					dataCss +=property.getLB02PROPERTY()+":";
					dataCss += property.getLBL02VALUE();
					dataCss += ";";			
					}
					}
				}
				dataCss += "position:absolute;";
				dataCss +="}";
		 }else if((i.getLBL01FIELD()).equals("LABEL")){
				spineCss += width;
				spineCss += i.getLBL01WIDTH() + "cm";
				spineCss += ";";
				spineCss += height;
				spineCss += i.getLBL01HEIGHT() + "cm";
				spineCss += ";";
				spineCss += "padding-top:";
				spineCss += i.getLBL01TOP() + "cm";
				spineCss += ";";
				spineCss += "padding-left:";
				spineCss += i.getLBL01LEFT() + "cm";
				spineCss += "}";	lCols = i.getLBL01COLS();
				lRows = i.getLBL01ROWS();
		 }else if(i.getLBL01MTYPE().equals("L") && i.getLBL01PRINT().equals("Y")){
			 
			 mType = i.getLBL01MTYPE();
			 print = i.getLBL01PRINT();
			 dSeqno = i.getLBL01SEQNO();
			 field = i.getLBL01FIELD();
			 listlabel.add(field);
			 
			 if(dataCss!=null){
					dataCss += "." + i.getLBL01FIELD() + "{";
				}else{
					dataCss = "." + i.getLBL01FIELD() + "{";
				}
				
				patrProperty = Config_Print.LblProperty(splitStyle, dSeqno);
				for(Config_Print property : patrProperty){
					if(dSeqno.equals(property.getLBL02SEQNO())){
					dataCss +=property.getLB02PROPERTY()+":";
					dataCss += property.getLBL02VALUE();
					dataCss += ";";			
					}
					
					if((property.getLB02PROPERTY()).equals("FORMAT")){
						format.add(property.getLBL02VALUE());
					}
				}
				
				dataCss += "visibility: hidden;"; 
				dataCss += "position:absolute;";
				dataCss +="}";
				
				for(String formats : format){
					dataCss += "." + i.getLBL01FIELD() + ":before{";
					dataCss += " visibility: visible;content:'"+formats+"'}";
				}

		 }
		
	}

	cssList.add(sheetCss);
	cssList.add(spineCss);
	cssList.add(dataCss);
	cssList.add(prints);
%>