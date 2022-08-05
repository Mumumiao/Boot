package com.mySpring.boot.Config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Bean1 {
    private Bean2 bean2;
    private String name;
}
