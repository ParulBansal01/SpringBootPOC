package com.poc.controller;

import com.poc.model.AuditorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @PostMapping("/api/test/postrequest")
    public static ResponseEntity<?> testPostRequest(RestController restController) {
        restController.logger.debug("Testing Post Request!");
        return new ResponseEntity("Successfully Invoked -", HttpStatus.OK);
    }

    @GetMapping("/api/getAuditorList")
    public static ResponseEntity<?> getAuditorList(RestController restController) {

        // Any DB operation or Business logic can be included here

        restController.logger.debug("Testing Post Request!");
        AuditorResponse response = new AuditorResponse();
        response.addAuditor("Auditor1");
        response.addAuditor("Auditor2");
        response.addAuditor("Auditor3");
        response.addAuditor("Auditor4");
        response.addAuditor("Auditor5");
        response.addAuditor("Auditor6");

        return new ResponseEntity(response, HttpStatus.OK);

    }


}
