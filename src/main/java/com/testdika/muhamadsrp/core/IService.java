package com.testdika.muhamadsrp.core;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IService<T> {

    public ResponseEntity<Object> save(T t, HttpServletRequest request);
    public ResponseEntity<Object> update(Long id, T t ,HttpServletRequest request);
    public ResponseEntity<Object> delete(Long id,HttpServletRequest request);
    public ResponseEntity<Object> findAll(int page,int size ,String sort,String sortBy,HttpServletRequest request);
    public Boolean findByParam(String param, String column, HttpServletRequest request);
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request);

}
