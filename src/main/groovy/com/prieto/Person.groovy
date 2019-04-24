package com.prieto

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class Person implements GormEntity<Person> {
    String firstName

    static constraints = {
        firstName blank:false

    }
}