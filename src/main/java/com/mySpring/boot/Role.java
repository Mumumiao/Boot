package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
public class Role {
    @Min(value=1,message = "id不能为空",groups =AddGroup.class)
    private int id;
    private  String scripe;
    private  String name;
    private  String status;
    private List<Urc> urc;
    private List<Menu> menus;
    private List<Userright> Userrights;

}
