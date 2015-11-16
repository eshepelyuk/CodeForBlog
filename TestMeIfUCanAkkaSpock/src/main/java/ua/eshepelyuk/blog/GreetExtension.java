package ua.eshepelyuk.blog;

import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.ExtensionKey;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GreetExtension implements Extension {

    public static final ExtensionKey<GreetExtension> KEY = new ExtensionKey<GreetExtension>(GreetExtension.class) {
    };

    protected ExtendedActorSystem actorSystem;

    public GreetExtension(ExtendedActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    public static final List<String> GREET_WORDS = Arrays.asList("Hello", "Nice to meet you", "What's up");

    public String greetWord() {
        return GREET_WORDS.get(new Random().nextInt(GREET_WORDS.size()));
    }
}
