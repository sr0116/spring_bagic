package com.imchobo.spring_basic.repository;

import com.imchobo.spring_basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long> {
}
