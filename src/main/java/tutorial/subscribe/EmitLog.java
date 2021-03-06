package tutorial.subscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import static tutorial.Utils.getMessage;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,
                BuiltinExchangeType.FANOUT);

        String message = getMessage(argv);

        channel.basicPublish(EXCHANGE_NAME, "", null,
                message.getBytes("UTF-8"));

        System.out.println("Sent [" + message + "]");

        channel.close();
        connection.close();
    }


}