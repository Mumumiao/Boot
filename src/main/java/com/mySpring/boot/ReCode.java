package com.mySpring.boot;

public enum ReCode {
    success("200"),
    not_found("404"),
    INVALIDTOKEN("501");
    public String code;
   ReCode(String code) {
        this.code = code;
    }
}
