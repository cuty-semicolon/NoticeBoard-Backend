package com.example.board.domain.posts;

// 기존 Dao라 불리는 DB layer 접근자가 JPA에서는
// Repository라 부르며 인터페이스로 생성된다.

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}

