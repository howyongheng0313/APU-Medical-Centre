package amc.controller.manager;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.*;
import amc.view.manager.ServicesPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ServicesCtl extends AbstractSubCtl {
    
    private final ServicesPanel viewServices = new ServicesPanel();
    
    // Constructor
    public ServicesCtl(AmcCtl ROOT){
        super(ROOT);
        setupServiceFeature();
        loadAllServices();
    }
    
    
    // Main
    private void setupServiceFeature(){
        // Department search
        viewServices.addPropertyChangeListener("searchByDepartment", evt ->{
            String department = viewServices.getSelectedDepartment();
            if(department != null && !department.isEmpty()) {
                loadServiceByDepartment(department);
            }
        });
        
        // Text search 
        viewServices.addPropertyChangeListener("searchByText", evt ->{
        String searchText = viewServices.getSearchInput();
            if(searchText != null && !searchText.trim().isEmpty() && !searchText.equals("Search service")) {
                this.loadServicesBySearch(searchText);
            } else {
                this.loadAllServices();
            }
        });
        
        // Create service
        viewServices.addPropertyChangeListener("createService", evt -> {
            createService();
        });
        
        // Update service
        viewServices.addPropertyChangeListener("updateService", evt -> {
            updateService();
        });
        
        // Update service
        viewServices.addPropertyChangeListener("deleteService", evt -> {
            deleteService();
        });
    }
    
    private void loadAllServices(){
        try {
            var services = this.getAllServices();
            viewServices.showServices(services);
            viewServices.resetSearchField();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(
                viewServices, 
                "Error loading services" + ex.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void loadServiceByDepartment(String department){
        try {
            var services = this.getServicesByDepartment(department);
            viewServices.showServices(services);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(
                viewServices, "Error loading services by department" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void loadServicesBySearch(String searchText) {
        try {
            var services = this.getServicesBySearch(searchText);
            viewServices.showServices(services);
            viewServices.resetSearchField();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                viewServices, "Error searching services: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    } 


    // CRUD
    private void createService(){
        try{
            String departmentName = viewServices.getCreateDepartment();
            String serviceName = viewServices.getCreateServiceName();
            String feeText = viewServices.getCreateServiceFee();
            
            // Validation
            if(departmentName == null || departmentName.trim().isEmpty()){
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Please select a department", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
            }
            if (serviceName == null || serviceName.trim().isEmpty() || "Enter service name".equals(serviceName)) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Please enter a service name", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
            } 
            if (feeText == null || feeText.trim().isEmpty() || "Enter service fee".equals(feeText)) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Please enter a service fee", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
            }
            
            // Parse fee
            double fee;
            try{
                fee = Double.parseDouble(feeText.replace("MYR", "").trim());
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Please enter a valid fee amount", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            // Get department ID
            String departmentId = getDepartmentIdByName(departmentName);
            if (departmentId == null) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Invalid department selected", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
            }
            
            // Generate new ServiceId
            String serviceId = generateNewServiceId();
            
            // Create service
            Service newService = new Service(serviceId, serviceName, fee, departmentId);
            
            // Insert into database
            boolean success = Db.Service.insert(List.of(newService));
            
            if(success){
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Service created successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                viewServices.closeCreateDialog();
                loadAllServices(); // Refresh the list
            } else {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Failed to create service", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                viewServices, 
                "Error creating service: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
        
    }
    
    private void updateService(){
        try{
            String serviceId = viewServices.getSelectedServiceId();
            if (serviceId == null || serviceId.isEmpty()) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Please select a service to update", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
            }
            
            String departmentName = (String)viewServices.getUpdateDepartment();
            String serviceName = viewServices.getUpdateServiceName();
            String feeText = viewServices.getUpdateServiceFee();
            
            // Validation
            if (departmentName == null || departmentName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Please select a department", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
            }
            if (serviceName == null || serviceName.trim().isEmpty() || "Enter new service name".equals(serviceName)) {
                JOptionPane.showMessageDialog(
                    viewServices, "Please enter a service name", 
                    "Validation Error", JOptionPane.WARNING_MESSAGE
                );
            } 
            if (feeText == null || feeText.trim().isEmpty() || "Enter new service fee".equals(feeText)) {
                JOptionPane.showMessageDialog(
                    viewServices, "Please enter a service fee", 
                    "Validation Error", JOptionPane.WARNING_MESSAGE
                );
            }
            
            // Parse fee
            double fee;
            try {
                fee = Double.parseDouble(feeText.replace("MYR", "").trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Please enter a valid fee amount", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            // Get department ID
            String departmentId = getDepartmentIdByName(departmentName);
            if (departmentId == null) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Invalid department selected", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
            }
            
            // Update service in database
            int updated = Db.Service.update(
                1, DbMan.checkById(serviceId), 
                s -> {
                    s.setServiceName(serviceName);
                    s.setFee(fee);
                    s.setDepartmentId(departmentId);
                    return s;
                }
            );
            
            if (updated > 0) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Service updated successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                viewServices.closeUpdateDialog();
                loadAllServices(); // Refresh the list
            } else {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Failed to update service", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }      
        } catch(Exception ex){
            JOptionPane.showMessageDialog(
                viewServices, 
                "Error updating service: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void deleteService() {
        try {
            String serviceId = viewServices.getSelectedServiceId();
            if (serviceId == null || serviceId.isEmpty()) {
                JOptionPane.showMessageDialog(
                    viewServices, 
                    "Please select a service to delete", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            // Confirm deletion
            int result = JOptionPane.showConfirmDialog(
                viewServices, 
                "Are you sure you want to delete this service?", 
                "Confirm Deletion", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (result == JOptionPane.YES_OPTION) {
                // Delete from database
                int deleted = Db.Service.delete(1, DbMan.checkById(serviceId));
                
                if (deleted > 0) {
                    JOptionPane.showMessageDialog(
                        viewServices, 
                        "Service deleted successfully!", 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    loadAllServices(); // Refresh the list
                } else {
                    JOptionPane.showMessageDialog(
                        viewServices, 
                        "Failed to delete service", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                viewServices, 
                "Error deleting service: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // Get info from database
    private List<ServiceDTO> getAllServices(){
        try {
            // Load all services and departments
            List<Service> services = Db.Service.select(-1, s -> true);
            List<Department> departments = Db.Department.select(-1, d -> true);
            
            // Build department lookup map
            Map<String, String> deptMap = new HashMap<>();
            for(Department department: departments){
                deptMap.put(department.getId(), department.getDepartmentName());
            }
            
            // Convert to ServiceDTO
            List<ServiceDTO> result = new ArrayList<>();
            for (Service service: services){
                String departmentName = deptMap.getOrDefault(service.getDepartmentId(), "Unknown Department");
                result.add(new ServiceDTO(
                    service.getId(),
                    service.getServiceName(),
                    service.getFee(),
                    departmentName
                ));
            }
            return result;
        } catch(Exception ex){
            return new ArrayList<>();
        }
    }
    
    private List<ServiceDTO> getServicesByDepartment(String department){
        try{
            if("All".equals(department)){
                return getAllServices();
            }
            
            // Find department ID by name
            List<Department> departments = Db.Department.select(-1, d -> d.getDepartmentName().equals(department));
            if(departments.isEmpty()){
                return new ArrayList<>();
            }
            
            String departmentId = departments.get(0).getId();
            
            // Load services for this department
            List<Service> services = Db.Service.select(-1, s -> s.getDepartmentId().equals(departmentId));
            
            // Convert to DTO
            List<ServiceDTO> result = new ArrayList();
            for(Service service : services){
                result.add(new ServiceDTO(
                    service.getId(),
                    service.getServiceName(),
                    service.getFee(),
                    department
                ));
            }
            return result;         
        } catch (Exception ex){
            return new ArrayList<>();
        }
    }
    
    private List<ServiceDTO> getServicesBySearch(String searchService) {
        try {
            // Load all services and departments
            List<Service> services = Db.Service.select(-1, s -> 
                s.getServiceName().toLowerCase().contains(searchService.toLowerCase())
            );
            List<Department> departments = Db.Department.select(-1, d -> true);
            
            // Build department lookup map
            Map<String, String> deptMap = new HashMap<>();
            for (Department dept : departments) {
                deptMap.put(dept.getId(), dept.getDepartmentName());
            }
            
            // Convert to DTOs
            List<ServiceDTO> result = new ArrayList<>();
            for (Service service : services) {
                String deptName = deptMap.getOrDefault(service.getDepartmentId(), "Unknown Department");
                result.add(new ServiceDTO(
                    service.getId(),
                    service.getServiceName(),
                    service.getFee(),
                    deptName
                ));
            }
            
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private String generateNewServiceId(){
        try{
            List<Service> services = Db.Service.select(-1, s -> true);
            int maxId = 0;
            
            for(Service service: services){
                String id = service.getId();
                if (id.startsWith("SVC-")){
                    int num = Integer.parseInt(id.substring(4));
                    maxId = Math.max(maxId, num);
                }
            }
            return String.format("SVC-%03d", maxId + 1);
            
        } catch(NumberFormatException ex){
            return ex.getMessage();
        }
    }
    
    private String getDepartmentIdByName(String departmentName){
        try{
            List<Department> departments = Db.Department.select(-1, d -> d.getDepartmentName().equals(departmentName));
            return departments.isEmpty() ? null : departments.get(0).getId();
        } catch (Exception ex) {
            return null;
        }
    }   
   
    public JPanel getView() { return viewServices;}
}
