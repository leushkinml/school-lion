package ru.hogwarts.schoollion.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    public List<Student> findStudentByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {

        studentRepository.deleteById(studentId);
    }
    public Student deleteStudentandReturn(Long studentId) {
        studentRepository.deleteById(studentId);
        return null;
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findStudentByAgeBetween(minAge, maxAge);
    }

    public List<Student> findStudentByNameIgnoreCase(String name) {
        return studentRepository.findStudentByNameIgnoreCase(name);
    }

    public Integer getCountAllStudentsInSchool() {
        return studentRepository.getCountAllStudentsInSchool();
    }

    public Integer getAvarageAgeOfAllStudentsInSchool() {
        return studentRepository.getAvarageAgeOfAllStudentsInSchool();
    }

    public List<Student> getFiveStudentsWithBiggestIdInSchool() {
        return studentRepository.getFiveStudentsWithBiggestIdInSchool();
    }
    public List<Student> getAllStudentsByPage(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return studentRepository.findAll(pageRequest).getContent();
    }
}

