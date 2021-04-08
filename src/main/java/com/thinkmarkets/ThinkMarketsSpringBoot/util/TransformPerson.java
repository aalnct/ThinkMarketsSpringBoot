package com.thinkmarkets.ThinkMarketsSpringBoot.util;

import com.thinkmarkets.ThinkMarketsSpringBoot.dto.PersonDTO;
import com.thinkmarkets.ThinkMarketsSpringBoot.dto.Records;
import com.thinkmarkets.ThinkMarketsSpringBoot.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class TransformPerson {

    public static List<Person> transformPerson(Records personList) {
        List<Person> personEntityList = new ArrayList<>();
        Person person;

        for (PersonDTO persondto : personList.getRecords()) {
            person = new Person();
            person.setName(persondto.getName());
            person.setAddress(persondto.getAddress());
            person.setPhone(persondto.getPhone());

            personEntityList.add(person);
        }
        return personEntityList;
    }
}
