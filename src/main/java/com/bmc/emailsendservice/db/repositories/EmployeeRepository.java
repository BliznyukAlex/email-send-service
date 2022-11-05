package com.bmc.emailsendservice.db.repositories;

import com.bmc.emailsendservice.db.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
