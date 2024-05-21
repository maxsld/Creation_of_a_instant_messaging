import java.util.ArrayList;
import java.util.List;

// Classe Bavard qui envoie et reçoit des messages de papotage
public class Bavard implements PapotageListener {
    private final String nom;
    private final List<PapotageListener> listeners;
    private final BavardInterface bavardInterface;

    public String getNom() {
        return nom;
    }

    // Constructeur
    public Bavard(String nom) {
        this.nom = nom;
        this.listeners = new ArrayList<>();
        this.bavardInterface = new BavardInterface(this);
    }

    // Méthode pour envoyer un message de papotage à tous les auditeurs
    public void sendMessage(String sujet, String corps) {
        PapotageEvent event = new PapotageEvent(sujet, corps, this.nom); // Inclure le nom du bavard comme expéditeur
        for (PapotageListener listener : listeners) {
            listener.onPapotageReceived(event);
        }
    }

    // Méthode appelée lorsqu'un papotage est reçu
    @Override
    public void onPapotageReceived(PapotageEvent event) {
        // Vérifier si le message a été envoyé par ce bavard
        if (!event.getExpediteur().equals(nom)) {
            // Mise à jour de l'interface avec le nouveau message
            bavardInterface.receiveMessage("Bavard " + nom + " a reçu un papotage : " + "'" + event.getSujet() + " - " + event.getCorps() + "'" + " de la part de : " + event.getExpediteur());
            System.out.println("Bavard " + nom + " a reçu un papotage : " + "'" + event.getSujet() + " - " + event.getCorps() + "'" + " de la part de : " + event.getExpediteur());
        }
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
