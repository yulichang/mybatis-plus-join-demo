package com.github.yulichang.join;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.join.dto.UserDTO;
import com.github.yulichang.join.entity.AreaDO;
import com.github.yulichang.join.entity.UserAddressDO;
import com.github.yulichang.join.entity.UserDO;
import com.github.yulichang.join.mapper.UserMapper;
import com.github.yulichang.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
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
     * 简单的关联查询 String
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
     * 简单的分页关联查询 lambda
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

    /**
     * 简单的分页关联查询 lambda
     * ON语句多条件
     */
    @Test
    void test3() {
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                Wrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .select(UserAddressDO::getAddress)
                        .leftJoin(UserAddressDO.class, on -> on
                                .eq(UserDO::getId, UserAddressDO::getUserId)
                                .eq(UserDO::getId, UserAddressDO::getUserId))
                        .eq(UserDO::getId, 1)
                        .and(i -> i.eq(UserDO::getHeadImg, "er")
                                .or()
                                .eq(UserAddressDO::getUserId, 1))
                        .eq(UserDO::getId, 1));
        page.getRecords().forEach(System.out::println);
    }

    /**
     * 简单的函数使用
     */
    @Test
    void test4() {
        UserDTO one = userMapper.selectJoinOne(UserDTO.class, Wrappers.<UserDO>lambdaJoin()
                .selectSum(UserDO::getId)
                .selectMax(UserDO::getId, UserDTO::getHeadImg)
//                .selectFunc(FuncEnum.IF_SEX, UserDO::getSex, UserDO::getName)
                .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                .eq(UserDO::getId, 1));
        System.out.println(one);
    }


    /**
     * 忽略个别查询字段
     */
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


    /**
     * 关联查询返回map
     */
    @Test
    void test7() {
        List<Map<String, Object>> list = userMapper.selectJoinMaps(Wrappers.<UserDO>lambdaJoin()
                .selectAll(UserDO.class)
                .select(UserAddressDO::getAddress)
                .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                .eq(UserDO::getId, 1));
        list.forEach(System.out::println);
    }


    /**
     * 子查询
     */
    @Test
    void test8() {
        List<UserDTO> list = userMapper.selectJoinList(UserDTO.class,
                Wrappers.<UserDO>lambdaJoin()
                        .selectQuery(UserAddressDO.class, q -> q
                                        .select(UserAddressDO::getAddress)
                                        .eq(UserDO::getId, UserAddressDO::getUserId),
                                UserDTO::getAddress)
                        .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .leftJoin(AreaDO.class, AreaDO::getId, UserAddressDO::getAreaId)
                        .eq(UserDO::getId, UserDO::getId));
        list.forEach(System.out::println);
    }

}
