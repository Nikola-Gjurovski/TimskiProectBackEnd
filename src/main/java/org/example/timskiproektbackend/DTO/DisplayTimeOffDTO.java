package org.example.timskiproektbackend.DTO;


import lombok.Data;
import org.example.timskiproektbackend.Model.TimeOffStatus;


import java.time.LocalDate;

@Data
public class DisplayTimeOffDTO {
    private Long id;
    private TimeOffStatus status;
    private Long requestedEmployeeId;
    private String requestedEmployeeName;
    private String requestedEmployeeLastName;
    private Long responsibleManagerId;
    private String responsibleManagerName;
    private String responsibleManagerLastName;
    private LocalDate startDate;
    private LocalDate endDate;

    public DisplayTimeOffDTO(Long id,
                             TimeOffStatus status,
                             Long requestedEmployeeId,
                             String requestedEmployeeName,
                             String requestedEmployeeLastName,
                             Long responsibleManagerId,
                             String responsibleManagerName,
                             String responsibleManagerLastName,
                             LocalDate startDate,
                             LocalDate endDate) {
        this.id = id;
        this.status = status;
        this.requestedEmployeeId = requestedEmployeeId;
        this.requestedEmployeeName = requestedEmployeeName;
        this.requestedEmployeeLastName = requestedEmployeeLastName;
        this.responsibleManagerId = responsibleManagerId;
        this.responsibleManagerName = responsibleManagerName;
        this.responsibleManagerLastName = responsibleManagerLastName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
