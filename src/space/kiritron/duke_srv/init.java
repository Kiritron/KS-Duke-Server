package space.kiritron.duke_srv;

import space.kiritron.duke_srv.core.Server;
import space.kiritron.duke_srv.ks_libs.pixel.CheckerDIR;
import space.kiritron.duke_srv.ks_libs.pixel.filefunc.GetPathOfAPP;

import java.io.IOException;

import static space.kiritron.duke_srv.core.console.ConsoleThread;
import static space.kiritron.duke_srv.ks_libs.pixel.filefunc.FileControls.*;

public class init {
    public static String NAME_APP = "КС Дьюк Сервер";
    public static String VER_APP = "Вер.: 1.0-КСДС";

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
