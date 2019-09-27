
//Contains the overall state and moves of the game grid
class Board{
private static char[][] grid = {{'.','.','.'},{'.','.','.'},{'.','.','.'}};
private final int grid_size = 3;
private int current_row = 0;
private int current_col = 0;
private int current_player = 2;

  public static void main(String[] args)
  {
    Board object = new Board();
    object.run();
  }

  public boolean Board_Check(char player)
  {
    if(Winner_Or_Draw(player,grid) == true){return true;}
    else{return false;}
  }

  public void Apply_AI_Move(int row, int col, char AI)
  {
    grid[row][col] = AI;
  }

  public char Switch_Player()
  {
    Player_Type current = new Player_Type();
    char temp = current.Person_Switch(current_player);

    if(current_player == 1){
      System.out.print("\nPlayer 2 (Crosses) move: \n");
      current_player = 2;
    }
    else{
      System.out.print("\nPlayer 1 (Noughts) move: \n");
      current_player = 1;
    }

    return temp;//The Character of the player (i.e. 'X');
  }

  public int Move_Check(String temp, char player, char[][] test_grid)
  {
    if(temp.length() != 2){
      System.out.println("\nMove instruction is invalid...Please try again\n");
      return 0;
    }

    char col = temp.charAt(1);
    char row = temp.charAt(0);

    if(row == 'a' || row == 'b' || row == 'c'){
     if(col == '1' || col == '2' || col == '3'){
      if(Check_Position(row, col, player, test_grid) == false){return 0;}
       return 1;
     }
    }
    System.out.println("\nMove instruction is invalid... Please try again\n");

    return 0;
  }

  private boolean Check_Position(char row, char col, char player, char[][] test_grid)
  {
    int current_col = Character.getNumericValue(col-1);// Minus 1 because an array starts at 0
    if(row == 'a') { current_row = 0;}
    if(row == 'b') { current_row = 1;}
    if(row == 'c') { current_row = 2;}

    if(test_grid[current_row][current_col] == '.'){
      grid[current_row][current_col] = player;// Make the move on the real game board
      return true;
    }
    System.out.println("\nPosition occupied, please try again...\n");

    return false;
   }

  public boolean Winner_Or_Draw(char player, char[][] grid)
  {
    if(Row_Win(player,grid) || Column_Win(player,grid) || Diagonal_Win(player,grid)){
       return true;
    }
    else if(Game_Draw(grid)){return true;}

    return false;
  }

  private boolean Game_Draw(char[][] grid)// All positions have been filled, and no winner found
  {
    for(int i = 0; i<3; i++){
      for(int k = 0; k<3; k++){
        if(grid[i][k] == '.'){return false;}
      }
    }

    return true;
  }

  private boolean Diagonal_Win(char player, char[][] grid)
  {
    int centre_col = 1;
    int centre_row = 1;

    if(grid[centre_row][centre_col] == player){
      if(grid[centre_row-1][centre_col-1] == player){
        if(grid[centre_row+1][centre_col+1] == player){
          return true;
        }
      }
      if(grid[centre_row+1][centre_col-1] == player){
        if(grid[centre_row-1][centre_col+1] == player){
          return true;
        }
      }
    }

    return false;
  }

  private boolean Row_Win(char player, char[][] grid)
  {
    int check = 0;
    int centre_col = 1;
    int row = 0;

    while(check < grid_size){
      if(grid[row][centre_col] == player){
        if(grid[row][centre_col-1] == player){
          if(grid[row][centre_col+1] == player){
            return true;
          }
        }
      }
      check++;
      row++;
    }

    return false;
  }

  private boolean Column_Win(char player, char[][] grid)
  {
    int check = 0;
    int centre_row = 1;
    int col = 0;

    while(check < grid_size){
      if(grid[centre_row][col] == player){
        if(grid[centre_row-1][col] == player){
          if(grid[centre_row+1][col] == player){
           return true;
          }
         }
       }
     check++;
     col++;
    }

    return false;
   }

   public char[][] Get_Array()
   {
    return this.grid;
   }

   void run()
   {
     Test_Switch_Player();
     Test_Move_Check();
     Test_Check_Position();
     Test_Game_Draw();
     Test_Winner_Or_Draw();
     Test_Diagonal_Win();
     Test_Column_Win();
     System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");// This provides clarity as there are print statements that appear during testing
     System.out.println("All tests passed");
   }

   void Test_Switch_Player()
   {
     int current_player = 2;

     assert(Switch_Player() == 'O');
     current_player = 1;
     assert(Switch_Player() == 'X');
   }

   void Test_Move_Check()
   {
     char[][] test_grid = {{'.','.','.'},{'.','X','.'},{'.','.','.'}};
     assert(Move_Check("a1",'X',test_grid) == 1);
     assert(Move_Check("b3", 'O', test_grid) == 1);
     assert((Move_Check("b2sdsdssadsa", 'X', test_grid)) == 0);
     assert(Move_Check("C2", 'O', test_grid) == 0);
     assert(Move_Check("7", 'X', test_grid) == 0);
     assert(Move_Check("b2", 'O', test_grid) == 0);
     assert(Move_Check("?", 'O', test_grid) == 0);
   }

   void Test_Check_Position()
   {
     char[][] test_grid = {{'.','.','.'},{'.','X','.'},{'.','.','.'}};
     assert(Check_Position('a','2','X',test_grid) == true);
     assert(Check_Position('a','2','O',grid) == false);
     assert(Check_Position('c','1','X',test_grid) == true);
     assert(Check_Position('c','1','X',grid) == false);
   }

   void Test_Game_Draw()
   {
     char[][] test_grid = {{'.','.','.'},{'.','X','.'},{'.','.','.'}};
     char[][] second_grid = {{'O','X','X'},{'O','X','O'},{'X','.','O'}};
     char[][] third_grid = {{'O','X','X'},{'O','X','O'},{'X','X','O'}};
     assert(Game_Draw(test_grid) == false);
     assert(Game_Draw(second_grid) == false);
     assert(Game_Draw(third_grid) == true);
   }

   void Test_Winner_Or_Draw()
   {
     char[][] test_grid = {{'.','.','.'},{'.','X','.'},{'.','.','.'}};
     char[][] row_win = {{'O','O','.'},{'X','X','X'},{'.','O','X'}};
     char[][] col_win = {{'O','O','.'},{'O','X','X'},{'O','.','X'}};

     assert(Winner_Or_Draw('X', test_grid) == false);
     assert(Winner_Or_Draw('X', row_win) == true);
     assert(Winner_Or_Draw('O', col_win) == true);
     assert(Winner_Or_Draw('X', col_win) == false);
   }

   void Test_Diagonal_Win()
   {
     char[][] Noughts_win = {{'.','.','O'},{'.','O','X'},{'O','.','.'}};
     char[][] Crosses_Win = {{'X','.','O'},{'.','X','0'},{'O','.','X'}};
     char[][] No_Win = {{'.','.','O'},{'.','O','X'},{'O','.','.'}};

     assert(Diagonal_Win('X', No_Win) == false);
     assert(Diagonal_Win('O', Noughts_win) == true);
     assert(Diagonal_Win('X', Crosses_Win) == true);
   }

   void Test_Column_Win()
   {
     char[][] Noughts_win = {{'O','.','.'},{'O','X','.'},{'O','X','.'}};
     char[][] Crosses_Win = {{'.','X','.'},{'.','X','.'},{'.','X','O'}};
     char[][] No_Win = {{'.','O','X'},{'.','X','.'},{'.','.','X'}};
     char[][] Diagonal_Win = {{'X','.','O'},{'.','X','0'},{'O','.','X'}};

     assert(Column_Win('O', Noughts_win) == true);
     assert(Column_Win('X', Crosses_Win) == true);
     assert(Column_Win('X', Noughts_win) == false);
     assert(Column_Win('O', No_Win) == false);
     assert(Column_Win('X', No_Win) == false);
     assert(Column_Win('O', Crosses_Win) == false);
     assert(Column_Win('X', Diagonal_Win) == false);
   }

   void Test_Row_Win()
   {
     char[][] Noughts_win = {{'O','O','O'},{'.','X','.'},{'X','O','.'}};
     char[][] Crosses_Win = {{'.','.','.'},{'.','X','.'},{'X','X','X'}};
     char[][] No_Win = {{'.','.','.'},{'.','X','.'},{'.','.','.'}};
     char[][] Column_Win = {{'O','.','.'},{'O','X','.'},{'O','.','.'}};

     assert(Row_Win('O', Noughts_win) == true);
     assert(Row_Win('X', Crosses_Win) == true);
     assert(Row_Win('O', No_Win) == false);
     assert(Row_Win('X', No_Win) == false);
     assert(Row_Win('O', Column_Win) == false);
   }

}
