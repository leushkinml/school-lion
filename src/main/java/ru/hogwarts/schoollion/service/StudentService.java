package ru.hogwarts.schoollion.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.repository.FacultyRepository;
import ru.hogwarts.schoollion.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    private final Map<Long, Student> students = new HashMap<>();
//    private long generatedStudentId = 0;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
//    public Student createStudent(Student student) {
//        student.setId(generatedStudentId++);
//        students.put(student.getId(), student);
//        return student;
//    }

    public Student getStudentById(long studentId) {
        return studentRepository.findById(studentId).get();
    }
//    public Student getStudentById(long studentId) {
//        return students.get(studentId);
//    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

//    public Student updateStudent(Student student) {
//        if (!students.containsKey(student.getId())) {
//            return null;
//        }
//        students.put(student.getId(), student);
//        return student;
//    }

//    public Student updateStudent(Student student) {
//        if (students.containsKey(student.getId())) {
//            students.put(student.getId(), student);
//            return student;
//        }
//        return null;
//    }

    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

//    public Student deleteStudent(long studentId) {
//        return students.remove(studentId);
//    }

//    public Collection<Student> getAllStudentInAge(int age) {
//        return students.values().stream().filter(a-> a.getAge() == age).collect(Collectors.toList());
//    }

//    public Collection<Student> findByAge(int age) {
//        ArrayList<Student> result = new ArrayList<>();
//        for (Student student : students.values()) {
//            if (student.getAge() == age) {
//                result.add(student);
//            }
//        }
//        return result;
//    }
    public List<Student> findStudentByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}

