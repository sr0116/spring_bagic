package com.imchobo.spring_basic.main;

import com.imchobo.spring_basic.domain.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberMain {
  public static void main(String[] args) {
   ApplicationContext context = new ClassPathXmlApplicationContext("xml/bean-config.xml");
    Member m = context.getBean("member", Member.class);
    Member m2 = context.getBean("member", Member.class);
    System.out.println(m);
    System.out.println(m2);
    System.out.println(m == m2); // scope 프로토 타입일시 같은 값이더라도 다른 객체 (기본 타입은 싱글톤)
  }
}
