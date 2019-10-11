package com.spring.asmspring.controller;

import com.spring.asmspring.entity.Clazz;
import com.spring.asmspring.entity.Student;
import com.spring.asmspring.repository.ClazzRepository;
import com.spring.asmspring.repository.StudentRepository;
import com.spring.asmspring.service.ClazzService;
import com.spring.asmspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/clazz")
public class ClazzController {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    ClazzService clazzService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable int id, Model model) {
        Clazz clazz = clazzService.getById(id);
        List<Student> studentList = studentService.getAll();
        if (clazz == null) {
            return "error/404";
        }
        model.addAttribute("studentList", studentList);
        model.addAttribute("clazz", clazz);
        return "clazz/detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addStudent")
    public String addStudent(int[] studentIds, int id) {
        List<Integer> intList = new ArrayList<Integer>();
        for (int i : studentIds) {
            intList.add(i);
        }

        List<Student> studentList = studentRepository.findAllById(intList);
        Clazz clazz = clazzService.getById(id);
        clazz.addStudent((Student) studentList);
        clazzRepository.save(clazz);
        return "redirect:/clazz/" + id;
    }
}
