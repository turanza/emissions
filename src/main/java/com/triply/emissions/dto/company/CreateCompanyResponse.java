package com.triply.emissions.dto.company;

import com.triply.emissions.response.BaseResponse;
import lombok.Data;

@Data
public class CreateCompanyResponse extends BaseResponse{

    private String id;

    public CreateCompanyResponse(String message, String id){
        super(message);
        this.id = id;
    }

}
