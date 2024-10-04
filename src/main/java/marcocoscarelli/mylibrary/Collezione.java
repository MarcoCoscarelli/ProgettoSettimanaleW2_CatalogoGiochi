package marcocoscarelli.mylibrary;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Collezione {
    private Map<Integer, Gioco> giochi = new HashMap<>();
    private Faker faker = new Faker();


    public void aggiungiGioco(Gioco gioco) {
        if (giochi.containsKey(gioco.getId())) {
            throw new IllegalArgumentException("Il gioco con ID " + gioco.getId() + " esiste già.");
        }
        giochi.put(gioco.getId(), gioco);
    }

    public boolean esisteId(int id) {
        return giochi.containsKey(id);
    }


    public Gioco cercaPerId(int id) {
        return Optional.ofNullable(giochi.get(id))
                .orElseThrow(() -> new NoSuchElementException("Nessun gioco trovato con ID: " + id));
    }


    public List<Gioco> cercaPerPrezzo(double prezzo) {
        return giochi.values().stream()
                .filter(gioco -> gioco.getPrezzo() < prezzo)
                .collect(Collectors.toList());
    }


    public List<GiocoDaTavolo> cercaPerNumeroGiocatori(int numeroGiocatori) {
        return giochi.values().stream()
                .filter(g -> g instanceof GiocoDaTavolo)
                .map(g -> (GiocoDaTavolo) g)
                .filter(g -> g.getNumeroGiocatori() == numeroGiocatori)
                .collect(Collectors.toList());
    }


    public boolean rimuoviGioco(int id) {
        if (!giochi.containsKey(id)) {
            return false; // Gioco non trovato
        }
        giochi.remove(id);
        return true; // Gioco rimosso con successo
    }


    public void aggiornaPrezzoGioco(int id, double nuovoPrezzo) {
        Gioco gioco = cercaPerId(id);
        gioco.setPrezzo(nuovoPrezzo);
    }


    public List<Gioco> getGiochi() {
        return new ArrayList<>(giochi.values());
    }


    public void visualizzaTuttiIGiochi() {
        if (giochi.isEmpty()) {
            System.out.println("La collezione è vuota.");
        } else {
            giochi.values().forEach(gioco -> {
                System.out.println(gioco);
            });
        }
    }


    public void statisticheCollezione() {
        long numeroVideogiochi = giochi.values().stream().filter(g -> g instanceof Videogioco).count();
        long numeroGiochiDaTavolo = giochi.values().stream().filter(g -> g instanceof GiocoDaTavolo).count();

        Optional<Gioco> giocoPiuCaro = giochi.values().stream().max(Comparator.comparingDouble(Gioco::getPrezzo));

        double mediaPrezzi = giochi.values().stream().mapToDouble(Gioco::getPrezzo).average().orElse(0);

        System.out.println("Numero di Videogiochi: " + numeroVideogiochi);
        System.out.println("Numero di Giochi da Tavolo: " + numeroGiochiDaTavolo);
        giocoPiuCaro.ifPresent(g -> System.out.println("Gioco più costoso: " + g));
        System.out.println("Prezzo medio: " + mediaPrezzi);
    }

    // logica dei giochi predefiniti
    public void aggiungiGiochiPredefiniti() {
        for (int i = 0; i < 5; i++) {
            int id = i + 1;
            String titolo = faker.book().title();
            int annoPubblicazione = faker.number().numberBetween(1980, 2024);
            double prezzo = faker.random().nextDouble() * 100; // Prezzo casuale
            int numeroGiocatori = faker.number().numberBetween(2, 10);
            int durataMediaPartita = faker.number().numberBetween(30, 180);

            GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(id, titolo, annoPubblicazione, prezzo, numeroGiocatori, durataMediaPartita);
            aggiungiGioco(giocoDaTavolo);
        }
    }


}
