package fr.cnam.chatnoir.sudoku.optimisation;

import java.util.ArrayList;

import fr.cnam.chatnoir.sudoku.core.Grille;

public class GrilleOptimizer {

	ArrayList<Grille> grilleContainer = new ArrayList<Grille>();
	boolean display;
	int compteur = 0;

	/**
	 * Trouve un ensemble de solution au problème
	 * @param grille
	 * @return
	 */
	public ArrayList<Grille> getSolutions(Grille grille){
		display = false;
		this.grilleContainer.clear();
		findSolution(grille);
		return grilleContainer;
	}
	/**
	 * Trouve un ensemble de solution au problème
	 * @param grille
	 * @return
	 */
	public void displaySolutions(Grille grille){
		display = true;
		this.grilleContainer.clear();
		findSolution(grille);
	}
	/**
	 * Cherche une solution pour la grille et ajoute la solution à la liste
	 */
	private void findSolution(Grille grille) {
		if(grille != null) {
			//System.out.println(grille);
			if(grille.estValide()) {
				addGrilleToSolutions(grille);
			} else if(grille.estRempliCorrectement()) {
				Possibilite nextCase = getBestChoice(grille);
				nextCase.listeChoix.forEach(n -> {
					Grille newGrille = new Grille(grille);
					newGrille.setValue(nextCase.ligne, nextCase.colonne, n);
					findSolution(newGrille);
				});
			}
		}
	}
	/**
	 * Détermine la meilleur case à tester pour une nouvelle valeur
	 * @param grille
	 * @return
	 */
	private Possibilite getBestChoice(Grille grille) {
		Possibilite choix = new Possibilite();
		for(int r = 0;r<Grille.MAX_DIMENSION;r++) {
			for(int c = 0;c<Grille.MAX_DIMENSION;c++) {
				if(grille.getValue(r+1, c+1) == 0) {
					ArrayList<Integer> choixPossible = scan(grille, r, c);
					if(choixPossible.size() < choix.listeChoix.size()) {
						choix.listeChoix = choixPossible;
						choix.ligne = r+1;
						choix.colonne = c+1;
					}
				}
			}
		}
		return choix;
	}
	/**
	 * Retourne la liste des choixde valeurs possibles d'une case
	 * @param grille
	 * @param indexLigne
	 * @param indexColonne
	 * @return
	 */
	private ArrayList<Integer> scan(Grille grille, int indexLigne, int indexColonne){
		ArrayList<Integer> mesChoix = getFullListOfChoice();
		//cherche en Ligne
		for(int c = 0;c<Grille.MAX_DIMENSION;c++) {
			int value = grille.getValue(indexLigne + 1, c+1);
			if(value != 0) {
				mesChoix.remove(Integer.valueOf(value));
			}
		}
		//cherche en colonne
		for(int r = 0;r<Grille.MAX_DIMENSION;r++) {
			int value = grille.getValue(r+1, indexColonne + 1);
			if(value != 0) {
				mesChoix.remove(Integer.valueOf(value));
			}
		}
		//cherche en block
		//TODO à faire
		return mesChoix;
	}
	/**
	 * Génère une liste contenant tous les choix des cases
	 * @return
	 */
	private ArrayList<Integer> getFullListOfChoice(){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for(int c = 0;c<Grille.MAX_DIMENSION;c++) {
			liste.add(c+1);
		}
		return liste;
	}
	/**
	 * Ajoute une solution à notre liste
	 * @param grille Grille à ajouter
	 */
	private void addGrilleToSolutions(Grille grille) {
		if(!grilleContainer.contains(grille)) {
			grilleContainer.add(grille);
			if(display) {
				compteur++;
				System.out.println("Solution : " + compteur + " ------------\n" + grille);
			}
		}
	}
}
