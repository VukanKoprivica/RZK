package rzk.message;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

import conf.CountryInfoService;
import conf.CountryInfoServiceSoapType;
import model.Conference;

/**
 * Message-Driven Bean implementation class for: ConferenceMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/conference"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/conference")
public class ConferenceMDB implements MessageListener {
	
	@WebServiceRef(CountryInfoService.class)
	CountryInfoServiceSoapType cisst;
	
	@Inject
	JMSContext cmx;
	
	@PersistenceContext
	EntityManager em;
	
	@Resource(mappedName = "java:/jms/topic/confinfo")
	Destination destinationTopic;

    /**
     * Default constructor. 
     */
    public ConferenceMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	try {
			ObjectMessage om = (ObjectMessage) message;
			Conference conf = (Conference) om.getObject();
			String code = cisst.countryISOCode(conf.getCountry());
			String dialing = cisst.countryIntPhoneCode(code);
			String currency = cisst.currencyName(cisst.fullCountryInfo(code).getSCurrencyISOCode());
			
			conf.setDialingCode(dialing);
			conf.setCountryCode(code);
			conf.setCurrency(currency);
			em.persist(conf);
			
			ObjectMessage send = cmx.createObjectMessage();
			send.setObject(conf);
			JMSProducer producer = cmx.createProducer();
			producer.send(destinationTopic, send);
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    }

}
