package com.triply.emissions.service;

import com.triply.emissions.command.vehicle.LoadNewCommand;
import com.triply.emissions.model.Vehicle;
import com.triply.emissions.repository.VehicleRepository;
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
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public void loadFromFile(LoadNewCommand command) throws IOException {
        String filepath = command.getFilePath();
        List<Vehicle> vehicles = new ArrayList<>();

        try(Reader reader = new FileReader(filepath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())){
            for(CSVRecord record : csvParser){
                Vehicle vehicle = new Vehicle();
                vehicle.setLicensePlate(record.get("licensePlate"));
                vehicle.setType(record.get("type"));
                vehicle.setBaseEmission(Double.parseDouble(record.get("baseEmission")));
                vehicles.add(vehicle);
            }
        }
        if (vehicles!=null){
            save(vehicles);// maybe needed to add check for existing
        }
    }

    public void save(List<Vehicle> vehicles) {
        vehicles.stream().forEach(t->{
            vehicleRepository.save(t);
//            t.setId(vehicleRepository.getIdByParams());
            vehicleRepository.setCompanyVehicleMatch(1l);
        });
        vehicleRepository.saveAll(vehicles);
    }
}
