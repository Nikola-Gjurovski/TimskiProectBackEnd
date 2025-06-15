package org.example.timskiproektbackend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.timskiproektbackend.Model.Role;

@Data
public class UpdateEmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String jobTitle;
    private Integer salary;
    private Long manager;
    private Role role;

    public UpdateEmployeeDTO() {
    }

    public UpdateEmployeeDTO(String firstName, String lastName,
                             String email, String password,
                             String jobTitle, Integer salary, Long manager, Role role) {
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
