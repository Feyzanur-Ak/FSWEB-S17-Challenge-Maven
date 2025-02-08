package com.workintech.spring17challenge.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
public enum Grade {
    LOW(1, "Low grade"),
    MID(2, "Mid grade"),
    HIGH(3, "High grade");


private int coefficient;
private String note;


}
