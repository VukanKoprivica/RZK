package rzk;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Blog;
import rzk.interceptor.BrojPregledaInterceptor;

/**
 * Session Bean implementation class PretragaBean
 */
@Stateless
@LocalBean
public class PretragaBean implements PretragaBeanRemote {

	
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public PretragaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@Interceptors(BrojPregledaInterceptor.class)
	public List<Blog> pretrazi(String tekst) {
		// TODO Auto-generated method stub
		
		return em.createQuery("SELECT b FROM Blog b WHERE b.text LIKE :text")
				.setParameter("text","%" + tekst + "%").getResultList();
		
	}

}
