package com.example.board.service;

import com.example.board.domain.user.User;
import com.example.board.domain.user.UserRepository;
import com.example.board.dto.auth.OAuthAttributes;
import com.example.board.dto.auth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 서비스 구분용 id (구글/네이버/등등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 로그인시 pk가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // 소셜 로그인 된 유저 정보를 객체화
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 사용자 정보 없데이트
        User user = saveOrUpdate(attributes);

        /**
         * 세션에 사용자 정보 등록
         * 이를 위한 DTO가 SessionDto
         */
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail()).map(  // 해당 이메일의 사용자를 찾으면 이름과 사진 없데이트
                entity -> entity.update(attributes.getName(),attributes.getPicture()))
                // 해당 사용자가 없으먄 신규 생성
                    .orElse(attributes.toEntity());

        // DB에 저장 후 해당 값 변환
        return userRepository.save(user);
    }
}
