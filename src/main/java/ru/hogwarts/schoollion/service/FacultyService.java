package ru.hogwarts.schoollion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoollion.model.Faculty;
import ru.hogwarts.schoollion.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public Faculty deleteById(Long facultyId) {
        facultyRepository.deleteById(facultyId);
        return null;
    }

    public Faculty deleteByIdandReturn(Long facultyId) {
        Faculty deletedFaculty = getFacultyById(facultyId);
        facultyRepository.deleteById(facultyId);
        return deletedFaculty;
    }

    public Set<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }
    public Set<Faculty> findFacultyByColorOrNameIgnorCase(String color, String name) {
        return facultyRepository.findByColorOrNameIgnoreCase(color, name);
    }
    public List<Faculty> findFacultiesByNameIgnoreCase(String name) {
        return facultyRepository.findFacultiesByNameIgnoreCase(name);
    }

    public List<Faculty> findFacultiesByColorIgnoreCase(String color) {
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }


    // Работа со Stream API
    public Optional<String> getFacultyWithLongestName() {

        List<Faculty> users = new ArrayList<>(facultyRepository.findAll());
        String longestFacultyName = users.stream()
                .map(u -> u.getName())
                .sorted(Comparator.comparing(String::length).reversed())
                .findFirst().orElse("1");
        return Optional.ofNullable(longestFacultyName);
    }

    // Тестирование скорости вычисления
    public String testMaxSpeed() throws InterruptedException {
        long start1 = System.nanoTime();
        int sum1 = Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );

        Thread.sleep(1000);
        long finish1 = System.nanoTime();
        long elapsed1 = finish1 - start1;

        long start2 = System.nanoTime();
        int sum2 = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);

        Thread.sleep(1000);
        long finish2 = System.nanoTime();
        long elapsed2 = finish2 - start2;

        long start3 = System.nanoTime();
        int sum3 = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );

        Thread.sleep(1000);
        long finish3 = System.nanoTime();
        long elapsed3 = finish3 - start3;

        return "1-ый метод: Время вычисления,нс: " + elapsed1 + ". Результат вычисления: " + sum1 +
                "\n" + "2-ой метод: Время вычисления,нс: " + elapsed2 + ". Результат вычисления: " + sum2 +
                "\n" + "3-ий метод: Время вычисления,нс: " + elapsed3 + ". Результат вычисления: " + sum3;
    }
//
//    public String getMaxSpeedTwo() throws InterruptedException {
//        long start2 = System.nanoTime();
//        int sum2 = Stream.iterate(1, a -> a + 1)
//                .limit(1_000_000)
//                .parallel()
//                .reduce(0, (a, b) -> a + b);
//
//        Thread.sleep(1000);
//        long finish2 = System.nanoTime();
//        long elapsed2 = finish2 - start2;
//
//
//
//
//        return "Прошло времени, 1-ый метод, нс: " + elapsed1 + ". Результат вычисления: " + sum1;
//    }
//
//    public String getMaxSpeedThree() throws InterruptedException {
//
//        long start3 = System.nanoTime();
//        int sum3 = Stream.iterate(1, a -> a +1)
//                .parallel()
//                .limit(1_000_000)
//                .reduce(0, (a, b) -> a + b );
//
//        Thread.sleep(1000);
//        long finish3 = System.nanoTime();
//        long elapsed3 = finish3 - start3;
//        return "Прошло времени, 1-ый метод, нс: " + elapsed1 + ". Результат вычисления: " + sum1;
//    }
}

