package com.imchobo.spring_basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class indexController {
  @GetMapping("")
  @ResponseBody // 생략시 jsp forward가 됨
  public Map<?, ?> index(){ // ? 어떠한 값이 와도 필요없다(제네릭 파트)-> run 타임 시점 // 생략하면 object라 생각 -> 컴파일 시점
    return  Map.of("test", 123456); // 8080포트에 해당 값 나옴
  }
}
