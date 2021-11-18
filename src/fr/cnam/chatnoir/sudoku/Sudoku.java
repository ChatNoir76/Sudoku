package fr.cnam.chatnoir.sudoku;

import fr.cnam.chatnoir.sudoku.core.Grille;
import fr.cnam.chatnoir.sudoku.optimisation.GrilleOptimizer;

public class Sudoku {

	public static void main(String[] args) {
		
		int[] chaine = new int[81];
		for(int r = 0;r<args.length;r++) {
			chaine[r] = Integer.valueOf(args[r]);
		}
		
		Grille grille = new Grille(chaine);
		GrilleOptimizer grilleOptim = new GrilleOptimizer();
		
		System.out.println("Grille initiale -------------\n" + grille);
		
		grilleOptim.displaySolutions(grille);
		
		System.out.println("--- Fin ---");
	}

}

//int[] chaine = new int[] {1,2,3,4,5,6,7,8,9,
//4,5,6,7,8,9,1,2,3,
//7,8,9,1,2,3,4,5,6,
//2,3,4,5,6,7,8,9,1,
//5,6,7,8,9,1,2,3,4,
//8,9,1,2,3,4,5,6,7,
//3,4,5,6,7,8,9,1,2,
//6,7,8,9,1,2,3,4,5,
//9,1,2,3,4,5,6,7,8};

//int[] chaine = new int[] {0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,
//  0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,
//0,0,0,0,0,0,0,0,0,};

//int[] chaine = new int[] {0,0,0,0,7,0,0,0,0,
//2,0,0,0,0,4,0,0,3,
//0,9,0,8,0,0,0,5,0,
//0,0,9,0,0,0,4,0,0,
//  0,0,0,0,0,1,0,0,0,
//0,4,0,6,0,5,0,8,0,
//0,0,0,0,9,0,0,7,0,
//0,0,3,7,0,6,0,0,5,
//0,6,0,0,1,0,0,0,0};
