import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.CompletionStrategy;
import akka.stream.OverflowStrategy;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import messages.Ack;
import messages.DataMessage;
import messages.StreamCompleted;
import messages.StreamFailure;
import messages.StreamInitialized;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Responder {

    private final List<Subscriber> subscribers;
    private final ActorSystem actorSystem;

    public Responder(ActorSystem actorSystem) {
        this.subscribers = new ArrayList<>();
        this.actorSystem = actorSystem;
    }

    public void addSubscriber(ActorRef sender) {
        String streamId = UUID.randomUUID().toString();

        Source<Object, ActorRef> source =
                Source.actorRef(
                        o -> {
                            if (o instanceof StreamCompleted) return Optional.of(CompletionStrategy.draining());
                            else return Optional.empty();
                        },
                        o -> Optional.empty(),
                        65000,
                        OverflowStrategy.fail());

        Sink<Object, NotUsed> sink =
                Sink.actorRefWithBackpressure(
                        sender,
                        new StreamInitialized(streamId),
                        Ack.INSTANCE,
                        new StreamCompleted(streamId),
                        e -> new StreamFailure(e, streamId));

        ActorRef sourceRef = source.to(sink).run(actorSystem);
        Subscriber subscriber = new Subscriber(sender, sourceRef);
        this.subscribers.add(subscriber);
    }

    public void publishMessages(List<DataMessage> msgs) {
        for (DataMessage msg : msgs) {
            for (Subscriber subscriber : this.subscribers) {
                subscriber.getSourceRef().tell(msg, ActorRef.noSender());
            }
        }

    }

}
