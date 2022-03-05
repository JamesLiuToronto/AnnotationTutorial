package com.peak.annotationtutorial.validation;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Color {

    @ColorValidation
    private String colorName;
    @NotNull(message = "validation.notnull.code")
    private String code;


}