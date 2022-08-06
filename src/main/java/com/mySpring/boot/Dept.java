package com.mySpring.boot;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@TableName("oa_dept")
public class Dept {
  @TableId(type = IdType.AUTO)
  @Min(value=1,message = "id不能为空",groups =AddGroup.class)
  private int id;
  @TableField("name")
  @NotBlank(message = "部门名字不能为空",groups = AddGroup.class)
  private String name;

}
