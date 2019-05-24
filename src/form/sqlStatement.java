package form;

import java.sql.*;
import java.util.Arrays;
import koneksi.koneksi;

/**
 *
 * @author Luciver
 *
 */
public class sqlStatement {
    private Statement stat;
    private ResultSet result;
    public static void main(String [] a) throws  SQLException{
        sqlStatement coba = new sqlStatement();
        ResultSet co = coba.getResult("Select * from tblBuku");
        while(co.next()){
            Object[] o = new Object[6];
                o[0] = co.getString("idBuku");
                o[1] = co.getString("judulBuku");
                o[2] = co.getString("pengarang");
                o[3] = co.getString("penerbit");
                o[4] = co.getString("tanggalterbit");
                o[5] = co.getString("stok");
//            System.out.println(co.getString("username"));
            System.out.print(co.getRow()+". ");
            System.out.println(Arrays.toString(o));
//            System.out.print(co.getRow()+".");
        }
//        coba.setSimpan("delete  from login where username = 'Lucifer'");
    }
    
    public ResultSet getResult(String sql){
        try{
            Connection con = new koneksi().getConnection();
            stat = con.createStatement();
            result = stat.executeQuery(sql);
//            return result;
        }catch (Exception ex){
            
        }
        return this.result;
    }
    
    public String getSQL(String sql){
        boolean sukses = false;
         try{
            Connection con = new koneksi().getConnection();
            stat = con.createStatement();
            stat.executeUpdate(sql);
            sukses = true;
        }catch(SQLException ex){
            System.out.println(ex);
        }
        if(sukses){
            return "Sukses";
        }else{
            return "gagal";
        }
    }
    
    public void setSql(String sql){
        try{
            Connection con = new koneksi().getConnection();
            stat = con.createStatement();
            stat.executeUpdate(sql);
            System.out.println("Sukses");
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
}
