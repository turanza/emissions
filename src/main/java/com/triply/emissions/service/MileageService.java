package com.triply.emissions.service;

import com.triply.emissions.command.vehicle.mileage.LoadMileageCommand;
import com.triply.emissions.model.Mileage;
import com.triply.emissions.repository.MileageRepository;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MileageService {

    private final MileageRepository mileageRepository;
    private final VehicleRepository vehicleRepository;

    public MileageService(MileageRepository mileageRepository, VehicleRepository vehicleRepository) {
        this.mileageRepository = mileageRepository;
        this.vehicleRepository = vehicleRepository;
    }

@Transactional
    public void loadMileage(LoadMileageCommand command) throws IOException {
        String filepath = command.getFilePath();
        List<Mileage> mileages = new ArrayList<>();
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());

        try(Reader reader = new FileReader(filepath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())){
            for(CSVRecord record : csvParser){
                Mileage mileage = new Mileage();

                mileage.setEmployeeId(Long.parseLong(record.get("employeeId")));
                mileage.setVehicleId(Long.parseLong(record.get("vehicleId")));
                mileage.setMileage(Double.parseDouble(record.get("mileage")));
                mileage.setYear(cl.get(Calendar.YEAR));
                mileage.setWeek(cl.get(Calendar.WEEK_OF_YEAR));
                mileages.add(mileage);
            }
        }
        if (mileages!=null){
            save(mileages);// maybe needed to add check for existing
        }
    }

    private void save(List<Mileage> mileages) {
        mileageRepository.saveAll(mileages);
    }

    @Transactional
    public Double getEmissionForEmployee(Long id) {
        Double mileage = 0.0;
        Double emission = 0.0;
        try{
        mileage = mileageRepository.getLastMileageForEmployee(id);
        emission = vehicleRepository.getById(mileageRepository.getVehicleId(id)).getBaseEmission();
        }catch (Exception e){}
        return mileage*emission;

    }

    public Double getEmissionForCompany() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return mileageRepository.getLastMileageForCompany(cl.get(Calendar.YEAR), cl.get(Calendar.WEEK_OF_YEAR));
    }

    @Transactional
    public Double getTotolEmissionForCompany() {
        List<Long> employeeIds = mileageRepository.getEmployeeIdsForCurCompany();
        Double totalEmission = 0.0;
        for (Long id : employeeIds
             ) {
            totalEmission+=getEmissionForEmployee(id);
        }
        return totalEmission;
    }
}
