import java.util.*;
import java.io.*;

class JavaLoopsII {
    public static void main(String []argh){
        Scanner in = new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            
            int res = a;
            int pow = 1;
            for (int j = 0; j < n; j++) {
                if (j > 0) pow *= 2;
                res += b * pow;
                System.out.printf("%d ", res);
            }
            System.out.printf("\n");
        }
        
        in.close();
    }
}