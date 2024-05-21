import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BavardGUI extends JFrame {
    private JTextField nomField;
    private JButton creerBavardButton;
    private JButton connecterBavardButton;
    private JComboBox<String> bavardComboBox;

    private Batiment batiment;
    private List<Bavard> bavards;

    public BavardGUI() {
        setTitle("Gestion des Bavards");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(340, 400);
        setLocationRelativeTo(null);

        // Initialisation des composants
        nomField = new JTextField(20);
        creerBavardButton = new JButton("Créer Bavard");
        connecterBavardButton = new JButton("Connecter Bavard");
        bavardComboBox = new JComboBox<>();

        // Initialisation du batiment et de la liste des bavards
        batiment = new Batiment();
        bavards = new ArrayList<>();

        // Création d'un panneau principal avec une bordure vide et une disposition verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Création d'un panneau pour le champ de texte et le bouton de création
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Nom du Bavard:"));
        inputPanel.add(nomField);
        inputPanel.add(creerBavardButton);

        // Ajustement de la taille de la JComboBox et du JTextField
        bavardComboBox.setPreferredSize(new Dimension(250, 30));
        nomField.setPreferredSize(new Dimension(500, 30));

        // Création d'un panneau pour la JComboBox et le bouton de connexion
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboPanel.add(new JLabel("Bavard à connecter:"));
        comboPanel.add(bavardComboBox);
        comboPanel.add(connecterBavardButton);

        // Ajout des panneaux au panneau principal
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Espace vertical
        mainPanel.add(comboPanel);

        // Ajout du panneau principal à la fenêtre
        add(mainPanel);

        // Ajout des listeners aux boutons
        creerBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                if (!nom.isEmpty()) {
                    Bavard bavard = batiment.creerBavard(nom);
                    updateBavardComboBox(); // Mettre à jour la JComboBox
                    JOptionPane.showMessageDialog(BavardGUI.this, "Bavard " + nom + " créé avec succès!");
                    batiment.afficherBavardsCrees();
                } else {
                    JOptionPane.showMessageDialog(BavardGUI.this, "Veuillez saisir un nom pour le Bavard!");
                }
            }
        });

        connecterBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = (String) bavardComboBox.getSelectedItem(); // Récupérer le nom du bavard sélectionné dans la JComboBox
                if (nom != null) {
                    // Récupérer le bavard correspondant au nom sélectionné
                    for (Bavard bavard : batiment.getListBavardsCrees()) {
                        if (bavard.getNom().equals(nom)) {
                            batiment.connecterBavard(bavard); // Connecter le bavard sélectionné
                            updateBavardComboBox(); // Mettre à jour la JComboBox
                            JOptionPane.showMessageDialog(BavardGUI.this, "Bavard " + nom + " connecté avec succès!");
                            batiment.afficherBavardsConnectes();
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(BavardGUI.this, "Veuillez sélectionner un bavard à connecter!");
                }
            }
        });

        // Ajout d'un bouton pour ouvrir la deuxième interface
        JButton ouvrirDeuxiemeInterfaceButton = new JButton("Envoyer un message");
        mainPanel.add(ouvrirDeuxiemeInterfaceButton);

        ouvrirDeuxiemeInterfaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeuxiemeInterface(batiment).setVisible(true);
            }
        });
    }

    private void updateBavardComboBox() {
        bavardComboBox.removeAllItems();
        for (Bavard b : batiment.getListBavardsCrees()) {
            if (!batiment.getListBavardsConnectes().contains(b)) {
                bavardComboBox.addItem(b.getNom());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BavardGUI().setVisible(true);
            }
        });
    }
}

class DeuxiemeInterface extends JFrame {
    private JComboBox<String> connectedBavardComboBox;
    private JButton sendMessageButton;
    private Batiment batiment;

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
                    JOptionPane.showMessageDialog(DeuxiemeInterface.this, "Message envoyé avec le bavard " + nom + "!");
                } else {
                    JOptionPane.showMessageDialog(DeuxiemeInterface.this, "Veuillez sélectionner un bavard connecté!");
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
}
