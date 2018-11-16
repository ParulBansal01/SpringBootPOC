package com.poc.controller;

import com.poc.model.AuditorResponse;
import org.apache.commons.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    //@PostMapping(value = "/api/test/postrequest")
    @RequestMapping(value = "/api/test/postrequest",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<?> testPostRequest(@RequestBody MultiValueMap<String, String> data) {
        Set set = data.keySet();
        Iterator iterator = set.iterator();
        String s = (String) iterator.next();

        addAuditorToFile("plan_details.txt", s);
        return new ResponseEntity("Successfully Invoked -", HttpStatus.OK);
    }

    @GetMapping("/api/getAuditorList")
    public ResponseEntity<?> getAuditorList(RestController restController) {

        ClassLoader classLoader = getClass().getClassLoader();
        String str = classLoader.getResource("managers.txt").getFile();
        AuditorResponse response = new AuditorResponse();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(str));
            String line = reader.readLine();
            response.addAuditor(line);
            while (line != null) {
                System.out.println(line);
                // read next line
                line = reader.readLine();
                if (line != null) {
                    response.addAuditor(line);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        restController.logger.debug("Testing Post Request!");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    private void addAuditorToFile(String fileName, String data) {
        ClassLoader classLoader = getClass().getClassLoader();
        String str = classLoader.getResource(fileName).getFile();
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(str, true));
            out.write("\n" + data );
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileWithUtil(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            String result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
