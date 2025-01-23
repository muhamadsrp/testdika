package com.testdika.muhamadsrp.service;


import com.testdika.muhamadsrp.core.IService;
import com.testdika.muhamadsrp.model.TestDika;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class TestDikaService implements IService<TestDika> {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<Object> save(TestDika testDika, HttpServletRequest request) {
        int intResult = 0;
        try
        {
            intResult = jdbcTemplate.update("call demo.sp_add_mst_contoh(?,?,?,?,?,?)",
                    testDika.getTestInt(),
                    testDika.getTestDouble(),
                    testDika.getTestString(),
                    testDika.getTestFloat(),
                    testDika.getTestBoolean(),
                    new java.sql.Date(((Date) testDika.getTestDate()).getTime()));
            if(intResult != 1){
                throw new Exception("DATA GAGAL DISIMPAN");
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<Object>("DATA GAGAL DISIMPAN "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(new HashMap<>(),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> findAll(int page,
                                          int size,
                                          String sort,
                                          String sortBy,
                                          HttpServletRequest request) {
        Object object[] = new Object[4];
        object[0] = page;
        object[1] = size;
        object[2] = sort;
        object[3] = sortBy;

        List<TestDika> contohList  = jdbcTemplate.query("SELECT * FROM find_all(?,?,?,?)", new RowMapper<TestDika>() {
            @Override
            public TestDika mapRow(ResultSet rs, int rowNum) throws SQLException {
                TestDika testDika = new TestDika();
                testDika.setId(rs.getLong("id"));
                testDika.setTestInt(rs.getInt("contoh_int"));
                testDika.setTestDouble(rs.getDouble("contoh_double"));
                testDika.setTestFloat(rs.getFloat("contoh_float"));
                testDika.setTestString(rs.getString("contoh_string"));
                testDika.setTestBoolean(rs.getBoolean("contoh_boolean"));
                testDika.setTestDate(rs.getDate("contoh_date"));
                return testDika;
            }
        },object);

        if(contohList.isEmpty()){
            return new ResponseEntity<Object>("Data Tidak Ditemukan",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(contohList,HttpStatus.OK);
    }

}
