import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.nio.file.Path;

public class PathChecker {
    public static void main(String[] args) {
        checker();
    }
    private static Map<Integer, Function<String, DicInterface>> dictionaryFactory = new HashMap<>();

    static {
        dictionaryFactory.put(1, EnglishDic::new); //инициализация словаря
        dictionaryFactory.put(2, NumberDic::new);

    }

    public static void checker() {
        Scanner scanner = new Scanner(System.in);
        DicInterface dictionary = null;

        while (true) {
            try {
                System.out.println("Выберите словарь для работы (1 - Английский, 2 - Цифры):");
                int dictionaryChoice = Integer.parseInt(scanner.nextLine());

                if (!dictionaryFactory.containsKey(dictionaryChoice)) {
                    System.out.println("Неверный выбор словаря. Пожалуйста, введите корректный номер.");
                    continue;
                }

                System.out.println("Введите путь к файлу:");
                String filePath = scanner.nextLine();

                if (!validatePath(filePath)) {
                    System.out.println("Файл не существует. Пожалуйста, попробуйте еще раз.");
                    continue;
                }

                dictionary = dictionaryFactory.get(dictionaryChoice).apply(filePath);

                Menu menu = new Menu(dictionary);
                menu.manage();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите число.");
            }
        }
        scanner.close();
    }
    private static boolean validatePath(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path) && Files.isRegularFile(path);
    }
}