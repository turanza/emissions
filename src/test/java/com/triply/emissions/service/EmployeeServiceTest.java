package com.triply.emissions.service;

import com.triply.emissions.command.employee.LoadNewCommand;
import com.triply.emissions.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    public EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup(){
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void loadFromFile() throws IOException {
        //given
        String filePath = "C:\\java\\pet projects\\emissions\\emissions\\src\\main\\resources\\employeeServiceTest.csv";
        LoadNewCommand command = new LoadNewCommand();
        command.setFilePath(filePath);
        when(employeeRepository.save(any())).thenReturn(null);
        doNothing().when(employeeRepository).setCompanyEmployeeMatch(any());
        //when
        employeeService.loadFromFile(command);
        //then
    }

    @Test
    void save() {
    }
}