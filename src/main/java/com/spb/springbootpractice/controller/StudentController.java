package com.spb.springbootpractice.controller;

import com.spb.springbootpractice.domain.Student;
import com.spb.springbootpractice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/id}")
    public ResponseEntity<Student> findStudentById(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
