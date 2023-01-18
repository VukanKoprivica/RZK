package rzk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Blog;
import model.BlogKateg;

/**
 * Session Bean implementation class KategorijeAndStuff
 */
@Singleton
@LocalBean
public class KategorijeAndStuff implements KategorijeAndStuffRemote {

	@PersistenceContext
	EntityManager em;
	private List<BlogKateg> kategorije;
	
	private HashMap<Integer, Integer> brojPregleda;
	
	private int brojPostavljenih;
	
	@Resource
	TimerService ts;
	
	Timer t;
	
    /**
     * Default constructor. 
     */
    public KategorijeAndStuff() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<BlogKateg> getSveKategorije() {
		// TODO Auto-generated method stub
		return kategorije;
	}
	
	@PostConstruct
	public void dovuciKAt(){
		kategorije= em.createNamedQuery("BlogKateg.findAll",BlogKateg.class).getResultList();
		brojPregleda = new HashMap<Integer,Integer>();
		brojPostavljenih=0;
	}
	
	public void povecajBrojPostavljenih() {
		brojPostavljenih++;
	}
	
	public void azurirajBrojPregleda(List<Blog> blogovi) {
		for(Blog b:blogovi) {
			if(brojPregleda.containsKey(b.getIdBlog())) {
				brojPregleda.put(b.getIdBlog(), brojPregleda.get(b.getIdBlog())+1);
			}else {
				brojPregleda.put(b.getIdBlog(), 1);
			}
		}
	}
	
	public void startTimer() throws ParseException {
		TimerConfig tc = new TimerConfig();
		tc.setPersistent(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse("2022-01-01 00:00:00");
		t=ts.createIntervalTimer(d,1000, tc);
	}
	
	@Timeout
	public void timeoutMoment() {
		System.out.println("Broj novih: "+new Date() +"je:"+brojPostavljenih);
		brojPostavljenih=0;
	}

}
