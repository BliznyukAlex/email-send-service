package com.bmc.emailsendservice.db.repositories;

import com.bmc.emailsendservice.db.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    Vendor findByName(String vendor);
}
