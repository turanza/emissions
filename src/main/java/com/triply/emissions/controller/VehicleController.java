package com.triply.emissions.controller;

import com.triply.emissions.command.vehicle.LoadNewCommand;
import com.triply.emissions.response.LoadEmployeeResponse;
import com.triply.emissions.response.BaseResponse;
import com.triply.emissions.response.LoadVehicleResponse;
import com.triply.emissions.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/vehicle")
public class VehicleController {

    private final Logger logger = Logger.getLogger(EmployeeController.class.getName());

    @Autowired
    private VehicleService vehicleService;

    @PostMapping(path = "/loadVehicles")
    public ResponseEntity<BaseResponse> loadEmployyes(@RequestBody LoadNewCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);
        try{
            vehicleService.loadFromFile(command);
            return new ResponseEntity<>(new LoadVehicleResponse("Vehicles loading request processed successfully!", id), HttpStatus.CREATED);
        }catch (IllegalStateException e){
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var safeErrorMessage = MessageFormat.format("Error while processing request to load vehicles for id - {0}.", id);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new LoadVehicleResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
