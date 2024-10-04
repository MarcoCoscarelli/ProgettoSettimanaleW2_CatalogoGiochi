package marcocoscarelli.mylibrary;

public class GiocoDaTavolo extends Gioco {
    private int numeroGiocatori;
    private int durataMedia; // in minuti

    public GiocoDaTavolo(int id, String titolo, int annoPubblicazione, double prezzo, int numeroGiocatori, int durataMediaPartita) {
        super(id, titolo, annoPubblicazione, prezzo);
        if (numeroGiocatori < 2 || numeroGiocatori > 10)
            throw new IllegalArgumentException("Il numero di giocatori deve essere tra 2 e 10.");
        this.numeroGiocatori = numeroGiocatori;
        this.durataMedia = durataMediaPartita;
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(int numeroGiocatori) {
        this.numeroGiocatori = numeroGiocatori;
    }

    public void setDurataMedia(int durataMedia) {
        this.durataMedia = durataMedia;
    }


    public int getDurataMediaPartita() {
        return durataMedia;
    }

    @Override
    public String toString() {
        return super.toString() + ", Giocatori: " + numeroGiocatori + ", Durata media: " + durataMedia + " minuti";
    }
}
