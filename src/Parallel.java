import java.util.ArrayDeque;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Parallel {
	
	private static CommonResourceTransitiveClosure commonResource = null;
	private static CyclicBarrier BARRIER = null;	
	
	public static void run(boolean A[][], boolean B[][]) {
		
		boolean AB[][] = Matrix.intersection(A, B);
        commonResource = new CommonResourceTransitiveClosure(AB.length);
		BARRIER = new CyclicBarrier(4, commonResource);
		
		Thread t[] = new Thread[4];		
        for (int i = 0; i < 4; i++) {				             
            t[i] = new Thread(new TransitiveClosureThread(commonResource, i, AB, BARRIER));
            t[i].start();
        }
        		
	}
	
}

class CommonResourceTransitiveClosure implements Runnable {
    
	boolean C[][];	
	CommonResourceTransitiveClosure(int size) {
		C = new boolean[size][size];
	}	
	
    synchronized void write(boolean D[], int index) {
    	C[index] = D;
    }
    
    boolean[][] getC() {
    	return C;
    }
    
    @Override
    public void run() {
    	boolean Q[][] = Matrix.transpose(C);
        boolean newC[][] = Matrix.logicalMultiplication(C, Q);
        boolean blockC[][] = Matrix.getBlockMatrix(newC, false);        
    }
}
 
class TransitiveClosureThread implements Runnable {
 
    CommonResourceTransitiveClosure res;
    int vertex, N;
    boolean G[][];
    CyclicBarrier BARRIER;
    TransitiveClosureThread(CommonResourceTransitiveClosure res, int vertex, boolean G[][], CyclicBarrier BARRIER){
        this.res = res;
        this.vertex = vertex;
        this.G = G;
        this.BARRIER = BARRIER;
        this.N = vertex;
    }
    
    @Override
    public void run() {
    	
    	long startTime = System.currentTimeMillis();
    	
    	while (vertex < G[0].length) {
	    	
    		int B[] = new int[G.length];
			for (int j = 0; j < B.length; j++) B[j] = -1;
			B[vertex] = 1;
			
			boolean D[] = new boolean[G.length];
			for (int i = 0; i < D.length; i++) D[i] = false;
			D[vertex] = true;
					
			ArrayDeque<Integer> Q = new ArrayDeque<>(); // очередь вершин
			Q.addLast(vertex);							// первая вершина задаётся как параметр функции (по заданию 7-ая (x(6)))
				
			ArrayDeque<Integer> L = new ArrayDeque<>(); // очередь расстояний
			L.addLast(B[vertex]);						// первое расстояние = 0
				
			while (!Q.isEmpty()) {						// обход в ширину 	 		
					
				int i = Q.removeFirst();
					
				int length = L.removeFirst() + 1;						
				for (int j = 0; j < length; j++)						
					if (G[i][j] && B[j] != 0 && B[j] == -1) {					
						Q.addLast(j);
						L.addLast(length);
						if (length > 0) {
							B[j] = length;
							D[j] = true;
						}
					} 								
			}
	    	
	        res.write(D, vertex);
	    	vertex += 4;			
		}
    	
        long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("  >> t(" + N + ") = " + timeSpent + "мс");
        
        try {
			BARRIER.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}