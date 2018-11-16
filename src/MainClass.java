// матричный метод разбиения

import java.util.Scanner;

public class MainClass {
	
	public static void main(String args[])
	{
		
		boolean exit = false;
		Scanner in = new Scanner(System.in);
					
			System.out.println("> Последовательный и параллельный алгоритмы >> 1");
			System.out.println("> Пример >> 2");
			System.out.println("> Выход >> 0");
			System.out.print("\n  >> ");
			
			int input = 0;			
			
			try { 
				input = in.nextInt();				
				if (input == 0) exit = true;				
			} catch (Exception e) { 
				System.out.println("\n> Неверный ввод\n");
			}
			
			if (!exit) {			
				
				switch (input) {
					case 1 : { 
						
						System.out.println();
						System.out.print("> Количество вершин (16 -- 4096) >> ");
						
						int count = 0;						
						try { 
							count = in.nextInt();				
							if (count == 0 || count < 16 || count > 4096) throw new Exception();				
						} catch (Exception e) { 
							System.out.println("\n> Неверный ввод\n");
							break;
						}						
						
						// последовательный алгоритм
						System.out.println("\n> Последовательный алгоритм >");
						boolean A[][] = Matrix.createBOOLEAN(count);					
//				        TransitiveClosure.printComponents(A);
				        
				        // параллельный алгоритм
				        System.out.print("> Параллельный алгоритм >");
//						TransitiveClosure.printComponentsMULTITHREAD(A);
				        break;
					}
									        					
					case 2 : {
						
						System.out.println();
						System.out.println(">> МАТРИЦА A >\n");	
						boolean A[][] = {{false, true,  false, false, false, false},
								 		 {false, false, false, false, true,  false},
										 {false, false, false, false, false, false},
										 {false, false, false, false, false, false},
										 {true,  false, false, true,  false, false},
										 {false, false, false, false, true,  true}};
						Matrix.printBOOLEAN(A, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> МАТРИЦА B >\n");
				        boolean B[][] = {{false, false, false, true,  false, false},
								 		 {true,  true,  false, false, false, false},
										 {false, false, true,  false, false, false},
										 {false, false, false, false, false, false},
										 {false, false, false, true,  false, false},
										 {false, false, false, true,  false, false}};
						Matrix.printBOOLEAN(B, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ПЕРЕСЕЧЕНИЕ >\n");
				        boolean C[][] = Matrix.intersection(A, B);
				        Matrix.printBOOLEAN(C, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> МАТРИЦА ДОСТИЖИМОСТИ >\n");
				        boolean D[][] = Matrix.getDirectTransitiveClosure(C);
				        Matrix.printBOOLEAN(D, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> МАТРИЦА КОНТРДОСТИЖИМОСТИ >\n");
				        boolean E[][] = Matrix.transpose(D);
				        Matrix.printBOOLEAN(E, 'x', 'x'); 
				        System.out.println("\n");
				        
				        
				        break;
					}
				}
				
				System.out.println("\n");
		         
			} 
		
		in.close();
		
	}
	
}
