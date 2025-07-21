package com.imchobo.spring_basic.repository;

import com.imchobo.spring_basic.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;

  @Test
  public void testExist() {
    log.info("testExist : {}", memberRepository);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testInsert(){
    Member member = Member.builder()
            .no(4L)
            .id("sae").pw("1234").age(20).name("새똥이")
            .build();
    memberRepository.save(member); //
  }
// findBy - > 옵셔널 타입
  // 단일 조회
  @Test
  public void testFindById(){
    Member member =  memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 회원 번호가 없습니다."));
//    Member member1 = memberRepository.findBy(Example.of(Member.builder().build()));
    log.info("testFindById : {}", member);
    // 먼저 select 함
    // pk를 찾으러 감
  }

  @Test
  public void testFindAll(){
    memberRepository.findAll().forEach(m -> log.info("{}", m));
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testUpdate(){
    Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 회원 번호가 없습니다."));
    member.setAge(30);
//    memberRepository.save(member);
  }

  @Test
  public void testDelete(){
//    memberRepository.deleteById(2L);
  }


}
