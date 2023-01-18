package rzk.servlet;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BlogKateg;
import rzk.BlogBean;

/**
 * Servlet implementation class UnesiServlet
 */
@WebServlet("/UnesiServlet")
public class UnesiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BlogBean bb;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnesiServlet() {
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
		// TODO Auto-generated method stub
		String tekst = (String) request.getSession().getAttribute("tekst");
		int idBlog = Integer.parseInt(request.getParameter("kategorija"));
		bb.dodajBlog(tekst, idBlog);
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
		
		
		doGet(request, response);
	}

}
