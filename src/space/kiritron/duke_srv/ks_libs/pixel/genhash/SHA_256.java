package space.kiritron.duke_srv.ks_libs.pixel.genhash;

/**
 * Класс с методом по созданию хеша алгоритмом SHA256.
 * @author Киритрон Стэйблкор
 * @version 1.1
 */

public class SHA_256 {
    /**
     * Создание хеша алгоритмом SHA256.
     * @param Message - Сообщение, которое нужно пропустить через алгоритм SHA256.
     * @return возвращает хеш SHA256.
     */

    //Киритрон: Метод сохранён и переделан, чтобы избежать проблем с совместимостью.
    public static String GenHash(String Message) {
        return Gen.Hash(Gen.SHA256, Message);
    }
}