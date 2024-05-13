public class Batiment {
    private final Concierge concierge;

    public Batiment() {
        this.concierge = new Concierge();
    }

    public Bavard creerBavard(String nom) {
        return new Bavard(nom);
    }

    public void connecterBavard(Bavard bavard) {
        concierge.addBavard(bavard);
        bavard.addPapotageListener(concierge);
    }
}
