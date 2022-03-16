package com.peak.annotationtutorial.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.peak.common.myvalidation.validation.EmailValidation;
import org.peak.common.myvalidation.validation.PhoneNumberValidation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class Color {

    @ColorValidation
    private String colorName;
    @NotNull(message = "validation.notnull.code")
    private String code;


    @Max(value=100, message = "validation.max_age.code")
    @Min(value=1, message = "validation.min_age.code")
    private int age;

    @EmailValidation()
    private String email ;

    @PhoneNumberValidation()
    private String phoneNumber;

    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$", message = "validation.ipaddress.code")
    private String ipAddress;

}