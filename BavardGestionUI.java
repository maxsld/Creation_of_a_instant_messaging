import java.util.ArrayList;
import java.util.List;

public class BavardGestionUI implements PapotageListener {
    private final List<PapotageListener> bavardsConnectes;
    private final BavardInterface bavardInterface;

    public BavardGestionUI(BavardInterface bavardInterface) {
        this.bavardsConnectes = new ArrayList<>();
        this.bavardInterface = bavardInterface;
    }

    @Override
    public void onPapotageReceived(PapotageEvent event) {
        // Transmettre le message à tous les bavards connectés
        for (PapotageListener bavard : bavardsConnectes) {
            bavard.onPapotageReceived(event);
        }
        // Ajouter le message à l'interface utilisateur
        bavardInterface.addMessage("Sujet: " + event.getSujet() + "\nMessage: " + event.getCorps() + "\nDe: " + event.getExpediteur() + "\n");
    }
}
