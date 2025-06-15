package org.example.timskiproektbackend.Service.Impl;


import org.example.timskiproektbackend.DTO.CreateEmployeeDTO;
import org.example.timskiproektbackend.DTO.DisplayEmployeeDTO;
import org.example.timskiproektbackend.DTO.UpdateEmployeeDTO;
import org.example.timskiproektbackend.Model.Employee;
import org.example.timskiproektbackend.Model.Exceptions.VacationDaysException;
import org.example.timskiproektbackend.Repository.EmployeeRepository;
import org.example.timskiproektbackend.Service.EmployeeService;
import org.example.timskiproektbackend.Service.Mapper.EmployeeMapper;
import org.hibernate.sql.Update;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<DisplayEmployeeDTO> findAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeToDisplayEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    public Employee findById(Long id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public DisplayEmployeeDTO findByEmail(String email) throws Exception {
        Employee e = employeeRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("email not found"));
        return employeeMapper.employeeToDisplayEmployeeDTO(e);
    }

    @Override
    public DisplayEmployeeDTO create(CreateEmployeeDTO employeeDto) throws Exception {
       Employee manager = employeeRepository.findById(employeeDto.getManager()).orElseThrow(Exception::new);
       Employee e= employeeMapper.createEmployeeDtoToEmployee(employeeDto,manager);
       e.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
       return employeeMapper.employeeToDisplayEmployeeDTO(employeeRepository.save(e));
    }

    @Override
    public DisplayEmployeeDTO update(Long id, UpdateEmployeeDTO employeeDto) throws Exception {
        Employee e = employeeRepository.findById(id).orElseThrow(Exception::new);

        if(employeeDto.getFirstName()!=null){
            e.setFirstName(employeeDto.getFirstName());
        }
        if(employeeDto.getLastName()!=null){
            e.setLastName(employeeDto.getLastName());
        }
        if(employeeDto.getPassword()!=null){
            e.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        }
        if(employeeDto.getEmail()!=null){
            e.setEmail(employeeDto.getEmail());
        }
        if(employeeDto.getSalary()!=null){
            e.setSalary(employeeDto.getSalary());
        }
        if(employeeDto.getJobTitle()!=null){
            e.setJobTitle(employeeDto.getJobTitle());
        }
        if(employeeDto.getManager()!=null){
            Employee manager = employeeRepository
                    .findById(employeeDto.getManager()).orElseThrow(Exception::new);
            e.setManager(manager);
        }
        if (employeeDto.getRole() != null) {
            e.setRole(employeeDto.getRole());
        }
        return employeeMapper.employeeToDisplayEmployeeDTO(employeeRepository.save(e));
    }

    @Override
    public void delete(Long id) throws Exception {
        Employee e = employeeRepository.findById(id).orElseThrow(Exception::new);
        employeeRepository.delete(e);
    }

    @Override
    public List<DisplayEmployeeDTO> findAllEmployeesManagedBy(Long id) throws Exception {
        Employee manager = employeeRepository.findById(id).orElseThrow(Exception::new);
        return employeeRepository.findAllByManager(manager)
                .stream().map(employeeMapper::employeeToDisplayEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    public void updateVacationDays(Employee e, Long days){
        if(e.getVacationDays().longValue()-days<0){
            throw new VacationDaysException("Not enough vacation days available!");
        }
        e.setVacationDays(e.getVacationDays()-days.intValue());
        employeeRepository.save(e);
    }
}
