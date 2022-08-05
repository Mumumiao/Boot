package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class User {
    private int id;
    @NotBlank(message = "账号不能为空",groups = AddGroup.class)
    @Size(min = 2,max = 10,message = "长度不对",groups = AddGroup.class)
    private  String account;
    private  String password;
    @NotNull(message = "角色不能为空",groups = AddGroup.class)
    @Valid
    private Role role;
    private String status;
}
