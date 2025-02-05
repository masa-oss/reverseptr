package jp.oops.clazz.rpgc;

import java.util.List;
import java.util.ArrayList;
import jp.oops.clazz.rpgc.widget.AbstractWidget;
import jp.oops.clazz.rpgc.widget.CellWidget;
import jp.oops.clazz.rpgc.widget.LispObject;
import jp.oops.clazz.rpgc.widget.NumOrSymbolWidget;

/**
 * <code>
 * アドレスは以下の通り
 *
 *  10 - 99 セル(cons)
 * 100 -    数字 or Symbol
 * </code>
 *
 */
public class MemoryModel {

    ArrayList<CellWidget> widgets = new ArrayList<>();
    ArrayList<NumOrSymbolWidget> numOrSyms = new ArrayList<>();
    // １００個のセルを確保
    static final int NUM_OF_CELLS = 100;

    public MemoryModel() {

        for (short i = 0; i < NUM_OF_CELLS; i++) {
            /*
            double d1 = Math.random() * 100;
            short car =  (short)(50 +  ((short) d1));
            double d2 = Math.random() * 100;
            short cdr = (short)(50 +   d2);
             */
            short init = 100;
            // セルをアロケートする
            widgets.add(new CellWidget(i, init, init));
        }
    }

    public void setCellValue(int idx, int car, int cdr) {

        setCellCar(idx, car);
        setCellCdr(idx, cdr);
    }

    public void setCellCar(int idx, int value) {
        widgets.get(idx).setCarIndex(value);
    }

    public void setCellCdr(int idx, int value) {
        widgets.get(idx).setCdrIndex(value);
    }

    public int getP() {
        return widgets.get(0).getCarIndex();
    }

    public void setP(int value) {
        widgets.get(0).setCarIndex(value);
    }

    public int getQ() {
        return widgets.get(1).getCarIndex();
    }

    public void setQ(int value) {
        widgets.get(1).setCarIndex(value);
    }

    public void addNumOrSymbol(NumOrSymbol numOrSym) {

        int count = numOrSyms.size();
        NumOrSymbolWidget wi = new NumOrSymbolWidget((short) (count + 100), numOrSym);
        numOrSyms.add(wi);
    }

    public int numOrSymbolSize() {
        return numOrSyms.size();
    }

    public NumOrSymbolWidget getNumOrSymbol(int idx) {
        int n = idx - 100;
        return numOrSyms.get(n);
    }

    public LispObject getLispObject(int idx) {

        if (idx < 100) {
            return widgets.get(idx);
        }
        int n = idx - 100;
        NumOrSymbolWidget get = numOrSyms.get(n);

        return get.getNumOrSymbol();
    }
    
    public int cellSize() {
        return widgets.size();
    }

    public CellWidget getMemory(int idx) {
        return widgets.get(idx);
    }

    public void clearFlags() {

        for (short i = 0; i < NUM_OF_CELLS; i++) {
            widgets.get(i).setMark((short) 0); // GCマークを 0 にする
            widgets.get(i).setStatus(ReversePointerMethod.ST_NONE); //状態を無しにする
        }
    }

}
