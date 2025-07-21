package com.imchobo.spring_basic.config;

import com.imchobo.spring_basic.domain.Member;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy
@Configuration// 이 어노테이션으로 이게 config파일이라고 정의 해두는 것
public class AppConfig {

//  @Bean
//  public Member member() {
//    return new Member("so-ddong", 22);
//  }
//
//  @Bean
//  @ConfigurationProperties("spring.datasource.hikari")
//  public HikariConfig hikariConfig() {
//
//    return new HikariConfig();
//  }
//
//  @Bean
//  public HikariDataSource hikariDataSource() {
//    return new HikariDataSource(hikariConfig());
//  }
}

