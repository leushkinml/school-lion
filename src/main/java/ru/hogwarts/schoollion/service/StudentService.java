package ru.hogwarts.schoollion.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long generatedStudentId = 0;

    public Student createStudent(Student student) {
        student.setId(generatedStudentId++);
        students.put(student.getId(), student);
        return student;
    }

    public Student getStudentById(long studentId) {

        return students.get(studentId);
    }
    public Student updateStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            return null;
        }
        students.put(student.getId(), student);
        return student;
    }

//    public Student updateStudent(Student student) {
//        if (students.containsKey(student.getId())) {
//            students.put(student.getId(), student);
//            return student;
//        }
//        return null;
//    }

    public Student deleteStudent(long studentId) {

        return students.remove(studentId);
    }

    public Collection<Student> getAllStudentInAge(int age) {
        return students.values().stream().filter(a-> a.getAge() == age).collect(Collectors.toList());
    }

//    public Collection<Student> findByAge(int age) {
//        ArrayList<Student> result = new ArrayList<>();
//        for (Student student : students.values()) {
//            if (student.getAge() == age) {
//                result.add(student);
//            }
//        }
//        return result;
//    }
}

