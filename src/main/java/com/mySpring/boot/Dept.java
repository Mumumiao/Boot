package com.mySpring.boot;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("oa_dept")
public class Dept {
  @TableId(type = IdType.AUTO)
  private int id;
  @TableField("name")
  private String name;

}
