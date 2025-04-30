<%--
  - zClient.jsp
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
<%@ page import="com.elmu.z3950.bm.*" %>
<%@ page import="com.elmu.z3950.util.*" %>
<%@ page import="com.paradigm.util.*" %>

   <script language="JavaScript" src="${pageContext.request.contextPath}/js/Cataloging/Z3950.js"></script>
<%
    String szAction = request.getParameter("ACTION");
    ZClientConfigHandler handler = ZClientConfigHandler.getInstance();
    String szRequestPage = request.getParameter("page") == null ? "" : request.getParameter("page");

    String szServer = request.getParameter("slcServer") == null ? "" : request.getParameter("slcServer");
    String szSearchTerm = request.getParameter("txtTerm") == null ? "" : request.getParameter("txtTerm");
    int iAttribute = -1;
    try {
        iAttribute = Integer.parseInt(request.getParameter("slcUseAttribute"));
    } catch (NumberFormatException nfe) {}
%>

<script language="JavaScript">

function validate() {
    var form = document.form1;
    if(getCheckedValue("slcServer") == "") {
        alert("Please select a Z39.50 Server.");
        return false;
    }
    if (form.txtTerm.value == "") {
        alert("Please fill in the search term.");
        form.txtTerm.focus();
        return false;
    }
    return true;
}

function testing() {
	
	  var servers = [];
	  var page = $("#page").val();
	  var txtTerm = $("#txtTerm").val();
	  var slcUseAttribute = $( "#slcUseAttribute option:selected" ).val();

	  localStorage.clear();
	  $("input[name='slcServer']:checked").each( function () {
		  servers.push($(this).val());
	   });

	  $.get("processingDelay",{slcServer:servers,page:page,txtTerm:txtTerm,
		  						slcUseAttribute: slcUseAttribute},function(data){
		  localStorage.setItem("lastData", data);				
		  $(".contents").html(data);
		  
	  });

	}


</script>
<!-- Modal content-->	  
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">
				Z39.50 CLIENT
		</h4> 
	</div>
	<div class="modal-body contents" style="max-height:75%;overflow-y:scroll">

<form class="form-horizontal" name="form1" action="" target="_self">
<input type="hidden" name="page" id="page" value="<%= szAction %>" />
<div class="form-group">
    <label class="control-label col-sm-2" for="email">Use Attribute:</label>
    <div class="col-sm-10">
        <select name="slcUseAttribute" id="slcUseAttribute" class="form-control">
            <%
            /*
            int[] useAttributeValues = handler.getUseAttributeValues();
            for (int i = 0; i < ArrayLengthCounter.countLength(useAttributeValues); i++) {
            %>
           <option value="<%= useAttributeValues[i] %>"<%= (iAttribute == useAttributeValues[i]) ? " selected" : "" %>><%= handler.getUseAttributeDescription(useAttributeValues[i]) %></option>
           <%
           }
            */
           %>
           <%
           UseAttribute[] useAttributes = handler.getAllUseAttributes();
           for (int i = 0; i < ArrayLengthCounter.countLength(useAttributes); i++) {
          %>
           <option value="<%= useAttributes[i].getId() %>"<%= (iAttribute == Integer.parseInt(useAttributes[i].getId()) ? " selected" : "") %>><%= useAttributes[i].getDescription() %></option>
          <%
          }
          %>
       </select>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Search Term:</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" id= "txtTerm" name="txtTerm" size="25" value="<%= szSearchTerm %>">
    </div>
  </div>
   <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Z39.50 Server:</label>
    <div class="col-sm-10"> 
          <%
                        /*
                        String[] z3950Servers = handler.getZ3950Servers();
                        for (int i = 0; i < ArrayLengthCounter.countLength(z3950Servers); i++) {
                    %>
                      <input type="checkbox" name="slcServer" value="<%= z3950Servers[i] %>" /><%= handler.getZ3950ServerDescription(z3950Servers[i]) %><br />
                    <%
                        }
                        */
                    %>
                    <%
                        
                        Z3950Server[] z3950Servers = handler.getAllZ3950Servers();
                        for (int i = 0; i < ArrayLengthCounter.countLength(z3950Servers); i++) {
                
                    %>
                      <label><input type="checkbox" name="slcServer" value="<%= z3950Servers[i].getId() %>" /><%= z3950Servers[i].getDescription() %></label><br />
                    <%
                        }
                        
                    %>
    </div>
  </div>

  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
     
    </div>
  </div>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" valign="top" align="center">

      <br />
      <br />
<%
    if (! szRequestPage.equals("")) {
%>
      <table border="0" width="90%" cellspacing="0" cellpadding="0">
        <tr class="message">
          <td width="10%">&nbsp;</td>
          <td><br/>
<%
        String szEvent = request.getParameter("event") == null ? "" : request.getParameter("event");
        if (szEvent.equals("1")) {
            out.println("Z39.50 Server not specify.");
        } else if (szEvent.equals("2")) {
            out.println("Use Attribute not specify.");
        } else if (szEvent.equals("3")) {
            out.println("Search Term not specify.");
        } else if (szEvent.equals("4")) {
            out.println("No result found.");
        } else if (szEvent.equals("5")) {
            out.println("Z39.50 connection could not establish.");
        }
%>
            <br/><br/>
          </td>
          <td width="10%">&nbsp;</td>
        </tr>
      </table>
<%
    }
%>
    </td>
  </tr>
</table>
</form>
</div>
<div class="modal-footer" id="modalfooter_Z390">
	<button type="button" name="sbmSearch" onclick="testing()" class="btn btn-info">Submit</button>
		<!-- <input type="button" name="cancel" value="Cancel" class="btn btn-info btn-cancel" data-dismiss="modal"/> -->
</div>	

