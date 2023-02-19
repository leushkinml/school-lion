package ru.hogwarts.schoollion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/return-faculty-by-student-id/{studentIdForReturnFaculty}")
    // GET http://localhost:8080/student/{studentIdForReturnFaculty}
    public ResponseEntity getStudentByIdAndReturnFacultybyLion(@PathVariable Long studentIdForReturnFaculty) {
        if (studentIdForReturnFaculty != null && studentIdForReturnFaculty > 0) {
            Student student = studentService.getStudentById(studentIdForReturnFaculty);
            if (student == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(studentService.getStudentById(studentIdForReturnFaculty).getFaculty());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/age/{minAge}&{maxAge}")   // GET http://localhost:8080/student/age/{minAge}&{maxAge}
    public ResponseEntity<Collection<Student>> findStudentByAgeBetweenByLion(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
        if (minAge != null && minAge > 0 && maxAge != null && maxAge > 0) {
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Работа с SQL запросами
    @GetMapping("/count-all-student-in-school")
    public Integer getCountAllStudentsInSchool() {
        return studentService.getCountAllStudentsInSchool();
    }

    @GetMapping("/avarage-age-of-all-student-in-school")
    public Integer getAvarageAgeOfAllStudentsInSchool() {
        return studentService.getAvarageAgeOfAllStudentsInSchool();
    }

    @GetMapping("/five-students-with-biggest-id-in-school")
    public List<Student> getFiveStudentsWithBiggestIdInSchool() {
        return studentService.getFiveStudentsWithBiggestIdInSchool();
    }

    @GetMapping("students-by-page")
    public ResponseEntity<List<Student>> getAllStudentsByPage(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Student> students = studentService.getAllStudentsByPage(pageNumber, pageSize);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        if (student.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be empty!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @GetMapping("/get-student-by-id/{id}")
    public Student getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return student;
    }

    @GetMapping("/get-student-by-age")
    public List<Student> findStudentsByAge(@RequestParam(required = false) Integer age) {
        return studentService.findStudentByAge(age);
    }

    @GetMapping(params = {"minAge", "maxAge"})
    public List<Student> findByAgeBetween(
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        return studentService.findStudentByAgeBetween(minAge, maxAge);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        if (student.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be specified!");
        }
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student deleteStudent = studentService.deleteStudentandReturn(id);
        if (deleteStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(deleteStudent);
        }
    }

    // Работа со Stream API
    @GetMapping("/students-with-name-start-with/{letter}")
    public ResponseEntity<List<String>> getStudentsWithNameStartWith(@PathVariable("letter") String letter) {
        List<String> students = studentService.getStudentsWithNameStartWith(letter);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students-with-name-start-A")
    public ResponseEntity<List<String>> getStudentsWithNameStartWithA() {
        List<String> students = studentService.getStudentsWithNameStartWithA();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students-average-age")
    public ResponseEntity<Double> studentsAverageAge() {
        Double averageAge = studentService.studentsAverageAge();
        return ResponseEntity.ok(averageAge);
    }
}