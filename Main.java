import java.util.*;
import java.io.*; 
import javax.sound.sampled.*;
import java.awt.*;
import javax.swing.*;

public class Main { 

  public static void type(String t) {
    String text = t;
    int i;
    for(i = 0; i < text.length(); i++){
        System.out.printf("%c", text.charAt(i));
        try {
          Thread.sleep(25);
        } catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
  }

  //Source: Monster Invasion Project 
  public static void playMusic() {
    try {
        File audioFile = new File ("investigate-8084.wav");
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
        stream = AudioSystem.getAudioInputStream(audioFile);
        format = stream.getFormat();
        info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (Exception e) {
        System.out.println("Error playing audio");
    }
  }
  
  public static void main(String[] args) {

    //Making Doors
    
    Door one = new Door(1, "It looks like the letters of the first\nword in the note are out of order.\nCan you unscramble the word TOGHINT?", "tonight", "later today", "this day --> today, this night --> ?", 2.5);

    Door two = new Door(2, "If c represents a and d represents b,\ndecipher the first two words on the second line: \nK mpqy", "i know", "K = I", "m = k, p = n", 4.0);

    Door three = new Door(3, "97 is the decimal equivalent of the letter \na on the ASCII conversion table. Can you figure out\nthe missing letters?: 069 s 099 a 112 e\nType in the full word.", "escape", "069 is E.","break out", 5.6);

    String doorFourAppearance = "\n _ _ _ _ _ _\n| | | | | | |\n|7|5|3|4|6|8|\n|_|_|_|_|_|_|\n _ _ _ _ _ _\n| | | | | | |\n|8|3|4|2|7|?|\n|_|_|_|_|_|_|\n";

    Door four = new Door(4, "You found this note in her brother's safe: \nOrange, Violet, Yellow, Blue, Red, Indigo \nWhat is the missing color?", "green", "Rainbow", "Red, Orange, Yellow, _____", 3.0);

    Door five = new Door(5,"You receive a text message from the number " + Integer.MAX_VALUE + " saying\nthey know you are trying to find them. The message reads \"Solve the \nproblem to find the first four letters of the street I'm in.\"\nFind a number with its letters in alphabetical order:\nEx: \"five\" has \"fiv\" in alphabetical order, but not \"e\".", "forty", "complete this word: f → o → r ", "complete this word: f → o → r → t → __ ", 7.2);

    Door six = new Door(6, "You receive another text message:\nSolve this final problem to know my building number. Good Luck!\n\"I was born on December 31st and spoke on January 1st. The day\nbefore yesterday I was 26, how old will I turn next year?\"", "29", "The day before Yesterday (Dec 30), he was 26. On Dec 31st, he turned 27.\nThis year he will turn 28. How old will he turn next year?", "Next January, he will be 28. How old will he turn that year in December?", 2);

    //Doors Completed
    Door [] all = {one, two, three, four, five, six};

    //Scanner for initial user input
     System.out.println("    __           _     __    __ \n" +
                        "   / /  ___  ___| |_  / / /\\ \\ \\__ _ _   _ ___ \n" +
                        "  / /  / _ \\/ __| __| \\ \\/  \\/ / _` | | | / __|\n" +
                        " / /__| (_) \\__ \\ |_   \\  /\\  / (_| | |_| \\__ \\\n" +
                        " \\____/\\___/|___/\\__|   \\/  \\/ \\__,_|\\__, |___/\n" +
                        "                                     |___/     \n");
    Scanner scan = new Scanner(System.in);
    System.out.print("\nWhat is your name: ");
    String playerName = scan.nextLine();
    System.out.print("How old are you: ");
    int playerAge = scan.nextInt();
    //End of Scanners

    if (playerAge < 8) {
      type("\nSorry! You are too young to play this game.");
      System.exit(1);
    } else {
      type("\nWelcome to Lost Ways. We are excited you have joined us, " + playerName + "!");
    }

    type("\nPress Enter To Continue: ");
    try {
      System.in.read();
    } catch(Exception e){}

    playMusic();

    //Role - Create Player Object
    type(ConsoleColors.YELLOW + "\n\"My name is Emily. Five years ago, my older brother was kidnapped. \nLast week, he was presumed dead, but my family has had a hard time \nbelieving it. I don't think our lives will ever be the same. However, \nwe received a strange note in the mail this morning. The postmark \nwas yesterday. My parents are frightened, but I know there is hope \nthat we will find my brother again. Please help us!\"\n" + ConsoleColors.RESET);
    Scanner scanTwo = new Scanner(System.in);
    type(ConsoleColors.RESET + "\nSelect a role:" + ConsoleColors.RESET + " (d)etective, (p)olice, (r)andom : ");
    String identity = scanTwo.nextLine();
    identity = identity.toLowerCase();
  
    if (identity.equals("r")){
      identity = Player.randomIdentity();
    }
    if (identity.equals("d")||identity.equals("p")){
      Player newPlayer = new Player (playerName, 0);
      if (identity.equals("d")) {
        newPlayer.addInfo ("detective", 1);
      }
      else {
        newPlayer.addInfo ("police", 2);
      }
      for (int a = 0; a < all.length; a ++){
        all[a].setEqual(newPlayer);
      }
      type(ConsoleColors.WHITE_BRIGHT + "\nThere are six doors in this game. Every door will have a puzzle \nbringing us one step closer to our goal. If you know the answer, \nyou can input it or type \"hint\" for a hint. You only have one try \nto open each door and " + newPlayer.getHints() + " hint(s) per door. \nKeep an eye for additional clues! " + ConsoleColors.RESET);
    }
    else {
      Player newPlayer = null;
      one.setEqual(newPlayer);
      System.out.println("You didn't enter an accurate identity. Please retry!");
      System.exit(1);
    }

    type("Press Enter To Continue: ");
    try {
      System.in.read();
    } catch(Exception e){}

    //Envelope - Finding Message
    System.out.println(ConsoleColors.RED +
                    "\n                   ..." +
                    "\n                 /`   `\\" +
                    "\n                /       \\"+
                    "\n _________     |\\~~~~~~~/|"+
                    "\n|~~      @|    | \\=====/ |" +
                    "\n|  ====   |    | /`...'\\ |" +
                    "\n|_________|    |/_______\\|" + ConsoleColors.RESET);

      type("\n\nYou open the letter and see...\n"+ConsoleColors.YELLOW + "\nTOGHINT the wind blows even colder still. \nK mpqy it is darkness, never light.\nWill break even the strongest heart. \n069 s 099 a 112 e my grasp on my dreams." + ConsoleColors.RESET);
    
    type("\n\nAre you ready to find Emily's brother? Press Enter To Continue: ");
      try {
        System.in.read();
      } catch(Exception e){}

    //Part one
    for (int i = 0; i < 3; i ++){
      type("\n"+ConsoleColors.YELLOW + "Door " + (i + 1) + ": " + all [i].getQuestion() + ConsoleColors.RESET);
      type(all [i].getAppearance() + "\n");
      all [i].checkAnswer();
      System.out.println();
    }

    type("\n\nBefore you move on to the next door .... Ding Dong! \nSomeone rang the bell. You rush to the door, \nbut a message slides in from under the door.\n");
    System.out.println(ConsoleColors.PURPLE_BOLD + 
          "\n  ___________________" +
          "\n||                   ||" +
          "\n|| Look At The First ||" +
          "\n|| Word of Each Line ||" +
          "\n||___________________||" + ConsoleColors.RESET);

    type("\n\nFind the secret message in the note. You have two chances. \nThis will determine if you are capable of finding Emily's brother. \nUse the solutions of the previous answers: \n");
    String answerDoor = scan.nextLine();
    answerDoor = scan.nextLine().toLowerCase();
    
    if (answerDoor.equals("tonight i will escape")) {
      type("\nYou found the message. Since the postmark is yesterday, \nEmily's brother escaped last night. Now we need to find \nwhere he's hiding. The next doors will reveal the answer, \nlook closely for an address!\n");
    } else {
        type("\nYour answer was incorrect. Try again!: ");
        String answerDoorTwo = scan.nextLine().toLowerCase();
        if (answerDoorTwo.equals("tonight i will escape")) {
          type("\nYou found the message. Since the postmark is yesterday, \nEmily's brother escaped last night. Now we need to find \nwhere he's hiding. The next doors will reveal the answer, \nlook closely for an address!\n");
        } else {
            type("\nYou are incorrect. Emily doesn't trust you to find her brother.");
            System.exit(1);
        }
    }

    //Part two
    for (int c = 3; c < all.length; c ++){
      type("\n"+ConsoleColors.YELLOW + "Door " + (c + 1) + ": " + all [c].getQuestion() + ConsoleColors.RESET);
      type(all [c].getAppearance() + "\n");
      all [c].checkAnswer();
      System.out.println();

      if (c == 3) {
        type("\n" + ConsoleColors.PURPLE + 
          "\n __________" +
          "\n|          |" +
          "\n| ~~~      |"+
          "\n| Message  |" +
          "\n| ~~~      |" +
          "\n|__________|"+
          "\n| __(__)__ |" + ConsoleColors.RESET);
        System.out.println();
      }
    }

    type(ConsoleColors.BLUE + "\n\n. . .\n. . .\nInteresting. The address should be in these clues. \nWhere is Emily's brother?\n> ");
    String finalLocation = scan.nextLine();
    finalLocation = finalLocation.toLowerCase();
    type(ConsoleColors.RESET);
    if (finalLocation.equals("29 fort greene place") || finalLocation.equals("29 fort greene") || finalLocation.equals("brooklyn tech") || finalLocation.equals("brooklyn technical high school") || finalLocation.equals("bths")) {
      type("\n. . .\n. . .\nThank you for helping us find Emily's brother! \nThe police found him, and he is safe at home now. \nEmily is so glad to have However brother back.");
    }
    else {
      type(ConsoleColors.BLUE +"\n. . .\n. . .\nEmily was able to text the unknown number again. Don't worry, \nthey're using a secret language that her brother and she used as kids. \nHe mentioned something about a school. I wonder what he means...\nTake another guess at his location: " + ConsoleColors.RESET);
      finalLocation = scan.nextLine().toLowerCase();
      if (finalLocation.equals("29 fort greene place") || finalLocation.equals("29 fort greene") || finalLocation.equals("brooklyn tech") || finalLocation.equals("brooklyn technical high school") || finalLocation.equals("bths")) {
            type("\n. . .\n. . .\nThank you for helping us find Emily's brother! He is safe at home now.");
      }
      else {
        type("\n. . .\n. . .\nUnfortunately, we did not find Emily's brother at that address. However, the \ntext messages we received were enough evidence to suspect he is still alive. They're reopening the case, \nand now it's all in their hands. Thank you for your help, we're getting closer to him.");
      }
    }
    
    type("\n\nHere's a summary of your game: ");
    for (int b = 0; b < all.length; b ++){
      type("\n" + all [b]);
    }

    System.out.println("\n\nSCAN FOR A SPECIAL MESSAGE :)\n\n▄▄▄▄▄▄▄   ▄ ▄ ▄   ▄▄▄  ▄  ▄▄▄▄▄▄▄\n█ ▄▄▄ █ ▀ ▀ ▄▀██   ▄███▄  █ ▄▄▄ █\n█ ███ █ ▀█▀ ▀▀▄ ▀▀▀▄▀  █▀ █ ███ █\n█▄▄▄▄▄█ █▀▄▀█▀▄▀▄▀█ ▄ █ ▄ █▄▄▄▄▄█\n▄▄▄▄▄ ▄▄▄█▀█ ▄▀█▄▄ ▀▀▀█▀█▄ ▄ ▄ ▄ \n▄ ▄ ██▄ ▄ █ ▀▀▀▄▄▄▀▀▀▄▄▀▄▄▀  ▄▀█▀\n▄██ ▄ ▄▀ ▄▄ █▄▄▀▄▄█▀ ▀██▄▀ ▄█▄▀  \n▀██  ▀▄▄█▀▄▄▄▄ ▄▄ ▄▀▀█▄▀█▄█    █▀\n█▀▀ ▄▀▄█ ▄██▄▄ ▄ ▄█▀▄▀█▄▄█ ▄▄ ▀▄ \n█ █▀█▀▄▀▄▀█ ▀ ▀▄▀▄▄▀ ▄▄▀  ▀  █ █▀\n▀ ▀▀ ▀▄██▄▀▀▄▄▀█▄▄▀ █▀█ ██ ▄█ ▀▄\n█▀▀▀█ ▄▄ ▄▄▀▄▀▀▄▄▄█▀ █▄▀▄▀▀▄▄█ █▀\n█ ▀▀ ▄▄▄ ▀▄ ▀▄▀█▄▄▀ █▀██▄▄█▄██▀ ▄\n▄▄▄▄▄▄▄ █▀ ▀▄▀▀▄▄ ▀▀▄▀▀▄█ ▄ ██▀▄▀\n█ ▄▄▄ █ ▄ ▀██▄ █▄▄█ ▀▀▀▀█▄▄▄██▀█▀\n█ ███ █ █ ▀▀▄ █▄▄ ▄▀▀ ▄███▄█▀█▀▄ \n█▄▄▄▄▄█ █▄▄█▄▄▀▄█▄█▀▄▀▀▄▄▄▄ ▀█▀▄");

    try {
      System.out.println("\n");
      Thread.sleep(300);
    } catch(InterruptedException ex){
      Thread.currentThread().interrupt();
    }

    System.exit(1);

  }
}  