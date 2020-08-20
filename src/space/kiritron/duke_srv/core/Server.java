package space.kiritron.duke_srv.core;

import space.kiritron.duke_srv.ks_libs.pixel.filefunc.GetPathOfAPP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static space.kiritron.duke_srv.core.console.writeToConsole;
import static space.kiritron.duke_srv.init.NAME_APP;
import static space.kiritron.duke_srv.ks_libs.pixel.tolchok.core.ReadCFG;

public class Server extends Thread {
    protected static int port;

    static {
        try {
            port = Integer.parseInt(ReadCFG(GetPathOfAPP.GetPathWithSep() + "cfg" + GetPathOfAPP.GetSep() + "server.tolf", "SERVER-OPTIONS", "PORT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            writeToConsole(true, true, NAME_APP, "Сервер запущен");
            writeToConsole(true, true, NAME_APP, "Сервер использует порт - " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new space.kiritron.duke_srv.core.ServerThread(socket).start();
            }
        } catch (IOException ex) {
            writeToConsole(true, true, NAME_APP, "ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
