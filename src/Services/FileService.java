package Services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileService {
    public static Path inputFile;
    public ArrayList<Character> read() {
        ArrayList<Character> result = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(inputFile.toFile());
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            int data;
            while ((data = bufferedReader.read()) != -1) {
                result.add((char) data);
            }

        } catch (Exception e) {
            System.out.println("------------------------------------------------------");
            System.out.println("Не вдалось знайти файл! Спробуй ще раз!");
            System.out.println("------------------------------------------------------");
            System.exit(0);
        }

        return result;
    }

    public Path write(Path fileReady, ArrayList<Character> arrayList) {
        try (OutputStream outputStream = new FileOutputStream(fileReady.toFile());
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

            for (char element : arrayList) {
                bufferedWriter.write(element);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileReady;
    }

    public Path create() {
        Scanner scanner = new Scanner(System.in);

        String originalFileName = String.valueOf(inputFile.getFileName());
        int dotIndex = originalFileName.lastIndexOf(".");

        String fileNameWithoutExtension = originalFileName.substring(0, dotIndex);
        String fileExtension = originalFileName.substring(dotIndex);

        String changedFileName = fileNameWithoutExtension + "[Brute-force decrypted]" + fileExtension;
        if (Scrambler.isEncrypt) {
            changedFileName = fileNameWithoutExtension + "[Encrypted]" + fileExtension;
        } else if (Scrambler.isDecrypt) {
            changedFileName = fileNameWithoutExtension + "[Decrypted]" + fileExtension;
        }

        Path result = Path.of(String.valueOf(inputFile.getParent()), changedFileName);

        try {
            Files.createFile(result);
        } catch (IOException e) {
            System.out.println("Файл с таким іменем вже існує, якщо хочете створите ще один файл - натисніть [Y], якщо не хочете [Будь яку іншу кнопку]!");
            String variant = scanner.nextLine();

            if (variant.equalsIgnoreCase("Y")) {
                result = createAnotherOne(2);
            } else {
                System.exit(0);
            }
        }

        return result;
    }

    public Path createAnotherOne(int queueNumber) {
        String originalFileName = String.valueOf(inputFile.getFileName());
        int dotIndex = originalFileName.lastIndexOf(".");

        String fileNameWithoutExtension = originalFileName.substring(0, dotIndex);
        String fileExtension = originalFileName.substring(dotIndex);

        String changedFileName = fileNameWithoutExtension + "[BruteForce]" + " - " + queueNumber + fileExtension;
        if (Scrambler.isEncrypt) {
            changedFileName = fileNameWithoutExtension + "[Encrypt]" + " - " + queueNumber + fileExtension;
        } else if (Scrambler.isDecrypt) {
            changedFileName = fileNameWithoutExtension + "[Decrypt]" + " - " + queueNumber + fileExtension;
        }

        Path result = Path.of(String.valueOf(inputFile.getParent()), changedFileName);

        try {
            Files.createFile(result);
        } catch (IOException e) {
            result = createAnotherOne(queueNumber + 1);
        }

        return result;
    }
}

