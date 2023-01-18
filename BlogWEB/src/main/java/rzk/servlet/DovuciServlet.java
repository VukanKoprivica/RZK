package rzk.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BlogKateg;
import rzk.KategorijeAndStuff;

/**
 * Servlet implementation class DovuciServlet
 */
@WebServlet("/DovuciServlet")
public class DovuciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	KategorijeAndStuff kat;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DovuciServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BlogKateg> kateg=kat.getSveKategorije();
		request.getSession().setAttribute("kateg", kateg);
		request.getRequestDispatcher("unosBloga.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
