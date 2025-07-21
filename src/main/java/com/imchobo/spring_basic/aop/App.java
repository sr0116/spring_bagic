package com.imchobo.spring_basic.aop;

import java.lang.reflect.Proxy;

public class App {
  public static void main(String[] args) {
    BoardService target = new BoardServiceImpl();
    System.out.println("===============target의 결과물===================");
    target.write("제목", "내용");

    BoardService proxy = (BoardService) Proxy.newProxyInstance(
            BoardService.class.getClassLoader(),
            new Class[]{BoardService.class},
            new LoggingInvicationHandler(target) // 스프링으로 하게 되면 이 과정을 줄일 수 있게 됨 이게 @Before 와 같은 어노테이션
    );

    System.out.println("===============proxy의 결과물=============");
    proxy.write("title", "content"); //class가 들어가게 되면 에러 터짐

  }
}
