package ru.hogwarts.schoollion;

// import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hogwarts.schoollion.model.Student;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchoolLionApplicationTestsSpringBoot {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateStudent() {
        Student student = givenStudentWith("studentName", 25);
        ResponseEntity<Student> response = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(response);
    }

    @Test
    public void testGetStudentById() {
        Student student = givenStudentWith("studentName", 25);
        ResponseEntity<Student> createResponse = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(createResponse);
        Student createStudent = createResponse.getBody();
        thenStudentWithIdHasBeenFound(createStudent.getId(), createStudent);
    }

    @Test
    public void testFindByAge() {
        Student student18 = givenStudentWith("studentName3", 18);
        Student student25 = givenStudentWith("studentName1", 25);
        Student student28 = givenStudentWith("studentName2", 28);
        Student student32 = givenStudentWith("studentName4", 32);

        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student18);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student25);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student28);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student32);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("age", "25");
        thenStudenAreFoundByCriteria(queryParams, student25);
    }

    @Test
    public void testFindByAgeBetween() {
        Student student18 = givenStudentWith("studentName3", 18);
        Student student25 = givenStudentWith("studentName1", 25);
        Student student28 = givenStudentWith("studentName2", 28);
        Student student32 = givenStudentWith("studentName4", 32);

        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student18);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student25);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student28);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student32);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("minAge", "20");
        queryParams.add("maxAge", "30");
        thenStudenAreFoundByCriteria(queryParams, student25, student28);
    }

    @Test
    public void testUpdate() {
        Student student = givenStudentWith("studentName", 25);

        ResponseEntity<Student> responseEntity = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(responseEntity);
        Student createdStudent = responseEntity.getBody();

        whenUpdatingStudent(createdStudent, 32, "newName");
        thenStudentHasBeenUpdated(createdStudent, 32, "newName");
    }
    @Test
    public void testDelete() {
        Student student = givenStudentWith("studentName", 25);

        ResponseEntity<Student> responseEntity = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(responseEntity);
        Student createdStudent = responseEntity.getBody();

        whenDeletingStudent(createdStudent);
        thenStudentNotFound(createdStudent);
    }

    private void whenDeletingStudent(Student createdStudent) {
        restTemplate.delete(getUriBuilder().path("/{id}").buildAndExpand(createdStudent.getId()).toUri());
    }

    private void thenStudentNotFound(Student createdStudent) {
        URI getUri = getUriBuilder().path("/{id}").buildAndExpand(createdStudent.getId()).toUri();
        ResponseEntity<Student> emptyRs = restTemplate.getForEntity(getUri, Student.class);

        Assertions.assertThat(emptyRs.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private void thenStudentWithIdHasBeenFound(Long studentId, Student student) {
        URI uri = getUriBuilder().path("/{id}").buildAndExpand(studentId).toUri();
        ResponseEntity<Student> response = restTemplate.getForEntity(uri, Student.class);
        Assertions.assertThat(response.getBody()).isEqualTo(student);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    private ResponseEntity<Student> whenSendingCreateStudentRequest(URI uri, Student student) {
        return restTemplate.postForEntity(uri, student, Student.class);
    }

    private void thenStudentHasBeenCreated(ResponseEntity<Student> response) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
    }

    private Student givenStudentWith(String name, int age) {
        return new Student(name, age);
    }

    private void resetIds(Collection<Student> students) {
        students.forEach(it -> it.setId(null));
    }

    private UriComponentsBuilder getUriBuilder() {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path("/hogwarts/student");
    }

    private void whenUpdatingStudent(Student createStudent, int newAge, String newName) {
        createStudent.setAge(newAge);
        createStudent.setName(newName);

        restTemplate.put(getUriBuilder().build().toUri(), createStudent);
    }

    private void thenStudenAreFoundByCriteria(MultiValueMap<String, String> queryParams, Student... students) {
        URI uri = getUriBuilder().queryParams(queryParams).build().toUri();

        ResponseEntity<Set<Student>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<Student>>() {
                });

        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Set<Student> actualResult = response.getBody();
        resetIds(actualResult);
        Assertions.assertThat(actualResult).containsExactlyInAnyOrder(students);
    }

    private void thenStudentHasBeenUpdated(Student createdStudent, int newAge, String newName) {
        URI getUri = getUriBuilder().path("/{id}").buildAndExpand(createdStudent.getId()).toUri();
        ResponseEntity<Student> updatedStudentRs = restTemplate.getForEntity(getUri, Student.class);

        Assertions.assertThat(updatedStudentRs.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(updatedStudentRs.getBody()).isNotNull();
        Assertions.assertThat(updatedStudentRs.getBody().getAge()).isEqualTo(newAge);
        Assertions.assertThat(updatedStudentRs.getBody().getName()).isEqualTo(newName);
    }

}