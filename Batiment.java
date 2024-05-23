import java.util.ArrayList;
import java.util.List;

public class Batiment {
    private final Concierge concierge;
    private final List<Bavard> listBavardsCrees;
    private final List<Bavard> listBavardsConnectes;

    public Batiment(ConciergeUI conciergeUI) {
        this.concierge = new Concierge(conciergeUI);
        this.listBavardsCrees = new ArrayList<>();
        this.listBavardsConnectes = new ArrayList<>();
    }

    public Bavard creerBavard(String nom) {
        Bavard bavard = new Bavard(nom);
        listBavardsCrees.add(bavard);
        return bavard;
    }

    public void connecterBavard(Bavard bavard) {
        if (!listBavardsConnectes.contains(bavard)) {
            listBavardsConnectes.add(bavard);
            concierge.addBavard(bavard);
            bavard.setConcierge(concierge); // Set the concierge for the bavard
            System.out.println("\n" + bavard.getNom() + " has successfully connected");
        }
    }

    public void deconnecterBavard(Bavard bavard) {
        if (listBavardsConnectes.contains(bavard)) {
            listBavardsConnectes.remove(bavard);
            concierge.removeBavard(bavard);
            bavard.setConcierge(null); // Set the concierge for the bavard
            System.out.println("\n" + bavard.getNom() + " has successfully deconnected");
        }
    }

    public void ajouterBavardCree(Bavard bavard) {
        if (!listBavardsCrees.contains(bavard)) {
            listBavardsCrees.add(bavard);
        }
    }

    public void afficherBavardsCrees() {
        System.out.println("\nBavards créés :");
        for (Bavard b : listBavardsCrees) {
            System.out.println("- " + b.getNom());
        }
    }

    public void afficherBavardsConnectes() {
        System.out.println("\nBavards connectés :");
        for (Bavard b : listBavardsConnectes) {
            System.out.println("- " + b.getNom());
        }
    }

    public void afficherBavardsNonConnectes() {
        System.out.println("\nBavards non connectés :");
        for (Bavard b : listBavardsCrees) {
            if (!listBavardsConnectes.contains(b)) {
                System.out.println("- " + b.getNom());
            }
        }
    }

    public List<String> afficherBavardsNonConnectesrList() {
        List<String> nonConnectes = new ArrayList<>();
        for (Bavard b : listBavardsCrees) {
            if (!listBavardsConnectes.contains(b)) {
                nonConnectes.add(b.getNom());
            }
        }
        return nonConnectes;
    }

    public List<Bavard> getListBavardsCrees() {
        return listBavardsCrees;
    }

    public List<Bavard> getListBavardsConnectes() {
        return listBavardsConnectes;
    }
}
