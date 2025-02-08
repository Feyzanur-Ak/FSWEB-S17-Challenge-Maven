package com.workintech.spring17challenge.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;



public enum Grade {
    LOW(1, "Low grade"),
    MID(2, "Mid grade"),
    HIGH(3, "High grade");


private int coefficient;
private String note;

    Grade(int coefficient,String note ) {
        this.note = note;
        this.coefficient = coefficient;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public String getNote() {
        return note;
    }

}
