package com.npichuzhkin.ProjectionsTask.controllers;

import com.npichuzhkin.ProjectionsTask.dto.EmployeeDTO;
import com.npichuzhkin.ProjectionsTask.repositories.EmployeeProjection;
import com.npichuzhkin.ProjectionsTask.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeProjection>> showAllEmployees(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> addNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.save(employeeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateEmployee(@PathVariable UUID id, @RequestBody EmployeeDTO employeeDTO){
        employeeService.update(id, employeeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeEmployee(@PathVariable UUID id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}