package space.kiritron.duke_srv.core;

import space.kiritron.duke_srv.ks_libs.pixel.filefunc.FileControls;
import space.kiritron.duke_srv.ks_libs.pixel.filefunc.GetPathOfAPP;
import space.kiritron.duke_srv.ks_libs.pixel.logger.genLogMessage;
import space.kiritron.duke_srv.ks_libs.pixel.logger.toConsole;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static space.kiritron.duke_srv.ks_libs.tolchok.TOLF_Handler.ReadParamFromData;

public class Server extends Thread {
    protected static int port;

    static {
        try {
            port = Integer.parseInt(ReadParamFromData(FileControls.ReadFile(GetPathOfAPP.GetPathWithSep() + "cfg" + GetPathOfAPP.GetSep() + "server.tolf"), "SERVER-OPTIONS", "PORT"));
        } catch (IOException e) {
            toConsole.print(genLogMessage.gen((byte) 3, true,"Не удалось установить порт из конфигурации."));
            port = 13585;
        }
    }

    @Override
    public void run() {
        startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            toConsole.print(genLogMessage.gen((byte) 1, true,"Сервер запущен"));
            toConsole.print(genLogMessage.gen((byte) 1, true, "Сервер использует порт - " + port));
            while (true) {
                Socket socket = serverSocket.accept();
                new space.kiritron.duke_srv.core.ServerThread(socket).start();
            }
        } catch (IOException ex) {
            toConsole.print(genLogMessage.gen((byte) 3, true, "ERROR: " + ex.getMessage()));
            ex.printStackTrace();
        }
    }
}
