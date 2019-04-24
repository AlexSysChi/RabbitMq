package tutorial.simple;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.lang.Math.random;

public class Prod {
    //  setting up the queue name
    private final static String QUEUE_NAME = "queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // creation of connection to the server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        // creation of channel (where most of the API for getting things done resides)
        Channel channel = connection.createChannel();

        //  declaring the queue (for sending/publish a message to it one)
        //  this operation s idempotent (i.e. - it is created if it doesn't already exists)
        channel.queueDeclare(QUEUE_NAME,
                false,
                false,
                false,
                null);

        //  create message text
        String message = "message text with id ["  + random()  + "]" ;

        //  publish/send message to a already declared queue
        channel.basicPublish("",
                QUEUE_NAME,
                null,
                message.getBytes());    //  here we pass message content as a byte array,
                                        // as result, you can encode whatever you want
        //  put message in the console
        System.out.println("Message [" + message + "] successfully sent");

        // close opened resources
        channel.close();
        connection.close();
    }
}
