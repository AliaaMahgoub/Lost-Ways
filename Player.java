public class Player {

  private String name;
  private String character;
  private int score;
  private int hints;
  
  public Player (String e, int s) {
    name = e;
    score = s;
    hints = 0;
  }
  
  public void addInfo(String c, int h){
    character = c;
    hints = h;
  }

  public void addPoints(Door d) {
    score += d.playerAddPoints();
  }

  public String toString() {
    return "The player is " + name + ". " + name + " is the " + character + " helping to find my brother. Well Done! You're score is " + score;
  }

  public static String randomIdentity() {
    int random = (int) (Math.random()*2)+1;
    if (random != 1){
      return "p";
    }
    else {
      return "d";
    }
  }

  public int getHints() {
    return hints;
  }

  public int getScore() {
    return score;
  }
  
}