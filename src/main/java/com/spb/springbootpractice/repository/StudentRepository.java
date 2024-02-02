package com.spb.springbootpractice.repository;

import com.spb.springbootpractice.domain.Student;
import com.spb.springbootpractice.dto.UpdateStudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    boolean existsByEmail(String email);

    List<Student> findAllByGrade(Integer grade); // SELECT * FROM student WHERE grade = student.grade

    List<Student> findAllByLastName(String lastName);

    //@Query("SELECT s FROM Student s WHERE s.id=:pid")
    @Query("SELECT new com.spb.springbootpractice.dto.UpdateStudentDTO(s) FROM Student s WHERE s.id=:pid")
    Optional<UpdateStudentDTO> findStudentDTOById(@Param("pid") Long id);


    /*
       JPQL----
        @Query("SELECT s FROM Student s WHERE s.grade = :grade")
        List<Student> findAllByGrade(@Param("grade") Integer grade);

        OR IF YOU WANT NATIVE QUERY

       SQL----
        @Query(value = "SELECT * FROM student WHERE grade = :grade", nativeQuery = true)
        List<Student> findAllByGrade(@Param("grade") Integer grade);
     */
}
