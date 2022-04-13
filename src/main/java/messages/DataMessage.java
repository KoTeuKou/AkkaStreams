package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DataMessage implements Serializable {

    @JsonProperty("num")
    private int num;

    @JsonCreator
    public DataMessage(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "DataMessage{" +
                "num=" + num +
                '}';
    }
}
