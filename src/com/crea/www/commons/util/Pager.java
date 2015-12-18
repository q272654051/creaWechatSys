package com.crea.www.commons.util;

import java.util.ArrayList;
import java.util.List;


/**
 * <p style="color:blue">
 * 分页工具,穿梭于dao+service+controllers
 * </p>
 *
 * @author moon
 *
 */
public class Pager {
    private int curPage = 1; // 当前页
    private int pageSize = 5; // 每页多少行
    private int totalRow; // 共多少行
    private int start;// 当前页起始行
    private int end;// 结束行
    private int totalPage; // 共多少页
    private  List list=new ArrayList();//装对象

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getCurPage() {
        return curPage;
    }

    public Pager() {

    }
    public Pager(int curPage,int pageSize) {
        this.curPage=curPage;
        this.pageSize=pageSize;
        this.start=curPage>0?(curPage-1)*pageSize:0;
        this.end=curPage*pageSize<this.totalRow?(curPage+1)*pageSize:this.totalPage;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.end=(this.start+this.pageSize)<totalRow?(this.start+this.pageSize):totalRow;
        this.totalRow = totalRow;
    }

    public int entityClass() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getTotalPage() {
        totalPage=totalRow%this.pageSize==0?totalRow/this.pageSize:totalRow/this.pageSize+1;
        return totalPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return "Pager [curPage=" + curPage + ", pageSize=" + pageSize
                + ", totalRow=" + totalRow + ", start=" + start + ", end="
                + end + ", totalPage=" + totalPage + ", list=" + list + "]";
    }

}