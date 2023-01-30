package ru.hogwarts.schoollion.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public Faculty deleteById(Long facultyId) {
        facultyRepository.deleteById(facultyId);
        return null;
    }

    public Faculty deleteByIdandReturn(Long facultyId) {
        Faculty deletedFaculty = getFacultyById(facultyId);
        facultyRepository.deleteById(facultyId);
        return deletedFaculty;
    }

    public Set<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }
    public Set<Faculty> findFacultyByColorOrNameIgnorCase(String color, String name) {
        return facultyRepository.findByColorOrNameIgnoreCase(color, name);
    }
    public List<Faculty> findFacultiesByNameIgnoreCase(String name) {
        return facultyRepository.findFacultiesByNameIgnoreCase(name);
    }

    public List<Faculty> findFacultiesByColorIgnoreCase(String color) {
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}

