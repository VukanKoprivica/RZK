package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rzk.Person;
import rzk.StatelessBean;


@Path("/greet")
public class GreeterRest {
	
	@EJB
	StatelessBean sb;
	
	
	
	@GET
	public String sayHi() {return sb.sayHi();}
	
	
	@GET
	@Path("zaIme/{name}")
	public String sayHi(@PathParam("name") String name) {
		return sb.sayHi(name);
	}
	
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> sayHi(@QueryParam("name1")String name1,@QueryParam("name2") String name2){
		return sb.sayHi(name1, name2);
	}
	
	@GET
	@Path("persons")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> addPerson(Person p){
		System.out.println("blabla");
		return sb.add(p);
	}

}
