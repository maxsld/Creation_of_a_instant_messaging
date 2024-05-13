import java.util.ArrayList;
import java.util.List;

public class Bavard implements PapotageListener {
    private final String nom;
    private final List<PapotageListener> listeners;

    public Bavard(String nom) {
        this.nom = nom;
        this.listeners = new ArrayList<>();
    }

    public void sendMessage(String sujet, String corps) {
        PapotageEvent event = new PapotageEvent(sujet, corps);
        for (PapotageListener listener : listeners) {
            listener.receivePapotage(event);
        }
    }

    @Override
    public void receivePapotage(PapotageEvent event) {
        // Traitement du papotage reçu
        System.out.println("Bavard " + nom + " a reçu un papotage: " + event.getSujet() + " - " + event.getCorps());
    }

    public void addPapotageListener(PapotageListener listener) {
        listeners.add(listener);
    }

    public void removePapotageListener(PapotageListener listener) {
        listeners.remove(listener);
    }
}
