package app.prieto.services

import app.prieto.domain.Address
import app.prieto.domain.Member
import grails.gorm.services.Service

@Service(Member)
interface MemberService {
    int count()
    Member save(String firstName, String lastName, String membershipNumber)
    Member save(String firstName, String lastName, String membershipNumber, Address address)
    Member save(Member member)

    List<Member> findAll()
    Member find(String firstName)
    void delete(Serializable id)
}