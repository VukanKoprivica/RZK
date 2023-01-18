package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.BlogKateg;

@Remote
public interface KategorijeAndStuffRemote {
	
	public List<BlogKateg> getSveKategorije();

}
