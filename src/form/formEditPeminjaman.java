package form;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public final class formEditPeminjaman extends javax.swing.JDialog {

    /**
     * Creates new form fromEditBuku
     *
     * @param parent
     * @param modal
     */
    public formEditPeminjaman(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tampilTblPeminjaman();
        btnDelete.setEnabled(false);
        simpan = false;
        isiComboBoxBuku();
        isiComboBoxSiswa();
        Jam_digital();
        Tanggal_sekarang();
    }

    //Deklarasi Variabel//
    private DefaultTableModel tabmode;
    String[] terpilih;
    String idBuku;
    int jumlah;
    public boolean simpan = false;
    String time, tanggal;
    String sql;
    java.util.Date utilDate;
    java.sql.Date sqlDate;
    Statement stat;
    PreparedStatement prestat;
    ResultSet result;
    Connection con = new koneksi().getConnection();

    public void Tanggal_sekarang() {
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        lblTanggal.setText(kal.format(sekarang));
    }

    public void Jam_digital() {
        new Thread() {
            @Override
            public void run() {
                int waktu_mulai = 0;
                while (waktu_mulai == 0) {
                    Calendar kalender = new GregorianCalendar();
                    int jam = kalender.get(Calendar.HOUR);
                    int menit = kalender.get(Calendar.MINUTE);
                    int detik = kalender.get(Calendar.SECOND);
                    int AM_PM = kalender.get(Calendar.AM_PM);
                    String siang_malam = "";
                    if (AM_PM == 1) {
                        siang_malam = "PM";
                    } else {
                        siang_malam = "AM";
                    }
                    time = jam + " : " + menit + " : " + detik + " " + siang_malam;
                    lblWaktu.setText(time);
                }
            }
        }.start();
    }

    public void tampilTblPeminjaman() {
        Object[] baris = {"Id Peminjaman", "Nis", "Nama", "Jurusan", "Kode Buku", "Judul Buku", "Tanggal Terbit", "Tanggal Pinjam", "Maksimal Waktu Pengembalian", "Tangal Pengembalian", "Status"};
        tabmode = new DefaultTableModel(null, baris);
        tblPeminjaman.setModel(tabmode);
        try {
            sql = "select * from tblPeminjaman order by idPeminjaman asc";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[11];
                o[0] = result.getString("idPeminjaman");
                o[1] = result.getString("nis");
                o[2] = result.getString("nama");
                o[3] = result.getString("Jurusan");
                o[4] = result.getString("idBuku");
                o[5] = result.getString("judulBuku");
                o[6] = result.getString("tanggalTerbit");
                o[7] = result.getString("tanggalPinjam");
                o[8] = result.getString("tanggalHarusKembali");
                o[9] = result.getString("tanggalPengembalian");
                o[10] = result.getString("status");
                tabmode.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void kliktable() {
        int baris = tblPeminjaman.getSelectedRow();
        cmbNis.setSelectedItem((String) tblPeminjaman.getValueAt(baris, 1));
        cmbIdBuku.setSelectedItem((String) tblPeminjaman.getValueAt(baris, 4));
        btnSimpan.setEnabled(false);
        btnDelete.setEnabled(true);
    }

    private void batal() {
        simpan = false;
    }

    private void bukaKunci() {
        txtJudulBuku.setEnabled(true);
        txtPengarang.setEnabled(true);
        txtPenerbit.setEnabled(true);
        txtStok.setEnabled(true);
        txtTanggalTerbit.setEnabled(true);
    }

    private void kunci() {
        txtJudulBuku.setEnabled(false);
        txtPengarang.setEnabled(false);
        txtPenerbit.setEnabled(false);
        txtStok.setEnabled(false);
        txtTanggalTerbit.setEnabled(false);
    }

    private void bersih() {
        txtNamaSiswa.setText("");
        txtKelas.setText("");
        txtJudulBuku.setText("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        txtStok.setText("");
        txtTanggalTerbit.setDateFormatString("");
    }

    private void dataSiswa() {
        String nis = (String) cmbNis.getSelectedItem();
        try {
            sql = "select * from tblsiswa where nis = '" + nis + "';";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                String Nis = result.getString("nis");
                String namaSiswa = result.getString("nama_siswa");
                String kelamin = result.getString("Kelamin");
                String alamat = result.getString("alamat");
                String email = result.getString("Email");
                String kelas = result.getString("kelas");
                String telepon = result.getString("Telepon");
                txtNamaSiswa.setText(namaSiswa);
                txtKelas.setText(kelas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data siswa " + e);
        }
    }

    private void dataBuku() {
        idBuku = (String) cmbIdBuku.getSelectedItem();
        try {
            sql = "Select * from tblBuku  where idBuku = '" + idBuku + "'";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                String nama_buku = result.getString("judulBuku");
                String Pengarang = result.getString("pengarang");
                String penerbit = result.getString("penerbit");
                Date TanggalTerbit = result.getDate("tanggalterbit");
                String Stok = result.getString("stok");
                txtJudulBuku.setText(nama_buku);
                txtPengarang.setText(Pengarang);
                txtPenerbit.setText(penerbit);
                txtTanggalTerbit.setDate(TanggalTerbit);
                txtStok.setText(Stok);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error \n" + e);
        }

    }

    private void isiComboBoxBuku() {
        cmbIdBuku.removeAllItems();
        try {
            for (int i = 0; i <= 1; i++) {
                sql = "select idBuku from tblBuku where stok > 0 order by idBuku asc";
                stat = con.createStatement();
                result = stat.executeQuery(sql);
                while (result.next()) {
                    cmbIdBuku.addItem(result.getString("idbuku"));
                }
            }
            cmbIdBuku.removeItemAt(0);
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    private void isiComboBoxSiswa() {
        cmbNis.removeAllItems();
        try {
            for (int i = 0; i <= 1; i++) {
                sql = "select nis from tblsiswa";
                stat = con.createStatement();
                result = stat.executeQuery(sql);
                while (result.next()) {
                    cmbNis.addItem(result.getString("nis"));
                }
            }
            cmbNis.removeItemAt(0);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbNis = new javax.swing.JComboBox<>();
        txtNamaSiswa = new javax.swing.JTextField();
        txtKelas = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeminjaman = new javax.swing.JTable();
        lblTanggal = new javax.swing.JLabel();
        lblWaktu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtJudulBuku = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtPengarang = new javax.swing.JTextField();
        txtTanggalTerbit = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPenerbit = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        cmbIdBuku = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jendela Edit Peminjaman");
        setBackground(new java.awt.Color(51, 153, 255));

        jPanel2.setBackground(new java.awt.Color(71, 120, 197));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Siswa"));

        jLabel2.setText("Nis");

        jLabel3.setText("Nama");

        jLabel4.setText("Kelas");

        cmbNis.setBackground(new java.awt.Color(123, 156, 225));
        cmbNis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cmbNis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNisItemStateChanged(evt);
            }
        });

        txtNamaSiswa.setBackground(new java.awt.Color(102, 204, 255));
        txtNamaSiswa.setForeground(new java.awt.Color(255, 255, 255));
        txtNamaSiswa.setBorder(null);
        txtNamaSiswa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNamaSiswa.setEnabled(false);
        txtNamaSiswa.setPreferredSize(new java.awt.Dimension(2, 20));

        txtKelas.setBackground(new java.awt.Color(123, 156, 225));
        txtKelas.setForeground(new java.awt.Color(255, 255, 255));
        txtKelas.setBorder(null);
        txtKelas.setCaretColor(new java.awt.Color(255, 255, 255));
        txtKelas.setPreferredSize(new java.awt.Dimension(2, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbNis, 0, 277, Short.MAX_VALUE)
                    .addComponent(txtNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbNis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNamaSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Data Peminjaman"));

        tblPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPeminjaman.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblPeminjaman.setGridColor(new java.awt.Color(51, 153, 255));
        tblPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeminjamanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblPeminjamanMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPeminjamanMouseReleased(evt);
            }
        });
        tblPeminjaman.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblPeminjamanKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeminjaman);

        lblTanggal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(255, 255, 255));
        lblTanggal.setText("Tanggal");

        lblWaktu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblWaktu.setForeground(new java.awt.Color(255, 255, 255));
        lblWaktu.setText("Waktu");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblWaktu)
                        .addGap(18, 18, 18)
                        .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTanggal)
                    .addComponent(lblWaktu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Reading_32px_1.png"))); // NOI18N
        jLabel1.setText("FORM PEMINJAMAN");

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Buku"));

        txtJudulBuku.setBackground(new java.awt.Color(123, 156, 225));
        txtJudulBuku.setForeground(new java.awt.Color(255, 255, 255));
        txtJudulBuku.setBorder(null);
        txtJudulBuku.setCaretColor(new java.awt.Color(255, 255, 255));
        txtJudulBuku.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtJudulBuku.setEnabled(false);

        jLabel5.setText("Id Buku");

        jLabel6.setText("Judul Buku");

        jLabel7.setText("Tanggal Terbit");

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Save_32px_1.png"))); // NOI18N
        btnSimpan.setText("Simpan");
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

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Delete_32px.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setOpaque(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtPengarang.setBackground(new java.awt.Color(123, 156, 225));
        txtPengarang.setForeground(new java.awt.Color(255, 255, 255));
        txtPengarang.setBorder(null);
        txtPengarang.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPengarang.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtPengarang.setEnabled(false);

        txtTanggalTerbit.setBackground(new java.awt.Color(123, 156, 225));
        txtTanggalTerbit.setDateFormatString("YYYY-MM-d");
        txtTanggalTerbit.setEnabled(false);

        jLabel8.setText("Pengarang");

        jLabel9.setText("Penerbit");

        txtPenerbit.setBackground(new java.awt.Color(123, 156, 225));
        txtPenerbit.setForeground(new java.awt.Color(255, 255, 255));
        txtPenerbit.setBorder(null);
        txtPenerbit.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPenerbit.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtPenerbit.setEnabled(false);

        jLabel10.setText("Stok");

        txtStok.setBackground(new java.awt.Color(123, 156, 225));
        txtStok.setForeground(new java.awt.Color(255, 255, 255));
        txtStok.setBorder(null);
        txtStok.setCaretColor(new java.awt.Color(255, 255, 255));
        txtStok.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtStok.setEnabled(false);

        cmbIdBuku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cmbIdBuku.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbIdBukuItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStok, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtJudulBuku)
                            .addComponent(txtPengarang)
                            .addComponent(txtTanggalTerbit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPenerbit)
                            .addComponent(cmbIdBuku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(btnReset)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(txtJudulBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(txtPengarang, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(txtPenerbit, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTanggalTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(txtStok, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        //nis | | jurusan | idBuku | judulBuku | tanggalTerbit | tanggalPinjam | tanggalHarusKembali | tanggalPengembalian
        String nis = "", namaSiswa = "", jurusan = "", idBuku = "", judulBuku = "", tanggalPinjam = "", tanggalHarusKembali = "", tanggalPengembalian, date = "";
        int max = 0;
        nis = (String) cmbNis.getSelectedItem();
        namaSiswa = txtNamaSiswa.getText();
        jurusan = txtKelas.getText();
        idBuku = (String) cmbIdBuku.getSelectedItem();
        judulBuku = txtJudulBuku.getText();
        utilDate = txtTanggalTerbit.getDate();
        sqlDate = new java.sql.Date(utilDate.getTime());

        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        date = (kal.format(sekarang));
        try {
            sql = "SELECT DATE_ADD(now(), INTERVAL 7 DAY)";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                tanggalHarusKembali = result.getString("DATE_ADD(now(), INTERVAL 7 DAY)");
            }

            sql = "select max(idPeminjaman) from tblPeminjaman;";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                max = result.getInt("max(idPeminjaman)") + 1;
            }

            sql = "insert into tblPeminjaman set idPeminjaman='" + max + "' , nis=?, nama=?, jurusan=?, idBuku=?, judulBuku=?,tanggalTerbit=?, tanggalPinjam=?, tanggalharuskembali=?,status=?";
            PreparedStatement preStat = (PreparedStatement) con.prepareStatement(sql);
            System.out.println(tanggalHarusKembali);
            preStat.setString(1, nis);
            preStat.setString(2, namaSiswa);
            preStat.setString(3, jurusan);
            preStat.setString(4, idBuku);
            preStat.setString(5, judulBuku);
            preStat.setDate(6, sqlDate);
            preStat.setString(7, date);
            preStat.setString(8, tanggalHarusKembali);
            preStat.setString(9, "Dipinjam");
            preStat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Menyimpan data BERHASIL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            reset();
            tampilTblPeminjaman();

            int stokBuku = 0;
            sql = "select (stok-1) from tblbuku where idbuku=" + idBuku + "";
            this.stat = con.createStatement();
            this.result = stat.executeQuery(sql);
            while (result.next()) {
                stokBuku = result.getInt("(stok-1)");
            }

            sql = "update tblBuku set stok ='" + stokBuku + "' where idbuku=" + idBuku + ";";
            this.stat = con.createStatement();
            this.stat.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menyimpan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        isiComboBoxBuku();
        isiComboBoxSiswa();
        dataBuku();
        dataSiswa();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if ("Delete Selected".equals(btnDelete.getText())) {
            int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data Peminjaman \n ID Peminjaman : " + Arrays.toString(terpilih) + " ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                for (String terpilih1 : terpilih) {
                    try {
                        sql = "delete from tblPeminjaman where idPeminjaman ='" + terpilih1 + "'";
                        stat = con.createStatement();
                        stat.executeUpdate(sql);
                        tampilTblPeminjaman();
                        reset();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Data Gagal di hapus\n" + e);
                    }
                }
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                btnDelete.setText("Delete");
            }

        } else {
            int baris = tblPeminjaman.getSelectedRow();
            String idPeminjaman = tabmode.getValueAt(baris, 0).toString();
            int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data Peminjaman \n ID Peminjaman : " + idPeminjaman + " ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    sql = "delete from tblPeminjaman where idPeminjaman ='" + idPeminjaman + "'";
                    stat = con.createStatement();
                    stat.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                    tampilTblPeminjaman();
                    reset();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal di hapus\n" + e);
                }
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cmbIdBukuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbIdBukuItemStateChanged
        dataBuku();
    }//GEN-LAST:event_cmbIdBukuItemStateChanged

    private void cmbNisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNisItemStateChanged
        dataSiswa();
    }//GEN-LAST:event_cmbNisItemStateChanged

    private void tblPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanMouseClicked
        kliktable();
        btnDelete.setText("Delete");
    }//GEN-LAST:event_tblPeminjamanMouseClicked

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered

    }//GEN-LAST:event_jPanel2MouseEntered

    private void tblPeminjamanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPeminjamanKeyPressed

    }//GEN-LAST:event_tblPeminjamanKeyPressed

    private void tblPeminjamanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanMouseReleased
        if (tblPeminjaman.getSelectedRowCount() >= 2) {
            bersih();
            btnDelete.setText("Delete Selected");
            int[] no = tblPeminjaman.getSelectedRows();
            System.out.println(Arrays.toString(no));
            terpilih = new String[tblPeminjaman.getSelectedRowCount()];
            for (int i = 0; i < terpilih.length; i++) {
                terpilih[i] = ((String) tblPeminjaman.getValueAt(no[i], 0));
                System.out.println(i);
            }
            System.out.println(Arrays.toString(terpilih));
            btnDelete.setEnabled(true);
        } else {
            btnDelete.setText("Delete");
        }
    }//GEN-LAST:event_tblPeminjamanMouseReleased

    private void tblPeminjamanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanMouseEntered
        if (tblPeminjaman.getSelectedRowCount() >= 2) {
            btnDelete.setText("Delete Selected");
        } else {
            btnDelete.setText("Delete");
        }
    }//GEN-LAST:event_tblPeminjamanMouseEntered

    //reset 
    public void reset() {
        btnSimpan.setEnabled(true);
//        txtIdBuku.setText("");
//        txtJudulBuku.setText("");
//        txtPengarang.setText("");
//        txtPenerbit.setText("");
//        txtStok.setText("");
//        btnUpdate.setEnabled(false);
//        btnDelete.setEnabled(false);
//        btnSimpan.setEnabled(true);
    }

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
            java.util.logging.Logger.getLogger(formEditPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formEditPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formEditPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formEditPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(() -> {
            formEditPeminjaman dialog = new formEditPeminjaman(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbIdBuku;
    private javax.swing.JComboBox<String> cmbNis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblWaktu;
    private javax.swing.JTable tblPeminjaman;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtNamaSiswa;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextField txtStok;
    private com.toedter.calendar.JDateChooser txtTanggalTerbit;
    // End of variables declaration//GEN-END:variables
}
