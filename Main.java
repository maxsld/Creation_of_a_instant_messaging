public class Main {
    public static void main(String[] args) {
        // Création du bâtiment
        Batiment batiment = new Batiment();

        // Création du concierge
        Concierge concierge = new Concierge();

        // Ajout des bavards au bâtiment
        Bavard bavard1 = batiment.creerBavard("Alice");
        Bavard bavard2 = batiment.creerBavard("Bob");
        Bavard bavard3 = batiment.creerBavard("Charlie");

        // Connexion des bavards au concierge
        batiment.connecterBavard(bavard1);
        batiment.connecterBavard(bavard3);

        System.out.println("");

        // Envoi d'un message par Alice
        bavard1.sendMessage("Hello Word", "Ceci est un test");
        bavard3.sendMessage("sujet", "corps");


        // Afficher la liste des bavards créés
        batiment.afficherBavardsCrees();

        // Afficher les bavards connectés
        batiment.afficherBavardsConnectes();

        // Afficher les bavards non connectés
        batiment.afficherBavardsNonConnectes();

        batiment.afficherBavardsNonConnectesrList();

        BavardGUI bavardGUI = new BavardGUI();

        // Rendre l'interface visible
        bavardGUI.setVisible(true);
    }
}
