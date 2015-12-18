package com.crea.www.commons.enumeration;

/**
 * @author dengbojing
 * @date 2015-8-13
 * @description EDITING 正在编辑
 *   WAIT_REVIEW  等待审核
 *	PUBLISHED 已发布   
 *  REVIEW_BACK  审核退回 
 *  NO_PUBLISHED 发布收回
 */
public enum News_status {
    EDITING(1), //正在编辑
    WAIT_REVIEW(2), //等待审核
    PUBLISHED(3),   //已发布
    REVIEW_BACK(4),  //审核退回
    NO_PUBLISHED(5);  //已收回
    
    
    
    private int status;
    private News_status(int status){
        this.status = status;
    }
    
    public int status(){
        return status;
    }
}
