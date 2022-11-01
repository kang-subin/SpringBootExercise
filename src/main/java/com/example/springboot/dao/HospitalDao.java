package com.example.springboot.dao;

import com.example.springboot.domein.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HospitalDao {

        private final JdbcTemplate jdbcTemplate;  // Autowired 대신 private final = DI

        public HospitalDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        // List<Hospital> - 11만건이 들어있음. Hospital
        public void add(Hospital hospital) {
            String sql = "INSERT INTO `nation_wide_hospital` \n" +
                    "(`id`, `open_service_name`, `open_local_government_code`, " +
                    "`management_number`, `license_date`, `business_status`, " +
                    "`business_status_code`, `phone`, `full_address`, " +
                    "`road_name_address`, `hospital_name`, `business_type_name`, " +
                    "`healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, " +
                    "`total_area_size`) \n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.jdbcTemplate.update(
                    sql,
                    hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(),
                    hospital.getManagementNumber(), hospital.getLicenseDate(), hospital.getBusinessStatus(),
                    hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(),
                    hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(),
                    hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(),
                    hospital.getTotalAreaSize()
            );
        }
    }

