package tutorial.simple;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//  This class [Cons] is pushed messages from the broker,
//  so unlike the [Prod], which is publish a singe message
// (at least in the example), it keep running for message listening
//  and print them out (after succeed listened)
public class Cons {

    //  setting up the queue name
    private final static String QUEUE_NAME = "queue";

    public static void main(final String[] args) throws IOException,
            TimeoutException {

        //  open connection, channel and declare queue
        // (from which we're going to consume messages)
        //  declared queue matches up with the this one
        // that "publish" a message in Prod.class
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,
                // queue declared here because we might start consumer before the publisher
                                            // and we want to make sure the queue exists before we try to consume message
                false,
                false,
                false,
                null);
        //  put message in the console
        System.out.println("Waiting for messages. To exit press CTRL+F2");

        //  declare object for buffering asynchronously pushed messages until process them
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                String message = new String(body,"UTF-8");
                System.out.println(message + " received successfully");
            }
        };        channel.basicConsume(QUEUE_NAME, true, consumer );


//        for (int i = 0; i < 5; i++ ) {
//            new Sender("message [" +  round(random()*100000000) + "]",
//            "queue").sendMessage();
//        }
    }
}
