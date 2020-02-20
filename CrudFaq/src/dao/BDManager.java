package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDManager {
	private static final String	DB_URL		= "jdbc:mysql://localhost:8889/faq";
	private static final String	DB_USER		= "root";
	private static final String	DB_PASSWORD	= "root";

	private static Connection	connexion;

	private BDManager(){
	}

	public static Connection getConnection() throws DaoException{
		if (connexion == null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection connexion = DriverManager.getConnection(DB_URL,
						DB_USER, DB_PASSWORD);
				System.out.println("connexion bdd suuccés");
				return connexion;
			}
			catch (ClassNotFoundException e){
				throw new DaoException("Erreur du chargement du pilote");
			}
			catch (SQLException e){
				throw new DaoException("Erreur: Impossible d'acceder à la base de donnée : "
						+ e.getMessage());
			}
		}
		return connexion;
	}
}
