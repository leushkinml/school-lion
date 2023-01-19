package ru.hogwarts.schoollion.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public List<Faculty> findFacultiesByColor(String color) {
        return facultyRepository.findFacultiesByColor(color);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}

