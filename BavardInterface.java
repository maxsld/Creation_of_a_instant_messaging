import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BavardInterface extends JFrame {
    private final JTextField subjectField;
    private final JTextArea bodyArea;
    private final JButton sendButton;
    private final Bavard bavard;
    private final DefaultListModel<String> listModel;
    private final JList<String> receivedMessagesList;

    // Constructeur de l'interface BavardInterface
    public BavardInterface(Bavard bavard) {
        // Configuration de la fenêtre
        setTitle("Interface de " + bavard.getNom());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.bavard = bavard;
        this.bavard.setBavardInterface(this); // Associer cette interface au bavard

        // Ajouter un label en haut de la fenêtre
        JLabel label = new JLabel("Interface de " + bavard.getNom());
        add(label, BorderLayout.NORTH);

        // Définir une bordure pour le contenu de la fenêtre
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Créer le panneau supérieur avec les champs de sujet
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("Sujet:"));
        subjectField = new JTextField();
        topPanel.add(subjectField);

        // Créer le panneau pour le corps du message
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(new JLabel("Message:"), BorderLayout.NORTH);
        bodyArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        bodyPanel.add(scrollPane, BorderLayout.CENTER);

        // Créer le panneau des boutons
        JPanel buttonPanel = new JPanel();
        sendButton = new JButton("Envoyer");
        buttonPanel.add(sendButton);

        // Créer le panneau pour afficher les messages reçus
        JPanel receivedMessagesPanel = new JPanel();
        receivedMessagesPanel.setLayout(new BorderLayout());
        receivedMessagesPanel.add(new JLabel("Messages reçus:"), BorderLayout.NORTH);
        listModel = new DefaultListModel<>();
        receivedMessagesList = new JList<>(listModel);
        receivedMessagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane receivedMessagesScrollPane = new JScrollPane(receivedMessagesList);
        receivedMessagesPanel.add(receivedMessagesScrollPane, BorderLayout.CENTER);

        // Ajouter les différents panneaux à la fenêtre principale
        add(topPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(receivedMessagesPanel, BorderLayout.EAST);

        // Ajouter un ActionListener pour le bouton d'envoi
        sendButton.addActionListener((ActionEvent e) -> {
            sendMessage();
        });

        // Ajouter un MouseListener pour ouvrir un message dans une nouvelle fenêtre
        receivedMessagesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double clic
                    int index = receivedMessagesList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String message = listModel.get(index);
                        openMessageInNewWindow(message);
                    }
                }
            }
        });
    }

    // Méthode pour envoyer un message
    private void sendMessage() {
        String subject = subjectField.getText();
        String body = bodyArea.getText();
        if (subject.isEmpty() && body.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir le sujet et le message.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (subject.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir le sujet.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            bavard.sendMessage(subject, body);
        }
    }

    // Méthode pour ajouter un message à la liste des messages reçus
    public void addMessage(String message) {
        listModel.addElement(message);
    }

    // Méthode pour ouvrir un message dans une nouvelle fenêtre
    private void openMessageInNewWindow(String message) {
        JFrame messageFrame = new JFrame("Message reçu");
        messageFrame.setSize(300, 200);
        messageFrame.setLocationRelativeTo(this);
        JTextArea messageArea = new JTextArea(message);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        messageFrame.add(scrollPane);
        messageFrame.setVisible(true);
    }

    }

