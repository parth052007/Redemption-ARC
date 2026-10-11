
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaymentGUI gui = new PaymentGUI();
            gui.setVisible(true);
        });
    }
}
