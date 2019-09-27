
// This class controls the game
class Oxo{
private char player = ' ';

  public static void main(String[] args){
    Oxo program = new Oxo();
    if(program.AI_OR_MULTIPLAYER() == 1){program.Play_Person();}
    else{program.Play_AI();}
  }

  private int AI_OR_MULTIPLAYER()
  {
    System.out.print("\nInput 1 to play multiplayer, or 2 to play with AI: ");
    Display object = new Display();
    int choice = object.Choose_Mode();

    switch(choice){
      case 1:
      return 1;
      case 2:
      return 2;
      default: System.out.println("Please enter 1 or 2...\n");
      return AI_OR_MULTIPLAYER();
    }
  }

  private void Play_Person()
  {
    Board object = new Board();

    while(object.Board_Check(player) != true){//Neither win or draw
      player = object.Switch_Player();
      Execute_Game(player);
    }

    Print_Final_Board();
  }

  private void Play_AI()
  {
    System.out.println("\n\nYou are Noughts!");
    Board object = new Board();
    AI program = new AI();

    while(program.Winner_Found() != true){
      Change_Player(player);
      Execute_Game(player);
      char[][] temp_grid = object.Get_Array();
      Change_Player(player);
      program.AI_Move(temp_grid, player);
    }

    Print_Final_Board();
  }

  private void Change_Player(char Character)
  {
    Player_Type program = new Player_Type();
    player = program.Next_Player(Character);
  }

  private void Execute_Game(char player)
  {
    String move;
    Board object = new Board();
    Display program = new Display();

    char[][] temp_grid = object.Get_Array();
    do{
      program.Print_Board(temp_grid);//Prints the current board 
      move = program.Recieve_Input();
    }while(object.Move_Check(move, player, temp_grid) == 0);

   }

  void Print_Final_Board()
  {
    Board object = new Board();
    Display program = new Display();

    char[][] final_grid = object.Get_Array();
    program.Print_Board(final_grid);
  }

}
