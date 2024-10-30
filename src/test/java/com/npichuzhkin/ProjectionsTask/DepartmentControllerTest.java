package com.npichuzhkin.ProjectionsTask;

import com.npichuzhkin.ProjectionsTask.controllers.DepartmentController;
import com.npichuzhkin.ProjectionsTask.dto.DepartmentDTO;
import com.npichuzhkin.ProjectionsTask.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    public void showAll_ShouldReturnDepartmentList() throws Exception {
        DepartmentDTO department = new DepartmentDTO(UUID.randomUUID(), "HR");
        Mockito.when(departmentService.findAll()).thenReturn(Collections.singletonList(department));

        mockMvc.perform(get("/api/v1/departments/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("HR"));
    }

    @Test
    public void showOne_ShouldReturnDepartmentById() throws Exception {
        UUID departmentId = UUID.randomUUID();
        DepartmentDTO department = new DepartmentDTO(departmentId, "IT");
        Mockito.when(departmentService.findById(departmentId)).thenReturn(department);

        mockMvc.perform(get("/api/v1/departments/{id}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("IT"));
    }

    @Test
    public void addNewDepartment_ShouldReturnOkStatus() throws Exception {
        String departmentJson = "{\"name\":\"Finance\"}";

        mockMvc.perform(post("/api/v1/departments/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentJson))
                .andExpect(status().isOk());

        Mockito.verify(departmentService).save(any(DepartmentDTO.class));
    }

    @Test
    public void updateDepartment_ShouldReturnOkStatus() throws Exception {
        UUID departmentId = UUID.randomUUID();
        String departmentJson = "{\"name\":\"Legal\"}";

        mockMvc.perform(put("/api/v1/departments/{id}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentJson))
                .andExpect(status().isOk());

        Mockito.verify(departmentService).update(eq(departmentId), any(DepartmentDTO.class));
    }

    @Test
    public void removeDepartment_ShouldReturnOkStatus() throws Exception {
        UUID departmentId = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/departments/{id}", departmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(departmentService).delete(eq(departmentId));
    }
}

