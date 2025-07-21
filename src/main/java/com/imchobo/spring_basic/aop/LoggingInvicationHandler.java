package com.imchobo.spring_basic.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvicationHandler implements InvocationHandler {

  private Object target;

  public LoggingInvicationHandler (Object target) {
    this.target = target;
  }

  public  Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
    System.out.println("로그 호출 전" + method.getName());
    Object o = method.invoke(target, args);
    System.out.println("로그 호출 후" + method.getName()); //
    return o;
  }
}
