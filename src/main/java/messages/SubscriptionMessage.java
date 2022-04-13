package messages;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class SubscriptionMessage implements Serializable {
    @JsonCreator
    public SubscriptionMessage() {}
}
