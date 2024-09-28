package com.jaga.epicmomentoapi.bean;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String address;
    private LocalDate dob;
    private String email;
    private LocalDateTime lastLogIn;

    @ManyToOne
    @JoinColumn(name = "studio_id", nullable = false)
    private Studio studio;

    public Employee(String firstName, String lastName, String address, String email, Long mobileNumber, LocalDate dob, LocalDateTime lastLogin, Studio studio) {
        this.firstName=firstName;
        this.lastName = lastName;
        this.address=address;
        this.email=email;
        this.mobileNumber=mobileNumber;
        this.lastLogIn=lastLogin;
        this.dob=dob;
        this.studio=studio;
    }
}
