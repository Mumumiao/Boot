package com.mySpring.mapper;

import com.mySpring.boot.Dept;
import com.mySpring.boot.Emp;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Scope("singleton")
public class EmpSqlProvide {
    public String seleS(Emp emp) {
        final int id = emp.getId();
        final String photo = emp.getPhoto();
        final String name = emp.getName();
        final String gender = emp.getGender();
        final String entry_date = emp.getEntry_date();
        final BigDecimal salary = emp.getSalary();
        final Dept dept = emp.getDept();
        return new SQL() {
            {
                SELECT("*");
                FROM("oa_emp");
                if (photo != null && photo != "") {
                    WHERE("photo=#{photo}");
                }

                if (name != null && name != "") {
                    WHERE("name=#{name}");

                }

                if (gender != null && gender != "") {
                    WHERE("gender=#{gender}");

                }

                if (entry_date != null && entry_date != "") {
                    WHERE("entry_date=#{entry_date}");

                }

                if (salary != null) {
                    WHERE("salary=#{salary}");

                }
                if (dept != null && dept.getId() != 0) {
                    WHERE("dept_id=#{dept.id}");
                }
                if (id != 0) {
                    WHERE("id=#{id}");
                }
            }
        }.toString();


    }
    public String update(Emp emp) {
        final int id = emp.getId();
        final String photo = emp.getPhoto();
        final String name = emp.getName();
        final String gender = emp.getGender();
        final String entry_date = emp.getEntry_date();
        final BigDecimal salary = emp.getSalary();
        final Dept dept = emp.getDept();
        return new SQL() {
            {
               UPDATE("oa_emp");

                if (photo != null && photo != "") {
                    SET("photo=#{photo}");
                }

                if (name != null && name != "") {
                    SET("name=#{name}");

                }

                if (gender != null && gender != "") {
                    SET("gender=#{gender}");

                }

                if (entry_date != null && entry_date != "") {
                    SET("entry_date=#{entry_date}");

                }

                if (salary != null) {
                    SET("salary=#{salary}");

                }
                if (dept != null && dept.getId() != 0) {
                    SET("dept_id=#{dept.id}");
                }

                    WHERE("id=#{id}");

            }
        }.toString();


    }
}
