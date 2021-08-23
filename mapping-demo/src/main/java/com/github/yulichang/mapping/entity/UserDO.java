package com.github.yulichang.mapping.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yulichang.annotation.EntityMapping;
import com.github.yulichang.annotation.FieldMapping;
import com.github.yulichang.mapping.enums.Sex;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@ToString
@Accessors(chain = true)
@TableName("user")
public class UserDO {

    @TableId
    private Integer id;
    private Integer pid;
    private String name;
    private Sex sex;
    private String headImg;
    @TableLogic
    private Boolean del;

//    /**
//     * 查询上级 一对一
//     */
//    @TableField(exist = false)
//    @EntityMapping(thisField = "pid", joinField = "id")
//    private UserDO pUser;
//
//    /**
//     * 查询下级 一对多
//     */
//    @TableField(exist = false)
//    @EntityMapping(thisField = "id", joinField = "pid", select = "id,name")
//    private List<UserDO> childUser;
//
//    /**
//     * 查询下级 一对多
//     */
//    @TableField(exist = false)
//    @FieldMapping(tag = UserDO.class, thisField = "id", joinField = "pid", select = "id")
//    private List<Integer> childIds;

    /**
     * 查询上级 一对一
     */
    @TableField(exist = false)
    @EntityMapping(tag = UserDO.class, thisField = "pid", joinField = "id", thisMapKey = "PID", joinMapKey = "ID")
    private Map<String, Object> pUser;

    /**
     * 查询下级 一对多
     */
    @TableField(exist = false)
    @EntityMapping(tag = UserDO.class, thisField = "id", joinField = "pid", select = "id,name", thisMapKey = "ID", joinMapKey = "PID")
    private List<Map<String, Object>> childUser;

    /**
     * 查询下级 一对多
     */
    @TableField(exist = false)
    @FieldMapping(tag = UserDO.class, thisField = "id", joinField = "pid", select = "id", thisMapKey = "ID", joinMapKey = "PID")
    private List<Integer> childIds;
}
