package ru.hogwarts.schoollion.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.schoollion.model.Avatar;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.service.AvatarService;
import ru.hogwarts.schoollion.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @PostMapping
    public Student createStudentByZurab(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping   // GET http://localhost:8080/student
    public ResponseEntity getStudentByZurab(@RequestParam(required = false) String name,
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

    @GetMapping("{studentIdForReturnFaculty}")   // GET http://localhost:8080/student/{studentIdForReturnFaculty}
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
    public ResponseEntity<Collection<Student>> findStudentByAgeBetweenbyLion(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
        if (minAge != null && minAge > 0 && maxAge != null && maxAge > 0) {
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudentByZurab(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        if (updateStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudentByZurab(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }





 // Работа с ФАЙЛАМИ

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)   // Работа с ФАЙЛАМИ
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{studentId}/avatar/previewAvatar")  // Работа с ФАЙЛАМИ
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long studentId) {
        Avatar avatar = avatarService.findAvatar(studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPreviewAvatar().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreviewAvatar());
    }

    @GetMapping(value = "/{studentId}/avatar")  // Работа с ФАЙЛАМИ
    public void downloadAvatar(@PathVariable Long studentId, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(studentId);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()){
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength(Math.toIntExact(avatar.getFileSize()));
            is.transferTo(os);
        }
    }




// Из РАЗБОРА домашки

//    @PostMapping // Из РАЗБОРА домашки
//    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
////        if (student.getId() != null) {
////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be empty!");
////        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student)); // Из РАЗБОРА домашки
//    }
//
////    @PostMapping
////    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
//////        if (student.getId() != null) {
//////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be empty!");
//////        }
////        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
////    }
//    @GetMapping("/{id}")   // Из РАЗБОРА домашки
//    public Student getStudent(@PathVariable Long id) {
//        Student student = studentService.getStudentById(id);
//        if (student == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return student;
//    }
//
//    @GetMapping(params = {"age"})  // Из РАЗБОРА домашки
//    public Set<Student> findStudentByAge(@RequestParam(required = false) Integer age) {
//        return (Set<Student>) studentService.findStudentByAge(age);
//    }
//
//    @GetMapping(params = {"minAge", "maxAge"})  // Из РАЗБОРА домашки
//    public Set<Student> findByAgeBetween(
//            @RequestParam(required = false) Integer minAge,
//            @RequestParam(required = false) Integer maxAge) {
//        return (Set<Student>) studentService.findStudentByAgeBetween(minAge, maxAge);
//    }
//
//    @PutMapping
//    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
////        if (student.getId() != null) {
////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be empty!");
////        }
//        return ResponseEntity.ok(studentService.updateStudent(student));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student deleteStudent = studentService.deleteStudentandReturn(id);
        if (deleteStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(deleteStudent);
        }
    }
}
