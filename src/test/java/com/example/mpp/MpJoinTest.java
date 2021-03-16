package com.example.mpp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpp.dto.UserDTO;
import com.example.mpp.entity.AreaDO;
import com.example.mpp.entity.UserAddressDO;
import com.example.mpp.entity.UserDO;
import com.example.mpp.mapper.UserMapper;
import com.github.yulichang.common.JoinLambdaWrapper;
import com.github.yulichang.common.support.alias.AliasLambdaQueryWrapper;
import com.github.yulichang.common.support.alias.AliasQueryWrapper;
import com.github.yulichang.common.support.func.S;
import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.query.MPJQueryWrapper;
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
     * @see MPJLambdaQueryWrapper
     */
    @Test
    void test1() {
        IPage<UserDTO> iPage = userMapper.selectJoinPage(new Page<>(1, 10).setOptimizeCountSql(false),
                UserDTO.class,
                new MPJQueryWrapper<UserDO>()
                        .selectAll(UserDO.class)
                        .select("addr.tel", "addr.address", "a.province", "a.city", "a.area")
                        .leftJoin("user_address addr on t.id = addr.user_id")
                        .leftJoin("area a on a.id = addr.area_id")
                        .eq("t.id", 1));
    }

    /**
     * @see MPJLambdaQueryWrapper
     */
    @Test
    void test2() {
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
    }

    /**
     * @see MPJJoinLambdaQueryWrapper
     */
    @Test
    void test3() {
        IPage<UserDTO> iPage = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                new MPJJoinLambdaQueryWrapper<UserDO>()
                        .selectAll(UserDO.class)
                        .leftJoin(false, UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .leftJoin(false, AreaDO.class, AreaDO::getId, UserAddressDO::getAreaId));
    }

    @Test
    void test4() {
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                new MPJJoinLambdaQueryWrapper<UserDO>()
                        .selectAll(UserDO.class)
                        .select(UserAddressDO::getAddress)
                        .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .eq(UserDO::getId, 1));
    }


    @Test
    void test5() {
        List<UserDTO> userDTO = userMapper.joinTest(new JoinLambdaWrapper<>()
                .eq(UserDO::getId, "1")
                .eq(UserAddressDO::getUserId, "1"));
    }

    @Test
    void test6() {
        List<UserDTO> userDTO = userMapper.joinTestAlias(new AliasQueryWrapper<>()
                .setAlias("u")
                .eq("id", "1")//u.id
                .like("sex", "3")//u.sex
                .eq("ua.tel", "10086")
                .like("ua.address", "北京"));
    }

    @Test
    void test7() {
        List<UserDTO> dto = userMapper.joinTestAliasF(new QueryWrapper<UserDO>()
                .eq(S.a(UserDO::getId), "1")//a.id
                .gt(S.a(UserDO::getSex), "3")//a.sex
                .eq(S.b(UserAddressDO::getTel), "10086")//b.tel
                .like(S.b(UserAddressDO::getAddress), "北京"));//b.address
    }

    @Test
    void test8() {
        List<UserDTO> dto = userMapper.joinTestAliasS(new AliasLambdaQueryWrapper<UserDO>()
                .eq(UserDO::getId, "1")//a.id
                .gt(UserDO::getSex, "3"));//a.sex
    }
}
