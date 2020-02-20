package beans;

public class QuestionAnswer {
	
	private String question;
	private String reponse;
	private int idqa;
	private int user_id;
	private int category_id;
	
	
	public QuestionAnswer(String question, String reponse,  int user_id, int categorie_id) {
		this.question = question;
		this.reponse = reponse;
		this.user_id = user_id;
		this.category_id = categorie_id;
	}
	
	
	
	/*
	public QuestionAnswer(String question,String reponse, Date date, int idqa ) {
		super();
		this.reponse = reponse;
		this.date = date;
		this.idqa = idqa;
		this.question = question;
	}

*/

	
	







	public QuestionAnswer(String question, String reponse, int id_user, int id_category, int id) {
		// TODO Auto-generated constructor stub
		
		this.question = question;
		this.reponse = reponse;
		this.user_id = id_user;
		this.category_id = id_category;
		this.idqa = id;
	}



	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getReponse() {
		return reponse;
	}


	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

/*
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

*/

	public int getIdqa() {
		return idqa;
	}


	public void setIdqa(int idqa) {
		this.idqa = idqa;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int categorie_id) {
		this.category_id = categorie_id;
	}
	
	
	

}
