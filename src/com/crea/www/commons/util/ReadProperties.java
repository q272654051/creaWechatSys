package com.crea.www.commons.util;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author moon
 *读取上传文件读取路径
 */
public class ReadProperties {
    private static final String properties = "FileStorage.properties";
	private static final String PropertiesPath = "com/crea/www/commons/properties/";//properties文件所在路径
    private static Properties fileProperties = null;
    /* 读取配置文件*/
    static {
        fileProperties = new Properties();
        InputStream loadFile = ReadProperties.class.getClassLoader()
                .getResourceAsStream(properties);
        try {
            fileProperties.load(loadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String paramInfo(String param){
        return fileProperties.getProperty(param);
    }
    public static void main(String[] args) {
        System.out.println(ReadProperties.paramInfo("ImgPath"));
    }
    
    public static String readPropertie(String propertiesName, String propertiesKey) {
        Properties fileProperties = new Properties();
        InputStream loadFile = ReadProperties.class.getClassLoader()
                .getResourceAsStream(PropertiesPath + propertiesName + ".properties");
        try {
            fileProperties.load(loadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileProperties.getProperty(propertiesKey);
    }
}
