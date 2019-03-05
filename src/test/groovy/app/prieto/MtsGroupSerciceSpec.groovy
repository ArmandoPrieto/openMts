package app.prieto

import app.prieto.domain.Member
import app.prieto.domain.MtsGroup
import app.prieto.services.MemberService
import app.prieto.services.MtsGroupService
import grails.gorm.transactions.Transactional
import io.micronaut.test.annotation.MicronautTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class MtsGroupServiceSpec extends Specification {

    @Inject
    MtsGroupService mtsGroupService

    @Inject
    MemberService memberService

    @Shared
    @AutoCleanup
    Member member

    @Shared
    @AutoCleanup
    MtsGroup mtsGroup

    @Transactional
    void "test save"() {

        when:
        mtsGroup = new MtsGroup()
        mtsGroup.name = "Nombre"
        mtsGroup.description = "Tarde"
        mtsGroup = mtsGroup.save()

        then:
        mtsGroup.findAll().size() == 1
        //mtsGroup.members.size() ==0

        when:
        member = memberService.save("Armando", "Prieto","1234")
        mtsGroup.addToMembers(member)

        then:
        mtsGroup.members.size() == 1
        member.mtsGroups.size() == 1

        when:
        mtsGroupService.save(new MtsGroup(
                name: "After School",
                description: "Program"))
        then:
        mtsGroupService.count() == 2

        when:
        member = memberService.save("Roberto","La Mata","2350")
        mtsGroup = mtsGroupService.find(2)
        mtsGroup.addToMembers(member)
        mtsGroupService.save(mtsGroup)

        then:
        mtsGroupService.find(1).members.size() == 1
        mtsGroupService.find(2).members.size() == 1
        mtsGroup.name == "After School"

    }


}
