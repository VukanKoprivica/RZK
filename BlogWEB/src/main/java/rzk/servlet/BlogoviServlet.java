package rzk.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Blog;
import rzk.PretragaBean;

/**
 * Servlet implementation class BlogoviServlet
 */
@WebServlet("/BlogoviServlet")
public class BlogoviServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PretragaBean pb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogoviServlet() {
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
		String text =request.getParameter("pretraga");
		List<Blog> blogovi= pb.pretrazi(text);
		request.getSession().setAttribute("blogovi", blogovi);
		request.getRequestDispatcher("pretraga.jsp").forward(request, response);
	
	}

}
