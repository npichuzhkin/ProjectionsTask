package com.npichuzhkin.ProjectionsTask.services;

import com.npichuzhkin.ProjectionsTask.dto.DepartmentDTO;
import com.npichuzhkin.ProjectionsTask.models.Department;
import com.npichuzhkin.ProjectionsTask.repositories.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll(){
        return departmentRepository.findAll().stream().
                map(e -> new DepartmentDTO(e.getId(), e.getName())).
                collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DepartmentDTO findById(UUID id){
        Department department = departmentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Department with such id is not found"));

        return new DepartmentDTO(department.getId(), department.getName());
    }

    @Transactional
    public void save(DepartmentDTO departmentDTO){
        departmentRepository.save(new Department(departmentDTO.getName()));
    }

    @Transactional
    public void update(UUID id, DepartmentDTO departmentDTO){
        departmentRepository.save(new Department(id, departmentDTO.getName()));
    }

    @Transactional
    public void delete(UUID id){
        departmentRepository.deleteById(id);
    }
}
