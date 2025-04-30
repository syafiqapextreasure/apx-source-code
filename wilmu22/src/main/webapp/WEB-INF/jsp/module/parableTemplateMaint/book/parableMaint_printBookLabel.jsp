<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/gh/jbdemonte/barcode/jquery/jquery-barcode.js"></script>
		
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/JsBarcode.all.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/qrcode.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/parableTemplateMaint/validatePrintDetails.js"></script>
	
		<style>
    
 		#barcodeTarget, #canvasTarget {
			    margin-top: 20px;
			} 

		.book-label{width:5.00cm;height:2.80cm;top:-0.35cm;left:0.00cm}
		.page{width:<%= request.getAttribute("LABELLB01WIDTH") %>cm; height:<%= request.getAttribute("LABELLB01HEIGHT") %>cm;}

			.accessionDate_v {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("AccessionDateFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: <%=request.getAttribute("AccessionDateFORMAT")%>;
				LEFT: <%=request.getAttribute("AccessionDateLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 0;
				TOP: <%=request.getAttribute("AccessionDateTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("AccessionDateWIDTH")%>;
				Z-INDEX: 2;
				position: absolute;
			}
			
			.accessionDate_h {
				visibility: hidden;
			}
			
			.accessionDate_v:before {
				visibility: visible;
				content: '<%=request.getAttribute("AccessionDatePRINTOUT")%>'
			}
			
			.accessionNo_v {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("AccessionNoFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: <%=request.getAttribute("AccessionNoFORMAT")%>;
				LEFT: <%=request.getAttribute("AccessionNoLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 0;
				TOP: <%=request.getAttribute("AccessionNoTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("AccessionNoWIDTH")%>;
				Z-INDEX: 2;
				position: absolute;
			}
			
			.accessionNo_h {
				visibility: hidden;
			}
			
			.accessionNo_v:before {
				visibility: visible;
				content: '<%=request.getAttribute("AccessionNoPRINTOUT")%>'
			}
			
			.Barcode {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("BarcodeFONT_SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;;
				LEFT: <%=request.getAttribute("BarcodeLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("BarcodeTOP")%>;
				WIDTH: <%=request.getAttribute("BarcodeWIDTH")%>;
				HEIGHT: <%=request.getAttribute("BarcodeHEIGHT")%>;
	/* 			position: absolute; */
			}
			
			.Currency {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("CurrencyFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: !;
				LEFT: <%=request.getAttribute("CurrencyLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 0;
				TOP: <%=request.getAttribute("CurrencyTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("CurrencyWIDTH")%>;
				position: absolute;
			}
			
			.ItemCategory {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("ItemCategoryFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: Justify;
				HEIGHT: 0;
				LEFT: <%=request.getAttribute("ItemCategoryLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("ItemCategoryTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("ItemCategoryWIDTH")%>;
				position: absolute;
			}
			
			.LocalCost {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("LocalCostFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: Justify;
				HEIGHT: 1.5cm;
				LEFT: <%=request.getAttribute("LocalCostLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("LocalCostTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("LocalCostWIDTH")%>;
				position: absolute;
			}
			
			
			.OrigCallNo {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("OrigCallNoFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: Justify;
				HEIGHT: 4.0cm;
				LEFT: <%=request.getAttribute("OrigCallNoLEFT")%>;
				LINE-HEIGHT: 1.3;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("OrigCallNoTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("OrigCallNoWIDTH")%>;
				position: absolute;
			}
			
			.Title {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("TitleFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: <%=request.getAttribute("TitleFORMAT")%>;
				LEFT: <%=request.getAttribute("TitleLEFT")%>;
				LINE-HEIGHT: 1.1;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("TitleTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("TitleWIDTH")%>;
				position: absolute;
			}
			
			.Title_v:before {
				visibility: visible;
				content: '<%=request.getAttribute("TitleFORMAT")%>'
			}
			
			.Subject_v {
				FONT-DECORATION: 0;
				FONT-FAMILY: Calibri;
				FONT-SIZE: <%=request.getAttribute("SubjectFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: <%=request.getAttribute("SubjectFORMAT")%>;
				LEFT: <%=request.getAttribute("SubjectLEFT")%>;
				LINE-HEIGHT: 1.1;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("SubjectTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("SubjectWIDTH")%>;
				position: absolute;
			}
			
			.Subject_v:before {
				visibility: visible;
				content: '<%=request.getAttribute("SubjectFORMAT")%>'
			}
			
			.Acqorder {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("AcqorderFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: Justify;
				HEIGHT: 0.7cm;
				LEFT: <%=request.getAttribute("AcqorderLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("AcqorderTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("AcqorderWIDTH")%>;
				position: absolute;
			}
			
			.Acqvend {
				FONT-DECORATION: 0;
				FONT-FAMILY: Times New Roman;
				FONT-SIZE: <%=request.getAttribute("AcqvendFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: Justify;
				HEIGHT: 0.7cm;
				LEFT: <%=request.getAttribute("AcqvendLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("AcqvendTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("AcqvendWIDTH")%>;
				position: absolute;
			}
			
			.Barcode_h {
				visibility: hidden;
			}
			
			.Barcode_v {
				FONT-DECORATION: <%=request.getAttribute("BarcodeFONT-DECORATION")%>;
				FONT-FAMILY: <%=request.getAttribute("BarcodeFONT-FAMILY")%>;
				FONT-SIZE:  <%=request.getAttribute("BarcodeFONT-SIZE")%>;
				FONT-STYLE: <%=request.getAttribute("BarcodeFONT-STYLE")%>;
				FONT-WEIGHT: <%=request.getAttribute("BarcodeFONT-WEIGHT")%>;
				FORMAT: <%=request.getAttribute("BarcodeFORMAT")%>;
<%-- 				HEIGHT: <%=request.getAttribute("BarcodeHEIGHT")%>; --%>
				LEFT: <%=request.getAttribute("BarcodeLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("BarcodeTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("BarcodeWIDTH")%>;
			}
			
			.Organisation_v {
				FONT-DECORATION: 0;
				FONT-FAMILY: Calibri;
				FONT-SIZE: <%=request.getAttribute("OrganisationFONT-SIZE")%>;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: <%=request.getAttribute("OrganisationFORMAT")%>;
				HEIGHT: <%=request.getAttribute("OrganisationHEIGHT")%>;
				LEFT: <%=request.getAttribute("OrganisationLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("OrganisationTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("OrganisationWIDTH")%>;
				visibility: hidden;
				position: absolute;
			}
			
			.Organisation_v:before {
				visibility: visible;
				content: '<%=request.getAttribute("OrganisationFORMAT")%>'
			}
<%-- 			
			.Barcode_v {
				FONT-DECORATION: 0;
				FONT-FAMILY: Calibri;
				FONT-SIZE: 15pt;
				FONT-STYLE: 0;
				FONT-WEIGHT: 0;
				FORMAT: !;
				HEIGHT: <%=request.getAttribute("BarcodeHEIGHT")%>;
				LEFT: <%=request.getAttribute("BarcodeLEFT")%>;
				PRINTOUT: 0;
				TEXT-ALIGN: 1;
				TOP: <%=request.getAttribute("BarcodeTOP")%>;
				TYPE: Text;
				WIDTH: <%=request.getAttribute("BarcodeWIDTH")%>;
				position: absolute;
			}
	 --%>
	</style>
	<style>

/* 	.printMe{
		background-color:teal;
		color:white;
		border:1px solid white;
		border-radius:20px;
		height:35px;
	} */
	
	 body {
       background: rgb(204, 204, 204);
       color:black;
		-webkit-text-stroke: 0.Xpx;
	/* 	-webkit-text-stroke: 1px rgba(0,0,0,0.1); */
		-webkit-text-stroke-color: #34343b;
		-webkit-font-smoothing:antialiased;
	/* 	text-shadow: #fff 0px 1px 1px;
		-moz-osx-font-smoothing: grayscale; */
	}
	*{
       box-sizing: border-box;
       -moz-box-sizing: border-box;
	}
	
	.book-label{
		position:relative;
/* 	   border: 1px #D3D3D3 solid;
       border-radius: 2px; */
	}
			.page {
			/* margin-left:auto;
			margin-top:0.5cm; */
	
			margin-left:auto;
			margin-right:auto;
			 background: white;
   		/*  margin: auto;
          border: 1px #D3D3D3 solid;
       border-radius: 2px; */
      
    /*    box-shadow: 0 0 0.2cm rgba(0, 0, 0, 0.5); */
	}
	

  @media print {
   body {
       background: rgb(204, 204, 204);
       color:black;
		-webkit-text-stroke: 0.Xpx;
		/* -webkit-text-stroke: 1px rgba(0,0,0,0.1); */
		-webkit-text-stroke-color: #34343b;
		-webkit-font-smoothing:antialiased;
	/* 	text-shadow: #fff 0px 1px 1px;
		-moz-osx-font-smoothing: grayscale; */
	}
  .page {
        page-break-after: avoid;
        page-break-before: avoid;
     }
	  @page { margin: 0; }
	  
	  /*@page { size: auto;  margin-bottom:5mm; margin-top:5mm }*/
   	.print-bar{
   		display:none;
   		}
   		.page {
           width: inherit;
           min-height: inherit;
           background: initial;
           left:inherit;
           top:inherit;
       } 
       
       body {
    margin: 0;
    color: #000;
    background-color: #fff;
  }
   		/* .page:last-child {
     		page-break-after: avoid;
		} */
   		/* .book-label {
           left: initial;
          	top: initial;
       }
  */
       /* .page {
           width: initial;
           min-height: initial;
           background: initial;
           left:inherit;
           top:inherit;
       } 
        */
   	   a[href]:after { content: none !important; }
   	       
		
		.page{width:5.30cm;height:3.20cm;padding-top:0.10cm;padding-left:0.10cm}

		
		.book-label{width:5.00cm;height:3.20cm;top:-0.35cm;left:0.00cm}

		
		@page{width:5.30cm;height:3.10cm;padding-top:0.10cm;padding-left:0.10cm}
		
	
      
  	}
 
	</style>

		<script>			
/* 			function barcode(accessionNo){

				var idAccession= "#patron_"+ accessionNo;
				
				JsBarcode(idAccession, accessionNo,  {
					displayValue:false,
				    format: "code128",
				    width:1});
				$(idAccession)
				   .removeAttr("width").removeAttr("height").removeAttr("viewBox")
				   .css({ width: "150px", height: "150px", viewBox:"auto" });

			}
			 */
			
			function qrcode(accessionNo){
				var idAccession= "patron_"+ accessionNo;
				var qrcode = new QRCode(idAccession, {
				    text: accessionNo,
				    width:null,
				    height:null,
				    colorDark : "#000000",
				    colorLight : "#ffffff",
				    correctLevel : QRCode.CorrectLevel.H
				});
				
				qrcode.makeCode(accessionNo);
			
			}
			
			$(function(){
			 $('.MembershipDate').each(function(){
					var date = $(this).text().trim();
					var dateFormat = $(".MembershipDate").css( "CONTENT" );
					var fromDate = new Date(date);
                	date =moment(date).format(dateFormat);
               		$(this).text(date.split('"').join(''));
				});
				 
				 
				$('.ICNumber').each(function(){
						var ic = $(this).text().trim();
						var icFormat = $(".ICNumber").css( "CONTENT" );

						if(icFormat.split('"').join('')=="-"){
						//	ic =ic.match(new RegExp('(.{6})(.{2})', 'g')).join("-");
						//ic = ic.replace(/^(.{6})(.{2})(.{4})(.*)$/, "$1 $2 $3");
						ic = ic.replace(/(\d{6})(\d{2})(\d{4})/, "$1-$2-$3");
							//ic = ic.replace(ic, '\\d{6}\\-\\d{2}\\-\\d{4}')
						}
	               		$(this).text(ic);
					});
				var news = $('.Barcode').length;
				if(news !=0){
					
					$("svg").each(function(){
						var classVal = $(this).attr("key");
						barcode(classVal);
					});
				}else{
					$(".qrcode").each(function(){
						var  i = 0;
						var classVal = $(this).attr("key");
						qrcode(classVal);
					});
				}
			})
			$('.printMe').click(function(){
				
				var news = $('.Barcode').length;
				var accNo = [];
				if(news !=0){
					
					$("svg").each(function(){
						var classVal = $(this).attr("key");
						accNo.push(classVal.trim());
					});
				}else{
					$(".qrcode").each(function(){
						var classVal = $(this).attr("key");
						accNo.push(classVal.trim());
					});
				}
				   // window.print();
				
				 $.get("update_print.jsp",{patron:accNo.toString()},function(text){
					 if(text.trim()=="Done"){
						 window.print();
					 }
				  });
			});

	
		</script>
	
	
	</head>
	<body>
		<div class="print-bar">
			<div style="position:relative;width:100%;text-align:center;margin-top:5;">
				<div style="position:relative;width:100%;"><button class="printMe" id="printMeBtn">Print Patron</button></div>
			</div>
		</div>

		<div class="book">
		    <div class="page">		    
		    	<input type="hidden" value="" id="printStyle">
		        	 <table>                          
                         <tbody>
                         	<tr>                
                              <td class="book-label"> 
 				<%
					String accessionDateClass = "";
				 	if (request.getAttribute("AccessionDateLB01PRINT").equals("N")) {
						accessionDateClass += "accessionDate_h ";
					} else if (request.getAttribute("AccessionDateLB01PRINT").equals("Y"))
						accessionDateClass += " accessionDate_v";
				%>

				<div class="<%=accessionDateClass%>"></div>
				
				<%
					String accessionNoClass = "";
					if (request.getAttribute("AccessionNoLB01PRINT").equals("N")) {
						accessionNoClass += "accessionNo_h ";
					} else if (request.getAttribute("AccessionNoLB01PRINT").equals("Y"))
						accessionNoClass += " accessionNo_v";
				%>
		 		

				<div class="<%=accessionNoClass%>"></div>
				
								<%
					String origCallNoClass = "";
					if (request.getAttribute("OrigCallNoLB01PRINT").equals("N")) {
						origCallNoClass += "origCallNo_h ";
					} else if (request.getAttribute("OrigCallNoLB01PRINT").equals("Y"))
						origCallNoClass += " origCallNo_v";
				%>

				<div class="<%=origCallNoClass%>"></div> 
  
  				<%
					String boxClassStr2 = "";
					if (request.getAttribute("SubjectLB01PRINT").equals("N")) {
						boxClassStr2 += "Subject_h ";
					} else if (request.getAttribute("SubjectLB01PRINT").equals("Y"))
						boxClassStr2 += "Subject_v";
				%>

				<div class="<%=boxClassStr2%>"></div>

 				
				<%
					String barcodeClass = "";
					if (request.getAttribute("BarcodeLB01PRINT").equals("N")) {
						barcodeClass += "Barcode_h";
					} else if (request.getAttribute("BarcodeLB01PRINT").equals("Y"))
						barcodeClass += " Barcode_v";
				%>
				<div class="<%=barcodeClass%>" style="position: absolute;"></div>
  
		<%
					String organisationClass = "";
					if (request.getAttribute("OrganisationLB01PRINT").equals("N")) {
						organisationClass += "Organisation_h ";
					} else if (request.getAttribute("OrganisationLB01PRINT").equals("Y"))
						organisationClass += " Organisation_v";
				%>

				<div class="<%=organisationClass%>"></div>

                             </td>                                          
             	         </tr>           	         
                    </tbody>
                </table>
		    </div>
		</div>
	</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html> --%>

