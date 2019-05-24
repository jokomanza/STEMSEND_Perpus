package form;


import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class formDetailSiswa extends javax.swing.JDialog {

    public formDetailSiswa(java.awt.Frame parent, boolean modal, String NIS){
        super(parent, modal);
        initComponents();
        isiComboBoxSiswa();
        nis = NIS;
        awal2();
        pindah();
    }
    //nis|nama_siswa| kelamin | email| alamat| telepon | kelas
    String Sql;
    int urut;
    public String nis;
    Connection con=new koneksi().getConnection();
    java.sql.Statement stat ;
    java.sql.ResultSet hasil;
    int xx,yy;
    
    
     private void isiComboBoxSiswa(){
        int jumlah=0;
        try{
            con = new koneksi().getConnection();
            String sql = "select count(nis) from tblsiswa;";
            java.sql.Statement statement = con.createStatement();
            java.sql.ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                jumlah=result.getInt("count(nis)");
                for(int i=1;i<=1;i++){
                    String sql2 = "Select nis from tblsiswa ";
                    java.sql.Statement statement2 = con.createStatement();
                    java.sql.ResultSet result2 = statement2.executeQuery(sql2);
                    while(result2.next()){
                        cmbNis.addItem(result2.getString("nis"));
                    }
                }
            }
        } catch (SQLException ex) {
        }
    }
     
    public void awal2(){
        cmbKelamin.addItem("L");
        cmbKelamin.addItem("P");
        try{
            Sql="select * from tblsiswa where nis='"+nis+"'";
            stat= con.createStatement();
            hasil = stat.executeQuery(Sql);
            while(hasil.next()){
                String namaSiswa = hasil.getString("nama_siswa");
                String kelamin=hasil.getString("Kelamin");
                String nis = hasil.getString("nis");
                String email = hasil.getString("Email");
                String alamat = hasil.getString("alamat");
                String telepon = hasil.getString("Telepon");
                String kelas = hasil.getString("kelas");
                cmbNis.setSelectedItem((String)nis);
                txtNama.setText((String)namaSiswa);
                cmbKelamin.setSelectedItem((String)kelamin);
                txtEmail.setText((String)email);
                txtAlamat.setText((String)alamat);
                txtTelepon.setText((String)telepon);
                txtKelas.setText((String)kelas);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n"+e,"Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void pindah() {
        cmbKelamin.addItem("L");
        cmbKelamin.addItem("P");
        nis=(String)cmbNis.getSelectedItem();
        System.out.println(nis);
        try{
            Sql="select * from tblsiswa where nis='"+nis+"'";
            stat= con.createStatement();
            hasil = stat.executeQuery(Sql);
            while(hasil.next()){
                String namaSiswa = hasil.getString("nama_siswa");
                String kelamin=hasil.getString("Kelamin");
//                String nis = hasil.getString("nis");
                String email = hasil.getString("Email");
                String alamat = hasil.getString("alamat");
                String telepon = hasil.getString("Telepon");
                String kelas = hasil.getString("kelas");
//                cmbNis.setSelectedItem((String)nis);
                txtNama.setText((String)namaSiswa);
                cmbKelamin.setSelectedItem((String)kelamin);
                txtEmail.setText((String)email);
                txtAlamat.setText((String)alamat);
                txtTelepon.setText((String)telepon);
                txtKelas.setText((String)kelas);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n"+e,"Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        //int baris = tblSiswaKelas10.getSelectedRow();
    }
    
    public void pindah2() {
        cmbKelamin.addItem("L");
        cmbKelamin.addItem("P");
        cmbNis.setSelectedIndex(urut);
        nis=(String)cmbNis.getSelectedItem();
        System.out.println(nis);
        try{
            Sql="select * from tblsiswa where nis='"+nis+"'";
            stat= con.createStatement();
            hasil = stat.executeQuery(Sql);
            while(hasil.next()){
                String namaSiswa = hasil.getString("nama_siswa");
                String kelamin=hasil.getString("Kelamin");
//                String nis = hasil.getString("nis");
                String email = hasil.getString("Email");
                String alamat = hasil.getString("alamat");
                String telepon = hasil.getString("Telepon");
                String kelas = hasil.getString("kelas");
//                cmbNis.setSelectedItem((String)nis);
                txtNama.setText((String)namaSiswa);
                cmbKelamin.setSelectedItem((String)kelamin);
                txtEmail.setText((String)email);
                txtAlamat.setText((String)alamat);
                txtTelepon.setText((String)telepon);
                txtKelas.setText((String)kelas);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL \n"+e,"Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        simpan = new javax.swing.JButton();
        txtTelepon = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        cmbKelamin = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtKelas = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnOk1 = new javax.swing.JButton();
        btnOk2 = new javax.swing.JButton();
        cmbNis = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));
        setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/src/gambar/icons8_Google_48px_1.png"));
        setMinimumSize(new java.awt.Dimension(403, 380));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        simpan.setText("Print");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        txtTelepon.setEnabled(false);

        txtNama.setEnabled(false);

        jLabel11.setText("Kelas");

        jLabel2.setText("NIS");

        jLabel3.setText("Nama");

        jLabel4.setText("Jenis Kelamin");

        txtEmail.setToolTipText("YYYY-MM-DD");
        txtEmail.setEnabled(false);

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        txtAlamat.setEnabled(false);
        jScrollPane1.setViewportView(txtAlamat);

        jLabel5.setText("Email");

        cmbKelamin.setEnabled(false);

        jLabel6.setText("Alamat");

        txtKelas.setEnabled(false);

        jLabel7.setText("Telepon");

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnOk1.setText(">");
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        btnOk2.setText("<");
        btnOk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk2ActionPerformed(evt);
            }
        });

        cmbNis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNisItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnOk2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnOk1)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnOk))
                                .addComponent(txtTelepon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbNis, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan)
                    .addComponent(btnOk)
                    .addComponent(btnOk2)
                    .addComponent(btnOk1))
                .addGap(120, 120, 120))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Form Detail Siswa");

        jPanel3.setBackground(new java.awt.Color(59, 70, 78));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        String reportSource = null;
        String reportDest = null;
        
        try{
            reportSource = System.getProperty("user.dir") + "/src/laporan/LaporanSiswa.jrxml";
            reportDest = System.getProperty("user.dir") + "/src/laporan/LaporanSiswa.jasper";
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,con);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
            JasperViewer.viewReport(jasperPrint,false);
            
        }catch(Exception e){
            System.out.println(e);
        }       
    }//GEN-LAST:event_simpanActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed
        try{
            urut=urut+1;
            pindah2();
        }catch (Exception ex){
            
        }
    }//GEN-LAST:event_btnOk1ActionPerformed

    private void btnOk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk2ActionPerformed
        try{
            urut=urut-1;
            pindah2();
        }catch (Exception ex){
            
        }
    }//GEN-LAST:event_btnOk2ActionPerformed

    private void cmbNisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNisItemStateChanged
        pindah();
    }//GEN-LAST:event_cmbNisItemStateChanged

   
    
    private DefaultTableModel tabmode;
    
    //menampilkan data dari datatabe ke tabel
    public void tampil_tb_mahasiswa(){
        Object []baris = {"No Bp","Nama","Tempat Lahir","Tanggal Lhair","Jurusan","Tanggal Masuk"};
        tabmode = new DefaultTableModel(null, baris);
        //tb_mahasiswa.setModel(tabmode);
        try {
            Connection con = new koneksi().getConnection();
            String sql = "select * from tb_mahasiswa order by no_bp asc";
            java.sql.Statement stat = con.createStatement();
            java.sql.ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String no_bp = hasil.getString("no_bp");
                String nama = hasil.getString("nama");
                String tempat_lahir = hasil.getString("tempat_lahir");
                String tanggal_lahir = hasil.getString("tanggal_lahir");
                String jurusan = hasil.getString("jurusan"); 
                String tanggal_masuk = hasil.getString("tanggal_masuk");
                String[] data = {no_bp, nama, tempat_lahir, tanggal_lahir, jurusan, tanggal_masuk};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Menampilkan data GAGAL","Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
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
            java.util.logging.Logger.getLogger(formDetailSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formDetailSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formDetailSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formDetailSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formDetailSiswa dialog = new formDetailSiswa(new javax.swing.JFrame(), true,"");
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
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnOk1;
    private javax.swing.JButton btnOk2;
    private javax.swing.JComboBox<String> cmbKelamin;
    private javax.swing.JComboBox<String> cmbNis;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton simpan;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTelepon;
    // End of variables declaration//GEN-END:variables
}
