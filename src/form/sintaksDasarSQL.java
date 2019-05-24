package form;

// import bumbu-bumbu yang diperlukan
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

// bisa juga diimpor sekaligus seperti ini:
// import java.sql.*


public class sintaksDasarSQL {
    
    // Menyiapkan paramter JDBC untuk koneksi ke datbase
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/project_perpus_from_zero";
    static final String USER = "root";
    static final String PASS = "";

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {
        
        // Melakukan koneksi ke database
        // harus dibungkus dalam blok try/catch
        try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);
            
            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // buat objek statement
            stmt = conn.createStatement();
            
            // buat query ke database
            String sql = "SELECT * FROM tblbuku";
            
            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);
            
            
            // tampilkan hasil query
            while(rs.next()){
                System.out.print("ID Buku: " + rs.getInt("idbuku")+" || ");
                System.out.print("Judul: " + rs.getString("judulBuku")+" || ");
                System.out.println("Pengarang: " + rs.getString("pengarang"));
            }
            //Setelah mendapatkan data dari database MySQL, selanjutnya kita bisa tampilkan dengan perulangan
            //Perulangan tersebut akan mengulang sebanyak isi tabelnya, atau dengan kata lain “Ulangi selama rs masih punya isi”.
            //Cara mengambil nilai dari objek rs menggunakan method get* dengan parameter nama kolom di tabel database.

            
            stmt.close();
            conn.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    
}

