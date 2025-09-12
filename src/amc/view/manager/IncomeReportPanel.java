package amc.view.manager;

import amc.model.entity.ReportsDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class IncomeReportPanel extends JPanel {
    // Keep only these fields and instantiate them
    private final JLabel yearLabel = new JLabel();
    private final JLabel totalIncomeLabel = new JLabel();
    private final JTable monthlyIncomeTable = new JTable(new DefaultTableModel(new Object[]{"Month","Income","Appointments"}, 0));
    private final JTable paymentMethodTable = new JTable(new DefaultTableModel(new Object[]{"Payment Method","Amount","Count"}, 0));

    public IncomeReportPanel() {
        initComponents();
    }

    public void displayReport(ReportsDTO.IncomeReport report) {
        yearLabel.setText("Income Report for " + report.getYear());
        totalIncomeLabel.setText("Total Income: " + formatCurrency(report.getTotalIncome()));

        // 月份表格
        DefaultTableModel monthlyModel = (DefaultTableModel) monthlyIncomeTable.getModel();
        monthlyModel.setRowCount(0);
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for (ReportsDTO.MonthlyIncome m : report.getMonthlyIncomes()) {
            monthlyModel.addRow(new Object[]{ 
                months[m.getMonth() - 1], 
                formatCurrency(m.getIncome()), 
                m.getAppointmentCount() 
            });
        }

        // 支付方法表格
        DefaultTableModel payModel = (DefaultTableModel) paymentMethodTable.getModel();
        payModel.setRowCount(0);
        for (ReportsDTO.PaymentBreakdown pb : report.getPaymentBreakdown()) {
            payModel.addRow(new Object[]{ 
                pb.getMethod().toString(), 
                formatCurrency(pb.getAmount()), 
                pb.getCount() 
            });
        }
    }

    private String formatCurrency(double amount) {
        return NumberFormat.getCurrencyInstance(Locale.US)
                .format(amount)
                .replace("$", "MYR ");
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(new Color(245, 253, 253));
        header.setPreferredSize(new Dimension(800, 60));

        yearLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalIncomeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        totalIncomeLabel.setText("Total Income: MYR 0.00");

        header.add(yearLabel);
        header.add(Box.createHorizontalStrut(20));
        header.add(totalIncomeLabel);
        add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel monthlyLbl = new JLabel("Monthly Income Breakdown");
        monthlyLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        content.add(monthlyLbl, gbc);

        JScrollPane monthlyScroll = new JScrollPane(monthlyIncomeTable);
        monthlyIncomeTable.setPreferredScrollableViewportSize(new Dimension(300, 200));
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.BOTH;
        content.add(monthlyScroll, gbc);

        JLabel payLbl = new JLabel("Payment Method Breakdown");
        payLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 1; gbc.gridy = 0; gbc.insets = new Insets(0, 20, 0, 0);
        content.add(payLbl, gbc);

        JScrollPane payScroll = new JScrollPane(paymentMethodTable);
        paymentMethodTable.setPreferredScrollableViewportSize(new Dimension(300, 200));
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.BOTH;
        content.add(payScroll, gbc);

        add(content, BorderLayout.CENTER);
    }
}