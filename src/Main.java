import Services.InfoCollector;
import Supports.CipherCaesar;

public class Main {
    public static void main(String[] args) {
        InfoCollector infoCollector = new InfoCollector();
        infoCollector.start(args);

        CipherCaesar cipherCaesar = new CipherCaesar();
        cipherCaesar.start();
    }
}