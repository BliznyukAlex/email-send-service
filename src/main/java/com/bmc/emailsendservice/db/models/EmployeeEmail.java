package com.bmc.emailsendservice.db.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "bmc_employee_email")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendorId", referencedColumnName = "id", nullable = false)
    private Vendor vendor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    private Employee employee;
}
