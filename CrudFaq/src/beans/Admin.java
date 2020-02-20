package beans;

public class Admin {
	
	private String nom, prenom, mail, password;



	public Admin() {
		
	}

	public Admin(String nom, String prenom, String login, String password)
	{
		this.nom = nom;
		this.prenom = prenom;
		this.mail = login;
		this.password = password;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public String getLogin()
	{
		return mail;
	}

	public void setLogin(String login)
	{
		this.mail = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}


}
