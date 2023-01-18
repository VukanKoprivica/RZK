package rzk.beans.session;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Conference;
import model.ConferencePredavac;

/**
 * Session Bean implementation class ConfInfoSB
 */
@Stateless
@LocalBean
public class ConfInfoSB implements ConfInfoSBRemote {


	@Inject
	JMSContext context;
	
	@Resource(mappedName="java:/jms/queue/conference")
	private javax.jms.Destination destinationQueue;
	
	@PersistenceContext
	EntityManager em;
	
    public ConfInfoSB() {
    }

	@Override
	public boolean addConference(Conference conference) {
		ObjectMessage om = context.createObjectMessage();
		try {
			om.setObject(conference);
			JMSProducer prod = context.createProducer();
			prod.send(destinationQueue, om);
			return true;
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean register(ConferencePredavac cp) {
		try {
			em.persist(cp);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Conference> getConfs(String ime, String prezime) {
		return em.createQuery("select c from Conference c inner join c.conferencePredavacs p where p.ime like :ime "
				+ "and p.prezime like :prez", Conference.class)
			.setParameter("ime", ime)
			.setParameter("prez", prezime)
			.getResultList();
	}

}
