package bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

/**
 * Session Bean implementation class Statistika
 */
@Singleton
@LocalBean
public class Statistika {
	
	@PersistenceContext
	EntityManager em;

	HashMap<Integer, Integer> brojPregleda;
	int brojJavljanja;
	
	
    /**
     * Default constructor. 
     */
    public Statistika() {
        // TODO Auto-generated constructor stub
    	brojPregleda=new HashMap<>();
    	brojJavljanja=0;
    }
    
    public void azurirajBrojPregleda(List<Ogla> oglasi) {
    	for(Ogla o : oglasi) {
    		if(brojPregleda.containsKey(o.getIdOglas())){
    			brojPregleda.put(o.getIdOglas(), brojPregleda.get(o.getIdOglas())+1);
    		}else {
    			brojPregleda.put(o.getIdOglas(), 1);
    		}
    	}
    }
    
    public void azurirajJavljanja() {
    	brojJavljanja++;
    }
    @Timeout
    public void startTimer() {
    	System.out.println("Broj javljanja="+brojJavljanja);
    	brojJavljanja=0;
    }
    
    @Resource
    TimerService ts;
    Timer timer;
    
  
    @PostConstruct
    public void programski() {
    	TimerConfig tc = new TimerConfig();
    	tc.setPersistent(false);
    	timer = ts.createIntervalTimer(new Date(), 10000, tc);
    	
    }
    
    @Schedule(hour = "*",minute = "*", second = "5",persistent = false)
    public void automatski() {
    	for(Entry<Integer, Integer> neki:brojPregleda.entrySet()) {
    		System.out.println(neki.getKey() + "   " + neki.getValue());
    		Ogla o = (Ogla) em.find(Ogla.class,neki.getKey());
    		
    		em.persist(o);
    		o.setBrojPregleda(neki.getValue() + o.getBrojPregleda());
    	}
    }
    

}
