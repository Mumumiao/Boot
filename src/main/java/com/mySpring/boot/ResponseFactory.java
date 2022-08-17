package com.mySpring.boot;

public class ResponseFactory {
    public static <T>ResponseEntility<T> getSuResponseEntility(T t){
        ResponseEntility<T> resp=new ResponseEntility<>(ReCode.success,"编译成功",t);
        return  resp;
    }
    public static <T> ResponseEntility<T> getDeResponseEntility(T t){
        ResponseEntility<T> resp=new ResponseEntility<>(ReCode.not_found,"编译失败",t);
        return  resp;
    }
    public static <T> ResponseEntility<T> getInResponseEntility(T t){
        ResponseEntility<T> resp=new ResponseEntility<>(ReCode.INVALIDTOKEN,"验证不过",t);
        return  resp;
    }
}
