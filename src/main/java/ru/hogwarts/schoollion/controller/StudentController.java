package ru.hogwarts.schoollion.controller;


import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("{studentAge}")   // GET http://localhost:8080/student/18
    public ResponseEntity<Collection<Student>> getAllStudentInAge(@PathVariable int studentAge) {
        return ResponseEntity.ok(studentService.getAllStudentInAge(studentAge));
    }

//    @GetMapping
//    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
//        if (age > 0) {
//            return ResponseEntity.ok(studentService.findByAge(age));
//        }
//        return ResponseEntity.ok(Collections.emptyList());
//    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        if (updateStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.deleteStudent(studentId));
    }
}
