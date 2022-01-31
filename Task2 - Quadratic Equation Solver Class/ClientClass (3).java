import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientClass {
	public static final String HOST = "127.0.0.1";
	public static final int PORT = 2020;

	static ArrayList<Integer> answer = new ArrayList<>();
	
	static void calc(int L, int R) {
		int x = 11 * 13 * 17;
		int res = 0;
		for (int i = L; i < R; i++) {
			if (i % x == 0) answer.add(i);
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			System.out.println("Устанавливаем соединение с " + HOST + " по порту " + PORT);
	        Socket socket = new Socket(HOST, PORT);
	        System.out.println("Соединение установлено");

			while (true) {
				DataInputStream input = new DataInputStream(socket.getInputStream());
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());

				output.writeInt(0); // to check that client is alive

				int L = input.readInt(), R = input.readInt();
				System.out.printf("Получена задача: L = %d, R = %d\n", L, R);

				calc(L, R);

				output.writeInt(answer.size());
				for (Integer i : answer) output.writeInt(i);

				answer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		in.close();
	}
}
