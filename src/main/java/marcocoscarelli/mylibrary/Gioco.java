package marcocoscarelli.mylibrary;

public abstract class Gioco {
    protected int id;
    protected String titolo;
    protected int annoPubblicazione;
    protected double prezzo;

    public Gioco(int id, String titolo, int annoPubblicazione, double prezzo) {
        if (prezzo <= 0) throw new IllegalArgumentException("Il prezzo deve essere positivo.");
        this.id = id;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0) throw new IllegalArgumentException("Il prezzo deve essere positivo.");
        this.prezzo = prezzo; // Imposta il nuovo prezzo
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Titolo: " + titolo + ", Anno: " + annoPubblicazione + ", Prezzo: " + prezzo;
    }
}
