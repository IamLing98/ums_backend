package com.linkdoan.backend.model.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesResource {

    @Autowired
    DocxGenerator docxGenerator;

    @GetMapping("/public/api/items")
    public ResponseEntity getAllCountry() {
        String result;
        UserInformation userInformation = new UserInformation();
        userInformation.setFirstName("Doan");
        userInformation.setLastName("Van Linh");
        userInformation.setMessage("Khoi tao word");
        userInformation.setSalutation("ccc");
        try {
            result = docxGenerator.generateDocxFileFromTemplate(userInformation);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}