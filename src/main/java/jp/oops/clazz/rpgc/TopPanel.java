package jp.oops.clazz.rpgc;

import javax.swing.JOptionPane;
import jp.oops.clazz.rpgc.widget.LispObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hemmi
 */
public class TopPanel extends javax.swing.JPanel {

    private static final Logger LOG = LoggerFactory.getLogger(TopPanel.class);

    /**
     * Creates new form TopPanel
     */
    public TopPanel() {
        initComponents();

        this.jTextField1.setText("10");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 23));

        jButton1.setText("RESET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("STEP");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Root");

        jButton3.setText("print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton3)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addGap(26, 26, 26)
                .addComponent(jButton2)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel1)
                    .addComponent(jButton3))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GuiMain.theApp.algorithm.start();
        GuiMain.theApp.fillBackground = false;

        this.getParent().repaint();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String text = this.jTextField1.getText();

        int root;
        try {
            root = Integer.parseInt(text);
        } catch (RuntimeException re) {
            JOptionPane.showMessageDialog(this, "数値を入力してください", "title", JOptionPane.WARNING_MESSAGE);
            return;
        }

        GuiMain.theApp.algorithm.step(root);

        this.getParent().repaint();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        String text = this.jTextField1.getText();

        int root;
        try {
            root = Integer.parseInt(text);
        } catch (RuntimeException re) {
            JOptionPane.showMessageDialog(this, "数値を入力してください", "title", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ReversePointerMethod rp = GuiMain.theApp.algorithm;
        int phase = rp.getPhase();
        if (!(phase == 0  || phase == 3 ) ) {
            JOptionPane.showMessageDialog(this, "GC途中では、表示できません", "title", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MemoryModel model = GuiMain.theApp.memoryModel;
        Printer pr = new Printer(model);
        LispObject lispObject = model.getLispObject(root);
        String sexp = pr.print(lispObject);
        LOG.info("form={}", sexp);
        
        PrintDialog dlg = new PrintDialog(GuiMain.theApp, true);
        dlg.setText(sexp);
        dlg.setVisible(true);
        
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
