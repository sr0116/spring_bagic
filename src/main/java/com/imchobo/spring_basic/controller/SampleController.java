package com.imchobo.spring_basic.controller;

import com.imchobo.spring_basic.domain.dto.SampleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Slf4j
@Controller
@RequestMapping("sample")
public class SampleController {

  @GetMapping("ex1")
  public  void ex1(){
    log.info("ex1..............");
  }

  @GetMapping({"ex2", "exLink"})
  public  void ex2(Model model){
    List<SampleDTO> dtos = LongStream.rangeClosed(1, 20).mapToObj(i -> SampleDTO.builder()
      .sno((long)i)
      .first("First" + i)
      .last("Last" + i)
      .regTime(LocalDateTime.now())
      .build()).toList();
    model.addAttribute("list", dtos);
  }

  @GetMapping("ex3inline")
  public String ex3(RedirectAttributes rttr){
    List<SampleDTO> dtos = LongStream.rangeClosed(1, 20).mapToObj(i -> SampleDTO.builder()
      .sno((long)i)
      .first("First" + i)
      .last("Last" + i)
      .regTime(LocalDateTime.now())
      .build()).toList();
    rttr.addFlashAttribute("list", dtos); // 1회성이라 새로고침하면 null 값
    rttr.addFlashAttribute("result", "success");
    rttr.addFlashAttribute("number", 10);
    rttr.addAttribute("date", LocalDateTime.now());
    return "redirect:/sample/ex3";
  }

  @GetMapping("ex3")
  public  void ex3(){}

  @GetMapping("exLayout1")
  public  void exLayout1(Model model){}
}
