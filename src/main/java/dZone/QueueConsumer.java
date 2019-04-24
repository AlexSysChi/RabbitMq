package dZone;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class QueueConsumer extends EndPoint implements Runnable, Consumer {
    public QueueConsumer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void run() {
        try {
            //  start consuming messages. Auto acknowledge messages
            channel.basicConsume(endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  Called when consumer is registered
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Cons [" + consumerTag + "] registered - OK");
    }

    public void handleCancelOk(String consumerTag) {

    }

    public void handleCancel(String consumerTag) throws IOException {

    }

    public void handleShutdownSignal(String consumerTag,
                                     ShutdownSignalException e) {

    }

    public void handleRecoverOk(String consumerTag) {

    }

    public void handleDelivery(String consumerTag,
                               Envelope envelope,
                               AMQP.BasicProperties basicProperties,
                               byte[] bytes) throws IOException {
        Map map = (HashMap)SerializationUtils.deserialize(bytes);
        System.out.println("Message number " + map.get("message number") + " received !");
    }
}
