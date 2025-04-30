<%@page import="com.kmlink.ilmu.parable.parable_beta.object.patron, com.kmlink.ilmu.parable.parable_beta.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="javax.swing.event.ListDataEvent"%>
<%@ page import="java.io.File, java.util.regex.Matcher, java.util.regex.Pattern" %>
<%@ include file="print_bookLabel_controller.jsp" %><html>



	<div class = "print-bar">
		<div style="position:relative;width:100%;text-align:center;margin-top:5;">
			<div style="position:relative;width:100%;"><button class = "printMe">Print Book Label</button></div>
		</div>
	</div>
<%-- 		
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
		-webkit-text-stroke: 0.5px rgba(0,0,0,0.1);
		-webkit-text-stroke-color: #34343b;
		-webkit-font-smoothing:antialiased;

	}
	{
       box-sizing: border-box;
       -moz-box-sizing: border-box;
	}
	
	.book-label{
		position:relative;
	}
			.page {
			margin-top:0.5cm;
			margin-left:auto;
			margin-right:auto;
			 background: white;

	}
	

  @media print {
   body {
      width: 210mm;
    height: 297mm;

    	   a[href]:after { content: none !important; }
   	       <% for(String cssStyle : cssList){%>
		
		<%=cssStyle%>
		
	<%}%>
      
  	}
 
	</style>
	 --%>
	
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
      /*  color:black; */
		-webkit-text-stroke: 0.Xpx;
		-webkit-text-stroke: 0.5px rgba(0,0,0,0.1);
		-webkit-text-stroke-color: #34343b;
		-webkit-font-smoothing:antialiased;
		/* text-shadow: #fff 0px 1px 1px;
		-moz-osx-font-smoothing: grayscale; */
	}
	*{
       box-sizing: border-box;
       -moz-box-sizing: border-box;
	}
	
	.book-label{
		position:relative;
		/*border: 1px solid black; */
	}
			.page {
			margin-top:0.5cm;
			margin-left:auto;
			margin-right:auto;
			 background: white;
	}

  @media print {
   body {
       background: rgb(204, 204, 204);
      /*  color:black; */
		-webkit-text-stroke: 0.Xpx;
		-webkit-text-stroke: 0.5px rgba(0,0,0,0.1);
		-webkit-text-stroke-color: #34343b;
		-webkit-font-smoothing:antialiased;
		/* text-shadow: #fff 0px 1px 1px;
		-moz-osx-font-smoothing: grayscale; */
	}
  .page {
        page-break-after: avoid;
        page-break-before: avoid;
     }
	  @page { margin: 0; }

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

   	   a[href]:after { content: none !important; }
   	       <% for(String cssStyle : cssList){%>
		
		<%=cssStyle%>
		
	<%}%>
      

  	}
 
	</style>

	 	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/kickstart.min.css"/>	
	 	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/JsBarcode.min.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment/moment.js"></script>	
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment/moment-with-locales.js"></script>		
		
		<script src="${pageContext.request.contextPath}/plugins/qrcode.min.js"></script>
		
		<script>	
		
		$(document).ready(function(){
			
			var input=<%=number%>;
			
		});
		
		function barcode(accessionNo){
			var idAccession= ".acession_"+ accessionNo.replace(/\s/g, '').replace("/", "\\/"); 
			  JsBarcode(idAccession, accessionNo, {
				    displayValue:false,
				    format: "<%=barcFormat%>",
				    width:1
				  });
			  $(idAccession)
			   .removeAttr("width").removeAttr("height").removeAttr("viewBox")
			    .css({ width: "<%=barcWidth%>", height: "<%=barcHeight%>", viewBox:"auto" }); 
		
		}

			function qrcode(accessionNo){
				var idAccession= "acession_"+ accessionNo;
				var qrcode = new QRCode(idAccession, {
				    text: accessionNo,
				    width:70,
				    height:40,
				    colorDark : "#000000",
				    colorLight : "#ffffff",
				    correctLevel : QRCode.CorrectLevel.H
				});
				
				qrcode.makeCode(accessionNo);
			
			}

			$(function(){
				$('.AccessionDate').each(function(){
					
					var dateFormat = $(".AccessionDate").css("CONTENT");
					var date = $(this).text();
					//alert("date"+date);
					////moment.locale('ms');
					//alert("dateFormat"+dateFormat);
					var fromDate = new Date(date);
					//alert("fromDate"+fromDate);
                	date =moment(fromDate).format("DD/MM/YYYY");
                	///date =moment(fromDate).format("MMM-YYYY");
                	//alert(date);
               		$(this).text(date.split('"').join(''));
				});
				var news = $('.Barcode').length;
				if(news !=0){
					$("svg").each(function(){
						var classVal = $(this).attr("key");
						barcode(classVal);
					});
				}else{
					$(".Qrcode").each(function(){
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
					$(".Qrcode").each(function(){
						var classVal = $(this).attr("key");
						accNo.push(classVal.trim());
					});
				}

				 $.get("update_print",{accession:accNo.toString()},function(text){
					 if(text.trim()=="Done"){
						 window.print();
					 }
				  });
			})
	
		</script>
		

	<body>
		
<div class="book">

			<%	
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
   				
   				Double totalPage = Math.ceil((double)listData.size()/totalPerPage);
   				totalPage = Math.ceil(totalPage); 
   			%>
   			<%
   				for( int j = 1; j <= totalPage.intValue() ; j++){
   			%>
			
		    <div class="page" style="page-break-after:always">
				<div class="subpage">		    
		    	<input type = "hidden" value ="<%=printStyle %>" id ="printStyle">   
		    	     		
                     <%
                        for (int row=newRow;row<totalRow;row++){

                      %>
                            <%
                            String patrValList = null;
                              for (int cols=0; cols < Integer.parseInt(lCols); cols++){
                            		if(startPoint > total){
                            			break;
	    							}
                            	book_spine accession = listData.get(startPoint);
                            	//System.out.println(listCol+"listCol");
                            	System.out.println(accession+"accession");
                            	System.out.println(startPoint+"startPoint");
                            	

                            	accDetails =  PatronDetails.loadAccession(listCol, accession.getAccessionNo());
                            	
                            	if(accDetails.get(3) != null){

                            		// Define the regular expression pattern
                                    String regex = "\\s+|(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D\\.)";

                                    // Split the string based on the pattern
                                    String[] parts = accDetails.get(3).split(regex);
                                    
                                    
                                    for (int x = 1; x <= parts.length; x++) {
                                    	System.out.println("startPointss"+parts[x-1]);
                                    	//accDetails.set(3, parts[0]);
                                    	//accDetails.set(4, parts[1]);
                                    	//accDetails.set(5, parts[2]);
                                    	//accDetails.set(6, parts[3]);
                                    	
                                    	if(x==1) {
                                    		accDetails.set(3, parts[0]);
                                    	}
                                    	if(x==2) {
                                    		accDetails.set(5, parts[1]);
                                    	}
                                    	if(x==3) {
                                    		accDetails.set(6, parts[2]);
            			                }
                                    	
                                    	if(x == 4 && parts[3].matches("\\d+")) {
                                    		accDetails.set(7, parts[3]);
                                    	}else {
                                    		if(i == 5 && parts[4].matches("\\d+")) {
                                    			accDetails.set(8, parts[4]);
            									
            			                	}else {
            			                
            			                		if(i==4) {
            			                			accDetails.set(6, parts[2]);
            					                }
            			                		
            					                if(i==5) {
            					                	accDetails.set(7, parts[3]);
            					                }
            					                if(i==6) {
            					                	accDetails.set(8, parts[4]);
            					                }
            			                	}
                                    	} 
                                    	/*
			                }
			         
			                
			                if(i == 4 && parts[3].matches("\\d+")) {
			                	jsonObject.put("Year", parts[i - 1]);
								LLC.add("<p class='Year'>" +parts[i - 1]+ "</p><br>");
			                }else {
			                	
			                	if(i == 5 && parts[4].matches("\\d+")) {
			                		jsonObject.put("Year", parts[i - 1]);
									LLC.add("<p class='Year'>" +parts[i - 1]+ "</p><br>");
									System.out.println("partlenY"+partlen);
			                	}else {
			                		System.out.println("partlen"+partlen);
			                		if(i==4) {
										jsonObject.put("Cutter2", parts[3]);
										LLC.add("<p class='Cutter2'>" +parts[3]+ "</p><br>");
					                }
			                		
					                if(i==5) {
										jsonObject.put("Cutter3", parts[4]);
										LLC.add("<p class='Cutter3'>" +parts[4]+ "</p><br>");
					                }
					                if(i==6) {
										jsonObject.put("Year", parts[5]);
										LLC.add("<p class='Year'>" +parts[5]+ "</p><br>");
					                }
			                	}*/
                                    }

                            	}
                            	
                            	//System.out.println(listCol+"listCollistCol");
                            	//System.out.println(accession.getAccessionNo()+"accession.getAccessionNo()");
                            	System.out.println(accDetails+"accDetailsaccDetailsaccDetails");
                            	System.out.println(accDetails.get(0)+"printSpine");
                   
    						 %>
                              <div class = "book-label" style="float:left">
                              <div class="Organisation">
								  
								</div>	
                               <% 
                               	for(int listlbl=0;listlbl<lblData.size()&& listlbl < accDetails.size();listlbl++){
                               	%>
                              	<%
									if(listlbl==0){
                             			int label=0;
                             			
		                        %>
<%-- 		                             	 <div class=<%=listlabel.get(label) %>>
		                             	 	<%=format.get(label) %>
		                              	 </div> --%>
		                              	  
		                       <%
									}
		                       %>  
                               <%
                               		if((lblData.get(listlbl).equals("Barcode"))){
                               %>
                             	<svg class="<%=lblData.get(listlbl) %> acession_<%=accession.getAccessionNo().replaceAll("\\s+", "").replaceAll("/", "\\/")%>" id ="acession_<%=accession.getAccessionNo().replaceAll("\\s+", "").replaceAll("/", "\\/")%>"  key="<%=accession.getAccessionNo()%>">line 317</svg>
                               <%
                               		}else if((lblData.get(listlbl).equals("Qrcode"))){System.out.println("Qr");
                               %>	
                             	<div class="<%=lblData.get(listlbl) %> " key="<%=accession.getAccessionNo().replaceAll("\\s+", "")%>" id ="acession_<%=accession.getAccessionNo().replaceAll("\\s+", "")%>" >line 322</div>

                               
                               <%
                               		}else{
                               %>	
                                 <div class="<%=lblData.get(listlbl)%>">
										
					                    <%=accDetails.get(listlbl) %>
					             </div>
                               	<%
                               		}
                               	%>
							  <%
                               	
                               
								}
							  %>
<%-- 								 <%
                  				//Print labels

                             	for(int label=0;label<listlabel.size()&& label < format.size();label++){

		                             	%>
		                             	
		                             	 <div class=<%=listlabel.get(label) %>>
		                             	 	<%=format.get(label) %>
		                              	 </div>
		                             	<%
                             	}
                             	%> --%>
 								<%
 									if(printSpine.equals("Y")){
 								%>
                             	<jsp:include page="print_Details_bookAndSpine.jsp">
 										 <jsp:param name="accession" value="<%=accession.getAccessionNo()%>"/>
								</jsp:include>
								<%
 									}
								%>
                             	  <%--  <%@ include file="print_Details_bookAndSpine.jsp" %> --%>
                             	   
                             </div>

                              <%
                              startPoint++;
                              } 
                               %>
                           
             	         <%
                        	}
                   //     }
             	         %>
				</div>
		    </div>
		    <%	
        		}
          	}

      		%>
		</div>
		


		
	</body>
	
</html>

