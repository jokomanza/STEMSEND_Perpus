package form;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public final class formEditSiswa extends javax.swing.JDialog {

    public formEditSiswa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        reset();
        setAutoNis();
        tampilTabel();
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        tingkat = (String) cmbFilterTingkat.getSelectedItem();
        jurusan = (String) cmbJurusan.getSelectedItem();
        KELAS3 = (String) cmbAB.getSelectedItem();
        txtKelas.setText(tingkat + " " + jurusan + " " + KELAS3);
        gantiWarna();
    }

    //Pendeklarasian Variabel Public
    private DefaultTableModel tabmode;
    boolean filter = true;
    boolean simpan = false;
    String tingkat, jurusan, KELAS3, KelasFinal;

    String sql;
    Statement stat;
    ResultSet result;

    //menampilkan data dari database ke tabel
    public void tampilTabel() {
        Object[] baris = {"Nis", "Nama Siswa", "Kelamin", "Email", "Alamat", "Telepon", "Kelas"};
        tabmode = new DefaultTableModel(null, baris);
        tblSiswaKelas10.setModel(tabmode);
        if (ckBoxFilter.isSelected()) {
            txtFilterKelas.setText((String) cmbFilterTingkat.getSelectedItem() + " " + (String) cmbFilterJurusan.getSelectedItem() + " " + (String) cmbFilterAB.getSelectedItem());
            if (cmbFilterTingkat.getSelectedItem() == "X") {
                try {
                    sql = "select * from tblsiswa where kelas = '" + txtFilterKelas.getText() + "'";
                    Connection con = new koneksi().getConnection();
                    stat = con.createStatement();
                    result = stat.executeQuery(sql);
                    while (result.next()) {
                        Object[] o = new Object[7];
                        o[0] = result.getString("nis");
                        o[1] = result.getString("nama_siswa");
                        o[2] = result.getString("kelamin");
                        o[3] = result.getString("email");
                        o[4] = result.getString("alamat");
                        o[5] = result.getString("kelas");
                        o[6] = result.getString("telepon");
                        tabmode.addRow(o);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (cmbFilterTingkat.getSelectedItem() == "XI") {
                try {
                    sql = "select * from tblsiswa where kelas = '" + txtFilterKelas.getText() + "'";
                    Connection con = new koneksi().getConnection();
                    stat = con.createStatement();
                    result = stat.executeQuery(sql);
                    while (result.next()) {
                        Object[] o = new Object[7];
                        o[0] = result.getString("nis");
                        o[1] = result.getString("nama_siswa");
                        o[2] = result.getString("kelamin");
                        o[3] = result.getString("email");
                        o[4] = result.getString("alamat");
                        o[5] = result.getString("kelas");
                        o[6] = result.getString("telepon");
                        tabmode.addRow(o);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (cmbFilterTingkat.getSelectedItem() == "XII") {
                try {
                    sql = "select * from tblsiswa where kelas = '" + txtFilterKelas.getText() + "'";
                    Connection con = new koneksi().getConnection();
                    stat = con.createStatement();
                    result = stat.executeQuery(sql);
                    while (result.next()) {
                        Object[] o = new Object[7];
                        o[0] = result.getString("nis");
                        o[1] = result.getString("nama_siswa");
                        o[2] = result.getString("kelamin");
                        o[3] = result.getString("email");
                        o[4] = result.getString("alamat");
                        o[5] = result.getString("kelas");
                        o[6] = result.getString("telepon");
                        tabmode.addRow(o);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            try {
                sql = "select * from tblsiswa  ";
                Connection con = new koneksi().getConnection();
                stat = con.createStatement();
                result = stat.executeQuery(sql);
                while (result.next()) {
                    Object[] o = new Object[7];
                    o[0] = result.getString("nis");
                    o[1] = result.getString("nama_siswa");
                    o[2] = result.getString("kelamin");
                    o[3] = result.getString("email");
                    o[4] = result.getString("alamat");
                    o[5] = result.getString("kelas");
                    o[6] = result.getString("telepon");
                    tabmode.addRow(o);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void reset() {
        txtNis.setText("");
        txtNama.setText("");
        cmbKelamin.removeAllItems();
        cmbKelamin.addItem("L");
        cmbKelamin.addItem("P");
        txtAlamat.setText("");
        txtEmail.setText("");
        txtTelepon.setText("");
    }

    private void setAutoNis() {
        jurusan = (String) cmbJurusan.getSelectedItem();
        autoNis ini = new autoNis();
        txtNis.setText((ini.getMaxNis(jurusan)));
    }

    private void kliktable() {
        filter = false;
        //nis|nama_siswa| kelamin | email| alamat| telepon | kelas
        int baris = tblSiswaKelas10.getSelectedRow();
        txtNis.setText((String) tblSiswaKelas10.getValueAt(baris, 0));
        txtNama.setText((String) tblSiswaKelas10.getValueAt(baris, 1));
        cmbKelamin.setSelectedItem((String) tblSiswaKelas10.getValueAt(baris, 2));
        txtEmail.setText((String) tblSiswaKelas10.getValueAt(baris, 3));
        txtAlamat.setText((String) tblSiswaKelas10.getValueAt(baris, 4));
        txtKelas.setText((String) tblSiswaKelas10.getValueAt(baris, 5));
        txtTelepon.setText((String) tblSiswaKelas10.getValueAt(baris, 6));
        btnSimpan.setEnabled(false);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        String kelas = txtKelas.getText();
        System.out.println(kelas.substring(4, 7));
        System.out.println(kelas.substring(0, 1));
        //XII ttl a
        if ("X ".equalsIgnoreCase((String) kelas.substring(0, 2))) {
//            System.out.println(kelas.subSequence(0, 1));
            cmbTingkat.setSelectedItem("X");
            if ("TTL".equalsIgnoreCase(kelas.substring(2, 5)) || "TPL".equalsIgnoreCase(kelas.substring(2, 5))) {
                cmbJurusan.setSelectedItem(kelas.substring(2, 5));
                cmbAB.setSelectedItem(kelas.substring(6, 7));
            } else {
                cmbJurusan.setSelectedItem(kelas.substring(2, 6));
                cmbAB.setSelectedItem(kelas.substring(7, 8));
            }
        } else if ("XI ".equalsIgnoreCase(kelas.substring(0, 3))) {
//            System.out.println(kelas.subSequence(0, 2));
            cmbTingkat.setSelectedItem("XI");
            if ("TTL".equalsIgnoreCase(kelas.substring(3, 6)) || "TPL".equalsIgnoreCase(kelas.substring(3, 6))) {
                cmbJurusan.setSelectedItem(kelas.substring(3, 6));
                cmbAB.setSelectedItem(kelas.substring(7, 8));
            } else {
                cmbJurusan.setSelectedItem(kelas.substring(3, 7));
                cmbAB.setSelectedItem(kelas.substring(8, 9));
            }
        } else if ("XII ".equalsIgnoreCase(kelas.substring(0, 4))) {
//            System.out.println(kelas.subSequence(0, 3));
            cmbTingkat.setSelectedItem("XII");
            if ("TTL".equalsIgnoreCase(kelas.substring(4, 7)) || "TPL".equalsIgnoreCase(kelas.substring(4, 7))) {
                cmbJurusan.setSelectedItem(kelas.substring(4, 7));
                cmbAB.setSelectedItem(kelas.substring(7, 8));
            } else {
                cmbJurusan.setSelectedItem(kelas.subSequence(4, 8));
                cmbAB.setSelectedItem(kelas.substring(9, 10));
            }
        }
    }

    private void gantiWarna() {
        if (!ckBoxFilter.isSelected()) {
            switch (cmbJurusan.getSelectedIndex()) {
                case 0:
                    pnlBackground.setBackground(Color.red);
//                    tblSiswaKelas10.setBackground(Color.red);
                    tblSiswaKelas10.setGridColor(Color.red);
                    break;
                case 1:
                    pnlBackground.setBackground(Color.pink);
//                    tblSiswaKelas10.setBackground(Color.pink);
                    tblSiswaKelas10.setGridColor(Color.pink);
                    break;
                case 2:
                    pnlBackground.setBackground(Color.green);
//                    tblSiswaKelas10.setBackground(Color.green);
                    tblSiswaKelas10.setGridColor(Color.green);
                    break;
                case 3:
                    pnlBackground.setBackground(Color.MAGENTA);
//                    tblSiswaKelas10.setBackground(Color.magenta);
                    tblSiswaKelas10.setGridColor(Color.magenta);
                    break;
                case 4:
                    pnlBackground.setBackground(Color.yellow);
//                    tblSiswaKelas10.setBackground(Color.yellow);
                    tblSiswaKelas10.setGridColor(Color.yellow);
                    break;
                case 5:
                    pnlBackground.setBackground(Color.BLUE);
//                    tblSiswaKelas10.setBackground(Color.blue);
                    tblSiswaKelas10.setGridColor(Color.blue);
                    break;
                case 6:
                    pnlBackground.setBackground((new Color(91, 155, 213)));
//                    tblSiswaKelas10.setBackground((new Color(91, 155, 213)));
                    tblSiswaKelas10.setGridColor((new Color(91, 155, 213)));
                    break;
                case 7:
                    pnlBackground.setBackground(Color.white);
//                    tblSiswaKelas10.setBackground(Color.white);
                    tblSiswaKelas10.setGridColor(Color.white);
                    break;
            }
        }

    }

    private void gantiWarnaFilter() {
        tblSiswaKelas10.setGridColor(Color.gray);
        switch (cmbFilterJurusan.getSelectedIndex()) {
            case 0:
                pnlBackground.setBackground(Color.gray);
//                    pnlSiswa.setBackground(Color.red);
                tblSiswaKelas10.setBackground(Color.white);
                break;
            case 1:
                pnlBackground.setBackground(Color.gray);
//                    pnlSiswa.setBackground(Color.pink);
                tblSiswaKelas10.setBackground(Color.white);
                break;
            case 2:
                pnlBackground.setBackground(Color.gray);
//                    pnlSiswa.setBackground(Color.green);
                tblSiswaKelas10.setBackground(Color.white);
                break;
            case 3:
                pnlBackground.setBackground(Color.gray);
//                    pnlSiswa.setBackground(Color.MAGENTA);
                tblSiswaKelas10.setBackground(Color.white);
                break;
            case 4:
                pnlBackground.setBackground(Color.gray);
//                    pnlSiswa.setBackground(Color.yellow);
                tblSiswaKelas10.setBackground(Color.white);
                break;
            case 5:
                pnlBackground.setBackground(Color.gray);
//                    pnlSiswa.setBackground(Color.BLUE);
                tblSiswaKelas10.setBackground(Color.white);
                break;
            case 6:
                pnlBackground.setBackground(Color.gray);
//                    pnlSiswa.setBackground((new Color(91, 155, 213)));
                tblSiswaKelas10.setBackground((new Color(91, 155, 213)));
                tblSiswaKelas10.setBackground(Color.WHITE);
                break;
            case 7:
                pnlBackground.setBackground(Color.gray);
//                    pnlSiswa.setBackground(Color.white);
                tblSiswaKelas10.setBackground(Color.white);
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        pnlTabel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSiswaKelas10 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Data Siswa");
        setBackground(new java.awt.Color(0, 204, 204));
        setMinimumSize(new java.awt.Dimension(960, 488));

        pnlBackground.setBackground(new java.awt.Color(51, 102, 255));
        pnlBackground.setPreferredSize(new java.awt.Dimension(960, 488));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Edit_Property_32px_1.png"))); // NOI18N
        jLabel1.setText("Edit Data Siswa");

        pnlSiswa.setBackground(new java.awt.Color(0, 204, 204));
        pnlSiswa.setMinimumSize(new java.awt.Dimension(375, 428));

        jLabel2.setText("NIS");

        jLabel3.setText("Nama");

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Save_32px_1.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setOpaque(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel4.setText("Jenis Kelamin");

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Synchronize_32px_2.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.setOpaque(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel5.setText("Email");

        jLabel6.setText("Alamat");

        jLabel7.setText("Telepon");

        txtNis.setBorder(null);

        txtNama.setBorder(null);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Available_Updates_32px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setOpaque(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Delete_32px.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setOpaque(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtEmail.setToolTipText("YYYY-MM-DD");
        txtEmail.setBorder(null);
        txtEmail.setMaximumSize(new java.awt.Dimension(0, 14));

        txtKelas.setBorder(null);
        txtKelas.setEnabled(false);
        txtKelas.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtKelasInputMethodTextChanged(evt);
            }
        });
        txtKelas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtKelasPropertyChange(evt);
            }
        });

        txtTelepon.setBorder(null);
        txtTelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTeleponKeyTyped(evt);
            }
        });

        jLabel11.setText("Kelas");

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        txtAlamat.setRows(5);
        txtAlamat.setBorder(null);
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

        txtFilterKelas.setBorder(null);
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
                .addGap(18, 18, 18)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSiswaLayout.createSequentialGroup()
                        .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addGap(55, 55, 55)
                        .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSiswaLayout.createSequentialGroup()
                                .addComponent(cmbTingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlSiswaLayout.createSequentialGroup()
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlSiswaLayout.createSequentialGroup()
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlSiswaLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSiswaLayout.createSequentialGroup()
                            .addComponent(ckBoxFilter)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmbFilterTingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbFilterJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbFilterAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtFilterKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        pnlSiswaLayout.setVerticalGroup(
            pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(6, 6, 6)
                .addGroup(pnlSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFilterJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFilterAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFilterKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckBoxFilter)
                    .addComponent(cmbFilterTingkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

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

        javax.swing.GroupLayout pnlTabelLayout = new javax.swing.GroupLayout(pnlTabel);
        pnlTabel.setLayout(pnlTabelLayout);
        pnlTabelLayout.setHorizontalGroup(
            pnlTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
        );
        pnlTabelLayout.setVerticalGroup(
            pnlTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(pnlSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed

        String nis, namaSiswa, kelamin, email, alamat, telepon, kelas;
        nis = txtNis.getText();
        namaSiswa = txtNama.getText();
        kelamin = (String) cmbKelamin.getSelectedItem();
        email = txtEmail.getText();
        alamat = txtAlamat.getText();
        telepon = txtTelepon.getText();
        kelas = txtKelas.getText();
        if ("".equals(namaSiswa) || "".equals(kelamin) || "".equals(email) || "".equals(alamat) || "".equals(telepon) || "".equals(kelas) || "".equals(nis)) {
            JOptionPane.showMessageDialog(null, "Data ada yang tidak lengkap. Harap dilengkapi");
        } else {
            try {
                Connection con = new koneksi().getConnection();
                sql = "insert into tblsiswa values('" + nis + "','" + namaSiswa + "','" + kelamin + "','" + email + "','" + alamat + "','" + telepon + "','" + kelas + "')";
                stat = con.createStatement();
                stat.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Menyimpan data BERHASIL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                reset();
                tampilTabel();
            } catch (SQLException ex) {

            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tblSiswaKelas10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaKelas10MouseClicked
        kliktable();
    }//GEN-LAST:event_tblSiswaKelas10MouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        if (simpan == false) {
            reset();
            btnSimpan.setEnabled(true);
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
        }
        filter = true;
        setAutoNis();
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
            sql = "update tblSiswa set nis='" + nis + "', nama_siswa='" + namaSiswa + "', kelamin='" + kelamin + "', email='" + email + "', alamat='" + alamat + "', telepon='" + telepon + "', kelas='" + kelas + "' where nis='" + nis + "';";
            stat = con.createStatement();
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Edit data BERHASIL", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            reset();
            tampilTabel();
            setAutoNis();
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Edit data GAGAL " + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String nis = txtNis.getText();
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data Mahasiswa BP : " + nis + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                Connection con = new koneksi().getConnection();
                sql = "delete from tblSiswa where nis='" + nis + "'";
                stat = con.createStatement();
                stat.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                tampilTabel();
                reset();
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di hapus\n" + e);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cmbFilterTingkatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFilterTingkatItemStateChanged
        tampilTabel();
    }//GEN-LAST:event_cmbFilterTingkatItemStateChanged

    private void cmbABItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbABItemStateChanged
        if (cmbJurusan.getSelectedItem() == "TPL") {
            cmbAB.setVisible(false);
            txtKelas.setText((String) cmbTingkat.getSelectedItem() + " " + (String) (cmbJurusan.getSelectedItem()));
        } else {
            cmbAB.setVisible(true);
            txtKelas.setText((String) cmbTingkat.getSelectedItem() + " " + (String) (cmbJurusan.getSelectedItem() + " " + (String) cmbAB.getSelectedItem()));
        }
//        isiTxtNisSiswa();
//        setAutoNis();
    }//GEN-LAST:event_cmbABItemStateChanged

    private void txtKelasInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtKelasInputMethodTextChanged
        setAutoNis();
    }//GEN-LAST:event_txtKelasInputMethodTextChanged

    private void cmbJurusanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJurusanItemStateChanged
        if (cmbJurusan.getSelectedItem() == "TPL") {
            cmbAB.setVisible(false);
            txtKelas.setText((String) cmbTingkat.getSelectedItem() + " " + (String) (cmbJurusan.getSelectedItem()));
        } else {
            cmbAB.setVisible(true);
            txtKelas.setText((String) cmbTingkat.getSelectedItem() + " " + (String) (cmbJurusan.getSelectedItem() + " " + (String) cmbAB.getSelectedItem()));
        }
//        isiTxtNisSiswa();
        if (filter) {
            setAutoNis();
        }
        gantiWarna();
    }//GEN-LAST:event_cmbJurusanItemStateChanged

    private void ckBoxFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckBoxFilterItemStateChanged
        if (ckBoxFilter.isSelected()) {
            cmbFilterTingkat.setEnabled(true);
            cmbFilterJurusan.setEnabled(true);
            cmbFilterAB.setEnabled(true);
            tampilTabel();
            gantiWarnaFilter();
        }
        if (ckBoxFilter.isSelected() == false) {
            cmbFilterTingkat.setEnabled(false);
            cmbFilterJurusan.setEnabled(false);
            cmbFilterAB.setEnabled(false);
            tampilTabel();
            tblSiswaKelas10.setBackground(Color.white);
            gantiWarna();
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
        if (cmbJurusan.getSelectedItem() == "TPL") {
            cmbAB.setVisible(false);
            txtKelas.setText((String) cmbTingkat.getSelectedItem() + " " + (String) (cmbJurusan.getSelectedItem()));
        } else {
            cmbAB.setVisible(true);
            txtKelas.setText((String) cmbTingkat.getSelectedItem() + " " + (String) (cmbJurusan.getSelectedItem() + " " + (String) cmbAB.getSelectedItem()));
        }
//        isiTxtNisSiswa();
        gantiWarna();
    }//GEN-LAST:event_cmbTingkatItemStateChanged

    private void txtKelasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtKelasPropertyChange
        setAutoNis();
    }//GEN-LAST:event_txtKelasPropertyChange

    private void txtTeleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeleponKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') && txtTelepon.getText().length() < 12 || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ADD))) {
            System.out.println(c);
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTeleponKeyTyped

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
            java.util.logging.Logger.getLogger(formEditSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formEditSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formEditSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formEditSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formEditSiswa dialog = new formEditSiswa(new javax.swing.JFrame(), true);
//                dialog.setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlSiswa;
    private javax.swing.JPanel pnlTabel;
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
