package com.thinkmarkets.ThinkMarketsSpringBoot.service;

import com.thinkmarkets.ThinkMarketsSpringBoot.dto.PersonDTO;
import com.thinkmarkets.ThinkMarketsSpringBoot.dto.Records;
import com.thinkmarkets.ThinkMarketsSpringBoot.entity.Person;

import java.util.List;

public interface PersonService {
    void savePersonDetails(Records records);
    PersonDTO findPersonById(int id);
    List<PersonDTO> findPersonByName(String name);
    List<PersonDTO> findPersonByAddress(String address);
    List<PersonDTO> findPersonByPhone(int phoneNumber);
}
