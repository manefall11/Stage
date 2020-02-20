package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Admin;
import beans.QuestionAnswer;
import dao.DaoException;
import dao.FaqDao;
import metier.AjouterFaqForm;
import metier.ModifierFaqForm;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/Faq/*")
public class GestionFaqServlet extends HttpServlet {

	private static final long				serialVersionUID		= 1L;
	private static final String				VUE_AJOUT_UTILISATEUR	= "/WEB-INF/AjouterQuestion.jsp";
	private static final String				VUE_LIST_UTILISATEUR	= "/WEB-INF/ListerQuestions.jsp";

	public static  List<QuestionAnswer>	    questionsreponses			        = new ArrayList<QuestionAnswer>();
	private static final String				VUE_UPDATE_QA	= "/WEB-INF/modifierFaq.jsp";
	private static  Admin              administrateur          = new Admin();






	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*try {
			clients = UtilisateurDao.getList();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*
		try {
			request.setAttribute("admin", FaqDao.getAdmin());
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		 */

		String requestedUrl = request.getRequestURI();
		if (requestedUrl.endsWith("/addQuestion"))
		{
			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
			.forward(request, response);
		}
		else if (requestedUrl.endsWith("/listQuestions"))
		{ 
			try {
				request.setAttribute("listFaq", FaqDao.getList());


			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR)
			.forward(request, response);
		}

		else if (requestedUrl.endsWith("/update"))
		{
			try {
				questionsreponses = FaqDao.getList();
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int idqa = Integer.parseInt(request.getParameter("idqa"));


			for (QuestionAnswer qa : questionsreponses)
			{
				if (qa.getIdqa() ==idqa)
				{
					request.setAttribute("faqUpdate", qa);
					break;
				}
			}

			getServletContext().getRequestDispatcher(VUE_UPDATE_QA)
			.forward(request, response);
		}


		else if (requestedUrl.endsWith("/delete"))
		{
			String idqa = request.getParameter("idqa");
			try {
				FaqDao.supprimer(idqa);
				request.setAttribute("listFaq", FaqDao.getList());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR)
			.forward(request, response);
		}


		else
		{
			response.sendRedirect(request.getContextPath());
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		request.setCharacterEncoding("utf-8");
		String requestedUrl = request.getRequestURI();
		request.setCharacterEncoding("utf-8");
		
		if (requestedUrl.endsWith("/Faq/add"))
		{
			AjouterFaqForm form = null;
			try {
				form = new AjouterFaqForm(request);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			QuestionAnswer questionAnswer = form.getQuestionAnswer();


			try {
				FaqDao.ajouter(questionAnswer);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			request.setAttribute("qa", questionAnswer);
			request.setAttribute("messageErreurs", form.getMessageErreurs());
			request.setAttribute("statusMessage", form.getStatusMessage());

			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
			.forward(request, response);
		}


		else if (requestedUrl.endsWith("/Faq/update"))
		{
			int idqa = Integer.parseInt(request.getParameter("idqa"));
			ModifierFaqForm form = null;
			try {
				form = new ModifierFaqForm(request);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			QuestionAnswer questionAnswer = form.getQuestionAnswer();


			try {
				FaqDao.modifier(questionAnswer, idqa);
			} catch (DaoException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			
			response.sendRedirect(request.getContextPath() + "/Faq/listQuestions");

		}


	}






	public static List<QuestionAnswer> getClients()
	{
		return questionsreponses;
	}


	public static Admin getAdmin() throws DaoException
	{
		administrateur = FaqDao.getAdmin();

		return administrateur;
	}





}
