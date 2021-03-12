package com.example.mpp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.mpp.dto.UserDTO;
import com.example.mpp.entity.UserDO;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends MPJBaseMapper<UserDO> {
    /**
     * 不使用别名
     */
    @Select("select user.*,user_address.tel from user left join user_address on user.id = user_address.user_id ${ew.customSqlSegment}")
    List<UserDTO> joinTest(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

    /**
     * 使用别名
     */
    @Select("select u.*,ua.tel from user u left join user_address ua on u.id = ua.user_id ${ew.customSqlSegment}")
    List<UserDTO> joinTestAlias(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);

    @Select("select a.*,b.tel from user a left join user_address b on a.id = b.user_id ${ew.customSqlSegment}")
    List<UserDTO> joinTestAliasS(@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);
}
