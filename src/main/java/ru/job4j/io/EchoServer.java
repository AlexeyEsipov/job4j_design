package ru.job4j.io;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean done = true;
            while (done) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("msg=Bye")) {
                            done = false;
                        }
                    }
                    if (done) {
                        out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    } else {
                        out.write("HTTP/1.1 400 OK\r\n\\".getBytes());
                    }
                }
            }
        }
    }
}
