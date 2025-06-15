package org.example.timskiproektbackend.Service.Mapper;


import org.example.timskiproektbackend.DTO.DisplayTimeOffDTO;
import org.example.timskiproektbackend.Model.TimeOff;
import org.springframework.stereotype.Component;

@Component
public class TimeOffMapper {

    public DisplayTimeOffDTO TimeOffToDisplayTimeOffDTO(TimeOff t){
        return new DisplayTimeOffDTO(t.getId(),t.getStatus()
                ,t.getRequestedEmployee().getId()
                ,t.getRequestedEmployee().getFirstName(),
                t.getRequestedEmployee().getLastName(),
                t.getResponsibleManager().getId(),
                t.getResponsibleManager().getFirstName(),
                t.getResponsibleManager().getLastName(),
                t.getStartDate(),t.getEndDate());
    }
}
