package com.crea.www.commons.util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
/**
 * <p style="color:blue">  map和bean互转工具类</p>
 * @author moon
 *
 */
public class BeanToMapUtil {

    static{
        ConvertUtils.register( new DateTimeConverter(), java.util.Date.class);
    }
    /**
     * Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
     * @param map map集合
     * @param obj 实体对象
     */
    public static void transMapToBean(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return;
        }
        try {

            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            System.out.println("map转换bean失败 " + e);
        }
    }
    /**
     * Bean -->Map  2: 利用org.apache.commons.beanutils 工具类实现 Bean -->   Map
     * @param obj 要转换的实体
     * @return
     */
    public static Map<String, String>  transBeanToMap( Object obj) {
        Map<String, String> map=null;
        if ( obj == null) {
            return map;
        }
        try {
            map=  BeanUtils.describe(obj);
        } catch (Exception e) {
            System.out.println("bean转换map失败 " + e);
        }
        return map;
    }
}
