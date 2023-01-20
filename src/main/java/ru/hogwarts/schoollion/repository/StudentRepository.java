package ru.hogwarts.schoollion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByAge(int age);

    List<Student> findStudentByAgeBetween(int minAge, int maxAge);



    // Варианты кода получения списка студентов факультета.
    List<Student> findStudentByFaculty(Faculty faculty);

    //Faculty findFacultyByStudentsContains(Long faculty_id);

}
