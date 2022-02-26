package com.peak.annotationtutorial.controller;

import com.peak.annotationtutorial.authorize.AuthorizeUser;
import com.peak.annotationtutorial.validation.BlackColorException;
import com.peak.annotationtutorial.validation.Color;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ColorController {
    @GetMapping("/color")
    @AuthorizeUser
    public Color getColorCode(@RequestHeader("userId") int userId,
                              @RequestParam("extraPara") String extraPara,
                              @Valid @RequestBody Color color) {

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
