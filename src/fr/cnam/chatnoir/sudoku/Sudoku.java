package fr.cnam.chatnoir.sudoku;

import fr.cnam.chatnoir.sudoku.core.Grille;
import fr.cnam.chatnoir.sudoku.core.GrilleOptimizer;

public class Sudoku {

	public static void main(String[] args) {
		
//		int[] chaine = new int[] {1,2,3,4,5,6,7,8,9,
//								  4,5,6,7,8,9,1,2,3,
//								  7,8,9,1,2,3,4,5,6,
//								  2,3,4,5,6,7,8,9,1,
//								  5,6,7,8,9,1,2,3,4,
//								  8,9,1,2,3,4,5,6,7,
//								  3,4,5,6,7,8,9,1,2,
//								  6,7,8,9,1,2,3,4,5,
//								  9,1,2,3,4,5,6,7,8};
		
		int[] chaine = new int[] {0,2,0,4,0,6,7,8,9,
								  4,0,6,7,8,0,1,2,3,
								  0,0,9,1,2,3,4,5,6,
								  2,3,4,5,6,7,8,9,1,
								  5,0,0,8,9,1,2,3,4,
								  8,9,1,2,3,4,5,6,7,
								  3,0,5,6,7,8,9,1,2,
								  0,7,8,9,1,2,3,4,5,
								  9,0,2,3,4,5,6,7,8};

		Grille grille = new Grille(chaine);
		GrilleOptimizer grilleOptim = new GrilleOptimizer();
		
		System.out.println("Grille initiale -------------\n" + grille);
//		System.out.println(grille.estRempliCorrectement());
//		System.out.println(grille.estValide());
		
		grilleOptim.displaySolutions(grille);
		
		System.out.println("--- Fin ---");
		
		
	}

}
