package amc.view.manager;

import amc.model.entity.ReportData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class DoctorPerformanceReportPanel extends JPanel {
    // UI fields (initialized eagerly to avoid nulls)
    private final JLabel yearLabel = new JLabel("Doctor Performance Report");
    private final JTable doctorPerformanceTable = new JTable(
        new DefaultTableModel(new Object[]{"Doctor Name","Total Appointments","Completed","Avg Rating","Total Revenue"}, 0)
    );

    public DoctorPerformanceReportPanel() {
        initComponents();
    }

    public void displayReport(ReportData.DocterPerformanceReport report) {
        yearLabel.setText("Doctor Performance Report for " + report.getYear());

        DefaultTableModel model = (DefaultTableModel) doctorPerformanceTable.getModel();
        model.setRowCount(0);
        for (ReportData.DoctorPerformance d : report.getDoctorPerformances()) {
            model.addRow(new Object[]{
                d.getDoctorName(),
                d.getTotalAppointments(),
                d.getCompletedAppointments(),
                String.format("%.1f", d.getAverageRating()),
                formatCurrency(d.getTotalRevenue())
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
        header.add(yearLabel);
        add(header, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(doctorPerformanceTable);
        doctorPerformanceTable.setPreferredScrollableViewportSize(new Dimension(720, 380));
        add(scroll, BorderLayout.CENTER);
    }
}