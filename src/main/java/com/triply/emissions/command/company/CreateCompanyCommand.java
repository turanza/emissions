package com.triply.emissions.command.company;

import com.triply.emissions.command.BaseCommand;
import lombok.Data;

@Data
public class CreateCompanyCommand extends BaseCommand {
    private String name;
}
