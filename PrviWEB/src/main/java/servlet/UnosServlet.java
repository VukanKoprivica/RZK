package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import model.User;
import rzk.bean.PlanerUserBean;

/**
 * Servlet implementation class UnosServlet
 */
@WebServlet("/UnosServlet")
public class UnosServlet extends HttpServlet {
	
	@EJB
	PlanerUserBean pub;
	
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String desc = request.getParameter("desc");
		int typeId =Integer.parseInt(request.getParameter("tipov"));
		User user = (User)(request.getSession().getAttribute("user"));
		
		String fDate = request.getParameter("fromDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fromDate =sdf.parse(fDate);
			
			String tDate = request.getParameter("toDate");
			Date toDate = sdf.parse(tDate);
			
			boolean dobar= pub.unesiObaveze(desc, toDate, fromDate, typeId,user);
			if(dobar) {
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fDate = request.getParameter("fromDatee");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date reDate = sdf.parse(fDate);
			List<Event> eventovi= pub.pretrazi(reDate);
			request.getSession().setAttribute("eventovi", eventovi);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
