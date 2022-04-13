package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class StreamInitialized implements Serializable {
    @JsonProperty("streamId")
    protected final String streamId;

    @JsonCreator
    public StreamInitialized(String streamId) {
        this.streamId = streamId;
    }

    @JsonProperty("streamId")
    public String getStreamId() {
        return streamId;
    }


    @Override
    public String toString() {
        return "messages.StreamInitialized{" +
                "streamId='" + streamId +
                '}';
    }
}
