/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.Scanner;

/**
 *
 * @author lborisz
 */
public class Game {
  
  public static int player_flag = 0, // 0 white, 1 black
                    from_row = 0, // A, B, C... (1-8)
                    to_row = 0, // A, B, C... (1-8)
                    from_column = 0, // 1 - 8
                    to_column = 0; // 1 - 8
  
  public static void play(){
    boolean exit = false;
    Scanner scanner = new Scanner(System.in);
    do {
      ChessBoardPrinter.printBoard();
        System.out.println("A "+(player_flag == 0 ? "világos" : "sötét")+" jön!");
        System.out.println("Kérem lépjen egyet! (példa-a világos gyalog egyet előre lép: b2 b3) Az exit-re kilép a programból.");
      String inputLine = scanner.nextLine().trim();
      if ("exit".equals(inputLine)){
        exit = true;
      } else {
        if (checkInputLine(inputLine)){
          System.out.println("FLAG: "+player_flag);
          if (Chess.move(player_flag, from_row, from_column, to_row, to_column)){
            player_flag = player_flag == 0 ? 1 : 0;
          } else {
            System.out.println("Hibás lépés, lépjen újra!!");
          }
        }
        System.out.println(from_row + " " + from_column + " " + to_row + " " + to_column);        
      }
    } while (!exit);
  }
  
  public static boolean checkInputLine(String inputLine){
    if (null == inputLine || "".equals(inputLine)){
      System.out.println("Nem megfelelő adatbevitel.");
      return false;
    }
    if (inputLine.length() != 5){
      System.out.println("Nem megfelelő adatbevitel.");
      return false;
    }
    char[] inputChars = inputLine.toCharArray();
//    if ((inputChars[0] < 'a' || inputChars[0] > 'f') || 
//        (inputChars[1] < '1' || inputChars[1] > '8') ||
//         inputChars[2] != ' ' ||
//        (inputChars[3] < 'a' || inputChars[3] > 'f') || 
//        (inputChars[4] < '1' || inputChars[4] > '8')){

if (    !((inputChars[0] >= 'a' && inputChars[0] <= 'h') && 
        (inputChars[1] >= '1' && inputChars[1] <= '8') &&
         inputChars[2] == ' ' && 
        (inputChars[3] >= 'a' && inputChars[3] <= 'h') && 
        (inputChars[4] >= '1' && inputChars[4] <= '8'))
   ){
      System.out.println("Nem megfelelő adatbevitel.");
      return false;
    } else {
      from_column = (int)inputChars[0] - 96;//b == 98. karakter az ascii táblában --> 98-96 == 2; AlphabeticAsciiPositionShiftToTable
      from_row = inputChars[1] - 48;//2 == 50. karakter az ascii táblában --> 50-48 == 2; NumericAsciiPositionShiftToTable
      
      to_column = (int)inputChars[3] - 96;
      to_row = inputChars[4] - 48;
    }
    return true;
  }
  
  public static void main(String[] args) {
    Chess.init();
    play();
  }
  
}
