import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Matrix {
	
	private static boolean vectors[][];
	private static int indexes[];
	
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
		
		System.out.print("        |����");
		for (int i = 0; i < A[0].length - 1; i++) {
			System.out.print("�����");
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
	public static boolean[][] intersection(boolean[][] A, boolean[][] B) {
		
		boolean C[][] = new boolean[A.length][A.length];		
		
		for (int i = 0; i < A.length; i++) 
			for (int j = 0; j < A.length; j++) {
				C[i][j] = A[i][j] || B[i][j];
			}
		
		return C;		
		
	}
		
	//--------------------------------------------------------------//
	// ������ ������������ ��������� 
	public static boolean[][] getDirectTransitiveClosure(boolean G[][]) {
			
		boolean C[][] = new boolean[G.length][G[0].length];
		
		long startTime = System.currentTimeMillis();
		
		for (int vertex = 0; vertex < G.length; vertex ++) {
			
			int B[] = new int[G[0].length];
			for (int i = 0; i < B.length; i++) B[i] = -1;
			B[vertex] = 1;
			
			boolean D[] = new boolean[G[0].length];
			for (int i = 0; i < D.length; i++) D[i] = false;
			D[vertex] = true;
					
			ArrayDeque<Integer> Q = new ArrayDeque<>(); // ������� ������
			Q.addLast(vertex);							// ������ ������� ������� ��� �������� ������� (�� ������� 7-�� (x(6)))
				
			ArrayDeque<Integer> L = new ArrayDeque<>(); // ������� ����������
			L.addLast(B[vertex]);						// ������ ���������� = 0
				
			while (!Q.isEmpty()) {						// ����� � ������ 	 		
					
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
		
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("  >> t = " + timeSpent + "��\n");
		
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
	public static boolean[][] getBlockMatrix(boolean A[][], boolean print) {

		vectors = A;
		
		// ��� ���������� ����� ���� ���������� �� ������� � ��� �����,
		// ��� ��� ���������, ��� �������� ������� ����������� ������������ �������  
		// ��������� -> ������, ��� ��, ��� ����������� ��� ��������, ����������� 
		// � ��� ����� -> ������� ����������� �����, ��� � ������
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		
		ArrayList<Integer> checked = new ArrayList<>();		
		indexes = new int[A.length];
		for (int i = 0; i < A.length; i++) indexes[i] = i; 
		
		for (int i = 0; i < A.length; i++) {
			
			for (int j = 0; j < A[0].length; j++) {				
				if (A[i][j] && !checked.contains(j)) { // && !isThisTrueSingle(A[i])
					left.add(j);
					checked.add(j);
				}
				else right.add(j);
			}

			vectors = getNewVectors(left, right);
	
			left.clear();
			right.clear();
			
		}
		
		if (print) printBOOLEAN_blockMatrix();
		return vectors;
		
	}
	
	private static boolean[][] getNewVectors(ArrayList<Integer> left, ArrayList<Integer> right) {
		
		boolean vectorsNew[][] = new boolean[vectors.length][vectors[0].length];
		boolean vectorsNewTMP[][] = new boolean[vectors.length][vectors[0].length];
		
		int indexesNew[] = new int[indexes.length];
		
		int index = 0;
		for (int i = 0; i < left.size(); i++) {		
			vectorsNewTMP[index] = vectors[left.get(i)];
			indexesNew[index] = indexes[left.get(i)];
			index++;
		}
		
		for (int i = 0; i < right.size(); i++) {			
			vectorsNewTMP[index] = vectors[right.get(i)];
			indexesNew[index] = indexes[right.get(i)];
			index++; 
		}
		
		index = 0;
		for (int i = 0; i < left.size(); i++) {			
			for (int j = 0; j < vectorsNewTMP[0].length; j++) {
				vectorsNew[j][index] = vectorsNewTMP[j][left.get(i)];
			}			
			index++;			
		}
		
		for (int i = 0; i < right.size(); i++) {			
			for (int j = 0; j < vectorsNewTMP[0].length; j++) {
				vectorsNew[j][index] = vectorsNewTMP[j][right.get(i)];
			}			
			index++;			
		}
		
		indexes = indexesNew;
				
		return vectorsNew;
		
	}
	
	public static boolean isThisTrueSingle(boolean A[]) {		
		int count = 0;		
		for (int i = 0; i < A.length; i++) {
			if (A[i]) count++;
			if (count > 1) return true;
		}					
		return false;		
	}
	
	public static void printBOOLEAN_blockMatrix() {
		
		System.out.print("        |");
		for (int i = 0; i < vectors[0].length; i++) {
			System.out.print("x(" + indexes[i] + ") ");
		}
		System.out.println();
		
		System.out.print("        |����");
		for (int i = 0; i < vectors[0].length - 1; i++) {
			System.out.print("�����");
		}
		System.out.println();

		for (int i = 0; i < vectors.length; i++) {
			System.out.print("   x(" + indexes[i] + ") |");
			for (int j = 0; j < vectors[0].length; j++) {				
				if (vectors[i][j] == true) System.out.print("  " + 1 + " ");
					else System.out.print("  " + 0 + " ");
				if (j < vectors[0].length - 1) System.out.print(" ");	        				
			}			
			if (i < vectors.length - 1) System.out.print("\n");	        			
		}		
	}
	
}
