package com.github.yulichang.join.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.join.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends MPJBaseMapper<UserDO> {

}
