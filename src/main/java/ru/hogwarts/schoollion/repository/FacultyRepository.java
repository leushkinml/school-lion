package ru.hogwarts.schoollion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoollion.model.Faculty;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultiesByNameIgnoreCase(String name);

    List<Faculty> findFacultiesByColorIgnoreCase(String color);
}
