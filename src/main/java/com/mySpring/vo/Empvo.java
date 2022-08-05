package com.mySpring.vo;

import com.mySpring.boot.Emp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Empvo {
    private Emp emp;
    private  int page;
    private  int size;
    private  int dt;
}
