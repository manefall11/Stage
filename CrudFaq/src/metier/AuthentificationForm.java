package metier;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Admin;
import dao.DaoException;
import servlets.GestionFaqServlet;;

public class AuthentificationForm {
	
	private static final String	CHAMP_LOGIN		= "login";
	private static final String	CHAMP_PASSWORD	= "password";
	private HttpServletRequest	request;
	private String				login;

	public AuthentificationForm(HttpServletRequest request)
	{
		this.request = request;
	}

	public Admin connect() throws DaoException
	{
		login = getParamater(CHAMP_LOGIN);
		String password = getParamater(CHAMP_PASSWORD);

		Admin admin = GestionFaqServlet.getAdmin();
		
		
	
		if (admin.getLogin().equals(login) && admin.getPassword().equals(password))
		{
			HttpSession session = request.getSession();
			Admin administrateur = admin;
			session.setAttribute("admin", admin);
			return administrateur;
		}
		
		
		
		else
		{
			return null;
		}
	}

	private String getParamater(String parametre)
	{
		String valeur = request.getParameter(parametre);
		valeur = valeur == null || valeur.trim().isEmpty() ? null
				: valeur.trim();
		return valeur;
	}

	public String getLogin()
	{
		return login;
	}

}
