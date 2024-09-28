package com.jaga.epicmomentoapi.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkIn;

    @Column
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkOut;

    @Column
    private Character leaveStatus; // Y or N

    @Column
    private Character eventStatus; // H or F

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Timesheet(LocalDate date,LocalTime checkIn, LocalTime checkOut, Employee employee) {
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.employee = employee;
    }



}
