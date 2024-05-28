import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BavardGUI extends JFrame {
    private final JTextField nomField; // Champ de texte pour entrer le nom du Bavard
    private final JButton creerBavardButton; // Bouton pour créer un Bavard
    private final JButton connecterBavardButton; // Bouton pour connecter un Bavard
    private final JButton deconnecterBavardButton; // Bouton pour déconnecter un Bavard
    private final JComboBox<String> bavardComboBox; // ComboBox pour afficher les Bavards créés mais non connectés
    private final JComboBox<String> bavardConnecteComboBox; // ComboBox pour afficher les Bavards connectés

    private final Batiment batiment; // Instance de la classe Batiment

    public BavardGUI(Batiment batiment) {
        this.batiment = batiment; // Utiliser l'instance de Batiment passée en paramètre

        setTitle("Gestion des Bavards");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(340, 450);
        setLocationRelativeTo(null);

        // Initialisation des composants
        nomField = new JTextField(20);
        creerBavardButton = new JButton("Créer Bavard");
        connecterBavardButton = new JButton("Connecter Bavard");
        deconnecterBavardButton = new JButton("Déconnecter Bavard");
        bavardComboBox = new JComboBox<>();
        bavardConnecteComboBox = new JComboBox<>();

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
        bavardConnecteComboBox.setPreferredSize(new Dimension(250, 30));
        nomField.setPreferredSize(new Dimension(500, 30));

        // Création d'un panneau pour la JComboBox et le bouton de connexion
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboPanel.add(new JLabel("Bavard à connecter:"));
        comboPanel.add(bavardComboBox);
        comboPanel.add(connecterBavardButton);

        // Création d'un panneau pour la JComboBox des bavards connectés et le bouton de déconnexion
        JPanel connectedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        connectedPanel.add(new JLabel("Bavard connecté:"));
        connectedPanel.add(bavardConnecteComboBox);
        connectedPanel.add(deconnecterBavardButton);

        // Ajout des panneaux au panneau principal
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Espace vertical
        mainPanel.add(comboPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Espace vertical
        mainPanel.add(connectedPanel);

        // Ajout du panneau principal à la fenêtre
        add(mainPanel);

        // Gestion du bouton de création d'un Bavard
        creerBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText(); // Récupérer le nom entré
                if (!nom.isEmpty()) {
                    boolean exists = false;
                    for (Bavard bavard : batiment.getListBavardsCrees()) {
                        if (bavard.getNom().equalsIgnoreCase(nom)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        Bavard bavard = batiment.creerBavard(nom); // Créer le Bavard
                        updateBavardComboBox(); // Mettre à jour les JComboBox
                        JOptionPane.showMessageDialog(BavardGUI.this, "Bavard " + nom + " créé avec succès!");
                        batiment.afficherBavardsCrees(); // Afficher la liste des Bavards créés dans la console
                    } else {
                        JOptionPane.showMessageDialog(BavardGUI.this, "Un Bavard avec ce nom existe déjà!");
                    }
                } else {
                    JOptionPane.showMessageDialog(BavardGUI.this, "Veuillez saisir un nom pour le Bavard!");
                }
            }
        });

        // Gestion du bouton de connexion d'un Bavard
        connecterBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = (String) bavardComboBox.getSelectedItem(); // Récupérer le nom du bavard sélectionné dans la JComboBox
                if (nom != null) {
                    // Récupérer le bavard correspondant au nom sélectionné
                    for (Bavard bavard : batiment.getListBavardsCrees()) {
                        if (bavard.getNom().equals(nom)) {
                            batiment.connecterBavard(bavard); // Connecter le bavard sélectionné
                            updateBavardComboBox(); // Mettre à jour les JComboBox
                            JOptionPane.showMessageDialog(BavardGUI.this, "Bavard " + nom + " connecté avec succès!");
                            batiment.afficherBavardsConnectes(); // Afficher la liste des Bavards connectés dans la console

                            BavardInterface bavardInterface = new BavardInterface(bavard); // Créer l'interface du Bavard
                            bavard.setBavardInterface(bavardInterface); // Associer l'interface au Bavard
                            bavardInterface.setVisible(true); // Afficher l'interface du Bavard
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(BavardGUI.this, "Veuillez sélectionner un bavard à connecter!");
                }
            }
        });

        // Gestion du bouton de déconnexion d'un Bavard
        deconnecterBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = (String) bavardConnecteComboBox.getSelectedItem(); // Récupérer le nom du bavard connecté sélectionné
                if (nom != null) {
                    // Récupérer le bavard correspondant au nom sélectionné
                    for (Bavard bavard : batiment.getListBavardsConnectes()) {
                        if (bavard.getNom().equals(nom)) {
                            batiment.deconnecterBavard(bavard); // Déconnecter le bavard sélectionné
                            BavardInterface bavardInterface = bavard.getBavardInterface();
                            updateBavardComboBox(); // Mettre à jour les JComboBox
                            JOptionPane.showMessageDialog(BavardGUI.this, "Bavard " + nom + " déconnecté avec succès!");
                            batiment.afficherBavardsConnectes(); // Afficher la liste des Bavards connectés dans la console
                            if (bavardInterface != null) {
                                bavardInterface.dispose(); // Fermer la fenêtre de l'interface du Bavard
                            }
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(BavardGUI.this, "Veuillez sélectionner un bavard à déconnecter!");
                }
            }
        });
    }

    // Mettre à jour les JComboBox avec les Bavards créés et connectés
    private void updateBavardComboBox() {
        bavardComboBox.removeAllItems();
        bavardConnecteComboBox.removeAllItems();

        for (Bavard b : batiment.getListBavardsCrees()) {
            if (!batiment.getListBavardsConnectes().contains(b)) {
                bavardComboBox.addItem(b.getNom());
            }
        }

        for (Bavard b : batiment.getListBavardsConnectes()) {
            bavardConnecteComboBox.addItem(b.getNom());
        }
    }
}
