package com.github.yulichang.join.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yulichang.join.enums.Sex;
import lombok.Data;
import lombok.ToString;

/**
 * 用户表
 */
@Data
@ToString
@TableName("`user`")
public class UserDO {

    @TableId
    private Integer id;

    private String name;

    private Sex sex;

    private String headImg;

    private Integer addressId;

    @TableLogic
    private Boolean del;
}
