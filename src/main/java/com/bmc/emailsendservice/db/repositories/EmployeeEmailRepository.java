package com.bmc.emailsendservice.db.repositories;

import com.bmc.emailsendservice.db.models.Employee;
import com.bmc.emailsendservice.db.models.EmployeeEmail;
import com.bmc.emailsendservice.db.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEmailRepository extends JpaRepository<EmployeeEmail, Integer> {
    EmployeeEmail findByEmployeeIdAndVendorId(Integer employeeId, Integer vendorId);
}
