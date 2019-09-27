
// Returns the Character symbols for each player  
class Player_Type{

  public char Person_Switch(int player)
  {
    switch(player){
      case 1:
      return 'X';
      case 2:
      return 'O';
      default:
      throw new Error("Incorrect player number used...");
    }
  }

  public char Next_Player(char player)
  {
    if(player == 'X' || player == ' '){return 'O';}
    else{return 'X';}
  }

}
