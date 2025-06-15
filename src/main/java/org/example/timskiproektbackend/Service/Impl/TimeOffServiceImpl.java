package org.example.timskiproektbackend.Service.Impl;

import org.example.timskiproektbackend.DTO.CreateTimeOffDTO;
import org.example.timskiproektbackend.DTO.DisplayTimeOffDTO;
import org.example.timskiproektbackend.Model.Employee;
import org.example.timskiproektbackend.Model.Exceptions.VacationDaysException;
import org.example.timskiproektbackend.Model.TimeOff;
import org.example.timskiproektbackend.Model.TimeOffStatus;
import org.example.timskiproektbackend.Repository.TimeOffRepository;
import org.example.timskiproektbackend.Service.EmployeeService;
import org.example.timskiproektbackend.Service.Mapper.TimeOffMapper;
import org.example.timskiproektbackend.Service.TimeOffService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeOffServiceImpl implements TimeOffService {
    private final TimeOffRepository timeOffRepository;
    private final EmployeeService employeeService;

    private final TimeOffMapper timeOffMapper;

    public TimeOffServiceImpl(TimeOffRepository timeOffRepository, EmployeeService employeeService, TimeOffMapper timeOffMapper) {
        this.timeOffRepository = timeOffRepository;
        this.employeeService = employeeService;
        this.timeOffMapper = timeOffMapper;
    }

    @Override
    public List<TimeOff> findAllByEmployee(Long id) throws Exception {
        Employee employee=employeeService.findById(id);
        return timeOffRepository.findAllByRequestedEmployee(employee);
    }

    @Override
    public List<DisplayTimeOffDTO> findAllByManager(Long id) throws Exception {
        Employee manager=employeeService.findById(id);
        return timeOffRepository.findAllByResponsibleManager(manager)
                .stream().map(timeOffMapper::TimeOffToDisplayTimeOffDTO).collect(Collectors.toList());
    }

    @Override
    public TimeOff createTimeOff(CreateTimeOffDTO createTimeOffDTO) throws Exception {
        Employee employee = employeeService.findById(createTimeOffDTO.getRequestedEmployeeId());
        Employee manager = employeeService.findById(createTimeOffDTO.getResponsibleManagerId());
        Long days= ChronoUnit.DAYS.between(createTimeOffDTO.getStartDate(),createTimeOffDTO.getEndDate());
        if(employee.getVacationDays().longValue()-days<0){
            throw new VacationDaysException("Not enough vacation days available!");
        }
        return timeOffRepository
                .save(new TimeOff(employee,manager,createTimeOffDTO.getStartDate(),createTimeOffDTO.getEndDate()));
    }

    @Override
    public void approveTimeOff(Long timeOffId) throws Exception {
        TimeOff t=timeOffRepository.findById(timeOffId).orElseThrow(Exception::new);
        Long days= ChronoUnit.DAYS.between(t.getStartDate(),t.getEndDate());
        employeeService.updateVacationDays(t.getRequestedEmployee(),days);
        t.setStatus(TimeOffStatus.APPROVED);
        timeOffRepository.save(t);
    }

    @Override
    public void rejectTimeOff(Long timeOffId) throws Exception {
        TimeOff t=timeOffRepository.findById(timeOffId).orElseThrow(Exception::new);
        t.setStatus(TimeOffStatus.REJECTED);
        timeOffRepository.save(t);
    }
}
