package com.github.yulichang.join;

import com.github.yulichang.join.entity.JoinTable;
import com.github.yulichang.join.entity.MainTable;
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
        MPJLambdaWrapper<MainTable> wrapper = new MPJLambdaWrapper<MainTable>()
                .selectAll(MainTable.class)
                .leftJoin(JoinTable.class, JoinTable::getMainId, MainTable::getId);

        List<MainTable> list = mainTableMapper.selectJoinList(MainTable.class, wrapper);
        list.forEach(System.out::println);
        list.forEach(f -> System.out.println(f.getId()));
        list.forEach(f -> System.out.println(f.getContactPhone()));
    }
}
