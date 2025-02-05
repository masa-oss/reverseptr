package jp.oops.clazz.rpgc;

import java.io.PrintWriter;
import java.io.StringWriter;
import jp.oops.clazz.rpgc.widget.LispObject;

/**
 *
 * @author hemmi
 */
public class Printer {

    final MemoryModel model;

    public Printer(MemoryModel model) {
        this.model = model;
    }

    public String print(LispObject list) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        printDepth(pw, list, 10, 10);
        pw.flush();
        return sw.toString();
    }

    void printDepth(final PrintWriter pw, final LispObject object, int carLimit, int cdrLimit) {
        if (carLimit == 0 || cdrLimit == 0) {
            pw.print(" ...");
            return;
        }

        if (!object.isCell()) {
            NumOrSymbol nos = (NumOrSymbol) object;
            pw.print(nos.decode());
        } else {
            LispObject obj = object;
            // Cell
            pw.print("(");
            for (;;) {
                if (cdrLimit == 0) {
                    pw.print(" ...");
                    break;
                }

                LispObject carObject = model.getLispObject(obj.getCarIndex());
                printDepth(pw, carObject, carLimit - 1, cdrLimit);

                LispObject cdrObject = model.getLispObject(obj.getCdrIndex());

                if (!cdrObject.isCell()) {
                    obj = cdrObject;

                    if (obj.isNil()) {
                        break;
                    }
                    if (obj instanceof NumOrSymbol) {
                        pw.print(" . ");
                        NumOrSymbol nos2 = (NumOrSymbol) obj;
                        pw.print(nos2.decode());
                    }
                    break;
                }
                obj = cdrObject;
                cdrLimit--;
                pw.print(" ");
            }

            pw.print(")");
        }

    }

}
