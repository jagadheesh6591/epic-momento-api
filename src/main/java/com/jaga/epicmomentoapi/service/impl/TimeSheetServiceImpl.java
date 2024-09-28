package com.jaga.epicmomentoapi.service.impl;

import com.jaga.epicmomentoapi.bean.Employee;
import com.jaga.epicmomentoapi.bean.Timesheet;
import com.jaga.epicmomentoapi.repository.EmployeeRepository;
import com.jaga.epicmomentoapi.repository.TimeSheetRepository;
import com.jaga.epicmomentoapi.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TimeSheetServiceImpl implements TimesheetService {

    @Autowired
    private TimeSheetRepository timesheetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public TimeSheetServiceImpl() {
    }

    @Override
    public Timesheet saveTimesheet(Long employeeId, Timesheet timesheet) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        timesheet.setEmployee(employee);
        timesheet.setDate(LocalDate.now()); // You can set the date as current
        timesheet.setCheckIn(LocalTime.now()); // Set the current time for check-in

        return timesheetRepository.save(timesheet);
    }

    // New method to fetch timesheets between two dates
    public Map<String, List<Timesheet>> getTimeSheetsBetweenDates(LocalDate fromDate, LocalDate toDate) {
        List<Timesheet> timesheets = timesheetRepository.findAllByDateBetweenOrderByDateAsc(fromDate, toDate);

        List<LocalDate> allDates = fromDate.datesUntil(toDate.plusDays(1)).collect(Collectors.toList());
        Map<String, List<Timesheet>> resultMap = new HashMap<>();
        Map<String, List<Timesheet>> timeSheetsMap = timesheets.stream()
                .collect(Collectors.groupingBy(timesheet ->
                        timesheet.getEmployee().getFirstName()));

        timeSheetsMap.entrySet().forEach(entrySetTimesheet -> {
            List<Timesheet> resultTimesheet = new ArrayList<>();
            allDates.forEach(date -> {
                entrySetTimesheet.getValue().forEach(timesheet -> {
                    if(timesheet.getDate().equals(date)) {
                        resultTimesheet.add(timesheet);
                    } else {
                        resultTimesheet.add(new Timesheet(date,null,null,timesheet.getEmployee()));
                    }
                });
            });

            resultMap.put(entrySetTimesheet.getKey(), resultTimesheet);

        });

        return timeSheetsMap;
    }

    public  List<Timesheet> retrieveTimesheets(LocalDate fromDate, LocalDate toDate) {
        List<Timesheet> timesheets = timesheetRepository.findAllByDateBetweenOrderByDateAsc(fromDate, toDate);
        return timesheets;
    }
}
