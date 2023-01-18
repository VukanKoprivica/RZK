package rzk;

import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.sql.DataSource;

@MessageDriven(name = "KursnaListaProcessorMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/KL") })
public class KursnaListaProcessorMDB implements MessageListener {
	@Resource(mappedName="java:jboss/datasources/mysql")
	private DataSource dataSource;
	
	private java.sql.Connection conn = null;

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
		try {
			Statement s = conn.createStatement();
			String q = "INSERT INTO KursnaLista (euro, dolar) VALUES ("+euro+","+dolar+")";
			int rows = s.executeUpdate(q);
			if(rows!=1){
				System.out.println("Something went wrong!");
			}
			s.close();
		} catch (Exception jmse) {
			jmse.printStackTrace();
		}
	}	

	@PostConstruct
	public void connect(){
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@PreDestroy
	public void cleanup(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}