package space.kiritron.duke_srv.ks_libs.pixel.tolchok;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static space.kiritron.duke_srv.ks_libs.pixel.filefunc.FileControls.*;

/**
 * Класс с методами для управления конфигами стандарта КС Толчок.
 * @author Киритрон Стэйблкор
 * @version 1.0
 */

public class core {
    /**
     * Запись данных в файл .TOLF, но при условии, что он пустой.
     * @param filename - Путь до файла.
     * @param data - Данные, которые нужно записать.
     * @return возвращает результат записи. OK - Всё в порядке.
     */
    public static String WriteCFG(String filename, String data) throws IOException {
        String out = "WRITE_TO_CFG_ERROR";
        if (SearchFile(filename) == true) {
            if (filename.contains(".tolf") == true) {
                FileWriter fstream1 = new FileWriter(filename);
                BufferedWriter out1 = new BufferedWriter(fstream1);
                out1.write("");
                out1.close();
                if (writeToFile(filename, data) == true) {
                    out = "OK";
                } else {
                    out = "ERROR_WRITE_TO_FILE";
                }
            } else {
                out = "ERROR_FORMAT_OF_FILE";
            }
        } else {
            out = "ERROR_FILE_NOT_FOUND";
        }
        return out;
    }

    /**
     * Изменение параметра в файле TOLF.
     * @param filename - Путь до файла.
     * @param category - Категория, в которой нужно обновить параметр.
     * @param field - Название параметра.
     * @param newParam - Новое значение параметра.
     * @return возвращает результат изменения параметра.
     */
    public static String WriteDataCFG(String filename, String category, String field, String newParam) throws IOException {
        String out = null;
        String cache = ReadCFGAll(filename);
        String CatOp = null;
        String CatCl = null;
        String CacheOne = null;
        String CacheTwo = null;

        int lenghtOfField = field.length();

        int index1 = category.indexOf(".");
        int index2 = category.indexOf("?");
        int index3 = category.indexOf("!");
        int index4 = category.indexOf('"');
        int index5 = category.indexOf("'");
        int index6 = category.indexOf(",");
        int index7 = category.indexOf("&");
        int index8 = category.indexOf("$");
        int index9 = category.indexOf(";");
        int index10 = category.indexOf(":");
        int index11 = category.indexOf("#");
        int index12 = category.indexOf("№");
        int index13 = category.indexOf("@");
        int index14 = category.indexOf("~");
        int index15 = category.indexOf("*");
        int index16 = category.indexOf("%");
        int index17 = category.indexOf(")");
        int index18 = category.indexOf("(");
        int index19 = category.indexOf("|");
        int index20 = category.indexOf("+");
        int index21 = category.indexOf("=");
        int index22 = category.indexOf("{");
        int index23 = category.indexOf("}");

        if((index1 == - 1) && (index2 == - 1) && (index3 == - 1) && (index4 == - 1) && (index5 == - 1) && (index6 == - 1) && (index7 == -1) && (index8 == -1) && (index9 == -1) && (index10 == -1) && (index11 == -1) && (index12 == -1) && (index13 == -1) && (index14 == -1) && (index15 == -1) && (index16 == -1) && (index17 == -1) && (index18 == -1) && (index19 == -1) && (index20 == -1) && (index21 == -1) && (index22 == -1) && (index23 == -1)) {
            if (cache.contains("[" + category + "]") == true) {
                if (cache.contains("[/" + category + "]") == true) {
                    CatOp = "[" + category + "]";
                    CatCl = "[/" + category + "]";
                    CacheOne = cache.format("\\%s[^)]+\\%s", "</", CatOp);
                    CacheTwo = cache.format("\\%s[^)]+\\%s", CatCl, "/>");

                    CacheOne = CacheOne.substring(9);
                    CacheTwo = CacheTwo.substring(1, CacheTwo.length() - (CacheTwo.length() - 1 - CatCl.length()));

                    cache = cache.replaceAll(String.format("\\%s[^)]+\\%s", "</", CatOp), "");
                    cache = cache.replaceAll(String.format("\\%s[^)]+\\%s", CatCl, "/>"), "");
                    int Field1 = cache.indexOf("- " + field + ": ") + 4 + lenghtOfField;
                    int Field2 = cache.indexOf(";", Field1);
                    String ParamOfFIELD = cache.substring(Field1, Field2);

                    cache = cache.replaceAll("- " + field + ": " + ParamOfFIELD + ";", "- " + field + ": " + newParam + ";");

                    String OutCache = "</" + "\n"  + "\t" + CacheOne + cache + CacheTwo + "\n"  + "/>";

                    try {
                        if (WriteCFG(filename, OutCache).equals("OK")) {
                            out = "OK";
                        } else {
                            out = "WRITE_TO_FILE_ERROR";
                        }
                    } catch (IOException e) {
                        out = "WRITE_TO_FILE_ERROR";
                    }
                } else {
                    out = "SEARCH_CATEGORY_FAILED";
                }
            } else {
                out = "SEARCH_CATEGORY_FAILED";
            }
        } else {
            out = "BLOCKED_CHARACTERS_DETECTED";
        }

        return out;
    }

    /**
     * Чтение параметра из файла TOLF.
     * @param filename - Путь до файла.
     * @param category - Категория, в которой нужно прочитать параметр.
     * @param field - Название параметра.
     * @return возвращает данные из параметра.
     */
    public static String ReadCFG(String filename, String category, String field) throws IOException {
        String out;
        if (SearchFile(filename) == true) {
            if (filename.contains(".tolf") == true) {
                String data = ReadFile(filename);
                out = ReadData(data, category, field);
            } else {
                out = "ERROR_FORMAT_OF_FILE";
            }
        } else {
            out = "ERROR_FILE_NOT_FOUND";
        }
        return out;
    }

    /**
     * Чтение файла TOLF.
     * @param filename - Путь до файла.
     * @return возвращает данные из файла TOLF.
     */
    public static String ReadCFGAll(String filename) throws IOException {
        String out;
        if (SearchFile(filename) == true) {
            if (filename.contains(".tolf") == true) {
                out = ReadFile(filename);
            } else {
                out = "ERROR_FORMAT_OF_FILE";
            }
        } else {
            out = "ERROR_FILE_NOT_FOUND";
        }
        return out;
    }

    public static String ReadData(String data, String category, String field) {
        String out = null;
        String cache = data;
        String CatOp = null;
        String CatCl = null;

        int lenghtOfField = field.length();

        int index1 = category.indexOf(".");
        int index2 = category.indexOf("?");
        int index3 = category.indexOf("!");
        int index4 = category.indexOf('"');
        int index5 = category.indexOf("'");
        int index6 = category.indexOf(",");
        int index7 = category.indexOf("&");
        int index8 = category.indexOf("$");
        int index9 = category.indexOf(";");
        int index10 = category.indexOf(":");
        int index11 = category.indexOf("#");
        int index12 = category.indexOf("№");
        int index13 = category.indexOf("@");
        int index14 = category.indexOf("~");
        int index15 = category.indexOf("*");
        int index16 = category.indexOf("%");
        int index17 = category.indexOf(")");
        int index18 = category.indexOf("(");
        int index19 = category.indexOf("|");
        int index20 = category.indexOf("+");
        int index21 = category.indexOf("=");
        int index22 = category.indexOf("{");
        int index23 = category.indexOf("}");

        if((index1 == -1) && (index2 == -1) && (index3 == -1) && (index4 == -1) && (index5 == -1) && (index6 == -1) && (index7 == -1) && (index8 == -1) && (index9 == -1) && (index10 == -1) && (index11 == -1) && (index12 == -1) && (index13 == -1) && (index14 == -1) && (index15 == -1) && (index16 == -1) && (index17 == -1) && (index18 == -1) && (index19 == -1) && (index20 == -1) && (index21 == -1) && (index22 == -1) && (index23 == -1)) {
            if (cache.contains("[" + category + "]") == true) {
                if (cache.contains("[/" + category + "]") == true) {
                    CatOp = "[" + category + "]";
                    CatCl = "[/" + category + "]";
                    cache = cache.replaceAll(String.format("\\%s[^)]+\\%s", "</", CatOp), "");
                    cache = cache.replaceAll(String.format("\\%s[^)]+\\%s", CatCl, "/>"), "");

                    int Field1 = cache.indexOf("- " + field + ": ") + 4 + lenghtOfField;
                    int Field2 = cache.indexOf(";", Field1);
                    String ParamOfFIELD = cache.substring(Field1, Field2);

                    out = ParamOfFIELD;
                } else {

                }
            } else {

            }
        } else {
            out = "BLOCKED_CHARACTERS_DETECTED";
        }

        return out;
    }
}
