/** The Board class stores infomation about the Connect Four gameboard.
* It contains a constructor and methods that do calculations and provide information.
* @author Lucy Wu
* @since October 23, 2020
* @version 1
*/

import java.io.*;

public class Board {
   
   private final int EMPTY = 0;
   private final int NUMROW = 6;   
   private final int NUMCOL = 7;
   private final int WINCOUNT = 4;
   private int[][] board;  
   
   /** Constructor of the Board class, create the empty 6 by 7 game board
   */
   public Board () {
      this.board = new int [NUMROW][NUMCOL];
   }
   
   /** Reset the board content
   */
   public void resetBoard(){
      for (int i = 0; i < NUMROW; i++) {
         for (int j = 0; j < NUMCOL; j++) {
            this.board[i][j] = EMPTY;
         } 
      }
   }

   /** Output the board content
   */
   public void displayBoard(){
      for (int[] rowArray : this.board){
         for (int piece : rowArray){
             System.out.print(piece + " ");
         }
         System.out.println();
      }
      System.out.println("-------------");
      for (int i = 0; i < NUMCOL; i++){
         System.out.print(i + " ");
      }
      System.out.println("\n¡ü Column numbering");
   }
   
   /** Search for and return the bottom most empty slot of the a column
   *  @param col - the selected column
   *  @return an int indicating the row of the bottom most empty slot
   */
   public int findEmptySlot(int col){
      int emptyRow = -1;
      for (int i = 0; i < NUMROW; i++) {
         if (this.board[i][col] == EMPTY) {
            emptyRow = i;
         }
      }
      return emptyRow;
   }
   
   /** Check whether the board is full
   *  @return a boolean indicating the board is full or not
   */
   public boolean checkFull() {
      boolean full = true;
      for (int i = 0; i < NUMCOL; i++) {
         if (this.board[0][i] == EMPTY) {
            full = false;
         }
      }
      return full;
   }

   /** Count and return the number of vertically-connected pieces formed by the given piece
   *  @param piece - the piece player 1 or 2 places
   *  @return an int indicating the number of vertically-connected pieces formed by the given piece
   */
   public int verticalCount(Piece piece) {
      int row = piece.getRow();
      int col = piece.getCol();
      int count = 1;
      boolean connect = true;
      
      //check below the piece
      for (int i = row; i < NUMROW - 1; i++){
         if (this.board[i][col] != this.board[i+1][col]){
            connect = false;
         }
         if (connect) {
            count++;
         }
      }
      
      //check above the piece
      connect = true;
      for (int i = row; i > 0; i--){
         if (this.board[i][col] != this.board[i-1][col]){
            connect = false;
         }
         if (connect) {
            count++;
         }
      }
      return count;
   }

   /** Count and return the number of horizontally-connected pieces formed by the given piece
   *  @param piece - the piece player 1 or 2 places
   *  @return an int indicating the number of horizontally-connected pieces formed by the given piece
   */
   public int horizontalCount (Piece piece) {
      int row = piece.getRow();
      int col = piece.getCol();
      int count = 1;
      boolean connect = true;
      
      //check to the right of the piece
      for (int i = col; i < NUMCOL-1; i++){
         if (this.board[row][i] != this.board[row][i+1]) {
            connect = false;
         }
         if (connect) {
            count++;
         }
      }
      
      //check to the left of the piece
      connect = true;
      for (int i = col; i > 0; i--) {
         if (this.board[row][i] != this.board[row][i-1]) {
            connect = false;
         }
         if (connect) {
            count++; 
         }
      }
      return count;
   }
   
   /** Count and return the number of diagonally-connected pieces (top left to bottom right) formed by the given piece
   *  @param piece - the piece player 1 or 2 places
   *  @return an int indicating the number of diagonally-connected pieces (top left to bottom right) formed by the given piece
   */
   public int diagonalCount1 (Piece piece) {
      int row = piece.getRow();
      int col = piece.getCol();
      boolean connect = true;
      int count = 1;
      
      //check downward from the piece
      for (int i = row, j = col; i < NUMROW-1 && j < NUMCOL-1; i++, j++) {
         if (this.board[i][j] != this.board[i+1][j+1]){
            connect = false;
         }
         if (connect) {
            count++;
         }
      }
      
      //check upward from the piece
      connect = true;
      for (int i = row, j = col; i > 0 && j > 0; i--, j--) {
         if (this.board[i][j] != this.board[i-1][j-1]) {
            connect = false;
         }
         if (connect) {
            count++;
         }
      }
      return count;
   }

   /** Count and return the number of diagonally-connected pieces (top right to bottom left) formed by the given piece
   *  @param piece - the piece player 1 or 2 places
   *  @return an int indicating the number of diagonally-connected pieces (top right to bottom left) formed by the given piece
   */
   public int diagonalCount2 (Piece piece) {
      int row = piece.getRow();
      int col = piece.getCol();
      boolean connect = true;
      int count = 1;
      
      //check downward from the piece
      for (int i = row, j = col; i < NUMROW-1 && j > 0; i++, j--) {
         if (this.board[i][j] != this.board[i+1][j-1]){
            connect = false;
         }
         if (connect) {
            count++;
         }
      }
      
      //check upward from the piece
      connect = true;
      for (int i = row, j = col; i > 0 && j < NUMCOL-1; i--, j++) {
         if (this.board[i][j] != this.board[i-1][j+1]) {
            connect = false;
         }
         if (connect) {
            count++;
         }
      }
      return count;
   }
   
   /** Check if one player has won the game by connecting 4 pieces
   * @param piece - the current piece player 1 or 2 inputs
   * @return a boolean indicating whether the current player has won or not
   */
   public boolean winCheck(Piece piece){
      int verCount, horCount, diaCount1, diaCount2;
      verCount = verticalCount(piece);
      horCount = horizontalCount(piece);
      diaCount1 = diagonalCount1(piece);
      diaCount2 = diagonalCount2(piece);
      if (verCount == WINCOUNT || horCount == WINCOUNT || diaCount1 == WINCOUNT || diaCount2 == WINCOUNT) {
         return true;
      } else {
         return false;
      }
   }
   
   /** Set one slot on the board to a player's piece
   * @param piece - the location of the player's input
   */
   public void setPlayer(Piece piece){
      int row = piece.getRow();
      int col = piece.getCol();
      int playerNumber = piece.getPlayerNumber();
      this.board[row][col] = playerNumber;
   }
   
   /** Return which player occupies the specifying slot
   * @param row - the specifying row of the slot
   * @param row - the specifying column of the slot
   */
   public int getPlayer(int row, int col) {
      return this.board[row][col];
   }
   
   /** Save the current board content and the next player to a file
   * @param fileName - a file name for saving
   * @param playerNumber - the player that goes next
   * Taught by my ICS teacher last year
   */
   public void saveFile (String fileName, int playerNumber) {
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter(fileName));
         out.write("" + playerNumber);
         out.newLine();
         for (int i = 0; i < NUMROW; i++) {
            for(int j = 0; j < NUMCOL; j++) {
               out.write(this.board[i][j] + " ");
            }
            out.newLine();
         }
         out.close();
         System.out.println("Your game is saved. Now leading you back to the menu...");
      } catch (IOException iox) {
         System.out.println("Error writing the file. Please try again.");
      }
   }
   
   /** Load a saved game from a specifying file 
   * @param fileName - the name of the file to load from
   * @return an int indicating which player goes next
   */
   public int loadFile (String fileName){
      try {
         BufferedReader in = new BufferedReader (new FileReader(fileName));
         String line;
         String[] words;
         int nextPlayer = 1;
         
         nextPlayer = Integer.parseInt(in.readLine());
         for (int i = 0; i < NUMROW; i++) {
            line = in.readLine();
            words = line.split(" ");
            for (int j = 0; j < NUMCOL; j++) {
               this.board[i][j] = Integer.parseInt(words[j]);
            }
         }
         in.close();
         System.out.println("Your game is loaded. Now leading you back to the menu...");
         return nextPlayer;
      } catch (IOException iox) {
         System.out.println("Error accessing the file. Please try again.");
         return 1;
      } 
   }
}