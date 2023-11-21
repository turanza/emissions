package com.triply.emissions.response;

import com.triply.emissions.response.BaseResponse;
import lombok.Data;

@Data
public class LoadEmployeeResponse extends BaseResponse {
    private String id;

    public LoadEmployeeResponse(String message, String id){
        super(message);
        this.id = id;
    }
}
