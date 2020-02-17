package usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usermanagement.model.UserBo;

public class UserBoDao {
	private String jdbcURL = "jdbc:mysql://localhost:8889/Faq";
	private String jdbcUsername= "root";
	private String jdbcPassword= "root";
	
	
	private static final String INSERT_USER_SQL = "INSERT INTO USERBO (nom, prenom, mail, password) VALUES (?,?,?,?);";
	private static final String SELECT_USER_BY_ID = "select idUser, nom, prenom, mail, password from USERBO where idUser =?";
    private static final String SELECT_ALL_USERS = "select * from USERBO";
    private static final String DELETE_USERS_SQL = "delete from USERBO where idUser = ?;";
    private static final String UPDATE_USERS_SQL = "update USERBO set nom = ?, prenom=?, mail= ?, password =?, idUser=? where id = ?;";
    
    public UserBoDao() {
    	
    }
public Connection getConnection() {
	Connection connection = null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		System.out.println("Essaie de connexion");

		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		System.out.println("Connecte a la base");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return connection;
	
}

public void insertUser (UserBo user) throws SQLException{
	try (Connection connection = getConnection(); 
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) 
	{
        preparedStatement.setString(1, user.getNom());
        preparedStatement.setString(2, user.getPrenom());
        preparedStatement.setString(3, user.getMail());
        preparedStatement.setString(4, user.getPassword());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        printSQLException(e);
    }
}

public UserBo selectUser (int idUser) throws SQLException {
	UserBo user =  null;
	try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);)
	{
		preparedStatement.setInt(1, idUser);
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String mail = rs.getString("mail");
			String password = rs.getString("password");
			user = new UserBo(idUser, nom, prenom, mail, password);
	}
		
	}
	catch (SQLException e) {
		printSQLException(e);
	}
	return user;
	
	
}

public List<UserBo> selectAllUsers(){
	
	List<UserBo> users = new ArrayList<>();
	try (
	Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			System.out.println("passe");
			int idUser = rs.getInt("idUser");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String mail = rs.getString("mail");
			String password = rs.getString("password");
			users.add(new UserBo(idUser, nom, prenom, mail, password));
			
			
		}
		
	} catch (SQLException e) {
		
		printSQLException(e);
	}
	
	for(UserBo user : users) {
		System.out.println(user.nom);
	}
	return users;
	
	
}

public boolean deleteUser(int id) throws SQLException {
	
	boolean rowDeleted;
	try (Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;
	return rowDeleted;
	
}
}

public boolean updateUser(UserBo user) throws SQLException {
    boolean rowUpdated;
    try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
        statement.setString(1, user.getNom());
        statement.setString(2, user.getPrenom());
        statement.setString(3, user.getMail());
        statement.setString(4, user.getPassword());
        statement.setInt(5, user.getIdUser());

        rowUpdated = statement.executeUpdate() > 0;
    }
    return rowUpdated;
}

private void printSQLException(SQLException ex) {
	for(Throwable e:ex) {
		 if (e instanceof SQLException) {
             e.printStackTrace(System.err);
             System.err.println("SQLState: " + ((SQLException) e).getSQLState());
             System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
             System.err.println("Message: " + e.getMessage());
             Throwable t = ex.getCause();
             while (t != null) {
                 System.out.println("Cause: " + t);
                 t = t.getCause();
             }
         }
     }
 }
	


	

}
