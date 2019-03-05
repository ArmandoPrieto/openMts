package app.prieto.services

import app.prieto.domain.Member
import app.prieto.domain.MtsGroup
import grails.gorm.services.Service

@Service(MtsGroup)
interface MtsGroupService {
    int count()
    MtsGroup save(MtsGroup mtsGroup)
    MtsGroup find(Serializable id)
    List<MtsGroup> findAll()
    void delete(Serializable id)


}