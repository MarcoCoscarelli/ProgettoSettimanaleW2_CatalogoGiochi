# Gioco Collection Application

## Descrizione

Questa applicazione gestisce una collezione di giochi, sia da tavolo che videogiochi. Permette di aggiungere, cercare, rimuovere e visualizzare i giochi nella collezione, oltre a fornire statistiche utili.

## Funzionalità

### 1. Aggiunta di Giochi

- **`aggiungiGioco(Gioco gioco)`**: Aggiunge un nuovo gioco alla collezione. Se il gioco con lo stesso ID esiste già, verrà sollevata un'eccezione.

### 2. Ricerca di Giochi

- **`cercaPerId(int id)`**: Restituisce il gioco con l'ID specificato. Se non esiste, solleva un'eccezione.
  
- **`cercaPerPrezzo(double prezzo)`**: Restituisce una lista di giochi il cui prezzo è inferiore al valore specificato.

- **`cercaPerNumeroGiocatori(int numeroGiocatori)`**: Restituisce una lista di giochi da tavolo che supportano il numero specificato di giocatori.

### 3. Rimozione di Giochi

- **`rimuoviGioco(int id)`**: Rimuove il gioco con l'ID specificato dalla collezione. Se il gioco non esiste, solleva un'eccezione.

### 4. Aggiornamento di Giochi

- **`aggiornaGioco(int id, Gioco nuovoGioco)`**: Aggiorna i dettagli di un gioco esistente.

### 5. Visualizzazione dei Giochi

- **`visualizzaTuttiIGiochi()`**: Stampa tutti i giochi presenti nella collezione.

### 6. Statistiche della Collezione

- **`statisticheCollezione()`**: Mostra il numero totale di videogiochi e giochi da tavolo, il gioco più costoso e il prezzo medio dei giochi nella collezione.

### 7. Aggiunta di Giochi Predefiniti

- **`aggiungiGiochiPredefiniti()`**: Aggiunge una serie di giochi generati casualmente utilizzando la libreria Faker. Questa funzione popola la collezione con giochi da tavolo predefiniti con dati casuali.

## Struttura del Progetto

Il progetto è organizzato in più classi, ognuna delle quali gestisce un aspetto specifico dell'applicazione:

- **`Gioco`**: Classe astratta che rappresenta un gioco generico, contenente attributi comuni come ID, titolo, anno di pubblicazione e prezzo.
  
- **`GiocoDaTavolo`**: Estende `Gioco` e aggiunge attributi specifici per i giochi da tavolo, come il numero di giocatori e la durata media della partita.

- **`Videogioco`**: Estende `Gioco` (se presente) e gestisce attributi specifici per i videogiochi.

- **`Collezione`**: Gestisce la raccolta di giochi e fornisce metodi per interagire con essi.

## Dipendenze

- **Faker**: Utilizzato per generare dati casuali per la creazione di giochi predefiniti.
- **Maven**: Questo progetto è gestito tramite Maven, il che facilita la gestione delle dipendenze e la costruzione del progetto.

## Come Eseguire

1. Clona il repository.
2. Assicurati di avere Java e Maven installati sul tuo sistema.
3. Naviga nella cartella del progetto e utilizza il comando `mvn clean install` per compilare il progetto.
4. Esegui il progetto utilizzando il tuo IDE preferito o con il comando `mvn exec:java` se configurato.

## Contributi

Se desideri contribuire al progetto, sentiti libero di inviare una pull request o aprire un problema.

## Licenza

Questo progetto è concesso in licenza sotto la [MIT License](LICENSE).
