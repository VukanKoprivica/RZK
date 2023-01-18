package rzk;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;

/**
 * Session Bean implementation class Stateless
 */
@Stateless
@LocalBean
public class StatelessCards implements StatelessRemote {
	
	@Inject
	JMSContext context;
	
	
	@Resource(mappedName = "java:/jms/queue/C")
	private javax.jms.Destination destinationQueue;
	
	@Resource(mappedName = "java:/jms/topic/C")
	private javax.jms.Destination destinationTopic;

    /**
     * Default constructor. 
     */
    public StatelessCards() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void sendQueueMessage(String cardFrom,String cardTo, String email, String type) {
		// TODO Auto-generated method stub
		try {
			ObjectMessage message = context.createObjectMessage();
			CardMessage card = new CardMessage(cardFrom, cardTo, email, type);
			message.setObject(card);
			
			JMSProducer producer = context.createProducer();
			producer.send(destinationQueue, message);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendTopicMessage(String cardFrom, String cardTo, String email, String type) {
		// TODO Auto-generated method stub
		try {
			ObjectMessage message = context.createObjectMessage();
			CardMessage card = new CardMessage(cardFrom, cardTo, email, type);
			message.setObject(card);
			
			JMSProducer producer = context.createProducer();
			producer.send(destinationTopic, message);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
