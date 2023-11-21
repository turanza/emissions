package com.triply.emissions.command.vehicle;

import com.triply.emissions.command.BaseCommand;
import com.triply.emissions.response.BaseResponse;
import lombok.Data;

@Data
public class LoadNewCommand extends BaseCommand {
    private String filePath;
}
