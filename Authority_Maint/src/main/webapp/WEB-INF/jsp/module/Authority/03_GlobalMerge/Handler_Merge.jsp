
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.Authority.AuthorityGlobal.*,
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
//int pointer = Integer.parseInt(request.getParameter("pointer"));
int topointer = Integer.parseInt(request.getParameter("topointer"));
String term = request.getParameter("term");
//String tag = request.getParameter("tag");
String totag = request.getParameter("totag");
String[] tag = request.getParameterValues("tag[]");
String[] pointer = request.getParameterValues("pointer[]");
int total = Integer.parseInt(request.getParameter("total"));
boolean next = false;
for(int i=0;i<total;i++){
	int hitcount = Global_Merge.getHitCount(Integer.parseInt(pointer[i]), tag[i]);
	int tohitcount = Global_Merge.getHitCount(topointer, totag);
	int totalcount = hitcount + tohitcount;
	System.out.println("Count" + hitcount);
	if(hitcount<=0){
		next = false;
		break;
	}else{
		boolean updateTerm = Global_Merge.updateHitcount(topointer, totag, totalcount);
		Global_Merge.updateIdxBy(Integer.parseInt(pointer[i]));
	if(updateTerm)
	{
		boolean updatePointer = Global_Merge.updatePointer(Integer.parseInt(pointer[i]), topointer, totag);
	

		if(updatePointer && hitcount>0)
		{
			boolean deleteTerm = Global_Merge.deleteTerm(Integer.parseInt(pointer[i]), tag[i]);
			
			
			/* if(deleteTerm)
			{
				Global_Merge.updateIdxBy(topointer);
				out.println("success");
			}else{
				out.println("Fail to delete invalid term");
			} */
			next = true;
		}else if(hitcount==0)
		{
			boolean deleteTerm = Global_Merge.deleteTerm(Integer.parseInt(pointer[i]), tag[i]);
			
			/* if(deleteTerm)
			{
				Global_Merge.updateIdxBy(topointer);
				out.println("success");
			}else{
				out.println("Fail to delete invalid term");
			} */
			next = true;
		}else{
			//out.println("Fail to update pointer");
			next = false;
		}
		next = true;
	}else{
		//out.println("Fail Merge");
		next = false;
	}
}
}
if(next){
	out.println("success");
}else{
	out.println("Fail to delete invalid term");
} 
/* int hitcount = Global_Merge.getHitCount(pointer, tag);
int tohitcount = Global_Merge.getHitCount(topointer, totag);
int totalcount = hitcount + tohitcount;

boolean updateTerm = Global_Merge.updateHitcount(topointer, totag, totalcount);

if(updateTerm)
{
	boolean updatePointer = Global_Merge.updatePointer(pointer, topointer, totag);

	if(updatePointer)
	{
		boolean deleteTerm = Global_Merge.deleteTerm(pointer, tag);
		
		if(deleteTerm)
		{
			Global_Merge.updateIdxBy(topointer);
			out.println("success");
		}else{
			out.println("Fail to delete invalid term");
		}
	}else{
		out.println("Fail to update pointer");
	}
}else{
	out.println("Fail Merge");
} */

%>