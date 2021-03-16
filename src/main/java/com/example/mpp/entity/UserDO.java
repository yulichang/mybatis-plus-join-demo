package com.example.mpp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mpp.enums.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class UserDO {

    @TableId
    private Integer id;

    /**
     * 别名测试
     */
    @TableField("name")
    private String nameName;

    private Sex sex;

    private String headImg;

    @TableLogic
    private Boolean del;

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
