package com.example.board.config.auth;

import com.example.board.domain.user.Role;
import com.example.board.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    // 비회원도 게시판 볼수 있도록

    /**
     *
     * @EnableWebSecurity - 스프링 시큐리티 설정 활성화
     *
     * http.csrf().disable()
     * .headers().frameOptions().disable() - h2-console 화면을 사용하기 위해 옵션을 disable
     *
     * - csrf().disable() : basic auth를 사용하기 위해 csrf 보호 기능 disable
     *
     * authorizeRequests() - URL별 권한 접근제어 관리 옵션 시작점
     *
     * antMatchers - 권한 관리 대상 지정.
     *
     * permitAll() - 모든 권한에게 공개
     *
     * hasRole(user권한) - user권한인 사람한테만 공개
     *
     * anyRequest().authenticated() - 나머지 요청들은 인증된 사람에게만 공개(인증된 사람 == 로그인 사용자)
     *
     * .oauth2Login() - oauth2 로그인 설정의 진입점
     *
     * userInfoEndpoint() - 로그인 성공 이후 사용자 정보 가져올때의 설정 담당
     *
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable()
                .and().authorizeRequests().antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.GUEST.name()).anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint().userService(customOAuth2UserService);
    }
}
