package com.example.springboot.controller;

import com.example.springboot.dao.HospitalDao;
import com.example.springboot.domein.Hospital;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RequestMapping("/api/v1/hospitals")
@RestController // 자동으로
public class HospitalController {
    private final HospitalDao hospitalDao; // 이렇게 주입할 경우 component 로 사용가능

    private HospitalController (HospitalDao hospitalDao){ //
        this.hospitalDao = hospitalDao;
    }

    @GetMapping("/getCount")
    public ResponseEntity<Integer> getCount() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(hospitalDao.getCount());
    }

    @GetMapping("/find")
    public ResponseEntity<Hospital> findById(int id) throws SQLException, ClassNotFoundException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(hospitalDao.findById(id));
        
    }
}


