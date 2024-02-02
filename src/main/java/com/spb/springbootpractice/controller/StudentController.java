package com.spb.springbootpractice.controller;

import com.spb.springbootpractice.domain.Student;
import com.spb.springbootpractice.dto.UpdateStudentDTO;
import com.spb.springbootpractice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody UpdateStudentDTO updateStudentDTO) {
        studentService.updateStudentById(id, updateStudentDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /*
   PAGINATION
/http://localhost: 8080/students/ page?
page=1&
size=10&
sort=name&
direction=DESC + GET
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getAllStudentsByPage(@RequestParam("page") Integer page,
                                                              @RequestParam("size") Integer size,
                                                              @RequestParam("sort") String sort,
                                                              @RequestParam("direction") Sort.Direction direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<Student> students = studentService.getAllStudentsByPage(pageable);
        return ResponseEntity.ok().body(students);
    }
    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getAllStudentsByGrade(@PathVariable("grade") Integer grade) {
        List<Student> students = studentService.getAllStudentsByGrade(grade);
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UpdateStudentDTO> getStudentInfo(@PathVariable("id")  Long id ){
        UpdateStudentDTO studentInfo = studentService.getStudentInfo(id);
        return ResponseEntity.ok().body(studentInfo);
    }

    @GetMapping("/query")
    public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam("lastName") String lastName) {
        List<Student> students = studentService.getStudentByLastName(lastName);
        return ResponseEntity.ok().body(students);
    }





}
