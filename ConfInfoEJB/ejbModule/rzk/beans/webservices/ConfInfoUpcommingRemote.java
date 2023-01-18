package rzk.beans.webservices;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import model.Conference;

@Remote
@WebService
public interface ConfInfoUpcommingRemote {

	public List<Conference> getConfs(String field);
}
