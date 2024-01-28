package com.spb.springbootpractice.controller;

import com.spb.springbootpractice.domain.Student;
import com.spb.springbootpractice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private  final StudentService studentService;

    @GetMapping("/greet")
    public String greet() {
        return "Hello from StudentController";
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok().body(students);
        //return new ResponseEntity<>(students, HttpStatus.OK) -- other way of doing it
    }
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveStudent(@Valid @RequestBody Student student) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student saved successfully");
        response.put("status", "active");
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(response , HttpStatus.CREATED);//201

    }

    @GetMapping("/query")
    public ResponseEntity<Student> getStudentById(@RequestParam ("id") Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok().body(student);
    }



}
