import akka.actor.AbstractLoggingActor;
import akka.japi.pf.ReceiveBuilder;
import messages.Ack;
import messages.DataMessage;
import messages.StreamCompleted;
import messages.StreamFailure;
import messages.StreamInitialized;

public class ReceiverService extends AbstractLoggingActor {

    private String name;

    public ReceiverService(String name) {
        this.name = name;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StreamInitialized.class, init -> {
                    System.out.println("Stream initialized " + init.getStreamId() + " for " + name);
                    getSender().tell(Ack.INSTANCE, self());
                })
                .match(StreamCompleted.class, completed -> {
                    System.out.println("Stream completed " + completed.getStreamId() + " for " + name);
                })
                .match(StreamFailure.class, failed -> {
                    System.out.println(failed.getCause() + " Stream failed " + failed.getStreamId() + " for " + name);
                })
                .match(DataMessage.class, msg -> {
                    System.out.println("Data received  " + msg.toString() + " for " + name);
                    getSender().tell(Ack.INSTANCE, self());
                })
                .build();
    }

}
