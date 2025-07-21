package com.imchobo.spring_basic.service;

import com.imchobo.spring_basic.domain.Member;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mem1")
@ToString
//@AllArgsConstructor// 유일 생성자 주입
@RequiredArgsConstructor // 필요한 생성자 주입
public class MemberServiceImpl implements MemberService {
  @NonNull // 이상태에서 null 강제 생성하면 터짐
//  private  Member member; // 2. @NonNull // 반드시 필요한 애들은 한다@RequiredArgsConstructor // 필요한 생성자 주입
//  private final Member member; //3. final 상수로 만들고 반드시 필요한 애들은 한다@RequiredArgsConstructor // 필요한 생성자 주입

////  @Autowired// 1. 생성자 주입(가장 추천하는 주입 방법) // 단 생성자가 하나라면 자동 주입기 가능해짐(유일 생성자일시 가능)
//  public  MemberServiceImpl(Member member){ 
//    this.member = member;
//  } // 이렇게 주석으로 생성자 주입 안 하면 null 이 나옴 // 테스트할 때 터지는 것은 못 찾아서 오류나는 것임 // 생성자가 없다고 해서 터지지 않음
//
  @Override
  public void register(Member member) {

  }

}
