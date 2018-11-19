import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Matrix {
	
	public static boolean[][] createBOOLEAN(int size) {		
		boolean A[][] = new boolean[size][size];		
		Random rnd = new Random(System.currentTimeMillis());		
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				A[i][j] = rnd.nextBoolean();		
		return A;		
	}
	
	//--------------------------------------------------------------//
	public static void printBOOLEAN(boolean A[][], char c1, char c2) {
		
		System.out.print("        |");
		for (int i = 0; i < A[0].length; i++) {
			System.out.print(c1 + "(" + i + ") ");
		}
		System.out.println();
		
		System.out.print("        |ЧЧЧЧ");
		for (int i = 0; i < A[0].length - 1; i++) {
			System.out.print("ЧЧЧЧЧ");
		}
		System.out.println();

		for (int i = 0; i < A.length; i++) {
			System.out.print("   " + c2 + "(" + i + ") |");
			for (int j = 0; j < A[0].length; j++) {				
				if (A[i][j] == true) System.out.print("  " + 1 + " ");
					else System.out.print("  " + 0 + " ");
				if (j < A[0].length - 1) System.out.print(" ");	        				
			}			
			if (i < A.length - 1) System.out.print("\n");	        			
		}		
	}
	
	//--------------------------------------------------------------//
	public static void printINT_VECTOR(int A[], char c) {
			
		System.out.print("        |  T*");
		System.out.println();			
		System.out.println("        |ЧЧЧЧЧ");

		for (int i = 0; i < A.length; i++) {
			
			if (A[i] == -1) System.out.print("   " + c + "(" + i + ") |  -");
			else System.out.print("   " + c + "(" + i + ") |  " + A[i] + " ");
				
			if (i < A.length - 1) System.out.print("\n");	        			
		}		
	}
	
	//--------------------------------------------------------------//
	public static boolean[][] intersection(boolean[][] A, boolean[][] B) {
		
		boolean C[][] = new boolean[A.length][A.length];		
		
		for (int i = 0; i < A.length; i++) 
			for (int j = 0; j < A.length; j++) {
				C[i][j] = A[i][j] || B[i][j];
			}
		
		return C;		
		
	}
		
	//--------------------------------------------------------------//
	// пр€мое транзитивное замыкание 
	public static boolean[][] getDirectTransitiveClosure(boolean G[][]) {
			
		boolean C[][] = new boolean[G.length][G[0].length];
		
		for (int vertex = 0; vertex < G.length; vertex ++) {
			
			int B[] = new int[G[0].length];
			for (int i = 0; i < B.length; i++) B[i] = -1;
			B[vertex] = 1;
			
			boolean D[] = new boolean[G[0].length];
			for (int i = 0; i < D.length; i++) D[i] = false;
			D[vertex] = true;
					
			ArrayDeque<Integer> Q = new ArrayDeque<>(); // очередь вершин
			Q.addLast(vertex);							// перва€ вершина задаЄтс€ как параметр функции (по заданию 7-а€ (x(6)))
				
			ArrayDeque<Integer> L = new ArrayDeque<>(); // очередь рассто€ний
			L.addLast(B[vertex]);						// первое рассто€ние = 0
				
			while (!Q.isEmpty()) {						// обход в ширину 	 		
					
				int i = Q.removeFirst();
					
				int length = L.removeFirst() + 1;						
				for (int j = 0; j < G[0].length; j++)						
					if (G[i][j] && B[j] != 0 && B[j] == -1) {					
						Q.addLast(j);
						L.addLast(length);
						if (length > 0) {
							B[j] = length;
							D[j] = true;
						}
					} 								
			}
			
			C[vertex] = D;			
			
		}
		
		return C;
			
	}	
	
	//--------------------------------------------------------------//
	public static boolean[][] transpose(boolean A[][]) {
		
		boolean B[][] = new boolean[A.length][A.length];
		
		for (int i = 0; i < A.length; i++) 
			for (int j = 0; j < A.length; j++) 
				B[j][i] = A[i][j];
			
		return B;		
		
	}
	
	//--------------------------------------------------------------//
	public static boolean[][] logicalMultiplication(boolean[][] A, boolean[][] B) {
			
		boolean C[][] = new boolean[A.length][A.length];		
			
		for (int i = 0; i < A.length; i++) 
			for (int j = 0; j < A.length; j++) {
				C[i][j] = A[i][j] && B[i][j];
			}
			
		return C;		
			
	}
	
	//--------------------------------------------------------------//
	public static boolean[][] getBlockMatrix(boolean A[][], int index) {

		boolean vectors[][] = A;
		
		// при сортировке строк буду записывать их индексы в две части,
		// это при допущении, что исходна€ матрица симметрична относительно главной  
		// диагонали -> считаю, что то, что справделиво дл€ столбцов, справедливо 
		// и дл€ строк -> столбцы переставл€ю также, как и строки
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		
		ArrayList<Integer> checked = new ArrayList<>();
		
		for (int i = 0; i < A.length; i++) {
			
			for (int j = 0; j < A[0].length; j++) {				
				if (A[i][j]) left.add(j);
				else right.add(j);
			}
			
			vectors = getNewVectors(left, right, vectors);
			left.clear();
			right.clear();
			
		}
		
		
//		for (int i = 0; i < A.length; i++) {			
//			boolean vector[] = vectors.get(i);			
//			for (int j = 0; j < A[0].length; j++) A[i][j] = vector[j];
//		}
		
		return vectors;
		
	}
	
	private static boolean[][] getNewVectors(ArrayList<Integer> left, ArrayList<Integer> right, boolean vectors[][]) {
		
		boolean vectorsNew[][] = new boolean[vectors.length][vectors[0].length];
		boolean vectorsNewTMP[][] = new boolean[vectors.length][vectors[0].length];
		
		int index = 0;
		for (int i = 0; i < left.size(); i++) {
			
			boolean v[] = vectors[left.get(i)];			
			vectorsNewTMP[index] = v;
			
			for (int j = 0; j < vectors[0].length; j++) 
				vectorsNewTMP[j][index] = v[j];
			
			
			index++;
		}
		
		for (int i = 0; i < right.size(); i++) {
			
			boolean v[] = vectors[right.get(i)];			
			vectorsNewTMP[index] = v;		
			
			for (int j = 0; j < vectors[0].length; j++) 
				vectorsNewTMP[j][index] = v[j];
			
			index++; 
		}
		
		
		
		index = 0;
		
		return vectorsNew;
		
	}
		
}
