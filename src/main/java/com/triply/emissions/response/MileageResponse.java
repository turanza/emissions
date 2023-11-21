package com.triply.emissions.response;

import lombok.Data;

@Data
public class MileageResponse extends BaseResponse{
    private String id;

    public MileageResponse(String message, String id){
        super(message);
        this.id = id;
    }
}
