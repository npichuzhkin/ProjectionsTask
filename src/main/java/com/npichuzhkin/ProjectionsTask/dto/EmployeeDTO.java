package com.npichuzhkin.ProjectionsTask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class EmployeeDTO {

    @NotNull(message = "FirstName field must be filled")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "FirstName field can only contain letters.")
    @Size(min = 2, max = 200, message = "FirstName field must contain from 2 to 200 letters.")
    private String firstName;

    @NotNull(message = "LastName field must be filled")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "LastName field can only contain letters.")
    @Size(min = 2, max = 200, message = "LastName field must contain from 2 to 200 letters.")
    private String lastName;

    @NotNull(message = "Position field must be filled")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Position field can only contain letters.")
    @Size(min = 2, max = 100, message = "Position field must contain from 2 to 100 letters.")
    private String position;

    @NotNull(message = "DepartmentId field must be filled")
    @org.hibernate.validator.constraints.UUID
    private UUID departmentId;

    @NotNull(message = "Salary field must be filled")
    @Digits(integer = 10, fraction = 2, message = "Salary field can only contain digits with fraction 2.")
    @Min(value = 0, message = "Salary cannot be negative.")
    @Max(value = 999999999, message = "Salary - maximum value exceeded.")
    private BigDecimal salary;
}
