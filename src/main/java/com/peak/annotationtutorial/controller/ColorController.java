package com.peak.annotationtutorial.controller;

import com.peak.annotationtutorial.authorize.AuthorizeUser;
import com.peak.annotationtutorial.validation.BlackColorException;
import com.peak.annotationtutorial.validation.Color;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class ColorController {

    @Value("${application.security.enableTokenCheck}")
    private String tokenCheckingEnabled;


    @GetMapping("/color")
    @AuthorizeUser
    public Color getColorCode(@RequestHeader("access_token") String access_token,
                              @RequestParam("extraPara") String extraPara,
                              @Valid @RequestBody Color color) {

        log.info("token=" + tokenCheckingEnabled);
        log.info("log something here");
        if (color.getColorName().equalsIgnoreCase("BLACK"))
            throw new BlackColorException("BLACK") ;
        Color colorObj = new Color();
         colorObj.setColorName(color.getColorName());
        if (color.getColorName().equals("RED")) {
            colorObj.setCode("R100");
        } else if (color.getColorName().equals("GREEN")) {
            colorObj.setCode("G200");
        } else {
            colorObj.setCode("B300");
        }
        return colorObj;
    }
}
