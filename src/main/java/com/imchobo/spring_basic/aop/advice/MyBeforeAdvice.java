package com.imchobo.spring_basic.aop.advice;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MyBeforeAdvice implements MethodBeforeAdvice {
  @Override
  public void before(Method method, Object[] args, Object target) throws  Throwable{
    System.out.println("나는 bofore");
  }
}
