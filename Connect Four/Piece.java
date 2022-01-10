/** The Piece class stores player 1 or 2¡¯s current piece input.
* It contains a constructor and methods for Piece objects.
* @author Lucy Wu
* @since October 23, 2020
* @version 1
*/

public class Piece{
   
   private int currentRow = -1;
   private int currentCol = -1;
   private int playerNumber;
   
   /** Constructor of the Piece class
   * @param playerNumber - a number indicating which player it is
   */
   public Piece (int playerNumber){
      this.playerNumber = playerNumber;
   }
   
   /** Set the row number of the player's current piece
   * @param rowChoice - the current row that corresponds to the player's column choice
   */
   public void setRow(int rowChoice){
      this.currentRow = rowChoice;
   }
   
   /** Set the column number of the player's current piece
   * @param colChoice - the current column that the player selects
   */
   public void setCol(int colChoice){
      this.currentCol = colChoice;
   }
   
   /** Return the row number of the player's current piece
   * @return the value of currentRow
   */
   public int getRow(){
      return this.currentRow;
   }
   
   /** Return the column number of the player's current piece
   * @return the value of currentCol
   */
   public int getCol(){
      return this.currentCol;
   }
   
   /** Return the player number the piece belong to
   * @return the value of playerNumber
   */
   public int getPlayerNumber(){
      return this.playerNumber;
   }
   
   /** Return the current row number, column number, and the player number this piece belongs to
   * @return the value of currentRow, currentCol, and playerNumber
   */
   @Override
   public String toString(){
      String toReturn;
      toReturn = "Current row number: " + this.currentRow + "\nCurrent column number: " + this.currentCol;
      return toReturn;
   }
}