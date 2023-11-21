package com.triply.emissions.command.vehicle.mileage;

import com.triply.emissions.command.BaseCommand;
import lombok.Data;

@Data
public class LoadMileageCommand extends BaseCommand {
    private String filePath;
}
