package bean;

import java.util.List;

import javax.ejb.Remote;

import model.Ogla;
import model.OglasKorisnik;

@Remote
public interface DrugiBeanRemote {
	
	public OglasKorisnik login(String user,String pass);
	public List<Ogla> pretraga ();
	public boolean dodajOglas(String text,int idKorisnik);
	public boolean javiSe(int idOglas,String text,int idKorisnik);

}
