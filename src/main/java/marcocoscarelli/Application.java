package marcocoscarelli;

import com.github.javafaker.Faker;
import marcocoscarelli.mylibrary.Collezione;
import marcocoscarelli.mylibrary.Gioco;
import marcocoscarelli.mylibrary.GiocoDaTavolo;
import marcocoscarelli.mylibrary.Videogioco;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Collezione collezione = new Collezione();
        Scanner scanner = new Scanner(System.in);
        Faker faker = new Faker();

        // giochi predefiniti utilizzando Faker
        for (int i = 1; i <= 3; i++) {
            String titolo = faker.esports().game();
            int anno = faker.number().numberBetween(2000, 2023);
            double prezzo = faker.number().randomDouble(2, 10, 100);
            String piattaforma = faker.gameOfThrones().house(); // Piattaforma casuale per i giochi predefiniti
            int durata = faker.number().numberBetween(5, 50);
            Videogioco.Genere genere = Videogioco.Genere.values()[faker.number().numberBetween(0, Videogioco.Genere.values().length)];
            Videogioco videogioco = new Videogioco(i, titolo, anno, prezzo, piattaforma, durata, genere);
            collezione.aggiungiGioco(videogioco);
        }

        // Aggiungi due giochi da tavolo predefiniti
        for (int i = 4; i <= 5; i++) {
            String titolo = faker.book().title(); // Faker non supportava titoli di giochi dunque ho usato altro
            int anno = faker.number().numberBetween(1980, 2023);
            double prezzo = faker.number().randomDouble(2, 15, 100);
            int numeroGiocatori = faker.number().numberBetween(2, 10);
            int durataMedia = faker.number().numberBetween(30, 120); // Durata casuale in minuti
            GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(i, titolo, anno, prezzo, numeroGiocatori, durataMedia);
            collezione.aggiungiGioco(giocoDaTavolo);
        }

        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU PRINCIPALE ---");
            System.out.println("1. Aggiungi un gioco");
            System.out.println("2. Cerca gioco per ID");
            System.out.println("3. Cerca giochi per prezzo");
            System.out.println("4. Cerca giochi da tavolo per numero di giocatori");
            System.out.println("5. Rimuovi gioco per ID");
            System.out.println("6. Aggiorna gioco per ID");
            System.out.println("7. Visualizza statistiche collezione");
            System.out.println("8. Visualizza tutti i giochi della collezione"); // Opzione per visualizzare i giochi
            System.out.println("9. Esci");
            System.out.print("Seleziona un'opzione: ");

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

    // aggiungere un gioco alla collezione
    private static void aggiungiGioco(Collezione collezione, Scanner scanner) {
        System.out.println("Aggiungi un nuovo gioco:");
        System.out.print("Inserisci l'ID del gioco: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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
            scanner.nextLine();
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
            System.out.println("Videogioco aggiunto con successo!");

        } else if (tipo == 2) {
            System.out.print("Inserisci il numero di giocatori (tra 2 e 10): ");
            int numeroGiocatori = scanner.nextInt();

            System.out.print("Inserisci la durata media di una partita (in minuti): ");
            int durataMedia = scanner.nextInt();

            GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(id, titolo, anno, prezzo, numeroGiocatori, durataMedia);
            collezione.aggiungiGioco(giocoDaTavolo);
            System.out.println("Gioco da tavolo aggiunto con successo!");

        } else {
            System.out.println("Scelta non valida.");
        }
    }

    // ricerca un gioco per ID
    private static void cercaGiocoPerId(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci l'ID del gioco da cercare: ");
        int id = scanner.nextInt();

        try {
            Gioco gioco = collezione.cercaPerId(id);
            System.out.println("Gioco trovato: " + gioco);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    //  ricerca giochi con prezzo inferiore a un dato valore
    private static void cercaGiochiPerPrezzo(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci il prezzo massimo: ");
        double prezzo = scanner.nextDouble();

        List<Gioco> giochi = collezione.cercaPerPrezzo(prezzo);
        if (giochi.isEmpty()) {
            System.out.println("Nessun gioco trovato con prezzo inferiore a " + prezzo);
        } else {
            System.out.println("Giochi trovati:");
            giochi.forEach(System.out::println);
        }
    }

    // ricerca giochi da tavolo per numero di giocatori
    private static void cercaGiochiPerNumeroGiocatori(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci il numero di giocatori: ");
        int numeroGiocatori = scanner.nextInt();

        List<GiocoDaTavolo> giochi = collezione.cercaPerNumeroGiocatori(numeroGiocatori);
        if (giochi.isEmpty()) {
            System.out.println("Nessun gioco da tavolo trovato per " + numeroGiocatori + " giocatori.");
        } else {
            System.out.println("Giochi da tavolo trovati:");
            giochi.forEach(System.out::println);
        }
    }

    // Funzione per rimuovere un gioco per ID
    private static void rimuoviGioco(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci l'ID del gioco da rimuovere: ");
        int id = scanner.nextInt();

        try {
            collezione.rimuoviGioco(id);
            System.out.println("Gioco rimosso con successo.");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void aggiornaGioco(Collezione collezione, Scanner scanner) {
        System.out.print("Inserisci l'ID del gioco da aggiornare: ");
        int id = scanner.nextInt();

        System.out.print("Inserisci il nuovo prezzo: ");
        double nuovoPrezzo = scanner.nextDouble();

        collezione.aggiornaPrezzoGioco(id, nuovoPrezzo);
        System.out.println("Prezzo aggiornato con successo.");
    }

    
    private static void visualizzaTuttiGiochi(Collezione collezione) {
        List<Gioco> giochi = collezione.getGiochi();
        if (giochi.isEmpty()) {
            System.out.println("La collezione è vuota.");
        } else {
            System.out.println("Giochi nella collezione:");
            giochi.forEach(System.out::println);
        }
    }
}
