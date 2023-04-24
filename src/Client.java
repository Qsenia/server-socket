import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8989);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String city = reader.readLine();
            System.out.println(city);
            Scanner scanner = new Scanner(System.in);
            String newCity = scanner.nextLine();
            writer.println(newCity);
            String result = reader.readLine();
            System.out.println(result);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}