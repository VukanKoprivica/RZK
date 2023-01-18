package rzk;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class StatelessBean
 */
@Stateless
@LocalBean
public class StatelessBean implements StatelessBeanRemote {

	List<Person> persone = new ArrayList<>();
    /**
     * Default constructor. 
     */
    public StatelessBean() {
        // TODO Auto-generated constructor stub
    	
    }

	@Override
	public String sayHi() {
		// TODO Auto-generated method stub
		System.out.println("Hi 1");
		return "Hi";
	}

	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		System.out.println("Hi 2");
		return "Hi "+name;
	}

	@Override
	public List<String> sayHi(String name1, String name2) {
		// TODO Auto-generated method stub
		System.out.println("Hi 3");
		List<String> list = new ArrayList<>();
		list.add(sayHi(name1));
		list.add(sayHi(name2));
		return list;
	}

	@Override
	public List<Person> add(Person p) {
		// TODO Auto-generated method stub
		persone.add(p);
		System.out.println(p);
		return persone;
	}

}
