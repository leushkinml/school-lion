package ru.hogwarts.schoollion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.service.FacultyService;

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

    @GetMapping   // GET http://localhost:8080/faculty
    public ResponseEntity getAllFaculty(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String color,
                                        @RequestParam(required = false) Long id) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultiesByNameIgnoreCase(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultiesByColorIgnoreCase(color));
        }
        if (id != null && id > 0) {
            return ResponseEntity.ok(facultyService.getFacultyById(id));
        }
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

//    @GetMapping("{studentName}")
//    public ResponseEntity<String > findFacultyByStudentsIgnoreCase(@RequestParam(required = false) String studentName) {
//        if (studentName != null && !studentName.isBlank()) {
//            return ResponseEntity.ok(facultyService.findFacultyByStudentsIgnoreCase(studentName));
//        }
//        return ResponseEntity.notFound().build();
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
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }
}
