import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServerClass {
	public static final int PORT = 2020;
	static Queue<Client> clients = new LinkedList<>();


	static class Client {
		Socket socket;
		DataInputStream input;
		DataOutputStream output;

		Client(Socket newSocket) throws IOException {
			socket = newSocket;
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		}
	}

	static class Subtask implements Runnable {
		int L, R;
		ArrayList<Integer> answer = new ArrayList<>();
		Client client;
		boolean died = false;

		Subtask(int L, int R, Client client) {
			this.L = L;
			this.R = R;
			this.client = client;
		}

		void setClient(Client client) {
			this.client = client;
		}

		public void run() {
			died = false;
			try {
				client.output.writeInt(L);
				client.output.writeInt(R);
				int N = client.input.readInt();

				for (int i = 0; i < N; i++) {
					answer.add(client.input.readInt());
				}

				synchronized (clients) {
					clients.add(client);
					clients.notify();
				}
			} catch(IOException e) {
				e.printStackTrace();
				died = true;
			}
		}
	}

	static class Task implements Runnable {
		int L, R, step;

		ArrayList<Integer> answer = new ArrayList<>();

		Task(int L, int R, int step) {
			this.L = L;
			this.R = R;
			this.step = step;
		}

		public void run() {
			int numberOfThreads = (R - L + step) / step; // (R - L + 1 + step - 1) / step

			System.out.printf("L = %d, R = %d, step = %d, numberOfThreads = %d\n", L, R, step, numberOfThreads);

			Subtask[] subtasks = new Subtask[numberOfThreads];
			Thread[] threads = new Thread[numberOfThreads];
			for (int i = 0; i < numberOfThreads; i++) {
				synchronized (clients) {
					if (clients.isEmpty()) {
						try {
							clients.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					Client client = clients.remove();

					try {
						if (client.input.readInt() != 0) {
							i--;
							continue;
						}
					} catch (IOException e) {
						i--;
						continue;
					}

					subtasks[i] = new Subtask(L + step * i,
							Math.min(R + 1, L + step * (i + 1)), client);
					threads[i] = new Thread(subtasks[i]);
					threads[i].start();

					System.out.printf("Подзадача L = %d, R = %d ожидает клиента\n", subtasks[i].L, subtasks[i].R);
				}
			}
			for (int i = 0; i < numberOfThreads; i++) {
				try {
					threads[i].join();

					while (subtasks[i].died) {
						synchronized (clients) {
							if (clients.isEmpty()) {
								try {
									clients.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							Client client = clients.remove();

							try {
								if (client.input.readInt() != 0) {
									i--;
									continue;
								}
							} catch (IOException e) {
								i--;
								continue;
							}

							subtasks[i].setClient(client);
							threads[i] = new Thread(subtasks[i]);
							threads[i].start();

							System.out.printf("Подзадача L = %d, R = %d ожидает клиента\n", subtasks[i].L, subtasks[i].R);
							threads[i].join();
						}


					}

					String stringAnswer = subtasks[i].answer.stream().map(String::valueOf).collect(Collectors.joining(","));

					System.out.printf("На подзадачу L = %d, R = %d получен ответ %s\n", subtasks[i].L,
							subtasks[i].R - 1, stringAnswer);

					answer.addAll(subtasks[i].answer);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			String stringAnswer = answer.stream().map(String::valueOf).collect(Collectors.joining(","));

			System.out.printf("Получен ответ на задачу  L = %d, R = %d, step = %d: answer = %s\n",
					L, R, step, stringAnswer);

		}
	}

	static class AddClients implements Runnable {
		ServerSocket server;

		AddClients(ServerSocket curSocket) {
			server = curSocket;
		}

		public void addClient(Client newClient) {
			synchronized(clients) {
				clients.add(newClient);
				clients.notify();

				System.out.println("Подключился новый клиент");

			}
		}

		public void run() {
			while (true) {
				try {
					Socket socket = server.accept();
					Client newClient = new Client(socket);
					addClient(newClient);
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			System.out.println("Готовимся начать прослушивать порт " + PORT);
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Сервер запущен, прослушиваем порт.");

			AddClients listenClients = new AddClients(server);
			Thread listenThread = new Thread(listenClients);
			listenThread.start();

			while (true) {
				System.out.println("Введите -1 для отстановки сервера или любое другое число для продолжения");
				int operation = in.nextInt();
				if (operation == -1) break;

				System.out.println("Введите задачу в формате 'L R Step'");

				int L = in.nextInt(), R = in.nextInt(), step = in.nextInt();

				System.out.printf("Получена задача L = %d, R = %d, step = %d\n", L, R, step);
				Task curTask = new Task(L, R, step);
				Thread curThread = new Thread(curTask);
				curThread.start();
			}

			System.out.println("Закрываем сокет и прекращаем слушать порт");
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		in.close();
	}
}
