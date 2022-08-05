package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Menu {
    private int id;
    private  String certificatify;
    private  String name;
    private  String href;
    private  String image;
    private  int fa;
    private  String status;
    private List<Mrc> mrc;
    private List<Menu> menuList;
}
