import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectWindow {
    private JTextField ServerAddress;
    private JTextField ServerPort;
    private JButton ConnectButton;
    private JPanel ConnectPanel;
    private JLabel StateField;
    private JTextArea ReceivedTasks;

    class Client implements Runnable {
        public static String HOST;
        public static int PORT;

        Client (String Host, int Port) {
            this.HOST = Host;
            this.PORT = Port;
        }

        ArrayList<Integer> answer = new ArrayList<>();

        void calc(int L, int R) {
            int x = 11 * 13 * 17;
            int res = 0;
            for (int i = L; i <= R; i++) {
                if (i % x == 0) answer.add(i);
            }
        }

        public void run() {
            try {
                System.out.println("Устанавливаем соединение с " + HOST + " по порту " + PORT);
                Socket socket = new Socket(HOST, PORT);
                System.out.println("Соединение установлено");

                StateField.setText("Connected");
                ConnectButton.setEnabled(false);

                while (!socket.isClosed()) {
                    Scanner input = new Scanner(socket.getInputStream());
                    PrintWriter output = new PrintWriter(socket.getOutputStream());

                    Integer L = input.nextInt(), R = input.nextInt();
                    ReceivedTasks.setText(ReceivedTasks.getText() + "\nL :" + L.toString() + " R: " + R.toString());

                    StateField.setText("Executing subtask");
                    calc(L, R);
                    StateField.setText("Connected");

                    output.println(answer.size());
                    for (Integer i : answer) output.println(i);
                    output.flush();

                    answer.clear();
                }
                StateField.setText("Disconnected");
                ConnectButton.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
                StateField.setText("Disconnected");
                ConnectButton.setEnabled(true);
            }
        }
    };

    public ConnectWindow() {
        ConnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = ServerAddress.getText();
                int port = Integer.parseInt(ServerPort.getText());

                Client client = new Client(address, port);
                Thread clientThread = new Thread(client);
                clientThread.start();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ConnectWindow");
        frame.setContentPane(new ConnectWindow().ConnectPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
