package com.peak.annotationtutorial.controller;

import com.peak.annotationtutorial.concurrent.ConcurrentRunner;
import com.peak.annotationtutorial.service.ColorService;
import com.peak.annotationtutorial.validation.Color;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.peak.common.myvalidation.exception.TooManyCallsException;
import org.peak.common.token.AuthorizeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;

@Slf4j
@RestController
public class ColorController {

    @Autowired
    ColorService service ;
    @Autowired
    ConcurrentRunner runner;

    @PostMapping("/color")
    @AuthorizeUser
    public Color postColorCode(@RequestHeader("access_token") String access_token,
                              @Valid @RequestBody Color color) {
        return service.getColorCode(color) ;
    }

    @GetMapping("/color")
    @AuthorizeUser
    @RateLimiter(name = "multipleRateLimiters_client1_limiter", fallbackMethod = "timelimitFallback")
    public Color getColorCode(@RequestHeader("access_token") String access_token,
                              @RequestParam("name") String name) {
        return service.getColorByName(name) ;
    }

    @GetMapping("/total")
    @AuthorizeUser
    public Integer getTotal(@RequestHeader("access_token") String access_token) {
        return runner.calculate();
    }

    public Color timelimitFallback(String access_token, String name, Throwable t) {
        throw new TooManyCallsException("client.too.many.calls");
    }

}
