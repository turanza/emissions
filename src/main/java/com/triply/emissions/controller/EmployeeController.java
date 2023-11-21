package com.triply.emissions.controller;

import com.triply.emissions.command.employee.LoadNewCommand;
import com.triply.emissions.response.LoadEmployeeResponse;
import com.triply.emissions.response.BaseResponse;
import com.triply.emissions.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/employee")
public class EmployeeController {

    private final Logger logger = Logger.getLogger(EmployeeController.class.getName());

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "/loadEmployees")
    public ResponseEntity<BaseResponse> loadEmployees(@RequestBody LoadNewCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try{
            employeeService.loadFromFile(command);
            return new ResponseEntity<>(new LoadEmployeeResponse("Employees loading request processed successfully!", id), HttpStatus.CREATED);
        }catch (IllegalStateException e){
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var safeErrorMessage = MessageFormat.format("Error while processing request to load employees for id - {0}.", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new LoadEmployeeResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
