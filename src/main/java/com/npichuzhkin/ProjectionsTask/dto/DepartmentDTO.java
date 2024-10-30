package com.npichuzhkin.ProjectionsTask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotNull(message = "Name field must be filled")
    @Size(min = 2, max = 200, message = "Name field must contain from 2 to 200 letters.")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Name field can only contain letters.")
    private String name;

}
