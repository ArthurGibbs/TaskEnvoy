package service;

import restx.factory.Component;

import taskenvoy.domain.ResponseMessage;
import taskenvoy.domain.TaskRequest;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;

import java.util.Map;

@Component
public class TaskPushingService {
	
	public TaskPushingService() {
	
	}
	
	public TaskRequest pushToQue(TaskRequest request) {
		
		
		 try {
			 System.out.println("about to send message");
             // Create a ConnectionFactory
			
			 //env.put(Context.PROVIDER_URL, "vm://localhost");
             ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

             // Create a Connection
             Connection connection = connectionFactory.createConnection();
             connection.start();

             // Create a Session
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

             // Create the destination (Topic or Queue)
             Destination destination = session.createQueue("ScheduledTasks");

             // Create a MessageProducer from the Session to the Topic or Queue
             MessageProducer producer = session.createProducer(destination);
             producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

             // Create a messages
             String text = request.toString();
             TextMessage message = session.createTextMessage(text);

             // Tell the producer to send the message
             System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
             producer.send(message);

             // Clean up
             session.close();
             connection.close();
             return request;
         }
         catch (Exception e) {
             System.out.println("Caught: " + e);
             e.printStackTrace();
             return null;
         }
	}
}
