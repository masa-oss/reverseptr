package jp.oops.clazz.rpgc.widget;

import java.awt.Graphics2D;

/**
 *
 * @author hemmi
 */
public class Cell {
    
    protected final int xpos;
    protected final int ypos;
    protected final int width;
    protected final int height;
    
    public Cell(int x, int y, int w, int h) {
        this.xpos = x;
        this.ypos = y;
        this.width = w;
        this.height = h;
    }
    

    public void drawWaku(Graphics2D g2) {
        
        final int yy = getYpos() + 15;
        
        g2.drawRect(getXpos(), getYpos(), getWidth(), getHeight());

        // 使用中マーク
        g2.drawLine(getXpos() + 15, getYpos(), getXpos() + 15, getYpos() + getHeight());

        // 逆転ポインタの状態
        g2.drawLine(getXpos() + 40, getYpos(), getXpos() + 40, getYpos() + getHeight());
        
        // car と cdr
        g2.drawLine(getXpos() + 68, getYpos(), getXpos() + 68, getYpos() + getHeight());
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
