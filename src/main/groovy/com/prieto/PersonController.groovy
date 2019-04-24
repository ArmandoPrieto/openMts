package com.prieto

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/persons")
class PersonController {

    private final PersonService personService

    PersonController(PersonService personService) {
        this.personService = personService
    }

    @Get("/")
    List<String> index() {
        personService.findAll()*.firstName
    }
}
