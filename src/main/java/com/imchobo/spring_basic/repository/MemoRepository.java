package com.imchobo.spring_basic.repository;

import com.imchobo.spring_basic.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    void deleteByMemoText (String memoText);
    List <Memo> findByMemoText (String memoText);
}
