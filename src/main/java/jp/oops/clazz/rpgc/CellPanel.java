package jp.oops.clazz.rpgc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;
import jp.oops.clazz.rpgc.widget.CellWidget;
import jp.oops.clazz.rpgc.widget.NumOrSymbolWidget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hemmi
 */
public class CellPanel extends JPanel {

    private static final Logger LOG = LoggerFactory.getLogger(CellPanel.class);

    boolean bDrawLine = false;
    Point from;
    Point to;

    public CellPanel() {
    }

    public void setLine(boolean draw, Point from, Point to) {
        this.bDrawLine = draw;
        this.from = from;
        this.to = to;
    }

    @Override
    public void paintComponent(Graphics g) {
        /*
        if (isLayoutRequired) {
            LOG.info("------- start re-Layout");
            layoutLabels();
            LOG.info("------- end re-Layout");
            isLayoutRequired = false;
        }
         */
        super.paintComponent(g);

        final Graphics2D g2 = (Graphics2D) g;
        final MemoryModel memoryModel = GuiMain.theApp.memoryModel;
        final int p = memoryModel.getP();
        final int q = memoryModel.getQ();

        {
            // +0 +1 +2 +3 +4 +5 +6 +7 +8 +9
            int yScale = CellWidget.calcYpos(10);
            CellWidget.drawScale(g2, yScale);
        }

        if (this.bDrawLine) {
            Color c0 = g2.getColor();
            try {
                g2.setColor(GuiMain.ADDRESS_COLOR);

                g2.drawLine(from.x, from.y, to.x, to.y);
            } finally {
                g2.setColor(c0);
            }
        }

        for (int i = 0; i < 100; i++) {

            if (1 < i && i < 10) {
                continue;
            }

            CellWidget wid = memoryModel.getMemory(i);
            wid.draw(g2, p, q);
        }

        // 100以上のエリア（数字 or シンボル)
        paintNumOrSymbol(g2);
    }

    void paintNumOrSymbol(Graphics2D g2) {

        MemoryModel memoryModel = GuiMain.theApp.memoryModel;
        final int p = memoryModel.getP();
        final int q = memoryModel.getQ();

        int numOrSymbolSize = memoryModel.numOrSymbolSize();

        NumOrSymbolWidget w0 = memoryModel.getNumOrSymbol(100);
        CellWidget.drawScale(g2, w0.getYpos());

        for (int i = 0; i < numOrSymbolSize; i++) {

            NumOrSymbolWidget w = memoryModel.getNumOrSymbol(i + 100);
            w.draw(g2, p, q);
        }
    }

}
