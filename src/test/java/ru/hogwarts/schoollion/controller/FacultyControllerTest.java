//package ru.hogwarts.schoollion.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ru.hogwarts.schoollion.model.Faculty;
//import ru.hogwarts.schoollion.repository.FacultyRepository;
//import ru.hogwarts.schoollion.service.FacultyService;
//
//@WebMvcTest(FacultyController.class)
//class FacultyControllerTest {
//
//        @Autowired
//        private MockMvc mockMvc;
//
//        @MockBean
//        private FacultyRepository facultyRepository;
//
//        @SpyBean
//        private FacultyService facultyService;
//
//        @Autowired
//        private ObjectMapper objectMapper;
//
//        @InjectMocks
//        private FacultyController facultyController;
//
//        @Test
//        public void testFaculty() throws Exception {
//            final String name = "Ivanov Ivan";
//            final String color = "green";
//            final Long id = 1L;
//
//
//            JSONObject facultyObject = new JSONObject();
//            facultyObject.put("id", id);
//            facultyObject.put("name", name);
//            facultyObject.put("color", color);
//
//            Faculty faculty = new Faculty(id, name, color);
//            faculty.setId(id);
//            faculty.setName(name);
//            faculty.setColor(color);
//
//            when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(faculty));
//
//            when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
//            when(facultyRepository.findFacultiesByColorIgnoreCase(eq(color))).thenReturn((List<Faculty>) Collections.singleton(faculty));
//            //when(facultyRepository.findAllByColor(eq(color))).thenReturn(Collections.singleton(faculty));
//
//            mockMvc.perform(MockMvcRequestBuilders
//                            .post("/faculty")
//                            .content(facultyObject.toString())
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(id))
//                    .andExpect(jsonPath("$.name").value(name))
//                    .andExpect(jsonPath("$.color").value(color));
//
//            mockMvc.perform(MockMvcRequestBuilders
//                            .put("/faculty")
//                            .content(facultyObject.toString())
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(id))
//                    .andExpect(jsonPath("$.name").value(name))
//                    .andExpect(jsonPath("$.color").value(color));
//
//            mockMvc.perform(MockMvcRequestBuilders
//                            .get("/faculty/{id}" + id)
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(id))
//                    .andExpect(jsonPath("$.name").value(name))
//                    .andExpect(jsonPath("$.color").value(color));
//
//            mockMvc.perform(MockMvcRequestBuilders
//                            .get("/faculty" + color)
//                            //.get("/faculty?color=" + color)
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(id))
//                    .andExpect(jsonPath("$.name").value(name))
//                    .andExpect(jsonPath("$.color").value(color));
//
//            mockMvc.perform(MockMvcRequestBuilders
//                            .delete("/faculty/" + id)
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk());
//        }
//}