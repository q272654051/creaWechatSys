/**
 * 
 */
package com.crea.www.commons.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/** 
 * @author moon
 * @date 2015-5-11 下午4:14:06
 * @version 
 * @param <Date>
 * @TODO 描述
 */
public class JsonDateSerializer extends JsonSerializer<Date>{

	  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss"); 
	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		String formattedDate = dateFormat.format(date); 
        gen.writeString(formattedDate); 
		
	}

}
