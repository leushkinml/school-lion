package ru.hogwarts.schoollion.service;

import org.springframework.data.domain.PageRequest;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.repository.StudentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class TestSpeed {

//    private static StudentRepository studentRepository;
//
//    public TestSpeed(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    static List<Student> students = studentRepository.findAll(PageRequest.of(0,6)).getContent();
//



//
//
//    public static void main(String[] args) throws InterruptedException {
//
//
//        System.out.println(students);
//
//
//
//
//
//
//
//    }
}



//    long start1 = System.nanoTime();
//
//        int sum1 = Stream.iterate(1, a -> a + 1)
//                .limit(1_000_000)
//                .reduce(0, (a, b) -> a + b);
//
//        Thread.sleep(1000);
//        long finish1 = System.nanoTime();
//        long elapsed1 = finish1 - start1;
//        System.out.println("Прошло времени, 1-ый метод, нс: " + elapsed1);
//
//
//        long start2 = System.nanoTime();
//
//        int sum2 = Stream.iterate(1, a -> a + 1)
//                .limit(1_000_000)
//                .parallel()
//                .reduce(0, (a, b) -> a + b);
//
//        Thread.sleep(1000);
//        long finish2 = System.nanoTime();
//        long elapsed2 = finish2 - start2;
//        System.out.println("Прошло времени, 2-ой метод, нс: " + elapsed2);
//
//        long start3 = System.nanoTime();
//
//        int sum3 = Stream.iterate(1, a -> a + 1)
//                .parallel()
//                .limit(1_000_000)
//                .reduce(0, (a, b) -> a + b);
//
//        Thread.sleep(1000);
//        long finish3 = System.nanoTime();
//        long elapsed3 = finish3 - start3;
//        System.out.println("Прошло времени, 3-ий метод, нс: " + elapsed3);