package com.imooc.policy.request;

public class PageQuery {

    private Integer page = 1;

    private Integer size = 30;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
