package com.prieto

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.StartupEvent

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Bootstrap implements ApplicationEventListener<StartupEvent> {

    @Inject
    PersonService personService

    @Override
    void onApplicationEvent(StartupEvent event) {
            personService.save('Donald Trump')
            personService.save('Barack Obama')
    }
}