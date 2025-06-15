package org.example.timskiproektbackend.Service;

import org.example.timskiproektbackend.DTO.CreateEmployeeDTO;
import org.example.timskiproektbackend.DTO.DisplayEmployeeDTO;
import org.example.timskiproektbackend.DTO.UpdateEmployeeDTO;
import org.example.timskiproektbackend.Model.Employee;

import java.util.List;

public interface EmployeeService {
    List<DisplayEmployeeDTO> findAllEmployees();

    Employee findById(Long id) throws Exception;

    DisplayEmployeeDTO findByEmail(String email) throws Exception;

    DisplayEmployeeDTO create(CreateEmployeeDTO employee) throws Exception;

    DisplayEmployeeDTO update(Long id, UpdateEmployeeDTO employeeDto) throws Exception;

    void delete(Long id) throws Exception;

    List<DisplayEmployeeDTO> findAllEmployeesManagedBy(Long id) throws Exception;

     void updateVacationDays(Employee e, Long days);





}
