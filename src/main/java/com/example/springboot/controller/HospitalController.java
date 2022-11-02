package com.example.springboot.controller;

import com.example.springboot.dao.HospitalDao;
import com.example.springboot.domein.Hospital;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/v1/hospitals")
@RestController // 자동으로
public class HospitalController {
    private final HospitalDao hospitalDao; // 이렇게 주입할 경우 component 로 사용가능

    private HospitalController (HospitalDao hospitalDao){ //
        this.hospitalDao = hospitalDao;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(Hospital hospital) {
        hospitalDao.add(hospital);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("추가완료");
    }

}
