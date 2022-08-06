package com.mySpring.vo;

import com.mySpring.boot.Emp;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Empvo {
    @NotNull
    private Emp emp;
    @Min(value=1,message = "页码不能为空")
    private  int page;
    @Min(value=1,message = "押大小不能为空")
    private  int size;
    private  int dt;
}
