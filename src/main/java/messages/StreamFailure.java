package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class StreamFailure implements Serializable {
    @JsonProperty("cause")
    private final Throwable cause;
    @JsonProperty("streamId")
    private final String streamId;

    @JsonCreator
    public StreamFailure(Throwable cause, String streamId) {
        this.cause = cause;
        this.streamId = streamId;
    }

    @JsonProperty("cause")
    public Throwable getCause() {
        return cause;
    }

    @JsonProperty("streamId")
    public String getStreamId() {
        return streamId;
    }

    @Override
    public String toString() {
        return "messages.StreamFailure{" +
                "cause=" + cause +
                ", streamId='" + streamId + '\'' +
                '}';
    }
}