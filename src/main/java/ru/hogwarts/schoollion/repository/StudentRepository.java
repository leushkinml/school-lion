package ru.hogwarts.schoollion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.service.StudentByType;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByNameIgnoreCase(String name);
    List<Student> findStudentByAge(int age);
    List<Student> findStudentByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getCountAllStudentsInSchool();

    @Query(value = "select avg(age) as average from student", nativeQuery = true)
    Integer getAvarageAgeOfAllStudentsInSchool();

    @Query(value = "SELECT * FROM student ORDER BY id DESC limit 5", nativeQuery = true)
    List<Student> getFiveStudentsWithBiggestIdInSchool();

}
