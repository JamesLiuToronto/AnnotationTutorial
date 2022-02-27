package com.peak.annotationtutorial.controller;

import com.peak.annotationtutorial.authorize.AuthorizeUser;
import com.peak.annotationtutorial.service.ColorService;
import com.peak.annotationtutorial.validation.Color;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class ColorController {

    @Autowired
    ColorService service ;

    @GetMapping("/color")
    @AuthorizeUser
    public Color getColorCode(@RequestHeader("access_token") String access_token,
                              @Valid @RequestBody Color color) {
        return service.getColorCode(color) ;
    }
}
