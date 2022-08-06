package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class Userright {
    private int id;
    private  String certificatify;
    @NotBlank(message = "权利名字不能为空",groups = AddGroup.class)
    private  String name;
    private  int fa ;
    private  String status;
    private List<Urc> urc;
    private String url;
}
