package com.example.mpp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpp.dto.UserDTO;
import com.example.mpp.entity.AreaDO;
import com.example.mpp.entity.UserAddressDO;
import com.example.mpp.entity.UserDO;
import com.example.mpp.mapper.UserMapper;
import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJJoinLambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 连表测试类
 */
@SpringBootTest
class MpJoinTest {
    @Resource
    private UserMapper userMapper;

    /**
     * 分页查询
     */
    @Test
    void joinTest() {
        IPage<UserDTO> iPage = userMapper.selectJoinPage(new Page<>(1, 10).setOptimizeCountSql(false), UserDTO.class,
                new MPJLambdaQueryWrapper<UserDO>()
                        .selectAll(UserDO.class)
                        .select("addr.tel", "addr.address", "a.province", "a.city", "a.area")
                        .leftJoin("user_address addr on t.id = addr.user_id")
                        .leftJoin("area a on a.id = addr.area_id")
                        .stringQuery()
                        .eq("a.id", "1"));
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * 普通查询
     */
    @Test
    void test1() {
        List<UserDTO> list = userMapper.selectJoinList(UserDTO.class,
                new MPJLambdaQueryWrapper<UserDO>()
                        .selectAll(UserDO.class)
                        .select("addr.tel", "addr.address", "a.province")
                        .leftJoin("user_address addr on t.id = addr.user_id")
                        .rightJoin("area a on addr.area_id = a.id")
                        .gt(true, UserDO::getId, 1)
                        .eq(true, UserDO::getSex, "男")
                        .stringQuery()
                        .like(true, "addr.tel", "1")
                        .le(true, "a.province", "1"));

        list.forEach(System.out::println);
    }

    /**
     * leftJoin支持条件,false是不会添加连表查询
     */
    @Test
    void test2() {
        IPage<UserDTO> iPage = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                new MPJJoinLambdaQueryWrapper<UserDO>()
                        .selectAll(UserDO.class)
//                        .select(UserAddressDO::getTel)
//                        .selectAs(UserAddressDO::getAddress, UserDTO::getUserAddress)
//                        .select(AreaDO::getProvince, AreaDO::getCity)
                        .leftJoin(false, UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .leftJoin(false, AreaDO.class, AreaDO::getId, UserAddressDO::getAreaId));

        iPage.getRecords().forEach(System.out::println);
    }

    @Test
    void tt() {
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                new MPJJoinLambdaQueryWrapper<>()
                        .selectAll(UserDO.class)
                        .select(UserAddressDO::getAddress)
                        .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .eq(UserDO::getId, 1));
    }

}
