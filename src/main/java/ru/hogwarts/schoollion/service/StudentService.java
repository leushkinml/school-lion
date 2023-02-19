package ru.hogwarts.schoollion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("Called method: public Student createStudent(Student student)");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        logger.debug("Called method: public Student getStudentById(Long studentId) ");
        return studentRepository.findById(studentId).orElse(null);
    }

    public List<Student> findStudentByAge(int age) {
        logger.debug("Called method: public List<Student> findStudentByAge(int age) ");
        if (age <= 0) throw new RuntimeException("The age is less than 0");
        return studentRepository.findStudentByAge(age);
    }

    public Student updateStudent(Student student) {
        logger.debug("Called method: public Student updateStudent(Student student) ");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        logger.debug("Called method: public void deleteStudent(Long studentId) ");
        studentRepository.deleteById(studentId);
    }

    public Student deleteStudentandReturn(Long studentId) {
        logger.debug("Called method: public Student deleteStudentandReturn(Long studentId) ");
        studentRepository.deleteById(studentId);
        return null;
    }

    public Collection<Student> getAllStudent() {
        logger.debug("Called method: public Collection<Student> getAllStudent() ");
        return studentRepository.findAll();
    }

    public List<Student> findStudentByAgeBetween(int minAge, int maxAge) {
        logger.debug("Called method: public List<Student> findStudentByAgeBetween(int minAge, int maxAge) ");
        return studentRepository.findStudentByAgeBetween(minAge, maxAge);
    }

    public List<Student> findStudentByNameIgnoreCase(String name) {
        logger.debug("public List<Student> findStudentByNameIgnoreCase(String name) ");
        return studentRepository.findStudentByNameIgnoreCase(name);
    }

    public Integer getCountAllStudentsInSchool() {
        logger.debug("public Integer getCountAllStudentsInSchool() ");
        return studentRepository.getCountAllStudentsInSchool();
    }

    public Integer getAvarageAgeOfAllStudentsInSchool() {
        logger.debug("Called method: public Integer getAvarageAgeOfAllStudentsInSchool()  ");
        return studentRepository.getAvarageAgeOfAllStudentsInSchool();
    }

    public List<Student> getFiveStudentsWithBiggestIdInSchool() {
        logger.debug("Called method: public List<Student> getFiveStudentsWithBiggestIdInSchool() ");
        return studentRepository.getFiveStudentsWithBiggestIdInSchool();
    }

    public List<Student> getAllStudentsByPage(Integer pageNumber, Integer pageSize) {
        logger.debug("Called method: public List<Student> getAllStudentsByPage(Integer pageNumber, Integer pageSize) ");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return studentRepository.findAll(pageRequest).getContent();
    }

    // Работа со Stream API
    public List<String> getStudentsWithNameStartWith(String letter) {

        List<Student> students = new ArrayList<>(studentRepository.findAll());
        List<String> studentsSorted = new ArrayList<>();
        studentsSorted = students.stream()
                .map(u -> u.getName())
                .filter(s -> s.startsWith(letter))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        return studentsSorted;
    }

    public List<String> getStudentsWithNameStartWithA() {

        List<Student> students = new ArrayList<>(studentRepository.findAll());
        List<String> studentsSorted = students.stream()
                .map(u -> u.getName())
                .filter(s -> s.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        return studentsSorted;
    }

    public Double studentsAverageAge() {
        List<Student> students = new ArrayList<>(studentRepository.findAll());
        Double studentsAverageAge = students.stream().
                mapToInt(Student::getAge).average().getAsDouble();
        return studentsAverageAge;
    }
}

