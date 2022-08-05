package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Userright {
    private int id;
    private  String certificatify;
    private  String name;
    private  int fa ;
    private  String status;
    private List<Urc> urc;
    private String url;
}
