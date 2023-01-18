package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Blog;

@Remote
public interface PretragaBeanRemote {
	
	public List<Blog> pretrazi(String tekst);

}
