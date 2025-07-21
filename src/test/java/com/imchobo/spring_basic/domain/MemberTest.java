package com.imchobo.spring_basic.domain;

import com.imchobo.spring_basic.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
//@Runwith(JUnit5.class)
@Slf4j
//@ContextConfiguration(locations = "classpath:xml/bean-config.xml") // 특정 위치를 찾아주는 (상세 호출을 할 때는)
@ContextConfiguration(classes = AppConfig.class)// 경로
public class MemberTest {
  @Autowired// (멤버) 객체가 있는지 타입으로 찾는 것
  Member member;

  @Test
  public void  testExist(){
//    System.out.println(member);
    log.info("{}",member);
  }

}
