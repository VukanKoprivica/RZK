package rzk;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Message-Driven Bean implementation class for: CardsQueueMessage
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/C"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/C")
public class CardsQueueMessage implements MessageListener {
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	StatelessRemote sr;

    /**
     * Default constructor. 
     */
    public CardsQueueMessage() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	try {
    		ObjectMessage objectMessage = (ObjectMessage)message;
    		CardMessage cm = (CardMessage)objectMessage.getObject();
    		System.out.println(cm);
    		sr.sendTopicMessage(cm.getCardFrom(), cm.getCardTo(), cm.getEmail(), cm.getType());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
        
    }

}
