package jp.oops.clazz.rpgc.widget;

import java.awt.Color;
import java.awt.Graphics2D;
import jp.oops.clazz.rpgc.GuiMain;
import jp.oops.clazz.rpgc.NumOrSymbol;

/**
 *
 * @author hemmi
 */
public class NumOrSymbolWidget /*implements AbstractWidget*/ {

    private final int xpos;
    private final int ypos;
    private final int width;
    private final int height;

    private final short adrs;
    private final NumOrSymbol numOrSymbol;

    public NumOrSymbolWidget(short ad, NumOrSymbol object) {

        if (ad < 100) {
            throw new IllegalArgumentException("adrs < 100");
        }
        if (object == null) {
            throw new NullPointerException("object ");
        }
        this.adrs = ad;
        this.numOrSymbol = object;

        int idx = ad; //ad - 100; 
        xpos = (idx % 10) * 100 + 30;
        ypos = (idx / 10) * 25 + 10 + 90;
        width = 96;
        height = 20;
    }

    public void draw(Graphics2D g2, final int p, final int q) {

        final int yy = getYpos() + 15;

        if ((adrs % 10) == 0) {
            // アドレスを左に表示
            Color c0 = g2.getColor();
            try {
                g2.setColor(GuiMain.ADDRESS_COLOR);
                g2.drawString(String.valueOf(adrs), getXpos() - 30, yy);
            } finally {
                g2.setColor(c0);
            }
        }

        g2.drawRect(getXpos(), getYpos(), getWidth(), getHeight());

        if (this.adrs == p) {
            Color c2 = g2.getColor();
            try {
                g2.setColor(GuiMain.P_COLOR);
                g2.drawRect(getXpos() - 1, getYpos() - 1, getWidth() + 2, getHeight() + 2);
            } finally {
                g2.setColor(c2);
            }
        }
        if (this.adrs == q) {
            Color c2 = g2.getColor();
            try {
                g2.setColor(GuiMain.Q_COLOR);
                g2.drawRect(getXpos() - 1, getYpos() - 1, getWidth() + 2, getHeight() + 2);
            } finally {
                g2.setColor(c2);
            }
        }

        String str = getNumOrSymbol().decode();

        g2.drawString(str, getXpos() + 2, yy);
    }

    /**
     * @return the adrs
     */
    public short getAdrs() {
        return adrs;
    }

    /**
     * @return the numOrSymbol
     */
    public NumOrSymbol getNumOrSymbol() {
        return numOrSymbol;
    }

    /**
     * @return the xpos
     */
    public int getXpos() {
        return xpos;
    }

    /**
     * @return the ypos
     */
    public int getYpos() {
        return ypos;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

}
