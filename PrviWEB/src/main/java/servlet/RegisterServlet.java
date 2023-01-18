package servlet;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.bean.PlanerUserBean;
import rzk.bean.PlanerUserBeanRemote;
import rzk.bean.RegistrerBean;
import rzk.bean.RegistrerBeanRemote;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
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

		// The app name is the application name of the deployed EJBs. This is typically
		// the ear name without the .ear suffix.
		final String appName = "PrviEAR";
		// This is the module name of the deployed EJBs on the server. This is typically
		// the jar name of the EJB deployment, without the .jar suffix.
		final String moduleName = "PrviEJB";
		// JBossAS allows each deployment to have an (optional) distinct name. We
		// haven't specified a distinct name for
		// our EJB deployment, so this is an empty string
		final String distinctName = "";
		// The EJB name which by default is the simple class name of the bean
		// implementation class
		final String beanName = RegistrerBean.class.getSimpleName();
		// the remote interface fully qualified class name
		final String interfaceName = RegistrerBeanRemote.class.getName();
		// let's do the lookup
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName
				;
		return name;
	}

	private static RegistrerBeanRemote doLookup() {
		Context context = null;
		RegistrerBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   " + lookupName);
			bean = (RegistrerBeanRemote) context.lookup(lookupName);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("registracija",true);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email =(String) request.getParameter("email");
		String pass =(String) request.getParameter("email");
		String firstName =(String) request.getParameter("email");
		String lastName =(String) request.getParameter("email");
		
		
		RegistrerBeanRemote bean = doLookup();
		boolean jeste = bean.registruj(email, pass, firstName, lastName);
		if(jeste) {
			request.setAttribute("registracija", false);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
