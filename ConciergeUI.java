import java.awt.*;
import javax.swing.*;

public class ConciergeUI extends JFrame {
    private final JTextArea messageArea;

    public ConciergeUI() {
        // Configure la fenêtre principale
        setTitle("Visualisation des Messages");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(50, 250);

        // Initialise les composants
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Ajoute les composants à la fenêtre
        JPanel panel = new JPanel();
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    public void addMessage(String message) {
        messageArea.append(message + "\n");
    }

    public void addConnecteBavavard(String message){
        messageArea.append(message + "\n");
    }
}
