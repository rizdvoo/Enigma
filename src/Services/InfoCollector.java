package Services;

import Supports.CipherCaesar;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InfoCollector {
    public void start(String[] args) {
        if (args.length == 3) {
            System.out.println("------------------------------------------------------");
            System.out.println("Привіт користувач! Я - шифр Цезаря!");
            System.out.println("Я допоможу тобі розсекретити секретні данні Кремля :)");
            System.out.println("------------------------------------------------------");

            startInitialization(args[0], args[1], Integer.parseInt(args[2]));
        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.println("------------------------------------------------------");
            System.out.println("Привіт користувач! Я - шифр Цезаря!");
            System.out.println("Я допоможу тобі розсекретити секретні данні Кремля :)");
            System.out.println("------------------------------------------------------");

            System.out.println("Введи, що ти хочеш зробити з файлом - " + "[E]Encrypt, [D]Decrypt, [B]Brute-force");
            String command = scanner.nextLine();

            System.out.println("Введи, абсолютний шлях до файлу!");
            String file = scanner.nextLine();

            int key = 0;
            if (!command.equals("B")){
                System.out.println("Введи, на скільки символів ти хочеш зашифрувати/розшифрувати текст!");
                try {
                    key = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Ключ повинен бути числом! Спробуй ще раз!");
                    System.out.println("------------------------------------------------------");
                    System.exit(0);
                }
            }

            startInitialization(command, file, key);
        }
    }


    public void startInitialization(String command, String file, int key) {
        switch (command.toUpperCase()) {
            case ("E") -> Scrambler.isEncrypt = true;
            case ("D") -> Scrambler.isDecrypt = true;
            case ("B") -> Scrambler.isBruteForce = true;
            default -> {
                System.out.println("------------------------------------------------------");
                System.out.println("Неправильно обрано команду! Спробуй ще раз!");
                System.out.println("------------------------------------------------------");
                System.exit(0);
            }
        }

        FileService.inputFile = Path.of(file).toAbsolutePath();

        CipherCaesar.key = key;
        int sizeAlphabet = Scrambler.alphabet.size();
        if (key > sizeAlphabet) {
            CipherCaesar.key = key % sizeAlphabet;
        }
    }
}
