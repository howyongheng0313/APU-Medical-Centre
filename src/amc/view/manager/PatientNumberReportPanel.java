package amc.view.manager;

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
}