package marcocoscarelli;

import com.github.javafaker.Faker;
import marcocoscarelli.mylibrary.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Collezione collezione = new Collezione();
        Scanner scanner = new Scanner(System.in);
        Faker faker = new Faker();
        LogManager logManager = new LogManager();


        initializeDefaultGames(collezione, faker);

        boolean running = true;

        while (running) {
            mostraMenu();
            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {
                    case 1:
                        aggiungiGioco(collezione, scanner);
                        break;
                    case 2:
                        cercaGiocoPerId(collezione, scanner);
                        break;
                    case 3:
                        cercaGiochiPerPrezzo(collezione, scanner);
                        break;
                    case 4:
                        cercaGiochiPerNumeroGiocatori(collezione, scanner);
                        break;
                    case 5:
                        rimuoviGioco(collezione, scanner);
                        break;
                    case 6:
                        aggiornaGioco(collezione, scanner);
                        break;
                    case 7:
                        collezione.statisticheCollezione();
                        break;
                    case 8:
                        visualizzaTuttiGiochi(collezione);
                        break;
                    case 9:
                        running = false;
                        System.out.println("Arrivederci!");
                        break;
                    case 10:
                        logManager.scriviCatalogoInLog(collezione.getGiochi());
                        break;
                    case 11:
                        logManager.visualizzaLog();
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Per favore, riprova.");
                scanner.nextLine(); // Consuma l'input errato
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void initializeDefaultGames(Collezione collezione, Faker faker) {
        // Aggiungi giochi predefiniti
        for (int i = 1; i <= 3; i++) {
            String titolo = faker.esports().game();
            int anno = faker.number().numberBetween(2000, 2023);
            double prezzo = faker.number().randomDouble(2, 10, 100);
            String piattaforma = faker.gameOfThrones().house();
            int durata = faker.number().numberBetween(5, 50);
            Videogioco.Genere genere = Videogioco.Genere.values()[faker.number().numberBetween(0, Videogioco.Genere.values().length)];
            Videogioco videogioco = new Videogioco(i, titolo, anno, prezzo, piattaforma, durata, genere);
            collezione.aggiungiGioco(videogioco);
        }


        for (int i = 4; i <= 5; i++) {
            String titolo = faker.book().title();
            int anno = faker.number().numberBetween(1980, 2023);
            double prezzo = faker.number().randomDouble(2, 15, 100);
            int numeroGiocatori = faker.number().numberBetween(2, 10);
            int durataMedia = faker.number().numberBetween(30, 120);
            GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(i, titolo, anno, prezzo, numeroGiocatori, durataMedia);
            collezione.aggiungiGioco(giocoDaTavolo);
        }
    }

    private static void mostraMenu() {
        System.out.println("\n--- MENU PRINCIPALE ---");
        System.out.println("1. Aggiungi un gioco");
        System.out.println("2. Cerca gioco per ID");
        System.out.println("3. Cerca giochi per prezzo");
        System.out.println("4. Cerca giochi da tavolo per numero di giocatori");
        System.out.println("5. Rimuovi gioco per ID");
        System.out.println("6. Aggiorna gioco per ID");
        System.out.println("7. Visualizza statistiche collezione");
        System.out.println("8. Visualizza tutti i giochi della collezione");
        System.out.println("9. Esci");
        System.out.println("10. Scrivi catalogo giochi in log");
        System.out.println("11. Visualizza file di log");
        System.out.print("Seleziona un'opzione: ");
    }

    private static void aggiungiGioco(Collezione collezione, Scanner scanner) {
        System.out.println("Aggiungi un nuovo gioco:");
        System.out.print("Inserisci l'ID del gioco: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (collezione.esisteId(id)) {
            System.out.println("ID già esistente. Scegli un ID diverso.");
            return;
        }

        System.out.print("Inserisci il titolo: ");
        String titolo = scanner.nextLine();

        System.out.print("Inserisci l'anno di pubblicazione: ");
        int anno = scanner.nextInt();

        System.out.print("Inserisci il prezzo: ");
        double prezzo = scanner.nextDouble();

        System.out.println("Seleziona il tipo di gioco:");
        System.out.println("1. Videogioco");
        System.out.println("2. Gioco da Tavolo");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            aggiungiVideogioco(collezione, id, titolo, anno, prezzo, scanner);
        } else if (tipo == 2) {
            aggiungiGiocoDaTavolo(collezione, id, titolo, anno, prezzo, scanner);
        } else {
            System.out.println("Scelta non valida.");
        }
    }

    private static void aggiungiVideogioco(Collezione collezione, int id, String titolo, int anno, double prezzo, Scanner scanner) {
        scanner.nextLine(); // Consuma il newline

        System.out.print("Inserisci la piattaforma (es. PC, PS5, XBOX): ");
        String piattaforma = scanner.nextLine();

        System.out.print("Inserisci la durata del gioco (in ore): ");
        int durata = scanner.nextInt();

        System.out.println("Seleziona il genere:");
        for (Videogioco.Genere genere : Videogioco.Genere.values()) {
            System.out.println(genere.ordinal() + 1 + ". " + genere);
        }
        int genereScelta = scanner.nextInt();
        Videogioco.Genere genere = Videogioco.Genere.values()[genereScelta - 1];

        Videogioco videogioco = new Videogioco(id, titolo, anno, prezzo, piattaforma, durata, genere);
        collezione.aggiungiGioco(videogioco);
        System.out.println("Videogioco aggiunto con successo.");
    }

    private static void aggiungiGiocoDaTavolo(Collezione collezione, int id, String titolo, int anno, double prezzo, Scanner scanner) {
        System.out.print("Inserisci il numero di giocatori: ");
        int numeroGiocatori = scanner.nextInt();

        System.out.print("Inserisci la durata media (in minuti): ");
        int durataMedia = scanner.nextInt();

        GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(id, titolo, anno, prezzo, numeroGiocatori, durataMedia);
        collezione.aggiungiGioco(giocoDaTavolo);
        System.out.println("Gioco da tavolo aggiunto con successo.");
    }

    private static void cercaGiocoPerId(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci l'ID del gioco da cercare: ");
        int id = scanner.nextInt();
        Gioco gioco = collezione.cercaPerId(id);
        if (gioco != null) {
            System.out.println("Gioco trovato: " + gioco);
        } else {
            System.out.println("Nessun gioco trovato con l'ID: " + id);
        }
    }

    private static void cercaGiochiPerPrezzo(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci il prezzo massimo: ");
        double prezzoMax = scanner.nextDouble();
        List<Gioco> giochiTrovati = collezione.cercaPerPrezzo(prezzoMax);
        if (giochiTrovati.isEmpty()) {
            System.out.println("Nessun gioco trovato con prezzo inferiore a " + prezzoMax);
        } else {
            System.out.println("Giochi trovati:");
            for (Gioco gioco : giochiTrovati) {
                System.out.println(gioco);
            }
        }
    }

    private static void cercaGiochiPerNumeroGiocatori(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci il numero di giocatori: ");
        int numeroGiocatori = scanner.nextInt();
        List<GiocoDaTavolo> giochiTrovati = collezione.cercaPerNumeroGiocatori(numeroGiocatori);
        if (giochiTrovati.isEmpty()) {
            System.out.println("Nessun gioco trovato per il numero di giocatori: " + numeroGiocatori);
        } else {
            System.out.println("Giochi da tavolo trovati:");
            for (Gioco gioco : giochiTrovati) {
                System.out.println(gioco);
            }
        }
    }

    private static void rimuoviGioco(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci l'ID del gioco da rimuovere: ");
        int id = scanner.nextInt();
        if (collezione.rimuoviGioco(id)) {
            System.out.println("Gioco rimosso con successo.");
        } else {
            System.out.println("Nessun gioco trovato con l'ID: " + id);
        }
    }

    private static void aggiornaGioco(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci l'ID del gioco da aggiornare: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Gioco gioco = collezione.cercaPerId(id);
        if (gioco != null) {
            System.out.print("Inserisci il nuovo titolo (leave empty to keep current): ");
            String titolo = scanner.nextLine();
            if (!titolo.isEmpty()) {
                gioco.setTitolo(titolo);
            }

            System.out.print("Inserisci il nuovo anno di pubblicazione (leave empty to keep current): ");
            String annoInput = scanner.nextLine();
            if (!annoInput.isEmpty()) {
                gioco.setAnno(Integer.parseInt(annoInput));
            }

            System.out.print("Inserisci il nuovo prezzo (leave empty to keep current): ");
            String prezzoInput = scanner.nextLine();
            if (!prezzoInput.isEmpty()) {
                gioco.setPrezzo(Double.parseDouble(prezzoInput));
            }

            if (gioco instanceof Videogioco) {
                Videogioco videogioco = (Videogioco) gioco;

                System.out.print("Inserisci la nuova piattaforma (leave empty to keep current): ");
                String piattaforma = scanner.nextLine();
                if (!piattaforma.isEmpty()) {
                    videogioco.setPiattaforma(piattaforma);
                }

                System.out.print("Inserisci la nuova durata del gioco (in ore, leave empty to keep current): ");
                String durataInput = scanner.nextLine();
                if (!durataInput.isEmpty()) {
                    videogioco.setDurataGioco(Integer.parseInt(durataInput));
                }

                System.out.println("Videogioco aggiornato con successo.");
            } else if (gioco instanceof GiocoDaTavolo) {
                GiocoDaTavolo giocoDaTavolo = (GiocoDaTavolo) gioco;

                System.out.print("Inserisci il nuovo numero di giocatori (leave empty to keep current): ");
                String numeroGiocatoriInput = scanner.nextLine();
                if (!numeroGiocatoriInput.isEmpty()) {
                    giocoDaTavolo.setNumeroGiocatori(Integer.parseInt(numeroGiocatoriInput));
                }

                System.out.print("Inserisci la nuova durata media (in minuti, leave empty to keep current): ");
                String durataMediaInput = scanner.nextLine();
                if (!durataMediaInput.isEmpty()) {
                    giocoDaTavolo.setDurataMedia(Integer.parseInt(durataMediaInput));
                }

                System.out.println("Gioco da tavolo aggiornato con successo.");
            }
        } else {
            System.out.println("Nessun gioco trovato con l'ID: " + id);
        }
    }

    private static void visualizzaTuttiGiochi(Collezione collezione) {
        List<Gioco> giochi = collezione.getGiochi();
        if (giochi.isEmpty()) {
            System.out.println("Nessun gioco nella collezione.");
        } else {
            System.out.println("Giochi nella collezione:");
            for (Gioco gioco : giochi) {
                System.out.println(gioco);
            }
        }
    }
}
