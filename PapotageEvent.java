public class PapotageEvent {
    private final String sujet;
    private final String corps;

    public PapotageEvent(String sujet, String corps) {
        this.sujet = sujet;
        this.corps = corps;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCorps() {
        return corps;
    }
}
