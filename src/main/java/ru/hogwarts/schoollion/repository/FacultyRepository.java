package ru.hogwarts.schoollion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultiesByColorIgnoreCase(String color);

    List<Faculty> findFacultiesByNameIgnoreCase(String name);

    //    Faculty findFacultyByStudentsIgnoreCase(Collection<Student> students);
//   Faculty findFacultyByStudentsContains(Long faculty_id);


}
