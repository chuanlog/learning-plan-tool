package com.scut;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 后端项目启动器
 */
@SpringBootApplication
@MapperScan("com.scut.mapper")
@EnableConfigurationProperties// 开启配置绑定
@EnableTransactionManagement// 开启事务管理
@Slf4j
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        log.info("服务成功启动!");
    }
}
