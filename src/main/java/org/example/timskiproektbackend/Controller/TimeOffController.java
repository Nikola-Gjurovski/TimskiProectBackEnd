package org.example.timskiproektbackend.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.timskiproektbackend.DTO.CreateTimeOffDTO;
import org.example.timskiproektbackend.DTO.DisplayEmployeeDTO;
import org.example.timskiproektbackend.DTO.DisplayTimeOffDTO;
import org.example.timskiproektbackend.Model.Exceptions.VacationDaysException;
import org.example.timskiproektbackend.Model.TimeOff;
import org.example.timskiproektbackend.Service.EmployeeDetails;
import org.example.timskiproektbackend.Service.EmployeeService;
import org.example.timskiproektbackend.Service.TimeOffService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/time-offs")
@Tag(name = "Time off API", description = "Endpoint for managing time offs")
public class TimeOffController {
    private final TimeOffService timeOffService;
    private final EmployeeService employeeService;

    public TimeOffController(TimeOffService timeOffService, EmployeeService employeeService) {
        this.timeOffService = timeOffService;
        this.employeeService = employeeService;
    }

    @GetMapping("/find-all-by-employee")
    public ResponseEntity<List<TimeOff>> findAllByEmployee(@AuthenticationPrincipal EmployeeDetails employeeDetails)throws Exception{
        Long loggedInUserId = employeeService.findByEmail(employeeDetails.getUsername()).getId();
        return ResponseEntity.ok(timeOffService.findAllByEmployee(loggedInUserId));
    }

    @GetMapping("/find-all-by-manager")
    public ResponseEntity<List<DisplayTimeOffDTO>> findAllByManager(@AuthenticationPrincipal EmployeeDetails employeeDetails) throws Exception{
        Long loggedInUserId = employeeService.findByEmail(employeeDetails.getUsername()).getId();
        return ResponseEntity.ok(timeOffService.findAllByManager(loggedInUserId));
    }

    @PostMapping("/request-time-off")
    public ResponseEntity<TimeOff> requestTimeOff(@AuthenticationPrincipal EmployeeDetails employeeDetails
            ,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate)
            throws Exception{

        DisplayEmployeeDTO loggedInDTO = employeeService.findByEmail(employeeDetails.getUsername());
        return ResponseEntity.ok(
                timeOffService.createTimeOff(
                        new CreateTimeOffDTO(loggedInDTO.getId(),
                                loggedInDTO.getManager(),
                                startDate,endDate)));
    }


    @PostMapping("/approve/{timeOffId}")
    public ResponseEntity<Void> approveTimeOff(@PathVariable Long timeOffId) throws Exception {
        timeOffService.approveTimeOff(timeOffId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject/{timeOffId}")
    public ResponseEntity<Void> rejectTimeOff(@PathVariable Long timeOffId) throws Exception {
        timeOffService.rejectTimeOff(timeOffId);
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(VacationDaysException.class)
    public ResponseEntity<String> handleVacationDaysException(VacationDaysException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }





}
