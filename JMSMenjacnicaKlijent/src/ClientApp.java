import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rzk.SenderSLRemote;


public class ClientApp {

	private static Context initialContext;

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	public static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);

			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}
	
	public static void main(String[] args) {

			try {
				initialContext = getInitialContext();
				SenderSLRemote beanRemote = 
					(SenderSLRemote)initialContext.lookup("ejb:JMSMenjacnica/JMSMenjacnicaEJB//SenderSL!rzk.SenderSLRemote");		
				beanRemote.sendTopicMessage();
				beanRemote.sendQueueMessage();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
