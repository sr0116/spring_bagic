package com.imchobo.spring_basic.repository;

import com.imchobo.spring_basic.domain.Member;
import com.imchobo.spring_basic.domain.Memo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;

@SpringBootTest
@Slf4j
public class MemoRepositoryTest {

  @Autowired
  private MemoRepository memoRepository;

  @Autowired
  private EntityManager entityManager;

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager() {
    log.info("{}", entityManager);

    entityManager.persist(new Memo());
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager2() {
//   Memo memo = memoRepository.findById(3L).orElseThrow(RuntimeException::new);
//   memo.setMemoText("Hello World");

    // JPA 는 dirty checking 을 통해 값 변경 감지

    Memo memo = entityManager.find(Memo.class, 1L); // 영속 객체
    memo.setMemoText("안녕");
  }

  @Test
  @Transactional
  @Rollback(false) // 원래는 기본적으로 true
  public void testEntityManager3() {
    Memo memo = new Memo();
    memo.setMno(3L);
    memo.setMemoText("비영속"); // 이 엔티티가  연결이 안 되어 있음

//    entityManager.persist(memo); // 오류남(내부적으로 pk값이 같을 경우가 있기 때문에 병합으로만(merge) 현재 가능 )
    entityManager.merge(memo);
  }


  @Test
  public void testExist() {
    log.info("{}", memoRepository);
  }


  @Test
  public void insertMemo() {
//    Memo memo = Memo.builder().memoText("테스트용 글쓰기")
//            .build();

    memoRepository.save(new Memo());

//    memoRepository.save(memo);
  }

  @Test
  public void findById() {
    Memo memo = memoRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 번호가 없습니다."));
    log.info("testFindById : {}", memo);
  }

  @Test
  public void findByAll() {
    memoRepository.findAll().forEach(m -> log.info("{}", m));
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testUpdate() {
    Memo memo = memoRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 번호가 없습니다."));
    memo.setMemoText("추가 수정");
  }

  @Test
  public void testDelete() {
    Memo memo = entityManager.find(Memo.class, 5L);
//    memoRepository.deleteById(memo);
    memoRepository.delete(memo);
  }


}
