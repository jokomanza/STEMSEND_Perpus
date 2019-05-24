package form;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class formEditBuku extends javax.swing.JDialog {

    /**
     * Creates new form fromEditBuku
     */
    public formEditBuku(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tampilTblBuku();
        pilihIdBuku();
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        simpan = false;
        btnBatal.setVisible(false);
        //lblGambar.setIcon();
    }

    //Deklarasi Variabel//
    private DefaultTableModel tabmode;
    public boolean simpan = false;
    int max = 0;

    int[] id;
    int[] hasil;

    String sql;
    Statement stat;
    PreparedStatement prestat;
    ResultSet result;

    Connection con = new koneksi().getConnection();

    private void tampilTblBuku() {
        Object[] baris = {"Id Buku", "Judul Buku", "Pengarang", "Penerbit", "Tangagl Terbit", "stok"};
        tabmode = new DefaultTableModel(null, baris);
        tblBuku.setModel(tabmode);
        try {
            sql = "select * from tblbuku";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[6];
                o[0] = result.getString("idBuku");
                o[1] = result.getString("judulBuku");
                o[2] = result.getString("pengarang");
                o[3] = result.getString("penerbit");
                o[4] = result.getString("tanggalTerbit");
                o[5] = result.getString("stok");
//                String idBuku=result.getString("idBuku");
//                String nama_buku=result.getString("judulBuku");
//                String Pengarang=result.getString("pengarang");
//                String Penerbit=result.getString("penerbit");
//                String TanggalTerbit=result.getString("tanggalterbit");
//                String Stok = result.getString("stok");
//                String [] nilai={idBuku,nama_buku,Pengarang,Penerbit,TanggalTerbit,Stok};
                tabmode.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error\n" + e);
        }
    }

    private void kliktable() {
        int baris = tblBuku.getSelectedRow();
        txtIdBuku.setText((String) tblBuku.getValueAt(baris, 0));
        try {
            sql = "select * from tblBuku where idBuku='" + txtIdBuku.getText() + "'";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                txtJudulBuku.setText(result.getString("judulBuku"));
                txtPengarang.setText(result.getString("pengarang"));
                txtPenerbit.setText(result.getString("penerbit"));
                Date tanggal = result.getDate("tanggalTerbit");
                txtTanggalTerbit.setDate(tanggal);
                txtStok.setText(result.getString("stok"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "error\n" + ex);
        }
        btnSimpan.setEnabled(false);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        bukaKunci();
    }

    public void simpan() {
        if (simpan) {
            if (!"".equals(txtJudulBuku.getText()) && !"".equals(txtPengarang.getText()) && !"".equals(txtPenerbit.getText()) && txtTanggalTerbit.getDate() != null && !"".equals(txtStok.getText())) {
                String idBuku = "", judulBuku = "", pengarang = "", penerbit = "", tanggalTerbit = "", stok = "";
                idBuku = String.valueOf(max);
                judulBuku = txtJudulBuku.getText();
                pengarang = txtPengarang.getText();
                penerbit = txtPenerbit.getText();
                java.util.Date tgl = txtTanggalTerbit.getDate();
                java.sql.Date tanggal_Terbit = new java.sql.Date(tgl.getTime());
                stok = txtStok.getText();
                try {
                    sql = "insert into tblBuku values (?,?,?,?,?,?)";
                    prestat = (PreparedStatement) con.prepareStatement(sql);
                    prestat.setString(1, idBuku);
                    prestat.setString(2, judulBuku);
                    prestat.setString(3, pengarang);
                    prestat.setString(4, penerbit);
                    prestat.setDate(5, tanggal_Terbit);
                    prestat.setString(6, stok);
                    prestat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Menyimpan data BERHASIL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    tampilTblBuku();
                    simpan = false;
                    btnSimpan.setText("Tambah");
                    btnBatal.setVisible(false);
                    bersih();
                    kunci();
                    pilihIdBuku();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Menyimpan data GAGAL\n" + e, "Informasi\n", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data Belum Lengkap");
            }
        } else {
            btnSimpan.setText("Simpan");
            simpan = true;
            btnBatal.setVisible(true);
            bukaKunci();
        }

    }

    private void batal() {
        kunci();
    }

    private void bukaKunci() {
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

    private void pilihIdBuku() {
        Statement stat = null;
        try {
            sql = "select max(idBuku) from tblBuku;";
            stat = con.createStatement();
            java.sql.ResultSet result2 = stat.executeQuery(sql);
            while (result2.next()) {
                max = result2.getInt("max(idBuku)") + 1;
                txtIdBuku.setText(String.valueOf(max));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                stat.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void reset() {
        txtJudulBuku.setText("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        txtStok.setText("");
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSimpan.setEnabled(true);
    }

    private void cariBuku() {
        Object[] baris = {"Id Buku", "Judul Buku", "Pengarang", "Penerbit", "Tangagl Terbit", "stok"};
        tabmode = new DefaultTableModel(null, baris);
        tblBuku.setModel(tabmode);
        String cari = txtCariBuku.getText();
        try {
            sql = "select * from tblbuku where idBuku like '%" + cari + "%' or judulBuku like '%" + cari + "%' or pengarang like '%" + cari + "%'";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
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
        btnBatal = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblGambar = new javax.swing.JLabel();
        txtCariBuku = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Data Buku");
        setBackground(new java.awt.Color(0, 204, 255));

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Add_New_32px.png"))); // NOI18N
        btnSimpan.setText("Tambah");
        btnSimpan.setOpaque(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Synchronize_32px_2.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setOpaque(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Available_Updates_32px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setOpaque(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Delete_32px.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setOpaque(false);
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

        txtPenerbit.setBackground(new java.awt.Color(0, 153, 255));
        txtPenerbit.setBorder(null);
        txtPenerbit.setEnabled(false);
        txtPenerbit.setMinimumSize(new java.awt.Dimension(20, 14));
        txtPenerbit.setNextFocusableComponent(txtTanggalTerbit);
        txtPenerbit.setPreferredSize(new java.awt.Dimension(20, 14));

        f.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        f.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f.setText("Pengarang");

        l1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l1.setText("Penerbit");

        j.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        j.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j.setText("Stok");

        txtTanggalTerbit.setBackground(new java.awt.Color(0, 153, 255));
        txtTanggalTerbit.setDateFormatString("YYYY-MM-d");
        txtTanggalTerbit.setEnabled(false);

        l.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        l.setText("Tanggal Terbit");

        txtStok.setBackground(new java.awt.Color(0, 153, 255));
        txtStok.setBorder(null);
        txtStok.setEnabled(false);
        txtStok.setNextFocusableComponent(btnSimpan);
        txtStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStokKeyTyped(evt);
            }
        });

        txtIdBuku.setBorder(null);
        txtIdBuku.setEnabled(false);
        txtIdBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBukuActionPerformed(evt);
            }
        });

        txtJudulBuku.setBackground(new java.awt.Color(0, 153, 255));
        txtJudulBuku.setBorder(null);
        txtJudulBuku.setEnabled(false);
        txtJudulBuku.setNextFocusableComponent(txtPengarang);

        txtPengarang.setBackground(new java.awt.Color(0, 153, 255));
        txtPengarang.setBorder(null);
        txtPengarang.setEnabled(false);
        txtPengarang.setNextFocusableComponent(txtPenerbit);

        a.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        a.setText("ID Buku");

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Cancel_32px.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.setOpaque(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

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

        txtCariBuku.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtCariBukuInputMethodTextChanged(evt);
            }
        });
        txtCariBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariBukuActionPerformed(evt);
            }
        });
        txtCariBuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariBukuKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCariBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReset))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReset)
                            .addComponent(btnSimpan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnUpdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(f))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtPenerbit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(l1))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(l)
                                .addGap(18, 18, 18)
                                .addComponent(j))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTanggalTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(txtCariBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Book_Shelf_32px.png"))); // NOI18N
        jLabel1.setText("Edit Buku");

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
        tblBuku.setGridColor(new java.awt.Color(0, 204, 255));
        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblBukuMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBuku);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String idBuku = "", judulBuku = "", pengarang = "", penerbit = "", stok = "";
        idBuku = txtIdBuku.getText();
        judulBuku = txtJudulBuku.getText();
        pengarang = txtPengarang.getText();
        penerbit = txtPenerbit.getText();
        java.util.Date tgl = txtTanggalTerbit.getDate();
        java.sql.Date tanggalTerbit = new java.sql.Date(tgl.getTime());
        stok = txtStok.getText();
        try {
            sql = "update tblBuku set judulBuku=?, pengarang=?, penerbit=?, tanggalTerbit=?, stok=? where idBuku='" + idBuku + "'";
            prestat = con.prepareStatement(sql);
            prestat.setString(1, judulBuku);
            prestat.setString(2, pengarang);
            prestat.setString(3, penerbit);
            prestat.setDate(4, tanggalTerbit);
            prestat.setString(5, stok);
            prestat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data BERHASIL diUpdate");
            tampilTblBuku();
            reset();
            pilihIdBuku();
            kunci();
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Data GAGAL diUpdate\n" + se);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (hasil.length >= 2) {
            int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data Buku \n Kode : " + Arrays.toString(id) + " ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            for (int id : hasil) {
                if (ok == 0) {
                    try {
                        //panggil method koneksi
                        sql = "delete from tblBuku where idBuku ='" + id + "'";
                        stat = con.createStatement();
                        stat.executeUpdate(sql);
                        tampilTblBuku();
                        reset();
                        pilihIdBuku();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Data Gagal di hapus\n" + e);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
        } else {
            int baris = tblBuku.getSelectedRow();
            String idBuku = tabmode.getValueAt(baris, 0).toString();
            int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data Buku \n Kode : " + idBuku + " ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    //panggil method koneksi
                    sql = "delete from tblBuku where idBuku ='" + idBuku + "'";
                    stat = con.createStatement();
                    stat.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                    tampilTblBuku();
                    reset();
                    pilihIdBuku();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal di hapus\n" + e);
                }
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBukuMouseClicked
        kliktable();
    }//GEN-LAST:event_tblBukuMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked

    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset();
        pilihIdBuku();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtIdBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBukuActionPerformed

    }//GEN-LAST:event_txtIdBukuActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        btnBatal.setVisible(false);
        btnSimpan.setText("Tambah");
        simpan = false;
        reset();
        kunci();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtStokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStokKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') && txtStok.getText().length() < 10 || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtStokKeyTyped

    private void txtCariBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariBukuActionPerformed

    }//GEN-LAST:event_txtCariBukuActionPerformed

    private void txtCariBukuInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtCariBukuInputMethodTextChanged

    }//GEN-LAST:event_txtCariBukuInputMethodTextChanged

    private void txtCariBukuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariBukuKeyTyped
        cariBuku();
    }//GEN-LAST:event_txtCariBukuKeyTyped


    private void tblBukuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBukuMouseReleased
        hasil = new int[tblBuku.getSelectedRowCount()];
        if (hasil.length >= 2) {
            id = tblBuku.getSelectedRows();
            for (int i = 0; i < hasil.length; i++) {
                hasil[i] = Integer.parseInt((String) tblBuku.getValueAt(id[i], 0));
            }
            btnDelete.setEnabled(true);
            btnDelete.setText("Delete Selected");
            System.out.println((Arrays.toString(hasil)).replace("[", "->"));
        }
        btnDelete.setText("Delete");
    }//GEN-LAST:event_tblBukuMouseReleased

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
            java.util.logging.Logger.getLogger(formEditBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formEditBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formEditBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formEditBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                formEditBuku dialog = new formEditBuku(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel a;
    private javax.swing.JButton btnBatal;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel lblGambar;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTextField txtCariBuku;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextField txtStok;
    private com.toedter.calendar.JDateChooser txtTanggalTerbit;
    // End of variables declaration//GEN-END:variables
}
