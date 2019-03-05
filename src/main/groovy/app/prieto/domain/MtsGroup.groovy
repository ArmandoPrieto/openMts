package app.prieto.domain


import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class MtsGroup implements GormEntity<MtsGroup> {

    String name
    String description
    Set<Member> members
    static hasMany = [members: Member]
    static mapping = {

    }


    @Override
    String toString(){
        return name
    }
}
