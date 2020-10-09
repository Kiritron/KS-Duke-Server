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

import space.kiritron.pixel.filefunc.FileControls;
import space.kiritron.pixel.filefunc.GetPathOfAPP;
import space.kiritron.pixel.logger.genLogMessage;
import space.kiritron.pixel.logger.toConsole;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static space.kiritron.tolchok.TOLF_Handler.ReadParamFromData;

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
