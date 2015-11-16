package ua.eshepelyuk.blog;

import akka.actor.UntypedActor;

import java.util.Objects;

public class HelloActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        getSender().tell("Hello " + Objects.toString(message.toString()), getSelf());
    }
}
