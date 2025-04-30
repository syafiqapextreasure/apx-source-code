<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/gh/jbdemonte/barcode/jquery/jquery-barcode.js"></script>
		
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/JsBarcode.all.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/qrcode.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/parableTemplateMaint/validatePrintDetails.js"></script>
	
		<style>
    
 		#barcodeTarget, #canvasTarget {
			    margin-top: 20px;
			} 
/* 		.page{width:5.30cm;height:3.10cm;padding-top:0.10cm;padding-left:0.10cm}*/

 		.book-label{width:5.00cm;height:2.80cm;top:-0.35cm;left:0.00cm}
		.page{width:<%= request.getAttribute("LABELLB01WIDTH") %>cm; height:<%= request.getAttribute("LABELLB01HEIGHT") %>cm;}

		.Subject_v{CONTENT:MMM-YYYY;FONT-DECORATION:0;FONT-FAMILY:TIMES NEW ROMAN;FONT-SIZE:<%= request.getAttribute("SubjectFONT-SIZE") %>;FONT-STYLE:0;FONT-WEIGHT:bold;FORMAT:KKP ahli;HEIGHT:<%= request.getAttribute("SubjectHEIGHT") %>;LEFT:<%= request.getAttribute("SubjectLEFT") %>;PRINTOUT:0;TEXT-ALIGN:1;TOP:<%= request.getAttribute("SubjectTOP") %>;TYPE:Text;WIDTH:<%= request.getAttribute("SubjectWIDTH") %>;visibility: hidden;position:absolute;}
		.Subject_v:before{visibility:visible;content:'<%= request.getAttribute("SubjectFORMAT")%>';} 
		.Subject_h:before{visibility:hidden;} 

		.ClassNo_v{CONTENT:MMM-YYYY;FONT-DECORATION:0;FONT-FAMILY:TIMES NEW ROMAN;FONT-SIZE:<%= request.getAttribute("ClassNoFONT-SIZE") %>;FONT-STYLE:0;FONT-WEIGHT:bold;FORMAT:KKP ahli;HEIGHT:<%= request.getAttribute("ClassNoHEIGHT") %>;LEFT:<%= request.getAttribute("ClassNoLEFT") %>;PRINTOUT:0;TEXT-ALIGN:1;TOP:<%= request.getAttribute("ClassNoTOP") %>;TYPE:Text;WIDTH:<%= request.getAttribute("ClassNoWIDTH") %>;visibility: hidden;position:absolute;}
		.ClassNo_v:before{visibility:visible;content:'<%= request.getAttribute("ClassNoFORMAT")%>';} 
		.ClassNo_h:before{visibility:hidden;} 

		.Cutter1_v{CONTENT:MMM-YYYY;FONT-DECORATION:0;FONT-FAMILY:TIMES NEW ROMAN;FONT-SIZE:<%= request.getAttribute("Cutter1FONT-SIZE") %>;FONT-STYLE:0;FONT-WEIGHT:bold;FORMAT:KKP ahli;HEIGHT:<%= request.getAttribute("Cutter1HEIGHT") %>;LEFT:<%= request.getAttribute("Cutter1LEFT") %>;PRINTOUT:0;TEXT-ALIGN:1;TOP:<%= request.getAttribute("Cutter1TOP") %>;TYPE:Text;WIDTH:<%= request.getAttribute("Cutter1WIDTH") %>;visibility: hidden;position:absolute;}
		.Cutter1_v:before{visibility:visible;content:'<%= request.getAttribute("Cutter1FORMAT")%>';} 
		.Cutter1_h:before{visibility:hidden;} 
		
		
		.Cutter2_v{CONTENT:MMM-YYYY;FONT-DECORATION:0;FONT-FAMILY:TIMES NEW ROMAN;FONT-SIZE:<%= request.getAttribute("Cutter2FONT-SIZE") %>;FONT-STYLE:0;FONT-WEIGHT:bold;FORMAT:KKP ahli;HEIGHT:<%= request.getAttribute("Cutter2HEIGHT") %>;LEFT:<%= request.getAttribute("Cutter2LEFT") %>;PRINTOUT:0;TEXT-ALIGN:1;TOP:<%= request.getAttribute("Cutter2TOP") %>;TYPE:Text;WIDTH:<%= request.getAttribute("Cutter2WIDTH") %>;visibility: hidden;position:absolute;}
		.Cutter2_v:before{visibility:visible;content:'<%= request.getAttribute("Cutter2FORMAT")%>';} 
		.Cutter2_h:before{visibility:hidden;} 
		
		.Cutter3_v{CONTENT:MMM-YYYY;FONT-DECORATION:0;FONT-FAMILY:TIMES NEW ROMAN;FONT-SIZE:<%= request.getAttribute("Cutter3FONT-SIZE") %>;FONT-STYLE:0;FONT-WEIGHT:bold;FORMAT:KKP ahli;HEIGHT:<%= request.getAttribute("Cutter3HEIGHT") %>;LEFT:<%= request.getAttribute("Cutter3LEFT") %>;PRINTOUT:0;TEXT-ALIGN:1;TOP:<%= request.getAttribute("Cutter3TOP") %>;TYPE:Text;WIDTH:<%= request.getAttribute("Cutter3WIDTH") %>;visibility: hidden;position:absolute;}
		.Cutter3_v:before{visibility:visible;content:'<%= request.getAttribute("Cutter3FORMAT")%>';} 
		.Cutter3_h:before{visibility:hidden;} 
	
		.Year_v{CONTENT:MMM-YYYY;FONT-DECORATION:0;FONT-FAMILY:TIMES NEW ROMAN;FONT-SIZE:<%= request.getAttribute("YearFONT-SIZE") %>;FONT-STYLE:0;FONT-WEIGHT:bold;FORMAT:KKP ahli;HEIGHT:<%= request.getAttribute("YearHEIGHT") %>;LEFT:<%= request.getAttribute("YearLEFT") %>;PRINTOUT:0;TEXT-ALIGN:1;TOP:<%= request.getAttribute("YearTOP") %>;TYPE:Text;WIDTH:<%= request.getAttribute("YearWIDTH") %>;visibility: hidden;position:absolute;}
		.Year_v:before{visibility:visible;content:'<%= request.getAttribute("YearFORMAT")%>';} 
		.Year_h:before{visibility:hidden;} 		 	 
			
	</style>
	<style>
/* 	.print-bar{
	    background-color:#000000;
	    opacity: 1;
	    filter: alpha(opacity=100); /* For IE8 and earlier */
	    height:45px;
	    width:100%;
	    margin-top:-38px;
	    position:fixed;
	    z-index:1000000;        
	    box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5);
	    transition: opacity .15s ease-in-out;
	  	-moz-transition: opacity .15s ease-in-out;
	    -webkit-transition: opacity .15s ease-in-out;
	} */
	.printMe{
		background-color:teal;
		color:white;
		border:1px solid white;
		border-radius:20px;
		height:35px;
	}
	
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
			function barcode(accessionNo){

				var idAccession= "#patron_"+ accessionNo;
				
				JsBarcode(idAccession, accessionNo,  {
					displayValue:false,
				    format: "code128",
				    width:1});
				$(idAccession)
				   .removeAttr("width").removeAttr("height").removeAttr("viewBox")
				   .css({ width: "150px", height: "50px", viewBox:"auto" });
<%-- 				.css({ width:<%= request.getAttribute("BarcodeWIDTH") %>, height:<%= request.getAttribute("BarcodeHEIGHT") %>, viewBox:"auto" }); --%>
			}
			
			
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
			}
			
    
	
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
                              
                            <% String boxClassStr1 = "";
								 if(request.getAttribute("SubjectLB01PRINT").equals("N")){
									 boxClassStr1 += "Subject_h ";
								}else if(request.getAttribute("SubjectLB01PRINT").equals("Y"))
									boxClassStr1 += " Subject_v";
							%>
								 
								<div class="<%=boxClassStr1%>" ></div> 
  
 							<% String boxClassStr2 = "";
								 if(request.getAttribute("ClassNoLB01PRINT").equals("N")){
									 boxClassStr2 += "ClassNo_h ";
								}else if(request.getAttribute("ClassNoLB01PRINT").equals("Y"))
									boxClassStr2 += " ClassNo_v";
							%>
								 
								<div class="<%=boxClassStr2%>" ></div>  
			 
				 				
                    			<% String boxClassStr3 = "";
								 if(request.getAttribute("Cutter1LB01PRINT").equals("N")){
									 boxClassStr3 += "Cutter1_h ";
								}else if(request.getAttribute("Cutter1LB01PRINT").equals("Y"))
									boxClassStr3 += " Cutter1_v";							
							%> 
  							
							 <div class="<%=boxClassStr3%>" ></div> 
							 
 							<% String boxClassStr4 = "";
								 if(request.getAttribute("Cutter2LB01PRINT").equals("N")){
									 boxClassStr4 += "Cutter2_h";
								}else if(request.getAttribute("Cutter2LB01PRINT").equals("Y"))
									boxClassStr4 += " Cutter2_v";							
							%> 

							<div class="<%=boxClassStr4%>"></div>  
						 
 							<% String boxClassStr5 = "";
								 if(request.getAttribute("Cutter3LB01PRINT").equals("N")){
									 boxClassStr5 += "Cutter3_h";
								}else if(request.getAttribute("Cutter3LB01PRINT").equals("Y"))
									boxClassStr5 += " Cutter3_v";							
							%> 

							<div class="<%=boxClassStr5%>"></div>  
							
							<% String boxClassStr6 = "";
								 if(request.getAttribute("YearLB01PRINT").equals("N")){
									 boxClassStr6 += "Year_h";
								}else if(request.getAttribute("YearLB01PRINT").equals("Y"))
									boxClassStr6 += " Year_v";							
							%> 

							<div class="<%=boxClassStr6%>"></div>  

                             </td>                                          
             	         </tr>           	         
                    </tbody>
                </table>
		    </div>
		</div>
</body>
</html>