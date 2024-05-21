import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BavardInterface extends JFrame {

    private JTextField subjectField;
    private JTextArea bodyArea;
    private JButton sendButton;
    private Bavard bavard;
    private JTextArea receivedMessagesArea;

    public BavardInterface(Bavard bavard) {
        setTitle("Interface de " + bavard.getNom());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.bavard = bavard;

        // Ajout d'un simple label pour l'exemple
        JLabel label = new JLabel("Interface de " + bavard.getNom());
        add(label, BorderLayout.NORTH);

        // Set padding around the content
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel for subject
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));

        topPanel.add(new JLabel("Sujet:"));
        subjectField = new JTextField();
        topPanel.add(subjectField);

        // Panel for body
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());

        bodyPanel.add(new JLabel("Message:"), BorderLayout.NORTH);
        bodyArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        bodyPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel for send button
        JPanel buttonPanel = new JPanel();
        sendButton = new JButton("Envoyer");
        buttonPanel.add(sendButton);

        // Panel for received messages
        JPanel receivedMessagesPanel = new JPanel();
        receivedMessagesPanel.setLayout(new BorderLayout());

        receivedMessagesPanel.add(new JLabel("Messages reçus:"), BorderLayout.NORTH);
        receivedMessagesArea = new JTextArea();
        receivedMessagesArea.setEditable(false);
        JScrollPane receivedMessagesScrollPane = new JScrollPane(receivedMessagesArea);
        receivedMessagesPanel.add(receivedMessagesScrollPane, BorderLayout.CENTER);

        // Add all panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(receivedMessagesPanel, BorderLayout.EAST);

        // Action listener for the send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String subject = subjectField.getText();
        String body = bodyArea.getText();

        // Send the message
        bavard.sendMessage(subject, body);
    }

    // Method to update the received messages area
    public void receiveMessage(String subject, String body) {
        receivedMessagesArea.append("Sujet: " + subject + "\n" + "Message: " + body + "\n\n");
    }
}
