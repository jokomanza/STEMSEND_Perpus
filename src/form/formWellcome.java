/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import AppPackage.AnimationClass;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Luciver
 */
public class formWellcome extends javax.swing.JFrame {

    /**
     * Creates new form loading
     */
    public formWellcome() {
        initComponents();
        this.setLocationRelativeTo(null);
        waktu.start();
    }

    int time = 0;

    private void setAnimation() {
        AnimationClass ani = new AnimationClass();

        //kiri 
        ani.jLabelXLeft(kiri.getX(), -1000, 7, 1, kiri);

        //kanan
        ani.jLabelXRight(kanan.getX(), 1000, 6, 1, kanan);

        //bawah
        ani.jLabelYDown(bawah.getY(), 1000, 9, 1, bawah);

        //tengah
        ani.jLabelXRight(-500, 500, 3, 1, wellcome);

    }

    ActionListener taskPerformer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {

            time++;
            if (time == 1) {
                setAnimation();
                waktu.stop();
            }

        }
    };

    Timer waktu = new Timer(1000, taskPerformer);

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        kanan = new javax.swing.JLabel();
        bawah = new javax.swing.JLabel();
        kiri = new javax.swing.JLabel();
        wellcome = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(594, 340));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Path 2.png"))); // NOI18N
        getContentPane().add(kanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, 340));

        bawah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Path 3.png"))); // NOI18N
        getContentPane().add(bawah, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 600, 260));

        kiri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Path 1.png"))); // NOI18N
        getContentPane().add(kiri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 310));

        wellcome.setFont(new java.awt.Font("Lucida Handwriting", 1, 24)); // NOI18N
        wellcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wellcome.setText("Wellcome User");
        getContentPane().add(wellcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(-500, 140, 620, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formWellcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formWellcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formWellcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formWellcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formWellcome home = new formWellcome();
                home.setBackground(new Color(10, 10, 10, 0));
                home.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bawah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel kanan;
    private javax.swing.JLabel kiri;
    private javax.swing.JLabel wellcome;
    // End of variables declaration//GEN-END:variables
}