<%@ page import="com.ilmu.global.serial.IssuesPattern, java.util.List, com.ilmu.global.DateFormatter" %>
<%@ page import="java.util.regex.Matcher, java.util.regex.Pattern, java.util.StringTokenizer"  %>
<%
try{
	String firstVolume = request.getParameter("firstVolume");
	System.out.println("firstVolume" +firstVolume);

	StringTokenizer token = new StringTokenizer(firstVolume, " ");
	Pattern p = Pattern.compile("\\d+");
    

	while (token.hasMoreTokens()) {
		String tempToken = token.nextToken();
		System.out.println(tempToken +"pppp");
		
		Matcher m = p.matcher(tempToken);
		while(m.find()) {
			System.out.println(" groupCount: " + m.groupCount());
	        System.out.println(m.group() +"Found");
	        System.out.println(m.group() + 1 +"Found + 1");
	    }
		
		
		  
        
	}
	
	int quantity = Integer.parseInt(request.getParameter("quantity"));
	int noOfIssues = Integer.parseInt(request.getParameter("noOfIssues"));
	System.out.println("noOfIssues " +noOfIssues);
	String startDate = request.getParameter("startDate");
	
	String freqType = request.getParameter("freqType");
	System.out.println("freqType " +freqType);
	/* String[] dateParts = startDate.split("/");
	String startYear = String.format("%04d", Integer.parseInt(dateParts[2]));
    System.out.println("Year for date object is : "+startYear);   */
    
	int pattern = Integer.parseInt(request.getParameter("patternID"));
	String firstPattern = request.getParameter("firstPattern");
	String freq = request.getParameter("freq");
	String freqTime = request.getParameter("freqTime");

	int count = 0;
	String result = "";
	
	int volCount = 0;
	String currPattern ="";
	String currDate = "";
	int istartMonth = 0;
	//currDate = startDate;
	
	for(int i = 0; i < quantity; i++){
	    
		for(int y = 0; y < noOfIssues; y++){
			
			System.out.println("START");
			System.out.println(noOfIssues + "|" +y);
			
			result += "<tr><td>"+ (i+1) +"</td><td>";
			
			if(y == 0){
				result += firstVolume;
			}else{
				String[] dateParts = startDate.split("/");
				String startYear = String.format("%04d", Integer.parseInt(dateParts[2]));
			    System.out.println("Year for date object is : "+startYear); 
			    
				////split volumn
		        String[] arrOfVolumn = firstVolume.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"); 
		  
		        for (String a : arrOfVolumn){ 
		            System.out.println(a +"qqq"); 
		        }
		        
		        int totalVolum = arrOfVolumn.length;
		        System.out.println(totalVolum +"ppppppp");
		        
		        String lastarrOfStr = arrOfVolumn[totalVolum-1] ;
		        
				boolean numeric = true;
				numeric  = lastarrOfStr.matches("-?\\d+(\\.\\d+)?");
				
				if(numeric){
		            System.out.println(lastarrOfStr + " is a number");
		            
		            //currDate = DateFormatter.nextDate(currDate, freq);
		            currDate = DateFormatter.nextDate(startDate, freq, (y));
		            
		            String[] dateParts2 = currDate.split("/");
					String currYear = String.format("%04d", Integer.parseInt(dateParts2[2]));
				    //System.out.println("currYear : "+currYear); 
		            
		            int istartYear=Integer.parseInt(startYear);
		            //System.out.println(istartYear +"istartYear"); 
	            	int icurrDate=Integer.parseInt(currYear); 
	            	//System.out.println(icurrDate +"icurrDate"); 
	            	
	            	int totalAdd = icurrDate - istartYear;
	            	//System.out.println(totalAdd +"totalAddtotalAdd");
	            	
	            	////getMonth
	            	String currmonth = String.format("%01d", Integer.parseInt(dateParts2[1]));
	            	//System.out.println("currmonth : "+currmonth); 
	            	istartMonth = Integer.parseInt(currmonth);
	            	//System.out.println("istartMonth : "+istartMonth); 
	            	
	            	if(totalAdd == 0){
	            		result += firstVolume;
	            	}else{
	            		String finalVol = "";
	        	        
	            		for (int b=0; b < totalVolum-1; b++){
					      finalVol = String.join("", arrOfVolumn[b] );
					    }
	            		
	            		int ifreqTime = Integer.parseInt(freqTime);
	            		
	            		 if(freqType.equalsIgnoreCase("YYYY")){	
	            			 result += finalVol + ((totalAdd/ifreqTime) + Integer.parseInt(lastarrOfStr)); 
	            		 }else{
	            			 result += finalVol + (totalAdd + Integer.parseInt(lastarrOfStr)); 
	            		 }
	            		
	            		System.out.println("eree" +totalAdd/2);
	            		
	            		//result += finalVol + (totalAdd + Integer.parseInt(lastarrOfStr));

	            	}
				}else{
					result += firstVolume;
		            System.out.println(lastarrOfStr + " is not a number");
				} 			    
			}		
			result += "</td>";
			result += "<td>";
			
			if(y == 0)
			{
				currPattern = firstPattern;
				result += firstPattern;
			}
			else{
				currPattern = IssuesPattern.SE04_getNextPattern(pattern,currPattern, istartMonth);
				result += currPattern; 
				/* if(freqType.equalsIgnoreCase("YYYY")){		
					currPattern = firstPattern;		
					result += firstPattern;
				}else{
					int ifreqTime=Integer.parseInt(freqTime);
		            System.out.println(ifreqTime +"ifreqTime"); 
					currPattern = IssuesPattern.SE04_getNextPattern(pattern,currPattern, istartMonth);
					//currPattern = IssuesPattern.SE04_getNextPattern(pattern,currPattern, ifreqTime);
					result += currPattern; 
				}  */
			}

			result += "</td>";
			result += "<td>";
			
			if(y == 0){
				currDate = startDate;
				result += currDate;
			}else{
				result +=currDate;
			}
			
			//result += "'></td></tr>";
			result += "</td></tr>";

		}
	}
	
	out.println(result);
} catch (NumberFormatException e) {
    e.printStackTrace();
}

%>