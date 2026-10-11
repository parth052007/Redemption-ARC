
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentGUI extends JFrame {

    private final Color BG = new Color(13, 18, 35);
    private final Color PANEL = new Color(24, 31, 53);
    private final Color BLUE = new Color(65, 105, 255);
    private final Color PURPLE = new Color(137, 83, 255);
    private final Color WHITE = new Color(240, 244, 255);
    private final Color MUTED = new Color(155, 166, 193);
    private final Color GREEN = new Color(42, 211, 151);

    private JTextField nameField, amountField;
    private JComboBox<String> methodBox;
    private JTextArea resultArea;
    private JLabel summaryAmount, statusLabel;

    public PaymentGUI() {

        setTitle("PayFlow | Payment Management System");
        setSize(1000, 720);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(22, 0));
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(24, 24, 24, 24));
        setContentPane(root);

        root.add(createSidebar(), BorderLayout.WEST);
        root.add(createDashboard(), BorderLayout.CENTER);
    }

    private JPanel createSidebar() {

        JPanel side = new JPanel();
        side.setPreferredSize(new Dimension(205, 0));
        side.setBackground(PANEL);
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setBorder(new EmptyBorder(22, 16, 20, 16));

        JLabel logo = new JLabel("  ◆  PayFlow");
        logo.setFont(new Font("SansSerif", Font.BOLD, 25));
        logo.setForeground(WHITE);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel menuTitle = label("WORKSPACE", 11, MUTED);
        menuTitle.setBorder(new EmptyBorder(42, 10, 15, 0));
        menuTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        side.add(logo);
        side.add(menuTitle);
        side.add(menuItem("▦   Dashboard", true));
        side.add(Box.createVerticalStrut(10));
        side.add(menuItem("↗   Make Payment", false));
        side.add(Box.createVerticalStrut(10));
        side.add(menuItem("▤   Transactions", false));
        side.add(Box.createVerticalStrut(10));
        side.add(menuItem("⚙   Settings", false));
        side.add(Box.createVerticalGlue());

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(new Color(34, 43, 70));
        info.setBorder(new EmptyBorder(14, 12, 14, 12));
        info.setAlignmentX(Component.LEFT_ALIGNMENT);
        info.setMaximumSize(new Dimension(200, 90));

        info.add(label("DEMO ENVIRONMENT", 11, GREEN));
        info.add(Box.createVerticalStrut(8));
        info.add(label("No real money transferred", 11, MUTED));

        side.add(info);

        return side;
    }

    private JPanel menuItem(String text, boolean active) {

        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 12));
        item.setMaximumSize(new Dimension(200, 45));
        item.setAlignmentX(Component.LEFT_ALIGNMENT);
        item.setBackground(active ? BLUE : PANEL);
        item.setBorder(new EmptyBorder(0, 8, 0, 0));

        JLabel name = label(text, 13, WHITE);
        item.add(name);

        return item;
    }

    private JPanel createDashboard() {

        JPanel main = new JPanel(new BorderLayout(0, 20));
        main.setBackground(BG);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));

        JLabel title = label("Payment Dashboard", 28, WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));

        heading.add(title);
        heading.add(Box.createVerticalStrut(7));
        heading.add(label("Manage your payments with ease.", 13, MUTED));

        JLabel badge = label("●  DEMO MODE", 12, GREEN);
        badge.setOpaque(true);
        badge.setBackground(new Color(20, 64, 63));
        badge.setBorder(new EmptyBorder(9, 12, 9, 12));

        header.add(heading, BorderLayout.WEST);
        header.add(badge, BorderLayout.EAST);

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel stats = new JPanel(new GridLayout(1, 3, 14, 0));
        stats.setOpaque(false);
        stats.add(statCard("TOTAL PAYMENTS", "Demo", BLUE));
        stats.add(statCard("PAYMENT METHODS", "02", PURPLE));
        stats.add(statCard("SYSTEM STATUS", "Ready", GREEN));

        JPanel forms = new JPanel(new GridLayout(1, 2, 16, 0));
        forms.setOpaque(false);
        forms.add(createPaymentForm());
        forms.add(createSummary());

        JPanel activity = new JPanel(new BorderLayout(0, 10));
        activity.setBackground(PANEL);
        activity.setBorder(new CompoundBorder(
            new LineBorder(new Color(48, 58, 85), 1, true),
            new EmptyBorder(16, 16, 16, 16)
        ));

        activity.add(label("Payment Activity", 17, WHITE),
                     BorderLayout.NORTH);

        resultArea = new JTextArea(
            "No payments processed yet.\nYour demo payment details will appear here."
        );
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBackground(PANEL);
        resultArea.setForeground(MUTED);
        resultArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        resultArea.setBorder(new EmptyBorder(10, 0, 0, 0));

        activity.add(new JScrollPane(resultArea) {{
            setBorder(null);
            getViewport().setBackground(PANEL);
        }}, BorderLayout.CENTER);

        content.add(stats);
        content.add(Box.createVerticalStrut(18));
        content.add(forms);
        content.add(Box.createVerticalStrut(18));
        content.add(activity);

        main.add(header, BorderLayout.NORTH);
        main.add(content, BorderLayout.CENTER);

        return main;
    }

    private JPanel statCard(String heading, String value, Color accent) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(PANEL);
        card.setBorder(new CompoundBorder(
            new LineBorder(new Color(48, 58, 85), 1, true),
            new EmptyBorder(16, 14, 16, 14)
        ));

        card.add(label(heading, 10, MUTED));
        card.add(Box.createVerticalStrut(13));

        JLabel valueLabel = label(value, 22, accent);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        card.add(valueLabel);

        return card;
    }

    private JPanel createPaymentForm() {

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(PANEL);
        form.setBorder(new CompoundBorder(
            new LineBorder(new Color(48, 58, 85), 1, true),
            new EmptyBorder(20, 18, 18, 18)
        ));

        form.add(label("Make a Payment", 19, WHITE));
        form.add(Box.createVerticalStrut(20));

        form.add(label("CUSTOMER NAME", 11, MUTED));
        form.add(Box.createVerticalStrut(7));

        nameField = inputField("Enter customer name");
        form.add(nameField);

        form.add(Box.createVerticalStrut(15));
        form.add(label("PAYMENT AMOUNT (₹)", 11, MUTED));
        form.add(Box.createVerticalStrut(7));

        amountField = inputField("Enter amount");
        form.add(amountField);

        form.add(Box.createVerticalStrut(15));
        form.add(label("PAYMENT METHOD", 11, MUTED));
        form.add(Box.createVerticalStrut(7));

        methodBox = new JComboBox<>(new String[]{"UPI", "Card"});
        methodBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        methodBox.setForeground(WHITE);
        methodBox.setBackground(new Color(34, 43, 70));
        methodBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        methodBox.setFocusable(false);
        form.add(methodBox);

        form.add(Box.createVerticalStrut(20));

        JButton payButton = new JButton("PROCESS PAYMENT   →");
        payButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        payButton.setForeground(Color.WHITE);
        payButton.setBackground(BLUE);
        payButton.setFocusPainted(false);
        payButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        payButton.setBorder(new EmptyBorder(13, 10, 13, 10));
        payButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        payButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));

        payButton.addActionListener(e -> processPayment());
        form.add(payButton);

        form.add(Box.createVerticalStrut(10));

        JButton clearButton = new JButton("Clear Form");
        clearButton.setForeground(MUTED);
        clearButton.setBackground(PANEL);
        clearButton.setFocusPainted(false);
        clearButton.setBorder(new EmptyBorder(8, 8, 8, 8));
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        clearButton.addActionListener(e -> {
            nameField.setText("");
            amountField.setText("");
            resultArea.setText("Form cleared. Ready for a new demo payment.");
            summaryAmount.setText("₹ 0.00");
            statusLabel.setText("●  Waiting for payment");
            statusLabel.setForeground(MUTED);
        });

        form.add(clearButton);

        return form;
    }

    private JPanel createSummary() {

        JPanel summary = new JPanel();
        summary.setLayout(new BoxLayout(summary, BoxLayout.Y_AXIS));
        summary.setBackground(new Color(30, 37, 68));
        summary.setBorder(new CompoundBorder(
            new LineBorder(new Color(86, 79, 170), 1, true),
            new EmptyBorder(20, 18, 18, 18)
        ));

        JLabel icon = label("✦", 30, PURPLE);
        summary.add(icon);
        summary.add(Box.createVerticalStrut(12));
        summary.add(label("Payment Summary", 19, WHITE));
        summary.add(Box.createVerticalStrut(28));
        summary.add(label("TOTAL AMOUNT", 11, MUTED));
        summary.add(Box.createVerticalStrut(8));

        summaryAmount = label("₹ 0.00", 30, WHITE);
        summaryAmount.setFont(new Font("SansSerif", Font.BOLD, 30));
        summary.add(summaryAmount);

        summary.add(Box.createVerticalStrut(25));
        summary.add(label("PAYMENT STATUS", 11, MUTED));
        summary.add(Box.createVerticalStrut(10));

        statusLabel = label("●  Waiting for payment", 12, MUTED);
        summary.add(statusLabel);

        summary.add(Box.createVerticalStrut(25));
        summary.add(label("Secure demo environment", 12, GREEN));
        summary.add(Box.createVerticalGlue());

        JLabel note = label(
            "<html>UPI and Card are simulated.<br>No actual transaction occurs.</html>",
            11, MUTED
        );
        summary.add(note);

        return summary;
    }

    private JTextField inputField(String placeholder) {

        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(WHITE);
        field.setCaretColor(WHITE);
        field.setBackground(new Color(34, 43, 70));
        field.setBorder(new CompoundBorder(
            new LineBorder(new Color(63, 74, 105), 1, true),
            new EmptyBorder(10, 11, 10, 11)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
        field.setToolTipText(placeholder);

        return field;
    }

    private JLabel label(String text, int size, Color color) {

        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, size));
        label.setForeground(color);

        return label;
    }

    private void processPayment() {

        String name = nameField.getText().trim();
        String amountText = amountField.getText().trim();
        String method = (String) methodBox.getSelectedItem();

        if (name.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter customer name and amount.",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            double amount = Double.parseDouble(amountText);

            if (!Double.isFinite(amount) || amount <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Enter an amount greater than zero.",
                    "Invalid Amount",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            payment p;

            if ("UPI".equals(method)) {
                p = new upi(name);
            } else {
                p = new CardPayment(name);
            }

            PaymentMethod selectedMethod = (PaymentMethod) p;
            selectedMethod.processpayment(amount);

            summaryAmount.setText(String.format("₹ %.2f", amount));
            statusLabel.setText("●  Demo payment processed");
            statusLabel.setForeground(GREEN);

            resultArea.setForeground(GREEN);
            resultArea.setText(
                "PAYMENT PROCESSED (DEMO)\n\n" +
                "Customer : " + name + "\n" +
                "Method   : " + method + "\n" +
                String.format("Amount   : ₹ %.2f%n", amount) +
                "\nNo real money was transferred."
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric amount.",
                "Invalid Amount",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
            }

            new PaymentGUI().setVisible(true);
        });
    }
}
