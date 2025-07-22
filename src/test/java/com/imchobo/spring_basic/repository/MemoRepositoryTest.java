package com.imchobo.spring_basic.repository;

import com.imchobo.spring_basic.domain.Member;
import com.imchobo.spring_basic.domain.Memo;
import com.imchobo.spring_basic.domain.dto.MemoDTO;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.awt.print.Pageable;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

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
    memo.setMemoText("안녕수정");
  }

  @Test
  @Transactional
  @Rollback(false) // 원래는 기본적으로 true
  public void testEntityManager3() {
    Memo memo = new Memo();
    memo.setMno(3L);
    memo.setMemoText("비영속임"); // 이 엔티티가  DB연결이 안 되어 있음
// merge 같은게 영속성을 가지고 DB에 저장 시작
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
    Long mno = 100L;
    Optional<Memo> result = memoRepository.findById(mno);
    if (result.isPresent()) {
      Memo memo = result.get();
      log.info("testFindById : {}", memo);
    }
//    Memo memo = memoRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 번호가 없습니다."));
//    log.info("testFindById : {}", memo);
  }

  @Test
  public void findByAll() {
    memoRepository.findAll().forEach(m -> log.info("{}", m));
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testUpdate() {
    Memo memo = Memo.builder().mno(1L).memoText("수정").build();
    log.info("{}", memo);
//    Memo memo = memoRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 번호가 없습니다."));
//    memo.setMemoText("추가 수정");
  }

  @Test
  public void testDelete() {
//    IntStream.rangeClosed(1, 100).forEach(id -> { -> 100개 한번에 삭제(1부터 100까지)
//      memoRepository.deleteById((long) id);
//    });
    Long mno = 100L;
    memoRepository.deleteById(mno);
//    Memo memo = entityManager.find(Memo.class, 5L);
//    memoRepository.delete(memo);
  }

  // 교재 버전 테스트 작업(등록)
  @Test
  public void testInsertDummies() { // 100개 한 번에 등록
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Memo memo = Memo.builder().memoText("Sample" + i)
        .build();
      memoRepository.save(memo);
    });
  }

  // 교재 버전 테스트 작업 페이지 처리
  @Test
  public void testPageDefault() {
    PageRequest pageRequest = PageRequest.of(0, 5); // 처음 보여줄거, 몇 개 보여줄건지
    Page<Memo> result = memoRepository.findAll(pageRequest);
    result.forEach(r -> log.info("{}", r));

    long totalElements = result.getTotalElements(); // 총 개수
    int totalPages = result.getTotalPages();

    log.info("totalElements={}", totalElements);
    log.info("totalPages={}", totalPages);

    // 현재 페이지 번호
    log.info("getNumber={}", result.getNumber());
    // 페이지당 데이터 개수
    log.info("getSize={}", result.getSize());
    // 다음 페이지 여부
    log.info("hasPrevious={}", result.hasPrevious());
    log.info("hasNext={}", result.hasNext());
    //시작 페이지 여부
    log.info("isFirst={}", result.isFirst());
    log.info("isLast={}", result.isLast());

    result.getContent().forEach(c -> log.info("{}", c));
  }

  @Test
  public void testSort() { // 역순
    Sort sort = Sort.by(Sort.Direction.DESC, "mno");
    PageRequest pageRequest = PageRequest.of(0, 5, sort);
    Page<Memo> result = memoRepository.findAll(pageRequest);
    result.forEach(r -> log.info("{}", r));

    // EAGER, LAZY
  }

  @Test
  public void testQueryMethod() {
    memoRepository.findByMnoBetweenOrderByMnoDesc(190L, 200L).forEach(m -> log.info("{}", m));
  }

  @Test
  public void testQueryMethod2() {
    Page<Memo> memos = memoRepository
      .findByMnoBetween(190L, 200L, PageRequest.of(0, 10, Sort.Direction.DESC, "mno"));
    memos.forEach(m -> log.info("{}", m));
  }

  // memo 의 총 갯수를 로깅하게끔
  @Test
  @Commit
  public void testCount() {
//    log.info("{}",memoRepository.count()); 이것도 가능 (전체 개수 나옴)
//    Long mno = 130L;
//    memoRepository.countByMno(mno);
    //mno가 특정 long이거나 memoText가 특정 문자열일때의 query method
//  memoRepository.findByMnoOrMemoText(130L, "sample");
    log.info("{}", memoRepository.countByMno(140L)); // 1개 나옴(있는지 없는지 확인)
    memoRepository.findByMnoOrMemoText(130L, "sample52").forEach(m -> log.info("{}", m)); // Or 이거나라 2개 나옴
  }


  @Test
  public void testMemo() {
    //mno가 특정 long이거나 memoText가 특정 문자열일때의 query method
    memoRepository.findByMnoOrMemoText(130L, "sample");
    log.info("{}", memoRepository.findByMnoOrMemoText(130L, "sample42")); // 이거나 Or
  }

  @Test
  public void testMemoList() {
//    memoRepository.getListDesc();
    Long mno = 120L;
    log.info("{}", memoRepository.getListDesc());
    log.info("{}", memoRepository.getListDesc2());
  }

  @Test
  public void testUpdateMemo() {
//    Memo memo = memoRepository.findById(120L).orElseThrow();
//    memo.setMemoText("변경 내용 1");

    memoRepository.updateMemoText(130L, "변경 4");
  }

  @Test
  public void testUpdateMemo2() {
    memoRepository.updateMemoText2(Memo.builder().mno(140L).memoText("변경 내용3").build());

  }

  @Test
  public void testUpdateMemo3() {
    memoRepository.updateMemoText3(141L, "순서 찾기로  param 생략");

  }

  @Test
  public void test() {
    Page<Object[]> objects = memoRepository.getListWithQueryObject(
      0L, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno")));
    objects.forEach(r -> {
      for (Object o : r) {
        log.info("{}", o);
      }
    });

  }

  @Test
  public void testListWithQueryProjection() {
    Page<MemoDTO> dtos = memoRepository.getListWithQueryProjection(0L, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno")));
    dtos.forEach(r -> log.info("{} {} {}", r.getMno(), r.getMemoText(), r.getN()));


  }
}
