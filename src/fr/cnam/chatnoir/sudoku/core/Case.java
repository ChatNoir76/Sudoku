package fr.cnam.chatnoir.sudoku.core;
/**
 * Classe correspondant à une case d'une grille de sudoku
 * @author chatnoir
 *
 */
public class Case {
	
	private int valeur;
	private boolean fixe;
	
	public int getValeur() {
		return valeur;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public void setFixe(boolean fixe) {
		this.fixe = fixe;
	}

	public boolean isFixe() {
		return fixe;
	}
	
	/**
	 * Création d'une case
	 * @param valeur valeur de la case
	 * @param fixe 
	 */
	public Case(int valeur, boolean fixe) {
		this.valeur = valeur;
		this.fixe = fixe;
	}
	/**
	 * Création d'une case fixe
	 * @param valeur valeur de la case
	 */
	public Case(int valeur) {
		this(valeur, true);
	}
	/**
	 * Création d'une case vide non fixe
	 */
	public Case() {
		this(0, false);
	}

	@Override
	public String toString() {
		return "["+valeur+"]";
	}
	
}
