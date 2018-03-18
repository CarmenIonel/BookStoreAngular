package consumer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import connection.EndPoint;
import org.apache.commons.lang.SerializationUtils;
import service.MailService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueueConsumer extends EndPoint implements Runnable, Consumer{

    private MailService mailService = new MailService("ionel.carmen1995@gmail.com","Cristof95");
    private String message;
    public QueueConsumer(String endPointName) throws IOException {
        super(endPointName);
    }

    public void run() {
        try {
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer registered");
    }

    public void handleDelivery(String consumerTag, Envelope env,
                               BasicProperties props, byte[] body) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(body);
        String message=map.get("books").toString();
        mailService.sendMail("nimigean.mihnea@gmail.com","Based on your order we recommand:",message);

    }

    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}