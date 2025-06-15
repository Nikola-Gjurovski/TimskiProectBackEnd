package org.example.timskiproektbackend.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.timskiproektbackend.DTO.CreateEmployeeDTO;
import org.example.timskiproektbackend.DTO.DisplayEmployeeDTO;
import org.example.timskiproektbackend.DTO.UpdateEmployeeDTO;
import org.example.timskiproektbackend.Model.Employee;
import org.example.timskiproektbackend.Service.EmployeeDetails;
import org.example.timskiproektbackend.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee API", description = "Endpoint for managing employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/current-user")
    public ResponseEntity<DisplayEmployeeDTO>
    getCurrentUser(@AuthenticationPrincipal EmployeeDetails employeeDetails) throws Exception {
        DisplayEmployeeDTO employee = employeeService.findByEmail(employeeDetails.getUsername());
        return ResponseEntity.ok(employee);
    }


    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees.")
    @GetMapping("/all")
    public ResponseEntity<List<DisplayEmployeeDTO>> findAll(){
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }


    @Operation(summary = "Get all employees managed by x", description = "Retrieves a list of all employees managed by x using path variable.")
    @GetMapping("/find-all-managed-by")
    public ResponseEntity<List<DisplayEmployeeDTO>> findAllManagedBy(@AuthenticationPrincipal EmployeeDetails employeeDetails) throws Exception{
        Long loggedInUserId = employeeService.findByEmail(employeeDetails.getUsername()).getId();
        return ResponseEntity.ok(employeeService.findAllEmployeesManagedBy(loggedInUserId));
    }


    @Operation(
            summary = "Add a new employee",
            description = "Creates a new product based on the given CreateEmployeeDto."
    )
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DisplayEmployeeDTO> save(@RequestBody CreateEmployeeDTO employeeDTO) throws Exception{
        return ResponseEntity.ok(employeeService.create(employeeDTO));
    }

    @Operation(
            summary = "Update an existing employee", description = "Updates an employee by ID using UpdateEmployeeDto."
    )
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.employee.id")
    public ResponseEntity<DisplayEmployeeDTO> update(
            @PathVariable Long id,@RequestBody UpdateEmployeeDTO employeeDTO) throws Exception{
        return ResponseEntity.ok(employeeService.update(id,employeeDTO));
    }

    @Operation(summary = "Delete an employee", description = "Deletes an employee by his ID.")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception{
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }





}
