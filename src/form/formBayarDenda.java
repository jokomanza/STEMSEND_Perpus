/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import koneksi.koneksi;
import java.sql.*;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luciver
 */
public class formBayarDenda extends javax.swing.JDialog {

    /**
     * Creates new form formDetailDenda
     *
     * @param parent
     * @param modal
     * @param id
     */
    public formBayarDenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tampilTblDenda();
        isiComboBox();
//        cmbIdPeminjaman.setSelectedItem(id);
    }

    //<--- Deklarsi Variabel --->
    private DefaultTableModel tabmodel;
    formatRupiah format = new formatRupiah();
    boolean lanjut = false;
    String sql;
    Statement stat;
    ResultSet result;
    Connection con = new koneksi().getConnection();

    private void tampilTblDenda() {
        Object[] baris = {"ID Peminjaman", "Nis", "Nama", "Jurusan", "Kode Buku", "Judul Buku", "Tanggal Pinjam", "Maksimal Waktu Pengembalian", "jumlah Hari", "denda"};
        tabmodel = new DefaultTableModel(null, baris);
        tblDenda.setModel(tabmodel);
        try {
            sql = "select \n"
                    + "	idPeminjaman, \n"
                    + "	nis,\n"
                    + "	nama,\n"
                    + "	jurusan, \n"
                    + "	idBuku, \n"
                    + "	judulBuku, \n"
                    + "	tanggalTerbit, \n"
                    + "	tanggalPinjam, \n"
                    + "	tanggalHarusKembali, \n"
                    + "	status,\n"
                    + "	datediff((select now()), tanggalHarusKembali) as date,\n"
                    + "	datediff((select now()), tanggalHarusKembali) * 1000 as denda\n"
                    + "	from tblPeminjaman \n"
                    + "where tanggalHaruskembali < (Select now()) and status ='Dipinjam' order by idPeminjaman asc;";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[10];
                o[0] = result.getString("idPeminjaman");
                o[1] = result.getString("nis");
                o[2] = result.getString("nama");
                o[3] = result.getString("Jurusan");
                o[4] = result.getString("idBuku");
                o[5] = result.getString("judulBuku");
                o[6] = result.getString("tanggalPinjam");
                o[7] = result.getString("tanggalHarusKembali");
                o[8] = result.getString("date");
                o[9] = format.getFormatRp(result.getString("denda"));
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void ganti(String id) {
        if ("kosong".equalsIgnoreCase(id)) {
            txtNis.setText("");
            txtNama.setText("");
            txtJurusan.setText("");
            txtKodeBuku.setText("");
            txtJudulBuku.setText("");
            txtTanggalPinjam.setText("");
            txtTanggalHarusKembali.setText("");
            txtJumlahHariTerlambat.setText("");
            txtJumlahHari.setText("");
            txtDenda.setText(format.getFormatRp("0"));
        } else {
            try {
                sql = "select \n"
                        + "	idPeminjaman, \n"
                        + "	nis,\n"
                        + "	nama,\n"
                        + "	jurusan, \n"
                        + "	idBuku, \n"
                        + "	judulBuku, \n"
                        + "	tanggalTerbit, \n"
                        + "	tanggalPinjam, \n"
                        + "	tanggalHarusKembali, \n"
                        + "	status,\n"
                        + "	datediff((select now()), tanggalHarusKembali) as date,\n"
                        + "	datediff((select now()), tanggalHarusKembali) * 1000 as denda\n"
                        + "	from tblPeminjaman \n"
                        + "where idPeminjaman = '" + id + "';";
                stat = con.createStatement();
                result = stat.executeQuery(sql);
                while (result.next()) {
                    txtNis.setText(result.getString("nis"));
                    txtNama.setText(result.getString("nama"));
                    txtJurusan.setText(result.getString("Jurusan"));
                    txtKodeBuku.setText(result.getString("idBuku"));
                    txtJudulBuku.setText(result.getString("judulBuku"));
                    txtTanggalPinjam.setText(result.getString("tanggalPinjam"));
                    txtTanggalHarusKembali.setText(result.getString("tanggalHarusKembali"));
                    txtJumlahHariTerlambat.setText(result.getString("date") + " Hari");
                    txtJumlahHari.setText(result.getString("date") + " Hari");
                    txtDenda.setText(format.getFormatRp(result.getString("denda")));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private void isiComboBox() {
        cmbIdPeminjaman.removeAllItems();
        try {
            sql = "select idPeminjaman from tblPeminjaman where tanggalHaruskembali < (Select now()) and status ='Dipinjam'";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            if (result.next()) {
                while (result.next()) {
                    cmbIdPeminjaman.addItem(result.getString("idPeminjaman"));
                }
            } else {
                cmbIdPeminjaman.addItem("Kosong");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            lanjut = true;
        }
        System.out.println(cmbIdPeminjaman.getItemCount());
        ganti((String) cmbIdPeminjaman.getSelectedItem());
    }

    private String getFormat(int harga) {
        String hasil = null;
        hasil = String.valueOf(harga);
        return hasil;
    }

    private void getrow() {
        tblDenda.getRowCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtJumlahHariTerlambat = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTanggalPinjam = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTanggalHarusKembali = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPref = new javax.swing.JButton();
        cmbIdPeminjaman = new javax.swing.JComboBox<>();
        txtNis = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtJurusan = new javax.swing.JTextField();
        txtKodeBuku = new javax.swing.JTextField();
        txtJudulBuku = new javax.swing.JTextField();
        txtJumlahHari = new javax.swing.JTextField();
        txtDendaPerHari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDenda = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDenda = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        txtJumlahHariTerlambat.setEnabled(false);

        txtNama.setEnabled(false);

        jLabel11.setText("Tanggal Pinjam");

        jLabel2.setText("NIS");

        jLabel3.setText("Nama");

        jLabel4.setText("Jurusan");

        txtTanggalPinjam.setToolTipText("YYYY-MM-DD");
        txtTanggalPinjam.setEnabled(false);

        jLabel5.setText("Judul Buku");

        jLabel6.setText("Kode Buku");

        txtTanggalHarusKembali.setEnabled(false);

        jLabel7.setText("Tanggal Harus Kembali");

        btnOk.setText("Bayar");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPref.setText("<");
        btnPref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrefActionPerformed(evt);
            }
        });

        cmbIdPeminjaman.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbIdPeminjamanItemStateChanged(evt);
            }
        });

        txtNis.setEnabled(false);

        jLabel1.setText("ID Peminjaman");

        txtJurusan.setEnabled(false);

        txtKodeBuku.setEnabled(false);

        txtJudulBuku.setEnabled(false);

        txtJumlahHari.setEnabled(false);

        txtDendaPerHari.setText("Rp. 1.000,00 Perhari");
        txtDendaPerHari.setEnabled(false);

        jLabel8.setText("Jumlah Hari Terlambat");

        jLabel9.setText("Denda");

        txtDenda.setEnabled(false);
        txtDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDendaActionPerformed(evt);
            }
        });

        jLabel12.setText("Dikali");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(btnPref)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnNext)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnOk))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtTanggalHarusKembali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtJudulBuku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(txtKodeBuku, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbIdPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDenda)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtJumlahHariTerlambat, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtJumlahHari, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDendaPerHari)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbIdPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKodeBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggalHarusKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlahHariTerlambat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlahHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDendaPerHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnPref)
                    .addComponent(btnNext))
                .addContainerGap())
        );

        tblDenda.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDenda.setGridColor(new java.awt.Color(255, 255, 255));
        tblDenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDendaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblDenda);

        jLabel10.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Bayar Denda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(374, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(75, 75, 75)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if (cmbIdPeminjaman.getSelectedItem() != "Kosong") {
            try {
                sql = "update tblPeminjaman set status = 'Bayar Denda' where idPeminjaman = " + cmbIdPeminjaman.getSelectedItem() + "";
                stat = con.createStatement();
                stat.executeUpdate(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                tampilTblDenda();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Data Kosong !");
        }

    }//GEN-LAST:event_btnOkActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (cmbIdPeminjaman.getSelectedIndex() < (cmbIdPeminjaman.getItemCount() - 1)) {
            System.out.println(cmbIdPeminjaman.getSelectedIndex());
            cmbIdPeminjaman.setSelectedIndex(cmbIdPeminjaman.getSelectedIndex() + 1);
        } else {

        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrefActionPerformed
        if (cmbIdPeminjaman.getSelectedIndex() == 0) {

        } else {
            cmbIdPeminjaman.setSelectedIndex(cmbIdPeminjaman.getSelectedIndex() - 1);
        }
    }//GEN-LAST:event_btnPrefActionPerformed

    private void cmbIdPeminjamanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbIdPeminjamanItemStateChanged
        if (lanjut) {
            ganti((String) cmbIdPeminjaman.getSelectedItem());
        }
//        System.out.println(lanjut);
    }//GEN-LAST:event_cmbIdPeminjamanItemStateChanged

    private void txtDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDendaActionPerformed

    private void tblDendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDendaMouseClicked
        String id = (String) tblDenda.getValueAt(tblDenda.getSelectedRow(), 0);
        cmbIdPeminjaman.setSelectedItem(id);
//        ganti(id);
    }//GEN-LAST:event_tblDendaMouseClicked

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
            java.util.logging.Logger.getLogger(formBayarDenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formBayarDenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formBayarDenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formBayarDenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formBayarDenda dialog = new formBayarDenda(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPref;
    private javax.swing.JComboBox<String> cmbIdPeminjaman;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tblDenda;
    private javax.swing.JTextField txtDenda;
    private javax.swing.JTextField txtDendaPerHari;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtJumlahHari;
    private javax.swing.JTextField txtJumlahHariTerlambat;
    private javax.swing.JTextField txtJurusan;
    private javax.swing.JTextField txtKodeBuku;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtTanggalHarusKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    // End of variables declaration//GEN-END:variables
}
