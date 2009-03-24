/*
 * Position.java
 *
 */

package no.hist.aitel.chess.position;

import no.hist.aitel.chess.board.Board;
import no.hist.aitel.chess.piece.IllegalTypeException;
import no.hist.aitel.chess.piece.Piece;
import static no.hist.aitel.chess.piece.PieceConstants.*;

/**
 *
 * @author martin
 */

public class Position {
    private Board board;
    private int from, to, diff;

    /**
     * Creates a position object which is used to validate moves
     */
    public Position(Board board) {
        this.board = board;
    }

    /**
     * Sets the from and to positions
     * @param from
     * @param to
     */
    public void setPositions(int from, int to) {
        this.from = from;
        this.to = to;
        this.diff = to - from;
    }

    /**
     * Verifies positions
     * @return Throws an exception if positions are invalid
     */
    public void verifyPositions() throws IllegalPositionException {
        // Get destionation pieces
        Piece fromPiece = board.getPiece(from);
        Piece toPiece = board.getPiece(to);

        // Can't capture piece of same color
        if (fromPiece.getColor() == toPiece.getColor()) {
            throw new IllegalPositionException("Can't capture piece of same type.\n" +
                    "Type: " + fromPiece.getType() +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }

        // Piece type
        int type = fromPiece.getType();

        // Direction
        int direction = getDirection(type);

        // Check if path is clear, not checking for type == 2 (Knight) since it can jump over pieces
        if (type != KNIGHT && !isValidPath(direction)) {
            throw new IllegalPositionException("A piece is blocking my path.\n" +
                    "Type: " + fromPiece.getType() +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }

        // Type specific rules
        switch (type) {
            case PAWN: {
                if (toPiece.isEmpty()) { // New position is empty, pawn can then only move forward
                    if (fromPiece.getColor() == 0) { // White piece
                        if (from >= 8 && from <= 15) { // If the pawn is in its original position, it can move 1 or 2 fields forward
                            if (diff != 8 && diff != 16) {
                                throw new IllegalPositionException("Pawn can only move one or two fields forward when in initial position.\n" +
                                        "Type: " + fromPiece.getType() +
                                        "\nFrom: " + from +
                                        "\nTo: " + to);
                            }
                        } else if (diff != 8) { // Pawn can always move 1 field forward
                            throw new IllegalPositionException("Pawn can only move one field forward when not in initial position.\n" +
                                    "Type: " + fromPiece.getType() +
                                    "\nFrom: " + from +
                                    "\nTo: " + to);
                        }
                    } else if (fromPiece.getColor() == 1) { // Black piece
                        if (from >= 48 && from <= 55) { // Same as above
                            if (diff != -8 && diff != -16) {
                                throw new IllegalPositionException("Pawn can only move one or two fields forward when in initial position.\n" +
                                        "Type: " + fromPiece.getType() +
                                        "\nFrom: " + from +
                                        "\nTo: " + to);
                            }
                        } else if (diff != -8) {
                            throw new IllegalPositionException("Pawn can only move one field forward when not in initial position.\n" +
                                    "Type: " + fromPiece.getType() +
                                    "\nFrom: " + from +
                                    "\nTo: " + to);
                        }
                    }
                } else {
                    if (fromPiece.getColor() == 0 && diff != 9 && diff != 7) {
                        throw new IllegalPositionException("Pawn can't move forward because field isn't empty.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    } else if (fromPiece.getColor() == 1 && diff != -9 && diff != -7) {
                        throw new IllegalPositionException("Pawn can't move forward because field isn't empty.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    }
                }
                break;
            }
            case BISHOP: {
                if (diff % 7 == 0 || diff % 9 == 0) {
                    break;
                } else {
                    throw new IllegalPositionException("Bishop can only move diagonally.\n" +
                            "Type: " + fromPiece.getType() +
                            "\nFrom: " + from +
                            "\nTo: " + to);
                }
            }
            case KNIGHT: {
                switch (diff) {
                    case -10:
                    case -17:
                    case -15:
                    case -6:
                    case 6:
                    case 10:
                    case 15:
                    case 17: {
                        break;
                    }
                    default: {
                        throw new IllegalPositionException("Knight can only move one field diagonally + one forward.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    }
                }
                break;
            }
            case ROOK: {
                if (diff % 8 == 0) {
                    break;
                } else {                    
                    // No idea why, but this seems to be working
                    int position = from;
                    while (position % 8 != 0) { // Find closest previous field that is dividable by 8
                        position--;
                    }
                    int myDiff = to - position;
                    if ((myDiff == 8 || myDiff == -8) || ((myDiff > 7 || myDiff < -7) && myDiff % 8 != 0)) {
                        throw new IllegalPositionException("Rook can only move forward, backward, left or right.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    } else {
                        break;
                    }

                    // The following also works (for white anyway :-P)
//                    for (int position = from; position < to; position++) {
//                        if (position != from && position % 8 == 0) { // Moved past end of this line
//                            throw new IllegalPositionException("Rook can only move forward, backward, left or right.\n" +
//                                    "Type: " + fromPiece.getType() +
//                                    "\nFrom: " + from +
//                                    "\nTo: " + to);
//                        }
//                    }
//                    break;
                }
            }
            case QUEEN: {
                switch (diff) {
                    case -10:
                    case -17:
                    case -15:
                    case -6:
                    case 10:
                    case 17:
                    case 15:
                    case 6: {
                        throw new IllegalPositionException("Queen can't move one field diagonally + one forward.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case KING: {
                switch (diff) {
                    case -1:
                    case -7:
                    case -8:
                    case -9:
                    case 1:
                    case 7:
                    case 8:
                    case 9: {
                        break;
                    }
                    default: {
                        throw new IllegalPositionException("King can only move one field in any direction.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    }
                }
                break;
            }
            default: {
                throw new IllegalTypeException("Invalid type: " + type);
            }
        }
    }

    /**
     * Get the current direction for a type (not for Knight)
     * @param type
     * @return The direction or -1 if something bad happens
     */
    private int getDirection(int type) {
        int[] directions = getDirections(type);
        switch (type) {
            case PAWN:
            case BISHOP:
            case KNIGHT:
            case ROOK:
            case QUEEN:
            case KING: {
                for (int direction : directions) {
                    if (diff % direction == 0) {
                        return direction;
                    }
                }
                return -1; // Piece is moving one field to the left or right (1 % (n!=1) != 0)
            }
            default: {
//                throw new IllegalTypeException("getDirection() was called with invalid type: "
//                        + type);
                return -1;
            }
        }
    }


    /**
     * Get possible directions for a type
     * @param type
     * @return The possible directions for the type or null if something bad happens
     */
    private int[] getDirections(int type) {
        switch (type) {
            case PAWN: {
                return new int[] {7, 8, 9, 16};
            }
            case BISHOP: {
                return new int[] {7, 9};
            }
            case KNIGHT: {
                return new int[] {6, 10, 15, 17};
            }
            case ROOK: {
                return new int[] {8}; // Not including 1 as n % 1 == 0 and that causes an invalid
                                      // warning to be displayed
            }
            case QUEEN:
            case KING: {
                return new int[] {7, 8, 9}; // Not including 1 as n % 1 == 0 and that causes an
                                            // invalid warning to be displayed

            }
            default: {
//                throw new IllegalTypeException("getDirections() was called with invalid type: "
//                        + type);
                return null;
            }
        }
    }

    /**
     * Checks if path in a direction is clear
     * @param direction
     * @return True if the path is clear and false otherwise
     */
    private boolean isValidPath(int direction) {
        if (direction == -1) { // Happens when a pieces moves one field to the left or right,
                               // which is always valid since no piece can exist between only two
                               // fields
            return true;
        }
        if (to < from) { // Black moves in a negative direction
            for (int position = from - direction; position > to; position -= direction) {
                if (!board.getPiece(position).isEmpty()) {
                    return false;
                }
            }
        } else { // White moves in a positive direction
            for (int position = from + direction; position < to; position += direction) {
                if (!board.getPiece(position).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }
}
