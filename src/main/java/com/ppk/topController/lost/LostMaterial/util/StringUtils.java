package com.ppk.topController.lost.LostMaterial.util;

import java.util.List;

import com.ppk.topController.lost.LostMaterial.service.ISBD2;

public class StringUtils {

    public static String checkNull(String raw ) {
    	 StringBuilder result = new StringBuilder();
    	    if (raw != null && raw != "") {
    	      String[] rawArray = raw.split("\\|");
    	      for (int i = 1; i < rawArray.length; i++) {
    	        if (rawArray[i].isEmpty()) {
    	          result.append(" ");
    	        } else if (!rawArray[i].substring(0, 1).equals("6") && !rawArray[i].substring(0, 1).equals("8")) {
    	          String splitData = rawArray[i].substring(1);
    	          if (splitData != null && splitData != "") {
    	            splitData = "|" + rawArray[i].substring(0, 1) + splitData;
    	            result.append(splitData);
    	            if (i != rawArray.length)
    	              result.append(" "); 
    	          } 
    	        } 
    	      } 
    	    } 
    	    return result.toString();
    }

    public static String toUpperCase(String value) {
        return value != null ? value.trim().toUpperCase() : "";
    }

    public static String toLowerCase(String value) {
        return value != null ? value.trim().toLowerCase() : "";
    }

    public static String replaceFirst(String source, String target, String replacement) {
        return source != null ? source.replaceFirst(target, replacement) : "";
    }

    public static String removeSpecialChars(String value) {
        if (value == null || value.isEmpty()) return "";
        return value.replaceAll("[^A-Za-z0-9 ]", "").replaceAll(" +", " ");
    }
}