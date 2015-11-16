package ua.eshepelyuk.blog;

import akka.actor.UntypedActor;

public class GreetExtensionActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        getSender().tell(GreetExtension.KEY.apply(context().system()).greetWord() + " " + message.toString(), context().self());
    }
}
