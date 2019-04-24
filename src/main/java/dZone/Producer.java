package dZone;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

class Producer extends EndPoint {

    Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
    }

}
