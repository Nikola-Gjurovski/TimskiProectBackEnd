package org.example.timskiproektbackend.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeOffRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;

    public TimeOffRequestDTO(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
