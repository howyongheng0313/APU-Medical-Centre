/*package amc.view.manager;

import amc.model.entity.ReportsDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PatientNumberReportPanel extends JPanel {
    // UI fields (initialized eagerly to avoid nulls)
    private final JLabel yearLabel = new JLabel("Patient Number Report");
    private final JLabel totalPatientsLabel = new JLabel("Total Unique Patients: 0");
    private final JTable monthlyPatientsTable = new JTable(
        new DefaultTableModel(new Object[]{"Month", "Total Patients", "New Patients"}, 0)
    );
    private final JTable departmentPatientsTable = new JTable(
        new DefaultTableModel(new Object[]{"Department", "Patient Count"}, 0)
    );

    public PatientNumberReportPanel() {
        initComponents();
    }

    public void displayReport(ReportsDTO.PatientNumberReport report) {
        // 设置标签
        yearLabel.setText("Patient Number Report for " + report.getYear());
        totalPatientsLabel.setText("Total Unique Patients: " + report.getTotalPatients());

        // 月份缩写
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        
        // 显示月度病人数据
        DefaultTableModel mModel = (DefaultTableModel) monthlyPatientsTable.getModel();
        mModel.setRowCount(0);
        for (ReportsDTO.MonthlyPatients m : report.getMonthlyPatients()) {
            mModel.addRow(new Object[]{
                months[m.getMonth() - 1],
                m.getPatientCount(),
                m.getNewPatients()
            });
        }

        // 显示科室病人数据
        DefaultTableModel dModel = (DefaultTableModel) departmentPatientsTable.getModel();
        dModel.setRowCount(0);
        for (ReportsDTO.DepartmentPatients d : report.getDepartmentPatients()) {
            dModel.addRow(new Object[]{ 
                d.getDepartmentName(), 
                d.getPatientCount() 
            });
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(new Color(245, 253, 253));
        header.setPreferredSize(new Dimension(800, 60));
        yearLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalPatientsLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.add(yearLabel);
        header.add(Box.createHorizontalStrut(16));
        header.add(totalPatientsLabel);
        add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel monthlyLbl = new JLabel("Monthly Patient Statistics");
        monthlyLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        content.add(monthlyLbl, gbc);

        JScrollPane monthlyScroll = new JScrollPane(monthlyPatientsTable);
        monthlyPatientsTable.setPreferredScrollableViewportSize(new Dimension(350, 260));
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.BOTH;
        content.add(monthlyScroll, gbc);

        JLabel deptLbl = new JLabel("Department Patient Count");
        deptLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 1; gbc.gridy = 0; gbc.insets = new Insets(0, 20, 0, 0); gbc.anchor = GridBagConstraints.WEST;
        content.add(deptLbl, gbc);

        JScrollPane deptScroll = new JScrollPane(departmentPatientsTable);
        departmentPatientsTable.setPreferredScrollableViewportSize(new Dimension(350, 260));
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.BOTH;
        content.add(deptScroll, gbc);

        add(content, BorderLayout.CENTER);
    }
}*/

package amc.view.manager;

import amc.model.entity.ReportsDTO;
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
** Panel showing: 
**   1) Monthly patient numbers (bar chart)
**   2) Department distribution (pie chart)
*/
public class PatientNumberReportPanel extends JPanel {

    // Header labels
    private final JLabel titleLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel totalLabel = new JLabel("", SwingConstants.CENTER);

    // Container for both charts
    private final JPanel chartContainer = new JPanel(new GridLayout(1, 2, 10, 0));

    public PatientNumberReportPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Header
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(new Color(245, 253, 253));
        header.setBorder(new EmptyBorder(10, 10, 10, 10));

        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.add(titleLabel);
        header.add(totalLabel);
        add(header, BorderLayout.NORTH);

        // Charts
        chartContainer.setBackground(Color.WHITE);
        add(chartContainer, BorderLayout.CENTER);
    }

    // Refresh the panel with a new report.
    public void displayReport(ReportsDTO.PatientNumberReport report) {
        titleLabel.setText("Patient Number Report for " + report.getYear());
        totalLabel.setText("Total Unique Patients: " + report.getTotalPatients());

        // Replace old charts
        chartContainer.removeAll();
        ChartPanel bar = createBarChart(report);
        ChartPanel pie = createPieChart(report);

        // Ensure equal height for perfect horizontal alignment
        int height = 400;
        bar.setPreferredSize(new Dimension(0, height));
        pie.setPreferredSize(new Dimension(0, height));

        chartContainer.add(bar);
        chartContainer.add(pie);
        chartContainer.revalidate();
        chartContainer.repaint();
    }

    // Build monthly patient bar chart. 
    private ChartPanel createBarChart(ReportsDTO.PatientNumberReport report) {
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for (ReportsDTO.MonthlyPatients m : report.getMonthlyPatients()) {
            data.addValue(m.getPatientCount(), "Total Patients", months[m.getMonth() - 1]);
            data.addValue(m.getNewPatients(),   "New Patients",   months[m.getMonth() - 1]);
        }

        JFreeChart chart = ChartFactory.createBarChart("", "Month", "Number of Patients", data);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.getRenderer().setSeriesPaint(0, new Color(79,129,189)); // blue
        plot.getRenderer().setSeriesPaint(1, new Color(192,80,77));  // red
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        ChartPanel panel = new ChartPanel(chart);
        panel.setBorder(new EmptyBorder(0,0,0,0));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    // Build department distribution pie chart.
    private ChartPanel createPieChart(ReportsDTO.PatientNumberReport report) {
        DefaultPieDataset data = new DefaultPieDataset();
        for (ReportsDTO.DepartmentPatients d : report.getDepartmentPatients()) {
            data.setValue(d.getDepartmentName(), d.getPatientCount());
        }

        JFreeChart chart = ChartFactory.createPieChart("", data, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})",
                NumberFormat.getIntegerInstance(),
                NumberFormat.getPercentInstance()
        ));

        ChartPanel panel = new ChartPanel(chart);
        panel.setBorder(new EmptyBorder(0,0,0,0));
        panel.setBackground(Color.WHITE);
        return panel;
    }
}
