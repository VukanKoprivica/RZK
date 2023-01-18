package rzk.webservice;

import java.util.List;

import javax.ejb.Remote;

import model.Conference;

@Remote
public interface ConfWebServiceRemote {
	public List<Conference> confList(String field);
}
