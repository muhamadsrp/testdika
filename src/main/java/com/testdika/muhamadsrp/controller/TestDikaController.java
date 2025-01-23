package com.testdika.muhamadsrp.controller;

import com.testdika.muhamadsrp.service.TestDikaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
public class TestDikaController {

    @Autowired
    private TestDikaService test;

    @GetMapping("/list/{page}/{sort}/{sort-by}")
    public ResponseEntity<Object> findAll(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "sort") String sort,
            @PathVariable(value = "sort-by") String sortBy,
            @RequestParam(value = "size") Integer size,
            HttpServletRequest request){
        return test.findAll(page,size,sort,sortBy,request);
    }

}
