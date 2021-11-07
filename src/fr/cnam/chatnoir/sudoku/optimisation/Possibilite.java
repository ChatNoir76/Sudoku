package fr.cnam.chatnoir.sudoku.optimisation;

import java.util.ArrayList;

public class Possibilite {

	public Possibilite() {
		for(int c = 1;c<10;c++) {
			listeChoix.add(c);
		}
		ligne = 1;
		colonne = 1;
	}
	
	public int ligne;
	public int colonne;
	public ArrayList<Integer> listeChoix = new ArrayList<Integer>();
	
}
