package form;

import java.awt.Cursor;
import java.awt.Frame;
import static java.awt.Frame.WAIT_CURSOR;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class formEditBukuLoop extends javax.swing.JDialog {

    /**
     * Creates new form fromEditBuku
     */
    public formEditBukuLoop(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        tampilTblBuku();
        pilihIdBuku();
        bukaKunci();
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        //lblGambar.setIcon();
    }
    private DefaultTableModel tabmode;

    private void tampilTblBuku() {
        Object[] baris = {"Id Buku", "Judul Buku", "Pengarang", "Penerbit", "Tangagl Terbit", "stok"};
        tabmode = new DefaultTableModel(null, baris);
        tblBuku.setModel(tabmode);
        try {
            Connection con = new koneksi().getConnection();
            String Sql = "select * from tblbuku";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(Sql);
            while (result.next()) {
                String idBuku = result.getString("idBuku");
                String nama_buku = result.getString("judulBuku");
                String Pengarang = result.getString("pengarang");
                String Penerbit = result.getString("penerbit");
                String TanggalTerbit = result.getString("tanggalterbit");
                String Stok = result.getString("stok");
                String[] nilai = {idBuku, nama_buku, Pengarang, Penerbit, TanggalTerbit, Stok};
                tabmode.addRow(nilai);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void kliktable() {
        //idBuku | judulBuku | pengarang       | penerbit | tanggalTerbit | stok
        int baris = tblBuku.getSelectedRow();
        txtIdBuku.setText((String) tblBuku.getValueAt(baris, 0));
        txtJudulBuku.setText((String) tblBuku.getValueAt(baris, 1));
        txtPengarang.setText((String) tblBuku.getValueAt(baris, 2));
        txtPenerbit.setText((String) tblBuku.getValueAt(baris, 3));
        txtTanggalTerbit.setDateFormatString((String) tblBuku.getValueAt(baris, 4));
        txtStok.setText((String) tblBuku.getValueAt(baris, 5));
        btnSimpan.setEnabled(false);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        bukaKunci();
    }

    public void simpan() {
        int i = 0;
        int ulang = Integer.parseInt(txtJumlahLoop.getText());
        for (; i <= ulang; i++) {
            pilihIdBuku();
            //idBuku | judulBuku | pengarang       | penerbit | tanggalTerbit | stok
            String idBuku = "", judulBuku = "", pengarang = "", penerbit = "", tanggalTerbit = "", stok = "";
            idBuku = String.valueOf(max);
            judulBuku = txtJudulBuku.getText() + i;
            pengarang = txtPengarang.getText();
            penerbit = txtPenerbit.getText();
            java.util.Date tanggal_terbit = txtTanggalTerbit.getDate();
            java.sql.Date tanggal_Terbit = new java.sql.Date(tanggal_terbit.getTime());
            stok = txtStok.getText();
            Connection con;
            try {
                con = new koneksi().getConnection();
                String sql = "insert into tblBuku values (?,?,?,?,?,?)";
                PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
                stat.setString(1, idBuku);
                stat.setString(2, judulBuku);
                stat.setString(3, pengarang);
                stat.setString(4, penerbit);
                stat.setDate(5, tanggal_Terbit);
                stat.setString(6, stok);
                stat.executeUpdate();
                //  JOptionPane.showMessageDialog(null, "Menyimpan data BERHASIL","Informasi", JOptionPane.INFORMATION_MESSAGE);
                tampilTblBuku();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Menyimpan data GAGAL\n" + e, "Informasi\n", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Menyimpan " + i + " Data Berhasil");
    }

    private void batal() {
        kunci();
    }

    private void bukaKunci() {
//        txtIdBuku.setEnabled(true);
        txtJudulBuku.setEnabled(true);
        txtPengarang.setEnabled(true);
        txtPenerbit.setEnabled(true);
        txtStok.setEnabled(true);
        txtTanggalTerbit.setEnabled(true);
    }

    private void kunci() {
        txtIdBuku.setEnabled(false);
        txtJudulBuku.setEnabled(false);
        txtPengarang.setEnabled(false);
        txtPenerbit.setEnabled(false);
        txtStok.setEnabled(false);
        txtTanggalTerbit.setEnabled(false);
    }

    private void bersih() {
        txtIdBuku.setText("");
        txtJudulBuku.setText("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        txtStok.setText("");
    }
    int max = 0;

    private void pilihIdBuku() {
        try {
            Connection con = new koneksi().getConnection();
            String sql = "select max(idBuku) from tblBuku;";
            java.sql.Statement statement2 = con.createStatement();
            java.sql.ResultSet result2 = statement2.executeQuery(sql);
            while (result2.next()) {
                max = result2.getInt("max(idBuku)") + 1;
                txtIdBuku.setText(String.valueOf(max));
            }
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        c = new javax.swing.JLabel();
        txtPenerbit = new javax.swing.JTextField();
        f = new javax.swing.JLabel();
        l1 = new javax.swing.JLabel();
        j = new javax.swing.JLabel();
        txtTanggalTerbit = new com.toedter.calendar.JDateChooser();
        l = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        txtIdBuku = new javax.swing.JTextField();
        txtJudulBuku = new javax.swing.JTextField();
        txtPengarang = new javax.swing.JTextField();
        a = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblGambar = new javax.swing.JLabel();
        txtJumlahLoop = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM ENTRY BUKU");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        c.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        c.setText("Judul Buku");

        txtPenerbit.setEnabled(false);

        f.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        f.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f.setText("Pengarang");

        l1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l1.setText("Penerbit");

        j.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        j.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j.setText("Stok");

        txtTanggalTerbit.setEnabled(false);

        l.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        l.setText("Tanggal Terbit");

        txtStok.setEnabled(false);

        txtIdBuku.setEnabled(false);
        txtIdBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBukuActionPerformed(evt);
            }
        });

        txtJudulBuku.setEnabled(false);

        txtPengarang.setEnabled(false);

        a.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        a.setText("ID Buku");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGambar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(c, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(f, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPengarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJudulBuku, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtJumlahLoop, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(j)
                            .addComponent(l, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l1))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTanggalTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnReset)
                    .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a)
                    .addComponent(txtJumlahLoop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(c)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(f)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(l)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(l1)
                                        .addGap(34, 34, 34)))
                                .addGap(18, 18, 18)
                                .addComponent(j))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTanggalTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tblBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBuku);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    //reset 
    public void reset() {
        txtIdBuku.setText("");
        txtJudulBuku.setText("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        txtStok.setText("");
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSimpan.setEnabled(true);
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //idBuku | judulBuku | pengarang | penerbit | tanggalTerbit | stok
        String idBuku = "", judulBuku = "", pengarang = "", penerbit = "", tanggalTerbit = "", stok = "";
        idBuku = txtIdBuku.getText();
        judulBuku = txtJudulBuku.getText();
        pengarang = txtPengarang.getText();
        penerbit = txtPenerbit.getText();
        tanggalTerbit = (String) txtTanggalTerbit.getDateFormatString();
        stok = txtStok.getText();
        String sql = "update tblBuku set judulBuku=?, pengarang=?, penerbit=?, tanggalTerbit=?, stok=? where idBuku='" + idBuku + "'";
        try {
            Connection con = new koneksi().getConnection();
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, judulBuku);
            stat.setString(2, pengarang);
            stat.setString(3, penerbit);
            stat.setString(4, tanggalTerbit);
            stat.setString(5, stok);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data BERHASIL diUpdate");
            tampilTblBuku();
            reset();
            kunci();
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Data GAGAL diUpdate\n" + se);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int baris = tblBuku.getSelectedRow();
        String idBuku = tabmode.getValueAt(baris, 0).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data Mahasiswa \n BP :" + idBuku + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                //panggil method koneksi
                Connection con = new koneksi().getConnection();
                String sql = "delete from tblBuku where idbuku ='" + idBuku + "'";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                tampilTblBuku();
                reset();
                pilihIdBuku();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di hapus\n" + e);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBukuMouseClicked
        kliktable();
    }//GEN-LAST:event_tblBukuMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtIdBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBukuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formEditBukuLoop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formEditBukuLoop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formEditBukuLoop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formEditBukuLoop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                try {
                    formEditBukuLoop dialog = new formEditBukuLoop(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(formEditBukuLoop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel c;
    private javax.swing.JLabel f;
    private javax.swing.JLabel j;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel lblGambar;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtJumlahLoop;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextField txtStok;
    private com.toedter.calendar.JDateChooser txtTanggalTerbit;
    // End of variables declaration//GEN-END:variables
}
