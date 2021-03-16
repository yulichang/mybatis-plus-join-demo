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
@TableName("area")
public class AreaDO {

    @TableId
    private Integer id;

    private String province;

    private String city;

    private String area;

    private String postcode;

    @TableLogic
    private Boolean del;


    @Override
    public String toString() {
        return "AreaDO{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
