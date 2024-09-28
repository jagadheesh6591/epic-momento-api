package com.jaga.epicmomentoapi;

import com.jaga.epicmomentoapi.bean.Employee;
import com.jaga.epicmomentoapi.bean.Studio;
import com.jaga.epicmomentoapi.bean.Timesheet;
import com.jaga.epicmomentoapi.repository.EmployeeRepository;
import com.jaga.epicmomentoapi.repository.StudioRepository;
import com.jaga.epicmomentoapi.repository.TimeSheetRepository;
import com.jaga.epicmomentoapi.service.TimesheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
@Slf4j
public class EpicmomentoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicmomentoapiApplication.class, args);
	}

	// Below commented code is used for testing this application
	@Bean
	public CommandLineRunner test(StudioRepository studioRepository, EmployeeRepository employeeRepository, TimeSheetRepository timeSheetRepository, TimesheetService timesheetService) {
		return args -> {

			log.info("################################Start##############################################");

			Studio nmg = new Studio("NMG","1");
			studioRepository.save(nmg);
			Employee jaga = new Employee("jaga", "thangavel", "Almere Netherlands", "jagadeesh@gmail.com", 9003344530L, LocalDate.now(), LocalDateTime.now(),nmg);
			employeeRepository.save(jaga);
			Employee sneha = new Employee("sneha", "jagadish", "Almere Netherlands", "sneha@gmail.com", 9003344777L, LocalDate.now(), LocalDateTime.now(),nmg);
			employeeRepository.save(sneha);
			Employee raja = new Employee("Raja", "Bharadhi", "Salem India", "Raja@gmail.com", 90990909090L, LocalDate.now(), LocalDateTime.now(),nmg);
			employeeRepository.save(raja);
			Employee sandy = new Employee("Sandy", "sivaji", "Salem India", "Sandy@gmail.com", 80990909090L, LocalDate.now(), LocalDateTime.now(),nmg);
			employeeRepository.save(sandy);

			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(5), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(4), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(3), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(1), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(2), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(3), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(4), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(5), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now(), LocalTime.of(9,20), LocalTime.of(19,20),jaga ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(1), LocalTime.of(9,20), LocalTime.of(19,20),sneha ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(2), LocalTime.of(9,20), LocalTime.of(19,20),sneha ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(3), LocalTime.of(9,20), LocalTime.of(19,20),sneha ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(4), LocalTime.of(9,20), LocalTime.of(19,20),sneha ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(5), LocalTime.of(9,20), LocalTime.of(19,20),sneha ));
			timeSheetRepository.save(new Timesheet(LocalDate.now(), LocalTime.of(9,20), LocalTime.of(19,20),sneha ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(1), LocalTime.of(9,20), LocalTime.of(19,20),raja ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(2), LocalTime.of(9,20), LocalTime.of(19,20),raja ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(3), LocalTime.of(9,20), LocalTime.of(19,20),raja ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(4), LocalTime.of(9,20), LocalTime.of(19,20),raja ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(5), LocalTime.of(9,20), LocalTime.of(19,20),raja ));
			timeSheetRepository.save(new Timesheet(LocalDate.now(), LocalTime.of(9,20), LocalTime.of(9,20),raja ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(1), LocalTime.of(9,20), LocalTime.of(19,20),sandy ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(2), LocalTime.of(9,20), LocalTime.of(19,20),sandy ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(3), LocalTime.of(9,20), LocalTime.of(19,20),sandy ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(4), LocalTime.of(9,20), LocalTime.of(19,20),sandy ));
			timeSheetRepository.save(new Timesheet(LocalDate.now().minusDays(5), LocalTime.of(9,20), LocalTime.of(19,20),sandy ));
			timeSheetRepository.save(new Timesheet(LocalDate.now(), LocalTime.of(9,20), LocalTime.of(19,20),sandy ));

			employeeRepository.findAll().forEach(user -> {
				log.info("User : {} ", user);
			});

//			timeSheetRepository.findAll().forEach(timesheet -> {
//				log.info("Timesheet : {} ", timesheet);
//			});

			timeSheetRepository.findAllByDateBetweenOrderByDateAsc(LocalDate.now().minusDays(1), LocalDate.now()).forEach(timesheet -> {
				log.info("Timesheet : {} ", timesheet);
			});

			timesheetService.getTimeSheetsBetweenDates(LocalDate.now().minusDays(1), LocalDate.now()).entrySet().forEach(entry -> {
				log.info(" {} : {} ", entry.getKey(), entry.getValue());
			});

			log.info("Found User By Name : {} ",employeeRepository.findByFirstName("Raja"));
			log.info("Found User By Email : {} ",employeeRepository.findByEmail("jagadeesh@gmail.com"));

			log.info("###################################End###########################################");

		};
	}
}
