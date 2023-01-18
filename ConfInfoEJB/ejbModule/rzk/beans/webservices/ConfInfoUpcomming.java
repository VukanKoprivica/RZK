package rzk.beans.webservices;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Conference;

/**
 * Session Bean implementation class ConfInfoUpcomming
 */
@Stateless
@LocalBean
@WebService
public class ConfInfoUpcomming implements ConfInfoUpcommingRemote {

	@PersistenceContext
	EntityManager em;
    
    public ConfInfoUpcomming() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@WebMethod
	public List<Conference> getConfs(String field) {
		return em.createQuery("select c from Conference c where c.field like :fieldName and c.dateFrom > :dateToday", Conference.class)
			.setParameter("fieldName", field)
			.setParameter("dateToday", new Date())
			.getResultList();
	}

}
