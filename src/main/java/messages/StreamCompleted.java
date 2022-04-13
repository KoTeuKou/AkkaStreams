package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class StreamCompleted implements Serializable {
    @JsonProperty("streamId")
    private final String streamId;

    @JsonCreator
    public StreamCompleted(String streamId) {
        this.streamId = streamId;
    }

    @JsonProperty("streamId")
    public String getStreamId() {
        return streamId;
    }

    @Override
    public String toString() {
        return "messages.StreamCompleted{" +
                "streamId='" + streamId + '\'' +
                '}';
    }
}