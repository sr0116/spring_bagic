package com.imchobo.spring_basic.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MemberServiceTest {

  @Autowired
  @Qualifier("mem1") // 이름 정의 해두면 mem1로 찾아감
  private  MemberService memberService; // 특정 시키는 것 : MemberServiceImpl

  @Test
  public void testExist(){
    log.info("{}", memberService);
  }
}
