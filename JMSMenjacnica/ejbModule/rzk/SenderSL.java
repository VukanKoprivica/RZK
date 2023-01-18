package rzk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.sql.DataSource;


/**
 * Session Bean implementation class SenderSL
 */
@Stateless
public class SenderSL implements SenderSLRemote {
	
	@Inject
	JMSContext context;
	
	@Resource(mappedName="java:/jms/queue/KL")
	private javax.jms.Destination destinationQueue;
	
	@Resource(mappedName="java:/jms/topic/KL")
	private javax.jms.Destination destinationTopic;

	@Resource(mappedName="java:jboss/datasources/mysql")
	private DataSource dataSource;

	private java.sql.Connection conn;
	
	/**
	 * Default constructor. 
	 */
	public SenderSL() {
		// TODO Auto-generated constructor stub
	}

	public void sendQueueMessage(){

		try {
			
			ObjectMessage message = context.createObjectMessage();
			KursnaListaMessage kL = new KursnaListaMessage();
			kL.setSender("Narodna Banka Srbije");
			kL.setEuro(new Double(122.5));
			kL.setDolar(new Double(97.4));
			message.setObject(kL);
			
			JMSProducer producer = context.createProducer();
			producer.send(destinationQueue, message);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendTopicMessage(){

		try {
			
			ObjectMessage message = context.createObjectMessage();
			KursnaListaMessage kL = new KursnaListaMessage();
			kL.setSender("Narodna Banka Srbije");
			kL.setEuro(new Double(122.5));
			kL.setDolar(new Double(97.4));
			message.setObject(kL);
			
			JMSProducer pub = context.createProducer();
			pub.send(destinationTopic, message);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tryDB(){
			try {
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("SELECT euro FROM KursnaLista");
				if(rs.next()){
					System.out.println(rs.getString(1));
				}
				rs.close();
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
