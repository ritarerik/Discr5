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
						System.out.println(">> ÌÀÒÐÈÖÀ ÑÌÅÆÍÎÑÒÈ >\n");
	
						boolean A[][] = {{false, true, true,  false,  false, false},
								 		 {false, true,  false, false, true,  false},
										 {false, false, false, false, false, false},
										 {false, false, true,  false, false, false},
										 {true,  false, false, true,  false, false},
										 {true,  false, false, false, true,  true}};					
						
						Matrix.printBOOLEAN(A, 'x', 'x'); 
				        System.out.println("\n");
				        
				        System.out.println(">> ÏÐßÌÎÅ ÒÐÀÍÇÈÒÈÂÍÎÅ ÇÀÌÛÊÀÍÈÅ ÄËß 7-ÎÉ ÂÅÐØÈÍÛ (x(6))>\n");
//				        int B[] = TransitiveClosure.getDirect(A, 1); // ÏÅÐÅÄÅËÀÒÜ ÍÀ 6!!!!!!!!!!!!!!!
//				        Matrix.printINT_VECTOR(B, 'x');
				        System.out.println("\n");
				        
				        System.out.println(">> ÎÁÐÀÒÍÎÅ ÒÐÀÍÇÈÒÈÂÍÎÅ ÇÀÌÛÊÀÍÈÅ ÄËß 7-ÎÉ ÂÅÐØÈÍÛ (x(6))>\n");
//				        int C[] = TransitiveClosure.getReverse(A, 2); // ÏÅÐÅÄÅËÀÒÜ ÍÀ 6!!!!!!!!!!!!!!!
//				        Matrix.printINT_VECTOR(C, 'x');
				        System.out.println("\n");
				        
				        System.out.println(">> ÏÎÑÈÊ ÑËÀÁÛÕ ÊÎÌÏÎÍÅÍÒ ÃÐÀÔÀ >\n");
						
//				        TransitiveClosure.printComponents(A);
				        break;
					}
				}
				
				System.out.println("\n");
		         
			} 
		
		in.close();
		
	}
	
}
