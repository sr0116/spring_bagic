package com.imchobo.spring_basic.aop;


import org.springframework.stereotype.Service;

@Service
public interface BoardService {
  void write (String title, String content);

  Object get(Long bno);

  void remove (Long bno);

  void modify (String title, String content);
}
