package rzk;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessBeanRemote {
	
	public String sayHi();
	public String sayHi(String name);
	public List<String> sayHi(String name1,String name2);
	public List<Person> add(Person p); 

}
