package beans;

public class Category {
	private int idCategory;
	private int codeCategory;
	private String libelle;
	
	
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public int getCodeCategory() {
		return codeCategory;
	}
	public void setCodeCategory(int codeCategory) {
		this.codeCategory = codeCategory;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Category(int idCategory, String libelle) {
		this.idCategory = idCategory;
		this.libelle = libelle;
	}
	
	
	
	

}
