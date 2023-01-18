package rzk.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Event;
import model.EventType;
import model.User;



@Remote
public interface PlanerUserBeanRemote {
	
	public User login(String email,String pass);
	public boolean unesiObaveze(String desc,Date toDate,Date fromDate,int idType,User user);
	public List<Event> pretrazi(Date fromDate);
	public List<EventType> tipovi();

}
