package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DrugiBean;
import bean.DrugiBeanRemote;
import model.Ogla;
import model.OglasKorisnik;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Context initialContext;

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	public static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}

	private static String getLookupName() {
		
		// The app name is the application name of the deployed EJBs. This is typically the ear name without the .ear suffix. 
        final String appName = "DrugiEAR";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the EJB deployment, without the .jar suffix.
        final String moduleName = "DrugiEJB";
        // JBossAS allows each deployment to have an (optional) distinct name. We haven&#039;t specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = DrugiBean.class.getSimpleName();
        // the remote interface fully qualified class name
        final String interfaceName = DrugiBeanRemote.class.getName();
        // let&#039;s do the <a class="autolink" title="Lookup" href="https://moodle.pmf.uns.ac.rs/mod/resource/view.php?id=76093">lookup</a>
		String name = "ejb:" + appName + "/" + moduleName + "/" +
				distinctName    + "/" + beanName + "!" + interfaceName+"?stateful";
		return name;
	}

	private static DrugiBeanRemote doLookup() {
		Context context = null;
		DrugiBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   "+lookupName);
			bean = (DrugiBeanRemote) context.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("dodaj", true);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		DrugiBeanRemote bean = (DrugiBeanRemote)request.getSession().getAttribute("bean");
		if(bean ==null) {
			bean = doLookup();
		}
		
		OglasKorisnik korisnik= bean.login(user, pass);
		
		if(korisnik != null) {
			request.getSession().setAttribute("bean", bean);
			request.getSession().setAttribute("korisnik", korisnik.getIdKorisnik());
			request.getRequestDispatcher("home.jsp").forward(request, response);
			List<Ogla> oglasi = bean.pretraga();
			request.getSession().setAttribute("oglasi", oglasi);
		}else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		
		
		
		
		
	}

}
