package com.spring.asmspring.controller;

import com.spring.asmspring.entity.Clazz;
import com.spring.asmspring.entity.Student;
import com.spring.asmspring.service.ClazzService;
import com.spring.asmspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @Autowired
    StudentService studentService;

    @Autowired
    ClazzService clazzService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Clazz> clazzList = clazzService.getAll();
        Student student = studentService.getByEmail(name);
        model.addAttribute("student", student);
        model.addAttribute("clazzList", clazzList);
        return "index";
    }
}
