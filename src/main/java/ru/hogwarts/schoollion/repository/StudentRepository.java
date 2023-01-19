package ru.hogwarts.schoollion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoollion.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByAge(int age);
}
