package no.hist.aitel.chess.gui;

/**
 *
 * @author lgreger_89
 */
//import no.hist.aitel.chess.gui.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import no.hist.aitel.chess.board.*;


class Mainwindow extends JFrame implements MouseListener, MouseMotionListener {
    private boolean canDrag = true;
    private Chessboard boardGui = new Chessboard();
    private Board board = new Board();
    private getRect getRect = new getRect();
    private int[] x_coords = boardGui.getXcoords();
    private int[] y_coords = boardGui.getYcoords();
    private int dragFromX = 0;
    private int dragFromY = 0;
    private int dragToX = 0;
    private int dragToY = 0;
    private int movingPiece = -1;
    private int x_coordStartPos;
    private int y_coordStartPos;
    int x;
    int y;
    
    public Mainwindow(String title) {        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new Buttons(), BorderLayout.SOUTH);
        add(boardGui, BorderLayout.CENTER);
        
        pack();
    }
    public void undoMove() {
        try {
            x_coords[movingPiece] = x - dragFromX;
            y_coords[movingPiece] = y - dragFromY;
            this.repaint();
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }        
    }
    
    public void mousePressed(MouseEvent e) {
        
        x = e.getX();
        y = e.getY();
        for(int i=0; i<64; i++){
            if(x>=x_coords[i] && x<=x_coords[i]+80 && y>= y_coords[i] && y<=y_coords[i]+80){
                movingPiece = i;
                canDrag=true;
                dragFromX = x - x_coords[i];
                dragFromY = y - y_coords[i];
                break;
            }           
            else{
                canDrag=false;
            }                       
        }        
        x_coordStartPos = x_coords[movingPiece];
        y_coordStartPos = y_coords[movingPiece];
        System.out.println(x+" "+y);
        
        
    }
    public void mouseDragged(MouseEvent e) {
        
        if(canDrag) {
            
            x_coords[movingPiece] = e.getX() - dragFromX;
            y_coords[movingPiece] = e.getY() - dragFromY;

            x_coords[movingPiece] = Math.max(x_coords[movingPiece], 0);
            x_coords[movingPiece] = Math.min(x_coords[movingPiece], getWidth() - 40);

            y_coords[movingPiece] = Math.max(y_coords[movingPiece], 0);
            y_coords[movingPiece] = Math.min(y_coords[movingPiece], getWidth() - 40);

            System.out.println(e.getX()+" "+e.getY());

            this.repaint();            
        }        
    }


    public void mouseReleased(MouseEvent e) {       
        x_coords[movingPiece] = x_coordStartPos;
        y_coords[movingPiece] = y_coordStartPos - 80;
        System.out.println(getRect.getRectNumber(e.getX(), e.getY()));
        this.repaint();
        
        
        //System.out.println(e.getX()+" "+e.getY());

    }
    public void mouseEntered(MouseEvent e) {        
    }
    public void mouseExited(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        canDrag = false;
        //System.out.println(board.toString());
    }
    public void mouseClicked(MouseEvent e) {        
    }
    public void mouseMoved(MouseEvent e) {        
    }

    public class Buttons extends JPanel {

        public Buttons() {
            Buttonlistener listener = new Buttonlistener();
            
            setLayout(new FlowLayout());

            JButton buttonOne = new JButton("New game");
            add(buttonOne);
            JButton buttonTwo = new JButton("Undo move");
            add(buttonTwo);
            JButton buttonThree = new JButton("Highscore");
            add(buttonThree);
            JButton buttonFour = new JButton("Save game");
            add(buttonFour);

            buttonOne.addActionListener(listener);
            buttonTwo.addActionListener(listener);
            buttonThree.addActionListener(listener);
            buttonFour.addActionListener(listener);
        }
    }

    public class Chessboard extends JPanel {

        private JLayeredPane layeredPane;
        private drawBoard chessBoard;
        private drawStartPos startPos = new drawStartPos();
        private int[] x_coords = startPos.getXcoords();
        private int[] y_coords = startPos.getYcoords();

        public Chessboard() {
            Dimension boardSize = new Dimension(1024, 768);
            layeredPane = new JLayeredPane();
            getContentPane().add(layeredPane);
            layeredPane.setPreferredSize(boardSize);
            chessBoard = new drawBoard();            
            chessBoard.setLayout(new GridLayout(8, 8));
            chessBoard.setPreferredSize(boardSize);
            startPos.initStartCoords();
            startPos.setBounds(5, 0, boardSize.width, boardSize.height);
            chessBoard.setBounds(5, 0, boardSize.width, boardSize.height);
            chessBoard.setOpaque(true);
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            startPos.setOpaque(false);    
            layeredPane.add(startPos, JLayeredPane.PALETTE_LAYER);
            add(layeredPane);          
                    

        }
        public void newgame() {            
            new Mainwindow("Chess");
        }
        public int[] getXcoords() {
            return x_coords;
        }
        public int[] getYcoords() {
            return y_coords;
        }
    }

    private class Buttonlistener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String commando = event.getActionCommand();

            if (commando.equals("New game")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new game?\nUnsaved progress will be lost.")) {
                    Chessboard newboard = new Chessboard();
                    newboard.newgame();
                }
            } else if (commando.equals("Undo move")) {
                undoMove();
                //JOptionPane.showMessageDialog(null, "Du angret et trekk");
            } else if (commando.equals("Highscore")) {
                JOptionPane.showMessageDialog(null, "Highscore:");
            } else if (commando.equals("Save game")) {
                JOptionPane.showMessageDialog(null, "Game saved");
            }
        }        
    }
}
class testGui {

    public static void main(String[] args) {

        JFrame frame = new Mainwindow("Chess");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
    }
}