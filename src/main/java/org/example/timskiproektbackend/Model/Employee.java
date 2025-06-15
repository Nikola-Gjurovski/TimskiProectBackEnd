package org.example.timskiproektbackend.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String jobTitle;
    private Integer vacationDays;
    private Integer salary;
    @ManyToOne // Many employees can have the same manager
    private Employee manager;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Employee() {
    }

    public Employee(String firstName, String lastName,
                    String email, String password, String jobTitle,
                    Integer salary, Employee manager, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.manager = manager;
        this.role = role;
        this.vacationDays=20;
    }
}
