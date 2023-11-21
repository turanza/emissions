package com.triply.emissions.controller;

import com.triply.emissions.command.company.CreateCompanyCommand;
import com.triply.emissions.dto.company.CreateCompanyResponse;
import com.triply.emissions.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.triply.emissions.response.BaseResponse;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/company")
public class CompanyController {

    private final Logger logger = Logger.getLogger(CompanyController.class.getName());

    @Autowired
    private CompanyService companyService;

    @PostMapping(path = "/create")
    public ResponseEntity<BaseResponse> createCompany(@RequestBody CreateCompanyCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try{
            companyService.createNewCompany(command);
            return new ResponseEntity<>(new CreateCompanyResponse("Company creation request completed successfully!", id), HttpStatus.CREATED);
        }catch (IllegalStateException e){
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var safeErrorMessage = MessageFormat.format("Error while processing request to create a company for id - {0}.", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new CreateCompanyResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
