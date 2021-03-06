package no.hist.aitel.chess.gui;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import static no.hist.aitel.chess.gui.guiConstants.*;

/**
 *
 * @author Vegard
 */
public class drawBoard extends JPanel { 
    
    private Font text = new Font("COURIER NEW", Font.PLAIN, 16);    

    /**
     * Draws the board in GUI
     */
    public drawBoard() {        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int y = 1;     

        boolean inverse = false;
        boolean evenNum;
        g.setColor(background);
        // filling background with color
        for(int i=0; i<8; i++) {            
            g.fillRect(i*width, 0, width, height);
        }
        for(int i=0; i<8; i++) {
            g.fillRect(i*width, 9*height, width, height);
        }
        for(int i=0; i<8; i++) {
            g.fillRect(i*width, 10*height, width, height);
        }        
        
        for (int i = 1; i<=64; i++) {
            int x = i%8;

            if (!inverse) { // Sjekker om vi har byttet om på rekkefølgen
                evenNum = (i % 2 == 0);
            } else {
                evenNum = !(i % 2 == 0);
            }
            if (evenNum) {
                g.setColor(white);
            } else {
                g.setColor(dunkel);
            }            
            g.fillRect(x*width, y*height, width, height);
            if (i % 8 == 0) { // Står nå på den siste ruten i linja
                inverse = !inverse;
                y+=1;
            }
        }

        int j=8;
        String number;
        for (int i = 0; i<8; i++) {            
            number = String.valueOf(j);
            g.setFont(text);
            g.setColor(black);
            g.drawString(number, 2, ((i+2)*height)-15);
            j--;
        }
        String abcdefgh = "abcdefgh";
        for (int i = 0; i<8; i++) {
            String letter;
            letter = abcdefgh.substring(i, i+1);
            g.setFont(text);
            g.setColor(black);
            g.drawString(letter, (width*i)+(width-35), (height*9)+15);
        }

    }  
}


    

    
 

                    

