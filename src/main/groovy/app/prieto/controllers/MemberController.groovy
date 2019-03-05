package app.prieto.controllers

import app.prieto.pojo.MemberPojo
import app.prieto.services.MemberService
import app.prieto.domain.Member
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.observers.ConsumerSingleObserver
import io.reactivex.observers.DisposableSingleObserver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.validation.Valid
import java.util.concurrent.CompletableFuture
import java.util.function.Function

@Controller("/member")
class MemberController {

    protected final MemberService memberService
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    MemberController(MemberService memberService) {
        this.memberService = memberService
    }

    @Get("/")
    HttpStatus index() {
        System.out.println("Here is passing")
        return HttpStatus.OK
    }
    @Post("/save")
    HttpResponse<MemberPojo> save(@Body MemberPojo member) {
        Member m = new Member()
        m.firstName = member.name
        m.lastName = member.name
        m.membershipNumber = "111"
        m= m.save()
        return !m.hasErrors()? HttpResponse.created(member) : HttpResponse.serverError(m)
    }
    @Validated
    @Consumes([MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON])
    @Post("/show/{id}")
    String show(@QueryValue Integer id) {
        logger.info("Showing member "+id)
        //Do some work
        return "Member # " +id
    }
    @Post("/complete")
    public CompletableFuture<HttpResponse<MemberPojo>> complete(@Body CompletableFuture<MemberPojo> person) {
         person.suppl

    }


}