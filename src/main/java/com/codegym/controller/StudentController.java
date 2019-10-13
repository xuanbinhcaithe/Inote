package com.codegym.controller;

import com.codegym.model.Lop;
import com.codegym.model.Student;
import com.codegym.service.ClassService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @ModelAttribute("lop")
    public Iterable<Lop> viewClass() {
        return classService.findAll();
    }


     private static List<String> listFavorites;
    private static List<String> listPosition;
    static {
        listFavorites = new ArrayList<String>();
        listFavorites.add("Swimming");
        listFavorites.add("Listening music");
        listFavorites.add("Walking");
        listFavorites.add("Watching movie");
        listFavorites.add("Reading book");

        listPosition = new ArrayList<String>();
        listPosition.add("Developer");
        listPosition.add("Designer");
        listPosition.add("Tester");
        listPosition.add("QA");
    }


    @GetMapping("/create-student")
    public ModelAndView showCreateForm() {

        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student",new Student());
        modelAndView.addObject("listFavorites",listFavorites);
        modelAndView.addObject("listPosition",listPosition);
        return modelAndView;
    }
    @PostMapping("/create-student")
    public ModelAndView createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
       if (bindingResult.hasFieldErrors()) {
           ModelAndView modelAndView = new ModelAndView("/student/create");
           modelAndView.addObject("listFavorites",listFavorites);
           modelAndView.addObject("listPosition",listPosition);
           return modelAndView;
       }else {
           studentService.save(student);
           ModelAndView modelAndView = new ModelAndView("/student/create");
           modelAndView.addObject("student",new Student());
           modelAndView.addObject("listFavorites",listFavorites);
           modelAndView.addObject("listPosition",listPosition);
           modelAndView.addObject("message","Create successfully!");
           return modelAndView;
       }
    }
    @GetMapping("/students")
    public ModelAndView listStudent(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Student> students;
        if (s.isPresent()) {
            students = studentService.findAllByNameContaining(s.get(),pageable);
        }else {
            students = studentService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("student",students);
        return modelAndView;
    }
    @GetMapping("/edit-student/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/edit");
            modelAndView.addObject("student",student);
            modelAndView.addObject("listFavorites",listFavorites);
            modelAndView.addObject("listPosition",listPosition);
            return modelAndView;
        }else {
            return new ModelAndView("/error.404");
        }
    }
    @PostMapping("/edit-student")
    public ModelAndView updateStudent(@Valid @ModelAttribute Student student,BindingResult bindingResult,Pageable pageable) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/student/edit");
            modelAndView.addObject("listFavorites",listFavorites);
            modelAndView.addObject("listPosition",listPosition);
            return modelAndView;
        }else {
            studentService.save(student);
            Page<Student> students = studentService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("/student/list");
            modelAndView.addObject("student",students);
            return modelAndView;
        }
    }

    @GetMapping("/delete-student/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id")Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/delete");
            modelAndView.addObject("student",student);
            return modelAndView;
        }else {
            return new ModelAndView("/error.404");
        }
    }
    @PostMapping("/delete-student")
    public String deleteStudent(@ModelAttribute Student student) {
        studentService.remove(student.getId());
        return "redirect:students";
    }

}
