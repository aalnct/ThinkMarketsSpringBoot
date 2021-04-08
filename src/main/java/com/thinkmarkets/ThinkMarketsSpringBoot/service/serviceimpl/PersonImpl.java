package com.thinkmarkets.ThinkMarketsSpringBoot.service.serviceimpl;

import com.thinkmarkets.ThinkMarketsSpringBoot.dao.PersonRepository;
import com.thinkmarkets.ThinkMarketsSpringBoot.dto.PersonDTO;
import com.thinkmarkets.ThinkMarketsSpringBoot.dto.Records;
import com.thinkmarkets.ThinkMarketsSpringBoot.entity.Person;
import com.thinkmarkets.ThinkMarketsSpringBoot.service.PersonService;
import com.thinkmarkets.ThinkMarketsSpringBoot.util.TransformPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void savePersonDetails(Records personEntityList) {
        List<Person> personEntity = transformDTOTOEntity(personEntityList);
        try {
            personRepository.saveAll(personEntity);
        }catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public List<Person> transformDTOTOEntity(Records personList) {
        List<Person> personEntityList = null;
        try {
            personEntityList = TransformPerson.transformPerson(personList);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return personEntityList;
    }

    @Override
    public PersonDTO findPersonById(int id) {
        PersonDTO personDTO = new PersonDTO();
        try {
            Person person = personRepository.findPersonById(id);
            if (person != null) {
                personDTO.setName(person.getName());
                personDTO.setAddress(person.getAddress());
                personDTO.setPhone(person.getPhone());
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }

        return personDTO;
    }

    @Override
    public List<PersonDTO> findPersonByName(String name) {
        List<Person> personList = personRepository.findPersonByName(name);
        List<PersonDTO> personDTOList = new ArrayList<>();
        PersonDTO personDTO;
        if (personList != null && personList.size() > 0) {
            for (Person person : personList) {
                personDTO = new PersonDTO();
                personDTO.setName(person.getName());
                personDTO.setPhone(person.getPhone());
                personDTO.setAddress(person.getAddress());

                personDTOList.add(personDTO);
            }
        }
        return personDTOList;
    }

    @Override
    public List<PersonDTO> findPersonByAddress(String address) {
        List<Person> personList = personRepository.findPersonByAddress(address);
        List<PersonDTO> personDTOList = new ArrayList<>();
        PersonDTO personDTO;
        if (personList!=null && personList.size() >0) {
            for (Person person : personList){
                personDTO = new PersonDTO();
                personDTO.setName(person.getName());
                personDTO.setAddress(person.getAddress());
                personDTO.setPhone(person.getPhone());

                personDTOList.add(personDTO);
            }
        }
        return personDTOList;
    }

    @Override
    public List<PersonDTO> findPersonByPhone(int phoneNumber) {
        List<Person> personList = personRepository.findPersonByPhone(phoneNumber);
        List<PersonDTO> personDTOList = new ArrayList<>();
        PersonDTO personDTO;
        if (personList != null && personList.size() >0) {
            for (Person person : personList) {
                personDTO = new PersonDTO();
                personDTO.setName(person.getName());
                personDTO.setAddress(person.getAddress());
                personDTO.setPhone(person.getPhone());

                personDTOList.add(personDTO);
            }
        }
        return personDTOList;
    }
}
