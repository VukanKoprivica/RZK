package rzk.beans.session;

import java.util.List;

import javax.ejb.Remote;

import model.Conference;
import model.ConferencePredavac;

@Remote
public interface ConfInfoSBRemote {

	public boolean addConference(Conference conference);
	public boolean register(ConferencePredavac cp);
	public List<Conference> getConfs(String ime, String prezime);
}
