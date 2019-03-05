package app.prieto.domain

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class Address implements GormEntity<Address> {

    String street
    String city
    String state
    String zipCode
    static belongsTo = [member: Member]

    static mapping = {

    }
    static constraints = {
        street nullable: false, blank: false
        city nullable: false, blank: false
        state nullable: false, blank: false
        zipCode nullable: false, blank: false



    }

    @Override
    String toString(){
        return street + ", "+ city +", "+state+", "+zipCode
    }
}
