package gestion;

public class Produit {
	
int id;
String libelle;
int prix;
int quantite;
int stock;
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public int getPrix() {
	return prix;
}
public void setPrix(int prix) {
	this.prix = prix;
}
public int getQuantite() {
	return quantite;
}
public void setQuantite(int quantite) {
	this.quantite = quantite;
}
public int getStock() {
	return stock;
}


public Produit(int id, String libelle, int prix, int quantite) { //constructeur avec parametre
	super();
	this.id = id;
	this.libelle = libelle;
	this.prix = prix;
	this.quantite = quantite;

}

public Produit() {
	super();
	// TODO Auto-generated constructor stub
}

}
