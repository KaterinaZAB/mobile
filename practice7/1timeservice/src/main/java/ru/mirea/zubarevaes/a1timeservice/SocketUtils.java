package ru.mirea.zubarevaes.a1timeservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtils {
    /**
     * BufferedReader для получения входящих данных
     */
    public static BufferedReader getReader(Socket s) throws IOException {
        return (new BufferedReader(new InputStreamReader(s.getInputStream())));
    }

    public static PrintWriter getWriter(Socket s) throws IOException {
    // Second argument of true means autoflush.
        return (new PrintWriter(s.getOutputStream(), true));
    }
}
