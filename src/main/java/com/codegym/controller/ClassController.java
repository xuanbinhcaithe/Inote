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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/view-class/{id}")
    public ModelAndView viewClass(@PathVariable("id") Long id, Pageable pageable) {
        Lop lop = classService.findById(id);
        if (lop != null) {
            ModelAndView modelAndView = new ModelAndView("/lop/view");
            Page<Student> students = studentService.findAllByLop(lop,pageable);
            modelAndView.addObject("lop",lop);
            modelAndView.addObject("student",students);
            return modelAndView;
        }
        else {
            return new ModelAndView("/error.404");
        }
    }

    @GetMapping("/create-class")
    public ModelAndView showCreateForm()  {
        ModelAndView modelAndView = new ModelAndView("/lop/create");
        modelAndView.addObject("lop",new Lop());
        return modelAndView;
    }
    @PostMapping("/create-class")
    public ModelAndView createClass(@Valid @ModelAttribute Lop lop, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/lop/create");
            return modelAndView;
        }
        classService.save(lop);
        ModelAndView modelAndView = new ModelAndView("/lop/create");
        modelAndView.addObject("lop",new Lop());
        modelAndView.addObject("message","Successfully!");
        return modelAndView;
    }
    @GetMapping("/classes")
    public ModelAndView listClass() {
        Iterable<Lop> classes = classService.findAll();
        ModelAndView modelAndView = new ModelAndView("/lop/list");
        modelAndView.addObject("classes",classes);
        return modelAndView;
    }
   @GetMapping("/edit-class/{id}")
        public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Lop lop = classService.findById(id);
        if (lop != null) {
            ModelAndView modelAndView = new ModelAndView("/lop/edit");
            modelAndView.addObject("lop",lop);
            return modelAndView;
        }else {
            return new ModelAndView("/error.404");
        }
        }
    @PostMapping("/edit-class")
    public ModelAndView updateClass(@Valid @ModelAttribute Lop lop,BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/lop/edit");
            return modelAndView;
        }
        classService.save(lop);
        ModelAndView modelAndView = new ModelAndView("/lop/edit");
        modelAndView.addObject("lop",lop);
        modelAndView.addObject("message","Successfully!");
        return modelAndView;
    }
    @GetMapping("/delete-class/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Lop lop = classService.findById(id);
        if (lop != null) {
            ModelAndView modelAndView = new ModelAndView("/lop/delete");
            modelAndView.addObject("lop",lop);
            return modelAndView;
        }else {
            return new ModelAndView("/error.404");
        }
    }
    @PostMapping("/delete-class")
    public String deleteClass(@ModelAttribute Lop lop) {
        classService.remove(lop.getId());
        return "redirect:classes";
    }
}
