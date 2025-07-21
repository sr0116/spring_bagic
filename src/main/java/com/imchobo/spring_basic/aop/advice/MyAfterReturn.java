package com.imchobo.spring_basic.aop.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MyAfterReturn implements AfterReturningAdvice {
  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
    System.out.println("나는 afterReturning 이야");
  }
}
