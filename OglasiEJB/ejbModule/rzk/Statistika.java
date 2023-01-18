package rzk;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

/**
 * Session Bean implementation class Statistika
 */
@Singleton
@LocalBean
public class Statistika implements StatistikaLocal {

	private HashMap<Integer, Integer> pregledi;

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public Statistika() {
		pregledi = new HashMap<Integer, Integer>();
	}

	public HashMap<Integer, Integer> getPregledi() {
		return pregledi;
	}

	public void setPregledi(HashMap<Integer, Integer> pregledi) {
		this.pregledi = pregledi;
	}

	// 1. metoda za update Mape
	public void updateMap(Ogla o) {
		if (pregledi.containsKey(o.getIdOglas())) {
			pregledi.put(o.getIdOglas(), pregledi.get(o.getIdOglas()) + 1);
		} else {
			pregledi.put(o.getIdOglas(), 1);
		}
	}

	// 2. metoda za update baze
//programski ili automatski?
	public void updateDB() {
		for (Entry<Integer, Integer> entry : pregledi.entrySet()) {
			Ogla o = em.find(Ogla.class, entry.getKey());
			o.setBrojPregleda(o.getBrojPregleda() + entry.getValue());
			em.merge(o);
		}
	}
}
