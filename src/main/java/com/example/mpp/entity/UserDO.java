package com.example.mpp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mpp.enums.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@TableName("user")
public class UserDO {

    @TableId
    private Integer id;

    @TableField("name")
    private String nameName;

    private Sex sex;

    private String headImg;

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", name='" + nameName + '\'' +
                ", sex='" + sex + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }
}
