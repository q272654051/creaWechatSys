package com.crea.www.commons.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.processors.JsonValueProcessor;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * json 工具类 包括格式的转换,key的的剔除
 * @author moon
 *
 */
public class JsonUtil {
public static void main(String[] args) {
	 Map<String, Object>  map=JsonUtil.jsontoMap("{'sex1':'female','name0':'zhangsan'}");
	 System.out.println(map.get("sex1"));
}
    /**
     * 把object 对象转换json 并且除去不需要的对象或者key
     * @param obj 需要转换的对象
     * @param excludes 需要除去的key
     * @return String
     */
    public static String jsonObject(Object obj,String[] excludes ,String contentUtil){
        JSONObject jsonArray=JSONObject.fromObject(obj);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        JsonDateValueProcessor jsonDateValueProcessor=new JsonDateValueProcessor(contentUtil);
        //JsonUtil.jsonCustomizeDate(jsonConfig, contentUtil);
        jsonConfig.registerJsonValueProcessor(Date.class,  jsonDateValueProcessor);
        JSONObject jsonString=  jsonArray.fromObject(obj,jsonConfig);
        return jsonString.toString();

    }
    /**list 对象转换json 并且除去不需要的对象或者key
     * @param obj 需要转换的对象
     * @param excludes 需要除去的key
     * @return String
     */
    public static String jsonArray(List list,String[] excludes,String contentUtil){
        JSONArray jsonArray=JSONArray.fromObject(list);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
      //jsonFormatDate(jsonConfig);
        JsonUtil.jsonCustomizeDate(jsonConfig, ContentUtil.YMDHMS);
        JSONArray jsonString=  jsonArray.fromObject(list,jsonConfig);
        return jsonString.toString();
    }
    /**
     * 把遇见的时间格式转换成js能解析的格式
     * @param jsonConfig
     */
    private static  void jsonFormatDate(JsonConfig jsonConfig) {
        jsonConfig.registerJsonBeanProcessor(Date.class,
                new JsDateJsonBeanProcessor());//转换日期格式

    }
    
    /**
     * 将Json对象转换成Map
     * 
     * @param jsonObject
     *            json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map jsontoMap(String jsonString) {

        JSONObject jsonObject =JSONObject.fromObject(jsonString);
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
        }
        return result;

    }
    /**
     * 把遇见的时间格式转换成自定义时间格式
     * @param jsonConfig
     */
    public static  void jsonCustomizeDate(JsonConfig jsonConfig,final String dateFarmat) {
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
            public Object processObjectValue(String kay, Object value, JsonConfig config) {
                if(value == null)
                {
                    return "";
                }
                if(value instanceof java.sql.Timestamp)
                {
                    String str = new SimpleDateFormat(dateFarmat).format((java.sql.Timestamp)value);
                    return str;
                }
                if (value instanceof java.util.Date)
                {
                    String str = new SimpleDateFormat(dateFarmat).format((java.util.Date) value);

                    return str;
                }
                return value;
            }
            public Object processArrayValue(Object value, JsonConfig config) {
                if(value == null)
                {
                    return "";
                }
                if(value instanceof java.sql.Timestamp)
                {
                    String str = new SimpleDateFormat(dateFarmat).format((java.sql.Timestamp)value);
                    return str;
                }
                if (value instanceof java.util.Date)
                {
                    String str = new SimpleDateFormat(dateFarmat).format((java.util.Date) value);

                    return str;
                }
                return value;
            }
        });

    }

}
