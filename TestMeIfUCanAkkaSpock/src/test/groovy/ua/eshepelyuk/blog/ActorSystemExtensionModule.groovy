package ua.eshepelyuk.blog

import akka.actor.ActorSystem
import akka.actor.Extension
import akka.actor.ExtensionId

class ActorSystemExtensionModule {
    static <T extends Extension> void mockAkkaExtension(ActorSystem actorSystem, ExtensionId<T> extId, T mock) {
        extId.get(actorSystem)
        actorSystem.extensions[extId] = mock
    }
}
