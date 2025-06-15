package org.example.timskiproektbackend.Service;

import org.example.timskiproektbackend.DTO.CreateTimeOffDTO;
import org.example.timskiproektbackend.DTO.DisplayTimeOffDTO;
import org.example.timskiproektbackend.Model.TimeOff;

import java.time.LocalDate;
import java.util.List;

public interface TimeOffService {
    List<TimeOff> findAllByEmployee(Long id) throws Exception;

    List<DisplayTimeOffDTO> findAllByManager(Long id) throws Exception;

    TimeOff createTimeOff(CreateTimeOffDTO createTimeOffDTO) throws Exception;

    void approveTimeOff(Long timeOffId) throws Exception;

    void rejectTimeOff(Long timeOffId) throws Exception;

}
