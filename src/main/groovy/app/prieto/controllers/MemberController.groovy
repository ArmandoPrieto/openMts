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
import io.reactivex.observers.DisposableSingleObserver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.validation.Valid
import java.util.concurrent.CompletableFuture

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
       /* Member m = memberService.save("Armando Prieto",30)
        System.out.println(m.name +" "+m.age)

        Member armando = memberService.find("Armando Prieto")
        System.out.println(armando.name +" "+armando.age)*/
        //memberService.delete(m.ident())

        return HttpStatus.OK
    }
    @Get("/save")
    HttpStatus save() {
        logger.info("saving member")
        //Do some work
        logger.debug("member saved")
        logger.warn("this should be a warn")
        logger.error("this should be an error")

        return HttpStatus.OK
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
    public CompletableFuture<HttpResponse<Person>> complete(@Body CompletableFuture<MemberPojo> person) {
         person.suppl

    }
    @Post("/savePerson")
    public String savePerson(@Body Single<MemberPojo> person) {
        MemberPojo member
        person.subscribe(new SingleObserver<MemberPojo>(){

            @Override
            void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            void onSuccess(@NonNull MemberPojo memberPojo) {
                System.out.println("Success: " + memberPojo)
            }

            @Override
            void onError(@NonNull Throwable e) {

            }
        })
       /* Disposable d = person.subscribeWith(new DisposableSingleObserver<MemberPojo>() {
            @Override
            public void onStart() {
                System.out.println("Started")
            }

            @Override
            public void onSuccess(MemberPojo value) {
                System.out.println("Success: " + value)
                member = value
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace()
            }
        })*/
        return member.toString()
        /*  logger.info(person.toString())
        return person.toString()
        */
    }

}