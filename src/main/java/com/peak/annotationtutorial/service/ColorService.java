package com.peak.annotationtutorial.service;

import com.peak.annotationtutorial.validation.BlackColorException;
import com.peak.annotationtutorial.validation.Color;
import org.peak.common.mylog.LogMethodData;
import org.springframework.stereotype.Service;

@Service
public class ColorService {

    @LogMethodData
    public Color getColorCode(Color color) {

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
