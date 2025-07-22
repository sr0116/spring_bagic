package com.imchobo.spring_basic.repository;

import com.imchobo.spring_basic.domain.Memo;
import com.imchobo.spring_basic.domain.dto.MemoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
  void deleteByMemoText(String memoText);

  List<Memo> findByMemoText(String memoText); // 이게 매서드 이름 자체가 쿼리 구문

  List<Memo> findByMnoBetweenOrderByMnoDesc(Long mno1,Long mno2 );

  Page<Memo> findByMnoBetween(Long mno1, Long mno2, Pageable pageable);

  // 개수
  long countByMno(Long mno); // groupBy를 자동으로 해줌

//  mno가 특정 long이거나 memoText가 특정 문자열일때의 query method
  List<Memo> findByMnoOrMemoText(Long mno, String memoText);

//  @Query("select * from tbl_memo order by mno desc ") * 허용 안함 , 테이블면 ㄴ
  @Query("select m from Memo m order by m.mno desc limit 10")
  List<Memo> getListDesc(); //  먼저 쿼리 등록 해줘야 안 터짐
// 두개 다 같음
  @Query(value = "select * from tbl_memo order by mno desc limit 10", nativeQuery = true)
  List<Memo> getListDesc2();

  @Transactional
  @Modifying
  @Query("update  Memo  m set m.memoText = :memoText where m.mno = :mno")
  int updateMemoText (@Param("mno") Long mno, @Param("memoText") String memoText);

  @Transactional
  @Modifying
  @Query("update Memo m set m.memoText = :#{#memo.memoText} where m.mno = :#{#memo.mno}") // #{}-> 자체가 매핑 해주는거고 안에 #은 별개
  int updateMemoText2  (@Param("memo") Memo memo);

  @Transactional
  @Modifying
  @Query("update  Memo  m set m.memoText = ?2 where m.mno = ?1")
  int updateMemoText3 (@Param("mno") Long mno, @Param("memoText") String memoText);

  @Query(value = "select m.mno, m.memoText , CURRENT_DATE  AS n from Memo m where m.mno > :mno"
    , countQuery = "select count(m) from Memo m where m.mno > :mno")
  Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);

  @Query(value = "select m.mno As mno, m.memoText AS memoText , CURRENT_DATE  AS n from Memo m where m.mno > :mno"
    , countQuery = "select count(m) from Memo m where m.mno > :mno")
  Page<MemoDTO> getListWithQueryProjection(Long mno, Pageable pageable);
}
