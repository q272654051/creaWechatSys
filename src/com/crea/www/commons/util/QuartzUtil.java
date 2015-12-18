package com.crea.www.commons.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 定时器工具,使用spring继承的定时器调用
 *
 * @author moon
 *
 */
public class QuartzUtil {
    //Logger logger = Logger.getLogger(QuartzUtil.class);
    private static Logger logger=LoggerFactory.getLogger(QuartzUtil.class);
    /**
     * 调用方法
     */
    int i=4;
    public void work(){
        try {
            System.out.println("开启定时器---->"+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            System.out.println(1/i--);
        } catch (Exception e) {
            logger.error("QuartzUtil->work",e);
        }


    }


}
