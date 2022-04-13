package messages;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class RunTask implements Serializable {

    @JsonCreator
    public RunTask() {
    }

    @Override
    public String toString() {
        return "RunTask{}";
    }
}
