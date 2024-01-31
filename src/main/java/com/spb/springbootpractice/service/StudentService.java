package com.spb.springbootpractice.service;

import com.spb.springbootpractice.domain.Student;
import com.spb.springbootpractice.dto.UpdateStudentDTO;
import com.spb.springbootpractice.exception.EmailConflictException;
import com.spb.springbootpractice.repository.StudentRepository;
import com.spb.springbootpractice.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        //Check if the email is already in use
        //SELECT * FROM student WHERE email = student.email
        //if exists throw an exception
        if(studentRepository.existsByEmail(student.getEmail())) {
            throw new EmailConflictException("Email already in use");
        }
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        return student;
    }

    public void deleteStudentById(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    public void updateStudentById(Long id, UpdateStudentDTO updateStudentDTO) {
        Student student = getStudentById(id);

        boolean existsByEmail = studentRepository.existsByEmail(updateStudentDTO.getEmail());

        if(existsByEmail && !student.getEmail().equals(updateStudentDTO.getEmail())) {
            throw new EmailConflictException("Email already in use");
        }

        student.setName(updateStudentDTO.getName());
        student.setLastName(updateStudentDTO.getLastName());
        student.setEmail(updateStudentDTO.getEmail());

        studentRepository.save(student);
    }

    public Page<Student> getAllStudentsByPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
