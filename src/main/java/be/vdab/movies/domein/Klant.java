package be.vdab.movies.domein;

public class Klant {
    private final long klantId;
    private final String familieNaam;
    private final String voorNaam;
    private final String adres;
    private final long postCode;
    private final String plaatsNaam;

    public Klant(long klantId, String familieNaam, String voorNaam, String adres, long postCode, String plaatsNaam) {
        this.klantId = klantId;
        this.familieNaam = familieNaam;
        this.voorNaam = voorNaam;
        this.adres = adres;
        this.postCode = postCode;
        this.plaatsNaam = plaatsNaam;
    }

    public long getKlantId() {
        return klantId;
    }

    public String getFamilieNaam() {
        return familieNaam;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public String getAdres() {
        return adres;
    }

    public long getPostCode() {
        return postCode;
    }

    public String getPlaatsNaam() {
        return plaatsNaam;
    }
}
