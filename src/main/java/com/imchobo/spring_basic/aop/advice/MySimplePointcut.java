package com.imchobo.spring_basic.aop.advice;

import com.imchobo.spring_basic.service.FirstService;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MySimplePointcut  extends StaticMethodMatcherPointcut  implements Pointcut {
  //StaticMethodMatcherPointcut 이게 제일 간단함

  @Override
  public boolean matches(Method method, Class<?> targetClass) { // Class<?> targetClass) boolean , 조건식을 만들어야 함
   // 매개 변수 개수가 1개 그리고 리턴 타입이 void
//    return  method.getReturnType() == void.class && method.getParameterCount() == 1 ;
//    System.out.println(method.getName());
//    System.out.println(method.getReturnType());
//    System.out.println(method.getParameterCount());
//    System.out.println(method.getAnnotatedReturnType());
//    System.out.println(method.getModifiers());
      return method.getName().equals("two") && targetClass == FirstService.class;

  }
}
