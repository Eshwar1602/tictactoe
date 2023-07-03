import java.util.HashSet;
import java.util.Scanner;

public class tictactoe {
	
	static HashSet<Integer> user_set=new HashSet<Integer>();
	static HashSet<Integer> comp_set=new HashSet<Integer>();   //collection to check the entered number is already present or not 
	
	public static void printboard(char[][] board) { // Method to print the tic tac toe Structure
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				System.out.print(board[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println(" ");
	}
	
	public static void XO(char [][] board, int pos, String user, char X_or_O) { // Method for printing Elements in the Position 
		
		 char symbol='X';
		 
		 if(user.equals("You")) {   // Users element
			 symbol= X_or_O;
			 user_set.add(pos);  // adds every position to the users hashset
			 
		 }
		 else if(user.equals("Comp"))     // computer element
		 {
			 if(X_or_O=='X' || X_or_O=='x')  // If user chooses x then computer takes O
			 symbol='O';
			 else                                // If user chooses o then computer takes x
			 symbol='X';
			 comp_set.add(pos);              // adds every position to the computer hashset
		 }
		 
		 
		 switch(pos){          // Switch case for switching the position according to user(input)
		 
		 case 1:
			 board[0][0]=symbol;
			 break;
		 case 2:
			 board[0][2]=symbol;
			 break;
		 case 3:
			 board[0][4]=symbol;
			 break;
		 case 4:
			 board[2][0]=symbol;
			 break;
		 case 5:
			 board[2][2]=symbol;
			 break;
		 case 6:
			 board[2][4]=symbol;
			 break;
		 case 7:
			 board[4][0]=symbol;
			 break;
		 case 8:
			 board[4][2]=symbol;
			 break;
		 case 9:
			 board[4][4]=symbol;
			 break;
			 
		 default :
		      System.out.println("Invalid input");
		      break;
		 }
		 
		 printboard(board);  // prints the board with X and O with user and computer's input
	}
	
	static int comp_random() { // Method for computer to choose random position
		int max =9;
		int min=1;
		
		int range=max-min+1;
		
		int result= (int) (Math.random()*range+min);
		return result;
		
	}
	
	static String winner() {     // Method for decide the winner
		
		HashSet<Integer> r1=new HashSet<Integer>();
		r1.add(1);
		r1.add(2);
		r1.add(3);
		HashSet<Integer> r2=new HashSet<Integer>();
		r2.add(4);
		r2.add(5);
		r2.add(6);
		HashSet<Integer> r3=new HashSet<Integer>();
		r3.add(7);
		r3.add(8);
		r3.add(9);
		
		HashSet<Integer> c1=new HashSet<Integer>();
		c1.add(7);
		c1.add(4);
		c1.add(1);
		HashSet<Integer> c2=new HashSet<Integer>();
		c2.add(8);
		c2.add(5);
		c2.add(2);
		HashSet<Integer> c3=new HashSet<Integer>();
		c3.add(9);
		c3.add(6);
		c3.add(3);
		
		HashSet<Integer> d1=new HashSet<Integer>();
		d1.add(1);
		d1.add(5);
		d1.add(9);
		HashSet<Integer> d2=new HashSet<Integer>();
		d2.add(7);
		d2.add(5);
		d2.add(3);               // Hashset contains all the rows and columns and diagonal position of the board
		
		HashSet<HashSet> res= new HashSet<HashSet>();
		res.add(r1);
		res.add(r2);
		res.add(r3);
		res.add(c1);
		res.add(c2);
		res.add(c3);
		res.add(d1);
		res.add(d2);           // Hashset contains all the elements of rows and colums and diagonals of above hashset hashset

		for(HashSet s: res) {
		  if(user_set.containsAll(s)) {
			return "YOU WIN" ;
		}
		  else if(comp_set.containsAll(s)){
		    return "COMPUTER WIN";
		}
		}
		
		if(user_set.size()+comp_set.size()==9) {
			return "DRAW";
		}
		
		return "";
	}
	
	public static void main(String args[]){ // Main Method

		Scanner sc=new Scanner(System.in);
		char[][] board= {
				{' ', '|',' ','|',' '},
				{'-', '-','-','-','-'},
				{' ', '|',' ','|',' '},
				{'-', '-','-','-','-'},
				{' ', '|',' ','|',' '}
		};
		
		System.out.println("TIC TAC TOE");
		printboard(board);     // Calls the method print the structure
		
		System.out.println("What you want to choose: X or O"); 
		char X_or_O=sc.next().charAt(0);
		
	   while(X_or_O!='x'  && X_or_O!='X' && X_or_O!='O'&& X_or_O!='o') {  
			System.out.println("Enter the Value X or O");
			X_or_O=sc.next().charAt(0); 
	   }               // Loop for user to choose X or O.If user enters any other input it will starts to loop
	
		while(true) {
			System.out.println("\nEnter any number 1-9 :");
			
			int input = sc.nextInt();
			
			while(true) {
				if(input>=1 && input<=9) {
			 while(user_set.contains(input) || comp_set.contains(input)) {
				 System.out.println("\nPlease Retry, Enter another number:");
				 input= sc.nextInt();
			 }//(While loop for checking hashset). The entered number is checked in the hashset if it present. it will ask user to enter another number
			}
				else {
					System.out.println("Enter number from 1-9: ");
					input=sc.nextInt();
					continue; //(If else for check users enters the number from 1-9
				}
				break;
			}  // While loop for user to enter the number. If user choose any other number from 1 to 9. It will starts to loop
			
			 System.out.println("\nUSER: "+ input );
			 XO(board,input,"You",X_or_O); // calls the XO method by passing parameters for user
			 
			 String res=winner(); 
			 if(res.length()>0)
			 {
				 System.out.println(res);
				 break;
			 }   // Winner checking for user
			 
			 
			 int input1= comp_random();  // for computer random number 
			 while(comp_set.contains(input1) || user_set.contains(input1)) {
				 input1=comp_random();
			 } //(While loop for checking hashset). The number is checked in the hashset if it present. Computer will go for another number
			 
			 System.out.println("COMPUTER: "+ input1);
			 XO(board, input1, "Comp",X_or_O); //calls the XO method by passing parameters for computer
			 
			 res=winner();
			 if(res.length()>0)
			 {
				 System.out.println(res);
				 break;
			 }  // Winner checking for user
		}
	}

}
