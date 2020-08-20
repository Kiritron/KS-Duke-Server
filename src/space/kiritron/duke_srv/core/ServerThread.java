package space.kiritron.duke_srv.core;

import space.kiritron.duke_srv.ks_libs.pixel.filefunc.GetPathOfAPP;
import space.kiritron.duke_srv.ks_libs.pixel.logger.genLogMessage;
import space.kiritron.duke_srv.ks_libs.pixel.logger.toConsole;

import java.io.*;
import java.net.Socket;

import static space.kiritron.duke_srv.init.NAME_APP;
import static space.kiritron.duke_srv.ks_libs.pixel.filefunc.FileControls.ReadFile;
import static space.kiritron.duke_srv.ks_libs.pixel.filefunc.FileControls.SearchFile;

public class ServerThread extends Thread {
    private Socket socket;

    protected ServerThread(Socket socket) throws IOException {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            String MessageFromClient = reader.readLine();
            if (MessageFromClient != null) {
                int CheckCommand = MessageFromClient.indexOf("Какой сейчас актуальный код версии у");

                if (CheckCommand != -1) {
                    String codeOfVersion;
                    String data = MessageFromClient;
                    data = data.replace("Какой сейчас актуальный код версии у ", "");
                    data = data.replace("?", "");
                    if (SearchFile(GetPathOfAPP.GetPathWithSep() + "data" + GetPathOfAPP.GetSep() + data) == true) {
                        codeOfVersion = ReadFile(GetPathOfAPP.GetPathWithSep() + "data" + GetPathOfAPP.GetSep() + data);
                    } else {
                        codeOfVersion = "Нечего ответить.";
                    }

                    writer.println(codeOfVersion);
                }
            }
            //writer.flush();
            socket.close();
        } catch (IOException e) {
            toConsole.print(genLogMessage.gen((byte) 3, false, NAME_APP + " - " + e.getMessage()));
            e.printStackTrace();
        } catch (Exception e) {
            toConsole.print(genLogMessage.gen((byte) 3, false, NAME_APP + " - " + e.getMessage()));
            e.printStackTrace();
        }
    }
}