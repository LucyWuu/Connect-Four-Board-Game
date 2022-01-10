/** This is the Interface class that construct the Connect Four game
* using the Board and Piece class methods.
* @author Lucy Wu
* @since October 23, 2020
* @version 1
*/

import java.util.*;

public class Interface {
   
   private Scanner sc = new Scanner (System.in);
   private Board board = new Board();
   private Piece piece1 = new Piece(1);
   private Piece piece2 = new Piece(2);
   private String fileName;
   private final Piece[] player = {piece1, piece2};
   private final int NUMCOL = 7;
   private int option, col, bottomRow, rate;
   private boolean end = false;
   private int playerNumber = 1;
   
   /** Display the game menu
   */
   public static void menu() {
      System.out.println("Welcome to the game of Connect Four!");
      System.out.println("1. Start/Continue Game");
      System.out.println("2. Game Rules");
      System.out.println("3. Reset Game Board");
      System.out.println("4. Save Your Game");
      System.out.println("5. Load Your Game");
      System.out.println("6. Rate This Game");
      System.out.println("7. Quit");
   }
   
   /** Menu option 1: Start/continue the game
   */
   public void play() {
      board.displayBoard();
      System.out.println();
      System.out.print("Player " + playerNumber + ", enter a column number to drop your piece, or enter -1 to return to the menu for more options: ");
      col = sc.nextInt();
      while (col != -1) {
                  
         //error check
         if (col < 0 || col >= NUMCOL) {
            System.out.println("\nInvalid column number. It must be between 0 and 6 inclusively. ");
            System.out.print("Player " + playerNumber + ", please enter another column number to drop your piece, or enter -1 to return to the menu for more options:  ");
            col = sc.nextInt();
         } else {
                  
            //locate and store data of the slot the piece would drop to
            //prompt the player for another column if the selected one is full
            bottomRow = board.findEmptySlot(col);
            if (bottomRow == -1) {
               System.out.println();
               System.out.print("Sorry, column " + col + " is full. Player " + playerNumber + ", please enter another column number to drop your piece, or enter -1 to return to the menu for more options: ");
               col = sc.nextInt();
            } else {
               player[playerNumber-1].setRow(bottomRow);
               player[playerNumber-1].setCol(col);
               board.setPlayer(player[playerNumber-1]);
                        
               //check for win and tie, then proceed accordingly
               if (board.winCheck(player[playerNumber-1])) {
                  board.displayBoard();
                  System.out.println("\nPlayer " + playerNumber + " wins! Now leading you back to the menu...");
                  col = -1;
                  board.resetBoard();
               } else if (board.checkFull()) {
                  System.out.println("\nTie game! Now leading you back to the menu...");
                  col = -1;
                  board.resetBoard();
               } else {
                  if (playerNumber == 1) {
                     playerNumber = 2;
                  } else {
                     playerNumber = 1;
                  }
                  System.out.println();
                  board.displayBoard();
                  System.out.println();
                  System.out.print("Player " + playerNumber + ", enter a column number to drop your piece, or enter -1 to return to the menu for more options: ");
                  col = sc.nextInt();
               }
            }
         }
      } 
      System.out.println();
   }
   
   /** Menu option 2: Display game rules
   */
   public static void displayRule(){
      System.out.println("Connect Four is a two player board game. Player 1 and player 2 take turns to drop");
      System.out.println("their own pieces into a seven-column, six-row vertically-suspended grid.");
      System.out.println("The objective each player is to connect four of their own pieces in a row");
      System.out.println("¨C vertically, horizontally, or diagonally before the opponent do.\n");
      System.out.println("Now leading you back to the menu...\n");
   }
   
   /** Menu option 3: Reset gameboard
   */
   public void reset(){
      board.resetBoard();
      playerNumber = 1;
      System.out.println("The gameboard has been reset. Now leading you back to the menu...");
      System.out.println();
   }
   
   /** Menu option 4: Save game
   */
   public void save(){
      System.out.print("Enter a file name: ");
      sc.nextLine();
      fileName = sc.nextLine();
      board.saveFile(fileName,playerNumber);
      System.out.println();
   }
   
   /** Menu option 5: Load game
   */
   public void load(){
      System.out.print("Enter the file name: ");
      sc.nextLine();
      fileName = sc.nextLine();
      playerNumber = board.loadFile(fileName);
      System.out.println();
   }
   
   /** Menu option 6: Rate game
   */
   public void rate(){
      System.out.println("From one to five, how many stars would you give to this game?");
      sc.nextLine();
      rate = sc.nextInt();
      if (rate >= 3 && rate <= 5) {
         System.out.println("Thank you for your support! I will aim to make better games in the future!\n");
         System.out.println("Now leading you back to the main menu...\n");
      } else if (rate >= 1 && rate <= 5){
         System.out.println("How do you think this game can improve?");
         sc.nextLine();
         sc.nextLine();
         System.out.println("Thank you for your feedback!\n");
         System.out.println("Now leading you back to the main menu...\n");
      } else {
         System.out.println("Invalid input, now leading you back to the main menu...\n");
      }
   }
   
   /** Provides an interface for the players
   */
   public void start (){
   
      //repeat the game until the player stops
      while (!end) {
         
         //display menu, prompt the players for option and proceed accordingly.
         menu();
         System.out.print("Enter the number of your choice: ");
         option = sc.nextInt();
         System.out.println();
         switch (option) {
            case 1: 
               play();
               break;
            case 2:
               displayRule();
               break;
            case 3: 
               reset();
               break;
            case 4:
               save();
               break;
            case 5:
               load();
               break;
            case 6:
               rate();
               break;
            case 7:
               System.out.print("Thank you for playing my game, have a nice day! :)");
               end = true;
               break;
            default:
               System.out.println("Invalid option! Try again!");
               System.out.println();
         }
      }
   }
}