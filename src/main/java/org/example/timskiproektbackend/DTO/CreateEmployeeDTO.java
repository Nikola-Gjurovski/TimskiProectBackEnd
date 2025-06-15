package org.example.timskiproektbackend.DTO;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.timskiproektbackend.Model.Employee;
import org.example.timskiproektbackend.Model.Role;

@Data
public class CreateEmployeeDTO {
    @NotBlank(message = "Name is required")
    private String firstName;
    @NotBlank(message = "LastName is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "JobTitle is required")
    private String jobTitle;
    @NotNull(message = "Salary is required")
    private Integer salary;
    @NotNull(message = "Manager is required")
    private Long manager;
    private Role role;

    public CreateEmployeeDTO() {
    }

    public CreateEmployeeDTO(String firstName, String lastName, String email,
                             String password, String jobTitle,
                             Integer salary, Long manager, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.manager = manager;
        this.role=role;
    }
}
