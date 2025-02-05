package jp.oops.clazz.rpgc.widget;

/**
 *
 * @author hemmi
 */
public interface LispObject {
    
    
    boolean isCell();
    
    boolean isNil();

    /**
     * @return the car
     */
    int getCarIndex();

    /**
     * @return the cdr
     */
    int getCdrIndex();

    /**
     * @return the mark
     */
    short getMark();

    /**
     * @return the status
     */
    short getStatus();

    /**
     * @param car the car to set
     */
    void setCarIndex(int car);

    /**
     * @param cdr the cdr to set
     */
    void setCdrIndex(int cdr);

    /**
     * @param mark the mark to set
     */
    void setMark(short mark);

    /**
     * @param status the status to set
     */
    void setStatus(short status);
    
}
