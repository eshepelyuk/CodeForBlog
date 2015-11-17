package ua.eshepelyuk.blog;

import akka.actor.UntypedActor;

import java.util.Objects;

public class GreetExtensionActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        GreetExtension greetExtension = GreetExtension.KEY.get(context().system());
        sender().tell(greetExtension.greetWord() + " " + Objects.toString(message), self());
    }
}
