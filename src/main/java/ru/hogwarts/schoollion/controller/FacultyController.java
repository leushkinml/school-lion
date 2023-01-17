package ru.hogwarts.schoollion.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.service.FacultyService;
import ru.hogwarts.schoollion.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }
//    @PostMapping
//    public ResponseEntity createFaculty(@RequestBody Faculty faculty) {
//        Faculty createdFaculty = facultyService.createFaculty(faculty);
//        return ResponseEntity.ok(createdFaculty);
//    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("{facultyColor}")   // GET http://localhost:8080/faculty/red
    public ResponseEntity<Collection<Faculty>> getAllFacultyInColor(@PathVariable String facultyColor) {
        return ResponseEntity.ok(facultyService.getAllFacultyInColor(facultyColor));
    }

//    @GetMapping
//    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color) {
//        if (color != null && !color.isBlank()) {
//            return ResponseEntity.ok(facultyService.findByColor(color));
//        }
//        return ResponseEntity.ok(Collections.emptyList());
//    }

    @PutMapping()
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.deleteFaculty(facultyId));
    }
}
