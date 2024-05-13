import java.util.ArrayList;
import java.util.List;

// Classe Bavard qui envoie et reçoit des messages de papotage
public class Bavard implements PapotageListener {
    private final String nom;
    private final List<PapotageListener> listeners;

    // Constructeur
    public Bavard(String nom) {
        this.nom = nom;
        this.listeners = new ArrayList<>();
    }

    // Méthode pour envoyer un message de papotage à tous les auditeurs
    public void sendMessage(String sujet, String corps) {
        PapotageEvent event = new PapotageEvent(sujet, corps);
        for (PapotageListener listener : listeners) {
            listener.receivePapotage(event);
        }
    }

    // Méthode appelée lorsqu'un papotage est reçu
    @Override
    public void receivePapotage(PapotageEvent event) {
        // Traitement du papotage reçu (dans cet exemple, simplement l'imprimer)
        System.out.println("Bavard " + nom + " a reçu un papotage: " + event.getSujet() + " - " + event.getCorps());
    }

    // Méthode pour ajouter un auditeur de papotage
    public void addPapotageListener(PapotageListener listener) {
        listeners.add(listener);
    }

    // Méthode pour supprimer un auditeur de papotage
    public void removePapotageListener(PapotageListener listener) {
        listeners.remove(listener);
    }
}