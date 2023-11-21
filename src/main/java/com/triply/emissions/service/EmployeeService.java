package com.triply.emissions.service;

import com.triply.emissions.command.employee.LoadNewCommand;
import com.triply.emissions.model.Employee;
import com.triply.emissions.repository.EmployeeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void loadFromFile(LoadNewCommand command) throws IOException {
        String filepath = command.getFilePath();
        List<Employee> employees = new ArrayList<>();

        try(Reader reader = new FileReader(filepath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())){
            for(CSVRecord record : csvParser){
                Employee employee = new Employee();
                employee.setName(record.get("name"));
                employee.setSurname(record.get("surname"));
                employees.add(employee);

            }
        }
        if (employees!=null){
            save(employees);// maybe needed to add check for existing
        }
    }

    public void save(List<Employee> employees) {
        employees.stream().forEach(t->{
            employeeRepository.save(t);
            employeeRepository.setCompanyEmployeeMatch(t.getId());
        });
    }
}
