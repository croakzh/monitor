package com.croakzh.webfront.po.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BaseBean 基础类
 *
 * @author croakzh
 */
public class BaseBean {

    /**
     * 排序方式
     */
    private String sortord;

    /**
     * 模糊条件
     */
    private String term;

    @JsonIgnore
    public String getSortord() {
        return sortord;
    }

    @JsonProperty
    public void setSortord(String sortord) {
        this.sortord = sortord;
    }

    @JsonIgnore
    public String getTerm() {
        return term;
    }

    @JsonProperty
    public void setTerm(String term) {
        this.term = term;
    }
}
