package org.example.timskiproektbackend.Service.Mapper;

import org.example.timskiproektbackend.DTO.CreateEmployeeDTO;
import org.example.timskiproektbackend.DTO.DisplayEmployeeDTO;
import org.example.timskiproektbackend.DTO.UpdateEmployeeDTO;
import org.example.timskiproektbackend.Model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee createEmployeeDtoToEmployee(CreateEmployeeDTO e, Employee manager){
    return new Employee(e.getFirstName(),
            e.getLastName(),
            e.getEmail(),
            e.getPassword(),
            e.getJobTitle(),
            e.getSalary(),
            manager,
            e.getRole());
    }

    public DisplayEmployeeDTO employeeToDisplayEmployeeDTO(Employee e){
        return new DisplayEmployeeDTO
                (e.getId(),e.getFirstName(), e.getLastName(),e.getEmail(),e.getJobTitle(),
                        e.getManager() != null ? e.getManager().getId(): null,
                        e.getManager() != null ? e.getManager().getFirstName(): null,
                        e.getManager() != null ? e.getManager().getLastName(): null,
                        e.getRole(),
                        e.getVacationDays());
    }

    public Employee updateEmployeeDtoToEmployee(UpdateEmployeeDTO e, Employee manager){
        return new Employee(e.getFirstName(),
                e.getLastName(),
                e.getEmail(),
                e.getPassword(),
                e.getJobTitle(),
                e.getSalary(),
                manager,
                e.getRole());
    }
}
