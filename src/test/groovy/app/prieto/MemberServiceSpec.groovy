package app.prieto

import app.prieto.domain.Address
import app.prieto.domain.Member
import app.prieto.services.AddressService
import app.prieto.services.MemberService
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class MemberServiceSpec extends Specification {

    @Inject
    MemberService memberService
    @Inject
    AddressService addressService

    void "test save"() {

        expect:
        memberService.count() == 0

        when:
        memberService.save(
                "Armando",
                "Prieto",
                new Address(street: "920 S Soto",
                        city: "Los Angeles",
                        state: "CA",
                        zipCode:"90023"))

        then:
        memberService.count() == 1
        addressService.count() == 1

        and:
        memberService.save("Peter","Otool")

        then:
        memberService.findAll().size() == 2

        and:
        memberService.delete(1)

        then:
        memberService.count() == 1
        addressService.count() == 0
    }


}
