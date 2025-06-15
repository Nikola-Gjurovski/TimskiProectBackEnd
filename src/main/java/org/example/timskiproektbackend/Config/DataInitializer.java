package org.example.timskiproektbackend.Config;

import jakarta.annotation.PostConstruct;
import org.example.timskiproektbackend.Model.Employee;
import org.example.timskiproektbackend.Model.Role;
import org.example.timskiproektbackend.Model.TimeOff;
import org.example.timskiproektbackend.Model.TimeOffStatus;
import org.example.timskiproektbackend.Repository.EmployeeRepository;
import org.example.timskiproektbackend.Repository.TimeOffRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    private final TimeOffRepository timeOffRepository;

    public DataInitializer(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, TimeOffRepository timeOffRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.timeOffRepository = timeOffRepository;
    }

  // @PostConstruct
    public void initData(){
        Employee e1= new Employee("Matej",
                "Samardziev",
                "msa@gmail.com",
                passwordEncoder.encode("password123"),
                "ceo",10000,null, Role.ADMIN);
        Employee e2= new Employee("Bob",
                "Dylan",
                "bd@gmail.com",
                passwordEncoder.encode("bd13bv"),"dev",1000,e1,Role.MANAGER);
        employeeRepository.save(e1);
        employeeRepository.save(e2);
        /*
        TimeOff t1=new TimeOff(e2,e1, LocalDate.now(),LocalDate.of(2025,4,7));
        TimeOff t2=new TimeOff(TimeOffStatus.APPROVED,e2,e1,LocalDate.now(),LocalDate.of(2025,4,2));
        timeOffRepository.save(t1);
        timeOffRepository.save(t2);

         */
    }
}
