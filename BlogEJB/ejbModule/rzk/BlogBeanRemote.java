package rzk;

import javax.ejb.Remote;

@Remote
public interface BlogBeanRemote {

	
	public boolean login(String user, String pass);
	public boolean dodajBlog(String text,int idBlogKategorija);
	public void logout();
}

