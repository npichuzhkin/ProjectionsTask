package com.npichuzhkin.ProjectionsTask;

import com.npichuzhkin.ProjectionsTask.controllers.EmployeesController;
import com.npichuzhkin.ProjectionsTask.dto.EmployeeDTO;
import com.npichuzhkin.ProjectionsTask.models.Department;
import com.npichuzhkin.ProjectionsTask.repositories.EmployeeProjection;
import com.npichuzhkin.ProjectionsTask.services.EmployeeService;
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

@WebMvcTest(EmployeesController.class)
public class EmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void showAllEmployees_ShouldReturnEmployeeProjectionList() throws Exception {
        EmployeeProjection employee = new EmployeeProjection() {
            @Override
            public String getFirstName() {
                return null;
            }

            @Override
            public String getLastName() {
                return null;
            }

            @Override
            public String getFullName() {
                return "John Doe";
            }

            @Override
            public String getPosition() {
                return null;
            }

            @Override
            public Department getDepartment() {
                return null;
            }

            @Override
            public String getDepartmentName() {
                return null;
            }
        };

        Mockito.when(employeeService.findAll()).thenReturn(Collections.singletonList(employee));

        mockMvc.perform(get("/api/v1/employees/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("John Doe"));
    }

    @Test
    public void addNewEmployee_ShouldReturnOkStatus() throws Exception {

        String employeeJson = "{\n" +
                "\t\"firstName\": \"firstName\",\n" +
                "\t\"lastName\": \"lastName\",\n" +
                "\t\"position\": \"position\",\n" +
                "\t\"salary\": 20000.00,\n" +
                "\t\"departmentId\": \"" + UUID.randomUUID() + "\"\n" +
                "}";

        mockMvc.perform(post("/api/v1/employees/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isOk());

        Mockito.verify(employeeService).save(any(EmployeeDTO.class));
    }

    @Test
    public void updateEmployee_ShouldReturnOkStatus() throws Exception {
        UUID employeeId = UUID.randomUUID();
        String employeeJson = "{\n" +
                "\t\"firstName\": \"UPDFirstName\",\n" +
                "\t\"lastName\": \"UPDLastName\",\n" +
                "\t\"position\": \"UPDPosition\",\n" +
                "\t\"salary\": 40000.00,\n" +
                "\t\"departmentId\": \"" + UUID.randomUUID() + "\"\n" +
                "}";

        mockMvc.perform(put("/api/v1/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isOk());

        Mockito.verify(employeeService).update(eq(employeeId), any(EmployeeDTO.class));
    }

    @Test
    public void removeEmployee_ShouldReturnOkStatus() throws Exception {
        UUID employeeId = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(employeeService).delete(eq(employeeId));
    }
}

