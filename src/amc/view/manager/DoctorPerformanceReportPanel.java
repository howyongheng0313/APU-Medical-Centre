/*package amc.view.manager;

import amc.model.entity.ReportsDTO;
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

    public void displayReport(ReportsDTO.DocterPerformanceReport report) {
        yearLabel.setText("Doctor Performance Report for " + report.getYear());

        DefaultTableModel model = (DefaultTableModel) doctorPerformanceTable.getModel();
        model.setRowCount(0);
        for (ReportsDTO.DoctorPerformance d : report.getDoctorPerformances()) {
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
}*/

package amc.view.manager;

import amc.model.entity.ReportsDTO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

// Panel to display doctors' average rating for a given year.
public class DoctorPerformanceReportPanel extends JPanel {

    // Title label shown at the top
    private final JLabel yearLabel = new JLabel("Doctor Performance Report", SwingConstants.CENTER);

    // Container that holds the chart
    private final JPanel chartContainer = new JPanel(new BorderLayout());

    public DoctorPerformanceReportPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 253, 253));  // overall background

        // Header with centered title 
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(getBackground());
        header.setPreferredSize(new Dimension(800, 60));
        yearLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.add(yearLabel, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Chart area 
        chartContainer.setBackground(Color.WHITE);
        chartContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(chartContainer, BorderLayout.CENTER);
    }

    // Public method to refresh the report with new data.
    public void displayReport(ReportsDTO.DocterPerformanceReport report) {
        yearLabel.setText("Doctor Performance Report for " + report.getYear());
        createAverageRatingBarChart(report.getDoctorPerformances());
    }

    //Builds and displays a bar chart of doctors' average ratings.
    private void createAverageRatingBarChart(List<ReportsDTO.DoctorPerformance> performances) {
        // Prepare dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ReportsDTO.DoctorPerformance d : performances) {
            String shortName = d.getDoctorName().length() > 15
                    ? d.getDoctorName().substring(0, 12) + "..."
                    : d.getDoctorName();
            dataset.addValue(d.getAverageRating(), "Average Rating", shortName);
        }

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "", "Doctors", "Average Rating", dataset);

        // Style plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.getRenderer().setSeriesPaint(0, new Color(79, 129, 189));
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Replace any old chart
        chartContainer.removeAll();
        chartContainer.add(new ChartPanel(chart), BorderLayout.CENTER);
        chartContainer.revalidate();
        chartContainer.repaint();
    }
}
