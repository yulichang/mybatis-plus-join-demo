package com.github.yulichang.join.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yulichang.annotation.Table;
import lombok.Data;
import lombok.ToString;

@Table(value = "Ognl#util.replaceSuffix(className, 'DO', 'APT').toUpperCase()", classPackage = "%s.apt",cache = false)
@Data
@ToString
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
}
