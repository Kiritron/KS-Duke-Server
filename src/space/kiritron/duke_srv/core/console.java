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

import space.kiritron.pixel.logger.genLogMessage;
import space.kiritron.pixel.logger.toConsole;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static space.kiritron.duke_srv.core.consoleCommands.*;
import static space.kiritron.duke_srv.init.NAME_APP;

/**
 * @author Киритрон Стэйблкор
 */

public class console {
    static BufferedReader br = null;

    public static Thread ConsoleThread = new Thread(() -> {
        try {
            spawnConsole();
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    public static void spawnConsole() {
        System.out.println("Список доступных команд по команде help.");
        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String command = br.readLine();
                final String user = "Админ";

                if (command.equals("exit")) {
                    toConsole.print(genLogMessage.gen((byte) 1, false, user + " - " + command));
                    command = null;
                    exit_from_programm();
                } else if (command.equals("create_prog_profile")) {
                    toConsole.print(genLogMessage.gen((byte) 1, false, user + " - " + command));
                    command = null;
                    create_prog_profile();
                } else if (command.equals("read_prog_profile")) {
                    toConsole.print(genLogMessage.gen((byte) 1, false, user + " - " + command));
                    command = null;
                    read_prog_profile();
                } else if (command.equals("write_to_prog_profile")) {
                    toConsole.print(genLogMessage.gen((byte) 1, false, user + " - " + command));
                    command = null;
                    write_to_prog_profile();
                } else if (command.equals("help")) {
                    toConsole.print(genLogMessage.gen((byte) 1, false, user + " - " + command));
                    command = null;
                    toConsole.print(genLogMessage.gen((byte) 1, false, "===== ПОМОЩЬ ====="));
                    toConsole.print(genLogMessage.gen((byte) 1, false, "exit - Отключение сервера и закрытие программы КС Дьюк."));
                    toConsole.print(genLogMessage.gen((byte) 1, false, "create_prog_profile - Создать профиль приложения."));
                    toConsole.print(genLogMessage.gen((byte) 1, false, "read_prog_profile - Прочитать профиль приложения."));
                    toConsole.print(genLogMessage.gen((byte) 1, false, "write_to_prog_profile - Записать код версии в профиль приложения."));
                    toConsole.print(genLogMessage.gen((byte) 1, false, "================="));
                } else {
                    toConsole.print(genLogMessage.gen((byte) 1, false, user + " - " + command));
                    command = null;
                    toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Такой команды нет. Используйте help."));
                }
            }
        } catch (IOException e) {
            toConsole.print(genLogMessage.gen((byte) 3, false, NAME_APP + " - " + e.getMessage()));
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    toConsole.print(genLogMessage.gen((byte) 3, false, NAME_APP + " - " + e.getMessage()));
                }
            }
        }
    }
}
