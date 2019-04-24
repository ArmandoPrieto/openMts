package com.prieto

import grails.gorm.services.Service


@Service(Person)
interface PersonService {
    Person getPerson(Serializable id)

    Person save(String firstName)

    List<Person> findAll()


}