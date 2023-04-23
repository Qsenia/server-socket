import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        String city = null;
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    if (city == null) {
                        out.println("???");
                        city = in.readLine();
                        out.println("OK");
                    } else {
                        out.println(city);
                        String newCity = in.readLine();
                        if ((city.charAt(city.length() - 1)) == newCity.charAt(0)) {
                            city = newCity;
                            out.println("OK");
                        } else {
                            out.println("NOT OK");
                        }
                    }
                    //" Подключен клиент " + clientSocket.getPort());
                    //System.out.println("New connection accepted");
                    //
                    // out.println(String.format("Hi %s, your port is %d", city, clientSocket.getPort()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}