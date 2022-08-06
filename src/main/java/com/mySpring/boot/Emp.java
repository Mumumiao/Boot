package com.mySpring.boot;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Setter
@Getter
@TableName("oa_emp")
@EqualsAndHashCode
public class Emp {
    @TableId(type = IdType.AUTO)
    @ExcelProperty("id")
    @Min(value=1,message = "id不能为空")
    private int id;
    @TableField("name")
    @ExcelProperty("name")
    @NotBlank(message = "员工名字不能为空",groups = AddGroup.class)
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
    @DecimalMin("0")
    private BigDecimal salary;
    @TableField(value = "dept_id",property ="dt")
    private Dept dept;
    @TableField(exist = false)
    @ExcelProperty("dt")
    @Min(value=1,message = "部门不能为空",groups = AddGroup.class)
    private int dt;
    @TableField(exist = false)
    private  int num;

}
