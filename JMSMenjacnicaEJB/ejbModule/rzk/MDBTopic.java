package rzk;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Message-Driven Bean implementation class for: MDBTopic
 *
 */
@MessageDriven(name = "MDBTopic", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/KL") })
public class MDBTopic implements MessageListener {

    /**
     * Default constructor. 
     */
    public MDBTopic() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			KursnaListaMessage kLM = (KursnaListaMessage) objectMessage.getObject();
			System.out.println("Topic1!!! "+kLM.getEuro());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
