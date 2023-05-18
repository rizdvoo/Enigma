package Services;

import Supports.Constants;
import java.util.ArrayList;

public class Scrambler {
    public static boolean isEncrypt;
    public static boolean isDecrypt;
    public static boolean isBruteForce;
    public static ArrayList<Character> alphabet = Constants.ALPHABET_EN;

    public ArrayList<Character> encrypt(ArrayList<Character> fileArray, int key) {
        for (int i = 0; i < fileArray.size(); i++) {
            for (int j = 0; j < alphabet.size(); j++) {
                if (fileArray.get(i) == alphabet.get(j)) {
                    int encryptedIndex = (j + key) % alphabet.size();
                    if (encryptedIndex < 0) {
                        encryptedIndex += alphabet.size();
                    }

                    fileArray.set(i, alphabet.get(encryptedIndex));
                    break;
                }
            }
        }
        return fileArray;
    }

    public ArrayList<Character> decrypt(ArrayList<Character> fileArray, int key) {
        for (int i = 0; i < fileArray.size(); i++) {
            for (int j = 0; j < alphabet.size(); j++) {
                if (fileArray.get(i) == alphabet.get(j)) {
                    int decryptedIndex = (j - key) % alphabet.size();
                    if (decryptedIndex < 0) {
                        decryptedIndex += alphabet.size();
                    }

                    fileArray.set(i, alphabet.get(decryptedIndex));
                    break;
                }
            }
        }
        return fileArray;
    }

    public ArrayList<Character> bruteForce(ArrayList<Character> fileArray) {
        ArrayList<Character> result = new ArrayList<>();

        for (int i = 1; i < alphabet.size(); i++) {
            ArrayList<Character> decryptedArray = decrypt(fileArray, i);

            int dotIndex = decryptedArray.indexOf('.');

            if (dotIndex != -1) {
                char enterIndex = decryptedArray.get((dotIndex + 1));
                if (enterIndex == '\n') {
                    result = decryptedArray;
                    break;
                }
            }
        }

        return result;
    }
}
