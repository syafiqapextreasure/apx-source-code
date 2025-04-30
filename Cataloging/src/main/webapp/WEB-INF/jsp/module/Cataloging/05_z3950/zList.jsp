<%--
  - zList.jsp
  -
  - ----------------------------------------------------------------------------
  - Version Date      By    Description
  - ----------------------------------------------------------------------------
  -  1.1    20050118  BKL   Releasing the first version.
  -  1.2    20050327  BKL   Modified to search multiple servers.
  - ----------------------------------------------------------------------------
  -
  - Paradigm Systems Berhad
 --%>
<%@ page language="java" %>
<%@ page import="com.elmu.z3950.util.*" %>
<%@ page import="com.elmu.z3950.*" %>
<%@ page import="com.paradigm.util.*" %>
<%@ page import="com.paradigm.boca.bl.*" %>
<%@ page import="com.paradigm.boca.ui.*" %>
<%@ page import="com.ilmu.cataloguing.bm.*" %>
<%@ page import="com.ilmu.cataloguing.util.*" %>
<%@ page import="java.util.*" %>
       
<%
    String szAction = request.getParameter("ACTION");
    String szRequestPage = request.getParameter("page") == null ? "" : request.getParameter("page");
    TagDisplayHandler tagDisplayHandler = TagDisplayHandler.getInstance("elmu-z3950");
    final String[][] aszTagDisplay = tagDisplayHandler.getBriefDisplayColumns();
    ZClientConfigHandler handler = ZClientConfigHandler.getInstance();

%>
<script language="JavaScript">

function downloadNow(x, y){
	/* document.getElementById("hdSelectedMARC").value = x;
	document.getElementById("hdSelectedServer").value = y;
	var hdSelectedMARC = $("#hdSelectedMARC").val();
	var hdSelectedServer = $("#hdSelectedServer").val();*/
	var page =null; 

	  $.get("zView",{hdSelectedMARC:x,page:page,hdSelectedServer:y},function(data){
		 $("#tmp").html(data);
			download(x,y);
		});

}

function viewz3950() {
	 /*  var servers = [];
	  var page = $("#page").val();
	  var txtTerm = $("#txtTerm").val();
	  var slcUseAttribute = $( "#slcUseAttribute option:selected" ).val();

	  $("input[name='slcServer']:checked").each( function () {
		  servers.push($(this).val());
	   }); */
	   

	  $.get("zView",function(data){
			//alert(data);
			
		  $(".contents").html(data);
	  });

	}
 function back(){
	$(".contents").html(localStorage.getItem("lastData"));
 }
function selectMARCRecord(x, y) {
	
	document.getElementById("hdSelectedMARC").value = x;
	document.getElementById("hdSelectedServer").value = y;

	var hdSelectedMARC = $("#hdSelectedMARC").val();
	var hdSelectedServer = $("#hdSelectedServer").val();
	var page = $("#page").val();

	  $.get("zView",{hdSelectedMARC:hdSelectedMARC,page:page,hdSelectedServer:hdSelectedServer},function(data){
			$(".contents").html(data);
			$("#modalfooter_Z390").html('<button class="btn btn-info" type="button" data-dismiss="modal" onclick="download('+hdSelectedMARC+','+hdSelectedServer+');" title="Download Marc"><span  class="glyphicon glyphicon-download-alt"></span></button>'+
										'<button class="btn btn-info" type="button" onclick="back();"><span class="glyphicon glyphicon-arrow-left"></span></button>')
		});

/* 	var form = document.frmZList;
    form.hdSelectedMARC.value = x;
    form.hdSelectedServer.value = y;
    form.submit();  */
}

function retrieveRecords(iCount, y, server) {
	alert("sdsdsd");
    var form = document.frmZList;
    var elmFrom;
    var elmTo;
    form.hdSelectedServer.value = y;
    form.slcServer.value = server;
    if (form.txtZClientFrom.length > 1) {
        elmFrom = form.txtZClientFrom[y];
        elmTo = form.txtZClientTo[y];
    } else {
        elmFrom = form.txtZClientFrom;
        elmTo = form.txtZClientTo;
    }
    if (elmFrom.value == "") {
        alert("Please key in the starting index.");
        elmFrom.select();
        return;
    }
    if (elmTo.value == "") {
        alert("Please key in the ending index.");
        elmTo.select();
        return;
    }
    if (! integerValid(elmFrom)) {
        alert("The starting index is not a valid number.");
        elmFrom.select();
        return;
    }
    if (! integerValid(elmTo)) {
        alert("The ending index is not a valid number.");
        elmTo.select();
        return;
    }
    var iFrom = parseInt(elmFrom.value);
    var iTo = parseInt(elmTo.value);
    if (iFrom > iTo) {
        alert("The starting index should not larger than the ending index.");
        elmFrom.select();
        return;
    }
    if (iFrom <= 0) {
        alert("The starting index should not less than 1.");
        elmFrom.select();
        return;
    }
    if (iFrom > iCount) {
        alert("The starting index should not larger than the total records found.");
        elmFrom.select();
        return;
    }
    if (iTo > iCount) {
        alert("The ending index should not larger than the total records found.");
        elmTo.select();
        return;
    }
    var count = iTo - iFrom + 1;
    if (count > 20) {
        if (! confirm("Are you sure you want to retrieve " + count + " records in a single batch? The system may need some time to process your request.")) {
            elmTo.select();
            return;
        }
    }
    form.hdZClientFrom.value = elmFrom.value;
    form.hdZClientTo.value = elmTo.value;
  /*   $.get("processingDelay",{slcServer:servers,page:page,txtTerm:txtTerm,
			slcUseAttribute: slcUseAttribute},function(data){
			localStorage.setItem("lastData", data);				
			$(".contents").html(data);

		}); */
    //submitForm2(form, 'zSearch');
		var servers = $("#slcServer").val();
		var page = $("#page").val();
    $.get("processingDelay",{slcServer:servers,page:page,hdZClientTo:iTo, hdZClientFrom:iFrom},function(data){
			localStorage.setItem("lastData", data);				
			$(".contents").html(data);
		});
}

</script>

<form name="frmZList" method="" action="">
<input type="hidden" name="page" id="page" value="zList.jsp" />
<input type="hidden" name="hdSelectedMARC" id="hdSelectedMARC"/>
<input type="hidden" name="hdSelectedServer" id="hdSelectedServer"/>
<input type="hidden" name="slcServer" id="slcServer" />
<input type="hidden" name="hdZClientFrom" id="hdZClientFrom"/>
<input type="hidden" name="hdZClientTo"  id="hdZClientTo"/>
 <div class="container" style="width:100%">
    <div class="row">
            <div class="panel-group" id="accordion">
<%

    Vector vZClientThreads = (Vector) session.getAttribute("zclientthreads");
    if (vZClientThreads != null) {
        for (int h = 0; h < vZClientThreads.size(); h++) {
            ZClientThread zclientThread = (ZClientThread) vZClientThreads.get(h);
            System.out.println("ZSSSS" + vZClientThreads.get(h));
            if (zclientThread.getStatus() == ZClientThread.RECORDS_FOUND) {
            	   System.out.println("ZSSSSqq");
                int intCount = zclientThread.getZClient().getRecordCount();
              
                String[] aszMarcRecords = zclientThread.getMarcRecords();
                System.out.println("ZSSSSqq1" + intCount + aszMarcRecords.toString());
                int intZClientFrom = zclientThread.getFrom();
                int intZClientTo = zclientThread.getTo();
                System.out.println("ZSSSSqq2" + zclientThread.getZ3950Server().getHost());
                System.out.println("ZSSSSqq3" + zclientThread.getZ3950Server().getId());
    
               if (intCount < intZClientTo) {
                    intZClientTo = intCount;
               }
                    System.out.println("ZSSSSqq4" + intCount + intZClientTo);
%>
 <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne<%=h%>"><strong><%= zclientThread.getZ3950Server().getDescription() %></strong></a>
     </h4>
    </div>
   <div id="collapseOne<%=h%>" class="panel-collapse collapse">
   	<div class="panel-body">
      <table border="0" cellpadding="3" cellspacing="2" width="100%">
		  <tr>
		    <td align="left" class="text-label">&nbsp;</td>
		    <td align="right" class="text-label">
			<div class="input-group" style="width:30%">
		    <input type="text" class="form-control" name="txtZClientFrom" size="3" value="<%= intZClientFrom %>"/>
		    <span class="input-group-addon">to</span>
		    <input type="text" class="form-control" name="txtZClientTo" size="3" value="<%= intZClientTo  %>"/>
			</div>
			</td>
		    <td style="width:25%"> of <%= intCount %> results <input type="button" class="btn btn-info" value="Retrieve" onclick="retrieveRecords(<%= intCount %>, <%= h %>, '<%= zclientThread.getZ3950Server().getId()%>');" /></td>
		  </tr>
	</table>
<table class="table-striped table-condensed"  border="0" cellpadding="3" cellspacing="2" width="100%">
<colgroup><col width="5%">
     <col width="50%">
     <col width="20%">
     <col width="10%">
     <col width="15%">
    
  </colgroup>
  <tr class="theadrow1">
    <td align="center">No</td>
<%
                for (int i = 0; i < ArrayLengthCounter.countLength(aszTagDisplay); i++) {
%>
    <td align="center"><%= aszTagDisplay[i][0] %></td>
<%
                }
%>
    <td align="center">Action</td>
  </tr>
<%
                boolean blRow = true;
                String szRowClass = "";
                for (int k = 0; k < ArrayLengthCounter.countLength(aszMarcRecords); k++) {
                    if (blRow){
                        szRowClass = "tdatarow1";
                    } else {
                        szRowClass = "tdatarow2";
                    }
                    blRow = ! blRow;
                    String szMarcRecord = aszMarcRecords[k];
                    if (szMarcRecord != null) {
%>
  <tr class="<%= szRowClass %>">
    <td align="center"><%= k + intZClientFrom %></td>
<%
                        Record record = Record.fromString(szMarcRecord);
                        for (int i = 0; i < ArrayLengthCounter.countLength(aszTagDisplay); i++) {
%>
    <td>
      <a href="" onclick="selectMARCRecord(<%= k %>, <%= h %>); return false;">
<%
                            DataField[] aDataFields = record.getDataFields(aszTagDisplay[i][1]);
                            if (aDataFields != null) {
                                if ((aszTagDisplay[i][2] != null) && (aszTagDisplay[i][2].length() > 0)) {
                                    Subfield[] aSubfields = aDataFields[0].getSubfields(aszTagDisplay[i][2].charAt(0));
                                    if ((aSubfields != null) && (aSubfields[0].getDataElement() != null)) {
                                        out.println(aSubfields[0].getDataElement());
                                    }
                                } else {
                                    out.println(aDataFields[0].getFormattedData(new DefaultDataFieldDataFormatter()));
                                }
                            }
%>
      </a>

<%
                        }
%>
        <td>
    	<a class="btn btn-primary btn-sm" id='viewz3950' title="View z3950" onclick="selectMARCRecord(<%= k %>, <%= h %>); return false;"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></a>
    	<a class="btn btn-primary btn-sm" id='downz3950' data-dismiss="modal" onclick="downloadNow(<%= k %>, <%= h %>)" title="Download MARC"><span class="glyphicon glyphicon-download-alt" style="color:white"></span></a>
    </td>
  </tr>
<%
                    } else {
%>
  <tr class="<%= szRowClass %>">
    <td align="center"><%= k %></td>
<%
                        for (int i = 0; i < ArrayLengthCounter.countLength(aszTagDisplay); i++) {
%>
    <td>
    	<a class="btn btn-primary btn-sm" id='viewz3950' title="View z3950"><span class="glyphicon glyphicon-eye-open" style="color:white"></span></a>
    	<a class="btn btn-primary btn-sm" id='downz3950' data-dismiss="modal" onclick="downloadNow(<%= k %>, <%= h %>)" title="Download MARC"><span class="glyphicon glyphicon-download-alt" style="color:white"></span></a>
    </td>
<%
                        }
%>
  </tr>
<%
                    }
                } // for
%>
</table>
<br />
<%
            } else {
%>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" class="text-label" colspan="3">&nbsp;<%= zclientThread.getZ3950Server().getDescription() %></td>
  </tr>
  <tr class="message">
    <td width="10%">&nbsp;</td>
    <td>
      <br/>
      No result found.
      <input type="hidden" name="txtZClientFrom" />
      <input type="hidden" name="txtZClientTo"  />
      <br/><br/>
    </td>
    <td width="10%">&nbsp;</td>
  </tr>
</table>
<br />

<%
            }
%>
</div>
</div>
</div>
<%
        }
    }
%>
<br />
</div>
</div>
</div>


</form>
<div id="tmp" style="display:none"></div>

