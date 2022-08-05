package com.mySpring.mapper;


import com.mySpring.boot.Role;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class RoleSqlprovide  {

    public String updateSql(Role role){
        final int id= role.getId();
        final  String scripe= role.getScripe();
        final  String name= role.getName();
        final String status= role.getStatus();
        return new SQL(){
            {
                UPDATE("role");
                if(scripe!=null){
                    SET("scripe=#{scripe}");
                }
                if(name!=null){
                    SET("name=#{name}");
                }
                if(status!=null){
                SET("status=#{status}");
            }
                WHERE("id=#{id}");
         }

        }.toString();
    }
    public String seleSql(Role role){
        final int id= role.getId();
        final  String scripe= role.getScripe();
        final  String name= role.getName();
        final String status= role.getStatus();
        return new SQL(){
            {
               SELECT("*");
               FROM("role");
                if(scripe!=null){
                   WHERE("scripe=#{scripe}");
                }
                AND();
                if(name!=null){
                    WHERE("name=#{name}");
                }
                AND();
                if(status!=null){
                    WHERE("status=#{status}");
                }
                AND();
                if(id!=0){
                    WHERE("id=#{id}");
                }
                AND();
                WHERE("status='可见'");
            }

        }.toString();
    }
}
