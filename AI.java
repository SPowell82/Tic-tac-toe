
//Controls all AI movements
class AI{

 public static void main(String[] args)
 {
   AI program = new AI();
   program.run();
 }

 public boolean Winner_Found()//Checks if the player or AI has won
 {
   Board object = new Board();

   if(object.Board_Check('O')){
    System.out.println("\nRematch!!!");
    return true;
   }
   if(object.Board_Check('X')){
    System.out.println("\n\nThe AI Wins!!...");
    return true;
   }

   return false;
  }

  public void AI_Move(char[][] grid, char AI)
  {
    char opponent = 'O';

    if(Winning_Move(grid, AI) == 1){return;}
    if(Opponent_Win(grid, AI) == 1){return;}
    if(Two_way_Win(grid, AI, AI) == 1){return;}
    if(Opponent_TWW(grid, AI, opponent) == 1){return;}//Can the opponent make a two way win
    Check_Corners(grid, AI);
    return;
  }

  private int Opponent_TWW(char[][] grid, char AI, char opponent)
  {
    if(Two_way_Win(grid, AI, opponent) == 1){return 1;}
    return 0;
  }

  private int Two_way_Win(char[][] grid, char AI, char player)
  {
    char[][] temp_grid = grid.clone();

    for(int i = 0; i<3; i++){
      for(int k = 0; k<3; k++){
       if(temp_grid[i][k] == '.'){
        temp_grid[i][k] = player;
         if(Two_Wins_Possible(temp_grid, player)){
          Apply_Move(i,k,AI);
           return 1;
         }
         else{temp_grid[i][k] = '.';}
       }
      }
     }

    return 0;
  }

  private boolean Two_Wins_Possible(char[][] grid, char player)
  {
    int possible_wins = 0;
    Board object = new Board();

    for(int i = 0; i<3; i++){
      for(int k = 0; k<3; k++){
        if(grid[i][k] == '.'){
          grid[i][k] = player;
          if(object.Winner_Or_Draw(player,grid) == true){
            possible_wins++;
          }
          grid[i][k] = '.';
        }
      }
    }

    if(possible_wins >= 2){return true;}

   return false;
  }

  private int Winning_Move(char[][] grid, char AI)
  {
    char[][] temp_grid = grid.clone();
    Board object = new Board();

    for(int i=0; i<3; i++){
      for(int k=0; k<3; k++){
        if(temp_grid[i][k] == '.'){
         temp_grid[i][k] = AI;
          if(object.Winner_Or_Draw(AI,temp_grid) == true){
           Apply_Move(i,k,AI);
            return 1;
          }
          else{temp_grid[i][k] = '.';}
          }
       }
    }

  return 0;
  }

  private int Opponent_Win(char[][] grid, char AI)
  {
    char[][] temp_grid = grid;
    Board object = new Board();
    char opponent = 'O';

    for(int i = 0; i<3; i++){
      for(int k = 0; k<3; k++){
        if(temp_grid[i][k] == '.'){
         temp_grid[i][k] = opponent;
          if(object.Winner_Or_Draw(opponent,temp_grid) == true){
           Apply_Move(i,k,AI);
            return 1;
          }
          else{temp_grid[i][k] = '.';}
        }
       }
     }

    return 0;
    }


  private void Check_Corners(char grid[][], char AI)//Selects the opposite corner of one that is chosen
  {
    if(grid[0][0] == 'O' && grid[2][2] == '.'){
     grid[2][2] = AI;
     return;
    }
    if(grid[0][2] == '0' && grid[2][0] == '.'){
     grid[2][0] = AI;
     return;
    }
    if(grid[2][0] == 'O' && grid[0][2] == '.'){
     grid[0][2] = AI;
     return;
    }
    if(grid[2][2] == 'O' && grid[0][0] == '.'){
     grid[0][0] = AI;
     return;
    }
    else if(grid[0][0] == '.'){
     grid[0][0] = AI;
     return;
    }

    else{Random_Position(grid,AI);}
    return;
  }

  private void Random_Position(char[][] grid, char AI)
  {
    for(int i = 0; i<3; i++){
      for(int k = 0; k<3; k++){
        if(grid[i][k] == '.'){
          Apply_Move(i,k,AI);
          return;
        }
      }
    }

    return;
  }

  private void Apply_Move(int row, int col, char AI)
  {
    Board object = new Board();
    object.Apply_AI_Move(row,col,AI);
  }


  void run()
  {
    Test_Two_Way_Wins();//Also tests 'Opponent_TWW'
    Test_Winning_Move();
    Test_Opponent_Win();
    System.out.println("All tests passed");
  }

  void Test_Two_Way_Wins()
  {
    char[][] test_grid = {{'O','.','.'},{'.','.','.'},{'.','.','O'}};
    char[][] AI_TWW = {{'.','.','X'},{'.','.','.'},{'X','.','.'}};
    char[][] No_TWW = {{'.','X','.'},{'.','.','.'},{'O','.','.'}};

    assert(Two_way_Win(AI_TWW, 'X', 'X') == 1);
    assert(Two_way_Win(AI_TWW, 'X', 'O') == 0);
    assert(Two_way_Win(No_TWW, 'X', 'X') == 0);
    assert(Two_way_Win(test_grid, 'X', 'X') == 0);

    assert(Opponent_TWW(test_grid,'X','O') == 1);
    assert(Opponent_TWW(AI_TWW,'X','O') == 0);/*AI can make a two way win, not the opponent */
    assert(Opponent_TWW(No_TWW,'X','O') == 0);
    assert(Opponent_TWW(No_TWW,'X','O') == 0);
   }

  void Test_Winning_Move()
   {
     char[][] Win_Possible = {{'X','.','.'},{'X','.','.'},{'.','.','.'}};
     char[][] test_grid = {{'X','.','.'},{'.','X','.'},{'.','.','.'}};
     char[][] No_win = {{'O','.','.'},{'.','X','.'},{'.','.','.'}};

     assert(Winning_Move(Win_Possible,'X') == 1);
     assert(Winning_Move(Win_Possible,'O') == 0);
     assert(Winning_Move(test_grid,'X') == 1);
     assert(Winning_Move(No_win,'X') == 0);
   }

  void Test_Opponent_Win()
   {
     char[][] test_grid = {{'O','O','.'},{'.','.','.'},{'.','.','.'}};
     char[][] AI_win = {{'X','X','.'},{'.','.','.'},{'.','.','.'}};
     char[][] No_Win = {{'O','.','.'},{'.','.','.'},{'.','O','.'}};

     assert(Opponent_Win(test_grid, 'X') == 1);
     assert(Opponent_Win(AI_win, 'X') == 0);
     assert(Opponent_Win(No_Win, 'X') == 0);
   }


  }
