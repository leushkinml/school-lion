package ru.hogwarts.schoollion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.repository.StudentRepository;
import ru.hogwarts.schoollion.service.FacultyService;
import ru.hogwarts.schoollion.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService, FacultyService facultyService, StudentRepository studentRepository) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping   // GET http://localhost:8080/student
    public ResponseEntity getStudent(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) Integer studentAge,
                                     @RequestParam(required = false) Long studentId) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(studentService.findStudentByNameIgnoreCase(name));
        }
        if (studentAge != null && studentAge > 0) {
            return ResponseEntity.ok(studentService.findStudentByAge(studentAge));
        }
        if (studentId != null && studentId > 0) {
            Student student = studentService.getStudentById(studentId);
            if (student == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("{studentIdForReturnFaculty}")
    public ResponseEntity getStudentByIdAndReturnFaculty(@PathVariable Long studentIdForReturnFaculty) {
        if (studentIdForReturnFaculty != null && studentIdForReturnFaculty > 0) {
            Student student = studentService.getStudentById(studentIdForReturnFaculty);
            if (student == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(studentService.getStudentById(studentIdForReturnFaculty).getFaculty());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/age/{minAge}&{maxAge}")   // GET http://localhost:8080/student/
    public ResponseEntity<Collection<Student>> findStudentByAgeBetween(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
        if (minAge != null && minAge > 0 && maxAge != null && maxAge > 0) {
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

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
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
