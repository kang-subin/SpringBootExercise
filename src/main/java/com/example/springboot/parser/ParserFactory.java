package com.example.springboot.parser;

import com.example.springboot.domain.Hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이렇게 하는거랑 readlinecontext 클래스에 hospitalparser 의존관계 만든다음 componet? 붙이는거랑 같은의미 아닌가?
@Configuration// component를 붙일 수 있는 상황이기 때문에 test 코드에서 잘 돌아가는 것 직접 bean 등록할 경우 생성자 식으로 작성이 아닌 bean 자체로 바로 작성가능함
public class ParserFactory {
@Bean
    public ReadLineContext<Hospital> hospitalReadLineContext(){ // 이 메소드는 ReadLineContext에 HospitalParser 주입한 객체를 반환 -> bean
        return new ReadLineContext<Hospital>(new HospitalParser());
    }
}
