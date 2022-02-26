package com.peak.annotationtutorial.validation;

import lombok.Data;

@Data
public class Color {

    @ColorValidation
    private String colorName;

    private String code;


}