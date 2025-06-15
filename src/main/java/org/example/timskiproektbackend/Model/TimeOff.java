package org.example.timskiproektbackend.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
public class TimeOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TimeOffStatus status;
    @ManyToOne
    private Employee requestedEmployee;
    @ManyToOne
    private Employee responsibleManager;
    private LocalDate startDate;
    private LocalDate endDate;

    public TimeOff() {
    }

    public TimeOff(TimeOffStatus status,
                   Employee requestedEmployee,
                   Employee responsibleManager,
                   LocalDate startDate,
                   LocalDate endDate) {
        this.status = status;
        this.requestedEmployee = requestedEmployee;
        this.responsibleManager = responsibleManager;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TimeOff(Employee requestedEmployee, Employee responsibleManager, LocalDate startDate, LocalDate endDate) {
        this.requestedEmployee = requestedEmployee;
        this.responsibleManager = responsibleManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = TimeOffStatus.PENDING;
    }
}
