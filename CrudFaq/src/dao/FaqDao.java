package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Admin;
import beans.Category;
import beans.QuestionAnswer;

public class FaqDao {
	
	private static final String	ADD_QA_SQL	= "INSERT INTO QuestionAnswer(question , reponse,  category_id ,user_id) VALUES( ?, ?, ?,?)";
	private static final String	SELECT_QA_SQL	= "SELECT * FROM QuestionAnswer";
	private static final String	UPDATE_QA_SQL	= "UPDATE QuestionAnswer SET question=?, reponse=? , category_id=?  WHERE idqa=?";
	private static final String	DELETE_CLIENT_SQL	= "DELETE FROM QuestionAnswer WHERE idqa=?";
	private static final String	SELECT_ADMIN_SQL	= "SELECT * FROM Admin";
	private static final String	SELECT_CATEGORY_SQL	= "SELECT * FROM Category";



	public static void ajouter(QuestionAnswer questionAnswer) throws DaoException
	{
		Connection connexion = BDManager.getConnection();
		try
		{
			PreparedStatement statement = connexion
					.prepareStatement(ADD_QA_SQL);
			statement.setString(1, questionAnswer.getQuestion());
			statement.setString(2, questionAnswer.getReponse());
			statement.setInt(3, questionAnswer.getCategory_id());
			statement.setInt(4, questionAnswer.getUser_id());

			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DaoException("question non ajoutée");
		}
	}
	
	
	public static List<QuestionAnswer> getList() throws DaoException
	{
		Connection connexion = BDManager.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<QuestionAnswer> questionAnswers = null;
		try
		{
			
			questionAnswers = new ArrayList<QuestionAnswer>();
			String question, reponse;
			int id_user , id_category;
			int id;
			//Date date;
			statement = connexion.createStatement();
			resultSet = statement.executeQuery(SELECT_QA_SQL);
			while (resultSet.next())
			{
				// id = resultSet.getInt(1);
				question = resultSet.getString(1);
				reponse = resultSet.getString(2);
				id_user = resultSet.getInt(3);
				id_category = resultSet.getInt(4);
				id = resultSet.getInt(5);
				questionAnswers.add(new QuestionAnswer(question, reponse, id_user, id_category, id));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionAnswers;
	}
	
	
	public static void modifier(QuestionAnswer qa, int idqa) throws SQLException, DaoException {
		Connection connexion = BDManager.getConnection();
		PreparedStatement statement = connexion
				.prepareStatement(UPDATE_QA_SQL);
		
		statement.setString(1, qa.getQuestion());
		statement.setString(2, qa.getReponse());
		statement.setInt(3, qa.getCategory_id());
		statement.setInt(4, idqa);
		 
		statement.executeUpdate();
		
		    System.out.println("modifications validees");
		    		
	}
	
	
	
	public static Admin getAdmin() throws DaoException
	{
		Connection connexion = BDManager.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Admin admin = null;
		try
		{
			String nom, prenom, login, password;
			statement = connexion.createStatement();
			resultSet = statement.executeQuery(SELECT_ADMIN_SQL);
			while (resultSet.next())
			{
				// id = resultSet.getInt(1);
				nom = resultSet.getString(1);
				prenom = resultSet.getString(2);
				login = resultSet.getString(3);
				password = resultSet.getString(4);
				admin = new Admin(nom, prenom, login, password);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
	
    
    public static void supprimer(String idqa) throws DaoException
	{
		Connection connexion = BDManager.getConnection();
		try
		{
			PreparedStatement statement = connexion
					.prepareStatement(DELETE_CLIENT_SQL);
			statement.setString(1, idqa);
			
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new DaoException("question non supprimé");
		}
	}
    
    public static List<Category> getCategorie() throws DaoException{
    	
    	Connection connexion = BDManager.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<Category> categories = null;
		try
		{
			
			categories = new ArrayList<Category>();
			String libelle;
			int id;
			
			statement = connexion.createStatement();
			resultSet = statement.executeQuery(SELECT_CATEGORY_SQL);
			while (resultSet.next())
			{
				id = resultSet.getInt(3);
				libelle = resultSet.getString(2);

				
				categories.add(new Category(id, libelle));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
		
	}
    
   
    	
    	
    
	
	
	
	
	
	
    
    
	

}
