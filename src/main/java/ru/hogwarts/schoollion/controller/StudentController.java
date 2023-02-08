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

    @GetMapping("/return-faculty-by-student-id/{studentIdForReturnFaculty}")   // GET http://localhost:8080/student/{studentIdForReturnFaculty}
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


// Из РАЗБОРА домашки

    // ПРОВЕРИЛ
    @PostMapping // Из РАЗБОРА домашки ПРОВЕРИЛ
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        if (student.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be empty!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student)); // Из РАЗБОРА домашки
    }

    // ПРОВЕРИЛ
    @GetMapping("/get-student-by-id/{id}")   // Из РАЗБОРА домашки  ПРОВЕРИЛ
    public Student getStudent(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return student;
    }

    // ПРОВЕРИЛ
    //@GetMapping(params = {"/get-student-by-age/{age}"})  // Из РАЗБОРА домашки   ПРОВЕРИЛ
    @GetMapping("/get-student-by-age/{age}")  // Из РАЗБОРА домашки   ПРОВЕРИЛ
    public List<Student> findStudentsByAge(@RequestParam(required = false) Integer age) {
        return studentService.findStudentByAge(age);
    }

    // ПРОВЕРИЛ
    @GetMapping(params = {"minAge", "maxAge"})  // Из РАЗБОРА домашки   ПРОВЕРИЛ
    public List<Student> findByAgeBetween(
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        return studentService.findStudentByAgeBetween(minAge, maxAge);
    }

    // ПРОВЕРИЛ
    @PutMapping   // Из РАЗБОРА домашки   ПРОВЕРИЛ
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        if (student.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be specified!");
        }
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    // НЕ ПОНЯТНО, как удалять
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student deleteStudent = studentService.deleteStudentandReturn(id);
        if (deleteStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(deleteStudent);
        }
    }

//    @DeleteMapping("{studentId}")
//    public ResponseEntity<Student> deleteStudentByZurab(@PathVariable Long studentId) {
//        studentService.deleteStudent(studentId);
//        return ResponseEntity.ok().build();
//    }


//    @PostMapping
//    public Student createStudentByZurab(@RequestBody Student student) {
//        return studentService.createStudent(student);
//    }

//    @GetMapping   // GET http://localhost:8080/student
//    public ResponseEntity getStudentByZurab(@RequestParam(required = false) String name,
//                                     @RequestParam(required = false) Integer studentAge,
//                                     @RequestParam(required = false) Long studentId) {
//        if (name != null && !name.isBlank()) {
//            return ResponseEntity.ok(studentService.findStudentByNameIgnoreCase(name));
//        }
//        if (studentAge != null && studentAge > 0) {
//            return ResponseEntity.ok(studentService.findStudentByAge(studentAge));
//        }
//        if (studentId != null && studentId > 0) {
//            Student student = studentService.getStudentById(studentId);
//            if (student == null) {
//                return ResponseEntity.notFound().build();
//            }
//            return ResponseEntity.ok(student);
//        }
//        return ResponseEntity.ok(studentService.getAllStudent());
//    }

//    @PutMapping()
//    public ResponseEntity<Student> updateStudentByZurab(@RequestBody Student student) {
//        Student updateStudent = studentService.updateStudent(student);
//        if (updateStudent == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        return ResponseEntity.ok(updateStudent);
//    }
}