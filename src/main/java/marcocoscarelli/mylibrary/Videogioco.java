package marcocoscarelli.mylibrary;

public class Videogioco extends Gioco {
    private String piattaforma;
    private int durataGioco;
    private Genere genere;

    public Videogioco(int id, String titolo, int annoPubblicazione, double prezzo, String piattaforma, int durataGioco, Genere genere) {
        super(id, titolo, annoPubblicazione, prezzo);
        this.piattaforma = piattaforma;
        this.durataGioco = durataGioco;
        this.genere = genere;
    }

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public int getDurataGioco() {
        return durataGioco;
    }

    // Cambia il nome di questo metodo per essere coerente con il campo 'durataGioco'
    public void setDurataGioco(int durataGioco) {
        this.durataGioco = durataGioco;
    }

    public Genere getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return super.toString() + ", Piattaforma: " + piattaforma + ", Durata: " + durataGioco + " ore, Genere: " + genere;
    }

    public enum Genere {AZIONE, AVVENTURA, RPG, SPORTIVO, SIMULAZIONE}
}

