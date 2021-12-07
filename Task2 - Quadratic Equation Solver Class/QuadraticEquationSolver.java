import java.util.Scanner;
import java.math.*;

class Solver {
	private double a, b, c;
	
	Solver(Scanner scan) {
		a = scan.nextDouble();
		b = scan.nextDouble();
		c = scan.nextDouble();
	}
	
	Solver(double initA, double initB, double initC) {
		a = initA;
		b = initB;
		c = initC;
	}
	
	public void solve() {
		System.out.printf("a = %f, b = %f, c = %f\n", a, b, c);
		
		double eps = 1e-9;
		
		if (a == 0) {
			if (b == 0) {
				if (c == 0) System.out.printf("There are infinitly many solutons\n");
				else System.out.printf("There are no solutions\n");
			}
			else System.out.printf("x = %f\n", -c / b);
			return;
		}
		
		double D = b * b - 4 * a * c;
		
		if (D + eps < 0) {
			System.out.printf("There are no solutions\n");
			return;
		}
		
		if (Math.abs(D) < eps) {
			System.out.printf("x = %f\n", -b / (2 * a));
			return;
		}
		
		double x1 = (-b + Math.sqrt(D)) / (2 * a);
		double x2 = (-b - Math.sqrt(D)) / (2 * a);
		
		System.out.printf("x1 = %f, x2 = %f\n", x1, x2);
	}
}

public class QuadraticEquationSolver {
	 public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
		 
		 testCase(0, 0, 0);
		 testCase(0, 0, 3);
		 
		 testCase(0, 1, 2);
		 testCase(0, 3, 91);
		 
		 testCase(1, 0, 4);
		 testCase(1, 1, 100);
		 
		 testCase(1, -7,10);
		 testCase(3, 67, 33);
		 
		 testCase(1, 2, 1);
		 testCase(1, -2, 1);
		 
		 testCaseFromCommandLine(scan);
	 }
	 
	 static void testCase(double a, double b, double c) {
		 Solver solver = new Solver(a, b, c);
		 solver.solve();
		 System.out.println();
	 }
	 
	 static void testCaseFromCommandLine(Scanner scan) {
		 Solver solver = new Solver(scan);
		 solver.solve();
		 System.out.println();
	 }
}
