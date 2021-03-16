package com.example.mpp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_address")
public class UserAddressDO {

    @TableId
    private Integer id;

    private Integer userId;

    private Integer areaId;

    private String tel;

    private String address;

    @TableLogic
    private Boolean del;

    @Override
    public String toString() {
        return "UserAddressDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", areaId=" + areaId +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
