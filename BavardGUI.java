import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Utilisation du look and feel Nimbus pour un aspect moderne
        
        // Initialisation des composants
        nomField = new JTextField(20);
        creerBavardButton = new JButton("Créer Bavard");
        connecterBavardButton = new JButton("Connecter Bavard");
        bavardComboBox = new JComboBox<>();

        // Initialisation du batiment et de la liste des bavards
        batiment = new Batiment();
        bavards = new ArrayList<>();

        // Création d'un panneau principal avec une bordure vide
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new GridLayout(3, 1, 0, 10)); // Utilisation d'une disposition en grille

        // Création d'un panneau pour le champ de texte et le bouton de création
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(new JLabel("Nom du Bavard:"));
        inputPanel.add(nomField);
        inputPanel.add(creerBavardButton);

        // Création d'un panneau pour la JComboBox et le bouton de connexion
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboPanel.add(new JLabel("Bavard à connecter:"));
        comboPanel.add(bavardComboBox);
        comboPanel.add(connecterBavardButton);

        // Ajout des panneaux au panneau principal
        mainPanel.add(inputPanel);
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
                    bavards.add(bavard); // Ajouter le bavard à la liste des bavards
                    bavardComboBox.addItem(nom); // Ajouter le nom du bavard à la JComboBox
                    JOptionPane.showMessageDialog(BavardGUI.this, "Bavard " + nom + " créé avec succès!");
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
                    for (Bavard bavard : bavards) {
                        if (bavard.getNom().equals(nom)) {
                            batiment.connecterBavard(bavard); // Connecter le bavard sélectionné
                            JOptionPane.showMessageDialog(BavardGUI.this, "Bavard " + nom + " connecté avec succès!");
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(BavardGUI.this, "Veuillez sélectionner un bavard à connecter!");
                }
            }
        });
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
