package com.mySpring.boot;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@TableName("oa_emp")
@EqualsAndHashCode
public class Emp {
    @TableId(type = IdType.AUTO)
    @ExcelProperty("id")
    private int id;
    @TableField("name")
    @ExcelProperty("name")
    private  String name;
    @TableField("gender")
    @ExcelProperty("gender")
    private  String gender;
    @TableField("photo")
    @ExcelProperty("photo")
    private String photo;
    @TableField("entry_date")
    @ExcelProperty("entry_date")
    private String entry_date;
    @TableField("salary")
    @ExcelProperty("salary")
    private BigDecimal salary;
    @TableField(value = "dept_id",property ="dt")
    private Dept dept;
    @TableField(exist = false)
    @ExcelProperty("dt")
    private int dt;
    @TableField(exist = false)
    private  int num;

}
