import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeuxiemeInterface extends JFrame {
    private JComboBox<String> connectedBavardComboBox;
    private final JButton sendMessageButton;
    private final Batiment batiment;

    public DeuxiemeInterface(Batiment batiment) {
        setTitle("Envoyer un Message");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.batiment = batiment;

        // Initialisation des composants
        connectedBavardComboBox = new JComboBox<>();
        sendMessageButton = new JButton("Envoyer un Message");

        // Création d'un panneau principal avec une bordure vide et une disposition verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Création d'un panneau pour la JComboBox et le bouton d'envoi de message
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboPanel.add(new JLabel("Bavard connecté:"));
        comboPanel.add(connectedBavardComboBox);

        // Ajout des composants au panneau principal
        mainPanel.add(comboPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Espace vertical
        mainPanel.add(sendMessageButton);

        // Ajout du panneau principal à la fenêtre
        add(mainPanel);

        // Ajout des listeners aux boutons
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = (String) connectedBavardComboBox.getSelectedItem();
                if (nom != null) {
                    Bavard bavard = getBavardByName(nom);
                    if (bavard != null) {
                        new BavardInterface(bavard).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(DeuxiemeInterface.this, "Erreur : Bavard non trouvé");
                    }
                } else {
                    JOptionPane.showMessageDialog(DeuxiemeInterface.this, "Veuillez sélectionner un bavard connecté");
                }
            }
        });

        updateConnectedBavardComboBox();
    }

    private void updateConnectedBavardComboBox() {
        connectedBavardComboBox.removeAllItems();
        List<Bavard> connectedBavards = batiment.getListBavardsConnectes();
        if (connectedBavards.isEmpty()) {
            connectedBavardComboBox.addItem("Aucun bavard connecté");
        } else {
            for (Bavard b : connectedBavards) {
                connectedBavardComboBox.addItem(b.getNom());
            }
        }
    }

    private Bavard getBavardByName(String nom) {
        for (Bavard b : batiment.getListBavardsConnectes()) {
            if (b.getNom().equals(nom)) {
                return b;
            }
        }
        return null;
    }
}
