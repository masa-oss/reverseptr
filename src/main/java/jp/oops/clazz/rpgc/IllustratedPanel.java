package jp.oops.clazz.rpgc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import jp.oops.clazz.rpgc.widget.Cell;

/**
 *
 * @author hemmi
 */
public class IllustratedPanel extends JPanel {
    
    Cell cell = new Cell(20,50, 96, 20);
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
    
        final int len = 20;
        
        cell.drawWaku(g2);
        
        {
            int x = cell.getXpos();
            int y = cell.getYpos();
            
            g.drawLine(x + 8, y, x+8, y-len);
            
            g.drawString("GCフラグ", x, y-len-4);
        }
        {
            int x = cell.getXpos();
            int y = cell.getYpos();
            int w = cell.getWidth();
            int h = cell.getHeight();
            
            x = x + 15 + 12;
            
            g.drawLine(x, y+h, x, y+h+len);
            int yy = y+h+len;
            g.drawString("10 - CARが書き替えられている", x-5, yy+20);
            g.drawString("01 - CDRが書き替えられている", x-5, yy+40);
        }
    }

}
