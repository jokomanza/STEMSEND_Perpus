package form;

import AppPackage.AnimationClass;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import koneksi.koneksi;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Luciver
 */
public final class formHomeEdit extends javax.swing.JFrame {

    /**
     * Creates new form formHome
     *
     * @param parent
     * @param modal
     * @param user
     * @param level
     */
    public formHomeEdit(java.awt.Frame parent, boolean modal, String user, String level) {
        initComponents();
        this.setTitle("STEMSEND Perpus - Home - " + user);
        this.setLocationRelativeTo(null);
        setSecurity(level);
        this.level = level;
        tabHome();
        awal();
        setDateNow();
        setTimeNow();
        btnBatalKembalikan.setEnabled(false);
        btnKembalikan.setEnabled(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/image/icons8_Google_48px_1.png");
        setIconImage(icon);
        lblTombol.setIcon(iconOff);

    }

    //<----- Deklarsi Variabel ----->
    formatRupiah rupiah = new formatRupiah();

    int urut = 0;
    String urutan = "";
    String[] terpilih;
    String level;
    Image icoOn = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/icon_32bit/icons8_Toggle_On_32px.png");
    Image icoOff = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/icon_32bit/icons8_Toggle_Off_32px.png");
    Icon iconOn = new ImageIcon(icoOn);
    Icon iconOff = new ImageIcon(icoOff);

    Image icoOn2 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/icon_32bit/icons8_Toggle_On_32px_1.png");
    Image icoOff2 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/icon_32bit/icons8_Toggle_Off_32px_1.png");
    Image icoCenter = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/icon_32bit/icons8_Toggle_Indeterminate_32px.png");
    Icon iconOn2 = new ImageIcon(icoOn2);
    Icon iconOff2 = new ImageIcon(icoOff2);
    Icon iconCenter = new ImageIcon(icoCenter);

    private DefaultTableModel tabmodel;
    String KELAS1, KELAS2, KELAS3;
    public String time, tanggal;

    String sql;
    Statement stat;
    PreparedStatement prestat;
    ResultSet result;
    Connection con = new koneksi().getConnection();
    //<----- Deklarsi Variabel ----->

    private void tabHome() {
        try {
            sql = "select count(*) from tblSiswa";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                lblSiswa.setText(result.getString("count(*)") + " Siswa");
            }

            sql = "select count(*) from tblBuku";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                lblBuku.setText(result.getString("count(*)") + " Buku");
            }

            sql = "select count(*) from tblPeminjaman where status = 'dipinjam'";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                lblPeminjaman.setText(result.getString("count(*)") + " Buku diPinjam");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    ActionListener taskPerformer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    };

    Timer waktu = new Timer(1000, taskPerformer);

    private void awal() {
        if (tblDiPinjam.getRowCount() == 0) {
            btnKembalikanSemua.setEnabled(false);
        }
        if (tblDiKembalikan.getRowCount() == 0) {
            btnBatalKembalikanSemua.setEnabled(false);
        }
        txtFilterKelas10.setText((String) (cmbJurusanKelas10.getSelectedItem() + " " + (String) cmbABKelas10.getSelectedItem()));
        txtFilterKelas11.setText((String) (cmbJurusanKelas11.getSelectedItem() + " " + (String) cmbABKelas11.getSelectedItem()));
    }

    public void setDateNow() {
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Tanggal.setText(kal.format(sekarang));

    }

    public void setTimeNow() {
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
                    String siang_malam;
                    if (AM_PM == 1) {
                        siang_malam = "PM";
                    } else {
                        siang_malam = "AM";
                    }
                    time = jam + " : " + menit + " : " + detik + " " + siang_malam;
                    lblJam.setText(time);
                }
            }
        }.start();
    }

    private void setSecurity(String level) {
        if ("Admin".equalsIgnoreCase(level)) {

        } else {
            btnEditBuku.setEnabled(false);
            btnEditBukuAdvance.setEnabled(false);
            btnEditBukuAdvance1.setEnabled(false);
            btnEditPeminjaman.setEnabled(false);
            btnEditSiswa.setEnabled(false);
            btnEditSiswaKelas11.setEnabled(false);
            btnEditSiswaKelas12.setEnabled(false);
            btnBayarDenda.setEnabled(false);
        }
    }

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
                    + "	datediff((select now()), tanggalHarusKembali) * 500 as denda\n"
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
                o[9] = rupiah.getFormatRp(result.getString("denda"));
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void tampilTblKeterlambatan() {
        Object[] baris = {"ID Peminjaman", "Nis", "Nama", "Jurusan", "Kode Buku", "Judul Buku", "Tanggal Pinjam", "Maksimal Waktu Pengembalian", "Tangal Pengembalian"};
        tabmodel = new DefaultTableModel(null, baris);
        tblKeterlambatan.setModel(tabmodel);
        try {
            sql = "select * from tblPeminjaman where tanggalHaruskembali<(Select now()) and status ='Dipinjam' order by idPeminjaman asc";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[9];
                o[0] = result.getString("idPeminjaman");
                o[1] = result.getString("nis");
                o[2] = result.getString("nama");
                o[3] = result.getString("Jurusan");
                o[4] = result.getString("idBuku");
                o[5] = result.getString("judulBuku");
                o[6] = result.getString("tanggalPinjam");
                o[7] = result.getString("tanggalHarusKembali");
                o[8] = result.getString("tanggalPengembalian");
                tabmodel.addRow(o);
            }

            sql = "Select count(*) from tblPeminjaman where tanggalHaruskembali<(Select now()) and status ='Dipinjam'";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                lblJumlahKeterlambatan.setText("Total : " + (result.getString("count(*)")) + " terlambat diKembalikan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void tampilTblDiPinjam() {
        Object[] baris = {"idPeminjaman", "Nama", "Kode Buku", "Judul Buku"};
        tabmodel = new DefaultTableModel(null, baris);
        tblDiPinjam.setModel(tabmodel);
        try {
            sql = "select idPeminjaman, nama, idBuku, judulBuku from tblPeminjaman where status='Dipinjam' order by idPeminjaman asc";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[4];
                o[0] = result.getString("idPeminjaman");
                o[1] = result.getString("nama");
                o[2] = result.getString("idBuku");
                o[3] = result.getString("judulBuku");
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void tampilTblDiKembalikan() {
        Object[] baris = {"IdPeminjaman", "Nis", "Nama", "Jurusan", "Kode Buku", "Judul Buku", "status"};
        tabmodel = new DefaultTableModel(null, baris);
        tblDiKembalikan.setModel(tabmodel);
        try {
            sql = "select * from tblPeminjaman where status='Dikembalikan' order by idPeminjaman asc";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[7];
                o[0] = result.getString("idPeminjaman");
                o[1] = result.getString("nis");
                o[2] = result.getString("nama");
                o[3] = result.getString("Jurusan");
                o[4] = result.getString("idBuku");
                o[5] = result.getString("judulBuku");
                o[6] = result.getString("status");
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void tampilTblPeminjaman() {
        Object[] baris = {"ID Peminjaman", "Nis", "Nama", "Jurusan", "Kode Buku", "Judul Buku", "Tanggal Pinjam", "Maksimal Waktu Pengembalian", "Status", "Tangal Pengembalian"};
        tabmodel = new DefaultTableModel(null, baris);
        tblPeminjaman.setModel(tabmodel);
        try {
            sql = "select * from tblPeminjaman order by idPeminjaman asc";
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
                o[8] = result.getString("status");
                o[9] = result.getString("tanggalPengembalian");
                tabmodel.addRow(o);
            }

            sql = "select count(*) from tblPeminjaman";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                lblJumlahPeminjaman.setText("Total : " + (result.getString("count(*)")) + " Peminjaman");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void tampilTblBuku() {//
        Object[] baris = {"Id Buku", "Judul Buku", "Pengarang", "Penerbit", "Tangagl Terbit", "stok"};
        tabmodel = new DefaultTableModel(null, baris);
        tblBuku.setModel(tabmodel);
        tblLaporanBuku.setModel(tabmodel);
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
                o[4] = result.getString("tanggalterbit");
                o[5] = result.getString("stok");
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        lblTotalBuku.setText("Total : " + tblBuku.getRowCount());
    }

    private void tampilTblSiswaKelas10() {
        Object[] baris = {"Nis", "Nama Siswa", "Kelamin", "Email", "Alamat", "kelas", "telepon"};
        tabmodel = new DefaultTableModel(null, baris);
        tblSiswaKelas10.setModel(tabmodel);
        tblLaporanSiswaKelas10.setModel(tabmodel);
        if (ckBoxFilterKelas10.isSelected()) {
            txtFilterKelas10.setText((String) (cmbJurusanKelas10.getSelectedItem() + " " + (String) cmbABKelas10.getSelectedItem()));
            try {
                sql = "select * from tblsiswa where kelas ='X " + txtFilterKelas10.getText() + "'";
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
                    tabmodel.addRow(o);
                }
                switch (cmbJurusanKelas10.getSelectedIndex()) {
                    case 0:
                        pnlSiswaKelas10.setBackground(Color.red);
                        tblSiswaKelas10.setGridColor(Color.red);
                        break;
                    case 1:
                        pnlSiswaKelas10.setBackground(Color.pink);
                        tblSiswaKelas10.setGridColor(Color.pink);
                        break;
                    case 2:
                        pnlSiswaKelas10.setBackground(Color.green);
                        tblSiswaKelas10.setGridColor(Color.green);
                        break;
                    case 3:
                        pnlSiswaKelas10.setBackground(Color.MAGENTA);
                        tblSiswaKelas10.setGridColor(Color.magenta);
                        break;
                    case 4:
                        pnlSiswaKelas10.setBackground(Color.yellow);
                        tblSiswaKelas10.setGridColor(Color.yellow);
                        break;
                    case 5:
                        pnlSiswaKelas10.setBackground(Color.BLUE);
                        tblSiswaKelas10.setGridColor(Color.blue);
                        break;
                    case 6:
                        pnlSiswaKelas10.setBackground((new Color(91, 155, 213)));
                        tblSiswaKelas10.setGridColor((new Color(91, 155, 213)));
                        break;
                    case 7:
                        pnlSiswaKelas10.setBackground(Color.white);
                        tblSiswaKelas10.setGridColor(Color.white);
                        break;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            try {
                sql = "select * from tblsiswa where kelas like 'X %' ";
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
                    tabmodel.addRow(o);
                }
                pnlSiswaKelas10.setBackground(Color.GRAY);
                tblSiswaKelas10.setGridColor(Color.gray);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void tampilTblSiswaKelas11() {
        Object[] baris = {"Nis", "Nama Siswa", "Kelamin", "Email", "Alamat", "kelas", "telepon"};
        tabmodel = new DefaultTableModel(null, baris);
        tblSiswaKelas11.setModel(tabmodel);
        tblLaporanSiswaKelas11.setModel(tabmodel);
        if (ckBoxFilterKelas11.isSelected()) {
            txtFilterKelas11.setText((String) (cmbJurusanKelas11.getSelectedItem() + " " + (String) cmbABKelas11.getSelectedItem()));
            try {
                sql = "select * from tblsiswa where kelas = 'XI " + txtFilterKelas11.getText() + "'";
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
                    tabmodel.addRow(o);
                }
                switch (cmbJurusanKelas11.getSelectedIndex()) {
                    case 0:
                        pnlSiswaKelas11.setBackground(Color.red);
                        tblSiswaKelas11.setGridColor(Color.red);
                        break;
                    case 1:
                        pnlSiswaKelas11.setBackground(Color.pink);
                        tblSiswaKelas11.setGridColor(Color.pink);
                        break;
                    case 2:
                        pnlSiswaKelas11.setBackground(Color.green);
                        tblSiswaKelas11.setGridColor(Color.green);
                        break;
                    case 3:
                        pnlSiswaKelas11.setBackground(Color.MAGENTA);
                        tblSiswaKelas11.setGridColor(Color.magenta);
                        break;
                    case 4:
                        pnlSiswaKelas11.setBackground(Color.yellow);
                        tblSiswaKelas11.setGridColor(Color.yellow);
                        break;
                    case 5:
                        pnlSiswaKelas11.setBackground(Color.BLUE);
                        tblSiswaKelas11.setGridColor(Color.blue);
                        break;
                    case 6:
                        pnlSiswaKelas11.setBackground((new Color(91, 155, 213)));
                        tblSiswaKelas11.setGridColor(new Color(91, 155, 213));
                        break;
                    case 7:
                        pnlSiswaKelas11.setBackground(Color.white);
                        tblSiswaKelas11.setGridColor(Color.white);
                        break;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            try {
                sql = "select * from tblSiswa where kelas like 'XI %'  ";
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
                    tabmodel.addRow(o);
                }
                pnlSiswaKelas11.setBackground(Color.GRAY);
                tblSiswaKelas11.setGridColor(Color.gray);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void tampilTblSiswaKelas12() {
        Object[] baris = {"Nis", "Nama Siswa", "Kelamin", "Email", "Alamat", "kelas", "telepon"};
        tabmodel = new DefaultTableModel(null, baris);
        tblSiswaKelas12.setModel(tabmodel);
        tblLaporanSiswaKelas12.setModel(tabmodel);
        if (ckBoxFilterKelas12.isSelected()) {
            txtFilterKelas12.setText((String) (cmbJurusanKelas12.getSelectedItem() + " " + (String) cmbABKelas12.getSelectedItem()));
            try {
                sql = "select * from tblsiswa where kelas = 'XII " + txtFilterKelas12.getText() + "'";
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
                    tabmodel.addRow(o);
                }

                switch (cmbJurusanKelas12.getSelectedIndex()) {
                    case 0:
                        pnlSiswaKelas12.setBackground(Color.red);
                        tblSiswaKelas12.setGridColor(Color.blue);
                        break;
                    case 1:
                        pnlSiswaKelas12.setBackground(Color.pink);
                        tblSiswaKelas12.setGridColor(Color.pink);
                        break;
                    case 2:
                        pnlSiswaKelas12.setBackground(Color.green);
                        tblSiswaKelas12.setGridColor(Color.green);
                        break;
                    case 3:
                        pnlSiswaKelas12.setBackground(Color.MAGENTA);
                        tblSiswaKelas12.setGridColor(Color.magenta);
                        break;
                    case 4:
                        pnlSiswaKelas12.setBackground(Color.yellow);
                        tblSiswaKelas12.setGridColor(Color.yellow);
                        break;
                    case 5:
                        pnlSiswaKelas12.setBackground(Color.BLUE);
                        tblSiswaKelas12.setGridColor(Color.blue);
                        break;
                    case 6:
                        pnlSiswaKelas12.setBackground((new Color(91, 155, 213)));
                        tblSiswaKelas12.setGridColor(new Color(91, 155, 213));
                        break;
                    case 7:
                        pnlSiswaKelas12.setBackground(Color.white);
                        tblSiswaKelas12.setGridColor(Color.white);
                        break;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            try {
                sql = "select * from tblSiswa where kelas like 'XII %'  ";
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
                    tabmodel.addRow(o);
                }
                pnlSiswaKelas12.setBackground(Color.GRAY);
                tblSiswaKelas12.setGridColor(Color.gray);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void cariDiKembalikan() {
        Object[] baris = {"IdPeminjaman", "Nis", "Nama", "Jurusan", "Kode Buku", "Judul Buku", "status"};
        tabmodel = new DefaultTableModel(null, baris);
        tblDiKembalikan.setModel(tabmodel);
        String cari = txtCariDiKembalikan.getText();
        try {
            sql = "select * from tblPeminjaman "
                    + "where status = 'DiKembalikan' and "
                    + "( idpeminjaman like '%" + cari + "%' or nama like '%" + cari + "%' or judulBuku like '%" + cari + "%') "
                    + "order by idPeminjaman asc";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[7];
                o[0] = result.getString("idPeminjaman");
                o[1] = result.getString("nis");
                o[2] = result.getString("nama");
                o[3] = result.getString("jurusan");
                o[4] = result.getString("idBuku");
                o[5] = result.getString("judulBuku");
                o[6] = result.getString("status");
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cariDiPinjam() {
        Object[] baris = {"idPeminjaman", "Nama", "Kode Buku", "Judul Buku"};
        tabmodel = new DefaultTableModel(null, baris);
        tblDiPinjam.setModel(tabmodel);
        String cari = txtCariDipinjam.getText();
        try {
            sql = "select * from tblPeminjaman where status='Dipinjam' and ( idpeminjaman like '%" + cari + "%' or nama like '%" + cari + "%' or judulBuku like '%" + cari + "%') order by idPeminjaman asc";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[4];
                o[0] = result.getString("idPeminjaman");
                o[1] = result.getString("nama");
                o[2] = result.getString("idBuku");
                o[3] = result.getString("judulBuku");
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cariBuku() {
        String cari = txtCariBuku.getText();
        Object[] baris = {"Id Buku", "Judul Buku", "Pengarang", "Penerbit", "Tangagl Terbit", "stok"};
        tabmodel = new DefaultTableModel(null, baris);
        tblBuku.setModel(tabmodel);
        try {
            sql = "select * from tblbuku where idBuku like '%" + cari + "%' or judulBuku like '%" + cari + "%' or pengarang like '%" + cari + "%'";
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
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        lblTotalBuku.setText("Total : " + tblBuku.getRowCount());
    }

    private void cariPeminjaman() {
        Object[] baris = {"ID Peminjaman", "Nis", "Nama", "Jurusan", "Kode Buku", "Judul Buku", "Tanggal Pinjam", "Maksimal Waktu Pengembalian", "Status", "Tangal Pengembalian"};
        tabmodel = new DefaultTableModel(null, baris);
        tblPeminjaman.setModel(tabmodel);
        String cari = txtCariPeminjaman.getText();
        try {
            sql = "select * from tblPeminjaman "
                    + "where idPeminjaman like '%" + cari + "%' or"
                    + " nama like '%" + cari + "%' or"
                    + " jurusan like '%" + cari + "%' or"
                    + " idBuku like '%" + cari + "%' or"
                    + " judulBuku like '%" + cari + "%' or"
                    + " status like '%" + cari + "%' "
                    + "order by idPeminjaman asc";
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
                o[8] = result.getString("status");
                o[9] = result.getString("tanggalPengembalian");
                tabmodel.addRow(o);
            }

            lblJumlahPeminjaman.setText("Total : " + String.valueOf(tblPeminjaman.getRowCount()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cariKeterlambatan() {
        Object[] baris = {"ID Peminjaman", "Nis", "Nama", "Jurusan", "Kode Buku", "Judul Buku", "Tanggal Pinjam", "Maksimal Waktu Pengembalian", "Tangal Pengembalian"};
        tabmodel = new DefaultTableModel(null, baris);
        tblKeterlambatan.setModel(tabmodel);
        String cari = txtCariKeterlambatan.getText();
        try {
            sql = "select * from tblPeminjaman"
                    + " where (tanggalHaruskembali<(Select now()) and "
                    + " status ='Dipinjam') and "
                    + " (idPeminjaman like '%" + cari + "%' or"
                    + " nama like '%" + cari + "%' or"
                    + " jurusan like '%" + cari + "%' or"
                    + " idBuku like '%" + cari + "%' or"
                    + " judulBuku like '%" + cari + "%' or"
                    + " status like '%" + cari + "%') "
                    + " order by idPeminjaman asc";
            stat = con.createStatement();
            result = stat.executeQuery(sql);
            while (result.next()) {
                Object[] o = new Object[9];
                o[0] = result.getString("idPeminjaman");
                o[1] = result.getString("nis");
                o[2] = result.getString("nama");
                o[3] = result.getString("Jurusan");
                o[4] = result.getString("idBuku");
                o[5] = result.getString("judulBuku");
                o[6] = result.getString("tanggalPinjam");
                o[7] = result.getString("tanggalHarusKembali");
                o[8] = result.getString("tanggalPengembalian");
                tabmodel.addRow(o);
            }

            lblJumlahKeterlambatan.setText("Total : " + (String.valueOf(tblKeterlambatan.getRowCount())) + " Terlambat Dikembalikan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cariSiswa(String kls) {
        String cari = "";
        Object[] baris = {"Nis", "Nama Siswa", "Kelamin", "Email", "Alamat", "kelas", "telepon"};
        tabmodel = new DefaultTableModel(null, baris);
        int panel = tabPanelSiswa.getSelectedIndex();
        switch (panel) {
            case 0:
                tblSiswaKelas10.setModel(tabmodel);
                cari = txtCariSiswaKelas10.getText();
                break;
            case 1:
                tblSiswaKelas11.setModel(tabmodel);
                cari = txtCariSiswaKelas11.getText();
                break;
            case 2:
                tblSiswaKelas12.setModel(tabmodel);
                cari = txtCariSiswaKelas12.getText();
                break;
        }
        try {
            sql = "select * from tblsiswa"
                    + " where kelas like '" + kls + "%'"
                    + " and (nis like '%" + cari + "%' or"
                    + " kelamin like '%" + cari + "%' or"
                    + " email like '%" + cari + "%'or"
                    + " nama_siswa like '%" + cari + "%' or"
                    + " alamat like '%" + cari + "%')";
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
                tabmodel.addRow(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void tampilJumlahCariSiswa() {
        int hasil = 0;
        int panel = tabPanelSiswa.getSelectedIndex();
        switch (panel) {
            case 0:
                hasil = tblSiswaKelas10.getRowCount();
                lblTotalCariSiswa10.setText("Total : " + hasil);
                break;
            case 1:
                hasil = tblSiswaKelas11.getRowCount();
                lblTotalCariSiswa11.setText("Total : " + hasil);
                break;
            case 2:
                hasil = tblSiswaKelas12.getRowCount();
                lblTotalCariSiswa12.setText("Total : " + hasil);
                break;
        }
    }

    private void klikTabelSiswaKelas10() {
        int baris = tblSiswaKelas10.getSelectedRow();
        new formDetailSiswa(this, true, ((String) tblSiswaKelas10.getValueAt(baris, 0))).setVisible(true);
    }

    private void klikTabelSiswaKelas11() {
        int baris = tblSiswaKelas11.getSelectedRow();
        new formDetailSiswa(this, true, ((String) tblSiswaKelas11.getValueAt(baris, 0))).setVisible(true);
    }

    private void klikTabelSiswaKelas12() {
        int baris = tblSiswaKelas12.getSelectedRow();
        new formDetailSiswa(this, true, ((String) tblSiswaKelas12.getValueAt(baris, 0))).setVisible(true);
    }

    private void klikTabelBuku() {
        int baris = tblBuku.getSelectedRow();
        new formDetailBuku(this, true, (Integer.valueOf((String) (tblBuku.getValueAt(baris, 0))))).setVisible(true);
    }

//    public ImageIcon resizeImage(String imagePatch, byte[] pic){
//        ImageIcon myImage=null;
//        if(imagePatch !=null){
//            myImage = new ImageIcon(imagePatch);
//            System.out.println("joko");
//        }else{
//            myImage = new ImageIcon(pic);
//        }
//        Image img = myImage.getImage();
//        Image img2 = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon image = new ImageIcon(img2);
//        lblImage.setIcon(image);
//        return image;
//    }
    private void export() {
        FileWriter fileWriter;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("export_output/excel"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                TableModel tModel = tblSiswaKelas10.getModel();
                fileWriter = new FileWriter(new File(chooser.getSelectedFile() + ".xls"));
                // write header
                for (int i = 0; i < tModel.getColumnCount(); i++) {
                    fileWriter.write(tModel.getColumnName(i).toUpperCase() + "\t");
                }
                fileWriter.write("\n");
                // write record
                for (int i = 0; i < tModel.getRowCount(); i++) {
                    for (int j = 0; j < tModel.getColumnCount(); j++) {
                        fileWriter.write(tModel.getValueAt(i, j).toString() + "\t");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void action1() {
        if ("Admin".equalsIgnoreCase(level)) {
            if (tblDiPinjam.getRowCount() <= 0) {
                btnKembalikanSemua.setEnabled(false);
            } else if (tblDiPinjam.getRowCount() >= 1) {
                btnKembalikanSemua.setEnabled(true);
            }

            if (tblDiKembalikan.getRowCount() <= 0) {
                btnBatalKembalikanSemua.setEnabled(false);
            } else if (tblDiKembalikan.getRowCount() >= 1) {
                btnBatalKembalikanSemua.setEnabled(true);
            }

        }
    }

    private void brose(String alamat) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI url = new URI(alamat);
            if (Desktop.isDesktopSupported()) {
                try {
                    desktop.browse(url);
                    System.out.println("Sukses");
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        } catch (URISyntaxException ex) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        tabPanelUtama = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblSiswa = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lblBuku = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        lblPeminjaman = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tabPanelSiswa = new javax.swing.JTabbedPane();
        pnlSiswaKelas10 = new javax.swing.JPanel();
        lblTombol1 = new javax.swing.JLabel();
        txtFilterKelas10 = new javax.swing.JTextField();
        btnEditSiswa = new javax.swing.JButton();
        txtCariSiswaKelas10 = new javax.swing.JTextField();
        btnCariSiswaKelas10 = new javax.swing.JButton();
        btnRefreshSiswaKelas10 = new javax.swing.JButton();
        lblTotalCariSiswa10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSiswaKelas10 = new javax.swing.JTable();
        cmbJurusanKelas10 = new javax.swing.JComboBox<>();
        cmbABKelas10 = new javax.swing.JComboBox<>();
        ckBoxFilterKelas10 = new javax.swing.JCheckBox();
        pnlSiswaKelas11 = new javax.swing.JPanel();
        btnEditSiswaKelas11 = new javax.swing.JButton();
        txtCariSiswaKelas11 = new javax.swing.JTextField();
        btnCariSiswaKelas11 = new javax.swing.JButton();
        btnRefreshSiswaKelas11 = new javax.swing.JButton();
        lblTotalCariSiswa11 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblSiswaKelas11 = new javax.swing.JTable();
        txtFilterKelas11 = new javax.swing.JTextField();
        ckBoxFilterKelas11 = new javax.swing.JCheckBox();
        cmbJurusanKelas11 = new javax.swing.JComboBox<>();
        cmbABKelas11 = new javax.swing.JComboBox<>();
        pnlSiswaKelas12 = new javax.swing.JPanel();
        btnEditSiswaKelas12 = new javax.swing.JButton();
        txtCariSiswaKelas12 = new javax.swing.JTextField();
        btnCariSiswaKelas12 = new javax.swing.JButton();
        btnRefreshSiswaKelas12 = new javax.swing.JButton();
        lblTotalCariSiswa12 = new javax.swing.JLabel();
        ckBoxFilterKelas12 = new javax.swing.JCheckBox();
        cmbJurusanKelas12 = new javax.swing.JComboBox<>();
        cmbABKelas12 = new javax.swing.JComboBox<>();
        txtFilterKelas12 = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblSiswaKelas12 = new javax.swing.JTable();
        panelBuku = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBuku = new javax.swing.JTable();
        btnEditBuku = new javax.swing.JButton();
        btnRefreshBuku = new javax.swing.JButton();
        btnCariBuku = new javax.swing.JButton();
        txtCariBuku = new javax.swing.JTextField();
        lblTotalBuku = new javax.swing.JLabel();
        panelPeminjaman = new javax.swing.JPanel();
        tabPanelPeminjaman = new javax.swing.JTabbedPane();
        pnlPeminjaman = new javax.swing.JPanel();
        txtCariPeminjaman = new javax.swing.JTextField();
        btnCariPeminjaman = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPeminjaman = new javax.swing.JTable();
        btnEditPeminjaman = new javax.swing.JButton();
        btnRefreshPeminjaman = new javax.swing.JButton();
        lblJumlahPeminjaman = new javax.swing.JLabel();
        pnlPengembalian = new javax.swing.JPanel();
        pnlDipinjam = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblDiPinjam = new javax.swing.JTable();
        txtCariDipinjam = new javax.swing.JTextField();
        btnCariDiPinjam = new javax.swing.JButton();
        btnKembalikan = new javax.swing.JButton();
        btnKembalikanSemua = new javax.swing.JButton();
        btnBatalKembalikan = new javax.swing.JButton();
        btnBatalKembalikanSemua = new javax.swing.JButton();
        btnRefreshPengembalian = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDiKembalikan = new javax.swing.JTable();
        txtCariDiKembalikan = new javax.swing.JTextField();
        btnCariDiKembalikan = new javax.swing.JButton();
        pnKeterlambatan = new javax.swing.JPanel();
        txtCariKeterlambatan = new javax.swing.JTextField();
        tbnCariKeterlambatan = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKeterlambatan = new javax.swing.JTable();
        btnRefreshKeterlambatan = new javax.swing.JButton();
        lblJumlahKeterlambatan = new javax.swing.JLabel();
        pnlDenda = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDenda = new javax.swing.JTable();
        btnBayarDenda = new javax.swing.JButton();
        btnRefreshDenda = new javax.swing.JButton();
        btnCariDenda = new javax.swing.JButton();
        txtCariDenda = new javax.swing.JTextField();
        btnBayarDenda1 = new javax.swing.JButton();
        panelLaporan = new javax.swing.JPanel();
        tabPanelLaporan = new javax.swing.JTabbedPane();
        pnlLaporanBuku = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblLaporanBuku = new javax.swing.JTable();
        btnRefreshLaporanBuku = new javax.swing.JButton();
        btnCariLaporanBuku = new javax.swing.JButton();
        txtCariLaporanBuku = new javax.swing.JTextField();
        tabPanelLaporanSiswa = new javax.swing.JTabbedPane();
        pnlLaporanSiswaKelas10 = new javax.swing.JPanel();
        txtCariLaporanSiswaKelas10 = new javax.swing.JTextField();
        btnCariLaporanSiswaKelas10 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblLaporanSiswaKelas10 = new javax.swing.JTable();
        btnRefreshLaporanSiswaKelas10 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlLaporanSiswaKelas11 = new javax.swing.JPanel();
        txtCariLaporanSiswaKelas11 = new javax.swing.JTextField();
        btnCariLaporanSiswaKelas11 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblLaporanSiswaKelas11 = new javax.swing.JTable();
        btnRefreshLaporanSiswaKelas11 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        data_mahasiswa6 = new javax.swing.JPanel();
        txtCariLaporanSiswaKelas12 = new javax.swing.JTextField();
        btnCariLaporanSiswaKelas12 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblLaporanSiswaKelas12 = new javax.swing.JTable();
        btnRefreshLaporanSiswaKelas12 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelLainLain = new javax.swing.JPanel();
        tabPanelLainLain = new javax.swing.JTabbedPane();
        pnlTentangkami = new javax.swing.JPanel();
        btnTentangKami = new javax.swing.JButton();
        pnlKelompokKami = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblSiswa1 = new javax.swing.JLabel();
        pnlPengaturan = new javax.swing.JPanel();
        pnlSettingInterface = new javax.swing.JPanel();
        rbtMetal = new javax.swing.JRadioButton();
        rbtNimbus = new javax.swing.JRadioButton();
        rbtMotif = new javax.swing.JRadioButton();
        rbtWindows = new javax.swing.JRadioButton();
        rbtWindowsClassic = new javax.swing.JRadioButton();
        btnUbahInterface = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnEditBukuAdvance = new javax.swing.JButton();
        btnEditBukuAdvance1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblTombol = new javax.swing.JLabel();
        lblTombol2 = new javax.swing.JLabel();
        pnlTitleBar = new javax.swing.JPanel();
        Tanggal = new javax.swing.JLabel();
        lblJam = new javax.swing.JLabel();
        lblImageSMK = new javax.swing.JLabel();
        bottonPanel = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        lblImage1 = new javax.swing.JLabel();
        lblImage2 = new javax.swing.JLabel();
        lblImage3 = new javax.swing.JLabel();
        lblImage4 = new javax.swing.JLabel();
        lblImage5 = new javax.swing.JLabel();
        lblLogOut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 592));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tabPanelUtama.setBackground(java.awt.Color.orange);
        tabPanelUtama.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabPanelUtama.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabPanelUtama.setOpaque(true);
        tabPanelUtama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPanelUtamaMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(240, 134, 47));
        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));

        jLabel8.setFont(new java.awt.Font("JBCursive-V3", 1, 26)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Perpustakaan SMK Negeri 2 Klaten");
        jLabel8.setToolTipText("Perpustakaan SMK N 2 Klaten");

        jPanel12.setBackground(new java.awt.Color(51, 153, 255));
        jPanel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel12.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel12MousePressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Student_Male_70px_1.png"))); // NOI18N

        lblSiswa.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblSiswa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSiswa.setText("1000 Siswa");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(51, 153, 255));
        jPanel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel19.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel19MousePressed(evt);
            }
        });

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Book_Shelf_70px_1.png"))); // NOI18N

        lblBuku.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblBuku.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBuku.setText("1000 Buku");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(51, 153, 255));
        jPanel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel23.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel23MousePressed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Reading_70px_1.png"))); // NOI18N

        lblPeminjaman.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblPeminjaman.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPeminjaman.setText("1000 Peminjaman");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPeminjaman, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(51, 153, 255));
        jPanel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel25.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel25MousePressed(evt);
            }
        });

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/icons8_Info_70px_1.png"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Tentang Kami");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPanelUtama.addTab("Home      ", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Home_32px.png")), jPanel1, ""); // NOI18N

        tabPanelSiswa.setBackground(java.awt.Color.orange);
        tabPanelSiswa.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabPanelSiswa.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabPanelSiswa.setOpaque(true);
        tabPanelSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPanelSiswaMouseClicked(evt);
            }
        });

        pnlSiswaKelas10.setBackground(new java.awt.Color(0, 153, 255));
        pnlSiswaKelas10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlSiswaKelas10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlSiswaKelas10MouseEntered(evt);
            }
        });

        lblTombol1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Toggle_Off_32px.png"))); // NOI18N
        lblTombol1.setText("Filter");
        lblTombol1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTombol1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTombol1MouseClicked(evt);
            }
        });

        txtFilterKelas10.setEnabled(false);
        txtFilterKelas10.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtFilterKelas10InputMethodTextChanged(evt);
            }
        });

        btnEditSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Edit_Property_32px_1.png"))); // NOI18N
        btnEditSiswa.setText("  Edit Data Siswa");
        btnEditSiswa.setToolTipText("Edit Data Siswa Kelas 10");
        btnEditSiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditSiswa.setOpaque(false);
        btnEditSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSiswaActionPerformed(evt);
            }
        });

        txtCariSiswaKelas10.setBackground(new java.awt.Color(153, 204, 255));
        txtCariSiswaKelas10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariSiswaKelas10.setToolTipText("Masukkan Kata Pencarian Mahasiswa");
        txtCariSiswaKelas10.setBorder(null);
        txtCariSiswaKelas10.setMinimumSize(new java.awt.Dimension(6, 14));
        txtCariSiswaKelas10.setPreferredSize(new java.awt.Dimension(6, 14));
        txtCariSiswaKelas10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariSiswaKelas10KeyTyped(evt);
            }
        });

        btnCariSiswaKelas10.setText("Cari");
        btnCariSiswaKelas10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariSiswaKelas10.setOpaque(false);
        btnCariSiswaKelas10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariSiswaKelas10MouseClicked(evt);
            }
        });

        btnRefreshSiswaKelas10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Synchronize_32px_2.png"))); // NOI18N
        btnRefreshSiswaKelas10.setText("Refresh");
        btnRefreshSiswaKelas10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshSiswaKelas10.setOpaque(false);
        btnRefreshSiswaKelas10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshSiswaKelas10ActionPerformed(evt);
            }
        });

        lblTotalCariSiswa10.setText("Total : 0");

        tblSiswaKelas10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIS", "Nama Siswa", "Kelamin", "Email", "Alamat", "Telepon", "Kelas"
            }
        ));
        tblSiswaKelas10.setGridColor(new java.awt.Color(0, 204, 204));
        tblSiswaKelas10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaKelas10MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSiswaKelas10);

        cmbJurusanKelas10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KGSP", "DPIB", "TEDK", "SIJA", "TTL", "TFLM", "TMPO", "TPL" }));
        cmbJurusanKelas10.setEnabled(false);
        cmbJurusanKelas10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJurusanKelas10ItemStateChanged(evt);
            }
        });

        cmbABKelas10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B" }));
        cmbABKelas10.setEnabled(false);
        cmbABKelas10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbABKelas10ItemStateChanged(evt);
            }
        });

        ckBoxFilterKelas10.setText("Filter");
        ckBoxFilterKelas10.setOpaque(false);
        ckBoxFilterKelas10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckBoxFilterKelas10ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlSiswaKelas10Layout = new javax.swing.GroupLayout(pnlSiswaKelas10);
        pnlSiswaKelas10.setLayout(pnlSiswaKelas10Layout);
        pnlSiswaKelas10Layout.setHorizontalGroup(
            pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSiswaKelas10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(pnlSiswaKelas10Layout.createSequentialGroup()
                        .addGroup(pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditSiswa)
                            .addGroup(pnlSiswaKelas10Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ckBoxFilterKelas10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbJurusanKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbABKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlSiswaKelas10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblTotalCariSiswa10)
                                .addGap(93, 93, 93)
                                .addComponent(lblTombol1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefreshSiswaKelas10))
                            .addGroup(pnlSiswaKelas10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFilterKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                                .addComponent(txtCariSiswaKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCariSiswaKelas10)))))
                .addContainerGap())
        );
        pnlSiswaKelas10Layout.setVerticalGroup(
            pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSiswaKelas10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCariSiswaKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCariSiswaKelas10)
                        .addComponent(cmbJurusanKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbABKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFilterKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ckBoxFilterKelas10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalCariSiswa10)
                        .addComponent(lblTombol1))
                    .addComponent(btnRefreshSiswaKelas10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPanelSiswa.addTab("Kelas 10", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_10_32px_1.png")), pnlSiswaKelas10); // NOI18N

        pnlSiswaKelas11.setBackground(new java.awt.Color(0, 204, 204));
        pnlSiswaKelas11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlSiswaKelas11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlSiswaKelas11MouseEntered(evt);
            }
        });

        btnEditSiswaKelas11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Edit_Property_32px_1.png"))); // NOI18N
        btnEditSiswaKelas11.setText("  Edit Data Siswa");
        btnEditSiswaKelas11.setToolTipText("Edit Data Siswa Kelas 11");
        btnEditSiswaKelas11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditSiswaKelas11.setOpaque(false);
        btnEditSiswaKelas11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSiswaKelas11ActionPerformed(evt);
            }
        });

        txtCariSiswaKelas11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariSiswaKelas11.setToolTipText("Masukkan Kata Pencarian Mahasiswa");
        txtCariSiswaKelas11.setBorder(null);
        txtCariSiswaKelas11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariSiswaKelas11KeyTyped(evt);
            }
        });

        btnCariSiswaKelas11.setText("Cari");
        btnCariSiswaKelas11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariSiswaKelas11.setOpaque(false);
        btnCariSiswaKelas11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariSiswaKelas11MouseClicked(evt);
            }
        });

        btnRefreshSiswaKelas11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Synchronize_32px_2.png"))); // NOI18N
        btnRefreshSiswaKelas11.setText("Refresh");
        btnRefreshSiswaKelas11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshSiswaKelas11.setOpaque(false);
        btnRefreshSiswaKelas11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshSiswaKelas11ActionPerformed(evt);
            }
        });

        lblTotalCariSiswa11.setText("Total : 0");

        tblSiswaKelas11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIS", "Nama Siswa", "Kelamin", "Email", "Alamat", "Telepon", "Kelas"
            }
        ));
        tblSiswaKelas11.setGridColor(new java.awt.Color(0, 204, 204));
        tblSiswaKelas11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaKelas11MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblSiswaKelas11);

        txtFilterKelas11.setEnabled(false);
        txtFilterKelas11.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtFilterKelas11InputMethodTextChanged(evt);
            }
        });

        ckBoxFilterKelas11.setText("Filter");
        ckBoxFilterKelas11.setOpaque(false);
        ckBoxFilterKelas11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckBoxFilterKelas11ItemStateChanged(evt);
            }
        });

        cmbJurusanKelas11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KGSP", "DPIB", "TEDK", "SIJA", "TTL", "TFLM", "TMPO", "TPL" }));
        cmbJurusanKelas11.setEnabled(false);
        cmbJurusanKelas11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJurusanKelas11ItemStateChanged(evt);
            }
        });

        cmbABKelas11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B" }));
        cmbABKelas11.setEnabled(false);
        cmbABKelas11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbABKelas11ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlSiswaKelas11Layout = new javax.swing.GroupLayout(pnlSiswaKelas11);
        pnlSiswaKelas11.setLayout(pnlSiswaKelas11Layout);
        pnlSiswaKelas11Layout.setHorizontalGroup(
            pnlSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSiswaKelas11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSiswaKelas11Layout.createSequentialGroup()
                        .addGroup(pnlSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditSiswaKelas11)
                            .addGroup(pnlSiswaKelas11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ckBoxFilterKelas11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbJurusanKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbABKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlSiswaKelas11Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblTotalCariSiswa11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefreshSiswaKelas11))
                            .addGroup(pnlSiswaKelas11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFilterKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                                .addComponent(txtCariSiswaKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCariSiswaKelas11))))
                    .addComponent(jScrollPane14))
                .addContainerGap())
        );
        pnlSiswaKelas11Layout.setVerticalGroup(
            pnlSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSiswaKelas11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariSiswaKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariSiswaKelas11)
                    .addComponent(cmbJurusanKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbABKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFilterKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckBoxFilterKelas11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditSiswaKelas11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalCariSiswa11))
                    .addComponent(btnRefreshSiswaKelas11))
                .addContainerGap())
        );

        tabPanelSiswa.addTab("Kelas 11", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_11_32px.png")), pnlSiswaKelas11); // NOI18N

        pnlSiswaKelas12.setBackground(new java.awt.Color(0, 204, 204));
        pnlSiswaKelas12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlSiswaKelas12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlSiswaKelas12MouseEntered(evt);
            }
        });

        btnEditSiswaKelas12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Edit_Property_32px_1.png"))); // NOI18N
        btnEditSiswaKelas12.setText("  Edit Data Siswa");
        btnEditSiswaKelas12.setToolTipText("Edit Data Siswa Kelas 12");
        btnEditSiswaKelas12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditSiswaKelas12.setOpaque(false);
        btnEditSiswaKelas12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSiswaKelas12ActionPerformed(evt);
            }
        });

        txtCariSiswaKelas12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariSiswaKelas12.setToolTipText("Masukkan Kata Pencarian Mahasiswa");
        txtCariSiswaKelas12.setBorder(null);
        txtCariSiswaKelas12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariSiswaKelas12KeyTyped(evt);
            }
        });

        btnCariSiswaKelas12.setText("Cari");
        btnCariSiswaKelas12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariSiswaKelas12.setOpaque(false);
        btnCariSiswaKelas12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariSiswaKelas12MouseClicked(evt);
            }
        });

        btnRefreshSiswaKelas12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Synchronize_32px_2.png"))); // NOI18N
        btnRefreshSiswaKelas12.setText("Refresh");
        btnRefreshSiswaKelas12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshSiswaKelas12.setOpaque(false);
        btnRefreshSiswaKelas12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshSiswaKelas12ActionPerformed(evt);
            }
        });

        lblTotalCariSiswa12.setText("Total : 0");

        ckBoxFilterKelas12.setText("Filter");
        ckBoxFilterKelas12.setOpaque(false);
        ckBoxFilterKelas12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckBoxFilterKelas12ItemStateChanged(evt);
            }
        });

        cmbJurusanKelas12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KGSP", "DPIB", "TEDK", "SIJA", "TTL", "TFLM", "TMPO", "TPL" }));
        cmbJurusanKelas12.setEnabled(false);
        cmbJurusanKelas12.setOpaque(false);
        cmbJurusanKelas12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJurusanKelas12ItemStateChanged(evt);
            }
        });

        cmbABKelas12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B" }));
        cmbABKelas12.setEnabled(false);
        cmbABKelas12.setOpaque(false);
        cmbABKelas12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbABKelas12ItemStateChanged(evt);
            }
        });

        txtFilterKelas12.setBackground(new java.awt.Color(231, 235, 238));
        txtFilterKelas12.setEnabled(false);
        txtFilterKelas12.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtFilterKelas12InputMethodTextChanged(evt);
            }
        });

        tblSiswaKelas12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIS", "Nama Siswa", "Kelamin", "Email", "Alamat", "Telepon", "Kelas"
            }
        ));
        tblSiswaKelas12.setGridColor(new java.awt.Color(0, 204, 204));
        tblSiswaKelas12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaKelas12MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblSiswaKelas12);

        javax.swing.GroupLayout pnlSiswaKelas12Layout = new javax.swing.GroupLayout(pnlSiswaKelas12);
        pnlSiswaKelas12.setLayout(pnlSiswaKelas12Layout);
        pnlSiswaKelas12Layout.setHorizontalGroup(
            pnlSiswaKelas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSiswaKelas12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSiswaKelas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSiswaKelas12Layout.createSequentialGroup()
                        .addComponent(btnEditSiswaKelas12)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalCariSiswa12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefreshSiswaKelas12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSiswaKelas12Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(ckBoxFilterKelas12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbJurusanKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbABKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFilterKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(txtCariSiswaKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCariSiswaKelas12))
                    .addComponent(jScrollPane15))
                .addContainerGap())
        );
        pnlSiswaKelas12Layout.setVerticalGroup(
            pnlSiswaKelas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSiswaKelas12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSiswaKelas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariSiswaKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariSiswaKelas12)
                    .addComponent(cmbJurusanKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbABKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFilterKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckBoxFilterKelas12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSiswaKelas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlSiswaKelas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditSiswaKelas12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalCariSiswa12))
                    .addComponent(btnRefreshSiswaKelas12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPanelSiswa.addTab("Kelas 12", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_12_32px.png")), pnlSiswaKelas12); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelSiswa)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelSiswa)
        );

        tabPanelUtama.addTab("Data Siswa  ", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Student_Male_32px.png")), jPanel2); // NOI18N

        panelBuku.setBackground(new java.awt.Color(15, 184, 253));
        panelBuku.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Buku", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBukuMouseEntered(evt);
            }
        });

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
        tblBuku.setGridColor(new java.awt.Color(15, 184, 253));
        tblBuku.setRowMargin(0);
        tblBuku.setSelectionBackground(new java.awt.Color(15, 184, 253));
        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBuku);

        btnEditBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Edit_Property_32px_1.png"))); // NOI18N
        btnEditBuku.setText("Edit Data Buku");
        btnEditBuku.setToolTipText("Edit Data Buku");
        btnEditBuku.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditBuku.setOpaque(false);
        btnEditBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBukuActionPerformed(evt);
            }
        });

        btnRefreshBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Synchronize_32px_2.png"))); // NOI18N
        btnRefreshBuku.setText("Refresh");
        btnRefreshBuku.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshBuku.setOpaque(false);
        btnRefreshBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshBukuActionPerformed(evt);
            }
        });

        btnCariBuku.setText("Cari");
        btnCariBuku.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariBuku.setOpaque(false);
        btnCariBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariBukuMouseClicked(evt);
            }
        });

        txtCariBuku.setBackground(new java.awt.Color(51, 153, 255));
        txtCariBuku.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariBuku.setBorder(null);
        txtCariBuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariBukuKeyTyped(evt);
            }
        });

        lblTotalBuku.setText("Total :");

        javax.swing.GroupLayout panelBukuLayout = new javax.swing.GroupLayout(panelBuku);
        panelBuku.setLayout(panelBukuLayout);
        panelBukuLayout.setHorizontalGroup(
            panelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBukuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBukuLayout.createSequentialGroup()
                        .addComponent(btnEditBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefreshBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBukuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCariBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariBuku)))
                .addContainerGap())
        );
        panelBukuLayout.setVerticalGroup(
            panelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBukuLayout.createSequentialGroup()
                .addGroup(panelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCariBuku)
                    .addComponent(txtCariBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefreshBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalBuku))
                .addContainerGap())
        );

        tabPanelUtama.addTab("Data Buku   ", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Book_Shelf_32px.png")), panelBuku); // NOI18N

        tabPanelPeminjaman.setBackground(java.awt.Color.orange);
        tabPanelPeminjaman.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabPanelPeminjaman.setToolTipText("");
        tabPanelPeminjaman.setOpaque(true);

        pnlPeminjaman.setBackground(new java.awt.Color(0, 204, 204));
        pnlPeminjaman.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Peminjaman", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtCariPeminjaman.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariPeminjaman.setToolTipText("Masukkan Kata Pencarian Peminjaman");
        txtCariPeminjaman.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariPeminjamanKeyTyped(evt);
            }
        });

        btnCariPeminjaman.setText("Cari");
        btnCariPeminjaman.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariPeminjaman.setOpaque(false);
        btnCariPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariPeminjamanMouseClicked(evt);
            }
        });

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
        tblPeminjaman.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane3.setViewportView(tblPeminjaman);

        btnEditPeminjaman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Edit_Property_32px_1.png"))); // NOI18N
        btnEditPeminjaman.setText("Pinjam Buku");
        btnEditPeminjaman.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditPeminjaman.setOpaque(false);
        btnEditPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPeminjamanActionPerformed(evt);
            }
        });

        btnRefreshPeminjaman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Synchronize_32px_2.png"))); // NOI18N
        btnRefreshPeminjaman.setText("Refresh");
        btnRefreshPeminjaman.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshPeminjaman.setOpaque(false);
        btnRefreshPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPeminjamanActionPerformed(evt);
            }
        });

        lblJumlahPeminjaman.setText("Total : ");

        javax.swing.GroupLayout pnlPeminjamanLayout = new javax.swing.GroupLayout(pnlPeminjaman);
        pnlPeminjaman.setLayout(pnlPeminjamanLayout);
        pnlPeminjamanLayout.setHorizontalGroup(
            pnlPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPeminjamanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPeminjamanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCariPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPeminjamanLayout.createSequentialGroup()
                        .addComponent(btnEditPeminjaman)
                        .addGap(18, 18, 18)
                        .addComponent(lblJumlahPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237)
                        .addComponent(btnRefreshPeminjaman, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlPeminjamanLayout.setVerticalGroup(
            pnlPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPeminjamanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariPeminjaman))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefreshPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJumlahPeminjaman))
                .addContainerGap())
        );

        tabPanelPeminjaman.addTab("Peminjaman   ", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Reading_32px_1.png")), pnlPeminjaman); // NOI18N

        pnlPengembalian.setBackground(new java.awt.Color(0, 204, 204));
        pnlPengembalian.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pengembalian", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        pnlPengembalian.setOpaque(false);
        pnlPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlPengembalianMouseEntered(evt);
            }
        });

        pnlDipinjam.setBackground(new java.awt.Color(153, 153, 153));

        jPanel16.setBackground(new java.awt.Color(0, 204, 204));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DiPinjam", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblDiPinjam.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDiPinjam.setGridColor(new java.awt.Color(0, 204, 204));
        tblDiPinjam.setRowMargin(0);
        tblDiPinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiPinjamMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDiPinjamMouseReleased(evt);
            }
        });
        jScrollPane13.setViewportView(tblDiPinjam);

        txtCariDipinjam.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariDipinjam.setToolTipText("Masukkan Kata Pencarian Peminjaman");
        txtCariDipinjam.setBorder(null);
        txtCariDipinjam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariDipinjamKeyTyped(evt);
            }
        });

        btnCariDiPinjam.setText("Cari");
        btnCariDiPinjam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariDiPinjam.setOpaque(false);
        btnCariDiPinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariDiPinjamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(txtCariDipinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCariDiPinjam)))
                .addGap(50, 50, 50))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariDipinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariDiPinjam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnKembalikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Forward_32px.png"))); // NOI18N
        btnKembalikan.setToolTipText("Kembalikan Buku yang Dipinjam");
        btnKembalikan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKembalikan.setOpaque(false);
        btnKembalikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembalikanActionPerformed(evt);
            }
        });

        btnKembalikanSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Double_Right_32px.png"))); // NOI18N
        btnKembalikanSemua.setToolTipText("Kembalikan Semua Buku yang Dipinjam");
        btnKembalikanSemua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKembalikanSemua.setEnabled(false);
        btnKembalikanSemua.setOpaque(false);
        btnKembalikanSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembalikanSemuaActionPerformed(evt);
            }
        });

        btnBatalKembalikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Back_32px.png"))); // NOI18N
        btnBatalKembalikan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatalKembalikan.setOpaque(false);
        btnBatalKembalikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalKembalikanActionPerformed(evt);
            }
        });

        btnBatalKembalikanSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Double_Left_32px.png"))); // NOI18N
        btnBatalKembalikanSemua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatalKembalikanSemua.setEnabled(false);
        btnBatalKembalikanSemua.setOpaque(false);
        btnBatalKembalikanSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalKembalikanSemuaActionPerformed(evt);
            }
        });

        btnRefreshPengembalian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Synchronize_32px_2.png"))); // NOI18N
        btnRefreshPengembalian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshPengembalian.setOpaque(false);
        btnRefreshPengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPengembalianActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(0, 204, 204));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dikembalikan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblDiKembalikan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDiKembalikan.setGridColor(new java.awt.Color(0, 204, 204));
        tblDiKembalikan.setRowMargin(0);
        tblDiKembalikan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiKembalikanMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDiKembalikanMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblDiKembalikan);

        txtCariDiKembalikan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariDiKembalikan.setToolTipText("Masukkan Kata Pencarian Peminjaman");
        txtCariDiKembalikan.setBorder(null);
        txtCariDiKembalikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariDiKembalikanKeyTyped(evt);
            }
        });

        btnCariDiKembalikan.setText("Cari");
        btnCariDiKembalikan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariDiKembalikan.setOpaque(false);
        btnCariDiKembalikan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariDiKembalikanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCariDiKembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCariDiKembalikan)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariDiKembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariDiKembalikan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDipinjamLayout = new javax.swing.GroupLayout(pnlDipinjam);
        pnlDipinjam.setLayout(pnlDipinjamLayout);
        pnlDipinjamLayout.setHorizontalGroup(
            pnlDipinjamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDipinjamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDipinjamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDipinjamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnBatalKembalikan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnKembalikan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnKembalikanSemua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(pnlDipinjamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnBatalKembalikanSemua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnRefreshPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDipinjamLayout.setVerticalGroup(
            pnlDipinjamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDipinjamLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(btnKembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKembalikanSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBatalKembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatalKembalikanSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefreshPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(pnlDipinjamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDipinjamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPengembalianLayout = new javax.swing.GroupLayout(pnlPengembalian);
        pnlPengembalian.setLayout(pnlPengembalianLayout);
        pnlPengembalianLayout.setHorizontalGroup(
            pnlPengembalianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDipinjam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlPengembalianLayout.setVerticalGroup(
            pnlPengembalianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDipinjam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPanelPeminjaman.addTab("Pengembalian", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Reading_32px_1.png")), pnlPengembalian); // NOI18N

        pnKeterlambatan.setBackground(new java.awt.Color(0, 204, 204));
        pnKeterlambatan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Keterlambatan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtCariKeterlambatan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariKeterlambatan.setToolTipText("Masukkan Kata Pencarian Peminjaman");
        txtCariKeterlambatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeterlambatanKeyTyped(evt);
            }
        });

        tbnCariKeterlambatan.setText("Cari");
        tbnCariKeterlambatan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbnCariKeterlambatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnCariKeterlambatanMouseClicked(evt);
            }
        });

        tblKeterlambatan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKeterlambatan.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane5.setViewportView(tblKeterlambatan);

        btnRefreshKeterlambatan.setText("Refresh");
        btnRefreshKeterlambatan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshKeterlambatan.setOpaque(false);
        btnRefreshKeterlambatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshKeterlambatanActionPerformed(evt);
            }
        });

        lblJumlahKeterlambatan.setText("Total : ");

        javax.swing.GroupLayout pnKeterlambatanLayout = new javax.swing.GroupLayout(pnKeterlambatan);
        pnKeterlambatan.setLayout(pnKeterlambatanLayout);
        pnKeterlambatanLayout.setHorizontalGroup(
            pnKeterlambatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKeterlambatanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKeterlambatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKeterlambatanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCariKeterlambatan, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbnCariKeterlambatan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKeterlambatanLayout.createSequentialGroup()
                        .addComponent(lblJumlahKeterlambatan, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefreshKeterlambatan)))
                .addContainerGap())
        );
        pnKeterlambatanLayout.setVerticalGroup(
            pnKeterlambatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKeterlambatanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKeterlambatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariKeterlambatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnCariKeterlambatan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnKeterlambatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefreshKeterlambatan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJumlahKeterlambatan))
                .addContainerGap())
        );

        tabPanelPeminjaman.addTab("Keterlambatan", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Error_32px_1.png")), pnKeterlambatan); // NOI18N

        pnlDenda.setBackground(new java.awt.Color(204, 0, 0));

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
        tblDenda.setGridColor(new java.awt.Color(204, 0, 0));
        tblDenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDendaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblDenda);

        btnBayarDenda.setText("Bayar Denda");
        btnBayarDenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBayarDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarDendaActionPerformed(evt);
            }
        });

        btnRefreshDenda.setText("  Segarkan Tabel");
        btnRefreshDenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshDendaActionPerformed(evt);
            }
        });

        btnCariDenda.setText("Cari");
        btnCariDenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariDenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariDendaMouseClicked(evt);
            }
        });

        txtCariDenda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariDenda.setToolTipText("Masukkan Kata Pencarian Peminjaman");
        txtCariDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariDendaActionPerformed(evt);
            }
        });
        txtCariDenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariDendaKeyTyped(evt);
            }
        });

        btnBayarDenda1.setText("Sudah Dibayar");
        btnBayarDenda1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBayarDenda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarDenda1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDendaLayout = new javax.swing.GroupLayout(pnlDenda);
        pnlDenda.setLayout(pnlDendaLayout);
        pnlDendaLayout.setHorizontalGroup(
            pnlDendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addGroup(pnlDendaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCariDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCariDenda))
                    .addGroup(pnlDendaLayout.createSequentialGroup()
                        .addComponent(btnBayarDenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBayarDenda1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefreshDenda)))
                .addContainerGap())
        );
        pnlDendaLayout.setVerticalGroup(
            pnlDendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariDenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBayarDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefreshDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBayarDenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabPanelPeminjaman.addTab("Denda            ", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_High_Priority_32px.png")), pnlDenda); // NOI18N

        javax.swing.GroupLayout panelPeminjamanLayout = new javax.swing.GroupLayout(panelPeminjaman);
        panelPeminjaman.setLayout(panelPeminjamanLayout);
        panelPeminjamanLayout.setHorizontalGroup(
            panelPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelPeminjaman)
        );
        panelPeminjamanLayout.setVerticalGroup(
            panelPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelPeminjaman)
        );

        tabPanelUtama.addTab("Peminjaman  ", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Books_32px_1.png")), panelPeminjaman); // NOI18N

        tabPanelLaporan.setBackground(java.awt.Color.orange);
        tabPanelLaporan.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabPanelLaporan.setToolTipText("");
        tabPanelLaporan.setOpaque(true);

        pnlLaporanBuku.setBackground(new java.awt.Color(15, 184, 253));
        pnlLaporanBuku.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Buku", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblLaporanBuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLaporanBuku.setGridColor(new java.awt.Color(15, 184, 253));
        jScrollPane9.setViewportView(tblLaporanBuku);

        btnRefreshLaporanBuku.setText("Refresh");
        btnRefreshLaporanBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshLaporanBukuMouseClicked(evt);
            }
        });

        btnCariLaporanBuku.setText("Cari");
        btnCariLaporanBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariLaporanBukuMouseClicked(evt);
            }
        });

        txtCariLaporanBuku.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariLaporanBuku.setBorder(null);
        txtCariLaporanBuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariLaporanBukuKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlLaporanBukuLayout = new javax.swing.GroupLayout(pnlLaporanBuku);
        pnlLaporanBuku.setLayout(pnlLaporanBukuLayout);
        pnlLaporanBukuLayout.setHorizontalGroup(
            pnlLaporanBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLaporanBukuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLaporanBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanBukuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlLaporanBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRefreshLaporanBuku, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanBukuLayout.createSequentialGroup()
                                .addComponent(txtCariLaporanBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariLaporanBuku)))))
                .addContainerGap())
        );
        pnlLaporanBukuLayout.setVerticalGroup(
            pnlLaporanBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLaporanBukuLayout.createSequentialGroup()
                .addGroup(pnlLaporanBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCariLaporanBuku)
                    .addComponent(txtCariLaporanBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefreshLaporanBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabPanelLaporan.addTab("Laporan Buku", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Increase_32px.png")), pnlLaporanBuku); // NOI18N

        tabPanelLaporanSiswa.setBackground(java.awt.Color.orange);
        tabPanelLaporanSiswa.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabPanelLaporanSiswa.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tabPanelLaporanSiswa.setOpaque(true);

        pnlLaporanSiswaKelas10.setBackground(new java.awt.Color(0, 204, 204));
        pnlLaporanSiswaKelas10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtCariLaporanSiswaKelas10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariLaporanSiswaKelas10.setToolTipText("Masukkan Kata Pencarian Mahasiswa");
        txtCariLaporanSiswaKelas10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariLaporanSiswaKelas10ActionPerformed(evt);
            }
        });
        txtCariLaporanSiswaKelas10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariLaporanSiswaKelas10KeyTyped(evt);
            }
        });

        btnCariLaporanSiswaKelas10.setText("Cari");
        btnCariLaporanSiswaKelas10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariLaporanSiswaKelas10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariLaporanSiswaKelas10MouseClicked(evt);
            }
        });

        tblLaporanSiswaKelas10.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLaporanSiswaKelas10.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane10.setViewportView(tblLaporanSiswaKelas10);

        btnRefreshLaporanSiswaKelas10.setText("Refresh");
        btnRefreshLaporanSiswaKelas10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshLaporanSiswaKelas10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshLaporanSiswaKelas10ActionPerformed(evt);
            }
        });

        jLabel9.setText("Ditemukan :");

        jLabel10.setText("0");

        javax.swing.GroupLayout pnlLaporanSiswaKelas10Layout = new javax.swing.GroupLayout(pnlLaporanSiswaKelas10);
        pnlLaporanSiswaKelas10.setLayout(pnlLaporanSiswaKelas10Layout);
        pnlLaporanSiswaKelas10Layout.setHorizontalGroup(
            pnlLaporanSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLaporanSiswaKelas10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLaporanSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanSiswaKelas10Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(pnlLaporanSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanSiswaKelas10Layout.createSequentialGroup()
                                .addComponent(txtCariLaporanSiswaKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCariLaporanSiswaKelas10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanSiswaKelas10Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 453, Short.MAX_VALUE)
                                .addComponent(btnRefreshLaporanSiswaKelas10)))))
                .addContainerGap())
        );
        pnlLaporanSiswaKelas10Layout.setVerticalGroup(
            pnlLaporanSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanSiswaKelas10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLaporanSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariLaporanSiswaKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariLaporanSiswaKelas10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLaporanSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefreshLaporanSiswaKelas10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLaporanSiswaKelas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)))
                .addContainerGap())
        );

        tabPanelLaporanSiswa.addTab("Kelas 10", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_10_32px_1.png")), pnlLaporanSiswaKelas10); // NOI18N

        pnlLaporanSiswaKelas11.setBackground(new java.awt.Color(0, 204, 204));
        pnlLaporanSiswaKelas11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtCariLaporanSiswaKelas11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariLaporanSiswaKelas11.setToolTipText("Masukkan Kata Pencarian Mahasiswa");
        txtCariLaporanSiswaKelas11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariLaporanSiswaKelas11ActionPerformed(evt);
            }
        });
        txtCariLaporanSiswaKelas11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariLaporanSiswaKelas11KeyTyped(evt);
            }
        });

        btnCariLaporanSiswaKelas11.setText("Cari");
        btnCariLaporanSiswaKelas11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariLaporanSiswaKelas11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariLaporanSiswaKelas11MouseClicked(evt);
            }
        });

        tblLaporanSiswaKelas11.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLaporanSiswaKelas11.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane11.setViewportView(tblLaporanSiswaKelas11);

        btnRefreshLaporanSiswaKelas11.setText("Refresh");
        btnRefreshLaporanSiswaKelas11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshLaporanSiswaKelas11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshLaporanSiswaKelas11ActionPerformed(evt);
            }
        });

        jLabel11.setText("Ditemukan :");

        jLabel12.setText("0");

        javax.swing.GroupLayout pnlLaporanSiswaKelas11Layout = new javax.swing.GroupLayout(pnlLaporanSiswaKelas11);
        pnlLaporanSiswaKelas11.setLayout(pnlLaporanSiswaKelas11Layout);
        pnlLaporanSiswaKelas11Layout.setHorizontalGroup(
            pnlLaporanSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLaporanSiswaKelas11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLaporanSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanSiswaKelas11Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(pnlLaporanSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanSiswaKelas11Layout.createSequentialGroup()
                                .addComponent(txtCariLaporanSiswaKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCariLaporanSiswaKelas11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanSiswaKelas11Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 453, Short.MAX_VALUE)
                                .addComponent(btnRefreshLaporanSiswaKelas11)))))
                .addContainerGap())
        );
        pnlLaporanSiswaKelas11Layout.setVerticalGroup(
            pnlLaporanSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanSiswaKelas11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLaporanSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariLaporanSiswaKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariLaporanSiswaKelas11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLaporanSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefreshLaporanSiswaKelas11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLaporanSiswaKelas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)))
                .addContainerGap())
        );

        tabPanelLaporanSiswa.addTab("Kelas 11", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_11_32px.png")), pnlLaporanSiswaKelas11); // NOI18N

        data_mahasiswa6.setBackground(new java.awt.Color(0, 204, 204));
        data_mahasiswa6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtCariLaporanSiswaKelas12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCariLaporanSiswaKelas12.setToolTipText("Masukkan Kata Pencarian Mahasiswa");
        txtCariLaporanSiswaKelas12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariLaporanSiswaKelas12ActionPerformed(evt);
            }
        });
        txtCariLaporanSiswaKelas12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariLaporanSiswaKelas12KeyTyped(evt);
            }
        });

        btnCariLaporanSiswaKelas12.setText("Cari");
        btnCariLaporanSiswaKelas12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariLaporanSiswaKelas12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariLaporanSiswaKelas12MouseClicked(evt);
            }
        });

        tblLaporanSiswaKelas12.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLaporanSiswaKelas12.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane12.setViewportView(tblLaporanSiswaKelas12);

        btnRefreshLaporanSiswaKelas12.setText("Refresh");
        btnRefreshLaporanSiswaKelas12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefreshLaporanSiswaKelas12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshLaporanSiswaKelas12ActionPerformed(evt);
            }
        });

        jLabel13.setText("Ditemukan :");

        jLabel14.setText("0");

        javax.swing.GroupLayout data_mahasiswa6Layout = new javax.swing.GroupLayout(data_mahasiswa6);
        data_mahasiswa6.setLayout(data_mahasiswa6Layout);
        data_mahasiswa6Layout.setHorizontalGroup(
            data_mahasiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_mahasiswa6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(data_mahasiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_mahasiswa6Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(data_mahasiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_mahasiswa6Layout.createSequentialGroup()
                                .addComponent(txtCariLaporanSiswaKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCariLaporanSiswaKelas12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_mahasiswa6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 453, Short.MAX_VALUE)
                                .addComponent(btnRefreshLaporanSiswaKelas12)))))
                .addContainerGap())
        );
        data_mahasiswa6Layout.setVerticalGroup(
            data_mahasiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_mahasiswa6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(data_mahasiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariLaporanSiswaKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariLaporanSiswaKelas12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(data_mahasiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefreshLaporanSiswaKelas12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(data_mahasiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)))
                .addContainerGap())
        );

        tabPanelLaporanSiswa.addTab("Kelas 12", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_12_32px.png")), data_mahasiswa6); // NOI18N

        tabPanelLaporan.addTab("Laporan Siswa", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Increase_32px.png")), tabPanelLaporanSiswa); // NOI18N

        javax.swing.GroupLayout panelLaporanLayout = new javax.swing.GroupLayout(panelLaporan);
        panelLaporan.setLayout(panelLaporanLayout);
        panelLaporanLayout.setHorizontalGroup(
            panelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelLaporan)
        );
        panelLaporanLayout.setVerticalGroup(
            panelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelLaporan, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tabPanelUtama.addTab("Laporan   ", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Increase_32px.png")), panelLaporan); // NOI18N

        tabPanelLainLain.setBackground(java.awt.Color.orange);
        tabPanelLainLain.setOpaque(true);

        pnlTentangkami.setBackground(new java.awt.Color(102, 102, 255));

        btnTentangKami.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_About_32px_1.png"))); // NOI18N
        btnTentangKami.setText("Kelompok kami");
        btnTentangKami.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTentangKami.setOpaque(false);
        btnTentangKami.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTentangKamiActionPerformed(evt);
            }
        });

        pnlKelompokKami.setBackground(new java.awt.Color(51, 153, 255));
        pnlKelompokKami.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblSiswa1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblSiswa1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSiswa1.setText("Kelompok 1");

        javax.swing.GroupLayout pnlKelompokKamiLayout = new javax.swing.GroupLayout(pnlKelompokKami);
        pnlKelompokKami.setLayout(pnlKelompokKamiLayout);
        pnlKelompokKamiLayout.setHorizontalGroup(
            pnlKelompokKamiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKelompokKamiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKelompokKamiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKelompokKamiLayout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jLabel17)
                        .addContainerGap(344, Short.MAX_VALUE))
                    .addGroup(pnlKelompokKamiLayout.createSequentialGroup()
                        .addComponent(lblSiswa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pnlKelompokKamiLayout.setVerticalGroup(
            pnlKelompokKamiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKelompokKamiLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(lblSiswa1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlTentangkamiLayout = new javax.swing.GroupLayout(pnlTentangkami);
        pnlTentangkami.setLayout(pnlTentangkamiLayout);
        pnlTentangkamiLayout.setHorizontalGroup(
            pnlTentangkamiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTentangkamiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlKelompokKami, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnTentangKami, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTentangkamiLayout.setVerticalGroup(
            pnlTentangkamiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTentangkamiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTentangkamiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTentangkamiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTentangKami, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlKelompokKami, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPanelLainLain.addTab("Tentang Kami", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_About_32px_1.png")), pnlTentangkami); // NOI18N

        pnlPengaturan.setBackground(new java.awt.Color(231, 115, 175));
        pnlPengaturan.setForeground(new java.awt.Color(255, 255, 255));

        pnlSettingInterface.setBackground(new java.awt.Color(231, 85, 181));
        pnlSettingInterface.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(rbtMetal);
        rbtMetal.setText("Metal");
        rbtMetal.setOpaque(false);

        buttonGroup1.add(rbtNimbus);
        rbtNimbus.setText("Nimbus");
        rbtNimbus.setOpaque(false);

        buttonGroup1.add(rbtMotif);
        rbtMotif.setText("Motif");
        rbtMotif.setOpaque(false);

        buttonGroup1.add(rbtWindows);
        rbtWindows.setSelected(true);
        rbtWindows.setText("Windows");
        rbtWindows.setOpaque(false);

        buttonGroup1.add(rbtWindowsClassic);
        rbtWindowsClassic.setText("Windows Classic");
        rbtWindowsClassic.setOpaque(false);

        btnUbahInterface.setText("Ok");
        btnUbahInterface.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahInterfaceActionPerformed(evt);
            }
        });

        jButton1.setText("Default");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSettingInterfaceLayout = new javax.swing.GroupLayout(pnlSettingInterface);
        pnlSettingInterface.setLayout(pnlSettingInterfaceLayout);
        pnlSettingInterfaceLayout.setHorizontalGroup(
            pnlSettingInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingInterfaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSettingInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSettingInterfaceLayout.createSequentialGroup()
                        .addComponent(rbtMetal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUbahInterface))
                    .addGroup(pnlSettingInterfaceLayout.createSequentialGroup()
                        .addGroup(pnlSettingInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtNimbus)
                            .addComponent(rbtMotif)
                            .addComponent(rbtWindows))
                        .addGap(0, 220, Short.MAX_VALUE))
                    .addGroup(pnlSettingInterfaceLayout.createSequentialGroup()
                        .addComponent(rbtWindowsClassic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        pnlSettingInterfaceLayout.setVerticalGroup(
            pnlSettingInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingInterfaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSettingInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtMetal)
                    .addComponent(btnUbahInterface))
                .addGroup(pnlSettingInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSettingInterfaceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtNimbus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtMotif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtWindows)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtWindowsClassic)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSettingInterfaceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );

        btnEditBukuAdvance.setText("Edit Data Buku Advance");
        btnEditBukuAdvance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBukuAdvanceActionPerformed(evt);
            }
        });

        btnEditBukuAdvance1.setText("Edit Data Siswa Advance");
        btnEditBukuAdvance1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBukuAdvance1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPengaturanLayout = new javax.swing.GroupLayout(pnlPengaturan);
        pnlPengaturan.setLayout(pnlPengaturanLayout);
        pnlPengaturanLayout.setHorizontalGroup(
            pnlPengaturanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPengaturanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPengaturanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSettingInterface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditBukuAdvance)
                    .addComponent(btnEditBukuAdvance1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPengaturanLayout.setVerticalGroup(
            pnlPengaturanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPengaturanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSettingInterface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnEditBukuAdvance)
                .addGap(35, 35, 35)
                .addComponent(btnEditBukuAdvance1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPanelLainLain.addTab("Pengaturan", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Gears_32px.png")), pnlPengaturan); // NOI18N

        lblTombol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Toggle_Off_32px.png"))); // NOI18N
        lblTombol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTombol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTombolMouseClicked(evt);
            }
        });

        lblTombol2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_Toggle_Off_32px_1.png"))); // NOI18N
        lblTombol2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTombol2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTombol2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTombol)
                    .addComponent(lblTombol2))
                .addContainerGap(864, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTombol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTombol2)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        tabPanelLainLain.addTab("Tambahan", jPanel3);

        javax.swing.GroupLayout panelLainLainLayout = new javax.swing.GroupLayout(panelLainLain);
        panelLainLain.setLayout(panelLainLainLayout);
        panelLainLainLayout.setHorizontalGroup(
            panelLainLainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelLainLain)
        );
        panelLainLainLayout.setVerticalGroup(
            panelLainLainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelLainLain)
        );

        tabPanelUtama.addTab("Lain Lain", new javax.swing.ImageIcon(getClass().getResource("/icon_32bit/icons8_View_More_32px_1.png")), panelLainLain); // NOI18N

        pnlTitleBar.setBackground(new java.awt.Color(51, 106, 134));

        Tanggal.setFont(new java.awt.Font("Digiface", 0, 24)); // NOI18N
        Tanggal.setText("0000-00-00");

        lblJam.setFont(new java.awt.Font("Digiface", 0, 20)); // NOI18N
        lblJam.setText("00:00");

        lblImageSMK.setFont(new java.awt.Font("stoNe", 0, 36)); // NOI18N
        lblImageSMK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logo (1).png"))); // NOI18N
        lblImageSMK.setToolTipText("SMK Negeri 2 Klaten");
        lblImageSMK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImageSMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImageSMKMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleBarLayout = new javax.swing.GroupLayout(pnlTitleBar);
        pnlTitleBar.setLayout(pnlTitleBarLayout);
        pnlTitleBarLayout.setHorizontalGroup(
            pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImageSMK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tanggal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblJam, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pnlTitleBarLayout.setVerticalGroup(
            pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTitleBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlTitleBarLayout.createSequentialGroup()
                        .addComponent(Tanggal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblJam))
                    .addComponent(lblImageSMK))
                .addContainerGap())
        );

        bottonPanel.setBackground(new java.awt.Color(59, 70, 78));

        javax.swing.GroupLayout bottonPanelLayout = new javax.swing.GroupLayout(bottonPanel);
        bottonPanel.setLayout(bottonPanelLayout);
        bottonPanelLayout.setHorizontalGroup(
            bottonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bottonPanelLayout.setVerticalGroup(
            bottonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        sidePanel.setBackground(java.awt.Color.orange);

        lblImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_64bit/icons8_Java_64px_1.png"))); // NOI18N
        lblImage1.setToolTipText("Java Language");
        lblImage1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImage1MousePressed(evt);
            }
        });

        lblImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_64bit/icons8_Source_Code_64px.png"))); // NOI18N
        lblImage2.setToolTipText("Code Editor");
        lblImage2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImage2MousePressed(evt);
            }
        });

        lblImage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_64bit/icons8_MySQL_64px.png"))); // NOI18N
        lblImage3.setToolTipText("MySQL DBMS");
        lblImage3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImage3MousePressed(evt);
            }
        });

        lblImage4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_64bit/icons8_Oracle_Logo_64px.png"))); // NOI18N
        lblImage4.setToolTipText("Oracle");
        lblImage4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImage4MousePressed(evt);
            }
        });

        lblImage5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_64bit/icons8_Java_Duke_64px.png"))); // NOI18N
        lblImage5.setToolTipText("Java Duke");
        lblImage5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImage5MousePressed(evt);
            }
        });

        lblLogOut.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblLogOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_64bit/icons8_Logout_Rounded_Left_64px.png"))); // NOI18N
        lblLogOut.setText("Log Out");
        lblLogOut.setToolTipText("Log Out");
        lblLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogOutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblImage3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblImage4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblImage5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage1)
                .addGap(12, 12, 12)
                .addComponent(lblImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImage3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImage4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImage5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogOut)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPanelUtama))
            .addComponent(pnlTitleBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlTitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabPanelUtama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBukuActionPerformed
        new formEditBuku(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnEditBukuActionPerformed

    private void btnRefreshBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshBukuActionPerformed
        tampilTblBuku();
    }//GEN-LAST:event_btnRefreshBukuActionPerformed

    private void btnCariBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariBukuMouseClicked
        cariBuku();
    }//GEN-LAST:event_btnCariBukuMouseClicked

    private void txtCariBukuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariBukuKeyTyped
        cariBuku();
    }//GEN-LAST:event_txtCariBukuKeyTyped

    private void btnCariPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariPeminjamanMouseClicked
        cariPeminjaman();
    }//GEN-LAST:event_btnCariPeminjamanMouseClicked

    private void btnEditPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPeminjamanActionPerformed
        new formEditPeminjaman(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnEditPeminjamanActionPerformed

    private void btnRefreshPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPeminjamanActionPerformed
        tampilTblPeminjaman();
    }//GEN-LAST:event_btnRefreshPeminjamanActionPerformed

    private void tbnCariKeterlambatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnCariKeterlambatanMouseClicked
        cariKeterlambatan();
    }//GEN-LAST:event_tbnCariKeterlambatanMouseClicked

    private void btnRefreshKeterlambatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshKeterlambatanActionPerformed
        tampilTblKeterlambatan();
    }//GEN-LAST:event_btnRefreshKeterlambatanActionPerformed

    private void btnEditSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSiswaActionPerformed
        new formEditSiswa(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnEditSiswaActionPerformed

    private void txtCariSiswaKelas10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariSiswaKelas10KeyTyped
        cariSiswa("X ");
        tampilJumlahCariSiswa();
    }//GEN-LAST:event_txtCariSiswaKelas10KeyTyped

    private void btnCariSiswaKelas10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariSiswaKelas10MouseClicked
        cariSiswa("X ");
        tampilJumlahCariSiswa();
    }//GEN-LAST:event_btnCariSiswaKelas10MouseClicked

    private void btnRefreshSiswaKelas10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshSiswaKelas10ActionPerformed
        txtCariSiswaKelas10.setText("");
        tampilTblSiswaKelas10();
    }//GEN-LAST:event_btnRefreshSiswaKelas10ActionPerformed

    private void txtCariKeterlambatanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeterlambatanKeyTyped
        cariKeterlambatan();
        if ("".equals(txtCariKeterlambatan.getText())) {
            tampilTblKeterlambatan();
        }
    }//GEN-LAST:event_txtCariKeterlambatanKeyTyped

    private void btnEditSiswaKelas12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSiswaKelas12ActionPerformed
        new formEditSiswa(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnEditSiswaKelas12ActionPerformed

    private void txtCariSiswaKelas12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariSiswaKelas12KeyTyped
        cariSiswa("XII ");
        tampilJumlahCariSiswa();
    }//GEN-LAST:event_txtCariSiswaKelas12KeyTyped

    private void btnCariSiswaKelas12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariSiswaKelas12MouseClicked
        cariSiswa("XII ");
        tampilJumlahCariSiswa();
    }//GEN-LAST:event_btnCariSiswaKelas12MouseClicked

    private void btnRefreshSiswaKelas12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshSiswaKelas12ActionPerformed
        txtCariSiswaKelas12.setText("");
        tampilTblSiswaKelas12();
    }//GEN-LAST:event_btnRefreshSiswaKelas12ActionPerformed

    private void btnCariLaporanBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLaporanBukuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariLaporanBukuMouseClicked

    private void txtCariLaporanBukuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariLaporanBukuKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanBukuKeyTyped

    private void txtCariLaporanSiswaKelas10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariLaporanSiswaKelas10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanSiswaKelas10ActionPerformed

    private void txtCariLaporanSiswaKelas10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariLaporanSiswaKelas10KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanSiswaKelas10KeyTyped

    private void btnCariLaporanSiswaKelas10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLaporanSiswaKelas10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariLaporanSiswaKelas10MouseClicked

    private void btnRefreshLaporanSiswaKelas10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshLaporanSiswaKelas10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshLaporanSiswaKelas10ActionPerformed

    private void txtCariLaporanSiswaKelas11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariLaporanSiswaKelas11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanSiswaKelas11ActionPerformed

    private void txtCariLaporanSiswaKelas11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariLaporanSiswaKelas11KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanSiswaKelas11KeyTyped

    private void btnCariLaporanSiswaKelas11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLaporanSiswaKelas11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariLaporanSiswaKelas11MouseClicked

    private void btnRefreshLaporanSiswaKelas11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshLaporanSiswaKelas11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshLaporanSiswaKelas11ActionPerformed

    private void txtCariLaporanSiswaKelas12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariLaporanSiswaKelas12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanSiswaKelas12ActionPerformed

    private void txtCariLaporanSiswaKelas12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariLaporanSiswaKelas12KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanSiswaKelas12KeyTyped

    private void btnCariLaporanSiswaKelas12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLaporanSiswaKelas12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariLaporanSiswaKelas12MouseClicked

    private void btnRefreshLaporanSiswaKelas12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshLaporanSiswaKelas12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshLaporanSiswaKelas12ActionPerformed

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBukuMouseClicked
        klikTabelBuku();
//        new formDetailBuku(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_tblBukuMouseClicked

    private void tblSiswaKelas10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaKelas10MouseClicked
//
        klikTabelSiswaKelas10();
    }//GEN-LAST:event_tblSiswaKelas10MouseClicked

    private void btnTentangKamiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTentangKamiActionPerformed
        new formAboutAnimation(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnTentangKamiActionPerformed

    private void tblDiKembalikanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiKembalikanMouseClicked
        if ("Admin".equalsIgnoreCase(level)) {
            btnBatalKembalikan.setEnabled(true);
            btnKembalikan.setEnabled(false);
            urut = tblDiKembalikan.getSelectedRow();
            urutan = ((String) tblDiKembalikan.getValueAt(urut, 0));
        }

    }//GEN-LAST:event_tblDiKembalikanMouseClicked


    private void tblDiPinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiPinjamMouseClicked
        if ("Admin".equalsIgnoreCase(level)) {
            btnKembalikan.setEnabled(true);
            btnBatalKembalikan.setEnabled(false);
            urut = tblDiPinjam.getSelectedRow();
            urutan = ((String) tblDiPinjam.getValueAt(urut, 0));
            btnKembalikan.setFocusTraversalKeysEnabled(true);
        }

    }//GEN-LAST:event_tblDiPinjamMouseClicked

    private void btnUbahInterfaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahInterfaceActionPerformed
        if (rbtMetal.isSelected()) {
            new ChangeTheme().setTheme(0);
            SwingUtilities.updateComponentTreeUI(rootPane);
        } else if (rbtNimbus.isSelected()) {
            new ChangeTheme().setTheme(1);
            SwingUtilities.updateComponentTreeUI(rootPane);
        } else if (rbtMotif.isSelected()) {
            new ChangeTheme().setTheme(2);
            SwingUtilities.updateComponentTreeUI(rootPane);
        } else if (rbtWindows.isSelected()) {
            new ChangeTheme().setTheme(3);
            SwingUtilities.updateComponentTreeUI(rootPane);
        } else if (rbtWindowsClassic.isSelected()) {
            new ChangeTheme().setTheme(4);
            SwingUtilities.updateComponentTreeUI(rootPane);
        }
    }//GEN-LAST:event_btnUbahInterfaceActionPerformed

    private void pnlSiswaKelas10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSiswaKelas10MouseEntered
        txtCariSiswaKelas10.requestFocus();
    }//GEN-LAST:event_pnlSiswaKelas10MouseEntered

    private void panelBukuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBukuMouseEntered
        txtCariBuku.requestFocus();
    }//GEN-LAST:event_panelBukuMouseEntered

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int result = JOptionPane.showConfirmDialog(null, "Yakin Ingin Keluar ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btnCariDiPinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariDiPinjamMouseClicked
        cariDiPinjam();
    }//GEN-LAST:event_btnCariDiPinjamMouseClicked

    private void btnCariDiKembalikanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariDiKembalikanMouseClicked
        cariDiKembalikan();
    }//GEN-LAST:event_btnCariDiKembalikanMouseClicked

    private void txtCariDipinjamKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariDipinjamKeyTyped
        cariDiPinjam();
    }//GEN-LAST:event_txtCariDipinjamKeyTyped

    private void cmbJurusanKelas10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJurusanKelas10ItemStateChanged
        txtFilterKelas10.setText((String) (cmbJurusanKelas10.getSelectedItem() + " " + (String) cmbABKelas10.getSelectedItem()));
        tampilTblSiswaKelas10();
    }//GEN-LAST:event_cmbJurusanKelas10ItemStateChanged

    private void cmbABKelas10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbABKelas10ItemStateChanged
        txtFilterKelas10.setText((String) (cmbJurusanKelas10.getSelectedItem() + " " + (String) cmbABKelas10.getSelectedItem()));
        tampilTblSiswaKelas10();
    }//GEN-LAST:event_cmbABKelas10ItemStateChanged

    private void ckBoxFilterKelas10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckBoxFilterKelas10ItemStateChanged
        if (ckBoxFilterKelas10.isSelected()) {
            cmbJurusanKelas10.setEnabled(true);
            cmbABKelas10.setEnabled(true);
            tampilTblSiswaKelas10();
        }
        if (ckBoxFilterKelas10.isSelected() == false) {
            cmbJurusanKelas10.setEnabled(false);
            cmbABKelas10.setEnabled(false);
            tampilTblSiswaKelas10();
        }
    }//GEN-LAST:event_ckBoxFilterKelas10ItemStateChanged

    private void txtFilterKelas10InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtFilterKelas10InputMethodTextChanged
        tampilTblSiswaKelas10();
    }//GEN-LAST:event_txtFilterKelas10InputMethodTextChanged

    private void cmbJurusan11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJurusan11ItemStateChanged
        txtFilterKelas11.setText((String) (cmbJurusanKelas11.getSelectedItem() + " " + (String) cmbABKelas11.getSelectedItem()));
        tampilTblSiswaKelas11();
    }//GEN-LAST:event_cmbJurusan11ItemStateChanged

    private void btnEditSiswaKelas11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSiswaKelas11ActionPerformed
        new formEditSiswa(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnEditSiswaKelas11ActionPerformed

    private void txtCariSiswaKelas11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariSiswaKelas11KeyTyped
        cariSiswa("XI ");
        tampilJumlahCariSiswa();
    }//GEN-LAST:event_txtCariSiswaKelas11KeyTyped

    private void btnCariSiswaKelas11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariSiswaKelas11MouseClicked
        cariSiswa("XI ");
        tampilJumlahCariSiswa();
    }//GEN-LAST:event_btnCariSiswaKelas11MouseClicked

    private void btnRefreshSiswaKelas11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshSiswaKelas11ActionPerformed
        txtCariSiswaKelas11.setText("");
        tampilTblSiswaKelas11();
    }//GEN-LAST:event_btnRefreshSiswaKelas11ActionPerformed

    private void tblSiswaKelas11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaKelas11MouseClicked
//
        klikTabelSiswaKelas11();
    }//GEN-LAST:event_tblSiswaKelas11MouseClicked

    private void txtFilterKelas11InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtFilterKelas11InputMethodTextChanged
        tampilTblSiswaKelas11();
    }//GEN-LAST:event_txtFilterKelas11InputMethodTextChanged

    private void ckBoxFilterKelas11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckBoxFilterKelas11ItemStateChanged
        if (ckBoxFilterKelas11.isSelected()) {
            cmbJurusanKelas11.setEnabled(true);
            cmbABKelas11.setEnabled(true);
            tampilTblSiswaKelas11();
        }
        if (ckBoxFilterKelas11.isSelected() == false) {
            cmbJurusanKelas11.setEnabled(false);
            cmbABKelas11.setEnabled(false);
            tampilTblSiswaKelas11();
        }
    }//GEN-LAST:event_ckBoxFilterKelas11ItemStateChanged

    private void cmbJurusanKelas11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJurusanKelas11ItemStateChanged
        txtFilterKelas11.setText((String) (cmbJurusanKelas11.getSelectedItem() + " " + (String) cmbABKelas11.getSelectedItem()));
        tampilTblSiswaKelas11();
    }//GEN-LAST:event_cmbJurusanKelas11ItemStateChanged

    private void cmbABKelas11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbABKelas11ItemStateChanged
        txtFilterKelas11.setText((String) (cmbJurusanKelas11.getSelectedItem() + " " + (String) cmbABKelas11.getSelectedItem()));
        tampilTblSiswaKelas11();
    }//GEN-LAST:event_cmbABKelas11ItemStateChanged

    private void btnBayarDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarDendaActionPerformed
        new formBayarDenda(this, true).setVisible(true);

    }//GEN-LAST:event_btnBayarDendaActionPerformed

    private void btnRefreshDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshDendaActionPerformed
        tampilTblDenda();
    }//GEN-LAST:event_btnRefreshDendaActionPerformed

    private void btnCariDendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariDendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariDendaMouseClicked

    private void txtCariDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariDendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariDendaActionPerformed

    private void txtCariDendaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariDendaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariDendaKeyTyped

    private void pnlPengembalianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPengembalianMouseEntered
        action1();
    }//GEN-LAST:event_pnlPengembalianMouseEntered

    private void ckBoxFilterKelas12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckBoxFilterKelas12ItemStateChanged
        if (ckBoxFilterKelas12.isSelected()) {
            cmbJurusanKelas12.setEnabled(true);
            cmbABKelas12.setEnabled(true);
            tampilTblSiswaKelas12();
        }
        if (ckBoxFilterKelas12.isSelected() == false) {
            cmbJurusanKelas12.setEnabled(false);
            cmbABKelas12.setEnabled(false);
            tampilTblSiswaKelas12();
        }
    }//GEN-LAST:event_ckBoxFilterKelas12ItemStateChanged

    private void cmbJurusanKelas12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJurusanKelas12ItemStateChanged
        txtFilterKelas12.setText((String) (cmbJurusanKelas12.getSelectedItem() + " " + (String) cmbABKelas12.getSelectedItem()));
        tampilTblSiswaKelas12();
    }//GEN-LAST:event_cmbJurusanKelas12ItemStateChanged

    private void cmbABKelas12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbABKelas12ItemStateChanged
        txtFilterKelas12.setText((String) (cmbJurusanKelas12.getSelectedItem() + " " + (String) cmbABKelas12.getSelectedItem()));
        tampilTblSiswaKelas12();
    }//GEN-LAST:event_cmbABKelas12ItemStateChanged

    private void txtFilterKelas12InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtFilterKelas12InputMethodTextChanged
        tampilTblSiswaKelas12();
    }//GEN-LAST:event_txtFilterKelas12InputMethodTextChanged

    private void btnEditBukuAdvanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBukuAdvanceActionPerformed
        String password = JOptionPane.showInputDialog(null, "Masukan Password", "Security", JOptionPane.OK_OPTION);
        if ("Lucifer".equals(password)) {
            try {
                new formEditBukuLoop(this, rootPaneCheckingEnabled).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(formHomeEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEditBukuAdvanceActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        waktu.start();
    }//GEN-LAST:event_formFocusLost

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        waktu.stop();
    }//GEN-LAST:event_formFocusGained

    private void btnRefreshPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPengembalianActionPerformed
        tampilTblDiPinjam();
        tampilTblDiKembalikan();
        awal();
        btnKembalikan.setEnabled(false);
        btnBatalKembalikan.setEnabled(false);
        txtCariDiKembalikan.setText("");
        txtCariDipinjam.setText("");
    }//GEN-LAST:event_btnRefreshPengembalianActionPerformed

    private void btnBatalKembalikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalKembalikanActionPerformed
        String idBuku = null;
        if (tblDiKembalikan.getSelectedRowCount() == 1) {
            try {
                sql = "select idBuku from tblPeminjaman where idPeminjaman = " + urutan;
                stat = con.createStatement();
                result = stat.executeQuery(sql);
                while (result.next()) {
                    idBuku = result.getString("idBuku");
                }

                sql = "update tblBuku set stok = (select (stok-1) where idbuku = " + idBuku + ") where idbuku = " + idBuku + ";";
                stat = con.createStatement();
                stat.executeUpdate(sql);

                sql = "update tblPeminjaman "
                        + "set status = 'Dipinjam', "
                        + "tanggalPengembalian = NULL "
                        + "where idPeminjaman = '" + urutan + "'";
                stat = con.createStatement();
                stat.executeUpdate(sql);
                tampilTblDiPinjam();
                tampilTblDiKembalikan();
                cariDiKembalikan();
                btnBatalKembalikan.setEnabled(false);
                action1();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Mengembalikan GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            for (String idPeminjaman : terpilih) {
                try {
                    sql = "select idBuku from tblPeminjaman where idPeminjaman='" + idPeminjaman + "';";
                    stat = con.createStatement();
                    result = stat.executeQuery(sql);
                    while (result.next()) {
                        idBuku = result.getString("idBuku");
                    }

                    sql = "update tblBuku set stok = (select (stok-1) where idBuku = " + idBuku + ") where idBuku = '" + idBuku + "';";
                    stat = con.createStatement();
                    stat.execute(sql);

                    sql = "update tblPeminjaman "
                            + "set status='Dipinjam', "
                            + "tanggalPengembalian = NULL "
                            + "where idPeminjaman = '" + idPeminjaman + "'";
                    stat = con.createStatement();
                    stat.executeUpdate(sql);
                    tampilTblDiPinjam();
                    tampilTblDiKembalikan();
                    cariDiKembalikan();
                    btnBatalKembalikan.setEnabled(false);
                    action1();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Mengembalikan GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_btnBatalKembalikanActionPerformed

    private void btnBatalKembalikanSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalKembalikanSemuaActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int Result = JOptionPane.showConfirmDialog(null, "Yakin ingin Pinjam Lagi Semua ?", "Pindah", dialog);
        if (Result == 0) {
            try {
                String[] idBuku = new String[tblDiKembalikan.getRowCount()];
                for (int i = 0; i < idBuku.length; i++) {
                    idBuku[i] = (String) (tblDiKembalikan.getValueAt(i, 4));
                }
                for (String id : idBuku) {

                    sql = "update tblBuku set stok = (select (stok-1) where idBuku = " + id + ") where idBuku ='" + id + "';";
                    stat = con.createStatement();
                    stat.executeUpdate(sql);
                }

                sql = "Update tblPeminjaman "
                        + "set status = 'Dipinjam', "
                        + "tanggalPengembalian = NULL "
                        + "where status = 'Dikembalikan'";
                stat = con.createStatement();
                stat.executeUpdate(sql);
                tampilTblDiPinjam();
                tampilTblDiKembalikan();
                btnBatalKembalikanSemua.setEnabled(false);
                btnKembalikanSemua.setEnabled(true);
                action1();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

    }//GEN-LAST:event_btnBatalKembalikanSemuaActionPerformed

    private void btnKembalikanSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembalikanSemuaActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int Result = JOptionPane.showConfirmDialog(null, "Yakin ingin Kembalikan Semua ?", "Pindah", dialog);
        if (Result == 0) {
            try {
                String[] idBuku = new String[tblDiPinjam.getRowCount()];
                for (int i = 0; i < idBuku.length; i++) {
                    idBuku[i] = (String) (tblDiPinjam.getValueAt(i, 2));
                }
                for (String id : idBuku) {
                    sql = "update tblBuku set stok = (select (stok+1) where idBuku = " + id + ") where idBuku ='" + id + "';";
                    stat = con.createStatement();
                    stat.executeUpdate(sql);
                }

                sql = "update tblPeminjaman "
                        + "set status='Dikembalikan' , "
                        + "tanggalPengembalian =(select now())  "
                        + "where status='Dipinjam'";
                stat = con.createStatement();
                stat.executeUpdate(sql);
                tampilTblDiPinjam();
                tampilTblDiKembalikan();
                btnKembalikan.setEnabled(false);
                btnKembalikanSemua.setEnabled(false);
                btnBatalKembalikanSemua.setEnabled(true);
                action1();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }//GEN-LAST:event_btnKembalikanSemuaActionPerformed

    private void btnKembalikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembalikanActionPerformed
        String idBuku = null;

        if (tblDiPinjam.getSelectedRowCount() == 1) {
            try {
                sql = "select idBuku from tblPeminjaman where idPeminjaman = " + urutan;
                stat = con.createStatement();
                result = stat.executeQuery(sql);
                while (result.next()) {
                    idBuku = result.getString("idBuku");
                }

                sql = "update tblPeminjaman "
                        + "set status = 'Dikembalikan' , "
                        + "tanggalPengembalian = (select now()) "
                        + "where idPeminjaman='" + urutan + "'";
                stat = con.createStatement();
                stat.executeUpdate(sql);
                tampilTblDiPinjam();
                tampilTblDiKembalikan();
                btnKembalikan.setEnabled(false);
                action1();

                sql = "update tblBuku set stok =(select (stok+1) where idbuku=" + idBuku + ") where idbuku=" + idBuku + ";";
                stat = con.createStatement();
                stat.executeUpdate(sql);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Menyimpan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {

            for (String idPeminjaman : terpilih) {
                try {
                    sql = "select idBuku from tblPeminjaman where idPeminjaman='" + idPeminjaman + "';";
                    stat = con.createStatement();
                    result = stat.executeQuery(sql);
                    while (result.next()) {
                        idBuku = result.getString("idBuku");
                    }

                    sql = "update tblBuku set stok = (select (stok+1) where idBuku = " + idBuku + " ) where idBuku='" + idBuku + "';";
                    stat = con.createStatement();
                    stat.execute(sql);

                    sql = "update tblPeminjaman "
                            + "set status='Dikembalikan' , "
                            + "tanggalPengembalian =(select now()) "
                            + "where idPeminjaman='" + idPeminjaman + "'";
                    stat = con.createStatement();
                    stat.executeUpdate(sql);

                    tampilTblDiPinjam();
                    tampilTblDiKembalikan();
                    btnKembalikan.setEnabled(false);
                    action1();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Menyimpan data GAGAL\n" + e, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }

    }//GEN-LAST:event_btnKembalikanActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        tabHome();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void tabPanelUtamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPanelUtamaMouseClicked
        tabHome();
        if (tabPanelUtama.isEnabledAt(1) && tabPanelSiswa.isEnabledAt(0)) {
            tampilTblSiswaKelas10();
            tampilJumlahCariSiswa();
        }
        if (tabPanelUtama.isEnabledAt(1) && tabPanelSiswa.isEnabledAt(1)) {
            tampilTblSiswaKelas11();
            tampilJumlahCariSiswa();
        }
        if (tabPanelUtama.isEnabledAt(1) && tabPanelSiswa.isEnabledAt(2)) {
            tampilTblSiswaKelas12();
            tampilJumlahCariSiswa();
        }
        if (tabPanelUtama.isEnabledAt(2)) {
            tampilTblBuku();
        }
        if (tabPanelUtama.isEnabledAt(3) && tabPanelPeminjaman.isEnabledAt(0)) {
            tampilTblPeminjaman();
        }
        if (tabPanelUtama.isEnabledAt(3) && tabPanelPeminjaman.isEnabledAt(1)) {
            tampilTblDiPinjam();
            tampilTblDiKembalikan();
        }
        if (tabPanelUtama.isEnabledAt(3) && tabPanelPeminjaman.isEnabledAt(2)) {
            tampilTblKeterlambatan();
        }
        if (tabPanelUtama.isEnabledAt(3) && tabPanelPeminjaman.isEnabledAt(3)) {
            tampilTblDenda();
        }
    }//GEN-LAST:event_tabPanelUtamaMouseClicked

    private void tblSiswaKelas12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaKelas12MouseClicked
        klikTabelSiswaKelas12();
    }//GEN-LAST:event_tblSiswaKelas12MouseClicked

    private void txtCariDiKembalikanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariDiKembalikanKeyTyped
        cariDiKembalikan();
    }//GEN-LAST:event_txtCariDiKembalikanKeyTyped

    private void txtCariPeminjamanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariPeminjamanKeyTyped
        cariPeminjaman();
        if ("".equals(txtCariPeminjaman.getText())) {
            tampilTblPeminjaman();
        }
    }//GEN-LAST:event_txtCariPeminjamanKeyTyped

    private void jPanel25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel25MousePressed
        new formAboutAnimation(this, true).setVisible(true);
    }//GEN-LAST:event_jPanel25MousePressed

    private void pnlSiswaKelas11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSiswaKelas11MouseEntered
        txtCariSiswaKelas11.requestFocus();
    }//GEN-LAST:event_pnlSiswaKelas11MouseEntered

    private void pnlSiswaKelas12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSiswaKelas12MouseEntered
        txtCariSiswaKelas12.requestFocus();
    }//GEN-LAST:event_pnlSiswaKelas12MouseEntered

    private void btnRefreshLaporanBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshLaporanBukuMouseClicked
        tampilTblBuku();
    }//GEN-LAST:event_btnRefreshLaporanBukuMouseClicked

    private void jPanel12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MousePressed
        tabPanelUtama.setSelectedIndex(1);
        tampilTblSiswaKelas10();
    }//GEN-LAST:event_jPanel12MousePressed

    private void jPanel19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MousePressed
        tabPanelUtama.setSelectedIndex(2);
        tampilTblBuku();
    }//GEN-LAST:event_jPanel19MousePressed

    private void jPanel23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MousePressed
        tabPanelUtama.setSelectedIndex(3);
        tampilTblPeminjaman();
    }//GEN-LAST:event_jPanel23MousePressed

    private void lblTombolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTombolMouseClicked
        if (lblTombol.getIcon() == iconOn) {
            this.lblTombol.setIcon((Icon) iconOff);
            this.lblTombol.setIcon(iconOff);
        } else {
            this.lblTombol.setIcon(iconOn);
        }
    }//GEN-LAST:event_lblTombolMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        rbtWindows.setSelected(true);
        new ChangeTheme().setTheme(3);
        SwingUtilities.updateComponentTreeUI(rootPane);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lblTombol2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTombol2MouseClicked
        if (lblTombol2.getIcon() == iconOn2) {
            this.lblTombol2.setIcon(iconCenter);
            this.lblTombol2.setIcon(iconOff2);
        } else {
            this.lblTombol2.setIcon(iconCenter);
            this.lblTombol2.setIcon(iconOn2);
        }
    }//GEN-LAST:event_lblTombol2MouseClicked

    private void lblLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogOutMouseClicked
        int result = JOptionPane.showConfirmDialog(null, "Yakin Ingin Log Out ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            AnimationClass ani = new AnimationClass();
            ani.jLabelXLeft(lblLogOut.getX(), -500, 2, 1, lblLogOut);
            this.dispose();
            new formLogin().setVisible(true);
        }
    }//GEN-LAST:event_lblLogOutMouseClicked

    private void lblTombol1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTombol1MouseClicked
        if (lblTombol1.getIcon() == iconOn) {
            this.lblTombol1.setIcon((Icon) iconOff);
            this.lblTombol1.setIcon(iconOff);
        } else {
            this.lblTombol1.setIcon(iconOn);
        }

        if (lblTombol1.getIcon() == iconOn) {
            cmbJurusanKelas10.setEnabled(true);
            cmbABKelas10.setEnabled(true);
            tampilTblSiswaKelas10();
        }
        if (lblTombol1.getIcon() == iconOff) {
            cmbJurusanKelas10.setEnabled(false);
            cmbABKelas10.setEnabled(false);
            tampilTblSiswaKelas10();
        }
    }//GEN-LAST:event_lblTombol1MouseClicked

    private void tabPanelSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPanelSiswaMouseClicked
        if (tabPanelUtama.isEnabledAt(1) && tabPanelSiswa.isEnabledAt(0)) {
            tampilTblSiswaKelas10();
            tampilJumlahCariSiswa();
        }
        if (tabPanelUtama.isEnabledAt(1) && tabPanelSiswa.isEnabledAt(1)) {
            tampilTblSiswaKelas11();
            tampilJumlahCariSiswa();
        }
        if (tabPanelUtama.isEnabledAt(1) && tabPanelSiswa.isEnabledAt(2)) {
            tampilTblSiswaKelas12();
            tampilJumlahCariSiswa();
        }
    }//GEN-LAST:event_tabPanelSiswaMouseClicked

    private void btnEditBukuAdvance1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBukuAdvance1ActionPerformed
        String password = JOptionPane.showInputDialog(null, "Masukan Password", "Security", JOptionPane.OK_OPTION);
        if ("Lucifer".equals(password)) {
            new formEditSiswaLoop(this, true).setVisible(true);
        }
    }//GEN-LAST:event_btnEditBukuAdvance1ActionPerformed

    private void lblImage1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImage1MousePressed

        brose("www.Java.com");
    }//GEN-LAST:event_lblImage1MousePressed

    private void lblImage2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImage2MousePressed

        brose("www.netbeans.org");
    }//GEN-LAST:event_lblImage2MousePressed

    private void lblImage3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImage3MousePressed

        brose("www.mysql.com");
    }//GEN-LAST:event_lblImage3MousePressed

    private void lblImage4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImage4MousePressed

        brose("www.oracle.com");
    }//GEN-LAST:event_lblImage4MousePressed

    private void lblImage5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImage5MousePressed

        brose("www.google.com");
    }//GEN-LAST:event_lblImage5MousePressed

    private void lblImageSMKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageSMKMousePressed
        brose("www.smkn2klaten.sch.id");
    }//GEN-LAST:event_lblImageSMKMousePressed

    private void tblDiPinjamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiPinjamMouseReleased

        int[] no = tblDiPinjam.getSelectedRows();
        System.out.println(Arrays.toString(no));

        btnKembalikan.setEnabled(true);
        btnBatalKembalikan.setEnabled(false);

        terpilih = new String[tblDiPinjam.getSelectedRowCount()];

        for (int i = 0; i < terpilih.length; i++) {
            terpilih[i] = ((String) tblDiPinjam.getValueAt(no[i], 0));
            System.out.println(i);
        }
        System.out.println(Arrays.toString(terpilih));
        btnKembalikan.setFocusTraversalKeysEnabled(true);


    }//GEN-LAST:event_tblDiPinjamMouseReleased

    private void tblDiKembalikanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiKembalikanMouseReleased
        int[] no = tblDiKembalikan.getSelectedRows();
        System.out.println(Arrays.toString(no));

        btnBatalKembalikan.setEnabled(true);
        btnKembalikan.setEnabled(false);

        terpilih = new String[tblDiKembalikan.getSelectedRowCount()];
        for (int i = 0; i < terpilih.length; i++) {
            terpilih[i] = ((String) tblDiKembalikan.getValueAt(no[i], 0));
            System.out.println(i);
        }
        System.out.println(Arrays.toString(terpilih));

        btnBatalKembalikan.setFocusTraversalKeysEnabled(true);
    }//GEN-LAST:event_tblDiKembalikanMouseReleased

    private void tblDendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDendaMouseClicked
        if ("Admin".equals(level)) {
            String no = (String) tblDenda.getValueAt(tblDenda.getSelectedRow(), 0);
            System.out.println(no);
            new formDetailDenda(this, true, no).setVisible(true);
        }
    }//GEN-LAST:event_tblDendaMouseClicked

    private void btnBayarDenda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarDenda1ActionPerformed
        new formBayarDendaSudahDibayar(this, true).setVisible(true);
    }//GEN-LAST:event_btnBayarDenda1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formHomeEdit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formHomeEdit dialog = null;
                dialog = new formHomeEdit(new javax.swing.JFrame(), true, "[User]", "Admin");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tanggal;
    private javax.swing.JPanel bottonPanel;
    private javax.swing.JButton btnBatalKembalikan;
    private javax.swing.JButton btnBatalKembalikanSemua;
    private javax.swing.JButton btnBayarDenda;
    private javax.swing.JButton btnBayarDenda1;
    private javax.swing.JButton btnCariBuku;
    private javax.swing.JButton btnCariDenda;
    private javax.swing.JButton btnCariDiKembalikan;
    private javax.swing.JButton btnCariDiPinjam;
    private javax.swing.JButton btnCariLaporanBuku;
    private javax.swing.JButton btnCariLaporanSiswaKelas10;
    private javax.swing.JButton btnCariLaporanSiswaKelas11;
    private javax.swing.JButton btnCariLaporanSiswaKelas12;
    private javax.swing.JButton btnCariPeminjaman;
    private javax.swing.JButton btnCariSiswaKelas10;
    private javax.swing.JButton btnCariSiswaKelas11;
    private javax.swing.JButton btnCariSiswaKelas12;
    private javax.swing.JButton btnEditBuku;
    private javax.swing.JButton btnEditBukuAdvance;
    private javax.swing.JButton btnEditBukuAdvance1;
    private javax.swing.JButton btnEditPeminjaman;
    private javax.swing.JButton btnEditSiswa;
    private javax.swing.JButton btnEditSiswaKelas11;
    private javax.swing.JButton btnEditSiswaKelas12;
    private javax.swing.JButton btnKembalikan;
    private javax.swing.JButton btnKembalikanSemua;
    private javax.swing.JButton btnRefreshBuku;
    private javax.swing.JButton btnRefreshDenda;
    private javax.swing.JButton btnRefreshKeterlambatan;
    private javax.swing.JButton btnRefreshLaporanBuku;
    private javax.swing.JButton btnRefreshLaporanSiswaKelas10;
    private javax.swing.JButton btnRefreshLaporanSiswaKelas11;
    private javax.swing.JButton btnRefreshLaporanSiswaKelas12;
    private javax.swing.JButton btnRefreshPeminjaman;
    private javax.swing.JButton btnRefreshPengembalian;
    private javax.swing.JButton btnRefreshSiswaKelas10;
    private javax.swing.JButton btnRefreshSiswaKelas11;
    private javax.swing.JButton btnRefreshSiswaKelas12;
    private javax.swing.JButton btnTentangKami;
    private javax.swing.JButton btnUbahInterface;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox ckBoxFilterKelas10;
    private javax.swing.JCheckBox ckBoxFilterKelas11;
    private javax.swing.JCheckBox ckBoxFilterKelas12;
    private javax.swing.JComboBox<String> cmbABKelas10;
    private javax.swing.JComboBox<String> cmbABKelas11;
    private javax.swing.JComboBox<String> cmbABKelas12;
    private javax.swing.JComboBox<String> cmbJurusanKelas10;
    private javax.swing.JComboBox<String> cmbJurusanKelas11;
    private javax.swing.JComboBox<String> cmbJurusanKelas12;
    private javax.swing.JPanel data_mahasiswa6;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblBuku;
    private javax.swing.JLabel lblImage1;
    private javax.swing.JLabel lblImage2;
    private javax.swing.JLabel lblImage3;
    private javax.swing.JLabel lblImage4;
    private javax.swing.JLabel lblImage5;
    private javax.swing.JLabel lblImageSMK;
    private javax.swing.JLabel lblJam;
    private javax.swing.JLabel lblJumlahKeterlambatan;
    private javax.swing.JLabel lblJumlahPeminjaman;
    private javax.swing.JLabel lblLogOut;
    private javax.swing.JLabel lblPeminjaman;
    private javax.swing.JLabel lblSiswa;
    private javax.swing.JLabel lblSiswa1;
    private javax.swing.JLabel lblTombol;
    private javax.swing.JLabel lblTombol1;
    private javax.swing.JLabel lblTombol2;
    private javax.swing.JLabel lblTotalBuku;
    private javax.swing.JLabel lblTotalCariSiswa10;
    private javax.swing.JLabel lblTotalCariSiswa11;
    private javax.swing.JLabel lblTotalCariSiswa12;
    private javax.swing.JPanel panelBuku;
    private javax.swing.JPanel panelLainLain;
    private javax.swing.JPanel panelLaporan;
    private javax.swing.JPanel panelPeminjaman;
    private javax.swing.JPanel pnKeterlambatan;
    private javax.swing.JPanel pnlDenda;
    private javax.swing.JPanel pnlDipinjam;
    private javax.swing.JPanel pnlKelompokKami;
    private javax.swing.JPanel pnlLaporanBuku;
    private javax.swing.JPanel pnlLaporanSiswaKelas10;
    private javax.swing.JPanel pnlLaporanSiswaKelas11;
    private javax.swing.JPanel pnlPeminjaman;
    private javax.swing.JPanel pnlPengaturan;
    private javax.swing.JPanel pnlPengembalian;
    private javax.swing.JPanel pnlSettingInterface;
    private javax.swing.JPanel pnlSiswaKelas10;
    private javax.swing.JPanel pnlSiswaKelas11;
    private javax.swing.JPanel pnlSiswaKelas12;
    private javax.swing.JPanel pnlTentangkami;
    private javax.swing.JPanel pnlTitleBar;
    private javax.swing.JRadioButton rbtMetal;
    private javax.swing.JRadioButton rbtMotif;
    private javax.swing.JRadioButton rbtNimbus;
    private javax.swing.JRadioButton rbtWindows;
    private javax.swing.JRadioButton rbtWindowsClassic;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JTabbedPane tabPanelLainLain;
    private javax.swing.JTabbedPane tabPanelLaporan;
    private javax.swing.JTabbedPane tabPanelLaporanSiswa;
    private javax.swing.JTabbedPane tabPanelPeminjaman;
    private javax.swing.JTabbedPane tabPanelSiswa;
    private javax.swing.JTabbedPane tabPanelUtama;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTable tblDenda;
    private javax.swing.JTable tblDiKembalikan;
    private javax.swing.JTable tblDiPinjam;
    private javax.swing.JTable tblKeterlambatan;
    private javax.swing.JTable tblLaporanBuku;
    private javax.swing.JTable tblLaporanSiswaKelas10;
    private javax.swing.JTable tblLaporanSiswaKelas11;
    private javax.swing.JTable tblLaporanSiswaKelas12;
    private javax.swing.JTable tblPeminjaman;
    private javax.swing.JTable tblSiswaKelas10;
    private javax.swing.JTable tblSiswaKelas11;
    private javax.swing.JTable tblSiswaKelas12;
    private javax.swing.JButton tbnCariKeterlambatan;
    private javax.swing.JTextField txtCariBuku;
    private javax.swing.JTextField txtCariDenda;
    private javax.swing.JTextField txtCariDiKembalikan;
    private javax.swing.JTextField txtCariDipinjam;
    private javax.swing.JTextField txtCariKeterlambatan;
    private javax.swing.JTextField txtCariLaporanBuku;
    private javax.swing.JTextField txtCariLaporanSiswaKelas10;
    private javax.swing.JTextField txtCariLaporanSiswaKelas11;
    private javax.swing.JTextField txtCariLaporanSiswaKelas12;
    private javax.swing.JTextField txtCariPeminjaman;
    private javax.swing.JTextField txtCariSiswaKelas10;
    private javax.swing.JTextField txtCariSiswaKelas11;
    private javax.swing.JTextField txtCariSiswaKelas12;
    private javax.swing.JTextField txtFilterKelas10;
    private javax.swing.JTextField txtFilterKelas11;
    private javax.swing.JTextField txtFilterKelas12;
    // End of variables declaration//GEN-END:variables
}
