// ìàòðè÷íûé ìåòîä ðàçáèåíèÿ

import java.util.Scanner;

public class MainClass {
	
	public static void main(String args[])
	{
		
		boolean exit = false;
		Scanner in = new Scanner(System.in);
					
			System.out.println("> Ïîñëåäîâàòåëüíûé è ïàðàëëåëüíûé àëãîðèòìû >> 1");
			System.out.println("> Ïðèìåð >> 2");
			System.out.println("> Âûõîä >> 0");
			System.out.print("\n  >> ");
			
			int input = 0;			
			
			try { 
				input = in.nextInt();				
				if (input == 0) exit = true;				
			} catch (Exception e) { 
				System.out.println("\n> Íåâåðíûé ââîä\n");
			}
			
			if (!exit) {			
				
				switch (input) {
					case 1 : { 
						
						System.out.println();
						System.out.print("> Êîëè÷åñòâî âåðøèí (16 -- 4096) >> ");
						
						int count = 0;						
						try { 
							count = in.nextInt();				
							if (count == 0 || count < 16 || count > 4096) throw new Exception();				
						} catch (Exception e) { 
							System.out.println("\n> Íåâåðíûé ââîä\n");
							break;
						}						
						
						// ïîñëåäîâàòåëüíûé àëãîðèòì
						System.out.println("\n> Ïîñëåäîâàòåëüíûé àëãîðèòì >");
						boolean A[][] = Matrix.createBOOLEAN(count);					
//				        TransitiveClosure.printComponents(A);
				        
				        // ïàðàëëåëüíûé àëãîðèòì
				        System.out.print("> Ïàðàëëåëüíûé àëãîðèòì >");
//						TransitiveClosure.printComponentsMULTITHREAD(A);
				        break;
					}
									        					
					case 2 : {
						
						System.out.println();
						System.out.println(">> ÌÀÒÐÈÖÀ A >\n");	
						boolean A[][] = {{false, true,  false, false, false, false},
								 		 {false, true,  false, false, true,  false},
										 {false, false, true,  false, false, false},
										 {false, false, false, false, false, false},
										 {true,  false, false, true,  false, false},
										 {false, false, false, false, true,  true}};
						Matrix.printBOOLEAN(A, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ÌÀÒÐÈÖÀ B >\n");
				        boolean B[][] = {{false, false, false, true,  false, false},
								 		 {true,  true,  false, false, false, false},
										 {false, false, true,  false, false, false},
										 {false, false, false, true,  false, false},
										 {false, false, false, true,  true,  false},
										 {false, false, false, true,  false, false}};
						Matrix.printBOOLEAN(B, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ÏÅÐÅÑÅ×ÅÍÈÅ >\n");
				        boolean AB[][] = Matrix.intersection(A, B);
				        Matrix.printBOOLEAN(AB, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ÌÀÒÐÈÖÀ ÄÎÑÒÈÆÈÌÎÑÒÈ (R) >\n");
				        boolean R[][] = Matrix.getDirectTransitiveClosure(AB);
				        Matrix.printBOOLEAN(R, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ÌÀÒÐÈÖÀ ÊÎÍÒÐÄÎÑÒÈÆÈÌÎÑÒÈ (Q) >\n");
				        boolean Q[][] = Matrix.transpose(R);
				        Matrix.printBOOLEAN(Q, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ËÎÃÈ×ÅÑÊÎÅ ÏÅÐÅÌÍÎÆÅÍÈÅ ÌÀÒÐÈÖ (R & Q = C) >\n");
				        boolean C[][] = Matrix.logicalMultiplication(R, Q);
				        Matrix.printBOOLEAN(C, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ÁËÎ×ÍÎ-ÄÈÀÃÎÍÀËÜÍÀß ÌÀÒÐÈÖÀ >\n");
				        boolean blockC[][] = Matrix.getBlockMatrix(C);
				        Matrix.printBOOLEAN(blockC, 'x', 'x'); 
				        System.out.println("\n");
				        
				        break;
					}
				}
				
				System.out.println("\n");
		         
			} 
		
		in.close();
		
	}
	
}
