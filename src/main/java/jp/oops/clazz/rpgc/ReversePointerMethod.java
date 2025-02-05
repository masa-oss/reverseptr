package jp.oops.clazz.rpgc;

import java.awt.Point;
import jp.oops.clazz.rpgc.widget.CellWidget;
import jp.oops.clazz.rpgc.widget.LispObject;

/**
 *
 * @author hemmi
 */
public class ReversePointerMethod {

    public static final short ST_NONE = 0;
    public static final short ST_CAR_HAS_BEEN_REWRITTEN = 2;
    public static final short ST_CDR_HAS_BEEN_REWRITTEN = 1;

    private MemoryModel memoryModel;

    // (default)表示が見ている
    private int phase = 0;

    private String explanation = "";

    public void start() {
        this.phase = 0;

    }

    private void clearLine() {
        
        CellPanel panel = GuiMain.theApp.panel;
        panel.setLine(false, null, null);
    }
    private void setLine(int p, int q, String carOrCdr) {

        LispObject pLo = memoryModel.getLispObject(p);
        LispObject qLo = memoryModel.getLispObject(q);
        
        CellWidget memory;
        if (pLo instanceof CellWidget) {
            memory =(CellWidget) pLo;
        } else {
            return;
        }
        CellWidget memory2;
        if (qLo instanceof CellWidget) {
            memory2 =(CellWidget) qLo;
        } else {
            return;
        }
        
        
        int startX = memory.getXpos();
        int startY = memory.getYpos() + (memory.getHeight() / 2);
        if ("car".equals(carOrCdr)) {
            startX = startX + 40 + 14;
        } else if ("cdr".equals(carOrCdr)) {
            startX = startX + 68 + 14;
        } else {
            throw new IllegalArgumentException();
        }
        Point startp = new Point(startX, startY);
        
        int endX = memory2.getXpos() + (memory2.getWidth() / 2);
        int endY = memory2.getYpos() + (memory2.getHeight() / 2);
        Point endp = new Point(endX, endY);
        
        CellPanel panel = GuiMain.theApp.panel;
        panel.setLine(true, startp, endp);
    }

    public void step(int root) {

        clearLine();
        
        if (getPhase() == 0) {
            GuiMain.theApp.fillBackground = false;

            memoryModel.clearFlags();

            explanation = "GCマークを0にしました\n状態を00にしました";

            phase = 1;

        } else if (getPhase() == 1) {

            memoryModel.setP(root);
            memoryModel.setQ(0);
            explanation = "P ← Root欄\nQ ← 0";

            phase = 2;

        } else if (getPhase() == 2) {

            final int p = memoryModel.getP();

            if (p == 0) {
                phase = 3;
                explanation = "p=0 なのでフェーズを進める";
                return;
            }

            final LispObject lo = memoryModel.getLispObject(p);

            if (lo.isCell()) {
                /*
                if (lo.getMark() != 0) {

                    int q = memoryModel.getQ();
                    memoryModel.setP(q);
                    memoryModel.setQ(p);
                    explanation = "GCマーク済みの為、PとQを入れ替えた";

                    return;
                }
                 */
                short st = lo.getStatus();
                if (st == ST_NONE) {

                    int newP = lo.getCarIndex();
                    int q = memoryModel.getQ();
                    lo.setMark((short) 1);
                    lo.setCarIndex(q);
                    lo.setStatus(ST_CAR_HAS_BEEN_REWRITTEN);

                    memoryModel.setP(newP);
                    memoryModel.setQ(p);

                    explanation = "P ← car\nsetCar ← Q\n状態 ← 10";

                    setLine(p, q, "car");   // 線を描画

                } else if (st == ST_CAR_HAS_BEEN_REWRITTEN) {

                    int newCdr = lo.getCarIndex();
                    int q = memoryModel.getQ();

                    int newP = lo.getCdrIndex();
                    lo.setCarIndex(q);
                    lo.setCdrIndex(newCdr);
                    lo.setStatus(ST_CDR_HAS_BEEN_REWRITTEN);

                    memoryModel.setP(newP);
                    memoryModel.setQ(p);
                    explanation = "p ← cdr\ntemp ← car\nsetCar ← Q\nsetCdr ← temp\n状態 ← 01";
                    
                    setLine(p, newCdr, "cdr");   // 線を描画

                } else if (st == ST_CDR_HAS_BEEN_REWRITTEN) {

                    int newP = lo.getCdrIndex();

                    int q = memoryModel.getQ();
                    lo.setCdrIndex(q);

                    memoryModel.setP(newP);
                    memoryModel.setQ(p);
                    lo.setStatus(ST_NONE);
                    explanation = "p ← cdr\nsetCdr ← Q\n状態 ← 00";

                    setLine(p, q, "cdr");   // 線を描画
                } else {
                    throw new IllegalStateException();
                }
                return;

            } else {
                // セルでないので、PとQを入れ替える
                int q = memoryModel.getQ();
                memoryModel.setP(q);
                memoryModel.setQ(p);
                explanation = "セルでないので、PとQを入れ替えた";
            }

        } else {

            GuiMain.theApp.fillBackground = true;

            explanation = "マークされていないのを回収";

        }

    }

    public String getExplanation() {
        return explanation;
    }

    /**
     * @param memoryModel the memoryModel to set
     */
    public void setMemoryModel(MemoryModel memoryModel) {
        this.memoryModel = memoryModel;
    }

    /**
     * @return the phase
     */
    public int getPhase() {
        return phase;
    }

}
