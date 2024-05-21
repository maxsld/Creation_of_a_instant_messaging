import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class BavardInterface extends JFrame {

    private JTextField subjectField;
    private JTextArea bodyArea;
    private JButton sendButton;

    public BavardInterface(String bavardName) {
        setTitle("Envoyer un message à " + bavardName);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set padding around the content
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel for subject
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));

        topPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        topPanel.add(subjectField);

        // Panel for body
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());

        bodyPanel.add(new JLabel("Message Body:"), BorderLayout.NORTH);
        bodyArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        bodyPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel for send button
        JPanel buttonPanel = new JPanel();
        sendButton = new JButton("Send");
        buttonPanel.add(sendButton);

        // Add all panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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

        // For simplicity, we'll just print the message to the console
        System.out.println("Subject: " + subject);
        System.out.println("Message Body: " + body);

        // You can add more functionality here, like sending an email or saving the message
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BavardInterface("Bavard").setVisible(true);
            }
        });
    }
}
