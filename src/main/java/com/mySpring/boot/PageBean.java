package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageBean<T> {
    private List<T> data;
    private int currPage;
    private int currPageSize;
    private int pageSize;
    private int totalNums;
    private int totalPages;
    private int othnum;
    private String othnum2;



}
