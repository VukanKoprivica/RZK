package bean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interceptor.InterceptorJavljanje;
import interceptor.InterceptorPregleda;
import interceptor.ObavesiInterceptor;
import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;

/**
 * Session Bean implementation class DrugiBean
 */
@Stateful
@LocalBean
@Interceptors(ObavesiInterceptor.class)
public class DrugiBean implements DrugiBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	OglasKorisnik korisnik;
	
	
	

    public OglasKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(OglasKorisnik korisnik) {
		this.korisnik = korisnik;
	}

	/**
     * Default constructor. 
     */
    public DrugiBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public OglasKorisnik login(String user, String pass) {
		try {
		korisnik = (OglasKorisnik)em.createQuery("SELECT o FROM OglasKorisnik o where o.username like :user and o.password like :pass",OglasKorisnik.class)
				.setParameter("user", user)
				.setParameter("pass", pass)
				.getSingleResult();
		
		return korisnik;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Interceptors(InterceptorPregleda.class)
	public List<Ogla> pretraga() {
		// TODO Auto-generated method stub
		return em.createQuery("select o from Ogla o where o.text like :tekst").setParameter("tekst", "Cone lopov").getResultList();
	}

	@Override
	public boolean dodajOglas(String text,int idKorisnik) {
		// TODO Auto-generated method stub
		Ogla o = new Ogla();
		o.setBrojPregleda(0);
		
		o.setOglasKorisnik(em.find(OglasKorisnik.class, idKorisnik));
		o.setText(text);
		try {
			em.persist(o);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
	@Interceptors(InterceptorJavljanje.class)
	public boolean javiSe(int idOglas,String text,int idKorisnik) {
		// TODO Auto-generated method stub
		Ogla o = em.find(Ogla.class, idOglas);
		OglasKorisnik ok = em.find(OglasKorisnik.class, idKorisnik);
		OglasPrijava op = new OglasPrijava();
		op.setOgla(o);
		op.setOglasKorisnik(ok);
		op.setText(text);
		
		try {
			em.persist(op);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

}
