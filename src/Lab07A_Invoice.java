import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Lab07A_Invoice extends JFrame {
    private List<LineItem> lineItems = new ArrayList<>();
    private JTextArea invoiceTextArea;
    private JTextField productNameField, quantityField, unitPriceField;

    public Lab07A_Invoice() {
        createUI();
    }

    private void createUI() {
        setTitle("Invoice Application");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        inputPanel.add(productNameField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Unit Price:"));
        unitPriceField = new JTextField();
        inputPanel.add(unitPriceField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLineItem();
            }
        });
        buttonPanel.add(addButton);

        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(inputPanel, BorderLayout.NORTH);
        combinedPanel.add(buttonPanel, BorderLayout.CENTER);

        add(combinedPanel, BorderLayout.NORTH);

        invoiceTextArea = new JTextArea();
        add(new JScrollPane(invoiceTextArea), BorderLayout.CENTER);
    }



    private void addLineItem() {
        String name = productNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double unitPrice = Double.parseDouble(unitPriceField.getText());
        Product product = new Product(name, unitPrice);
        LineItem lineItem = new LineItem(quantity, product);
        lineItems.add(lineItem);

        productNameField.setText("");
        quantityField.setText("");
        unitPriceField.setText("");

        displayInvoice();
    }

    private void displayInvoice() {
        StringBuilder sb = new StringBuilder();
        double total = 0;
        for(LineItem item : lineItems) {
            sb.append(item.getProduct().getName())
                    .append(" ")
                    .append(item.getProduct().getUnitPrice())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(": $")
                    .append(item.calculateTotal())
                    .append("\n");
            total += item.calculateTotal();
        }
        total = Math.round(total / 100.0) * 100.0;
        sb.append("Total Amount Due: $").append(String.format("%.2f", total));
        invoiceTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Lab07A_Invoice().setVisible(true);
        });
    }
}