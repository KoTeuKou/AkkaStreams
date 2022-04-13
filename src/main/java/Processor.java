import akka.actor.AbstractLoggingActor;
import akka.japi.pf.ReceiveBuilder;
import messages.DataMessage;
import messages.RunTask;
import messages.SubscriptionMessage;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Processor extends AbstractLoggingActor {

    private final Responder responder;

    public Processor() {
        this.responder = new Responder(getContext().getSystem());
        scheduleRepeatableTask(1000, 2000);

    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(SubscriptionMessage.class, msg -> {
                    System.out.println("Subscription came into processor " + msg);
                    responder.addSubscriber(getSender());
                })
                .match(RunTask.class, msg -> {
                    List<DataMessage> dataMessages = new ArrayList<>();
                    for (int k = 0; k < 3; k++) {
                        dataMessages.add(new DataMessage(k));
                    }
                    responder.publishMessages(dataMessages);
                })
                .build();
    }

    public void scheduleRepeatableTask(int initDelay, int timeInterval) {
        context().system().scheduler().schedule(
                Duration.apply(initDelay, TimeUnit.MILLISECONDS),
                Duration.apply(timeInterval, TimeUnit.MILLISECONDS),
                getSelf(),
                new RunTask(),
                context().dispatcher(),
                getSelf());
    }
}
