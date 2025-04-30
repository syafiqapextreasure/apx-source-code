<%@page import="com.ilmu.foundation.LibraryCalendar.SQLStatement, java.text.SimpleDateFormat, java.util.Calendar"%>

<%
	try {
		String date = request.getParameter("holDate");
		String branch = request.getParameter("holBranch");
		String description = request.getParameter("holDesc");
		String type = request.getParameter("holType");
		String all = request.getParameter("allBranch");
		String endDate = request.getParameter("endDate");
		
		//String different = request.getParameter("different");
		int different = Integer.parseInt(request.getParameter("different"));
		
		System.out.println("endDate : " +endDate);
		System.out.println("different : " +different);
		
		int i = 1;
		
		if(different > 1){
			while(i <= different){
				System.out.println("I = "+i);
				System.out.println("date = "+date);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(date));
				c.add(Calendar.DATE, 1);  // number of days to add

				if (all.equals("true")) {
					SQLStatement.addHolidayAllBranch(date, description, type);
					//out.println("ok");
				} else if (type.equals("4")) {
					
					SQLStatement.addHoliday(date, branch, description, type);
					//out.println("ok");
				} else {
					SQLStatement.addHoliday(date, branch, description, type);
					//out.println("ok");
				}
				i++;
				date = sdf.format(c.getTime());  // dt is now the new date
			}
			out.println("ok");
		}else{
			if (all.equals("true")) {
				SQLStatement.addHolidayAllBranch(date, description, type);
				out.println("ok");
			} else if (type.equals("4")) {
				
				SQLStatement.addHoliday(date, branch, description, type);
				out.println("ok");
			} else {
				
				SQLStatement.addHoliday(date, branch, description, type);
				out.println("ok");
			}
		}
		
		

		/* if (all.equals("true")) {
			
			SQLStatement.addHolidayAllBranch(date, description, type);
			out.println("ok");
		} else if (type.equals("4")) {
			
			SQLStatement.addHoliday(date, branch, description, type);
			out.println("ok");
		} else {
			
			SQLStatement.addHoliday(date, branch, description, type);
			out.println("ok");
		} */
		
		/*  holiday create save to all library branch */
		/* only for weekend if weekend is drag to a specific date  */
		/*  holiday create only for current library branch only */
		
	} catch (Exception e) {
		out.println("error");
	}
%>