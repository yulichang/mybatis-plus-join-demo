package com.example.mpp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpp.dto.UserDTO;
import com.example.mpp.entity.AreaDO;
import com.example.mpp.entity.UserAddressDO;
import com.example.mpp.entity.UserDO;
import com.example.mpp.mapper.UserMapper;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
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
     * @see MPJQueryWrapper
     */
    @Test
    void test1() {
        IPage<UserDTO> iPage = userMapper.selectJoinPage(new Page<>(1, 10).setOptimizeCountSql(false),
                UserDTO.class, Wrappers.<UserDO>queryJoin()
                        .selectAll(UserDO.class)
                        .selectAll(UserAddressDO.class, "addr")
                        .selectAll(AreaDO.class, "a")
                        .selectIgnore("addr.id", "a.id", "t.del", "addr.del")
                        .leftJoin("user_address addr on t.id = addr.user_id")
                        .leftJoin("area a on a.id = addr.area_id")
                        .eq("t.id", 1));
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * @see MPJLambdaWrapper
     */
    @Test
    void test2() {
        IPage<UserDTO> iPage = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                Wrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .leftJoin(AreaDO.class, AreaDO::getId, UserAddressDO::getAreaId));
        iPage.getRecords().forEach(System.out::println);
    }

    @Test
    void test3() {
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                Wrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .select(UserAddressDO::getAddress)
                        .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .eq(UserDO::getId, 1)
                        .and(i -> i.eq(UserDO::getHeadImg, "er")
                                .or()
                                .eq(UserAddressDO::getUserId, 1)));
        page.getRecords().forEach(System.out::println);
    }


    @Test
    void test6() {
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                Wrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .selectAll(UserAddressDO.class)
                        .selectIgnore(UserDO::getId)
                        .selectIgnore(UserAddressDO::getId)
                        .select(UserAddressDO::getAddress)
                        .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .eq(UserDO::getId, 1));
        page.getRecords().forEach(System.out::println);
    }


    @Test
    void test7() {
        List<UserDTO> dtos = userMapper.selectJoinList(UserDTO.class,
                Wrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .select(UserAddressDO::getAddress)
                        .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .eq(UserDO::getId, 1));
        dtos.forEach(System.out::println);
    }

}
