package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DrugiBean;
import bean.DrugiBeanRemote;

/**
 * Servlet implementation class DrugiServlet
 */
@WebServlet("/DrugiServlet")
public class DrugiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    DrugiBeanRemote dbr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrugiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idOglasa = Integer.parseInt(request.getParameter("ogla"));
		String text = request.getParameter("text");
		int idKorisnik = (int) request.getSession().getAttribute("korisnik");
		boolean uspesno = dbr.javiSe(idOglasa, text,idKorisnik);
		if(uspesno) {
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("tekst");
		boolean dobroJe = dbr.dodajOglas(text,(int) request.getSession().getAttribute("korisnik"));
		if(dobroJe) {
			request.getRequestDispatcher("home.jsp").forward(request, response);
			
		}else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
