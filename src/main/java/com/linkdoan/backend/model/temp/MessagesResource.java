package com.linkdoan.backend.model.temp;

import com.linkdoan.backend.service.DocumentGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MessagesResource {

    @Autowired
    DocumentGenerationService documentGenerationService;

    @PostMapping("/public/api/items")
    public ResponseEntity getAllCountry(@RequestBody HashMap<String, String> variables, @RequestParam(name="id") Long id) {
        String result;
        UserInformation userInformation = new UserInformation();
        userInformation.setFirstName("Doan");
        userInformation.setLastName("Van Linh");
        userInformation.setMessage("Khoi tao word");
        userInformation.setSalutation("ccc");
        try {
            result = documentGenerationService.generateFile(variables,id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}