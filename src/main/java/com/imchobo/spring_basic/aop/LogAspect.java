package com.imchobo.spring_basic.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
  // after, before 는 무조건 첫번째 파라미터...., 조인 포인터
  @Before(value = "execution(* com..*.get(..))")// 이름이 get인 모든 매서드
  public  void before(JoinPoint joinPoint){
    System.out.println(joinPoint.getSignature().getName() + "before 처리"); // 시그니처는 매서드 생각하면됨 타켓은 매서드가 선언되었던 클래스명
  }
  // around 는 리턴 타입 필요
  @Around("execution(* com..*.get(Long))")
  public  Object aroud(ProceedingJoinPoint joinPoint) throws Throwable{
    System.out.println("around's before");
    Object ret = joinPoint.proceed();
    System.out.println("around's after");
    return  ret;
  }
  @After("bean(boardServiceImpl)")
  public void after(JoinPoint joinPoint){
    System.out.println("after finally"); // 실패하든 안 하든 전부 실행
  }

  @AfterReturning("args(String, String) && execution(* *..BoardServiceImpl.*(..))") // 패런트가 라이트인것만? 조건식
  public void AfterReturn(JoinPoint joinPoint){
    System.out.println("정상 종료 AfterReturning "); // 이 안에서 조건식을 사용할 수도 있음

  }
}
