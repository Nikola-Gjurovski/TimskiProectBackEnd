package org.example.timskiproektbackend.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTimeOffDTO {
    private Long requestedEmployeeId;
    private Long responsibleManagerId;
    private LocalDate startDate;
    private LocalDate endDate;


    public CreateTimeOffDTO(Long requestedEmployeeId, Long responsibleManagerId, LocalDate startDate, LocalDate endDate) {
        this.requestedEmployeeId = requestedEmployeeId;
        this.responsibleManagerId = responsibleManagerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
