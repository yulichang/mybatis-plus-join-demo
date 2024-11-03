package com.github.yulichang.join.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("user_address")
public class AddressDO {

    @TableId
    private Integer id;

    /**
     * 用户id
     * sdaflkj
     * 而的风景
     */
    private Integer userId;

    private Integer areaId;

    private String tel;

    private String address;

    @TableLogic
    private Boolean del;
}
