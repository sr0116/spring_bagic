package com.imchobo.spring_basic.aop.advice;


import com.imchobo.spring_basic.service.FirstService;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
// 포인트 컷 두개면 터짐
//@Component
//public class MyPointCut extends StaticMethodMatcherPointcut implements Pointcut {
//
//  @Override
//  public boolean matches(Method method, Class<?> targetClass) {
////    System.out.println(method.getName());
////    System.out.println(method.getReturnType());
////    System.out.println(method.getParameterCount());
////    System.out.println(method.getAnnotatedReturnType());
////    System.out.println(method.getModifiers());
//    return method.getName().equals("two") && targetClass == FirstService.class;
//  }
//}

