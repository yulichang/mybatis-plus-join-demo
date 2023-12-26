package com.github.yulichang.join.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("join_table")
public class JoinTable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String mainId;
}
