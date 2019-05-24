package form;

import java.sql.Connection;
import java.sql.*;
import koneksi.koneksi;

/**
 *
 * @author Luciver
 *
 */
public class autoNis {

    static String kelas = "X SIJA B";
    static String nis = "18.7.0999";
    String depan = "", max = "", hasil;

    public static void main(String[] a) {
        String src = "XII TPL";
        System.out.println(src.substring(0, 6));

//        System.out.println(src.charAt(0));
        src = "18.11.0001";
        System.out.println(src.substring(0, 6));
        autoNis ini = new autoNis();
        ini.getMaxNis("TPL");
    }

    public String getMaxNis(String kelas) {
        int nis1;
        int nis2;
        int nis3;
        int nis4;
        try {
            Connection con = new koneksi().getConnection();

            if (!"TPL".equals(kelas)) {
                String sql = "select max(nis) from tblsiswa where kelas like '% " + kelas + " %'";
                Statement stat = con.createStatement();
                ResultSet result = stat.executeQuery(sql);
                while (result.next()) {
                    max = result.getString("max(nis)");
                    System.out.println(max);
                }

                if ("TMPO".equals(kelas)) {
                    depan = max.substring(0, 6);
                    nis1 = Integer.parseInt(max.substring(9, 10));
                    nis2 = Integer.parseInt(max.substring(8, 9));
                    nis3 = Integer.parseInt(max.substring(7, 8));
                    nis4 = Integer.parseInt(max.substring(6, 7));
                    nis1++;
                    if (nis1 == 10) {
                        nis1 = 0;
                        nis2++;
                    }
                    if (nis2 == 10) {
                        nis2 = 0;
                        nis3++;
                    }
                    if (nis3 == 10) {
                        nis3 = 0;
                        nis4++;
                    }

                    String Nis1, Nis2, Nis3, Nis4;
                    Nis1 = String.valueOf(nis1);
                    Nis2 = String.valueOf(nis2);
                    Nis3 = String.valueOf(nis3);
                    Nis4 = String.valueOf(nis4);
                    hasil = (depan + Nis4 + Nis3 + Nis2 + Nis1);
                    System.out.println(hasil);
                } else if ("TPL".equals(kelas)) {
                    depan = max.substring(0, 6);
                    nis1 = Integer.parseInt(max.substring(9, 10));
                    nis2 = Integer.parseInt(max.substring(8, 9));
                    nis3 = Integer.parseInt(max.substring(7, 8));
                    nis4 = Integer.parseInt(max.substring(6, 7));
                    nis1++;
                    if (nis1 == 10) {
                        nis1 = 0;
                        nis2++;
                    }
                    if (nis2 == 10) {
                        nis2 = 0;
                        nis3++;
                    }
                    if (nis3 == 10) {
                        nis3 = 0;
                        nis4++;
                    }

                    String Nis1, Nis2, Nis3, Nis4;
                    Nis1 = String.valueOf(nis1);
                    Nis2 = String.valueOf(nis2);
                    Nis3 = String.valueOf(nis3);
                    Nis4 = String.valueOf(nis4);
                    hasil = (depan + Nis4 + Nis3 + Nis2 + Nis1);
                    System.out.println(hasil);
                } else {
                    depan = max.substring(0, 5);
                    nis1 = Integer.parseInt(max.substring(8, 9));
                    nis2 = Integer.parseInt(max.substring(7, 8));
                    nis3 = Integer.parseInt(max.substring(6, 7));
                    nis4 = Integer.parseInt(max.substring(5, 6));
                    nis1++;
                    if (nis1 == 10) {
                        nis1 = 0;
                        nis2++;
                    }
                    if (nis2 == 10) {
                        nis2 = 0;
                        nis3++;
                    }
                    if (nis3 == 10) {
                        nis3 = 0;
                        nis4++;
                    }
                    String Nis1, Nis2, Nis3, Nis4;
                    Nis1 = String.valueOf(nis1);
                    Nis2 = String.valueOf(nis2);
                    Nis3 = String.valueOf(nis3);
                    Nis4 = String.valueOf(nis4);
                    hasil = (depan + Nis4 + Nis3 + Nis2 + Nis1);
                    System.out.println(Nis1);
                    System.out.println(hasil);
                }
            } else {
                String sql = "select max(nis) from tblsiswa where kelas like '% " + kelas + "'";
                Statement stat = con.createStatement();
                ResultSet result = stat.executeQuery(sql);
                while (result.next()) {
                    max = result.getString("max(nis)");
                    System.out.println(max);
                }

                if ("TMPO".equals(kelas)) {
                    depan = max.substring(0, 6);
                    nis1 = Integer.parseInt(max.substring(9, 10));
                    nis2 = Integer.parseInt(max.substring(8, 9));
                    nis3 = Integer.parseInt(max.substring(7, 8));
                    nis4 = Integer.parseInt(max.substring(6, 7));
                    nis1++;
                    if (nis1 == 10) {
                        nis1 = 0;
                        nis2++;
                    }
                    if (nis2 == 10) {
                        nis2 = 0;
                        nis3++;
                    }
                    if (nis3 == 10) {
                        nis3 = 0;
                        nis4++;
                    }

                    String Nis1, Nis2, Nis3, Nis4;
                    Nis1 = String.valueOf(nis1);
                    Nis2 = String.valueOf(nis2);
                    Nis3 = String.valueOf(nis3);
                    Nis4 = String.valueOf(nis4);
                    hasil = (depan + Nis4 + Nis3 + Nis2 + Nis1);
                    System.out.println(hasil);
                } else if ("TPL".equals(kelas)) {
                    depan = max.substring(0, 6);
                    nis1 = Integer.parseInt(max.substring(9, 10));
                    nis2 = Integer.parseInt(max.substring(8, 9));
                    nis3 = Integer.parseInt(max.substring(7, 8));
                    nis4 = Integer.parseInt(max.substring(6, 7));
                    nis1++;
                    if (nis1 == 10) {
                        nis1 = 0;
                        nis2++;
                    }
                    if (nis2 == 10) {
                        nis2 = 0;
                        nis3++;
                    }
                    if (nis3 == 10) {
                        nis3 = 0;
                        nis4++;
                    }

                    String Nis1, Nis2, Nis3, Nis4;
                    Nis1 = String.valueOf(nis1);
                    Nis2 = String.valueOf(nis2);
                    Nis3 = String.valueOf(nis3);
                    Nis4 = String.valueOf(nis4);
                    hasil = (depan + Nis4 + Nis3 + Nis2 + Nis1);
                    System.out.println(hasil);
                } else {
                    depan = max.substring(0, 5);
                    nis1 = Integer.parseInt(max.substring(8, 9));
                    nis2 = Integer.parseInt(max.substring(7, 8));
                    nis3 = Integer.parseInt(max.substring(6, 7));
                    nis4 = Integer.parseInt(max.substring(5, 6));
                    nis1++;
                    if (nis1 == 10) {
                        nis1 = 0;
                        nis2++;
                    }
                    if (nis2 == 10) {
                        nis2 = 0;
                        nis3++;
                    }
                    if (nis3 == 10) {
                        nis3 = 0;
                        nis4++;
                    }
                    String Nis1, Nis2, Nis3, Nis4;
                    Nis1 = String.valueOf(nis1);
                    Nis2 = String.valueOf(nis2);
                    Nis3 = String.valueOf(nis3);
                    Nis4 = String.valueOf(nis4);
                    hasil = (depan + Nis4 + Nis3 + Nis2 + Nis1);
                    System.out.println(Nis1);
                    System.out.println(hasil);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return hasil;
    }

}
