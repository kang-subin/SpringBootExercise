package com.example.springboot.dao;

import com.example.springboot.domein.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class HospitalDao {

        private final JdbcTemplate jdbcTemplate;  // Autowired 할 경우 test 에서 생성자 주입을 해줘야함 대신 private final = DI

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

    public int getCount() {
        String sql = "select count(*) from nation_wide_hospital";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

        public void deleteAll() {
            this.jdbcTemplate.update("delete from nation_wide_hospitals");
        }

    RowMapper<Hospital> rowMapper = (rs, rowNum) -> { // RowMapper로 Hospital에 rs 값을 넣어줌
        Hospital hospital = new Hospital(
                rs.getInt("id"),
                rs.getString("open_service_name"),
                rs.getInt("open_local_government_code"),
                rs.getString("management_number"),
                rs.getTimestamp("license_date").toLocalDateTime(),
                rs.getInt("business_status"),
                rs.getInt("business_status_code"),
                rs.getString("phone"),
                rs.getString("full_address"),
                rs.getString("road_name_address"),
                rs.getString("hospital_name"),
                rs.getString("business_type_name"),
                rs.getInt("healthcare_provider_count"),
                rs.getInt("patient_room_count"),
                rs.getInt("total_number_of_beds"),
                rs.getFloat("total_area_size")
        );
        return hospital;
    };

    public Hospital findById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from nation_wide_hospital where id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}





