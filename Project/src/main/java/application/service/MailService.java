package application.service;

import application.misc.CreateRecomandation;
import application.misc.QueueProducer;
import application.model.Book;
import application.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private CreateRecomandation createRecomandation;

    public void sendRecomandation(Order order) throws IOException {
        List<Book> books = createRecomandation.recomandation(order);
        QueueProducer producer = new QueueProducer("queue");

        HashMap message = new HashMap();
        for(int i=0; i<books.size(); i++)
            message.put("books", books.get(i).toString());
        producer.sendMessage(message);
    }
}
