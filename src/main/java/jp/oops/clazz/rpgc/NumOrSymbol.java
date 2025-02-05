package jp.oops.clazz.rpgc;

import jp.oops.clazz.rpgc.widget.LispObject;

/**
 *
 * @author hemmi
 */
public class NumOrSymbol implements LispObject {


    public final Object object;

    public NumOrSymbol(Object obj) {
        if (obj == null) throw new NullPointerException();
        this.object = obj;
    }

    @Override
    public boolean isNil() {
        
        if (object instanceof Symbol) {
            
            return ( Symbol.NIL == object  );
            
        }
        return false;
    }
    
    
    
    @Override
    public boolean isCell() {
        return false;
    }

    @Override
    public int getCarIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCdrIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public short getMark() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public short getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCarIndex(int car) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCdrIndex(int cdr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMark(short mark) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setStatus(short status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String decode() {

        if (object instanceof Number) {
            Number num = (Number) object;
            return num.toString();
        }

        Symbol sym = (Symbol) object;
        return sym.name;
    }

}
