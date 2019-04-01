/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author lborisz
 */
public class Chess {
    
    public final static char SPACE = '\u0020';

  public static final char WHITE_KING = '\u2654';
  public static final char BLACK_KING = '\u265A';
  public static final char WHITE_QUEEN = '\u2655';
  public static final char BLACK_QUEEN = '\u265B';

  public static final char WHITE_ROOK = '\u2656';
  public static final char BLACK_ROOK = '\u265C';
  public static final char WHITE_KNIGHT = '\u2658';
  public static final char BLACK_KNIGHT = '\u265E';
  public static final char WHITE_BISHOP = '\u2657';
  public static final char BLACK_BISHOP = '\u265D';

  public static final char WHITE_PAWN = '\u2659';
  public static final char BLACK_PAWN = '\u265F';

  public static char[][] board = new char[9][9];

  public static final char[] WHITE_FIGURES = {WHITE_PAWN, WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING};
  public static final char[] BLACK_FIGURES = {BLACK_PAWN, BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING};

  public static char[] wfq = {' ', WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN,};
  public static char[] bfq = {' ', BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN,};

  public static char[] wrq = {' ', WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK,};
  public static char[] brq = {' ', BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK,};

  public static void init() {
    
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = SPACE;
      }
    }
    board[1] = wrq;
    board[2] = wfq;
    board[7] = bfq;
    board[8] = brq;
  }


  public static boolean move(int player_flag, int from_row, int from_column, int to_row, int to_column) {
    char figure = board[from_row][from_column];
    //System.out.println("move");
    if (!isValidFigure(player_flag, figure)){
      return false;
    }
    if (!checkRule(player_flag, figure, from_row, from_column, to_row, to_column)){
      return false;
    }
    board[from_row][from_column] = ' ';
      board[to_row][to_column] = figure;
      return true;
    
  }
  //Ha valid akkor a játékos érvényes bábut választott.
  public static boolean isValidFigure(int player_flag, char figure) {
    if (figure == ' ') {
      return false;
    }
    int numberOfFigure = 0;
    //Ha fehér figura
    if (player_flag == 0) {
        //Végig szaladunka fehér figurák tömbön. Addig amig a paraméterben kapott figura meg nem egyezik az egyik tömbben figurával.
        //Ha megvan a figura akkor annak számát megörizzük és kilépünk a ciklusból.
        //Igy biztos kisebb számunk lesz mint a figurák tömb hossza.
      while (numberOfFigure < WHITE_FIGURES.length && figure != WHITE_FIGURES[numberOfFigure]) {
        numberOfFigure++;
      }
    //Egyébként fekete figura
    } else {
      while (numberOfFigure < BLACK_FIGURES.length && figure != BLACK_FIGURES[numberOfFigure]) {
        numberOfFigure++;
      }
    }
    return numberOfFigure < BLACK_FIGURES.length ? true : false;
  }

  public static boolean checkRule(int player_flag, char figure, int from_row, int from_column, int to_row, int to_column) {
    boolean isOK = false;
    if (figure == BLACK_PAWN || figure == WHITE_PAWN){
      isOK = checkPawnRule(player_flag, from_row, from_column, to_row, to_column);
    }
    if (figure == BLACK_ROOK || figure == WHITE_ROOK){
      isOK = checkRookRule(player_flag, from_row, from_column, to_row, to_column);
    }
    if (figure == BLACK_KNIGHT || figure == WHITE_KNIGHT){
      isOK = checkKnightRule(player_flag, from_row, from_column, to_row, to_column);
    }
    if (figure == BLACK_BISHOP || figure == WHITE_BISHOP){
      isOK = checkBishopRule(player_flag, from_row, from_column, to_row, to_column);
    }
    if (figure == BLACK_QUEEN || figure == WHITE_QUEEN){
      isOK = checkQueenRule(player_flag, from_row, from_column, to_row, to_column);
    }
    if (figure == BLACK_KING || figure == WHITE_KING){
      isOK = checkKingRule(player_flag, from_row, from_column, to_row, to_column);
    }
    return isOK;
  }
    
    private static boolean checkPawnRule(int player_flag, int from_row, int from_column, int to_row, int to_column) {
        return true;
    }
    private static boolean checkRookRule(int player_flag, int from_row, int from_column, int to_row, int to_column) {
        return true;
    }
    private static boolean checkKnightRule(int player_flag, int from_row, int from_column, int to_row, int to_column) {
        return true;
    }
    //Az még nincs megvalósitva, hogy a futó mit csinál ha van valaki a lépése útjában.
    private static boolean checkBishopRule(int player_flag, int from_row, int from_column, int to_row, int to_column) {
        //Irányvektor v(1, 1)
        int v1 = 1, v2 = 1;
        //Irányvektor z(1, -1)
        int z1 = 1, z2 = -1;
        // Pont p(x0, y0) azaz (from_row, from_column) 
        int x0 = from_row, y0 = from_column;
        // pont p1(x, y) azaz (to_row, to_column)
        int x = to_row, y = to_column;
        //Egyenes egyenlete
        // v2 x - v1 y = v2x0 - v1y0 a jobb oldal legyen c = v2x0 - v1y0
        int c1 = (v2 * x0) - (v1 * y0); //A v irányvektorra
        System.out.println("c1: "+c1);
        System.out.println((v2 * x) - (v1 * y));
        //Rajta van-e a p1(to_row, to_column) 
        if (c1 == (v2 * x) - (v1 * y)){
          return true;
        }
        int c2 = (z2 * x0) - (z1 * y0); //A z irányvektorra
        System.out.println("c2: "+c2);
        System.out.println((z2 * x) - (z1 * y));
        //Rajta van-e a p1(to_row, to_column)
        if (c2 == (z2 * x) - (z1 * y)){
          return true;
        }
        return false;
    }
    
    private static boolean checkQueenRule(int player_flag, int from_row, int from_column, int to_row, int to_column) {
        return true;
    }
    
    private static boolean checkKingRule(int player_flag, int from_row, int from_column, int to_row, int to_column) {
        boolean isGoodStep = false;
        
        //1. Sima király lépés: Egy mezőt léphet minden irányban a táblán.
        if(((int)Math.abs(to_row-from_row)==1) || ((int)Math.abs(to_column-from_column)==1)){
            boolean leftSide = false;
            boolean rightSide = false;
            boolean upSide = false;
            boolean downSide = false;

            int beginRow = from_row;
            int endRow = from_row;
            int beginCol = from_column;
            int endCol = from_column;

            //Ha a király a tábla szélén vagy sarkán van,
            //akkor eltolást kell alkalmaznunk a király körüli mezők vizsgálatakor.
            if(from_row==1) upSide = true;
            if(from_row==board.length-1) downSide = true;
            if(from_column==1) leftSide = true;
            if(from_column==board[0].length-1) rightSide = true;

            if(leftSide) beginCol=2;
            if(rightSide) endCol=board[0].length-2;
            if(upSide) beginRow=2;
            if(downSide) endRow=board.length-2;

            //Két For ciklussal megnézzük a királytól balra, illetve jobbra levő mezőt,
            //és a tőle egy sorral feljebb és lentebb levő három-három mezőt is, mert ezekre a mezőkre léphet a király.
            boolean coordsIsOk = false;
            for (int j = beginRow-1; j <= endRow+1; j++) {
                for (int k = beginCol-1; k <= endCol+1; k++) {
                    coordsIsOk = (j == to_row) && (k == to_column);
                    //Ha a paraméterben kapott to_row és a to_column koordináta egyzik a király körüli mezők egyikével, akkor jó a lépés
                    if(coordsIsOk){
                        //Ha nincs saját figura a To mezőn akkor jó a lépés.
                        if(!isOwnFigureOnToField(player_flag, to_row, to_column)){
                            //És ide kéne még egy ellenőrzés, hogy a király nincs-e sakkban a to_row-to_column mezőn
                            isGoodStep = true;
                        }
                    }
                }
            }
        }
        
        //2. Rosálás lépés: azaz a király kettő mezőt lép, de azt csak ugyanabban a sorban teheti.
        //A rosálást majd valahogy függvényen kivülre jelezni kell, mert ebben az esetben 2 figurával kell lépni egyszerre!
        if(((to_row-from_row)==0) && ((int)Math.abs(to_column-from_column)==2)){
            //Ha világos lépés van
            if(player_flag == 0){
                //Ha a király még nem lépett (From mező: E1)
                if(board[from_row][from_column] == WHITE_KING){
                    //-Ha hosszú rosálás van (To mező: C1)
                    if(to_column == 3){
                        //Az A1 mezőn levő bástya még nem lépett. (Az A1 mezőn világos bástya van.)
                        if(board[1][1] == WHITE_ROOK){
                            //A király (E1) és a bástya (A1) közötti terület üres.
                            if(board[1][2]==SPACE && board[1][3]==SPACE && board[1][4]==SPACE)
                                //A király (E1) és a bástya (A1) közötti üres mezők nem támadottak-e (minden mezőre checkMate() függvény)
                                    isGoodStep = true;
                        }    
                    }
                    //-Ha rövid rosálás van (To mező: G1)
                    if(to_column == 7){
                        //A H1 mezőn levő bástya még nem lépett. (Az H1 mezőn világos bástya van.)
                        if(board[1][8] == WHITE_ROOK){
                            //A király (E1) és a bástya (H1) közötti terület üres.
                            if(board[1][6]==SPACE && board[1][7]==SPACE){
                                //A király (E1) és a bástya (H1) közötti üres mezők nem támadottak-e (minden mezőre checkMate() függvény)  
                                    isGoodStep = true;
                            }
                        }
                    }
                }                    
            }
            //Ha sötét lépés vaan
            if(player_flag == 1){
                //Ha a király még nem lépett (From mező: E8)
                if(board[from_row][from_column] == BLACK_KING){
                    //-Ha hosszú rosálás van (To mező: C8)
                    if(to_column == 3){
                        //Az A8 mezőn levő bástya még nem lépett. (Az A8 mezőn sötét bástya van.)
                        if(board[8][1] == BLACK_ROOK){
                            //A király (E8) és a bástya (A8) közötti terület üres.
                            if(board[8][2]==SPACE && board[8][3]==SPACE && board[8][4]==SPACE){
                                //A király (E8) és a bástya (A8) közötti üres mezők nem támadottak-e (minden mezőre checkMate() függvény)
                                    isGoodStep = true;
                            }
                        }
                    }
                    //-Ha rövid rosálás van (To mező: G8)
                    if(to_column == 7){
                        //A H8 mezőn levő bástya még nem lépett. (Az H8 mezőn sötét bástya van.)
                        if(board[8][8] == BLACK_ROOK){
                            //A király (E8) és a bástya (H8) közötti terület üres.
                            if(board[8][6]==SPACE && board[8][7]==SPACE){
                                //A király (E8) és a bástya (H8) közötti üres mezők nem támadottak-e (minden mezőre checkMate() függvény)
                                    isGoodStep = true;
                            }
                        }
                    }
                }                 
            }
        }
        return isGoodStep;
    }
    //végig szaladunk a saját szinű figurák tömbön és megnézzük, hogy van-e a To koordinéta mezőn a saját figuráink egyike.
    public static boolean isOwnFigureOnToField(int player_flag, int to_row, int to_column){
        boolean ownFigureIsOnToField = false;
        for (int i = 0; i < (player_flag==0 ? WHITE_FIGURES.length : BLACK_FIGURES.length); i++) {
            if(board[to_row][to_column]==(player_flag==0 ? WHITE_FIGURES[i] : BLACK_FIGURES[i])){
                ownFigureIsOnToField = true;
            }
        }
        return ownFigureIsOnToField;
    }
}
