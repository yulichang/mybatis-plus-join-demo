package com.github.yulichang.join.service;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.join.entity.UserDO;
import com.github.yulichang.join.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, UserDO> implements UserService {
}
