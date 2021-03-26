package com.example.domain;

import java.util.List;

/**
 * @author Cindy.H
 * @description: 分页功能的JavaBean
 * @date 2021-03-25 22:54:25
 */
public class PageBean<T> {  //使用泛型为了以后可以扩展功能
    private int totalCount;  //总记录数
    private int totalPage;
    List<T> list;
    int currentPage; //当前页码
    int rows;  //每页显示的条数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageBean{");
        sb.append("totalCount=").append(totalCount);
        sb.append(", totalPage=").append(totalPage);
        sb.append(", list=").append(list);
        sb.append(", currentPage=").append(currentPage);
        sb.append(", rows=").append(rows);
        sb.append('}');
        return sb.toString();
    }
}
