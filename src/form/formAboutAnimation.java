package form;

import AppPackage.AnimationClass;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class formAboutAnimation extends javax.swing.JDialog {

    public formAboutAnimation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        animasi();
    }

    AnimationClass ani = new AnimationClass();

    private void animasi() {

        ani.jLabelXRight(lblTitle.getX(), 0, 1, 2, lblTitle);

//        ani.jXRight(panelSiswa1.getX(), 0, 1, 5, panelSiswa1);
        ani.jLabelYUp(lblImage1.getY(), 11, 20, 1, lblImage1);
        ani.jLabelYUp(lblImage2.getY(), 11, 19, 1, lblImage2);
        ani.jLabelYUp(lblImage3.getY(), 11, 18, 1, lblImage3);
        ani.jLabelYUp(lblImage4.getY(), 11, 17, 1, lblImage4);
        ani.jLabelYUp(lblImage5.getY(), 11, 16, 1, lblImage5);
        ani.jLabelYUp(lblImage6.getY(), 11, 15, 1, lblImage6);

//        ani.jLabelYDown(lblTitle.getY(), 15, 10, 1, lblTitle);
        ani.jLabelYUp(lblNamaSiswa1.getY(), 111, 30, 1, lblNamaSiswa1);
        ani.jLabelYUp(lblPangkat1.getY(), 151, 30, 1, lblPangkat1);

        ani.jLabelYUp(lblNamaSiswa2.getY(), 111, 30, 1, lblNamaSiswa2);
        ani.jLabelYUp(lblPangkat2.getY(), 151, 30, 1, lblPangkat2);

        ani.jLabelYUp(lblNamaSiswa3.getY(), 111, 30, 1, lblNamaSiswa3);
        ani.jLabelYUp(lblPangkat3.getY(), 151, 30, 1, lblPangkat3);

//        ani.jLabelYDown(lblTitle.getY(), 11, 10, 1, lblTitle);
        ani.jLabelYUp(lblNamaSiswa4.getY(), 111, 30, 1, lblNamaSiswa4);
        ani.jLabelYUp(lblPangkat4.getY(), 151, 30, 1, lblPangkat4);

        ani.jLabelYUp(lblNamaSiswa5.getY(), 111, 30, 1, lblNamaSiswa5);
        ani.jLabelYUp(lblPangkat5.getY(), 151, 30, 1, lblPangkat5);

        ani.jLabelYUp(lblNamaSiswa6.getY(), 111, 30, 1, lblNamaSiswa6);
        ani.jLabelYUp(lblPangkat6.getY(), 151, 30, 1, lblPangkat6);

        ani.jLabelXLeft(lblTitle.getX(), 0, 1, 5, lblTitle);

    }

    private void mouseEntered(JLabel image, JLabel name) {
        ani.jLabelYUp(image.getY(), 5, 3, 2, image);
        ani.jLabelYUp(name.getY(), 100, 2, 1, name);
    }

    private void mouseExited(JLabel image, JLabel name) {
        ani.jLabelYDown(image.getY(), 11, 3, 2, image);
        ani.jLabelYDown(name.getY(), 111, 2, 1, name);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelSiswa1 = new javax.swing.JPanel();
        lblImage1 = new javax.swing.JLabel();
        lblNamaSiswa1 = new javax.swing.JLabel();
        lblPangkat1 = new javax.swing.JLabel();
        lblGaris1 = new javax.swing.JSeparator();
        panelSiswa2 = new javax.swing.JPanel();
        lblImage2 = new javax.swing.JLabel();
        lblNamaSiswa2 = new javax.swing.JLabel();
        lblPangkat2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        panelSiswa3 = new javax.swing.JPanel();
        lblImage3 = new javax.swing.JLabel();
        lblNamaSiswa3 = new javax.swing.JLabel();
        lblPangkat3 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        panel4 = new javax.swing.JPanel();
        lblImage4 = new javax.swing.JLabel();
        lblNamaSiswa4 = new javax.swing.JLabel();
        lblPangkat4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        panel6 = new javax.swing.JPanel();
        lblImage6 = new javax.swing.JLabel();
        lblNamaSiswa6 = new javax.swing.JLabel();
        lblPangkat6 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        panel5 = new javax.swing.JPanel();
        lblImage5 = new javax.swing.JLabel();
        lblNamaSiswa5 = new javax.swing.JLabel();
        lblPangkat5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About We");
        setBackground(new java.awt.Color(0, 204, 204));
        setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/gambar/icons8_Google_48px_1.png"));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSiswa1.setBackground(new java.awt.Color(51, 153, 255));
        panelSiswa1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelSiswa1.setPreferredSize(new java.awt.Dimension(200, 200));
        panelSiswa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSiswa1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSiswa1MouseExited(evt);
            }
        });
        panelSiswa1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Administrator_Male_70px.png"))); // NOI18N
        panelSiswa1.add(lblImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, -1));

        lblNamaSiswa1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblNamaSiswa1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaSiswa1.setText("Joko Supriyanto");
        panelSiswa1.add(lblNamaSiswa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        lblPangkat1.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        lblPangkat1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPangkat1.setText("as Koordinator");
        panelSiswa1.add(lblPangkat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, -1));

        lblGaris1.setBackground(new java.awt.Color(0, 0, 0));
        lblGaris1.setForeground(new java.awt.Color(0, 0, 0));
        panelSiswa1.add(lblGaris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 180, 6));

        jPanel2.add(panelSiswa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 58, -1, 169));

        panelSiswa2.setBackground(new java.awt.Color(51, 153, 255));
        panelSiswa2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelSiswa2.setPreferredSize(new java.awt.Dimension(200, 200));
        panelSiswa2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSiswa2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSiswa2MouseExited(evt);
            }
        });
        panelSiswa2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Graduate_70px.png"))); // NOI18N
        panelSiswa2.add(lblImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, -1));

        lblNamaSiswa2.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblNamaSiswa2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaSiswa2.setText("Alifia Latifah");
        panelSiswa2.add(lblNamaSiswa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        lblPangkat2.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        lblPangkat2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPangkat2.setText("as Anggota");
        panelSiswa2.add(lblPangkat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        panelSiswa2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 180, 6));

        jPanel2.add(panelSiswa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 117, -1, 169));

        panelSiswa3.setBackground(new java.awt.Color(51, 153, 255));
        panelSiswa3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelSiswa3.setPreferredSize(new java.awt.Dimension(200, 200));
        panelSiswa3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSiswa3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSiswa3MouseExited(evt);
            }
        });
        panelSiswa3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Graduate_70px.png"))); // NOI18N
        panelSiswa3.add(lblImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, -1));

        lblNamaSiswa3.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblNamaSiswa3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaSiswa3.setText("Euis Desy Fitriana");
        panelSiswa3.add(lblNamaSiswa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        lblPangkat3.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        lblPangkat3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPangkat3.setText("as Anggota");
        panelSiswa3.add(lblPangkat3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, -1));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        panelSiswa3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 180, 6));

        jPanel2.add(panelSiswa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 117, -1, 169));

        panel4.setBackground(new java.awt.Color(51, 153, 255));
        panel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel4.setPreferredSize(new java.awt.Dimension(200, 200));
        panel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel4MouseExited(evt);
            }
        });
        panel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Graduate_70px.png"))); // NOI18N
        panel4.add(lblImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, -1));

        lblNamaSiswa4.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblNamaSiswa4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaSiswa4.setText("Fatimah Nur Septiani");
        panel4.add(lblNamaSiswa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        lblPangkat4.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        lblPangkat4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPangkat4.setText("as Anggota");
        panel4.add(lblPangkat4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, -1));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        panel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 180, 6));

        jPanel2.add(panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 337, -1, 169));

        panel6.setBackground(new java.awt.Color(51, 153, 255));
        panel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel6.setPreferredSize(new java.awt.Dimension(200, 200));
        panel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel6MouseExited(evt);
            }
        });
        panel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Graduate_70px.png"))); // NOI18N
        panel6.add(lblImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, -1));

        lblNamaSiswa6.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblNamaSiswa6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaSiswa6.setText("Unknown");
        panel6.add(lblNamaSiswa6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        lblPangkat6.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        lblPangkat6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPangkat6.setText("as Anggota");
        panel6.add(lblPangkat6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, -1));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        panel6.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 180, 6));

        jPanel2.add(panel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 337, -1, 169));

        panel5.setBackground(new java.awt.Color(51, 153, 255));
        panel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel5.setPreferredSize(new java.awt.Dimension(200, 200));
        panel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel5MouseExited(evt);
            }
        });
        panel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Student_Male_70px_1.png"))); // NOI18N
        panel5.add(lblImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, -1));

        lblNamaSiswa5.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblNamaSiswa5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaSiswa5.setText("Johan Budi Pangestu");
        panel5.add(lblNamaSiswa5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        lblPangkat5.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        lblPangkat5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPangkat5.setText("as Anggota");
        panel5.add(lblPangkat5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        panel5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 180, 6));

        jPanel2.add(panel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 305, -1, 169));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Kelompok Kami");
        jPanel2.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(-500, 10, 820, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ani.jLabelXRight(lblTitle.getX(), 500, 1, 100, lblTitle);
    }//GEN-LAST:event_formWindowClosing

    private void panelSiswa1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSiswa1MouseEntered
        mouseEntered(lblImage1, lblNamaSiswa1);
    }//GEN-LAST:event_panelSiswa1MouseEntered

    private void panelSiswa1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSiswa1MouseExited
        mouseExited(lblImage1, lblNamaSiswa1);
    }//GEN-LAST:event_panelSiswa1MouseExited

    private void panelSiswa2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSiswa2MouseEntered
        mouseEntered(lblImage2, lblNamaSiswa2);
    }//GEN-LAST:event_panelSiswa2MouseEntered

    private void panelSiswa2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSiswa2MouseExited
        mouseExited(lblImage2, lblNamaSiswa2);
    }//GEN-LAST:event_panelSiswa2MouseExited

    private void panelSiswa3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSiswa3MouseEntered
        mouseEntered(lblImage3, lblNamaSiswa3);
    }//GEN-LAST:event_panelSiswa3MouseEntered

    private void panelSiswa3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSiswa3MouseExited
        mouseExited(lblImage3, lblNamaSiswa3);
    }//GEN-LAST:event_panelSiswa3MouseExited

    private void panel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel4MouseEntered
        mouseEntered(lblImage4, lblNamaSiswa4);
    }//GEN-LAST:event_panel4MouseEntered

    private void panel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel4MouseExited
        mouseExited(lblImage4, lblNamaSiswa4);
    }//GEN-LAST:event_panel4MouseExited

    private void panel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel5MouseEntered
        mouseEntered(lblImage5, lblNamaSiswa5);
    }//GEN-LAST:event_panel5MouseEntered

    private void panel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel5MouseExited
        mouseExited(lblImage5, lblNamaSiswa5);
    }//GEN-LAST:event_panel5MouseExited

    private void panel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel6MouseEntered
        mouseEntered(lblImage6, lblNamaSiswa6);
    }//GEN-LAST:event_panel6MouseEntered

    private void panel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel6MouseExited
        mouseExited(lblImage6, lblNamaSiswa6);
    }//GEN-LAST:event_panel6MouseExited

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formAboutAnimation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formAboutAnimation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formAboutAnimation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formAboutAnimation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formAboutAnimation dialog = new formAboutAnimation(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {

                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator lblGaris1;
    private javax.swing.JLabel lblImage1;
    private javax.swing.JLabel lblImage2;
    private javax.swing.JLabel lblImage3;
    private javax.swing.JLabel lblImage4;
    private javax.swing.JLabel lblImage5;
    private javax.swing.JLabel lblImage6;
    private javax.swing.JLabel lblNamaSiswa1;
    private javax.swing.JLabel lblNamaSiswa2;
    private javax.swing.JLabel lblNamaSiswa3;
    private javax.swing.JLabel lblNamaSiswa4;
    private javax.swing.JLabel lblNamaSiswa5;
    private javax.swing.JLabel lblNamaSiswa6;
    private javax.swing.JLabel lblPangkat1;
    private javax.swing.JLabel lblPangkat2;
    private javax.swing.JLabel lblPangkat3;
    private javax.swing.JLabel lblPangkat4;
    private javax.swing.JLabel lblPangkat5;
    private javax.swing.JLabel lblPangkat6;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panelSiswa1;
    private javax.swing.JPanel panelSiswa2;
    private javax.swing.JPanel panelSiswa3;
    // End of variables declaration//GEN-END:variables
}
