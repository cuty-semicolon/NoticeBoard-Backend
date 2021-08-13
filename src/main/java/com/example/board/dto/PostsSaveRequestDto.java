package com.example.board.dto;

//Entity와 유사하지만, Dto를 추가로 생성. 왜냐하면 Entity클래스를 절대 Request/response로 사용하면 안되기 때문


import com.example.board.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

/**
 * Entity와 거의 유사하지만, Dto 클래스를 추가로 생성한 이유
 *
 * Entity 클래스를 Request / Response 클래스로 사용해서는 안됨
 * Entity 클래스는 Db와 맞닿아 있는 핵심 클래스이다
 * 회면 병경은 사소하고 자주 일어나는데 Entity를 사용하면 너무큰 변경이 됨
 * View Layer와 DB Layer의 역활을 분리 시켜야 함
 **/
