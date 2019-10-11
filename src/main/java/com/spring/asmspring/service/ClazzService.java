package com.spring.asmspring.service;

import com.spring.asmspring.entity.Clazz;
import com.spring.asmspring.repository.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService {

    @Autowired
    ClazzRepository clazzRepository;

    public List<Clazz> getAll(){
        return clazzRepository.findAll();
    }

    public Clazz getById(int id) {
        return clazzRepository.findById(id).orElse(null);
    }
}
