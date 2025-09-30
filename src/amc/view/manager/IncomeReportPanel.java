/*package amc.view.manager;

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
}*/

package amc.view.manager;

import amc.model.entity.ReportsDTO;
import amc.model.DataUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.NumberFormat;

/*
** IncomeReportPanel:
**      Monthly income (bar chart)
**      Payment method distribution (pie chart)
*/
public class IncomeReportPanel extends JPanel {

    private final JLabel titleLabel  = new JLabel("", SwingConstants.CENTER);
    private final JLabel totalLabel  = new JLabel("", SwingConstants.CENTER);
    private final JPanel chartContainer = new JPanel(new GridLayout(1, 2, 10, 0)); // two charts side by side

    public IncomeReportPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // panel background

        // Header Section 
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(new Color(245, 253, 253));       // soft teal background
        header.setBorder(new EmptyBorder(10, 10, 10, 10));    // padding around labels

        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        header.add(titleLabel);
        header.add(totalLabel);
        add(header, BorderLayout.NORTH);

        // Chart Container 
        chartContainer.setBackground(Color.WHITE);
        add(chartContainer, BorderLayout.CENTER);
    }

    // Populate the panel with a specific report.
    public void displayReport(ReportsDTO.IncomeReport report) {
        // Update header text
        titleLabel.setText("Income Report for " + report.getYear());
        totalLabel.setText("Total Income: " + DataUtil.amount2str(report.getTotalIncome()));

        // Clear old charts and add new ones
        chartContainer.removeAll();
        chartContainer.add(createMonthlyIncomeBar(report));
        chartContainer.add(createPaymentMethodPie(report));

        chartContainer.revalidate();
        chartContainer.repaint();
    }

    // Creates a bar chart showing monthly income.
    private ChartPanel createMonthlyIncomeBar(ReportsDTO.IncomeReport report) {
        // Build dataset
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun",
                           "Jul","Aug","Sep","Oct","Nov","Dec"};
        for (ReportsDTO.MonthlyIncome m : report.getMonthlyIncomes()) {
            data.addValue(m.getIncome(), "Income", months[m.getMonth() - 1]);
        }

        // Create chart with no title (we already show it in the header)
        JFreeChart chart = ChartFactory.createBarChart(
                "", "Month", "Income (MYR)", data);

        // Customize plot appearance
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.getRenderer().setSeriesPaint(0, new Color(79, 129, 189)); // blue bars
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45); // rotate labels

        // Wrap in ChartPanel
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(0, 400));
        panel.setBorder(new EmptyBorder(0,0,0,0));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    // Creates a pie chart showing payment method breakdown.
    private ChartPanel createPaymentMethodPie(ReportsDTO.IncomeReport report) {
        // Build dataset
        DefaultPieDataset data = new DefaultPieDataset();
        for (ReportsDTO.PaymentBreakdown pb : report.getPaymentBreakdown()) {
            data.setValue(pb.getMethod().toString(), pb.getAmount());
        }

        // Create chart with no title
        JFreeChart chart = ChartFactory.createPieChart("", data, true, true, false);

        // Customize plot appearance
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                "{0}: {2}",
                NumberFormat.getNumberInstance(),
                NumberFormat.getPercentInstance()
        ));

        // Wrap in ChartPanel
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(0, 400));
        panel.setBorder(new EmptyBorder(0,0,0,0));
        panel.setBackground(Color.WHITE);
        return panel;
    }
}
