// ��������� ����� ���������

import java.util.Scanner;

public class MainClass {
	
	public static void main(String args[])
	{
		
		boolean exit = false;
		Scanner in = new Scanner(System.in);
					
			System.out.println("> ���������������� � ������������ ��������� >> 1");
			System.out.println("> ������ >> 2");
			System.out.println("> ����� >> 0");
			System.out.print("\n  >> ");
			
			int input = 0;			
			
			try { 
				input = in.nextInt();				
				if (input == 0) exit = true;				
			} catch (Exception e) { 
				System.out.println("\n> �������� ����\n");
			}
			
			if (!exit) {			
				
				switch (input) {
					case 1 : { 
						
						System.out.println();
						System.out.print("> ���������� ������ (16 -- 4096) >> ");
						
						int count = 0;						
						try { 
							count = in.nextInt();				
							if (count == 0 || count < 16 || count > 4096) throw new Exception();				
						} catch (Exception e) { 
							System.out.println("\n> �������� ����\n");
							break;
						}						
						
						// ���������������� ��������
						System.out.println("\n> ���������������� �������� >");
						boolean A[][] = Matrix.createBOOLEAN(count);					
//				        TransitiveClosure.printComponents(A);
				        
				        // ������������ ��������
				        System.out.print("> ������������ �������� >");
//						TransitiveClosure.printComponentsMULTITHREAD(A);
				        break;
					}
									        					
					case 2 : {
						
						System.out.println();
						System.out.println(">> ������� A >\n");	
						boolean A[][] = {{false, true,  false, false, false, false},
								 		 {false, true,  false, false, true,  false},
										 {false, false, true,  false, false, false},
										 {false, false, false, false, false, false},
										 {true,  false, false, true,  false, false},
										 {false, false, false, false, true,  true}};
						Matrix.printBOOLEAN(A, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ������� B >\n");
				        boolean B[][] = {{false, false, false, true,  false, false},
								 		 {true,  true,  false, false, false, false},
										 {false, false, true,  false, false, false},
										 {false, false, false, true,  false, false},
										 {false, false, false, true,  true,  false},
										 {false, false, false, true,  false, false}};
						Matrix.printBOOLEAN(B, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ����������� >\n");
				        boolean AB[][] = Matrix.intersection(A, B);
				        Matrix.printBOOLEAN(AB, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ������� ������������ (R) >\n");
				        boolean R[][] = Matrix.getDirectTransitiveClosure(AB);
				        Matrix.printBOOLEAN(R, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ������� ����������������� (Q) >\n");
				        boolean Q[][] = Matrix.transpose(R);
				        Matrix.printBOOLEAN(Q, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ���������� ������������ ������ (R & Q = C) >\n");
				        boolean C[][] = Matrix.logicalMultiplication(R, Q);
				        Matrix.printBOOLEAN(C, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ������-������������ ������� >\n");
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
