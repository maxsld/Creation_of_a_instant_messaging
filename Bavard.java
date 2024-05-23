import java.util.ArrayList;
import java.util.List;

public class Bavard implements PapotageListener {
    private final String nom;
    private final List<PapotageListener> listeners;
    private Concierge concierge;
    private BavardInterface bavardInterface;

    public String getNom() {
        return nom;
    }

    // Constructeur
    public Bavard(String nom) {
        this.nom = nom;
        this.listeners = new ArrayList<>();
    }

    // Méthode pour envoyer un message de papotage à tous les auditeurs
    public void sendMessage(String sujet, String corps) {
        PapotageEvent event = new PapotageEvent(sujet, corps, this.nom); // Inclure le nom du bavard comme expéditeur
        if (concierge != null) {
            concierge.onPapotageReceived(event);
        }
    }

    // Méthode appelée lorsqu'un papotage est reçu
    @Override
    public void onPapotageReceived(PapotageEvent event) {
        // Vérifier si le message a été envoyé par ce bavard
        if (!event.getExpediteur().equals(nom)) {
            // Mise à jour de l'interface avec le nouveau message
            if (bavardInterface != null) {
                bavardInterface.addMessage("Sujet: " + event.getSujet() + "\nMessage: " + event.getCorps() + "\nDe: " + event.getExpediteur() + "\n");
            }
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

    // Méthode pour définir le concierge
    public void setConcierge(Concierge concierge) {
        this.concierge = concierge;
    }

    // Méthode pour définir l'interface utilisateur du bavard
    public void setBavardInterface(BavardInterface bavardInterface) {
        this.bavardInterface = bavardInterface;
    }
}
