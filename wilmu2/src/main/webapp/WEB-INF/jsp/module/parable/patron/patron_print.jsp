<%@page import="com.kmlink.ilmu.parable.parable_beta.object.patron, com.kmlink.ilmu.parable.parable_beta.*"%>
<%@page import="javax.swing.event.ListDataEvent"%>
<%@ page import="java.io.File" %>
<%@ include file="patron_print_controller.jsp" %>

<html>
	<div class = "print-bar">
		<div style="position:relative;width:100%;text-align:center;margin-top:5;">
			<div style="position:relative;width:100%;"><button class = "printMe">Print Patron</button></div>
		</div>
	</div>
	<style>
    <% for(String cssStyle : cssList){%>
		
		<%=cssStyle%>
		
	<%}%>
	</style>
<style>

	.printMe{
		background-color:teal;
		color:white;
		border:1px solid white;
		border-radius:20px;
		height:35px;
	}
	 body {
       background: rgb(204, 204, 204);
		  -webkit-text-stroke: 0.Xpx;
		-webkit-text-stroke-color: #34343b;
		-webkit-font-smoothing:antialiased; 
	}
	*{
       box-sizing: border-box;
       -moz-box-sizing: border-box;
	}
	
	.book-label{
		position:relative;
	}
			.page {
			/* margin-left:auto;
			margin-top:0.5cm; */
			margin-top:0.5cm;
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
      /*  color:black; */
		 -webkit-text-stroke: 0.Xpx;
		/*-webkit-text-stroke: 0.5px rgba(0,0,0,0.1); */
		-webkit-text-stroke-color: #34343b; 
		-webkit-font-smoothing:antialiased; 
		/* text-shadow: #fff 0px 1px 1px;
		-moz-osx-font-smoothing: grayscale; */
	}
  .page {
        page-break-after: always;
        page-break-before: always;
        
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
           margin-top:0cm;
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
   	       <% for(String cssStyle : cssList){%>
		
		<%=cssStyle%>
		
	<%}%>
      
  	}
 
 
 
	</style>
					
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/JsBarcode.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/qrcode.min.js"></script>
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/kickstart.min.css"/>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/kickstart.min.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment/moment.js"></script>	
			<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment/moment-with-locales.js"></script>		
		<script>			
			function barcode(accessionNo){

				var idAccession= "#patron_"+ accessionNo;
				JsBarcode(idAccession, accessionNo,  {
					displayValue:false,
				    format: "CODE128",
				    width:1});
				$(idAccession)
				   .removeAttr("width").removeAttr("height").removeAttr("viewBox")
				   .css({ width: "<%=barcWidth%>", height: "<%=barcHeight%>", viewBox:"auto" });
			}
			<%-- function qrcode(patronID){
				var idAccession= "patron_"+ patronID;
				var qrcode = new QRCode(idAccession, {
				    text: patronID,
				    width:<%=qrWidth%>,
				    height:<%=qrHeight%>,
				    colorDark : "#000000",
				    colorLight : "#ffffff",
				    correctLevel : QRCode.CorrectLevel.H
				});
				
				qrcode.makeCode(patronID);
			
			} --%>
			
			function qrcode(accessionNo){
				var idAccession= "patron_"+ accessionNo;
				var qrcode = new QRCode(idAccession, {
				    text: accessionNo,
				    width:<%=qrWidth%>,
				    height:<%=qrHeight%>,
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
				
				 $.get("update_printPatron",{patron:accNo.toString()},function(text){
					 if(text.trim()=="Done"){
						 window.print();
					 }
				  });
			});
		</script>
	<body>
		
		<div class="book">
			<%	
			//Double totalPage = Math.ceil((double)listData.size()/8);
			System.out.println("BarcodeTT" + barcWidth);
        	if(listData.size()> 0){			
        		int i = 0;
				int total = 0;
				int currentTotal = 0;
				int count = 0;
				int startPoint = 0;
				int newRow  = 0;
				int totalRow = 0;
				int totalData = 0;
				int totalPerPage = Integer.parseInt(lCols)*Integer.parseInt(lRows);
				
				currentTotal = totalPerPage;
   				String position= "left";
   				total = listData.size()-1;
   				if(total < totalPerPage){
   					
   					currentTotal = listData.size()-1;
   				}
   				totalRow = Integer.parseInt(lRows);
   				totalData = listData.size();
   				System.out.println(totalRow);
   				System.out.println(totalData);
   				Double totalPage = Math.ceil((double)listData.size()/totalPerPage);
   				totalPage = Math.ceil(totalPage); 
   			%>
   			
   			<%
   				for( int j = 1; j <= totalPage.intValue() ; j++){
		   		%>
		    
		    <div class="page">		    
		    	<input type = "hidden" value ="<%=printStyle %>" id ="printStyle">
		        	 <table>      
                     <%
                    	
                        for (int row=newRow;row<totalRow;row++){
                      	
                      %>
                         <tr>

                            <%
                            	String patrValList = null;
                              for (int cols=0;cols<Integer.parseInt(lCols); cols++){
                            		if(startPoint > total){
                            			break;
	    							}
                            	  patron patron = listData.get(startPoint);
                            		patrValue =  PatronDetails.PatDetails(listCol, patron.getPatronId());
                     
    						 %>
                              <td class = "book-label"> 
                              	 <%
                  				//Print labels
                             	for(int listlbl=0;listlbl<listlabel.size();listlbl++){
                             	
                             	%>
                             	 <div class=<%=listlabel.get(listlbl) %>>
                              	 </div>
                             	<%
                             	}
                             	%>
                               <% 
                             	patrValue =  PatronDetails.PatDetails(listCol, patron.getPatronId());
                               
                               
                               	for(int lbldata=0;lbldata<lblData.size()&& lbldata < patrValue.size();lbldata++){
                               	
                               	%>
                               
                              	
                               	<%
                              
                               		if((lblData.get(lbldata).equals("Barcode"))){
                               %>
                             	<svg class="<%=lblData.get(lbldata) %>" id ="patron_<%=patron.getPatronId()%>" key="<%=patron.getPatronId()%>"></svg>
                               <%
                               		}else if((lblData.get(lbldata).equals("Qrcode"))){
                               %>	
                             	<div class="<%=lblData.get(lbldata) %> qrcode" key="<%=patron.getPatronId()%>" id ="patron_<%=patron.getPatronId()%>"></div>
                               <%
                               		}else{
                               %>	
                                 <div class=<%=lblData.get(lbldata) %>>
                               		<%=patrValue.get(lbldata) %>
                               	  </div>
                               	<%
                               		}
                               	%>
                              

								 <%
								
								
								%> 
							  <%
                               	
                               
								}
							  %>

                             </td>
                              <%
                              startPoint++;

                              } 
                               %>
                   
             	         </tr>
             	         <%
             	         
                        }
             	         %>
                    </table>

		    </div>
		 
		    <%	
		   		
          	}
        	}

      		%>
		</div>
	</body>
</html>