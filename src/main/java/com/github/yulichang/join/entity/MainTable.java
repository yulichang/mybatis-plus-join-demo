package com.github.yulichang.join.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("main_table")
public class MainTable {

    @TableId(value = "main_id", type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    @TableField("phone")
    private String contactPhone;
}
