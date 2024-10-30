package com.npichuzhkin.ProjectionsTask.controllers;

import com.npichuzhkin.ProjectionsTask.dto.DepartmentDTO;
import com.npichuzhkin.ProjectionsTask.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DepartmentDTO>> showAll(){
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> showOne(@PathVariable UUID id){
        return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> addNewDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        departmentService.save(departmentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateDepartment(@PathVariable UUID id,
                                                       @RequestBody @Valid DepartmentDTO departmentDTO){
        departmentService.update(id, departmentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeDepartment(@PathVariable UUID id){
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
