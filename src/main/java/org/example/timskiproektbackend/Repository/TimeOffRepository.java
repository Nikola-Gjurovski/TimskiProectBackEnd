package org.example.timskiproektbackend.Repository;

import org.example.timskiproektbackend.Model.Employee;
import org.example.timskiproektbackend.Model.TimeOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeOffRepository extends JpaRepository<TimeOff,Long> {
    List<TimeOff> findAllByRequestedEmployee(Employee employee);
    List<TimeOff> findAllByResponsibleManager(Employee employee);
}
