package com.triply.emissions.service;

import com.triply.emissions.command.vehicle.mileage.LoadMileageCommand;
import com.triply.emissions.repository.MileageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MileageServiceTest {

    public MileageService mileageService;
    private MileageRepository mileageRepository;

    @BeforeEach
    public void setup(){
        mileageRepository = Mockito.mock(MileageRepository.class);
        mileageService = new MileageService(mileageRepository, vehicleRepository);
    }

    @Test
    public void loadMileageTest() throws IOException {
        //given
        String filePath = "C:\\java\\pet projects\\emissions\\emissions\\src\\main\\resources\\mileageServiceTest.csv";
        LoadMileageCommand command = new LoadMileageCommand();
        command.setFilePath(filePath);
        when(mileageRepository.saveAll(any())).thenReturn(null);
        //when
        mileageService.loadMileage(command);
        //then

    }

}