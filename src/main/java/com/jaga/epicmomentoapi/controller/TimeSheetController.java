package com.jaga.epicmomentoapi.controller;

import com.jaga.epicmomentoapi.bean.Timesheet;
import com.jaga.epicmomentoapi.service.TimesheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/timesheets")
@Slf4j
public class TimeSheetController {

    @Autowired
    private TimesheetService timesheetService;

    @PostMapping("/{employeeId}")
    public Timesheet logTimesheet(
            @PathVariable Long employeeId,
            @RequestBody Timesheet timesheet
    ) {
        return timesheetService.saveTimesheet(employeeId, timesheet);
    }

    @GetMapping("/between")
    public List<Timesheet> getTimeSheetsBetweenDates(
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate
    ) throws InterruptedException {

       // Thread.sleep(1000000l);
        LocalDate from = LocalDate.parse(fromDate); // Parse the 'from' date from the query param
        LocalDate to = LocalDate.parse(toDate);     // Parse the 'to' date from the query param

        return timesheetService.retrieveTimesheets(from, to);
    }

    @GetMapping("/groupByEmp")
    public Map<String, List<Timesheet>> getTimeSheetsBetweenDatesGroupByEmp(
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate
    ) throws InterruptedException {

        // Thread.sleep(1000000l);
        LocalDate from = LocalDate.parse(fromDate); // Parse the 'from' date from the query param
        LocalDate to = LocalDate.parse(toDate);     // Parse the 'to' date from the query param

        return timesheetService.getTimeSheetsBetweenDates(from, to);
    }
}
