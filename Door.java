import java.util.Scanner; 

public class Door {

  private int name;
  private String appearance;
  private boolean isOpen;
  private final String question;
  private final String answer;
  private String hintOne;
  private String hintTwo;
  private double point;
  private Scanner a = new Scanner(System.in);
  private String userAnswer;
  private Player p;
  private int playerHint;
  private int score;

  //Constructors 

  public Door(int n, String q, String s,String ho, String ht,double p) {
    name = n;
    appearance = ConsoleColors.BLUE_BOLD +
                "\n ________"+
                "\n|  ____  |" +
                "\n| | " + n + ". | |" +
                "\n| |____| |" +
                "\n|  _  _ +|" +
                "\n| |_||_| |" +
                "\n|________|" +
                ConsoleColors.RESET;

    isOpen = false;
    question = q;
    answer = s;
    hintOne = ho;
    hintTwo = ht;
    point = p;
  }

  public Door(String q, String n, String h) {
    appearance = ConsoleColors.BLUE_BOLD +
                "\n ________"+
                "\n|  ____  |" +
                "\n| | " + n + ". | |" +
                "\n| |____| |" +
                "\n|  _  _ +|" +
                "\n| |_||_| |" +
                "\n|________|" +
                ConsoleColors.RESET;
    isOpen = false;
    question = q;
    answer = n;
    hintOne = h;
  }

  public Door(String q, String a) {
    question = q;
    answer = a;
  }

  //Accessors
  public int getName() {
    return name; }
  public String getAppearance() {
    return appearance; }
  public boolean getIsOpen() {
    return isOpen; }
  public String getQuestion() {
    return question; }
  public String getAnswer() {
    return answer; }
  public String getHintOne() {
    return hintOne; }
  public String getHintTwo() {
    return hintTwo; }
  public double getPoint() {
    return point; }  
  
  public static void type(String t) {
    String text = t;
    int i;
    for(i = 0; i < text.length(); i++){
        System.out.printf("%c", text.charAt(i));
        try{
          Thread.sleep(30);
        }catch(InterruptedException ex){
          Thread.currentThread().interrupt();
        }
    }
  }

  public void setEqual(Player one) {
    this.p = one;
    playerHint = one.getHints();
  }

  public double playerPoints() {
    return p.getScore();
  }

  public void incorrectDoor() {
    appearance = ConsoleColors.RED +
                "\n ________"+
                "\n|  ____  |" +
                "\n| | \\/ | |" +
                "\n| |_/\\_| |" +
                "\n|  _  _ +|" +
                "\n| |_||_| |" +
                "\n|________|" +
                ConsoleColors.RESET;
    type(appearance);
  }

  public int playerAddPoints() {
    score += point;
    return score;
  }

  public String correctDoor(String mess) {
    isOpen = true;
    appearance = ConsoleColors.GREEN +
                "\n  /|" + mess +
                "\n /_|_____" +
                "\n|  ____  |" +
                "\n| | " + name + ". | |" +
                "\n| |____| |" +
                "\n|  _  _ +|" +
                "\n| |_||_| |" +
                "\n|________|" +
                ConsoleColors.RESET;
    return appearance;
  }

  public String correctDoor() {
    isOpen = true;
    appearance = ConsoleColors.GREEN +
                "\n  /|" +
                "\n /_|_____" +
                "\n|  ____  |" +
                "\n| | " + name + ". | |" +
                "\n| |____| |" +
                "\n|  _  _ +|" +
                "\n| |_||_| |" +
                "\n|________|" +
                ConsoleColors.RESET;


    return appearance;
  }

  public void scanner(String s){
    type(s);
    userAnswer = (a.nextLine()).toLowerCase();
  }

  public void checkAnswer () {
    scanner("\nEnter the answer or ask for a hint: ");
    if (userAnswer.equals(this.answer)){
      int random = (int)(Math.random()*2)+1;
      if (random==1){
        type(correctDoor());
        type("\n");
      }
      else {
        type(correctDoor("you got it!"));
        type("\n");
      }
    }
    else if (userAnswer.equals("hint")){
      System.out.println("Hint: "+ hintOne);
      if (playerHint == 2) {
        scanner("Would you like another hint: ");
        if (userAnswer.equals("yes")) {
          type("Hint: " + hintTwo);
        }
      }
      this.scanner("\nEnter your new answer: ");

      if (userAnswer.equals(this.answer)){
        int random = (int) (Math.random()*2)+1;

        if (random==1){
          type(this.correctDoor());
          if (this.name != 8) {
            type("\n");
          }
        }
        else {
        type(this.correctDoor("you got it!"));
        if (this.name != 8) { 
          type("\n");
        }
        }
      }
      else {
        this.incorrectDoor();
        if (this.name != 8) {
          type("\nTry to get the next door correct, otherwise, \nthe final message will be hard to find.\n");
        }
      }
    } else {
      this.incorrectDoor();
      if (this.name != 8) {
        type("\nTry to get the next door correct, otherwise the final message will be hard to find.\n");
      }
    }
  }

  public void checkLastAnswer () {
    this.scanner("\nEnter the answer or ask for a hint: ");

    if (userAnswer.equals(this.answer)){
      this.playerAddPoints();
      int random = (int)(Math.random()*2)+1;
      if (random==1){
        type(correctDoor());
      }
      else {
        type(correctDoor("you got it!"));
      }
    }
    else if (userAnswer.equals("hint")){
      System.out.println("Hint: "+ hintOne);
      if (p.getHints() == 2){
        this.scanner("Would you like another hint: ");
        if (userAnswer.equals("yes")) {
          type("Hint: " + hintTwo);
        }
      }

      this.scanner("\nEnter your new answer: ");

      if (userAnswer.equals(this.answer)){
        this.playerAddPoints();
        int random = (int) (Math.random()*2)+1;

        if (random==1){
          type(this.correctDoor());
        }
        else {
        type(this.correctDoor("you got it!"));
        }
      }
      else {
        this.incorrectDoor();
      }
    } else {
      this.incorrectDoor();
    }
  }

  public String toString(){
    String status = "";
    if (isOpen)
    {
      status = "✓";
    }
    else
    {
      status = "X";
    }
    return "Door " + name + ": " + status;
  }

}