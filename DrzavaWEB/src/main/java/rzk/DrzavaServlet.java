package rzk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import rsd.StatelessBeanRemote;
import rsd.StatelessBeanService;

/**
 * Servlet implementation class DrzavaServlet
 */
@WebServlet("/DrzavaServlet")
public class DrzavaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@WebServiceRef(StatelessBeanService.class)
	StatelessBeanRemote sbr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrzavaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		String respons= sbr.resolve(code);
		request.getSession().setAttribute("response", respons);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
