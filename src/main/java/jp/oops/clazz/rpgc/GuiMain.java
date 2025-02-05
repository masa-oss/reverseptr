package jp.oops.clazz.rpgc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hemmi
 */
public class GuiMain extends JFrame {

    private static final Logger LOG = LoggerFactory.getLogger(GuiMain.class);

    final ReversePointerMethod algorithm = new ReversePointerMethod();
    final CellPanel panel = new CellPanel();
    final TopPanel topPanel = new TopPanel();
    final ExplainPanel explainPanel = new ExplainPanel();
    public final MemoryModel memoryModel = new MemoryModel();
    final IllustratedPanel illust = new IllustratedPanel();
    final JPanel leftLayout = new JPanel();

    public boolean fillBackground = false;
    
    public static final Color ADDRESS_COLOR = Solarized.BLUE;
    
    public static final Color P_COLOR = Solarized.RED;
    public static final Color Q_COLOR = Solarized.CYAN;

    
    public GuiMain() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 600);
//        this.addWindowListener(new WindowLogListener());
        setTitle("GUI Main");
        initNumOrSymbol();
        initCells();

        illust.setPreferredSize(new Dimension(150, 150));
        explainPanel.setPreferredSize(new Dimension(250, 300));


        algorithm.setMemoryModel(memoryModel);

        leftLayout.setLayout(new BoxLayout(leftLayout, BoxLayout.Y_AXIS));
        leftLayout.add(illust);
        leftLayout.add(explainPanel);

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(leftLayout, BorderLayout.WEST);

    }

    private void initNumOrSymbol() {

        memoryModel.addNumOrSymbol(new NumOrSymbol(  Symbol.NIL   )); // 100
        memoryModel.addNumOrSymbol(new NumOrSymbol(new Symbol("t")));   // 101
        memoryModel.addNumOrSymbol(new NumOrSymbol(new Symbol("quote")));   // 102
        memoryModel.addNumOrSymbol(new NumOrSymbol(new Symbol("list")));   // 103
        memoryModel.addNumOrSymbol(new NumOrSymbol(new Symbol("a")));   // 104
        memoryModel.addNumOrSymbol(new NumOrSymbol(new Symbol("b")));   // 105
        memoryModel.addNumOrSymbol(new NumOrSymbol(new Symbol("c")));   // 106  
        memoryModel.addNumOrSymbol(new NumOrSymbol(new Symbol("let")));   // 107
        memoryModel.addNumOrSymbol(new NumOrSymbol(Integer.valueOf(1))); // 108
        memoryModel.addNumOrSymbol(new NumOrSymbol(Integer.valueOf(2))); // 109
        memoryModel.addNumOrSymbol(new NumOrSymbol(Integer.valueOf(3))); // 110
    }
    
    private void initCells() {
        
        //    (list (quote a) b)
        memoryModel.setCellValue(10, 103, 20);  // Root

        //    (let ((a 1) (b 2)) (list a b))
        memoryModel.setCellValue(11, 107, 30);  // Root
        
        //    ((a . 1) (b . 2) (c . 3))
        memoryModel.setCellValue(12, 40, 41);  // Root

        //    ((a  1) (c  2 3))
        memoryModel.setCellValue(13, 50, 55);  // Root

        
        //    (list (quote a) b)
        memoryModel.setCellValue(20, 21, 23);
        memoryModel.setCellValue(21, 102, 22);
        memoryModel.setCellValue(22, 104, 100);
        memoryModel.setCellValue(23, 105, 100);
        

        //    (let ((a 1) (b 2)) (list a b))
        memoryModel.setCellValue(25, 103, 26);
        memoryModel.setCellValue(26, 104, 27);
        memoryModel.setCellValue(27, 105, 100);


        memoryModel.setCellValue(30, 32, 31);
        memoryModel.setCellValue(31, 25, 100);   // car=(list a b)
        memoryModel.setCellValue(32, 34, 33);
        memoryModel.setCellValue(33, 36, 100);
        memoryModel.setCellValue(34, 104, 35);
        memoryModel.setCellValue(35, 108, 100);
        memoryModel.setCellValue(36, 105, 37);
        memoryModel.setCellValue(37, 109, 100);

        //    ((a . 1) (b . 2) (c . 3))
        memoryModel.setCellValue(40, 104, 108);
        memoryModel.setCellValue(41, 42, 43);
        memoryModel.setCellValue(42, 105, 109);
        memoryModel.setCellValue(43, 44, 100);
        memoryModel.setCellValue(44, 106, 110);

        //    ((a  1) (c  2 3))
        memoryModel.setCellValue(50, 104, 51);
        memoryModel.setCellValue(51, 108, 100);
        memoryModel.setCellValue(52, 106, 53);
        memoryModel.setCellValue(53, 109, 54);
        memoryModel.setCellValue(54, 110, 100);
        memoryModel.setCellValue(55, 52, 100);
    }
    
    

    public static GuiMain theApp;

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            LOG.error("Exception", ex);
            return;
        }

        theApp = new GuiMain();
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            theApp.setVisible(true);
        });

    }

}
