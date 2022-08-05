package com.mySpring.boot.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
 /*   @Bean("bean1")
    public Bean1 getBean1(){
       Bean1 bean1=new Bean1();
       bean1.setBean2(getBean2());
       return bean1;

    }*/
    @Bean("bean1")
    public Bean1 getBean1(Bean2 bean2){
        Bean1 bean1=new Bean1();
        bean1.setBean2(bean2);
        return bean1;

    }
    @Bean("bean2")
    public Bean2 getBean2(){
        Bean2 bean2=new Bean2();
        bean2.setName("第3个");
        return bean2;
    }
    @Bean
    public Bean2 getBean(){
        Bean2 bean2=new Bean2();
        bean2.setName("第1个");
        return bean2;
    }

}
