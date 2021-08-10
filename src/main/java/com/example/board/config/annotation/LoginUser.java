package com.example.board.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)      //어노테이션이 생성될수있는위치지정
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
