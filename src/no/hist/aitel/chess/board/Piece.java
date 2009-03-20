/*
 * Piece.java
 * 
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author martin
 */
public class Piece {

    private int position;
    final private int color;
    final private int type;

    /**
     * Creates a new piece.
     * @param position
     * @param color
     * @param type
     */
    public Piece(int position, int color, int type) {
        this.position = position;
        this.color = color;
        this.type = type;
    }

    /**
     * Get the piece color
     * @return A number which represents the piece color:<br />-1: Undefined<br />&nbsp;0: White<br />&nbsp;1: Black
     */
    public int getColor() {
        return color;
    }

    /**
     * Get the piece type
     * @return A number which represents the piece type:<br />-1: Undefined<br />&nbsp;0: Pawn<br />&nbsp;1: Bishop<br />&nbsp;2: Knight<br />&nbsp;3: Rook<br />&nbsp;4: Queen<br />&nbsp;5: King
     */
    public int getType() {
        return type;
    }

    /**
     * Get the piece position
     * @return Number which represents the piece position:<br />0-63: Index in the board array
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the position for this piece
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Check if position is empty
     * @return True if position is empty and false otherwise
     */
    public boolean isEmpty() {
        if (type == -1 && color == -1) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of this piece
     * @return A string containing piece position, color and type
     */
    @Override
    public String toString() {
        String out = "Position: " + position + "\nColor: " + getColor() + " (" + getColorStr() +
                ")\nType: " + getType() + " (" + getTypeStr() + ")";
        return out;
    }

    /**
     * Returns a string representation of the current color
     * @return Color as a string
     */
    private String getColorStr() {
        switch (color) {
            case -1: {
                return "Undefined";
            }
            case 0: {
                return "White";
            }
            case 1: {
                return "Black";
            }
            default: {
                return null;
            }
        }
    }

    /**
     * Returns a string representation of the current type
     * @return Type as a string
     */
    private String getTypeStr() {
        switch (type) {
            case -1: {
                return "Undefined";
            }
            case 0: {
                return "Pawn";
            }
            case 1: {
                return "Bishop";
            }
            case 2: {
                return "Knight";
            }
            case 3: {
                return "Rook";
            }
            case 4: {
                return "Queen";
            }
            case 5: {
                return "King";
            }
            default: {
                return null;
            }
        }
    }

}
