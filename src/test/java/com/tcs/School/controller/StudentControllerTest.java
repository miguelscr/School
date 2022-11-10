package com.tcs.School.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tcs.School.model.StudentModel;
import com.tcs.School.security.AuthCredentials;
import com.tcs.School.service.StudentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    public String token = "";
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private StudentService studentService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    void shouldReturnListOfStudents() throws Exception {
        when(studentService.getStudents()).thenReturn(List.of(new StudentModel(1L,"Miguel",23,"finished")));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api-students/students"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Miguel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(23))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].semester").value("finished"));
    }

    @Test
    void shouldReturnStudent() throws Exception {
        StudentModel student = new StudentModel(4L, "Miguel", 23, "finished");
        when(studentService.getStudent(4L)).thenReturn(Optional.of(student));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api-students/student/"+student.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Miguel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(23))
                .andExpect(MockMvcResultMatchers.jsonPath("$.semester").value("finished"));
    }

    @Test
    void shouldCreateStudent() throws Exception {
        this.mockMvc.perform(post("/api-students/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\":\"Catchan\",\n" +
                        "    \"age\":17,\n" +
                        "    \"semester\": \"2\"\n" +
                        "}"))
                .andExpect(status().isOk());
        verify(studentService).saveStudent(any(StudentModel.class));
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        StudentModel student = new StudentModel(4L, "Miguel", 23, "finished");
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api-students/student/"+student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Catchan\",\n" +
                                "    \"age\":17,\n" +
                                "    \"semester\": \"2\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFooDelete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api-students/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }
}