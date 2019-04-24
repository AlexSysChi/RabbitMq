package dZone;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class EndPoint {

    Channel channel;
    Connection connection;
    String endPointName;

    public EndPoint(String endPointName) throws IOException, TimeoutException {
        this.endPointName = endPointName;
        //  Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();
        // hostname of your rabbitmq sever
        factory.setHost("localhost");
        // getting connection
        connection = factory.newConnection();
        //  creating a channel
        channel = connection.createChannel();
        //  declaring a queue for this channel. If queue does not exist, it will be created on the server.
        channel.queueDeclare(endPointName, false, false, false, null);
    }
    /*
    *   Close channel and connection. Not nexesary as it happens implicitly any way
    *   @throws IOException
    */
    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
