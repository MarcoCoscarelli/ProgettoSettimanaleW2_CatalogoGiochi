package marcocoscarelli.mylibrary;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class LogManager {

    private static final String LOG_FILE_PATH = "logs/catalogo_giochi.log";


    public void scriviCatalogoInLog(List<Gioco> giochi) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH))) {
            for (Gioco gioco : giochi) {
                writer.write(gioco.toString());
                writer.newLine();
            }
            System.out.println("Catalogo giochi scritto nel file di log.");
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura del file di log: " + e.getMessage());
        }
    }


    public void visualizzaLog() {
        File logFile = new File(LOG_FILE_PATH);
        if (!logFile.exists()) {
            System.out.println("File di log non trovato.");
            return;
        }

        try (Scanner scanner = new Scanner(logFile)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Errore nel leggere il file di log: " + e.getMessage());
        }
    }
}
