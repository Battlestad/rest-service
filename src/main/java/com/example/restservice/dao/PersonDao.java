package com.example.restservice.dao;

import com.example.restservice.dto.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class PersonDao {
    static int id = 1;
    private final Map<Integer, PersonDto> personList = new HashMap<>();

    private int createAndGetId() {
        return id++;
    }

    @Cacheable("persons")
    public List<PersonDto> getPersons() throws InterruptedException {
        return new ArrayList(personList.values());
    }

    @Cacheable("persons")
    public List<PersonDto> getPersonsByPostnr(int postNr) throws InterruptedException {
        // Det er sikkert en bedre måte å gjøre dette på
        ArrayList prList = new ArrayList();
        for(Map.Entry<Integer, PersonDto> entry:personList.entrySet()){
            Integer key=entry.getKey();
            PersonDto b=entry.getValue();
            if (b.getPostnr() == postNr) {
                prList.add(b);
            }
        }
        return prList;
    }

    @Cacheable(value = "persons", key = "#id")
    public Optional<PersonDto> getPerson(int id) throws InterruptedException {
        return Optional.ofNullable(personList.get(id));
    }

    @CacheEvict(value = "persons", allEntries = true)
//    @CachePut("persons")
    public PersonDto addPerson(PersonDto person) {
        int id = createAndGetId();
        person.setId(id);
        person.setName(person.getName());
        person.setAddress(person.getAddress());
        person.setPostnr(person.getPostnr());
        person.setPlace(person.getPlace());
        person.setMobile(person.getMobile());
        person.setEmail(person.getEmail());
        person.setBirthDate(person.getBirthDate());
        person.setMarketingAllowed(person.isMarketingAllowed());

        personList.put(id, person);
        return person;
    }

}
