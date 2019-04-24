package tutorial.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {

    private String message;
    private String queueName;

    public Sender(String message, String queueName) {
        this.message = message;
        this.queueName = queueName;
    }

    public void sendMessage() throws IOException, TimeoutException {
        // creation of connection to the server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        // creation of channel (where most of the API for getting things done resides)
        Channel channel = connection.createChannel();
        //    declaring the queue (for sending/publish a message to it one)
        //    this operation s idempotent (i.e. - it is created if it doesn't already exists)
        channel.queueDeclare(queueName,
                false,
                false,
                false,
                null);
        //  publish or send message to a already declared queue
        channel.basicPublish("",queueName, null, message.getBytes());
        //  here we pass message content as a byte array, as result, you can encode whatever you want
        //  put message in the console
        System.out.println("id: " + message + " successfully sent");
        // close opened resources
        channel.close();
        connection.close();
    }
}
