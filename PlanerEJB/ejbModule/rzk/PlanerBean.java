package rzk;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Event;
import model.EventType;
import model.User;

/**
 * Session Bean implementation class PlanerBean
 */
@Stateful
@LocalBean
public class PlanerBean implements PlanerBeanRemote {

	int userId;
	User user;

	@EJB
	EventTypeBean etb;
	
	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public PlanerBean() {
	}

	@Override
	public String login(String username, String password) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email LIKE :user AND u.password LIKE :pass");
		q.setParameter("user", username);
		q.setParameter("pass", password);
		List<User> users = q.getResultList();
		System.out.println(users.size());
		try {

			user = users.get(0);
			userId = users.get(0).getId();

			return users.get(0).getEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@PostConstruct
	public void setLista() {
		etb.setLista(em.createQuery("Select * from EventType").getResultList());
	}

	@Override
	public void UnesiObaveze(Date fromDate, Date toDate, int idEt) {
		// TODO Auto-generated method stub
		model.Event event  = new Event();
		model.EventType et = (EventType)em.createQuery("Select e from EventType e where e.id like :idO").setParameter("idO", idEt).getSingleResult();
		event.setFromDate(fromDate);
		event.setToDate(toDate);
		event.setDescription("Moja obaveza");
		event.setEventType(et);
		em.persist(event);
		System.out.println("Uspesno dodat");
		
	}

	@Override
	public List<Event> vratiObaveze(Date fromDate) {
		// TODO Auto-generated method stub
		
		List<Event> lista = em.createQuery("Select e from Event e where :datum between e.fromDate and e.toDate").setParameter("datum", fromDate).getResultList();
		if(lista == null)return null;
		
		return lista;
	}

	@Remove
	public void destroy() {
		
		
	}

	@Override
	public List<EventType> vratiListu() {
		// TODO Auto-generated method stub
		return etb.getLista();
	}

}
