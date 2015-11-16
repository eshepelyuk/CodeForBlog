package ua.eshepelyuk.blog

import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.JavaTestKit
import spock.lang.AutoCleanup
import spock.lang.Specification

class HelloActorTest extends Specification {

    @AutoCleanup("shutdown")
    def actorSystem = ActorSystem.create()

    def probe = new JavaTestKit(actorSystem)

    def "actor should say hello"() {
        given:
        def helloActor = actorSystem.actorOf(Props.create(HelloActor))
        when:
        helloActor.tell("world", probe.ref)
        then:
        probe.expectMsgEquals("Hello world")
    }
}
