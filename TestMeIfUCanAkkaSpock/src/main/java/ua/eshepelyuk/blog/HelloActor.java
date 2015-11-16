package ua.eshepelyuk.blog;

import akka.actor.UntypedActor;

import java.util.Objects;

public class HelloActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        sender().tell("Hello " + Objects.toString(message.toString()), self());
    }
}
