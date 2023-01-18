package rzk.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Event;
import model.EventType;
import model.User;

/**
 * Session Bean implementation class PlanerUserBean
 */
@Stateful
@LocalBean
public class PlanerUserBean implements PlanerUserBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	User user;
	
	

    /**
     * Default constructor. 
     */
    public PlanerUserBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public User login(String email, String pass) {
		try {
		user = new User();
		
		
		List<User> users = (List<User>)em.createQuery("select u from User u where u.email=:email AND u.password=:pass",User.class)
				.setParameter("email", email)
				.setParameter("pass", pass)
				.getResultList();
		user=users.get(0);
		
		return user;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		// TODO Auto-generated method stub
		
	}
	
	public List<EventType> tipovi(){
		return em.createNamedQuery("EventType.findAll").getResultList();
		
	}

	@Override
	public boolean unesiObaveze(String desc, Date toDate, Date fromDate, int idType,User user) {
		// TODO Auto-generated method stub
		try {
			Event event = new Event();
			
			EventType et = (EventType)em.createQuery("select t from EventType t where t.id = :idType")
					.setParameter("idType",idType)
					.getSingleResult();
			
			event.setDescription(desc);
			event.setEventType(et);
			event.setFromDate(fromDate);
			event.setToDate(toDate);
			event.setUser(user);
			
			em.persist(event);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}

	@Override
	public List<Event> pretrazi(Date fromDate) {
		// TODO Auto-generated method stub
		try {
			List<Event> eventovi = em.createQuery("select e from Event e where e.fromDate like :date")
					.setParameter("date", fromDate)
					.getResultList();
			return eventovi;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

}
