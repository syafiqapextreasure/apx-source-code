<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.kmlink.ilmu.parable.parable_beta.GeneralUtil"%>
<%@page import="javax.swing.event.ListDataEvent"%>
<%@ page import="java.io.File" %>
<%@ include file="/WEB-INF/jsp/module/parable/spine/printSpine_controller.jsp" %>

<html>
	<div class = "print-bar">
		<div style="position:relative;width:100%;text-align:center;margin-top:5;">
			<div style="position:relative;width:100%;"><button class = "printMe">Print Spine Label</button></div>
		</div>
	</div>
<style>
    <% for(String cssStyle : cssList){%>
		
		<%=cssStyle%>
		
	<%}%>
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
	}
	*{
       box-sizing: border-box;
       -moz-box-sizing: border-box;
	}

	.spine-label{
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
	  @page { margin: 0; }
	  /*@page { size: auto;  margin-bottom:5mm; margin-top:5mm }*/
   	.print-bar{
   		display:none;
   		}
   		.page:last-child {
     		page-break-after: avoid;
		}
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
		<script src="${pageContext.request.contextPath}/js/JsBarcode.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/qrcode.min.js"></script>
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/kickstart.min.css"/>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/kickstart.min.js"></script>
	
	<script>			
		
	$('.printMe').click(function(){
		var accno = localStorage.getItem("accno");
		//var news = $('.Barcode').length;
		/* var accNo = [];
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
		 */
		   // window.print();
		  $.get("updateSpine_print",{accession:accno.toString()},function(text){	
			 if(text.trim()=="Done"){
						 window.print();
					 }
		  });
	});
		/* $(function(){
			 $.get("update_print.jsp",{accession:accNo.toString()},function(text){
				 if(text.trim()=="Done"){
					 window.print();
				 }
			  });
		}) */
	</script>
	<body>
	<div class="book">
			<%	
			
			
	
	        	if(listData.size()> 0){		
	        		int i = 0;
					int total = 0;
					int currentTotal = 0;
					int count = 0;
					int startPoint=0;
					int totalData=0;
	   				currentTotal = 0;
	   				int startPosition=0;
	   				total = listData.size() - 1;
	   				//startPosition=3;
	   				int positioning = 0;
	   				int totalPerPage = Integer.parseInt(lCols)*Integer.parseInt(lRows);

	   				currentTotal = totalPerPage;
	   				Double totalPage = Math.ceil((double)listData.size()/totalPerPage);

	   				totalPage = Math.ceil(totalPage); 
	   				totalData = listData.size();

	   				if(total < totalPerPage){
	   					currentTotal = listData.size()-1;
	   				}

	       			for( int j = 1; j <= totalPage.intValue() ; j++){
   			%>
		    <div class="page" style="page-break-after:always">		    
		    	<input type = "hidden" value ="<%=printStyle %>" id ="printStyle">
		        <%

		        for (int row=0;row<Integer.parseInt(lRows);row++){
		        %>
		        	<div class ="row">
		        	<% 

		        	  for (int cols=0;cols<Integer.parseInt(lCols); cols++){
		        		  if(startPoint > total){
								break;
							}
	        			book_spine book_spine = listData.get(startPoint);

		        		%>
			        	<div class = "spine-label">	
			        	
		        			<div class = "text-center" style=" line-height: 1.0;font-style=:Arial; margin-top: 5px;" align="center">
		        			<% 	
		        			
	        					GeneralUtil generalUtil = new GeneralUtil();
		        				List<String> call_no = new ArrayList<String>();
		        				List<String> acNno = new ArrayList<String>();
		        				String callno = book_spine.getCallNo().replace(" ." , ".");
		        				String brncNo = book_spine.getBranch();
		        				if(styletype.equalsIgnoreCase("L")){
									call_no = generalUtil.LLCSplitTestSpine(book_spine.getSpineItemCategory(),
																	book_spine.getSpineSMD(),
																	callno, 
																	book_spine.getVolume(),
																	book_spine.getCopyNo(), 
																	book_spine.getPartNo(),styletype, splitStyle);

		        				}
		        				else{
		        					call_no = generalUtil.DDCSplitTest(book_spine.getSpineItemCategory(),
											book_spine.getSpineSMD(),
											book_spine.getCallNo(), book_spine.getBranch(),styletype, splitStyle);
		        				}
								for(int f=0;f<call_no.size();f++){
									if(call_no.get(f).isEmpty()){
										continue;
									}else{
							%>
										<%=call_no.get(f).trim()%>
																					
							<%		}
								}
							%>
							</div>
		        		</div>
		        		<%
		        		  startPoint++;
	        			
		        		}
		        		%>
	        		</div>
	        	<%
		        }
	        	%>
		    </div>
		    <%
					//count = total - currentTotal;
				/* 	if(count >= 7 ){
						currentTotal+=8;
						startPoint= i;
					}else{
						currentTotal = currentTotal+count;
						startPoint=i;
					}		 */
				}	
	       	}else{
	       	}
      		%>
		</div>
	</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.kmlink.ilmu.parable.parable_beta.GeneralUtil"%>
<%@page import="javax.swing.event.ListDataEvent"%>
<%@ page import="java.io.File" %>
<%@ include file="/WEB-INF/jsp/module/parable/spine/printSpine_controller.jsp" %>

<html>
	<div class = "print-bar">
		<div style="position:relative;width:100%;text-align:center;margin-top:5;">
			<div style="position:relative;width:100%;"><button class = "printMe">Print Spine Label</button></div>
		</div>
	</div>
<style>
    <% for(String cssStyle : cssList){%>
		
		<%=cssStyle%>
		
	<%}%>
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
	}
	*{
       box-sizing: border-box;
       -moz-box-sizing: border-box;
	}

	.spine-label{
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
	  @page { margin: 0; }
	  /*@page { size: auto;  margin-bottom:5mm; margin-top:5mm }*/
   	.print-bar{
   		display:none;
   		}
   		.page:last-child {
     		page-break-after: avoid;
		}
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
   	   
   	    <% for(String cssStyle : cssList){>
		
		<%=cssStyle%>
		
	<%}%>
      
  	}
 
	</style>
			
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/JsBarcode.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/qrcode.min.js"></script>
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/kickstart.min.css"/>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/kickstart.min.js"></script>
	
	<script>			
		
	$('.printMe').click(function(){
		var accno = localStorage.getItem("accno");
		//var news = $('.Barcode').length;
		/* var accNo = [];
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
		 */
		   // window.print();
		  $.get("updateSpine_print",{accession:accno.toString()},function(text){	
			 if(text.trim()=="Done"){
						 window.print();
					 }
		  });
	});
		/* $(function(){
			 $.get("update_print.jsp",{accession:accNo.toString()},function(text){
				 if(text.trim()=="Done"){
					 window.print();
				 }
			  });
		}) */
	</script>
	<body>
	<div class="book">
			<%	
			
			
	
	        	if(listData.size()> 0){		
	        		int i = 0;
					int total = 0;
					int currentTotal = 0;
					int count = 0;
					int startPoint=0;
					int totalData=0;
	   				currentTotal = 0;
	   				int startPosition=0;
	   				total = listData.size() - 1;
	   				//startPosition=3;
	   				int positioning = 0;
	   				int totalPerPage = Integer.parseInt(lCols)*Integer.parseInt(lRows);

	   				currentTotal = totalPerPage;
	   				Double totalPage = Math.ceil((double)listData.size()/totalPerPage);

	   				totalPage = Math.ceil(totalPage); 
	   				totalData = listData.size();

	   				if(total < totalPerPage){
	   					currentTotal = listData.size()-1;
	   				}

	       			for( int j = 1; j <= totalPage.intValue() ; j++){
   			%>
   			
		    <div class="page" style="page-break-after:always">		    
		    	<input type = "hidden" value ="<%=printStyle %>" id ="printStyle">
		        <%

		        for (int row=0;row<Integer.parseInt(lRows);row++){
		        %>
		        	<div class ="row"> <%=row %>
		        	<% 

		        	  for (int cols=0;cols<Integer.parseInt(lCols); cols++){
		        		  if(startPoint > total){
								break;
							}
	        			book_spine book_spine = listData.get(startPoint);

		        		%>
			        	<div class = "spine-label">	
			        	
		        			<div class = "text-center" style=" line-height: 1.0;font-style=:Arial; margin-top: 5px;" align="center">
		        			<% 	
		        			
	        					GeneralUtil generalUtil = new GeneralUtil();
		        				List<String> call_no = new ArrayList<String>();
		        				List<String> acNno = new ArrayList<String>();
		        				String callno = book_spine.getCallNo().replace(" ." , ".");
		        				String brncNo = book_spine.getBranch();
		        				if(styletype.equalsIgnoreCase("L")){
									call_no = generalUtil.LLCSplitTestSpine(book_spine.getSpineItemCategory(),
																	book_spine.getSpineSMD(),
																	callno, 
																	book_spine.getVolume(),
																	book_spine.getCopyNo(), 
																	book_spine.getPartNo(),styletype, splitStyle);

		        				}
		        				else{
		        					call_no = generalUtil.DDCSplitTest(book_spine.getSpineItemCategory(),
											book_spine.getSpineSMD(),
											book_spine.getCallNo(), book_spine.getBranch(),styletype, splitStyle);
		        				}
								for(int f=0;f<call_no.size();f++){
									if(call_no.get(f).isEmpty()){
										continue;
									}else{
							%>
										<%=call_no.get(f).trim()%>
																					
							<%		}
								}
							%>
							</div>
		        		</div>
		        		<%
		        		  startPoint++;
	        			
		        		}
		        		%>
	        		</div>
	        	<%
		        }
	        	%>
		    </div>
		    <%
					//count = total - currentTotal;
				/* 	if(count >= 7 ){
						currentTotal+=8;
						startPoint= i;
					}else{
						currentTotal = currentTotal+count;
						startPoint=i;
					}		 */
				}	
	       	}else{
	       	}
      		%>
		</div>
	</body>
</html>
 --%>