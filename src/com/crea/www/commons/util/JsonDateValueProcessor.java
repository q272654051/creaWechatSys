package com.crea.www.commons.util;

import java.util.Date;
import java.util.Locale;

import com.ibm.icu.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor{
	 private String datePattern = "yyyy-MM-dd";  
	 
	 public JsonDateValueProcessor(String datePattern) {
		 super();
		this.datePattern = datePattern;
	}
	public String getDatePattern() {
		return datePattern;
	}
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	public JsonDateValueProcessor() {  
		         super();  
		     } 
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		  return process(arg1); 
	}

	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		  return process(arg1); 
	}
	private Object process(Object value) {  
        try {  
            if (value instanceof Date) {  
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern,  
                        Locale.UK);  
                return sdf.format((Date) value);  
            }  
            return value == null ? "" : value.toString();  
        } catch (Exception e) {  
            return "";  
        }  
  
    }  
}
