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
                    String hello = "What.";
                    while (in.ready()) {
                        str = in.readLine();

                        System.out.println(str);
                        if (str.contains("msg=Hello")) {
                            hello = "Hello.";
                        }
                        if (str.contains("msg=Exit")) {
                            done = false;
                        }
                    }
                    if (done) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write(hello.getBytes());
                    } else {
                        out.write("HTTP/1.1 400 OK\r\n\r\n".getBytes());
                    }
                }
            }
        }
    }
}
