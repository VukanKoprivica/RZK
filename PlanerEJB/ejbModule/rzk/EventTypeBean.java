package rzk;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import model.EventType;

/**
 * Session Bean implementation class EventTypeBean
 */
@Singleton
@LocalBean
public class EventTypeBean {

    /**
     * Default constructor. 
     */
    public EventTypeBean() {
        // TODO Auto-generated constructor stub
    }
    private List<EventType> lista = new ArrayList<>();
	public List<EventType> getLista() {
		return lista;
	}
	public void setLista(List<EventType> lista) {
		this.lista = lista;
	}
    
   

}
