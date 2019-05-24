package form;

import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
 */
import java.awt.*;
import static java.awt.Cursor.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import koneksi.koneksi;

public final class formEditSiswaLoop extends javax.swing.JDialog {

    public formEditSiswaLoop(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        reset();
        tampilTabel();
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        tingkat = (String) cmbFilterTingkat.getSelectedItem();
        jurusan = (String) cmbJurusan.getSelectedItem();
        KELAS3 = (String) cmbAB.getSelectedItem();
        txtKelas.setText(tingkat + " " + jurusan + " " + KELAS3);
        isiTxtNisSiswa();
        gantiWarna();
    }

    //Pendeklarasian Variabel Public
    private DefaultTableModel tabmode;
    boolean simpan = false;
    String tingkat, jurusan, KELAS3, KelasFinal;

    //menampilkan data dari datatabe ke tabel
    public void tampilTabel() {
        Object[] baris = {"Nis", "Nama Siswa", "Kelamin", "Email", "Alamat", "Telepon", "Kelas"};
        tabmode = new DefaultTableModel(null, baris);
        tblSiswaKelas10.setModel(tabmode);
        if (ckBoxFilter.isSelected()) {
            txtFilterKelas.setText((String) cmbFilterTingkat.getSelectedItem() + " " + (String) cmbFilterJurusan.getSelectedItem() + " " + (String) cmbFilterAB.getSelectedItem());
            if (cmbFilterTingkat.getSelectedItem() == "X") {
                try {
                    String sql = "select * from tblsiswa where kelas = '" + txtFilterKelas.getText() + "'";
                    Connection con = new koneksi().getConnection();
                    java.sql.Statement stat = con.createStatement();
                    java.sql.ResultSet hasil = stat.executeQuery(sql);
                    while (hasil.next()) {
                        String nis = hasil.getString("nis");
                        String namaSiswa = hasil.getString("nama_siswa");
                        String kelamin = hasil.getString("Kelamin");
                        String alamat = hasil.getString("alamat");
                        String email = hasil.getString("Email");
                        String kelas = hasil.getString("kelas");
                        String telepon = hasil.getString("Telepon");
                        String[] data = {nis, namaSiswa, kelamin, email, alamat, telepon, kelas};
                        tabmode.addRow(data);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (cmbFilterTingkat.getSelectedItem() == "XI") {
                try {
                    String sql = "select * from tblsiswa where kelas = '" + txtFilterKelas.getText() + "'";
                    Connection con = new koneksi().getConnection();
                    java.sql.Statement stat = con.createStatement();
                    java.sql.ResultSet hasil = stat.executeQuery(sql);
                    while (hasil.next()) {
                        String nis = hasil.getString("nis");
                        String namaSiswa = hasil.getString("nama_siswa");
                        String kelamin = hasil.getString("Kelamin");
                        String alamat = hasil.getString("alamat");
                        String email = hasil.getString("Email");
                        String kelas = hasil.getString("kelas");
                        String telepon = hasil.getString("Telepon");
                        String[] data = {nis, namaSiswa, kelamin, email, alamat, telepon, kelas};
                        tabmode.addRow(data);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (cmbFilterTingkat.getSelectedItem() == "XII") {
                try {
                    String sql = "select * from tblsiswa where kelas = '" + txtFilterKelas.getText() + "'";
                    Connection con = new koneksi().getConnection();
                    java.sql.Statement stat = con.createStatement();
                    java.sql.ResultSet hasil = stat.executeQuery(sql);
                    while (hasil.next()) {
                        String nis = hasil.getString("nis");
                        String namaSiswa = hasil.getString("nama_siswa");
                        String kelamin = hasil.getString("Kelamin");
                        String alamat = hasil.getString("alamat");
                        String email = hasil.getString("Email");
                        String kelas = hasil.getString("kelas");
                        String telepon = hasil.getString("Telepon");
                        String[] data = {nis, namaSiswa, kelamin, email, alamat, telepon, kelas};
                        tabmode.addRow(data);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            try {
                String sql = "select * from tblsiswa  ";
                Connection con = new koneksi().getConnection();
                java.sql.Statement stat = con.createStatement();
                java.sql.ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()) {
                    String nis = hasil.getString("nis");
                    String namaSiswa = hasil.getString("nama_siswa");
                    String kelamin = hasil.getString("Kelamin");
                    String alamat = hasil.getString("alamat");
                    String email = hasil.getString("Email");
                    String kelas = hasil.getString("kelas");
                    String telepon = hasil.getString("Telepon");
                    String[] data = {nis, namaSiswa, kelamin, email, alamat, telepon, kelas};
                    tabmode.addRow(data);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void reset() {
        txtNis.setText("");
        txtNama.setText("");
        cmbKelamin.addItem("L");
        cmbKelamin.addItem("P");
        txtAlamat.setText("");
        txtEmail.setText("");
        txtKelas.setText("");
        txtTelepon.setText("");
    }

    private void isiTxtNisSiswa() {
        autoNis ini = new autoNis();
        String kelas = (String) cmbJurusan.getSelectedItem();
        txtNis.setText((ini.getMaxNis(kelas)));
    }

    private void kliktable() {
        //nis|nama_siswa| kelamin | email| alamat| telepon | kelas
        int baris = tblSiswaKelas10.getSelectedRow();
        txtNis.setText((String) tblSiswaKelas10.getValueAt(baris, 0));
        txtNama.setText((String) tblSiswaKelas10.getValueAt(baris, 1));
        cmbKelamin.setSelectedItem((String) tblSiswaKelas10.getValueAt(baris, 2));
        txtEmail.setText((String) tblSiswaKelas10.getValueAt(baris, 3));
        txtAlamat.setText((String) tblSiswaKelas10.getValueAt(baris, 4));
        txtTelepon.setText((String) tblSiswaKelas10.getValueAt(baris, 5));
        txtKelas.setText((String) tblSiswaKelas10.getValueAt(baris, 6));
        btnSimpan.setEnabled(false);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        String Kelasnya = txtKelas.getText();
        String kelas = Kelasnya.substring(0, 1);
        String jurusan = Kelasnya.substring(2, 6);
        String KELAS3 = Kelasnya.substring(7, 8);
        cmbFilterTingkat.setSelectedItem(kelas);
        cmbJurusan.setSelectedItem(jurusan);
        cmbAB.setSelectedItem(KELAS3);
    }

    private void gantiWarna() {
        switch (cmbJurusan.getSelectedIndex()) {
            case 0:
                pnlSiswa.setBackground(Color.red);
                tblSiswaKelas10.setBackground(Color.red);
                break;
            case 1:
                pnlSiswa.setBackground(Color.pink);
                tblSiswaKelas10.setBackground(Color.pink);
                break;
            case 2:
                pnlSiswa.setBackground(Color.green);
                tblSiswaKelas10.setBackground(Color.green);
                break;
            case 3:
                pnlSiswa.setBackground(Color.MAGENTA);
                tblSiswaKelas10.setBackground(Color.magenta);
                break;
            case 4:
                pnlSiswa.setBackground(Color.yellow);
                tblSiswaKelas10.setBackground(Color.yellow);
                break;
            case 5:
                pnlSiswa.setBackground(Color.BLUE);
                tblSiswaKelas10.setBackground(Color.blue);
                break;
            case 6:
                pnlSiswa.setBackground((new Color(91, 155, 213)));
                tblSiswaKelas10.setBackground((new Color(91, 155, 213)));
                break;
            case 7:
                pnlSiswa.setBackground(Color.white);
                tblSiswaKelas10.setBackground(Color.white);
                break;
        }
    }

    private void gantiWarnaFilter() {
        switch (cmbFilterJurusan.getSelectedIndex()) {
            case 0:
                pnlSiswa.setBackground(Color.red);
                tblSiswaKelas10.setBackground(Color.red);
                break;
            case 1:
                pnlSiswa.setBackground(Color.pink);
                tblSiswaKelas10.setBackground(Color.pink);
                break;
            case 2:
                pnlSiswa.setBackground(Color.green);
                tblSiswaKelas10.setBackground(Color.green);
                break;
            case 3:
                pnlSiswa.setBackground(Color.MAGENTA);
                tblSiswaKelas10.setBackground(Color.magenta);
                break;
            case 4:
                pnlSiswa.setBackground(Color.yellow);
                tblSiswaKelas10.setBackground(Color.yellow);
                break;
            case 5:
                pnlSiswa.setBackground(Color.BLUE);
                tblSiswaKelas10.setBackground(Color.blue);
                break;
            case 6:
                pnlSiswa.setBackground((new Color(91, 155, 213)));
                tblSiswaKelas10.setBackground((new Color(91, 155, 213)));
                break;
            case 7:
                pnlSiswa.setBackground(Color.white);
                tblSiswaKelas10.setBackground(Color.white);
                break;
        }
    }

    private void simpan(String jurusan) {
        String nis, namaSiswa, kelamin, email, alamat, telepon, kelas;
        nis = txtNis.getText();
        namaSiswa = txtNama.getText();
        kelamin = (String) cmbKelamin.getSelectedItem();
        email = txtEmail.getText();
        alamat = txtAlamat.getText();
        telepon = txtTelepon.getText();
        kelas = txtKelas.getText();

        for (int i = 0; i <= 36; i++) {
//            nis = String.valueOf(Integer.parseInt(kgsp10)+1);
            try {
                Connection con = new koneksi().getConnection();
                String sql = "insert into tblsiswa values('" + nis + "','" + namaSiswa + "','" + kelamin + "','" + email + "','" + alamat + "','" + telepon + "','" + kelas + "')";
                PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Menyimpan data BERHASIL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                reset();
                tampilTabel();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Menyimpan data GAGAL " + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSiswaKelas10 = new javax.swing.JTable();
        pnlSiswa = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNis = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        txtKelas = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        cmbKelamin = new javax.swing.JComboBox<>();
        cmbFilterTingkat = new javax.swing.JComboBox<>();
        cmbAB = new javax.swing.JComboBox<>();
        cmbJurusan = new javax.swing.JComboBox<>();
        ckBoxFilter = new javax.swing.JCheckBox();
        cmbFilterJurusan = new javax.swing.JComboBox<>();
        cmbFilterAB = new javax.swing.JComboBox<>();
        txtFilterKelas = new javax.swing.JTextField();
        cmbTingkat = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Data SIswa Advance");
        setBackground(new java.awt.Color(0, 204, 204));

        jPanel3.setBackground(java.awt.Color.gray);

        tblSiswaKelas10.setBackground(new java.awt.Color(0, 204, 204));
        tblSiswaKelas10.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSiswaKelas10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaKelas10MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSiswaKelas10);

        pnlSiswa.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setText("NIS");

        jLabel3.setText("Nama");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel4.setText("Jenis Kelamin");

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel5.setText("Email");

        jLabel6.setText("Alamat");

        jLabel7.setText("Telepon");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtEmail.setToolTipText("YYYY-MM-DD");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtKelas.setEnabled(false);
        txtKelas.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtKelasInputMethodTextChanged(evt);
            }
        });

        jLabel11.setText("Kelas");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        cmbFilterTingkat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "X", "XI", "XII" }));
        cmbFilterTingkat.setEnabled(false);
        cmbFilterTingkat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFilterTingkatItemStateChanged(evt);
            }
        });

        cmbAB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B" }));
        cmbAB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbABItemStateChanged(evt);
            }
        });

        cmbJurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KGSP", "DPIB", "TEDK", "SIJA", "TTL", "TFLM", "TMPO", "TPL" }));
        cmbJurusan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJurusanItemStateChanged(evt);
            }
        });

        ckBoxFilter.setText("Filter");
        ckBoxFilter.setOpaque(false);
        ckBoxFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckBoxFilterItemStateChanged(evt);
            }
        });

        cmbFilterJurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KGSP", "DPIB", "TEDK", "SIJA", "TTL", "TFLM", "TMPO", "TPL" }));
        cmbFilterJurusan.setEnabled(false);
        cmbFilterJurusan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFilterJurusanItemStateChanged(evt);
            }
        });

        cmbFilterAB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B" }));
        cmbFilterAB.setEnabled(false);
        cmbFilterAB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFilterABItemStateChanged(evt);
            }
        });

        txtFilterKelas.setEnabled(false);
        txtFilterKelas.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtFilterKelasInputMethodTextChanged(evt);
            }
        });
        txtFilterKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFilterKelasActionPerformed(evt);
            }
        });

        cmbTingkat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "X", "XI", "XII" }));
        cmbTingkat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTingkatItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlSiswaLayout = new javax.swing.GroupLayout(pnlSiswa);
        pnlSiswa.setLayout(pnlSiswaLayout);
        pnlSiswaLayout.setHorizontalGroup(
            pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSiswaLayout.createSequentialGroup()
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSiswaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlSiswaLayout.createSequentialGroup()
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNama)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                    .addComponent(cmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlSiswaLayout.createSequentialGroup()
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlSiswaLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlSiswaLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSiswaLayout.createSequentialGroup()
                                .addComponent(cmbTingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSiswaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ckBoxFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbFilterTingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFilterJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFilterAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilterKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlSiswaLayout.setVerticalGroup(
            pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFilterJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFilterAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFilterKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckBoxFilter)
                    .addComponent(cmbFilterTingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Edit_Property_32px_1.png"))); // NOI18N
        jLabel1.setText("Edit Data Siswa Advance");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(pnlSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        Connection con = new koneksi().getConnection();
        
        String nis, namaSiswa, kelamin, email, alamat, telepon, kelas;
        nis = txtNis.getText();
        namaSiswa = txtNama.getText();
        kelamin = (String) cmbKelamin.getSelectedItem();
        email = txtEmail.getText();
        alamat = txtAlamat.getText();
        telepon = txtTelepon.getText();
        kelas = txtKelas.getText();
        int jumlah = 0;
        try {
            String sql = "select count(nis) from tblsiswa where kelas='" + kelas + "'";
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery(sql);
            while (result.next()) {
                jumlah = result.getInt("count(nis)");
                jumlah = 36 - jumlah;
            }
        } catch (SQLException ex) {

        }
        for (int i = 1; i <= jumlah; i++) {
            try {
                isiTxtNisSiswa();
                nis = txtNis.getText();
                namaSiswa = txtNama.getText() + i;
                String sql = "insert into tblsiswa values('" + nis + "','" + namaSiswa + "','" + kelamin + "','" + email + "','" + alamat + "','" + telepon + "','" + kelas + "')";
                PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
                stat.executeUpdate();
                tampilTabel();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menyimpan data GAGAL " + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Selesai");
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tblSiswaKelas10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaKelas10MouseClicked
        // TODO add your handling code here:
        kliktable();
    }//GEN-LAST:event_tblSiswaKelas10MouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        if (simpan == false) {
            reset();
            btnSimpan.setEnabled(true);
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String nis, namaSiswa, kelamin, email, alamat, telepon, kelas;
        nis = txtNis.getText();
        namaSiswa = txtNama.getText();
        kelamin = (String) cmbKelamin.getSelectedItem();
        email = txtEmail.getText();
        alamat = txtAlamat.getText();
        telepon = txtTelepon.getText();
        kelas = txtKelas.getText();
        try {
            Connection con = new koneksi().getConnection();
            //nis|nama_siswa| kelamin | email| alamat| telepon | kelas
            String sql = "update tblSiswa set nis='" + nis + "', nama_siswa='" + namaSiswa + "', kelamin='" + kelamin + "', email='" + email + "', alamat='" + alamat + "', telepon='" + telepon + "', kelas='" + kelas + "' where nis='" + nis + "';";
            PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Edit data BERHASIL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            reset();
            tampilTabel();
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Edit data GAGAL " + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int baris = tblSiswaKelas10.getSelectedRow();
        String nis = tabmode.getValueAt(baris, 0).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data Mahasiswa BP : " + nis + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                Connection con = new koneksi().getConnection();
                String sql = "delete from tblSiswa where nis='" + nis + "'";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                tampilTabel();
                reset();
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di hapus\n" + e);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cmbFilterTingkatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFilterTingkatItemStateChanged
        tampilTabel();
    }//GEN-LAST:event_cmbFilterTingkatItemStateChanged

    private void cmbABItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbABItemStateChanged
        if(cmbJurusan.getSelectedItem()=="TPL"){
            cmbAB.setVisible(false);
            txtKelas.setText((String)cmbTingkat.getSelectedItem()+" "+(String)(cmbJurusan.getSelectedItem()));
        }else{
            cmbAB.setVisible(true);
            txtKelas.setText((String)cmbTingkat.getSelectedItem()+" "+(String)(cmbJurusan.getSelectedItem()+" "+(String)cmbAB.getSelectedItem()));
        }
        gantiWarna();
        isiTxtNisSiswa();
    }//GEN-LAST:event_cmbABItemStateChanged

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtKelasInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtKelasInputMethodTextChanged

    }//GEN-LAST:event_txtKelasInputMethodTextChanged

    private void cmbJurusanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJurusanItemStateChanged
        if(cmbJurusan.getSelectedItem()=="TPL"){
            cmbAB.setVisible(false);
            txtKelas.setText((String)cmbTingkat.getSelectedItem()+" "+(String)(cmbJurusan.getSelectedItem()));
        }else{
            cmbAB.setVisible(true);
            txtKelas.setText((String)cmbTingkat.getSelectedItem()+" "+(String)(cmbJurusan.getSelectedItem()+" "+(String)cmbAB.getSelectedItem()));
        }
        gantiWarna();
        isiTxtNisSiswa();
    }//GEN-LAST:event_cmbJurusanItemStateChanged

    private void ckBoxFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckBoxFilterItemStateChanged
        if (ckBoxFilter.isSelected()) {
            cmbFilterTingkat.setEnabled(true);
            cmbFilterJurusan.setEnabled(true);
            cmbFilterAB.setEnabled(true);
            tampilTabel();
        }
        if (ckBoxFilter.isSelected() == false) {
            cmbFilterTingkat.setEnabled(false);
            cmbFilterJurusan.setEnabled(false);
            cmbFilterAB.setEnabled(false);
            tampilTabel();
        }
    }//GEN-LAST:event_ckBoxFilterItemStateChanged

    private void cmbFilterJurusanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFilterJurusanItemStateChanged
        tampilTabel();
        gantiWarnaFilter();
    }//GEN-LAST:event_cmbFilterJurusanItemStateChanged

    private void cmbFilterABItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFilterABItemStateChanged
        tampilTabel();
    }//GEN-LAST:event_cmbFilterABItemStateChanged

    private void txtFilterKelasInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtFilterKelasInputMethodTextChanged
        tampilTabel();
    }//GEN-LAST:event_txtFilterKelasInputMethodTextChanged

    private void txtFilterKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFilterKelasActionPerformed

    private void cmbTingkatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTingkatItemStateChanged
        if(cmbJurusan.getSelectedItem()=="TPL"){
            cmbAB.setVisible(false);
            txtKelas.setText((String)cmbTingkat.getSelectedItem()+" "+(String)(cmbJurusan.getSelectedItem()));
        }else{
            cmbAB.setVisible(true);
            txtKelas.setText((String)cmbTingkat.getSelectedItem()+" "+(String)(cmbJurusan.getSelectedItem()+" "+(String)cmbAB.getSelectedItem()));
        }
        gantiWarna();
        isiTxtNisSiswa();
    }//GEN-LAST:event_cmbTingkatItemStateChanged

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
            java.util.logging.Logger.getLogger(formEditSiswaLoop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formEditSiswaLoop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formEditSiswaLoop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formEditSiswaLoop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                formEditSiswaLoop dialog = new formEditSiswaLoop(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox ckBoxFilter;
    private javax.swing.JComboBox<String> cmbAB;
    private javax.swing.JComboBox<String> cmbFilterAB;
    private javax.swing.JComboBox<String> cmbFilterJurusan;
    private javax.swing.JComboBox<String> cmbFilterTingkat;
    private javax.swing.JComboBox<String> cmbJurusan;
    private javax.swing.JComboBox<String> cmbKelamin;
    private javax.swing.JComboBox<String> cmbTingkat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlSiswa;
    private javax.swing.JTable tblSiswaKelas10;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFilterKelas;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtTelepon;
    // End of variables declaration//GEN-END:variables
}
