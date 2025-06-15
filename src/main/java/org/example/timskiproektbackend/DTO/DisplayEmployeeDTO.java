package org.example.timskiproektbackend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.timskiproektbackend.Model.Role;

@Data
public class DisplayEmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private Long manager;
    private String managerFirstName;
    private String managerLastName;
    private Integer vacationDays;
    private Role role;

    public DisplayEmployeeDTO() {
    }

    public DisplayEmployeeDTO(
                              Long id,
                              String firstName,
                              String lastName,
                              String email,
                              String jobTitle,
                              Long manager,
                              String managerFirstName,
                              String managerLastName, Role role, Integer vacationDays) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.manager = manager;
        this.managerFirstName = managerFirstName;
        this.managerLastName = managerLastName;
        this.vacationDays=vacationDays;
        this.role=role;
    }
}
