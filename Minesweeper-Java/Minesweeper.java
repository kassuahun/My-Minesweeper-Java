import java.util.Scanner;
/**
 * This is class which holds the main method
 * create rank @Ranking, filed(greed) @MineField 
 */

public class Minesweeper {

	private static MineField field;
	private static Ranking rank;
	
	public static void main(String[] args) {
		rank=new Ranking();
		mainMessage();
		
		while(gameCountinue());
		System.out.println("\nThank you for playing :) Have a nice day!");
	}
	
	 /** 
	    * this function read player input, and work accordingly 
	    */
	@SuppressWarnings("resource")
	private static boolean gameCountinue() {
		field = new MineField();
		int result = 0;
		
		while (true) {
			field.show();
			System.out.print("\nPlease enter your move(row col): ");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			//in.close();

			if (input.equals("top")) {
				rank.show();// see @ Ranking
			}
			
			else if (input.equals("restart")) {
				rank.recordName(result);// see @ Ranking
				return true;
			}
			
			else if (input.equals("exit")) {
				rank.recordName(result);// see @ Ranking
				return false;
			}
			
			else if (field.legalMoveString(input)) {//see @ Minefield
				result++;
				if (result == 35) {
					System.out.println("Congratulations you WON the game!");
					rank.recordName(result);// see @ Ranking
					return true;
				}
				
			}
			else if (field.getBoom()) //see @ Minefield
			{
				System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
				rank.recordName(result);// see @ Ranking
				return true;
			}
		} 
	}
	 /** 
	    * displays main menu
	    */
	private static void mainMessage(){
		System.out.println("Welcome to Minesweeper! \n"
				+ "To play just input some coordinates and try not to step ont mine :) \n"
				+ "Usefull commands:\n "
				+ "\t restart- Starts a new game.\n"
				+ "\t exit- Quits the game.\n"
				+ "\t top- Reveals the top scoreboard.\n"
				+ "Have Fun !");
	}
}
