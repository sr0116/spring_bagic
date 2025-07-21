package com.imchobo.spring_basic.service;

import com.imchobo.spring_basic.domain.Member;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service// 빈으로 등록 시키는 과정이라 생각하면 됨
//@Setter
//@Setter
//@ToString
//@Data
public interface MemberService {
void register(Member member);
  // 주입 방식은 총 3가지 있음
  // 2. @Autowired (필드 주입, 권장은 하지 않지만 실행은 시켜줌, (스프링은 주로) 컴파일 시점에서 하게 됨, 먼저 코드를 보고 오류가 날지 안날지 확인을 하게 됨(리플랙션은 먼저 실행 해보고 오류가 날지 안 날지 확인을 하고 알려주는 것임))
  // 필드 초기화를 하는 방법 : 상속(CGLIP: 코드제너레이터라이브러리)
//  @Autowired
//  private Member member; // 이 클래스를 사용하기 위해서 bean 등록 해야 됨
  // 객체 초기화는 필드 초기화라 하면 됨

//3 @Autowired
//  public void setMember(Member member) {
//    this.member = member; // //@Setter Injection그냥 만들어준거
//  }
}
