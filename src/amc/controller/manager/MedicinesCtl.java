package amc.controller.manager;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.Medicine;
import amc.view.manager.MedicinesPanel;
import java.awt.Component;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MedicinesCtl extends AbstractSubCtl {
    
    private final MedicinesPanel viewMedicines = new MedicinesPanel();
    
    public MedicinesCtl(AmcCtl ROOT){
        super(ROOT);
        setupMedicineFeatures();
        loadAllMedicines();
    }
    
    private void setupMedicineFeatures(){
        // Text search
        viewMedicines.addPropertyChangeListener("searchByText", evt -> {
            String searchText = viewMedicines.getSearchInput();
            if(searchText != null && !searchText.trim().isEmpty() && !searchText.equals("Search medicine")) {
                this.loadMedicinesBySearch();
            } else {
                this.loadAllMedicines();
            }
        });
        
        // Create medicine
        viewMedicines.addPropertyChangeListener("createMedicine", evt -> {
            this.createMedicine();
        });
        
        // Update medicine
        viewMedicines.addPropertyChangeListener("updateMedicine", evt -> {
            this.updateMedicine();
        });
        
        // Delete medicine
        viewMedicines.addPropertyChangeListener("deleteMedicine", evt -> {
            this.deleteMedicine();
        });
    }
    
    // Load all medicines
    private void loadAllMedicines() {
        try {
            List<Medicine> medicines = this.getAllMedicines();
            viewMedicines.showMedicines(medicines);
            viewMedicines.resetSearchField();
        } catch(Exception ex) {
            this.warn(viewMedicines, "Error loading medicines: " + ex.getMessage());
        }
    }
    
    // Load medicine by search
    private void loadMedicinesBySearch() {
        try {
            String searchText = viewMedicines.getSearchInput();
            List<Medicine> medicines = this.getMedicinesBySearch(searchText);
            viewMedicines.showMedicines(medicines);
            viewMedicines.resetSearchField();
        } catch(Exception ex) {
            this.warn(viewMedicines, "Error searching medicines: " + ex.getMessage());
        }
    }
    
    // Create medicine
    private void createMedicine() {
        try {
            String name = viewMedicines.getCreateMedicineName();
            double price = viewMedicines.getCreateMedicinePrice();
            
            if (name == null || name.trim().isEmpty()) {
                this.warn(viewMedicines, "Medicine name cannot be empty");
                return;
            }
            
            if (price <= 0) {
                this.warn(viewMedicines, "Medicine price must be greater than 0");
                return;
            }
            
            // Generate new medicine ID
            String newId = this.generateNewMedicineId();
            
            Medicine newMedicine = new Medicine(newId, name.trim(), price);
            Db.Medicine.insert(List.of(newMedicine));
            
            viewMedicines.hideCreateDialog();
            viewMedicines.clearCreateForm();
            loadAllMedicines();
            
            // Congrats message
            this.congrats(viewMedicines, "Medicine created successfully");
                
        } catch(Exception ex) {
            this.warn(viewMedicines, "Error creating medicine: " + ex.getMessage());
        }
    }
 
    // Update medicine
    private void updateMedicine() {
        try {
            Medicine selectedMedicine = viewMedicines.getSelectedMedicine();
            if (selectedMedicine == null) {
                this.warn(viewMedicines, "Please select a medicine to update");
                return;
            }
            
            String newName = viewMedicines.getUpdateMedicineName();
            double newPrice = viewMedicines.getUpdateMedicinePrice();
            
            if (newName == null || newName.trim().isEmpty()) {
                this.warn(viewMedicines, "Medicine name cannot be empty");
                return;
            }
            
            if (newPrice <= 0) {
                this.warn(viewMedicines, "Medicine price must be greater than 0");
                return;
            }
            
            Db.Medicine.update(-1, 
                DbMan.checkById(selectedMedicine.getId()),
                medicine -> {
                    medicine.setMedicineName(newName.trim());
                    medicine.setPrice(newPrice);
                    return medicine;
                });
            
            viewMedicines.hideUpdateDialog();
            viewMedicines.clearUpdateForm();
            loadAllMedicines();
            
            // Congrats message
            this.congrats(viewMedicines, "Medicine updated successfully!");
                
        } catch(Exception ex) {
            this.warn(viewMedicines, "Error updating medicine: " + ex.getMessage());
        }
    }
    
    // Delete medicine
    private void deleteMedicine() {
        try {
            Medicine selectedMedicine = viewMedicines.getSelectedMedicine();
            if (selectedMedicine == null) {
                this.warn(viewMedicines, "Please select a medicine to delete");
                return;
            }
            
            // Confirmation message 
            int confirm = JOptionPane.showConfirmDialog(viewMedicines,
                "Are you sure you want to delete medicine: " + selectedMedicine.getMedicineName() + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                Db.Medicine.delete(-1, DbMan.checkById(selectedMedicine.getId()));
                
                this.loadAllMedicines(); // Load all medicines
                
                this.congrats(viewMedicines, "Medicine deleted successfully!"); // Congrats message
            }
            
        } catch(Exception ex) {
            this.warn(viewMedicines, "Error deleting medicine: " + ex.getMessage());
        }
    }
    
    // Get all medicine from database
    private List<Medicine> getAllMedicines() {
        return Db.Medicine.select(-1, medicine -> true);
    }
    
    // Get medicine by search input
    private List<Medicine> getMedicinesBySearch(String searchText){
        try {
            List<Medicine> allMedicines = getAllMedicines();
            String lowerSearchText = searchText.toLowerCase();
            
            return allMedicines.stream()
                    .filter(med ->
                            med.getMedicineName().toLowerCase().contains(lowerSearchText)
                    )
                    .collect(Collectors.toList());
        } catch(Exception ex) {
            this.warn(viewMedicines, "Error searching medicines: " + ex.getMessage());
            return List.of();
        }
    }
    
    // Generate new medicineId for create
    private String generateNewMedicineId() {
        List<Medicine> allMedicines = getAllMedicines();
        int maxId = 0;
        
        for (Medicine medicine : allMedicines) {
            String id = medicine.getId();
            if (id.startsWith("MDC-")) {
                try {
                    int num = Integer.parseInt(id.substring(4));
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {}
            }
        }
        
        return String.format("MDC-%03d", maxId + 1);
    }
    
    // Warning message
    private void warn(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Error", JOptionPane.WARNING_MESSAGE);
    }
    
    // Congrats message
        private void congrats(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public JPanel getView() { return viewMedicines;}
}
