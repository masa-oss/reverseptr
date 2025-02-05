package jp.oops.clazz.rpgc;

import java.util.Objects;

/**
 *
 * @author hemmi
 */
public class Symbol {
    
    public static final Symbol NIL = new Symbol("nil");
    
    
    public final String name;
    
    public Symbol(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Symbol other = (Symbol) obj;
        return Objects.equals(this.name, other.name);
    }
    
}
