package com.imchobo.spring_basic.domain;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_member")
@ToString(exclude = "boards")
@Setter
@Getter
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터 베이스가 권한을 가짐
  private Long no;
  private String name;
  private String id;
  private String pw;
  private int age;

  @OneToMany(mappedBy = "member") // 멤버 필드에 대한 맵핑이 되었다 // DB 설정을 여기서 하는 것이라 보면 됨
  private List<Board> boards;
}
