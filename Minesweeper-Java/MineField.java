
import java.util.Random;
 /*
  * This is a simulation of play ground greed
  * This is class create and utilize play ground 5 by 10 greed.
  */
class MineField{

	private boolean[][] mines,visible;
	private boolean boom;
	private final int rowMax = 5;
	private final int colMax = 10;
	
   /* 
    * Class constructor.
    */
	MineField(){
		
		mines=new boolean[rowMax][colMax];
		visible=new boolean[rowMax][colMax];
		boom=false;
		
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				mines[row][col]=false;
				visible[row][col]=false;
			}
		}
		
		int counter2=15; // 15 Coordinates will have true value
		int randomRow,randomCol;
		Random RGenerator=new Random(); //random number generator
		
		while(counter2>0){
			randomRow=Math.abs(RGenerator.nextInt()%rowMax);
			randomCol=Math.abs(RGenerator.nextInt()%colMax);
			//if it is not marked before mark it true esle it will do it again
			// @see trymove()
			if(trymove(randomRow,randomCol)){
				counter2--;
			}
		}
	}
	
   /** 
    * cheeks if mines at randomRow and randomCol already set true it jumps else it will be true.
    * @param randomRow         the x-coordinate (row)
    * @param randomCol         the y-coordinate (column)
    * @return status if mines value changed or not 
    */
	private boolean trymove(int randomRow, int randomCol) {
		if(mines[randomRow][randomCol]){
			return false;
		}
		else{
			mines[randomRow][randomCol]=true;
			return true;
		}
	}
	/** 
	  * it will set visibility  true all the way and end the operation
	  */
	private void boom() {
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				if(mines[row][col]){
					visible[row][col]=true;
				}
			}
		}
		boom=true;
		show();
	}

	 /** 
	    * @returns character that represents point in the greed.
	    * @param row         the x-coordinate (row)
	    * @param col         the y-coordinate (column)
	    */
	private char drawChar(int row, int col) {
		int count=0;
		if(visible[row][col]){
			if(mines[row][col]) return '*';
			for(int irow=row-1;irow<=row+1;irow++){
				for(int icol=col-1;icol<=col+1;icol++){
					if(icol>=0&&icol<colMax&&irow>=0&&irow<rowMax){
						if(mines[irow][icol]) count++;
					}
				}
			}
		}
		else{
			if(boom){
				return '-';
			}
			{
				return '?';
			}
		}
		if(0<= count && count < 9){
			return (char) ((char) count + '0');
		}
		else
		{
			return 'X';
		}
	}
	
	 /** 
	    * @returns boom value
	    */
	public boolean getBoom(){
		return boom;
	}

	 /** 
	    * this validate if the user typed valid input 
	    * convert to int and send to function that register the input.
	    * @returns boolean  registration is well done.
	    * @param input    receive user input  expected (row column)
	    */
	public boolean legalMoveString(String input) {
		String[] separated=input.split(" ");
		int row;
		int col;
		try{
			row=Integer.parseInt(separated[0]);
			col=Integer.parseInt(separated[1]);
			if(row<0||col<0||row>=rowMax||col>=colMax){
				throw new java.io.IOException();
			}
		}
		catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}
		
		if(legalMoveValue(row,col)){
			return true;
		}
		else{
			return false;
		}
	}

	/** 
	    * this validate if coordinates are free to stepped on
	    * after stepped on cheeks if it is on mines if true Booooooom  
	    * convert to int and send to function that register the input.
	    * @returns boolean  false for boom and already stepped point 
	    * @param row         the x-coordinate (row)
	    * @param col         the y-coordinate (column)
	    */
	private boolean legalMoveValue(int row, int col) {
		
		if(visible[row][col]){
			System.out.println("You stepped in allready revealed area!");
			return false;
		}
		else{
			visible[row][col]=true;
		}
		
		if(mines[row][col]){
			boom();
			return false;
		}
		
		return true;
	}
	
	/** 
	  * it draw the game greed  
	  */
	public void show() {
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 ");
		System.out.println("   ---------------------");
		for(int row=0;row<rowMax;row++){
			System.out.print(row+" |");
			for(int col=0;col<colMax;col++){
				System.out.print(" "+ drawChar(row,col));
				
			}
			System.out.println(" |");
		}
		System.out.println("   ---------------------");
	}
	
}
