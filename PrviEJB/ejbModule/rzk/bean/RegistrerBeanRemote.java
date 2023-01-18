package rzk.bean;

import javax.ejb.Remote;

@Remote
public interface RegistrerBeanRemote {
	public boolean registruj(String email,String pass,String firstName,String lastName);

}
