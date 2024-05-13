import java.util.ArrayList;
import java.util.List;

public class Concierge implements PapotageListener {
    private final List<PapotageListener> bavardsConnectes;

    public Concierge() {
        this.bavardsConnectes = new ArrayList<>();
    }

    @Override
    public void onPapotageReceived(PapotageEvent event) {
        // Transmettre le message à tous les bavards connectés
        for (PapotageListener bavard : bavardsConnectes) {
            bavard.onPapotageReceived(event);
        }
    }

    public void addBavard(PapotageListener bavard) {
        bavardsConnectes.add(bavard);
    }

    public void removeBavard(PapotageListener bavard) {
        bavardsConnectes.remove(bavard);
    }
}
