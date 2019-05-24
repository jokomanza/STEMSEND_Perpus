package form;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Luciver
 *
 */
public class formatRupiah {

    public static void main(String[] args) {
        formatRupiah format = new formatRupiah();
        format.getFormatRp("100000000000000000000");
    }

    public String getFormatRp(String nilai) {
        double harga = Double.valueOf(nilai);

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();

        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setMonetaryDecimalSeparator(',');
        formatRP.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRP);
        String hasil = kursIndonesia.format(harga);
        System.out.printf("Nilai Rupiah : %s %n ", kursIndonesia.format(harga));
        return hasil;
    }

}
