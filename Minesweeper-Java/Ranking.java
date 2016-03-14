
import java.util.Scanner;

/**
 * This is class store name and score of players
 * The class can store up to top 5 players
 */
public class Ranking{

	private final int MAX_PEOPLE_LIMIT=5;
	private String[] name;
	private int[] record;
	private int last;
	
	 /** 
	  * Class constructor.
	  */
	Ranking(){
		name=new String[MAX_PEOPLE_LIMIT];
		record=new int[MAX_PEOPLE_LIMIT];
		last=0;
	}
	
	 /** 
	    * cheeks if mines at randomRow and randomCol already set true it jumps else it will be true.
	    * @param result         the final score at exit or game over 
	    */
	@SuppressWarnings("resource")
	public void recordName(int result) {
		System.out.print("\n Please enter your name -");
		Scanner inn=new Scanner(System.in);
		String newName=inn.nextLine();
		
		// if new record is less than the 5th result recorded it will be discarded  
		if((last==MAX_PEOPLE_LIMIT)&&record[MAX_PEOPLE_LIMIT-1]>result){
			System.out.println("\nSorry you cannot enter top "+(MAX_PEOPLE_LIMIT)+" players");
			return;
		}
		//the player with smallest score replaced by new 
		else if(last==MAX_PEOPLE_LIMIT){
			name[MAX_PEOPLE_LIMIT-1]=newName;
			record[MAX_PEOPLE_LIMIT-1]=result;
		}
		else{
			name[last]=newName;
			record[last]=result;
			last++;
		}
		sort();//@see sort
		show();// @see show
	}

	 /** 
	    * this display the array which holds players and there score. 
	    */
	public void show() {
		if(last==0){
			System.out.println("Still no results");
			return;
		}
		System.out.println("N Name\t\tresult");
		for(int i=0;i<last;i++){
			System.out.println((i+1)+" "+name[i]+"\t"+record[i]);
		}
	}
	
	 /** 
	    * sorts players (name) and scores (record) accordingly by record ascending 
	    */	
	private void sort(){
		if(last<2) return;
		boolean unsorted=true;
		while(unsorted)
		{
			unsorted=false;
			for(int i=0;i<(last-1);i++)
			{
				if(record[i+1]>record[i])
					unsorted=swaper(i+1,i);
			}
		}
	}
	
	 /** 
	    * swap both arrays (record and name) at index i and  j 
	    * @param j         index of the record
	    * @param i         index of the record
	    * @return boolean         returns true if the swap operation done.
	    */
	private boolean swaper(int j, int i ){
		int swapR=record[i];
		record[i]=record[j];
		record[j]=swapR;
		String swapN=name[i];
		name[i]=name[j];
		name[j]=swapN;
		return true;
	}
	
}
