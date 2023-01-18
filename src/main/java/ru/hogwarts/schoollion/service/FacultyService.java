package ru.hogwarts.schoollion.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

//    private final Map<Long, Faculty> faculties = new HashMap<>();
//    private long generatedFacultyId = 0;


    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

//    public Faculty createFaculty(Faculty faculty) {
//        faculty.setId(++generatedFacultyId);
//        faculties.put(generatedFacultyId, faculty);
//        return faculty;
//    }
//    public Faculty createFaculty(Faculty faculty) {
//        faculty.setId(++generatedFacultyId);
//        faculties.put(generatedFacultyId, faculty);
//        return faculty;
//    }

    public Faculty getFacultyById(long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

//    public Faculty getFacultyById(long facultyId) {
//        return faculties.get(facultyId);
//    }
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

//    public Faculty updateFaculty(Faculty faculty) {
//        if (!faculties.containsKey(faculty.getId())) {
//            return null;
//        }
//        faculties.put(faculty.getId(), faculty);
//        return faculty;
//    }
//    public Faculty updateFaculty(Faculty faculty) {
//        if (faculties.containsKey(faculty.getId())) {
//            faculties.put(faculty.getId(), faculty);
//            return faculty;
//        }
//        return null;
//    }

    public void deleteFaculty(long facultyId) {
       facultyRepository.deleteById(facultyId);
    }

//    public Faculty deleteFaculty(long facultyId) {
//        return faculties.remove(facultyId);
//    }

    public List<Faculty> findFacultiesByColor(String color) {
        return facultyRepository.findFacultiesByColor(color);
    }
//    public Collection<Faculty> getAllFacultyInColor(String color) {
//        return faculties.values().stream().filter(c-> Objects.equals(c.getColor(), color)).collect(Collectors.toList());
//    }

//    public Collection<Faculty> findByColor(String color) {
//        ArrayList<Faculty> result = new ArrayList<>();
//        for (Faculty faculty : faculties.values()) {
//            if (Objects.equals(faculty.getColor(), color)) {
//                result.add(faculty);
//            }
//        }
//        return result;
//    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}

