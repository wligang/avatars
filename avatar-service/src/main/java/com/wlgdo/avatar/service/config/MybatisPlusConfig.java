package com.wlgdo.avatar.service.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/10 18:23
 */
@Configuration
@MapperScan("com.wlgdo.avatar.service")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
