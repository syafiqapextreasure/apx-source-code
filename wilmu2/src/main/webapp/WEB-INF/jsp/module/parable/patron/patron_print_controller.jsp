<%@page contentType="text/html" pageEncoding="UTF-8" language="java" import="java.util.*,javax.sql.*"%>
<%@page import="org.json.JSONObject"%>
<%-- <%@ page import="com.kmlink.ilmu.parable.parable_beta.DBConnect"%> --%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.patron.QueryPatron"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.patron"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.Config_Print"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.PatronDetails"%>

<%
	String number1 = null;
	String number2 = null;
	String catalog_date1 = null;
	String catalog_date2 = null;
	String call_number = null;
	List<patron> listData =new ArrayList<patron>();
	List<String>listlabel = new ArrayList<String>();
	String[] selectedValue = null;
	String splitStyle = (String)request.getParameter("splitStyle");
	String printStyle = (String)request.getParameter("printStyle");
	int number = Integer.parseInt(request.getParameter("noData"));
	System.out.println("this is it >> "+ printStyle);
	
	if(number!= 1){
		for(int i = 1; i <= number-1 ;i++){
			patron queryPatron = new patron();
			//book_spinex = "";
			listData.add(queryPatron);
			//listlabel.add("");
		}
	}
	
	if(request.getParameterValues("selectedValue[]")!= null && request.getParameterValues("selectedValue[]").length > 0){
		selectedValue = request.getParameterValues("selectedValue[]");
		for(int i = 0; i <= selectedValue.length - 1 ;i++){
			System.out.println("ASWAD >> "+ selectedValue[i]);
			QueryPatron queryPatron = new QueryPatron();
			patron patronx = new patron();
			patronx = queryPatron.loadByPatron(selectedValue[i]);
			//boolean update = queryPatron.updatGL14BSTATUS(selectedValue[i]);
			System.out.println("XXXXX"+ new JSONObject(patronx).toString());
				listData.add(patronx);

		}
	}
	
	List <Config_Print> data = null;
	ArrayList<Object> colName = new ArrayList<Object>();
	ArrayList<Object> value = new ArrayList<Object>();
	ArrayList<Object> prop = new ArrayList<Object>();
	List<String>cssList = new ArrayList<String>();
	List<String>lblData = new ArrayList<String>();
	List<String>patronValues = new ArrayList<String>();
	List<String>listCol = new ArrayList<String>();
	
	List<String> format = new ArrayList<String>();
	List <Config_Print> patrlbl = null;
	
	List <Config_Print> spineLbl = null;
	
	System.out.println("sdsdLabel" + splitStyle);
	patrlbl = Config_Print.LblMstr(splitStyle.trim());
	/* if(splitStyle.equalsIgnoreCase("PATBLBL")){
		patrlbl = Config_Print.LblMstr("PATBLBL");
	}else{
		patrlbl = Config_Print.LblMstr("PATQRLBL");
	} */
	//patrlbl = Config_Print.LblMstr("PATBLBL");
	List <Config_Print> patrProperty = null;
	List <String> patrValue = null;
	

	
	
	String width = "width:";
	String height = "height:";
	String top = "top:";
	String left = "left:";	
	String sWidth, sHeight, sTop, sLeft, sRows, sCols, colN, 
	mType, dSeqno, print;
	
	sWidth= sHeight= sTop= sLeft= sRows= sCols = colN = 
	mType = dSeqno = print = null;
	
	String lCols, lRows, patronValue;
	lCols = lRows = patronValue = null;

	String qrWidth, qrHeight, barcWidth, barcHeight, barcFormat;
	qrWidth = qrHeight=barcWidth=barcHeight= barcFormat = null;
	
	String sheetCss = ".page{";
	String lblCss = ".book-label{";
	String prints = "@page{";
	String dataCss = null;
	for(Config_Print i : patrlbl){
		if((i.getLBL01FIELD()).equals("SHEET")){
			System.out.println("Sheet" +  i.getLBL01WIDTH());
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
		}else if((i.getLBL01FIELD()).equals("LABEL")){
			lblCss += width;
			lblCss += i.getLBL01WIDTH()+ "cm";
			lblCss += ";";
			lblCss += height;
			lblCss += i.getLBL01HEIGHT()+ "cm";
			lblCss += ";";
			lblCss += top;
			lblCss += i.getLBL01TOP()+ "cm";
			lblCss += ";";
			lblCss += left;
			lblCss += i.getLBL01LEFT()+ "cm";
			lblCss += "}";
			lCols = i.getLBL01COLS();
			lRows = i.getLBL01ROWS(); 
		}else if(i.getLBL01MTYPE().equals("D") && i.getLBL01PRINT().equals("Y")){
			mType = i.getLBL01MTYPE();
			print = i.getLBL01PRINT();
			dSeqno = i.getLBL01SEQNO();
			lblData.add(i.getLBL01FIELD());
			if((i.getLBL01COLN()!=null)){
			listCol.add(i.getLBL01COLN());
			System.out.println("QR");
			patrProperty = Config_Print.LblProperty(splitStyle, dSeqno);
			if(dataCss!=null){
				dataCss += "." + i.getLBL01FIELD() + "{";
			}else{
				dataCss = "." + i.getLBL01FIELD() + "{";
			}
			
			for(Config_Print property : patrProperty){
			
				if(dSeqno.equals(property.getLBL02SEQNO())){
					System.out.println("Rwwww" + i.getLBL01FIELD());
				if((i.getLBL01FIELD()).equals("Qrcode")){
					if((property.getLB02PROPERTY()).equals("WIDTH")){
						qrWidth = property.getLBL02VALUE();
						System.out.println("Rwwww" + qrWidth);
					}
					else if((property.getLB02PROPERTY()).equals("HEIGHT")){
						qrHeight = property.getLBL02VALUE();
					}else{
						dataCss +=property.getLB02PROPERTY()+":";
						dataCss += property.getLBL02VALUE();
						dataCss += ";";	
					}
				}
				
	/* 			dataCss +=property.getLB02PROPERTY()+":";
				dataCss += property.getLBL02VALUE();
				dataCss += ";";	
				 */
				else if((i.getLBL01FIELD()).equals("Barcode")){
					System.out.println("Rwwww22" +property.getLB02PROPERTY());
					if((property.getLB02PROPERTY()).equals("WIDTH")){
						barcWidth= property.getLBL02VALUE();
						System.out.println("barcWidth" + barcWidth);
					}else if((property.getLB02PROPERTY()).equals("HEIGHT")){
						barcHeight = property.getLBL02VALUE();
					}else if((property.getLB02PROPERTY()).equals("FORMAT")){
						barcFormat = property.getLBL02VALUE();
					}else{
						System.out.println("ereeer223332Barcode" + property.getLB02PROPERTY() +  property.getLBL02VALUE());
						dataCss +=property.getLB02PROPERTY()+":";
						dataCss += property.getLBL02VALUE();
						dataCss += ";";		
					}
				}else{
					System.out.println("ereeer223332Evrythg" + property.getLB02PROPERTY()+  property.getLBL02VALUE());
				dataCss +=property.getLB02PROPERTY()+":";
				dataCss += property.getLBL02VALUE();
				dataCss += ";";			
				}
				}
				
				
			/* 	pProperty = property.getLB02PROPERTY();
				pValue = property.getLBL02VALUE(); */
			}
			dataCss += "position:absolute;";
			dataCss +="}";
			
			/* if((i.getLBL01FIELD()).equals("Qrcode")){
				
				dataCss += "canvas{width:";
				dataCss += qrWidth + ";height:";
				dataCss += qrHeight + "}";
				
			} */
			}
		}else if(i.getLBL01MTYPE().equals("L")&& i.getLBL01PRINT().equals("Y")){
			System.out.println("LabelAdd");
			mType = i.getLBL01MTYPE();
			print = i.getLBL01PRINT();
			dSeqno = i.getLBL01SEQNO();
			listlabel.add(i.getLBL01FIELD());
			
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
					
				if((property.getLB02PROPERTY()).equals("FORMAT")){
					format.add(property.getLBL02VALUE());
				}
				/* 	pProperty = property.getLB02PROPERTY();
				pValue = property.getLBL02VALUE(); */
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
	cssList.add(lblCss);
	cssList.add(dataCss);
	cssList.add(prints);
	
/* 	if(request.getParameterValues("selectedValue[]")!= null && request.getParameterValues("selectedValue[]").length > 0){
		selectedValue = request.getParameterValues("selectedValue[]");
		for(int j = 0; j <= selectedValue.length - 1 ;j++){
			System.out.println(listCol + selectedValue[j]);
			patrValue =  PatronDetails.PatDetails(listCol, selectedValue[j]);
			for(PatronDetails patrValues : patrValue){
				patronValues.add(patrValues.getPatrValue());
			} 
		}
	}  */
	System.out.println("List" + listlabel);
			/* if(request.getParameterValues("selectedValue[]")!= null && request.getParameterValues("selectedValue[]").length > 0){
				selectedValue = request.getParameterValues("selectedValue[]");
				for(int j = 0; j <= selectedValue.length - 1 ;j++){
					patrValue =  PatronDetails.PatDetails(listCol.get(j), selectedValue[j]);
					for(PatronDetails patrValues : patrValue){
						System.out.println(patrValues.getPatrValue());
					} 
				}
			} */

%>