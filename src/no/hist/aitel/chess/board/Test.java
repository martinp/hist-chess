/*
 * Test.java
 * 
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author martin
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b  = new Board();

        // 56  57  58  59  60  61  62  63
        // 48  49  50  51  52  53  54  55
        // 40  41  42  43  44  45  46  47
        // 32  33  34  35  36  37  38  39
        // 24  25  26  27  28  29  30  31
        // 16  17  18  19  20  21  22  23
        // 8   9   10  11  12  13  14  15
        // 0   1   2   3   4   5   6   7

        b.movePiece(8, 16);
        b.movePiece(49, 41);
        b.movePiece(9, 17);
        b.movePiece(48, 40);
        b.movePiece(17, 25);
        b.movePiece(40, 32);
        b.movePiece(25, 32);
        b.movePiece(41, 32);
        b.movePiece(12, 28);
        b.movePiece(51, 43);
        b.movePiece(2, 9);
        b.movePiece(58, 49);
        b.movePiece(9, 54);
        b.movePiece(61, 54);
        b.movePiece(16, 24);
        b.movePiece(49, 42);
        b.movePiece(1, 16);
        b.movePiece(60, 51);
        b.movePiece(0, 8);
        b.movePiece(56, 40);
        b.movePiece(8, 9);
        b.movePiece(52, 44);
        b.movePiece(9, 57);

        System.out.println(b.toString());
    }


}
