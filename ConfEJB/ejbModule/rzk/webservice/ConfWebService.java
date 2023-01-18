package rzk.webservice;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Conference;

/**
 * Session Bean implementation class ConfWebService
 */
@Stateless
@LocalBean
@WebService
public class ConfWebService implements ConfWebServiceRemote {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ConfWebService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Conference> confList(String field) {
		// TODO Auto-generated method stub
		List<Conference> list = em.createQuery("SELECT C FROM Conference c WHERE c.field LIKE :field "
				+ "AND c.dateFrom > :fromDate",Conference.class)
				.setParameter("field", field).setParameter("fromDate", new Date()).getResultList();
		/*
		 * for(Conference c : list){ System.out.println(c); };
		 */
		return list;
	}

}
