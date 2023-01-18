package rzk;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.KursnaLista;

@MessageDriven(name = "KursnaListaProcessorMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/KL") })
public class KursnaListaProcessorMDB implements MessageListener {
	
	@PersistenceContext
	EntityManager em;

	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			KursnaListaMessage kLM = (KursnaListaMessage) objectMessage.getObject();
			writeToDB(kLM.getEuro().doubleValue(),kLM.getDolar().doubleValue());
			System.out.println("Nova kursna lista je procesirana.");
		} catch (JMSException jmse) {
			jmse.printStackTrace();
		}
	}

	public void writeToDB(double euro, double dolar){
		
		KursnaLista kl = new KursnaLista();
		kl.setEuro(euro);
		kl.setDolar(dolar);
		em.persist(kl);
		System.out.println("Values updated!");
			
	}	

	@PostConstruct
	public void connect(){
		System.out.println("KursnaListaProcessorMDB created.");
	}
	@PreDestroy
	public void cleanup(){
		System.out.println("KursnaListaProcessorMDB destroyed.");
	}
}