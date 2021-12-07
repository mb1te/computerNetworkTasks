import java.util.*;
import java.text.*;
import java.text.NumberFormat;

public class JavaCurrencyFormatter {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();
        
        NumberFormat US = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat IN = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        NumberFormat CN = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat FR = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        
        String us = US.format(payment);
        String india = IN.format(payment);
        String china = CN.format(payment);
        String france = FR.format(payment);
        
        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }
}
