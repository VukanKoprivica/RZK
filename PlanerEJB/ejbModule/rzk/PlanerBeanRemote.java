package rzk;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Event;
import model.EventType;

@Remote
public interface PlanerBeanRemote {

	public String login(String username, String password);
	public void UnesiObaveze(Date fromDate,Date toDate,int idEt);
	public List<Event> vratiObaveze(Date fromDate);
	public List<EventType> vratiListu();
	public void destroy();
}
