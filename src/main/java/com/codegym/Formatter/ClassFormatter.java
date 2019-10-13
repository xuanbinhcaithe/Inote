package com.codegym.Formatter;

import com.codegym.model.Lop;
import com.codegym.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class ClassFormatter implements Formatter<Lop> {
    @Autowired
    private ClassService classService;

    public ClassFormatter(ClassService classService) {
        this.classService = classService;
    }

    @Override
    public Lop parse(String text, Locale locale) throws ParseException {
        return classService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Lop object, Locale locale) {
        return "[" + object.getId() + "," +object.getName() + "]";
    }
}
