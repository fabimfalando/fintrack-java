package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Formatador {
    public static String formatarMoeda(double valor) {
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatoMoeda.format(valor);
    }
}
