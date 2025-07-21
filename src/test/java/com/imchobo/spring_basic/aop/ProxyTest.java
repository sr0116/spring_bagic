package com.imchobo.spring_basic.aop;


import com.imchobo.spring_basic.aop.advice.MyBeforeAdvice;
import com.imchobo.spring_basic.aop.advice.MyAroundAdvice;
import com.imchobo.spring_basic.service.FirstService;
import com.imchobo.spring_basic.service.SecondService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.aop.*;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@SpringBootTest
@Slf4j
public class ProxyTest {
  @Autowired
  private BoardService boardService;

  @Autowired
  private MyAroundAdvice advice;
  @Autowired
  private MyBeforeAdvice before;
  @Autowired
  private AfterReturningAdvice afterReturn;
  @Autowired
  private ThrowsAdvice throwsAdvice;
  @Autowired
  private Pointcut pointcut;

  @Autowired
  private FirstService firstService;

  @Autowired
  private SecondService secondService;


  private BoardService proxy;
  @Autowired
  private BeforeAdvice beforeAdvice;


  @PostConstruct // 먼저 이렇게 proxy를 정의 해줘서 밑에서 테스트 코드에서 일일히 전부 작성할 필요 없이 proxy로 적어도 이 메서드 실행
  public void init() {
    Advice[] advices = new Advice[]{afterReturn, throwsAdvice};
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    for (Advice a : advices) {
      proxyFactory.addAdvice(a);
    }
    proxy = (BoardService) proxyFactory.getProxy();
  }


  @Test
  public void testExist() {
    log.info("{}", boardService);
  }

  @Test
  public void testWrite() {
    boardService.write("원본 객체의 내용", "내용");

//    ProxyFactory proxyFactory = new ProxyFactory(); 이거 주석해도 위에 정의해둬서 바로 가능
//    proxyFactory.setTarget(boardService);
//    proxyFactory.addAdvice(advice);
//    BoardService proxy = (BoardService) proxyFactory.getProxy(); //반환 타입은 오브젝트
    proxy.write("프록시 객체의 내용", "내용"); // 프록시 객체가 실행 하기 전에 다른 것들도 실행을 할 수도 있다
    // 한개의 타겟에 여러개의 프록시를 넣을 수 있음
    // 이걸 통해 세션 체크를 다 해줄 필요가 없어짐(장점이자 이걸 하는 이유)
  }

  @Test
  public void testBefore() { //  주로 "나는 비포야"에서 인증 체크를 함(조건에 따라) // 어드 바이스 5개를 어디에 적용 시켜야 되는지 생각해야 됨//
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    proxyFactory.addAdvice(advice);
    proxyFactory.addAdvice(before);
    BoardService proxy = (BoardService) proxyFactory.getProxy();
    proxy.write("프록시 객체의 내용", "내용");

  }

  // 1번 글일 때 예외 처리 발생 시키기
  @Test
  public void testReturn() {
    try {
      proxy.remove(1L); // 여기에 1번 넣어도 try 있으면 예외로 통과할 수 있음
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testAdvisor() {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);

//    Advisor advisor;
    PointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(pointcut, before);// 이게 제일 간단// 어드바이저는 기본적으로 포인트컷이 필요하고 만약 포인트컷을 지정하지 않을시 true가 기본 값으로 지정
    proxyFactory.addAdvisor(pointcutAdvisor);

    proxy = (BoardService) proxyFactory.getProxy();

    proxy.write("제목", "내용");
    proxy.get(3L);
    proxy.remove(4L);// 삭제 시에만 비포가 걸린다
  }


  @Test
  public void test() {
    MethodBeforeAdvice beforeAdvice = (method, args, target) -> System.out.println("익명");
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new StaticMethodMatcherPointcut() {
      @Override
      public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("two") && targetClass == FirstService.class;
      }
    }, beforeAdvice);
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(firstService);
    proxyFactory.addAdvisor(advisor);

    ProxyFactory proxyFactory2 = new ProxyFactory();
    proxyFactory2.setTarget(secondService);
    proxyFactory2.addAdvisor(advisor);

    FirstService proxy = (FirstService) proxyFactory.getProxy();
    proxy.one();
    proxy.two();

//    SecondService proxy2 = (SecondService) proxyFactory.getProxy();
//    proxy2.one();
//    proxy2.two(); // 두개면 터짐

  }

  @Test
  public void testAspectj(){
    AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
    pc.setExpression("execution(void *.write*(..))");// 표현식을 정의 해줘야 함(* 모든// 현재 이 식은 반환타임 클래스명, 매서드, ..은 파라미터 타입)

    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, before);

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);

    proxyFactory.addAdvisor(advisor);

    BoardService proxy = (BoardService) proxyFactory.getProxy() ;
    proxy.write("title", "content");
    proxy.get(3L);
  }
}
