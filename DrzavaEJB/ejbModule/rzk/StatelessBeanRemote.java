package rzk;

import javax.ejb.Remote;
import javax.jws.WebService;

@Remote
@WebService
public interface StatelessBeanRemote {
	public String resolve (String s);
}
