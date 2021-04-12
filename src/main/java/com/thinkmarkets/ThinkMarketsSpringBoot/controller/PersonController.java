package com.thinkmarkets.ThinkMarketsSpringBoot.controller;

import com.thinkmarkets.ThinkMarketsSpringBoot.dto.PersonDTO;
import com.thinkmarkets.ThinkMarketsSpringBoot.dto.Records;
import com.thinkmarkets.ThinkMarketsSpringBoot.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thinkmarkets/")
public class PersonController {

    private static Logger loggerFactory = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<String> savePersonRecords (@RequestBody Records records) {
        loggerFactory.info("Saving Person Records");
        try {
            personService.savePersonDetails(records);
        }catch (Exception exception) {
            return ResponseEntity.badRequest()
                    .body("Please check the request again");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Data Saved Successfully");
    }


    @GetMapping(value = "/findPersonById/{id}"  ,produces = "application/json")
    public PersonDTO findPersonById (@PathVariable("id") int id) {
        PersonDTO personDTO = personService.findPersonById(id);
        return personDTO;
    }


    @GetMapping(value = "/findPersonByName/{name}")
    public List<PersonDTO> findPersonByName(@PathVariable("name") String name) {
        List<PersonDTO> personDTOList = personService.findPersonByName(name);
        return personDTOList;
    }

    @GetMapping(value = "/findPersonByAddress/{address}")
    public List<PersonDTO> findPersonByAddress(@PathVariable("address") String address) {
        List<PersonDTO> personDTOList = personService.findPersonByAddress(address);
        return personDTOList;
    }

    @GetMapping(value = "/findPersonByPhone/{phoneNumber}")
    public List<PersonDTO> findPersonByPhone(@PathVariable("phoneNumber") int phoneNumber) {
        List<PersonDTO> personDTOList = personService.findPersonByPhone(phoneNumber);
        return personDTOList;
    }
}
