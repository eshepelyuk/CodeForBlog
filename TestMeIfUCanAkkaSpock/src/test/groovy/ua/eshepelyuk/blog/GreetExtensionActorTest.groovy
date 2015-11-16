package ua.eshepelyuk.blog

import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.JavaTestKit
import spock.lang.AutoCleanup
import spock.lang.Specification

class GreetExtensionActorTest extends Specification {
    @AutoCleanup("shutdown")
    def actorSystem = ActorSystem.create()

    def probe = new JavaTestKit(actorSystem)

    def "actor should greet"() {
        given:
        def helloActor = actorSystem.actorOf(Props.create(GreetExtensionActor))
        when:
        helloActor.tell("world", probe.ref)
        then:
        def msg = probe.expectMsgClass(String)
        msg.endsWith("world") && GreetExtension.GREET_WORDS.any { msg.startsWith(it) }
    }

    def "actor should greet with mocked AKKA extension"() {
        given:
        def helloActor = actorSystem.actorOf(Props.create(GreetExtensionActor))
        and:
        GreetExtension.KEY.get(actorSystem)
        actorSystem.extensions[GreetExtension.KEY] = Stub(GreetExtension) {
            greetWord() >> "Bye"
        }
        when:
        helloActor.tell("world", probe.ref)
        then:
        probe.expectMsgClass(String) == "Bye world"
    }

    def "actor should greet with mocked AKKA extension, using Groovy extension module"() {
        given:
        def helloActor = actorSystem.actorOf(Props.create(GreetExtensionActor))
        and:
        actorSystem.mockAkkaExtension(GreetExtension.KEY, Stub(GreetExtension) {
            greetWord() >> "Bye cruel"
        })
        when:
        helloActor.tell("world", probe.ref)
        then:
        probe.expectMsgClass(String) == "Bye cruel world"
    }
}
