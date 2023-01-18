package rzk.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;

/**
 * Session Bean implementation class RegistrerBean
 */
@Stateless
@LocalBean
public class RegistrerBean implements RegistrerBeanRemote {

	
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public RegistrerBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean registruj(String email, String pass, String firstName, String lastName) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setEmail(email);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setPassword(pass);
		
		try {
		em.persist(u);
		return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

}
