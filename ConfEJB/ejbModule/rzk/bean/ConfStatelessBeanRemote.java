package rzk.bean;

import java.util.List;

import javax.ejb.Remote;

import model.Conference;
import model.ConferencePredavac;

@Remote
public interface ConfStatelessBeanRemote {
	
	public boolean addConference(Conference conference);
	public boolean register(ConferencePredavac p);
	public List<Conference> getConf(String ime,String prezime);

}
