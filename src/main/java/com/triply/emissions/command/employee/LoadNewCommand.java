package com.triply.emissions.command.employee;

import com.triply.emissions.command.BaseCommand;
import lombok.Data;

@Data
public class LoadNewCommand extends BaseCommand {
    private String filePath;
}
