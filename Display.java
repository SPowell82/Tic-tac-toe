
import java.util.*;
import java.io.*;

//Recieves input from the user and prints the board
class Display{
private char[] move = new char[2];// i.e. 'b2'

  public int Choose_Mode()
  {
    try{
    Scanner choice = new Scanner(System.in);
    int mode = choice.nextInt();
    return mode;
  }catch(Exception e){
    System.out.print("Error: ");
    }
    return 0;
  }

  public String Recieve_Input()
  {
    Scanner ss = new Scanner(System.in);
    System.out.print("Make your move: ");
    String move = ss.nextLine();

    return move;
  }

  public void Print_Board(char[][] grid)
  {
    int num = 0;

    System.out.println("  123");
    for(int i=0; i<3; i++){
      System.out.print(Print_Value(num) + " ");//Prints a,b or c for each row 
       for(int k=0; k<3; k++){
         System.out.print(grid[i][k]);
       }
       num++;
       System.out.println("\n");
    }
   }

   private char Print_Value(int number)
   {
     switch(number){
       case 0:
       return 'a';
       case 1:
       return 'b';
       case 2:
       return 'c';
       default: System.out.println("Incorrect number used..");
       System.exit(1);
     }

     throw new Error("Processing error...");
   }

}
