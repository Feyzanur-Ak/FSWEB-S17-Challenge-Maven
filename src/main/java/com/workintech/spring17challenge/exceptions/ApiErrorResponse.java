package com.workintech.spring17challenge.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private int status;
    private  String message;
    private long timestamp;


}
