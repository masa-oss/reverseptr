package jp.oops.clazz.rpgc.widget;

import java.awt.Color;
import java.awt.Graphics2D;
import jp.oops.clazz.rpgc.GuiMain;
import jp.oops.clazz.rpgc.MemoryModel;
import jp.oops.clazz.rpgc.Solarized;

/**
 *
 * @author hemmi
 */
public final class CellWidget extends Cell implements LispObject {

    final short adrs;
    private short car;
    private short cdr;

    private short mark = 1;
    private short status = 0;

    public CellWidget(short adrs, short car, short cdr) {

        super(calcXpos(adrs), calcYpos(adrs), 96, 20);

        this.adrs = adrs;
        this.car = car;
        this.cdr = cdr;
    }

    public static int calcYpos(int adrs) {

        int ad10 = adrs / 10;
        // １行目と２行目の間に隙間を空けている
        return (adrs / 10) * 25 + ((ad10 > 0) ? 35 : 10);
    }

    static int calcXpos(int adrs) {

        return (adrs % 10) * 100 + 30;
    }

    public static void drawScale(Graphics2D g2, int yy) {

        Color c0 = g2.getColor();
        try {
            g2.setColor(GuiMain.ADDRESS_COLOR);
            for (int i = 0; i < 10; i++) {

                int xx = calcXpos(i);
                g2.drawString("+" + String.valueOf(i), xx + 40, yy - 3);
            }
        } finally {
            g2.setColor(c0);
        }
    }

    public void draw(Graphics2D g2, final int p, final int q) {

        if (this.adrs < 10) {
            drawBelow10(g2, p, q);
        } else {
            drawOver10(g2, p, q);
        }
    }

    public void drawBelow10(Graphics2D g2, final int pp, final int qq) {

        MemoryModel memoryModel = GuiMain.theApp.memoryModel;
        int p = memoryModel.getP();
        int q = memoryModel.getQ();
        final int yy = this.getYpos() + 15;

        g2.drawRect(getXpos(), getYpos(), getWidth(), getHeight());

        Color c0 = g2.getColor();
        try {
            if (this.adrs == 0) {
                g2.setColor(GuiMain.P_COLOR);

                g2.drawString("P=" + p, getXpos() + 5, yy);
            } else if (this.adrs == 1) {
                g2.setColor(GuiMain.Q_COLOR);

                g2.drawString("Q=" + q, getXpos() + 5, yy);
            } else {
                throw new IllegalStateException();
            }
        } finally {
            g2.setColor(c0);
        }
    }

    public void drawOver10(Graphics2D g2, final int p, final int q) {

        final int yy = getYpos() + 15;

        if ((adrs % 10) == 0) {
            // アドレスを左に表示
            Color c0 = g2.getColor();
            try {
                g2.setColor(GuiMain.ADDRESS_COLOR);
                g2.drawString(String.valueOf(adrs), getXpos() - 20, yy);
            } finally {
                g2.setColor(c0);
            }
        }

        if (GuiMain.theApp.fillBackground && (mark == 0)) {

            Color c1 = g2.getColor();
            try {
                //   g2.setBackground(Solarized.BASE0);
                g2.setColor(Solarized.YELLOW);
                g2.fillRect(getXpos(), getYpos(), getWidth(), getHeight());
            } finally {
                g2.setColor(c1);
            }
        } else {
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
                    g2.drawRect(getXpos() - 2, getYpos() - 2, getWidth() + 4, getHeight() + 4);
                } finally {
                    g2.setColor(c2);
                }
            }

        }

        // 使用中マーク
        g2.drawLine(getXpos() + 15, getYpos(), getXpos() + 15, getYpos() + getHeight());
        g2.drawString(String.valueOf(getMark()), getXpos() + 2, yy);

        // 逆転ポインタの状態
        g2.drawLine(getXpos() + 40, getYpos(), getXpos() + 40, getYpos() + getHeight());
        g2.drawString(decodeStatus(), getXpos() + 17, yy);

        // car と cdr
        g2.drawLine(getXpos() + 68, getYpos(), getXpos() + 68, getYpos() + getHeight());
        g2.drawString(String.valueOf(getCarIndex()), getXpos() + 40 + 2, yy);
        g2.drawString(String.valueOf(getCdrIndex()), getXpos() + 68 + 2, yy);
    }

    String decodeStatus() {
        switch (this.status) {
            case 0:
                return "00";
            case 1:
                return "01";
            case 2:
                return "10";
        }
        return "??";
    }

    @Override
    public boolean isCell() {
        return true;
    }

    /**
     * @return the car
     */
    @Override
    public int getCarIndex() {
        return car;
    }

    /**
     * @param car the car to set
     */
    @Override
    public void setCarIndex(int car) {
        this.car = (short) car;
    }

    /**
     * @return the cdr
     */
    @Override
    public int getCdrIndex() {
        return cdr;
    }

    /**
     * @param cdr the cdr to set
     */
    @Override
    public void setCdrIndex(int cdr) {
        this.cdr = (short) cdr;
    }

    /**
     * @return the mark
     */
    @Override
    public short getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    @Override
    public void setMark(short mark) {
        if (mark < 0 || 1 < mark) {
            throw new IllegalArgumentException("Mark must be 0 or 1");
        }
        this.mark = mark;
    }

    /**
     * @return the status
     */
    @Override
    public short getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    @Override
    public void setStatus(short status) {
        if (status < 0 || 2 < status) {
            throw new IllegalArgumentException("Status must be 0 or 1 or 2");
        }
        this.status = status;
    }

    @Override
    public boolean isNil() {
        return false;
    }

}
