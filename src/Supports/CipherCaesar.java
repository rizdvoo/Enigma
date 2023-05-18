package Supports;

import Services.FileService;
import Services.Scrambler;

import java.nio.file.Path;
import java.util.ArrayList;

public class CipherCaesar {
    public static int key;
    public void start() {
        FileService fileService = new FileService();
        Scrambler scrambler = new Scrambler();

        ArrayList<Character> fileArray = fileService.read();
        ArrayList<Character> madeFileArray;

        if (Scrambler.isEncrypt) {
            madeFileArray = scrambler.encrypt(fileArray, key);
        } else if (Scrambler.isDecrypt) {
            madeFileArray = scrambler.decrypt(fileArray, key);
        } else {
            madeFileArray = scrambler.bruteForce(fileArray);
            if (madeFileArray.size() == 0) {
                System.out.println("------------------------------------------------------");
                System.out.println("Операцію не виконано! Файл не містить символи для корректної роботи Brute Force!");
                System.out.println("------------------------------------------------------");

                System.exit(0);
            }
        }

        Path newFile = fileService.create();
        Path fileReady = fileService.write(newFile, madeFileArray);

        System.out.println("------------------------------------------------------");
        System.out.println("Роботу виконано успішно! Ось ваш файл: " + fileReady);
        System.out.println("------------------------------------------------------");
    }
}
