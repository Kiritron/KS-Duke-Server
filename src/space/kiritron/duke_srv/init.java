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

package space.kiritron.duke_srv;

import space.kiritron.duke_srv.core.Server;
import space.kiritron.pixel.CheckerDIR;
import space.kiritron.pixel.filefunc.GetPathOfAPP;

import java.io.IOException;

import static space.kiritron.duke_srv.core.console.ConsoleThread;
import static space.kiritron.pixel.filefunc.FileControls.*;

public class init {
    public static String NAME_APP = "КС Дьюк Сервер";
    public static String VER_APP = "Вер.: 1.2-КСДС";

    public static void main(String[] args) throws IOException {
        System.out.println("==================");
        System.out.println(NAME_APP);
        System.out.println(VER_APP);
        System.out.println("==================");

        CheckerDIR.Check("logs");
        CheckerDIR.Check("cfg");
        CheckerDIR.Check("data");

        if (SearchFile(GetPathOfAPP.GetPathWithSep() + "cfg" + GetPathOfAPP.GetSep() + "server.tolf") == false) {
            CreateFile(GetPathOfAPP.GetPathWithSep() + "cfg" + GetPathOfAPP.GetSep() + "server.tolf");
            writeToFile(GetPathOfAPP.GetPathWithSep() + "cfg" + GetPathOfAPP.GetSep() + "server.tolf", "</\n" + "\t[SERVER-OPTIONS]\n" + "\t\t- PORT: 13585;\n" + "\t[/SERVER-OPTIONS]\n" + "/>");
        }

        new Server().start();
        ConsoleThread.start();
    }
}
