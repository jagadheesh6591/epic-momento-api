package com.jaga.epicmomentoapi.service.impl;

import com.jaga.epicmomentoapi.bean.Employee;
import com.jaga.epicmomentoapi.bean.Timesheet;
import com.jaga.epicmomentoapi.repository.EmployeeRepository;
import com.jaga.epicmomentoapi.repository.TimeSheetRepository;
import com.jaga.epicmomentoapi.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
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
            Employee employee = entrySetTimesheet.getValue().get(0).getEmployee();
            Map<LocalDate, Timesheet> timeSheetGroupByDate = entrySetTimesheet.getValue().stream().collect(Collectors.toMap(Timesheet::getDate, Function.identity()));
            allDates.forEach(date -> {

                if(timeSheetGroupByDate.containsKey(date)) {
                    resultTimesheet.add(timeSheetGroupByDate.get(date));
                }  else {
                    resultTimesheet.add(new Timesheet(date, null, null, employee));
                }
            });

            resultMap.put(entrySetTimesheet.getKey(), resultTimesheet);

        });

        return resultMap;
    }

    public  List<Timesheet> retrieveTimesheets(LocalDate fromDate, LocalDate toDate) {
        List<Timesheet> timesheets = timesheetRepository.findAllByDateBetweenOrderByDateAsc(fromDate, toDate);
        return timesheets;
    }
}
