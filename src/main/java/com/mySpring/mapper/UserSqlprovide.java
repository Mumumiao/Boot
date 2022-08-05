package com.mySpring.mapper;


import com.mySpring.boot.Role;
import com.mySpring.boot.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class UserSqlprovide{

    public String addSql(User user) {
        final int id = user.getId();
        final String account = user.getAccount();
        final String password = user.getPassword();
        final Role role = user.getRole();
        final String status = user.getStatus();
        String sql = new SQL() {
            {
                INSERT_INTO("user");
                VALUES("account", "#{account}").VALUES("password", "#{password}").
                        VALUES("role", "#{role.id}").VALUES("status", "#{status}");
            }

        }.toString();
        log.debug(sql);
        return sql;

    }
    public String updateSql(User user) {
        final int id = user.getId();
        final String account = user.getAccount();
        final String password = user.getPassword();
        final Role role = user.getRole();
        final String status = user.getStatus();
        String sql = new SQL() {
            {
                UPDATE("user");
                if(account!=null){
                    SET("account=#{account}");
                }
                if(password!=null){
                    SET("password=#{password}");
                }
                if(role!=null){
                    SET("role=#{role.id}");
                }if(status!=null){
                SET("status=#{status}");
            }
                WHERE("id=#{id}");

            }

        }.toString();
        log.debug(sql);
        return sql;

    }


}
