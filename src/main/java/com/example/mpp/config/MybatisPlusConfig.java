package com.example.mpp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.yulichang.injector.MPJSqlInjector;
import com.github.yulichang.interceptor.MPJInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 *
 * @author yulichang
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 启用mybatis-plus分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //连表插件
        interceptor.addInnerInterceptor(new MPJInterceptor());
        return interceptor;
    }

    @Bean
    public MPJSqlInjector mySqlInjector() {
        return new MPJSqlInjector();
    }
}
