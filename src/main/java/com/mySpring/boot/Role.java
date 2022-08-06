package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class Role {
    @Min(value=1,message = "id不能为空")
    private int id;
    private  String scripe;
    @NotBlank(message = "角色名字不能为空",groups = AddGroup.class)
    @NotNull(message = "角色名字不能为空",groups = AddGroup.class)
    private  String name;
    private  String status;
    private List<Urc> urc;
    private List<Menu> menus;
    private List<Userright> Userrights;

}
