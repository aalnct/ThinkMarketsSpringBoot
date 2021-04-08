package com.thinkmarkets.ThinkMarketsSpringBoot.dao;

import com.thinkmarkets.ThinkMarketsSpringBoot.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {
    Person findPersonById(int id);
    List<Person> findPersonByName(String name);
    List<Person> findPersonByAddress(String address);
    List<Person> findPersonByPhone(int phoneNumber);
}
