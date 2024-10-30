package com.npichuzhkin.ProjectionsTask.repositories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.npichuzhkin.ProjectionsTask.models.Department;
import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    @JsonIgnore
    String getFirstName();
    @JsonIgnore
    String getLastName();
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    @Value("#{target.getPosition()}")
    String getPosition();

    @JsonIgnore
    Department getDepartment();
    @Value("#{target.department.getName()}")
    String getDepartmentName();
}
