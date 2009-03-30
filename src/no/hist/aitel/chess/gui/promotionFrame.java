

package no.hist.aitel.chess.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Vegard
 */
public class promotionFrame extends JFrame implements ActionListener {
    private JButton okButton;
    private JLayeredPane desktop;
    private JInternalFrame internalFrame;
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    static String queenString = "queen";
    static String rookString = "rook";
    static String knightString = "knight";
    static String bishopString = "bishop";
    static String blackOrWhite;
    static String picked;
    JLabel picture;
    

    public promotionFrame(String color) {
        if(color.equals("white")) {
            blackOrWhite = "w";
        }
        else {
            blackOrWhite = "b";
        }
        
        internalFrame = new JInternalFrame();       
        Buttonlistener listener = new Buttonlistener();

        okButton = new JButton("Ok");
        okButton.addActionListener(listener);
        Panel button = new Panel();
        button.add(okButton);
        add(button, BorderLayout.SOUTH);

        ButtonGroup group = new ButtonGroup();        

        JRadioButton queenButton = new JRadioButton(queenString);
        group.add(queenButton);
        queenButton.setActionCommand(queenString);
        queenButton.setSelected(true);
        queenButton.addActionListener(this);

        JRadioButton rookButton = new JRadioButton(rookString);
        group.add(rookButton);
        rookButton.setActionCommand(rookString);       
        rookButton.addActionListener(this);

        JRadioButton knightButton = new JRadioButton(knightString);
        group.add(knightButton);
        knightButton.setActionCommand(knightString);       
        knightButton.addActionListener(this);

        JRadioButton bishopButton = new JRadioButton(bishopString);
        group.add(bishopButton);
        bishopButton.setActionCommand(bishopString);        
        bishopButton.addActionListener(this);

        JPanel radioButtons = new JPanel(new GridLayout(0, 1));
        radioButtons.add(queenButton);
        radioButtons.add(rookButton);
        radioButtons.add(knightButton);
        radioButtons.add(bishopButton);

        picture = new JLabel(createImageIcon("./src/no/hist/aitel/chess/resources/"
                                             + queenString + blackOrWhite
                                             + ".gif"));
        
        add(radioButtons, BorderLayout.LINE_START);
        add(picture, BorderLayout.CENTER);       
        

        desktop = new JDesktopPane();
        desktop.setOpaque(true);
        internalFrame.add(desktop, BorderLayout.CENTER);

        setTitle("Promotion");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
        
    }

    protected static ImageIcon createImageIcon(String path) {
        File test = new File(path);
        if (test != null) {
            return new ImageIcon(path);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void actionPerformed(ActionEvent e) {
        picked = e.getActionCommand();
        picture.setIcon(createImageIcon("./src/no/hist/aitel/chess/resources/"
                                        + e.getActionCommand() + blackOrWhite
                                        + ".gif"));
        System.out.println(picked);
    }

    private class Buttonlistener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            
            System.out.println("test");
        }
    }

    public static void main(String[] args) {
        new promotionFrame("black");
    }
}