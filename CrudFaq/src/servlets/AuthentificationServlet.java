package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Admin;
import dao.DaoException;
import metier.AuthentificationForm;

/**
 * Servlet implementation class AuthentificationServlet
 */
@WebServlet({ "/login", "/logout" })
public class AuthentificationServlet extends HttpServlet {
       
	private static final long	serialVersionUID	= 1L;
	private static final String	VUE_LOGIN			= "/WEB-INF/login.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String requestedUrl = request.getServletPath();

		if (requestedUrl.equals("/login"))
		{
			getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request,
					response);
		}
		else
		{
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		AuthentificationForm form = new AuthentificationForm(request);
		Admin utilisateur = null;
		try {
			utilisateur = form.connect();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (utilisateur != null)
		{
			response.sendRedirect(request.getContextPath() + "/Faq/listQuestions");
		}
		else
		{
			request.setAttribute("form", form);
			getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request,
					response);
		}
	}

}
