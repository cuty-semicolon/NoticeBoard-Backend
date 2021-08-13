package com.example.board.dto;

import com.example.board.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}

/**
 * PostsResponseDto는 Entity의 필드 중 일부만 사용 ~> 생성자로 Entity 받아 필드에 넣을 필요 X
 *
 */
