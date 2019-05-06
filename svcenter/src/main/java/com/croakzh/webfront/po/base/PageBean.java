package com.croakzh.webfront.po.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PageBean 分页类
 *
 * @author croakzh
 */
public class PageBean extends BaseBean {

    /**
     * 页面起始位 sql语句的 limit的开始，从1开始
     */
    private Integer pagestart;

    /**
     * 页面大小
     */
    private Integer pagesize;

    /**
     * 当前页码
     */
    private Integer curpage;

    @JsonIgnore
    public Integer getPagestart() {
        return pagestart;
    }

    @JsonProperty
    public void setPagestart(Integer pagestart) {
        this.pagestart = pagestart;
    }

    @JsonIgnore
    public Integer getPagesize() {
        return pagesize;
    }

    @JsonProperty
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    @JsonIgnore
    public Integer getCurpage() {
        return curpage;
    }

    @JsonProperty
    public void setCurpage(Integer curpage) {
        this.curpage = curpage;
    }
}
