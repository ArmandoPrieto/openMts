package app.prieto

import grails.gorm.transactions.Rollback
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.netty.handler.codec.http.HttpRequest
import io.reactivex.Flowable
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import static io.micronaut.http.HttpRequest.POST

@Rollback
class MemberControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.build().run(EmbeddedServer)
          //  .packages("app.prieto.domain")


    @Shared
    @AutoCleanup
    RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    void "test index"() {
        given:
        HttpResponse response = client.toBlocking().exchange("/member")


        expect:
        response.status == HttpStatus.OK

    }
    void "test save"() {
        given:
        HttpResponse response = client.toBlocking().exchange("/member/save")


        expect:
        response.status == HttpStatus.OK

    }
    void "test show"() {
        given:
        //String response = client.toBlocking().retrieve("/member/show/1")
        String response = client.toBlocking().retrieve(
               POST("/member/show/1", "Hello John")
                        .contentType(MediaType.TEXT_PLAIN_TYPE)
                       // .accept(MediaType.TEXT_PLAIN_TYPE)
        )

        expect:
        response == "Member # 1"

    }
    void "/member/show/{number} with an invalid Integer number responds 400"() {
        when:
        client.toBlocking().exchange(POST("/member/show/isNotNumber","Something")
                        .contentType(MediaType.TEXT_PLAIN_TYPE))

        then:
        HttpClientResponseException e = thrown(HttpClientResponseException)
        e.status.code == 400
    }

    void "/member/show/{number} without number responds 404"() {
        when:
        client.toBlocking().exchange("/member/show/")

        then:
        HttpClientResponseException e = thrown(HttpClientResponseException)
        e.status.code == 404
    }


}
