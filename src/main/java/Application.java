import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import messages.SubscriptionMessage;

public class Application {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create();
        ActorRef processor = actorSystem.actorOf(Props.create(Processor.class));
        for (int i = 0; i < 3; i++) {
            ActorRef subscriber = actorSystem.actorOf(Props.create(ReceiverService.class, "subscriber " + i));
            processor.tell(new SubscriptionMessage(), subscriber);
        }
    }
}
