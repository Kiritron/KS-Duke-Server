/*
 * Copyright 2020 Kiritron's Space
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package space.kiritron.duke_srv.core;

import space.kiritron.pixel.filefunc.GetPathOfAPP;
import space.kiritron.pixel.logger.genLogMessage;
import space.kiritron.pixel.logger.toConsole;

import java.io.*;
import java.net.Socket;

import static space.kiritron.duke_srv.init.NAME_APP;
import static space.kiritron.pixel.filefunc.FileControls.ReadFile;
import static space.kiritron.pixel.filefunc.FileControls.SearchFile;

/**
 * @author Киритрон Стэйблкор
 */

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