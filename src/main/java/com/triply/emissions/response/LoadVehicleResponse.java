package com.triply.emissions.response;

import lombok.Data;

@Data
public class LoadVehicleResponse extends BaseResponse{
    private String id;

    public LoadVehicleResponse(String message, String id){
        super(message);
        this.id = id;
    }
}
