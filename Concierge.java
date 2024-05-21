import java.util.ArrayList;
import java.util.List;

public class Concierge implements PapotageListener {
    private final List<PapotageListener> bavardsConnectes;
    private final ConciergeUI conciergeUI;

    public Concierge(ConciergeUI conciergeUI) {
        this.bavardsConnectes = new ArrayList<>();
        this.conciergeUI = conciergeUI;
    }

    @Override
    public void onPapotageReceived(PapotageEvent event) {
        // Transmettre le message à tous les bavards connectés
        for (PapotageListener bavard : bavardsConnectes) {
            bavard.onPapotageReceived(event);
        }
        // Ajouter le message à l'interface utilisateur
        conciergeUI.addMessage("Sujet: " + event.getSujet() + "\nMessage: " + event.getCorps() + "\nDe: " + event.getExpediteur() + "\n");
    }

    public void addBavard(PapotageListener bavard) {
        // Vérifier si le bavard n'est pas déjà connecté avant de l'ajouter
        if (!bavardsConnectes.contains(bavard)) {
            bavardsConnectes.add(bavard);
        }
    }

    public void removeBavard(PapotageListener bavard) {
        bavardsConnectes.remove(bavard);
    }
}
