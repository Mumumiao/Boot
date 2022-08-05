package com.mySpring.boot;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseEntility<T> {
    private ReCode reCode;
    private String msg;
    private T date;
    public ResponseEntility(){

    }
    public ResponseEntility(ReCode reCode,String msg,T t){
        this.reCode=reCode;
        this.msg=msg;
        this.date=t;

    }

}
