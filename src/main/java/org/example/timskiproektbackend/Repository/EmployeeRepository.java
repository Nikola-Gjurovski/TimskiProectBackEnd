package org.example.timskiproektbackend.Repository;

import org.example.timskiproektbackend.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByManager(Employee manager);

    Optional<Employee> findByEmail(String email);

}
