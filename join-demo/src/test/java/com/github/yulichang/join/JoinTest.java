package com.github.yulichang.join;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.join.dto.UserDTO;
import com.github.yulichang.join.entity.AddressDO;
import com.github.yulichang.join.entity.AreaDO;
import com.github.yulichang.join.entity.UserDO;
import com.github.yulichang.join.mapper.UserMapper;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 连表测试类
 * <p>
 * 支持mybatis-plus 查询枚举字段
 * 支持mybatis-plus typeHandle功能
 * <p>
 * 移除了mybatis-plus 逻辑删除支持，逻辑删除需要在连表查询时自己添加对应的条件
 */
@SpringBootTest
class JoinTest {
    @Resource
    private UserMapper userMapper;

    /**
     * 一对多
     */
    @Test
    void testJoin() {
        MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
                .selectAll(UserDO.class)
                .selectCollection(AddressDO.class, UserDTO::getAddressList)
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                .orderByDesc(UserDO::getId);

        List<UserDTO> list = userMapper.selectJoinList(UserDTO.class, wrapper);
        list.forEach(System.out::println);
    }


    /**
     * 简单的分页关联查询 lambda
     */
    @Test
    void test1() {
        IPage<UserDTO> iPage = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                MPJWrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .select(AddressDO::getAddress)
                        .select(AreaDO::getProvince)
                        .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                        .leftJoin(AreaDO.class, AreaDO::getId, AddressDO::getAreaId));
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * 简单的关联查询 String
     */
    @Test
    void test2() {
        MPJQueryWrapper<UserDO> wrapper = MPJWrappers.<UserDO>queryJoin()
                .selectAll(UserDO.class)
                .selectAll(AddressDO.class, "addr")
                .selectAll(AreaDO.class, "a")
                .selectIgnore("addr.id", "a.id", "t.del", "addr.del")
                .leftJoin("user_address addr on t.id = addr.user_id")
                .leftJoin("area a on a.id = addr.area_id")
                .eq("t.id", 1);

        List<UserDO> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }


    /**
     * 简单的分页关联查询 lambda
     * ON语句多条件
     */
    @Test
    void test3() {
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                MPJWrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .select(AddressDO::getAddress)
                        .leftJoin(AddressDO.class, on -> on
                                .eq(UserDO::getId, AddressDO::getUserId)
                                .eq(UserDO::getId, AddressDO::getUserId))
                        .eq(UserDO::getId, 1)
                        .and(i -> i.eq(UserDO::getHeadImg, "er")
                                .or()
                                .eq(AddressDO::getUserId, 1))
                        .eq(UserDO::getId, 1));
        page.getRecords().forEach(System.out::println);
    }

    /**
     * 简单的函数使用
     */
    @Test
    void test4() {
        UserDTO one = userMapper.selectJoinOne(UserDTO.class, MPJWrappers.<UserDO>lambdaJoin()
                .selectSum(UserDO::getId)
                .selectMax(UserDO::getId, UserDTO::getHeadImg)
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                .eq(UserDO::getId, 1));
        System.out.println(one);
    }


    /**
     * 忽略个别查询字段
     */
    @Test
    void test6() {
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                MPJWrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .select(AddressDO.class, p -> true)
                        .select(AddressDO::getAddress)
                        .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                        .eq(UserDO::getId, 1));
        page.getRecords().forEach(System.out::println);
    }


    /**
     * 关联查询返回map
     */
    @Test
    void test7() {
        List<Map<String, Object>> list = userMapper.selectJoinMaps(MPJWrappers.<UserDO>lambdaJoin()
                .selectAll(UserDO.class)
                .select(AddressDO::getAddress)
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                .eq(UserDO::getId, 1));
        list.forEach(System.out::println);
    }
}
