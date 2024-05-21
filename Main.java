import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialiser l'interface du Concierge
                ConciergeUI conciergeUI = new ConciergeUI();
                
                // Créer une instance de Batiment avec l'interface du Concierge
                Batiment batiment = new Batiment(conciergeUI);
                
                // Initialiser l'interface de gestion des Bavards avec l'instance de Batiment
                BavardGUI bavardGUI = new BavardGUI(batiment);
                
                // // Afficher les interfaces
                // bavardGUI.setVisible(true);
                // conciergeUI.setVisible(true);

                Bavard bavard = new Bavard("H");

                BavardInterface test = new BavardInterface(bavard);
                test.setVisible(true);
            }
        });
    }
}
