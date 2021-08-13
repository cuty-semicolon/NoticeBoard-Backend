package com.example.board.dto.auth;

import com.example.board.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {  // 인증된 사용자 정보 저장 클래스
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();

    }
}

/**
 * 사용 이유
 *
 * User 클래스 에서 Session을 저장하려는 경우, User 클래스에 직형화 하지 않았기 때문에 오루 발생
 *
 */
