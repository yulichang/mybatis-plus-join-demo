package com.github.yulichang.join;

import com.github.yulichang.extension.apt.AptQueryWrapper;
import com.github.yulichang.extension.apt.toolkit.AptWrappers;
import com.github.yulichang.join.entity.JoinTableDO;
import com.github.yulichang.join.entity.MainTableDO;
import com.github.yulichang.join.entity.UserDO;
import com.github.yulichang.join.entity.apt.*;
import com.github.yulichang.join.mapper.MainTableMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AliasTest {

    @Autowired
    private MainTableMapper mainTableMapper;

    /**
     * 一对多
     */
    @Test
    void testJoin() {
        MPJLambdaWrapper<MainTableDO> wrapper = new MPJLambdaWrapper<MainTableDO>()
                .selectAll(MainTableDO.class)
                .leftJoin(JoinTableDO.class, JoinTableDO::getMainId, MainTableDO::getId);

        List<MainTableDO> list = mainTableMapper.selectJoinList(MainTableDO.class, wrapper);
        list.forEach(System.out::println);
        list.forEach(f -> System.out.println(f.getId()));
        list.forEach(f -> System.out.println(f.getContactPhone()));
    }

    @Test
    void test() {
        Address.build("asdf");
        AREAAPT.build("asdf");
        JoinTable.build("asdf");
        MainTable.build("asdf");
        User.build("asdf");

        System.out.println(1);

        User u = new User();
        Address addr = new Address("addr");

        AptQueryWrapper<UserDO> apt = AptWrappers.query(u)
                .select(u.id)
                .leftJoin(addr, addr.userId, u.id)
                .ge(u.id, 0);

        apt.list();
    }
}
