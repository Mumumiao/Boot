package com.mySpring;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.mySpring.boot.Config.SpringFilt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.mySpring.mapper")
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

    //    @Bean
//    @Order(0)
//    public SpringFilt SpringFilt() {
//        SpringFilt springFilt=new SpringFilt();
//        springFilt.setOrder();
//        return springFilt;
//    }
    @Bean
    public SpringFilt springFilt() {

        return new SpringFilt();
    }

//    @Bean
//    public JwtFilt jwtFilt() {
//
//        return new JwtFilt();
//    }
//
//    @Bean
//    public RightFilt rightFilt() {
//
//        return new RightFilt();
//    }

    @Bean
    public FilterRegistrationBean filterspringBean(SpringFilt springFilt) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(springFilt());
        //过滤器名称
        registration.setName("springFilter");
        //拦截路径
        registration.addUrlPatterns("/*");
        //设置顺序
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean filterJwtBean(JwtFilt jwtFilt) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(jwtFilt());
//        //过滤器名称
//        registration.setName("jwtFilter");
//        //拦截路径
//        registration.addUrlPatterns("/*");
//        //设置顺序
//        registration.setOrder(Integer.MIN_VALUE + 1);
//        return registration;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterJRightBean(RightFilt rightFilt) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(rightFilt());
//        //过滤器名称
//        registration.setName("rightFilter");
//        //拦截路径
//        registration.addUrlPatterns("/*");
//        //设置顺序
//        registration.setOrder(Integer.MIN_VALUE + 2);
//        return registration;
//    }

}
