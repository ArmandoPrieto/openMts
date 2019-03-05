package app.prieto.domain


import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class Member implements GormEntity<Member> {

    String firstName
    String lastName
    String membershipNumber
    Date dateOfBirth
    //List<Contact> contactList
    Set<MtsGroup> mtsGroups
    static hasMany = [mtsGroups: MtsGroup]
    static hasOne = [address: Address]
    static mapping = {

    }
    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        membershipNumber nullable: false, blank: false
        dateOfBirth nullable: true
        address nullable: true
    }

    @Override
    String toString(){
        return firstName + " "+ lastName
    }
}
