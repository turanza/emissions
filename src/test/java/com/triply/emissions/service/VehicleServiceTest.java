package com.triply.emissions.service;

import com.triply.emissions.command.vehicle.LoadNewCommand;
import com.triply.emissions.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class VehicleServiceTest {

    private VehicleService vehicleService;
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setup(){
        vehicleRepository = Mockito.mock(VehicleRepository.class);
        vehicleService = new VehicleService(vehicleRepository);
    }

    @Test
    void loadFromFile() throws IOException {
        //given
        String filePath = "C:\\java\\pet projects\\emissions\\emissions\\src\\main\\resources\\vehicleServiceTest.csv";
        LoadNewCommand command = new LoadNewCommand();
        command.setFilePath(filePath);
        when(vehicleRepository.save(any())).thenReturn(null);
        doNothing().when(vehicleRepository).setCompanyVehicleMatch(any());
        //when
        vehicleService.loadFromFile(command);
        //then
    }

    @Test
    void save() {
    }
}