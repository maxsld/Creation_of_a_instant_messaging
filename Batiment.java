import java.util.ArrayList;
import java.util.List;

public class Batiment {
    private final Concierge concierge;
    private final List<Bavard> listBavardsCrees;
    private final List<Bavard> listBavardsConnectes;

    public Batiment() {
        this.concierge = new Concierge();
        this.listBavardsCrees = new ArrayList<>();
        this.listBavardsConnectes = new ArrayList<>();
    }

    public Bavard creerBavard(String nom) {
        Bavard bavard = new Bavard(nom);
        listBavardsCrees.add(bavard);
        return bavard;
    }

    public void connecterBavard(Bavard bavard) {
        listBavardsConnectes.add(bavard);
        concierge.addBavard(bavard);
        bavard.addPapotageListener(concierge);
        System.out.println(bavard.getNom() + " has successfully connected");
    }

    public void afficherBavardsCrees() {
        System.out.println("\nBavards créés :");
        for (Bavard b : listBavardsCrees) {
            System.out.println("- " + b.getNom());
        }
    }

    public void afficherBavardsConnectes(){
        System.out.println("\nBavards connectés :");
        for (Bavard b : listBavardsConnectes) {
            System.out.println("- " + b.getNom());
        }
    }

    public void afficherBavardsNonConnectes(){
        System.out.println("\n Bavards non connectés :");
        for (Bavard b : listBavardsCrees) {
            if (!listBavardsConnectes.contains(b)) {
                System.out.println("- " + b.getNom());
            }
        }
    }

    public List<Bavard> getListBavardsCrees() {
        return listBavardsCrees;
    }

    public List<Bavard> getListBavardsConnectes() {
        return listBavardsConnectes;
    }
}