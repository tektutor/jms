package org.tektutor;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

public class Consumer {

   public static void main(String[] args) throws Exception {
      Connection connection = null;
      //ConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:5672");
      ConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://ex-aao-amqp-0-svc.default.svc.cluster.local:5672");

      try {
         connection = connectionFactory.createConnection();

         Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

         connection.start();
         Queue queue = session.createQueue("exampleQueue");
         MessageConsumer consumer = session.createConsumer(queue);

         TextMessage m = (TextMessage) consumer.receive(5000);
         System.out.println("message = " + m.getText());

         m = (TextMessage) consumer.receive(5000);
         System.out.println("message = " + m.getText());

         m = (TextMessage) consumer.receive(5000);
         System.out.println("message = " + m.getText());

      } finally {
         if (connection != null) {
            connection.close();
         }
      }
   }
}
