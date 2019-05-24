package form;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Luciver
 *
 */
public class NewClass {
    static ResultSet result;
    Statement stat;
    
    public static void main(String[]a) throws SQLException{
        sqlStatement ax1 = new sqlStatement();
        result=ax1.getResult("select * from login");
        while(result.next()){
            System.out.print(result.getString("username")+" | ");
            System.out.print(result.getString("password")+" | ");
            System.out.println(result.getString("name")+" | ");
        }


        Scanner scan = new Scanner(System.in);
        System.out.println("Selamat Datang ");
        System.out.print("Silahkan Masukan Nama anda : ");
        String name = scan.nextLine();
        System.out.print("Silakan Masukan Username : ");
        String username = scan.nextLine();
        System.out.print("Silakan Masukan password : ");
        String password = scan.nextLine();
        System.out.println(ax1.getSQL("insert into login values('"+username+"','"+password+"','"+name+"')"));
        
    }
}
