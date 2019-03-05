package app.prieto.services

import app.prieto.domain.Address
import app.prieto.domain.Member
import grails.gorm.services.Service

@Service(Address)
interface AddressService {
    int count()
    Address save(String street, String city, String state, String zipCode)
    List<Address> findAll()
    void delete(Serializable id)
}