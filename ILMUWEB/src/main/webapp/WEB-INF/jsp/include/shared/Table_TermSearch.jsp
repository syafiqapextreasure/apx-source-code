	<!-- Java class-->
	<%@ page import="com.ilmu.global.*,
					java.util.*"%>
	<script>
	$(document).ready(function(){
		$('.clickable-row1').click(function() {

			/* var title = $(this).attr("data-value");
			$('td[class^="subf_"]').each(function (event) {
			var replace = this.className.replace("subf_", "");
			var testing = $(".concatData").attr("id");
			var splitSubf = title.split("|");
			var raw = "";
			for(var i = 1; i < splitSubf.length; i++)
			{
				var subfield = splitSubf[i].substring(0,1);
				raw = splitSubf[i].substring(1);
				if(replace==subfield){
					document.getElementById(testing).value = raw;
					continue;
				}
				else{
					alert(subfield + replace);
				}
			}
			
			});  */
			var title = $(this).attr("data-value");
			var splitSubf = title.split("|");
			 var html = "";
				for(var i = 1; i < splitSubf.length; i++)
				{
					var subfield = splitSubf[i].substring(0,1);
					var raw = splitSubf[i].substring(1);
					   var name = $(".concatData").attr('name');

					    html += 
				    	"<tr class='primarySubf'>" + 
					"<td>" + 
						"<a class='btn btn-success btn-xs sort'>" + 
							"<span class='glyphicon glyphicon-sort'></span>"+
						"</a>" + 
					"</td>" + 
					"<td class='subf_"+subfield+"'>" + 
						subfield + 
					"</td>" + 
					"<td colspan='2'>" + 
						"<input class='concatData' name='"+name+"' id='|"+subfield+"' data-desc='|"+subfield+"' value='"+raw+"' onkeyup='getDesc(this);getData(this)' type='text' size='99'>"+
					"</td>" +
					"<td>"  +
						"<a class='btn btn-danger btn-sm' onclick='removeSubf(this)'>" + 
							"<span class='glyphicon glyphicon-trash'></span>" + 
						"</a>" +
						"<a class='btn btn-default btn-sm duplicateSubf'>" + 
							"<span class='glyphicon glyphicon-duplicate'></span>"+
						"</a>" + 
					"</td>"+
				"</tr>";
				
				   /*  var row = table.insertRow(rowCount);

				     var cell1 = row.innerHTML(0);
				     cell1.innerHTML = "<a class='btn btn-success btn-xs sort'><span class='glyphicon glyphicon-sort'></span></a>";
				     var cell2 = row.insertCell(1);
				     cell2.innerHTML = subfield;
				     
				     var cell3 = row.insertCell(2);
				     cell3.colSpan = 2;
				     cell3.innerHTML ="<input size='99' type='text' onkeyup='getDesc(this);getData(this)' value='"+raw+"' name='"+(name++)+"' data-desc='|"+subfield+"' id='|"+subfield+"'>";
		      
				     var cell4 = row.insertCell(3);
				     cell4.innerHTML = "<a class='btn btn-danger btn-sm' onclick='removeSubf(this)'><span class='glyphicon glyphicon-trash'></span></a>" +
				     "<a class='btn btn-default btn-sm duplicateSubf'><span class='glyphicon glyphicon-duplicate'></span></a>"; */
			}
			$("tr.primarySubf").empty();
			$("tr.headingTr").after(html);
		    
			$('#termSearch').modal('hide');
			
		});
		});
	</script>
	<table class="table table-hover" data-toggle="table" id="title_result" data-click-to-select="true">
		<thead>
			<tr>
				<th data-sortable="true">Term</th>
				<th data-sortable="true">Hits</th>
			</tr>
		</thead>
		<tbody>
		<% 
		int count = 0;
		String tag = request.getParameter("tag");
		String criteria = request.getParameter("criteria");
		
		List<SearchRecord> searchResult = null;
		
		if(criteria == null)
		{
			out.println();
		}else
		{	
			searchResult = SearchRecord.getByTermSearch(criteria, tag);
		%>
		<%
			for(SearchRecord i: searchResult){
				count++;
		%>
			<tr class="clickable-row1" data-value="<%= i.getRaw() %>">
				<td style="display:none" class="pointer" data-value=""></td>
				<td class="title" data-value="<%= i.getTitle() %>"><%= i.getTitle() %></td>
				<td class="hits" data-value="<%= i.getHits() %>"><%= i.getHits() %></td>
			</tr>
		<% 
				}
		if (count==0){
		%>
		<tr class="clickable-row1">
				<td class="pointer" colspan="2">
					<div class="alert alert-info" align="center">
						<font style="color:black;">No record found</font>
					</div>
				</td>
			</tr>
		<%
		}
		}
		%>
		</tbody>
	</table>
	