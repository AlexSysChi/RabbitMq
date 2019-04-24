package dZone;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class StartRabbit {
    private StartRabbit() throws IOException, TimeoutException {

        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("queue");
        for (int i = 0; i < 50_000; i++) {
            HashMap<String, Integer> message = new HashMap<>();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number [" + i + "] sent...");

        }
    }
    public static void main(String[] args) throws IOException, TimeoutException {
        new StartRabbit();
    }
}
