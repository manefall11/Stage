package metier;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.QuestionAnswer;

public class ModifierFaqForm {
	private static final String	CHAMP_question		= "question";
	private static final String	CHAMP_reponse		= "reponse";
	//private static final String	CHAMP_user_id		= "user_id";
	public static final String	CHAMP_category_id	= "category";

	private HttpServletRequest	request;
	private QuestionAnswer			qa;
	private String				statusMessage;
	private Map<String, String>	messageErreurs = new HashMap<String, String>();

	public ModifierFaqForm(HttpServletRequest request) throws ParseException
	{
		this.request = request;

		String question = getParamater(CHAMP_question);
		String reponse = getParamater(CHAMP_reponse);
		String category = getParamater(CHAMP_category_id);

		

		validerChamps(CHAMP_question, CHAMP_reponse,  
				CHAMP_category_id);
		if (messageErreurs.isEmpty())
		{
			statusMessage = "modifications effectuées avec succès";
			try {
				qa = new QuestionAnswer(question, reponse,4,Integer.parseInt(category));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			statusMessage = "Echec de la modification de la question";
			qa = null;
		}
	}

	private void validerChamps(String... champs)
	{
		for (String champ : champs)
		{
			if (getParamater(champ) == null)
			{
				messageErreurs.put(champ, "Vous devez renseigner ce champ");
			}
		}
	}

	private String getParamater(String parametre)
	{
		String valeur = request.getParameter(parametre);
		valeur = valeur == null || valeur.trim().isEmpty() ? null
				: valeur.trim();
		return valeur;
	}

	

	public QuestionAnswer getQuestionAnswer()
	{
		return qa;
	}

	public String getStatusMessage()
	{
		return statusMessage;
	}

	public Map<String, String> getMessageErreurs()
	{
		return messageErreurs;
	}

	public boolean isValid()
	{
		return messageErreurs.isEmpty();
	}

}
