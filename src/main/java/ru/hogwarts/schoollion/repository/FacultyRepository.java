package ru.hogwarts.schoollion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoollion.model.Faculty;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultiesByNameIgnoreCase(String name);

    List<Faculty> findFacultiesByColorIgnoreCase(String color);

    Set<Faculty> findByColor(String color);

    Set<Faculty> findByColorOrNameIgnoreCase(String color, String name);

    Optional<Faculty> findById(Long id);

    Faculty getById(Long id);

    void deleteById(Long id);
}
