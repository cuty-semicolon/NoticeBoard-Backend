package com.example.board.domain.posts;

import com.example.board.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Entity클래스가 DB와 매칭되서 사용된다.
//Entity클래스는 Seter클래스를 만들지않는다.
//대신 값 변경이 필요하면 메소드를 추가한다.


@Getter
@NoArgsConstructor      //기본 생성자(인자없는) 자동추가
@Entity                 //테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {
    @Id                 //PK필드 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //PK생성규칙  auto increment
    private Long id;

    @Column(length = 500, nullable = false)     //Column은 굳이 선언하지 않아도 모두 칼럼이지만, 이처럼 변경사항있을시 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 빌더 패턴 클래스 생성, 생성자에 포함된 필드만 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
