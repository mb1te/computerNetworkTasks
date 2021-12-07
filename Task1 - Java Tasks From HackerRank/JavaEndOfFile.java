import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class JavaEndOfFile {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        for (int i = 1; scan.hasNext(); i++) {
            String s = scan.nextLine();
            System.out.printf("%d %s\n", i, s);
        }
    }
}
