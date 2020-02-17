package usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usermanagement.dao.UserBoDao;
import usermanagement.model.UserBo;

/**
 * Servlet implementation class UserServlet
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBoDao userBoDao; 
	public void init() {
		userBoDao = new UserBoDao();
	}
       
    
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		
		try {
			switch(action) {
			
			case "/new" :
				showNewForm(request, response);
				break;
			case "/insert" :
				insertUser(request, response);
				break;
			case "/delete" :
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            default:
                listUser(request, response);
                break;
        }
    } catch (SQLException ex) {
        throw new ServletException(ex);
    }
}
			
		
private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
	List<UserBo> listUserBo = null;
	try {
		listUserBo = userBoDao.selectAllUsers();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	request.setAttribute("listUserBo", listUserBo );
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user-list.jsp");
	dispatcher.forward(request, response);
}
 private void insertUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException,ServletException {
	 String nom = request.getParameter("nom");
	 String prenom = request.getParameter("prenom");
	 String mail = request.getParameter("mail");
	 String password = request.getParameter("password");
	 UserBo newUser = new UserBo(nom,prenom,mail,password);
	 try {
		userBoDao.insertUser(newUser);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 response.sendRedirect("list");
 }
private void deleteUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException,ServletException {
	 
	 int idUser = Integer.parseInt(request.getParameter("idUser"));
	 try {
		userBoDao.deleteUser(idUser);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 response.sendRedirect("list");
 }

private void updateUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException,ServletException {
	 
	int idUser = Integer.parseInt(request.getParameter("idUser"));
    String nom = request.getParameter("nom");
    String prenom = request.getParameter("prenom");
    String mail = request.getParameter("mail");
    String password = request.getParameter("password");
    
    UserBo userUp = new UserBo(idUser, nom, prenom, mail, password);
    try {
		userBoDao.updateUser(userUp);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void showEditForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException,ServletException {
	 
	 int idUser = Integer.parseInt(request.getParameter("idUser"));
	 UserBo existingUser = null;
	try {
		existingUser = userBoDao.selectUser(idUser);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user-form.jsp");
     request.setAttribute("user", existingUser);
     dispatcher.forward(request, response);
}
private void showNewForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException,ServletException {
	 
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/user-form.jsp");
	dispatcher.forward(request, response);
	
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
