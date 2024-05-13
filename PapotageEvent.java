public class PapotageEvent {
    private final String sujet;
    private final String corps;
    private final String expediteur;

    public PapotageEvent(String sujet, String corps, String expediteur) {
        this.sujet = sujet;
        this.corps = corps;
        this.expediteur = expediteur;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCorps() {
        return corps;
    }

    public String getExpediteur() {
        return expediteur;
    }
}
