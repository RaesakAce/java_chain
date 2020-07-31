//Marco Casaglia 5759711
package chain;

import java.util.Scanner;

public class Run {
	public static <T> void main(String[] args){	
		boolean quit = false;
		DLChain<String> chain = new DLChain<String>();
		System.out.println("Insert first node element ");
		Scanner reader = new Scanner(System.in); 
		String e = reader.nextLine(); 
		System.out.println("Insert first node key ");
		int k = reader.nextInt(); 
		reader.nextLine();
		while (k<1 || k>1000000) {
			System.out.println("Invalid key inserted ");
			k = reader.nextInt(); 
			reader.nextLine();
		}
		chain.addFirst(e, k);
		while(quit==false) {
			System.out.println("Select operation number: \n"
					+ "0 :Add Node\n"
					+ "1 :Remove Node containing Key\n"
					+ "2 :Search Node with Key\n"
					+ "3 :Count Nodes\n"
					+ "4 :Get Nodes String\n"
					+ "5 :Get Nodes by Key\n"
					+ "9 :Quit");
			int input = reader.nextInt(); 
			reader.nextLine();
			
			
			switch(input) {
			case 0: System.out.println("Insert node element ");
			e = reader.nextLine();  
			System.out.println("Insert node key ");
			k = reader.nextInt();
			reader.nextLine();
			while (k<1 || k>1000000) {
				System.out.println("Invalid key inserted ");
				k = reader.nextInt();
				reader.nextLine();
			}
			chain.addNode(e, k);
			break;
			
			case 1: System.out.println("Insert node key ");
			k = reader.nextInt(); 
			reader.nextLine();
			chain.removeNode(k);
			break;
			
			case 2: System.out.println("Insert node key ");
			k = reader.nextInt(); 
			reader.nextLine();
			System.out.println(chain.search(k));
			break;
			
			case 3: System.out.println("The chain contains "+chain.countNodes()+" nodes.");
			break;
			
			case 4: System.out.println(chain.chainToString());
			break;
			
			case 5: System.out.println(chain.keyOrderString());
			break;
			
			case 9: quit=true;
			break;
			
			default: System.out.println("Invalid number ");
			break;
			}
			
		}
		reader.close();
	}

}
