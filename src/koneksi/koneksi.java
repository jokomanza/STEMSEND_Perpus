package koneksi;

import com.mysql.jdbc.Driver;
import java.nio.channels.NoConnectionPendingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luciver
 *
 */
public class koneksi {

    public Connection koneksi;

    public Connection getConnection() {
        try {
            if (koneksi == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                koneksi = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Database_STEMSEND_Perpus?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "root", "root");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal koneksi ke Server\n" + ex);
        } catch (NoConnectionPendingException ex) {
            JOptionPane.showMessageDialog(null, "Server Offline" + ex);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Servernya Offline" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return koneksi;
    }

    public static void main(String[] ar) {
        Connection con = new koneksi().getConnection();
    }
}
