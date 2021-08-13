package com.example.board.service;

import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import com.example.board.dto.PostsListResponseDto;
import com.example.board.dto.PostsResponseDto;
import com.example.board.dto.PostsSaveRequestDto;
import com.example.board.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자 값으로 하는 생성자를 룸북으로 대신 생성, final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성 의존성 병경 할때마다 코드 변경 할 필요 없음

@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id" + id));

        return new PostsResponseDto(entity);

    }

    /**
     * JPA의 영속성 컨텍스트
     *
     * 엔터티를 영구 저장하는 환경(JPA의 핵심 ~> Entity가 영속성 컨텍스트에 포함 or X)
     *
     * JPA 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 DB 데이터를 가져오면, 이 데이터는 영속성 컨텍스트가 유지된 상태
     *
     * 해당 데이터 값을 변경하면, 트랜잭션이 끝나는 시점에서 해당 테이블에 변경분을 반영
     *
     * update 기능에서 DB에 쿼리를 직접 날릴 필요 없이, Entity 객체 값만 변경하면 됨
     *
     * 이를 더티 체킹(Dirty Checking) 이라고 함
     */

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        postsRepository.delete(posts);
    }}
