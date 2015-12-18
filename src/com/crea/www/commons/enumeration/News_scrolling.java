package com.crea.www.commons.enumeration;

/**
 * @author dengbojing
 * @date 2015-8-13
 * @description
 */
public enum News_scrolling {
    NO_SROLLING(0),  //不滚动
    SCROLLING(1);   //滚动
    
    
    private int status;
    private News_scrolling(int status){
        this.status = status;
    }
    
    public int status(){
        return this.status;
    }
}
