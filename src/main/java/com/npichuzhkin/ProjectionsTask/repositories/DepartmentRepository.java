package com.npichuzhkin.ProjectionsTask.repositories;

import com.npichuzhkin.ProjectionsTask.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
