package com.peak.annotationtutorial.service;

import com.peak.annotationtutorial.validation.BlackColorException;
import com.peak.annotationtutorial.validation.Color;
import lombok.extern.slf4j.Slf4j;
import org.peak.common.mylog.LogMethodData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ColorService {

    @LogMethodData

    public Color getColorCode(Color color) {

        if (color.getColorName().equalsIgnoreCase("BLACK"))
            throw new BlackColorException("BLACK") ;
        String code = null ;

        if (color.getColorName().equals("RED")) {
            code ="R100";
        } else if (color.getColorName().equals("GREEN")) {
            code ="G200";
        } else {
            code ="B300";
        }
        return Color.builder()
                .colorName(color.getColorName())
                .age(color.getAge())
                .email(color.getEmail())
                .code(code)
                .ipAddress(color.getIpAddress())
                .build();
    }

    @Cacheable(cacheNames = "color")
    public Color getColorByName(String name){
        log.info("retrieve");
        return Color.builder()
                .colorName(name)
                .age(100)
                .email("jaliu@chubb.com")
                .code("100GE")
                .ipAddress("192.168.100.1")
                .build();

    }
}
