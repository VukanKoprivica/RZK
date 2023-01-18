package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Conference;
import rzk.beans.session.ConfInfoSBRemote;

/**
 * Servlet implementation class SaveConference
 */
@WebServlet("/SaveConference")
public class SaveConference extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	ConfInfoSBRemote confInfo;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveConference() {
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
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String field = request.getParameter("field");
		String title = request.getParameter("title");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFrom = null;
		Date dateTo = null;
		
		try {
			dateFrom = sdf.parse(request.getParameter("dateFrom"));
			dateTo = sdf.parse(request.getParameter("dateTo"));
			
			Conference conf = new Conference();
			conf.setCity(city);
			conf.setCountry(country);
			conf.setDateFrom(dateFrom);
			conf.setDateTo(dateTo);
			conf.setField(field);
			conf.setTitle(title);
			
			boolean ok = confInfo.addConference(conf);
			System.out.println("Status ok: "+ok);
			String msg = "";
			if(ok)
				msg = "Conference added.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
