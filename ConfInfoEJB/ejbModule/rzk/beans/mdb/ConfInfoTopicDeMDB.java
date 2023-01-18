package rzk.beans.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import model.Conference;

/**
 * Message-Driven Bean implementation class for: ConfInfoTopicDeMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/confinfo"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") }, mappedName = "java:/jms/topic/confinfo")
public class ConfInfoTopicDeMDB implements MessageListener {

	/**
	 * Default constructor.
	 */
	public ConfInfoTopicDeMDB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		ObjectMessage om = (ObjectMessage) message;
		try {
			Conference c = (Conference) om.getObject();
			if (c.getCountryCode().equals("DE"))
				System.out.println("It is Germany.");
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
