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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static space.kiritron.duke_srv.init.NAME_APP;
import static space.kiritron.pixel.filefunc.FileControls.*;

public class consoleCommands {
    public static void exit_from_programm() {
        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Вы собираетесь завершить работу КС Дьюк Сервер. Вы уверены?"));
        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Если вы хотите продолжить, то напишите Y, а если нет, то любой другой символ."));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer = null;
        try {
            answer = br.readLine();
            if (answer.equals("Y") || answer.equals("y") || answer.equals("н") || answer.equals("Н")) {
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Выключаю сервер..."));
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Выхожу из программы..."));
                System.exit(0);
            } else {
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Операция отменена."));
            }
        } catch (IOException e) {
            toConsole.print(genLogMessage.gen((byte) 3, false, NAME_APP + " - " + e.getMessage()));
        }
    }

    public static void create_prog_profile() {
        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Вы собираетесь создать профиль для программы. Вы уверены?"));
        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Если вы хотите продолжить, то напишите Y, а если нет, то любой другой символ."));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer = null;
        try {
            answer = br.readLine();
            if (answer.equals("Y") || answer.equals("y") || answer.equals("н") || answer.equals("Н")) {
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Понял, продолжим."));
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Введите название профиля."));
                String name = br.readLine();
                if (SearchFile(GetPathOfAPP.GetPathWithSep() + "data" + GetPathOfAPP.GetSep() + name) == false) {
                    if (CreateFile(GetPathOfAPP.GetPathWithSep() + "data" + GetPathOfAPP.GetSep() + name) == true) {
                        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Профиль под названием " + name + " успешно создан."));
                    } else {
                        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Профиль под названием " + name + " создать не удалось."));
                    }
                } else {
                    toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Профиль " + name + " уже существует."));
                }
            } else {
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Операция отменена."));
            }
        } catch (IOException e) {
            toConsole.print(genLogMessage.gen((byte) 3, false, NAME_APP + " - " + e.getMessage()));
        }

    }

    public static void read_prog_profile() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Введите название профиля."));
        String name = null;
        try {
            name = br.readLine();
            if (SearchFile(GetPathOfAPP.GetPathWithSep() + "data" + GetPathOfAPP.GetSep() + name) == true) {
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + ReadFile(GetPathOfAPP.GetPathWithSep() + "data" + GetPathOfAPP.GetSep() + name)));
            } else {
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Профиль " + name + " не найден."));
            }
        } catch (IOException e) {
            toConsole.print(genLogMessage.gen((byte) 3, false, NAME_APP + " - " + e.getMessage()));
        }
    }

    public static void write_to_prog_profile() {
        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Вы хотите записать данные о версии в профиль программы. Вы уверены?"));
        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Если вы хотите продолжить, то напишите Y, а если нет, то любой другой символ."));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer = null;
        try {
            answer = br.readLine();
            if (answer.equals("Y") || answer.equals("y") || answer.equals("н") || answer.equals("Н")) {
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Понял, продолжим."));
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Введите название профиля."));
                String name = br.readLine();
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Введите код версии для профиля " + name + "."));
                String data = br.readLine();
                if (SearchFile(GetPathOfAPP.GetPathWithSep() + "data" + GetPathOfAPP.GetSep() + name) == true) {
                    if (writeToFile(GetPathOfAPP.GetPathWithSep() + "data" + GetPathOfAPP.GetSep() + name, data) == true) {
                        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Данные в профиль " + name + " успешно записаны."));
                    } else {
                        toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Данные в профиль " + name + " записать не удалось."));
                    }
                } else {
                    toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Профиль " + name + " не найден."));
                }
            } else {
                toConsole.print(genLogMessage.gen((byte) 1, false, NAME_APP + " - " + "Операция отменена."));
            }
        } catch (IOException e) {
            toConsole.print(genLogMessage.gen((byte) 3, false, NAME_APP + " - " + e.getMessage()));
        }
    }
}