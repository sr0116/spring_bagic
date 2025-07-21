package com.imchobo.spring_basic.service;

import com.imchobo.spring_basic.domain.Member;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mem2")
@ToString
public class MemberServiceImpl2 implements MemberService {


  @Override
  public void register(Member member) {

  }

}
