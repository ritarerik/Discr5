import java.util.ArrayDeque;
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
		
		System.out.print("        |————");
		for (int i = 0; i < A[0].length - 1; i++) {
			System.out.print("—————");
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
		System.out.println("        |—————");

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
	// прямое транзитивное замыкание 
	public static boolean[][] getDirectTransitiveClosure(boolean G[][]) {
			
		boolean C[][] = new boolean[G.length][G[0].length];
		
		for (int vertex = 0; vertex < G.length; vertex ++) {
			
			int B[] = new int[G[0].length];
			for (int i = 0; i < B.length; i++) B[i] = -1;
			B[vertex] = 0;
			
			boolean D[] = new boolean[G[0].length];
			for (int i = 0; i < D.length; i++) D[i] = false;	
					
			ArrayDeque<Integer> Q = new ArrayDeque<>(); // очередь вершин
			Q.addLast(vertex);							// первая вершина задаётся как параметр функции (по заданию 7-ая (x(6)))
				
			ArrayDeque<Integer> L = new ArrayDeque<>(); // очередь расстояний
			L.addLast(B[vertex]);						// первое расстояние = 0
				
			while (!Q.isEmpty()) {						// обход в ширину 	 		
					
				int i = Q.removeFirst();
					
				int length = L.removeFirst() + 1;						
				for (int j = 0; j < G[0].length; j++)						
					if (G[i][j] && B[j] != 0 && B[j] == -1) {					
						Q.addLast(j);
						L.addLast(length);
						if (length > 0)
						B[j] = length;
						D[j] = true;
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
	
	
}
