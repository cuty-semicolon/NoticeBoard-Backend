package com.example.board.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)      //지역 변수 선언시 어노테이션 적용, 전달 인자 선언함
@Retention(RetentionPolicy.RUNTIME) // 어노테이션을 런타임시에 까지 사용, 컴파일 이후에도 JVM에 의해 계속 사용 (리플렉션 사용)
public @interface LoginUser {
}
