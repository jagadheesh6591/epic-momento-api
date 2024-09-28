package com.jaga.epicmomentoapi.repository;

import com.jaga.epicmomentoapi.bean.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeSheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findAllByDateBetweenOrderByDateAsc(LocalDate fromDate, LocalDate toDate);
    List<Timesheet> findAllByDateBetweenOrderByEmployeeId(LocalDate fromDate, LocalDate toDate);


}
