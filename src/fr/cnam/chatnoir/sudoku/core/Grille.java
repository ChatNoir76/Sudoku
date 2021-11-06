package fr.cnam.chatnoir.sudoku.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grille {
	
	public static final int MAX_DIMENSION = 9;
	public static final int STEP = 3;
	private long somme;
	protected Case[][] grille = new Case[MAX_DIMENSION][MAX_DIMENSION];
	
	/**
	 * Remplissage de la grille via une chaine contenant des valeurs fixes
	 * @param chaine liste des chiffres du sudoku
	 */
	public Grille(int[] chaine) {
		if(chaine.length == (MAX_DIMENSION * MAX_DIMENSION)) {
			initialize(chaine);
		} else {
			initialize(new int[MAX_DIMENSION * MAX_DIMENSION]);
		}
	}
	public Grille(Grille grille) {
		somme = calculSomme(Grille.MAX_DIMENSION);
		for(int r = 0;r<MAX_DIMENSION;r++) {
			for(int c = 0;c<MAX_DIMENSION;c++) {
				this.grille[r][c] = new Case(grille.getCase(r, c).getValeur(), grille.getCase(r, c).isFixe());
			}
		}
	}
	/**
	 * Création d'une grille vierge
	 */
	public Grille() {
		this(new int[MAX_DIMENSION * MAX_DIMENSION]);
	}
	/**
	 * Initialise la grille via une chaine
	 * @param chaine
	 */
	private void initialize(int[] chaine) {
		somme = calculSomme(Grille.MAX_DIMENSION);
		for(int r = 0; r < MAX_DIMENSION;r++) {
			for(int c = 0; c < MAX_DIMENSION;c++) {
				int index = c+(r*MAX_DIMENSION);
				int valeur = chaine[index];
				grille[r][c] = valeur > 0 && valeur <= 9 ? new Case(valeur) : new Case();
			}
		}
	}
	/**
	 * Changer la valeur par défaut d'une case
	 * @param ligne numéro de ligne commençant par 1
	 * @param colonne numéro de colonne commençant par 1
	 * @param value Valeur de la case commençant par 1
	 */
	public void setValue(int ligne, int colonne, int value) {
		if(ligne > 0 && ligne <= MAX_DIMENSION && colonne > 0 && colonne <= MAX_DIMENSION && value > 0 && value <= MAX_DIMENSION) {
			Case maCase = grille[ligne - 1][colonne - 1];
			if(!maCase.isFixe()) {
				maCase.setValeur(value);
			}
		}
	}
	/**
	 * Retourne la valeur d'une case
	 * @param ligne numéro de ligne commençant par 1
	 * @param colonne numéro de colonne commençant par 1
	 * @return
	 */
	public int getValue(int ligne, int colonne) {
		return getCase(ligne-1, colonne-1).getValeur();
	}
	/**
	 * Détermine si la valeur de la case est fixe
	 * @param ligne numéro de ligne commençant par 1
	 * @param colonne numéro de colonne commençant par 1
	 * @return
	 */
	public boolean estFixe(int ligne, int colonne) {
		return getCase(ligne-1, colonne-1).isFixe();
	}
	/**
	 * Récupère la case aux coordonnées rIndex, cIndex
	 * @param rIndex numéro de ligne (à partir de 0)
	 * @param cIndex numéro de colonne (à partir de 0)
	 * @return l'objet Case correspondant
	 */
	private Case getCase(int rIndex, int cIndex) {
		return grille[rIndex][cIndex];
	}
	
	/**
	 * Récupère tous les ensembles de lignes (ligne/colonne/block)
	 * @return
	 */
	private ArrayList<Case[]> getAllSetOfCaseLine(){
		ArrayList<Case[]> liste = new ArrayList<Case[]>();
		
		for(int l = 0;l<Grille.MAX_DIMENSION;l++) {
			liste.add(this.getRow(l+1));
		}
		for(int l = 0;l<Grille.MAX_DIMENSION;l++) {
			liste.add(this.getColumn(l+1));
		}
		for(int l = 0;l<Grille.MAX_DIMENSION;l++) {
			liste.add(this.getBlock(l+1));
		}
		return liste;
	}
	/**
	 * Retourne une liste Ligne de case
	 * @param number numéro de la ligne commençant à 1
	 * @return Liste des cases de la ligne
	 */
	private Case[] getRow(int number) {
		Case[] data = new Case[Grille.MAX_DIMENSION];
		for(int c = 0; c < Grille.MAX_DIMENSION;c++) {
			data[c] = this.getCase(number - 1,c);
		}
		return data;
	}
	/**
	 * Retourne une liste Colonne de case
	 * @param number numéro de la colonne commençant à 1
	 * @return Liste des cases de la colonne
	 */
	private Case[] getColumn(int number) {
		Case[] data = new Case[Grille.MAX_DIMENSION];
		for(int r = 0; r < Grille.MAX_DIMENSION;r++) {
			data[r] = this.getCase(r, number - 1);
		}
		return data;
	}
	/**
	 * Retourne une liste Block de case
	 * @param number numéro du bloc commençant à 1 (haut/gauche(1) vers bas/droite(9))
	 * @return Liste des cases du bloc
	 */
	private Case[] getBlock(int number) {
		int row = (number-1)/STEP;
		int column = (number-1)%STEP;
		
		Case[] data = new Case[Grille.MAX_DIMENSION];
		int cpt = 0;
		for(int r = (row * STEP); r < ((row+1) * STEP);r++) {
			for(int c = (column * STEP); c < ((column+1) * STEP);c++) {
				data[cpt++] = this.getCase(r,c);
			}
		}
		return data;
	}
	/**
	 * Détermine si l'ensemble partiel (ligne/colonne/block) est cohérent
	 * @param setCase
	 * @return
	 */
	public boolean estRempliCorrectement() {
		return this.getAllSetOfCaseLine().stream().allMatch(sc -> {
			Set<Integer> liste = new HashSet<Integer>();
			for(int n = 0; n < Grille.MAX_DIMENSION;n++) {
				int valeur = sc[n].getValeur();
				if(valeur != 0) {
					if(!liste.add(valeur)) {
						return false;
					};
				}
			}
			return true;
		});
	}
	/**
	 * Détermine si l'ensemble (ligne/colonne/block) est complet et cohérent
	 * @param setCase
	 * @return
	 */
	public boolean estValide() {
		return this.getAllSetOfCaseLine().stream().allMatch(sc -> {
			int calcul = 0;
			for(int n = 0; n < Grille.MAX_DIMENSION;n++) {
				calcul += sc[n].getValeur();
			}
			return calcul == somme;
		});
	}
	/**
	 * 
	 * @param n
	 * @return
	 */
	private long calculSomme(int n) {
	    if (n == 0) {
	        return n;
	    }
	    return n + calculSomme(n - 1);
	}
	@Override
	public boolean equals(Object o) {
		return this.toString().equals(o.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder strGrille = new StringBuilder();
		for(int r = 0; r < MAX_DIMENSION;r++) {
			for(int c = 0; c < MAX_DIMENSION;c++) {
				strGrille.append(grille[r][c]);
				if((c+1)%3==0) strGrille.append(' ');
			}
			strGrille.append('\n');
			if((r+1)%3==0) strGrille.append('\n');
		}

		return strGrille.toString();
	}
	
}
