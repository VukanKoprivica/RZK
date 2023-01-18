package rzk;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import model.Conference;
import rzk.bean.ConfStatelessBean;
import rzk.webservice.ConfWebService;
import rzk.webservice.ConfWebServiceRemote;

/**
 * Servlet implementation class ConfServlet
 */
@WebServlet("/ConfServlet")
public class ConfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ConfStatelessBean csb;
	
	@EJB
	ConfWebServiceRemote cwsr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String field = request.getParameter("field");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date toDate = null;
		Date fromDate = null;
		
		try {
			toDate = sdf.parse(request.getParameter("toDate"));
			fromDate = sdf.parse(request.getParameter("fromDate"));
			
			Conference conf = new Conference();
			conf.setTitle(name);
			conf.setCity(city);
			conf.setCountry(country);
			conf.setField(field);
			conf.setDateTo(toDate);
			conf.setDateFrom(fromDate);
			
			boolean ok= csb.addConference(conf);
			System.out.println("Status add: "+ok);
			String msg = "";
			if(ok)msg="Conference added";
			request.getSession().setAttribute(msg, msg);
			cwsr.confList(field);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
