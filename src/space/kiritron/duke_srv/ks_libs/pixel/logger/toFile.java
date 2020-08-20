package space.kiritron.duke_srv.ks_libs.pixel.logger;

import space.kiritron.duke_srv.ks_libs.pixel.GDate;
import space.kiritron.duke_srv.ks_libs.pixel.filefunc.GetPathOfAPP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс с методами для создания ЛОГ файлов.
 * @author Киритрон Стэйблкор
 * @version 1.0
 */

public class toFile {
    private static GDate TADClass = new GDate();
    private static void mlogg_sm(String prefixLogFile, String log_message) {
        String output = log_message;
        LogFileWriter(prefixLogFile, output);
    }

    private static void LogFileWriter(String prefixLogFile, String log_messageFile) {
        File logFile;
        if (prefixLogFile.equals("")) {
            logFile = new File(GetPathOfAPP.GetPathWithSep() + "logs" + GetPathOfAPP.GetSep() + TADClass.GetDate + ".log");
        } else {
            logFile = new File(GetPathOfAPP.GetPathWithSep() + "logs" + GetPathOfAPP.GetSep() + prefixLogFile + "-" + TADClass.GetDate + ".log");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            String lineSeparator = System.getProperty("line.separator");
            writer.write(log_messageFile + lineSeparator);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запись лог файла.
     * @param log_message - Сообщение, которое нужно записать в лог файл.
     * @param prefixLogFile - Префикс лог файла.
     */
    public static void WriteLog(String log_message, String prefixLogFile) { mlogg_sm(prefixLogFile, log_message); }

    /**
     * Запись лог файла, но уже без префикса в названии лог файла.
     * @param log_message - Сообщение, которое нужно записать в лог файл.
     */
    public static void WriteLogNoPrefix(String log_message) {
        mlogg_sm("", log_message);
    }
}
