package com.triply.emissions.controller;

import com.triply.emissions.command.vehicle.mileage.LoadMileageCommand;
import com.triply.emissions.response.BaseResponse;
import com.triply.emissions.response.MileageResponse;
import com.triply.emissions.service.MileageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/mileage")
public class MileageController {

    private final Logger logger = Logger.getLogger(MileageController.class.getName());

    private final MileageService mileageService;

    public MileageController(MileageService mileageService) {
        this.mileageService = mileageService;
    }

    @PostMapping(path = "/loadMileage")
    public ResponseEntity<BaseResponse> loadMileage(@RequestBody LoadMileageCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try {
        mileageService.loadMileage(command);
        return new ResponseEntity<>(new MileageResponse("Mileage loading request processed successfully!", id), HttpStatus.CREATED);
        }catch (IllegalStateException e){
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var safeErrorMessage = MessageFormat.format("Error while processing request to load vehicles for id - {0}.", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new MileageResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getForEmployee/{id}")
    public ResponseEntity<BaseResponse> getEmissionForEmployee(@PathVariable(value = "id")Long id){
        try {
            Double mileageForEmpolyee = mileageService.getEmissionForEmployee(id);
            return new ResponseEntity<>(new BaseResponse(MessageFormat.format("The emission for employee {0} is {1}!",id.toString(),
                    mileageForEmpolyee.toString())),HttpStatus.OK);
        }catch (IllegalStateException e){
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var safeErrorMessage = "Error while processing request to show mileage for employee.";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getForCompany")
    public ResponseEntity<BaseResponse> getEmissionForCurrentCompany(){
        try {
            Double mileageForEmployee = mileageService.getEmissionForCompany();
            Double totalEmissionForCompany = mileageService.getTotolEmissionForCompany();
            return new ResponseEntity<>(new BaseResponse(MessageFormat.format("The mileage for company is {0} and total emission for this week is {1}!",
                    mileageForEmployee.toString(), totalEmissionForCompany.toString())),
                    HttpStatus.OK);
        }catch (IllegalStateException e){
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var safeErrorMessage ="Error while processing request to show mileage for company.";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
