package com.imchobo.spring_basic.main;

import com.imchobo.spring_basic.domain.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberMain2 {
  public static void main(String[] args) {
   ApplicationContext context = new ClassPathXmlApplicationContext("xml/bean-config-java.xml");
    Member m = context.getBean("member", Member.class);
    Member m2 = context.getBean("member", Member.class);
    System.out.println(m == m2);// 같다고 나옴(싱글톤) 만약 서로 다른 객체를 사용해야 되는거면
  }
}
