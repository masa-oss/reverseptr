package jp.oops.clazz.rpgc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hemmi
 */
public class ExplainPanel extends JPanel {
    
    private static final Logger LOG = LoggerFactory.getLogger(ExplainPanel.class);
    
    @Override
    public void paintComponent(Graphics g) {
        
        LOG.info("paintComponent");
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
    
        int yy = 25;
        int phase = GuiMain.theApp.algorithm.getPhase();
        
        g2.drawString("phase=" +phase, 5, yy);
        yy += 20;
        g2.drawString("▼ STEPボタンで実行した事 ▼", 5, yy);
        yy += 20;

        String explanation = GuiMain.theApp.algorithm.getExplanation();
        String[] split = explanation.split("\n");
        
        for (String s : split) {
            LOG.info("s = {}", s);
            g2.drawString(s, 5, yy);
            yy += 20;
        }
        
    }
    
    
}
