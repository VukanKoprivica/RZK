package rzk;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import model.Blog;
import model.BlogKateg;
import model.BlogKorisnik;

import rzk.interceptor.BrojPostavljenihInterceptor;

/**
 * Session Bean implementation class BlogBean
 */
@Stateful
@LocalBean
public class BlogBean implements BlogBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	BlogKorisnik korisnik;

    /**
     * Default constructor. 
     */
    public BlogBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean login(String user, String pass) {
		try {
		korisnik =(BlogKorisnik)em.createQuery("Select k from BlogKorisnik k WHERE k.username LIKE :user AND k.password like :pass")
		.setParameter("user", user)
		.setParameter("pass", pass).getSingleResult();
		
		
		}catch(Exception e){
			return false;
		}
		
		
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@Interceptors(BrojPostavljenihInterceptor.class)
	public boolean dodajBlog(String text, int idBlogKategorija) {
		// TODO Auto-generated method stub
		try {
		Blog b = new Blog();
		b.setBlogKorisnik(korisnik);
		BlogKateg kat = em.find(BlogKateg.class, idBlogKategorija);
		b.setBlogKateg(kat);
		b.setText(text);
		b.setLikeNo(0);
		b.setDatum(new Date());
		em.persist(b);
		
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@PostConstruct
	public void kreiran() {
		System.out.println("stateful bean created on:" + new Date());
	}
	
	@PreDestroy
	public void unisti() {
		System.out.println("Stateful bean destroyed on:"+new Date());
	}

	@Remove
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

}
