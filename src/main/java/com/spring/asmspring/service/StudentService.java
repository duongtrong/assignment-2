package com.spring.asmspring.service;

import com.spring.asmspring.entity.Student;
import com.spring.asmspring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Student create(Student student) {
        student.setStatus(1);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(int id) {
        return studentRepository.findById(id).orElse(null);
    }
    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

}
