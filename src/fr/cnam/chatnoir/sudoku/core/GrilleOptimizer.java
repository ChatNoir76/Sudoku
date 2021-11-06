package fr.cnam.chatnoir.sudoku.core;

import java.util.ArrayList;

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
				for(int r = 0;r<Grille.MAX_DIMENSION;r++) {
					for(int c = 0;c<Grille.MAX_DIMENSION;c++) {
						for(int v = 0;v<Grille.MAX_DIMENSION;v++) {
							if(grille.getValue(r+1, c+1) == 0) {
								Grille newGrille = new Grille(grille);
								newGrille.setValue(r+1, c+1, v+1);
								findSolution(newGrille);
							}
						}
					}
				}
			}
		}
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
