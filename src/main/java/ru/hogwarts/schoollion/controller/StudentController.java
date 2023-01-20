package ru.hogwarts.schoollion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.service.FacultyService;
import ru.hogwarts.schoollion.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;
    private final FacultyService facultyService;

    public StudentController(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping   // GET http://localhost:8080/student
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
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
    public ResponseEntity<Collection<Student>> getAllStudentByAge(@PathVariable int studentAge) {
        return ResponseEntity.ok(studentService.findStudentByAge(studentAge));
    }

    @GetMapping("{minAge}&{maxAge}")   // GET http://localhost:8080/student/
    public ResponseEntity<Collection<Student>> findStudentByAgeBetween(@PathVariable int minAge, @PathVariable int maxAge) {
        return ResponseEntity.ok(studentService.findStudentByAgeBetween(minAge, maxAge));
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


    // Варианты кода получения списка студентов факультета.
    @GetMapping("{facultyId}")
    public ResponseEntity<Collection<Student>> findStudentByFacultyID(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        return ResponseEntity.ok(studentService.findStudentByFaculty(faculty));
    }

    @GetMapping("{facultyName}")
    public ResponseEntity<Collection<Student>> findStudentByFacultyName(@PathVariable String facultyName) {
        Faculty faculty = (Faculty) facultyService.findFacultiesByNameIgnoreCase(facultyName);
        return ResponseEntity.ok(studentService.findStudentByFaculty(faculty));
    }
}
