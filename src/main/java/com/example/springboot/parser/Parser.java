package com.example.springboot.parser;

import org.springframework.stereotype.Component;

@Component
public interface Parser<T> {
    T parse (String str); // T 타입을 반환하는 메소드
}
