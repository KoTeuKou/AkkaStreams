import akka.actor.ActorRef;

public class Subscriber {
    private ActorRef ref;
    private ActorRef sourceRef;

    public Subscriber(ActorRef ref, ActorRef streamRef) {
        this.ref = ref;
        this.sourceRef = streamRef;
    }

    public ActorRef getRef() {
        return ref;
    }

    public ActorRef getSourceRef() {
        return sourceRef;
    }

}
