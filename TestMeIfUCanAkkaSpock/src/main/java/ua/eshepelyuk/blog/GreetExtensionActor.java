package ua.eshepelyuk.blog;

import akka.actor.UntypedActor;

import java.util.Objects;

public class GreetExtensionActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        sender().tell(GreetExtension.KEY.apply(context().system()).greetWord() + " " + Objects.toString(message), self());
    }
}
