package com.example.springboot.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadLineContext <T> { // T 의미 이 class에서 사용되는 객체의 타입

    private Parser<T> parser;

    public ReadLineContext(Parser<T> parser) { // HospitalParser 주입 parser 를 구현한 놈만 들어올 수 있음
        this.parser = parser;
    }

    public List<T> readByLine(String filename) throws IOException {
        // 삽
        List<T> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        );
        String str;
        while ((str = reader.readLine()) != null) {
            try {
                result.add(parser.parse(str));
            } catch (Exception e) {
                System.out.printf("파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:%s\n", str.substring(0, 20));
            }
        }
        reader.close();
        return result;
    }

}
