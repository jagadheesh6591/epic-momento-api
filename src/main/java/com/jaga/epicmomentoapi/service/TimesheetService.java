package com.jaga.epicmomentoapi.service;

import com.jaga.epicmomentoapi.bean.Timesheet;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TimesheetService {
    Timesheet saveTimesheet(Long employeeId, Timesheet timesheet);
    List<Timesheet> retrieveTimesheets(LocalDate fromDate, LocalDate toDate);
    Map<String, List<Timesheet>> getTimeSheetsBetweenDates(LocalDate from, LocalDate to);
}
