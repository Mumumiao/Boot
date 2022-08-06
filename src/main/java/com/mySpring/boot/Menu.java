package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class Menu {
    private int id;
    private  String certificatify;
    @NotBlank(message = "菜单名字不能为空",groups = AddGroup.class)
    private  String name;
    private  String href;
    private  String image;
    private  int fa;
    private  String status;
    private List<Mrc> mrc;
    private List<Menu> menuList;
}
