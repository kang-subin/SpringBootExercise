package com.example.springboot.parser;

import com.example.springboot.dao.HospitalDao;
import com.example.springboot.domein.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HospitalParserTest {
    HospitalParser hp = new HospitalParser();
    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";

@Autowired
ReadLineContext<Hospital> hospitalReadLineContext; // 파일 파싱해서 hospital 객체 result list 로 만든 거

    @Autowired
    HospitalDao hospitalDao; // private final 로 jdbc 주입 안했을 경우 생성자 형식으로 작성해야 했음

    @Test
    @DisplayName("csv 1줄을 Hospital로 잘 만드는지 Test")
    void convertToHospital(String line1) {

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate());
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());
    }

    @Test
    void findById() throws IOException, SQLException, ClassNotFoundException {
        Hospital hospital = hp.parse(line1);
        Hospital hospital1 = hospitalDao.findById(1);
        System.out.println(hospital1.getHospitalName());
        assertEquals(hospital.getHospitalName(), hospital1.getHospitalName());
        assertEquals(hospital1.getLicenseDate(), hospital.getLicenseDate());
    }

    @Test
    void getCont() throws IOException, SQLException, ClassNotFoundException {
        int count = hospitalDao.getCount();
        System.out.println(count);
        assertEquals(1, count);
    }

    @Test
    void add(){
        Hospital hospital = hp.parse(line1); // 파일 파싱해서 각 칼럼에 값을 hospital에 넣은 상태
        hospitalDao.add(hospital); // hospital에 담겨 있는 칼럼 값을 insert 쿼리문에 넣음
    }

    @Test
    void name() throws IOException {
        String filename = "/Users/kangsubin/Desktop/전국병원정보.csv";
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
        assertTrue(hospitalList.size()>1000);
        assertTrue(hospitalList.size()>10000);
    }
}



