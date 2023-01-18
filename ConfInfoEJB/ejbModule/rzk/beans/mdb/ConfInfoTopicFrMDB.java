package rzk.beans.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import model.Conference;

/**
 * Message-Driven Bean implementation class for: ConfInfoTopicFrMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/confinfo"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") }, mappedName = "java:/jms/topic/confinfo")
public class ConfInfoTopicFrMDB implements MessageListener {

	public ConfInfoTopicFrMDB() {
	}

	public void onMessage(Message message) {
		ObjectMessage om = (ObjectMessage) message;
		try {
			Conference c = (Conference) om.getObject();
			if (c.getCountryCode().equals("FR"))
				System.out.println("It is France.");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
