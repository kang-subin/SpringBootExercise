package com.example.springboot.service;

import com.example.springboot.dao.HospitalDao;
import com.example.springboot.domein.Hospital;
import com.example.springboot.parser.ReadLineContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HospitlaService { //test에서 insert 하는 것은 빌드 할 때마다 10만건이 올라가기 때문에 문제가 생겨서 service 구간에서 insert 함

    private final ReadLineContext<Hospital> hospitalReadLineContext;
    private final HospitalDao hospitalDao;

    public HospitlaService(ReadLineContext<Hospital> hospitalReadLineContext, HospitalDao hospitalDao) {
        this.hospitalReadLineContext = hospitalReadLineContext;
        this.hospitalDao = hospitalDao;
    }

    public int insertLargeVolumeHospitalData(String filename) {
        int cnt = 0;
        try {
            List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
            for (Hospital hospital : hospitalList) { // loop구간
                try {
                    this.hospitalDao.add(hospital); // db에 insert하는 구간
                    cnt++;
                } catch (Exception e) {
                    System.out.printf("id:%d 레코드에 문제가 있습니다.", hospital.getId());
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }
}

