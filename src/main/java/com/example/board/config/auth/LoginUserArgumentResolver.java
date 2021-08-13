package com.example.board.config.auth;

import com.example.board.config.annotation.LoginUser;
import com.example.board.dto.auth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        //파라미터에 @LoginUser 어노테이션이 붙어 있는지?

        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;

        // 파라미터의 클래스 타입이 dto.SessionUser.class 인지?
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }

    /**
     *  HandlerMethodArgumentResolver 인터페이스를 상속받는 구현체 클래스를 생성
     *  이 인터페이스는 ArgumentResolver에 대한 메서드 지워
     *  supportsParameter 매서드는 들어온 파라미터에 대해 resolveArgument 메서드를 실행할지 말지 판단한다 리턴 값이 true면 결과적으로 resolveArgument 메서드를 실행하게 됨
     *  이 메서드는 파라미터를 가공하는 역할
     *  parameter.getParameterAnnotation(LoginUser.class) != null 파라미터의 어노테이션이 LoginUser인데
     *    parameter.getParameterType이 SessionUser일 경우 true를 반환하고 resolveArgument 메서드는 HttpSession. getAttribute("user") 를 반환
     *
     */
}
