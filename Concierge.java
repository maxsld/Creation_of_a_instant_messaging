import java.util.ArrayList;
import java.util.List;

public class Concierge implements PapotageListener {
    private final List<Bavard> bavardsConnectes;
    private final ConciergeUI conciergeUI;

    public Concierge(ConciergeUI conciergeUI) {
        this.bavardsConnectes = new ArrayList<>();
        this.conciergeUI = conciergeUI;
    }

    @Override
    public void onPapotageReceived(PapotageEvent event) {
        // Ajouter le message à l'interface utilisateur
        conciergeUI.addMessage("Sujet: " + event.getSujet() + "\nMessage: " + event.getCorps() + "\nDe: " + event.getExpediteur() + "\n");
        
        // Transmettre le message à tous les bavards connectés
        for (Bavard bavard : bavardsConnectes) {
            bavard.onPapotageReceived(event);
        }
    }

    public void addBavard(Bavard bavard) {
        // Vérifier si le bavard n'est pas déjà connecté avant de l'ajouter
        if (!bavardsConnectes.contains(bavard)) {
            bavardsConnectes.add(bavard);
            bavard.setConcierge(this); // Définir le concierge pour le bavard
        }
    }

    public void connectBavard(Bavard bavard){
        conciergeUI.addConnecteBavavard("Bavard : " + bavard.getNom() + "s'est connecté !");
    }

    public void removeBavard(Bavard bavard) {
        bavardsConnectes.remove(bavard);
        bavard.setConcierge(null); // Retirer le concierge du bavard
    }
}
