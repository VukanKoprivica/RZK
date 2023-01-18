package rzk.bean;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import model.Conference;
import model.ConferencePredavac;

/**
 * Session Bean implementation class ConfStatelessBean
 */
@Stateless
@LocalBean
public class ConfStatelessBean implements ConfStatelessBeanRemote {

	@Inject
	JMSContext cnt;
	
	@Resource(mappedName = "java:/jms/queue/conference")
	Destination destinationQueue;
	
	
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ConfStatelessBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean addConference(Conference conference) {
		// TODO Auto-generated method stub
		try {
			ObjectMessage om = cnt.createObjectMessage();
			om.setObject(conference);
			JMSProducer produce = cnt.createProducer();
			produce.send(destinationQueue, om);
			return true;
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	


	@Override
	public boolean register(ConferencePredavac p) {
		// TODO Auto-generated method stub
		try {
			em.persist(p);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}

	@Override
	public List<Conference> getConf(String ime, String prezime) {
		// TODO Auto-generated method stub
		
		return em.createQuery("SELECT c FROM Conference c INNER JOIN c.conferencePredavacs cp where cp.ime like :name and "
				+ "cp.prezime like :lastName",Conference.class)
				.setParameter("name", ime)
				.setParameter("lastName", prezime)
				.getResultList();
				
	}

}
