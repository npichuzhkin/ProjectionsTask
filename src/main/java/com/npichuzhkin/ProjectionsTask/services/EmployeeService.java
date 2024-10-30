package com.npichuzhkin.ProjectionsTask.services;

import com.npichuzhkin.ProjectionsTask.dto.EmployeeDTO;
import com.npichuzhkin.ProjectionsTask.models.Employee;
import com.npichuzhkin.ProjectionsTask.repositories.DepartmentRepository;
import com.npichuzhkin.ProjectionsTask.repositories.EmployeeProjection;
import com.npichuzhkin.ProjectionsTask.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional(readOnly = true)
    public List<EmployeeProjection> findAll(){
        return employeeRepository.findAllBy();
    }

    @Transactional
    public void save(EmployeeDTO employeeDTO){
        Employee newEmployee = new Employee(employeeDTO.getFirstName(), employeeDTO.getLastName(),
                employeeDTO.getPosition(), employeeDTO.getSalary(),
                departmentRepository.findById(employeeDTO.getDepartmentId()).orElseThrow(() -> new EntityNotFoundException("Department with such id is not found")));
        employeeRepository.save(newEmployee);
    }

    @Transactional
    public void update(UUID id, EmployeeDTO employeeDTO){
        Employee updatedEmployee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with such id is not found"));

        updatedEmployee.setFirstName(employeeDTO.getFirstName());
        updatedEmployee.setLastName(employeeDTO.getLastName());
        updatedEmployee.setPosition(employeeDTO.getPosition());
        updatedEmployee.setSalary(employeeDTO.getSalary());
        updatedEmployee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId()).orElseThrow(() -> new EntityNotFoundException("Department with such id is not found")));

        employeeRepository.save(updatedEmployee);
    }

    @Transactional
    public void delete(UUID id){
        employeeRepository.deleteById(id);
    }
}
