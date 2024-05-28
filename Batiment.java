import java.util.ArrayList;
import java.util.List;

public class Batiment {
    private final Concierge concierge;
    private final List<Bavard> listBavardsCrees;
    private final List<Bavard> listBavardsConnectes;

    // Constructeur de la classe Batiment
    public Batiment(ConciergeUI conciergeUI) {
        this.concierge = new Concierge(conciergeUI); // Initialiser le concierge avec son interface utilisateur
        this.listBavardsCrees = new ArrayList<>(); // Liste pour les bavards créés
        this.listBavardsConnectes = new ArrayList<>(); // Liste pour les bavards connectés
    }

    // Méthode pour créer un nouveau bavard
    public Bavard creerBavard(String nom) {
        Bavard bavard = new Bavard(nom);
        listBavardsCrees.add(bavard);
        return bavard;
    }

    // Méthode pour connecter un bavard
    public void connecterBavard(Bavard bavard) {
        if (!listBavardsConnectes.contains(bavard)) {
            listBavardsConnectes.add(bavard);
            concierge.addBavard(bavard);
            bavard.setConcierge(concierge); // Associer le concierge au bavard
            System.out.println("\n" + bavard.getNom() + " has successfully connected");
        }
    }

    // Méthode pour déconnecter un bavard
    public void deconnecterBavard(Bavard bavard) {
        if (listBavardsConnectes.contains(bavard)) {
            listBavardsConnectes.remove(bavard);
            concierge.removeBavard(bavard);
            bavard.setConcierge(null); // Désassocier le concierge du bavard
            System.out.println("\n" + bavard.getNom() + " has successfully deconnected");
        }
    }

    // Méthode pour ajouter un bavard créé à la liste
    public void ajouterBavardCree(Bavard bavard) {
        if (!listBavardsCrees.contains(bavard)) {
            listBavardsCrees.add(bavard);
        }
    }

    // Méthode pour afficher tous les bavards créés
    public void afficherBavardsCrees() {
        System.out.println("\nBavards créés :");
        for (Bavard b : listBavardsCrees) {
            System.out.println("- " + b.getNom());
        }
    }

    // Méthode pour afficher tous les bavards connectés
    public void afficherBavardsConnectes() {
        System.out.println("\nBavards connectés :");
        for (Bavard b : listBavardsConnectes) {
            System.out.println("- " + b.getNom());
        }
    }

    // Méthode pour afficher tous les bavards non connectés
    public void afficherBavardsNonConnectes() {
        System.out.println("\nBavards non connectés :");
        for (Bavard b : listBavardsCrees) {
            if (!listBavardsConnectes.contains(b)) {
                System.out.println("- " + b.getNom());
            }
        }
    }

    // Méthode pour obtenir une liste des noms des bavards non connectés
    public List<String> afficherBavardsNonConnectesrList() {
        List<String> nonConnectes = new ArrayList<>();
        for (Bavard b : listBavardsCrees) {
            if (!listBavardsConnectes.contains(b)) {
                nonConnectes.add(b.getNom());
            }
        }
        return nonConnectes;
    }

    // Accesseurs pour obtenir la liste des bavards créés
    public List<Bavard> getListBavardsCrees() {
        return listBavardsCrees;
    }

    // Accesseurs pour obtenir la liste des bavards connectés
    public List<Bavard> getListBavardsConnectes() {
        return listBavardsConnectes;
    }
}
