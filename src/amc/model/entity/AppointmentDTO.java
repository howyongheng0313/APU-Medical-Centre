package amc.model.entity;

public final class AppointmentDTO {
    private AppointmentDTO() {}

    public static class AppointmentSummary {
        public final String appointmentID;
        public final String customerID;
        private String recipientName = null;
        private int appointmentCount = 0;

        public AppointmentSummary(String appointmentID, String customerID) {
            this.appointmentID = appointmentID;
            this.customerID = customerID;
        }

        public String getRecipientName() { return recipientName; }
        public int getAppointmentCount() { return appointmentCount; }

        public void setRecipientName(String name) {
            if (recipientName == null) recipientName = name;
        }

        public void addAppointment() {
            appointmentCount++;
        }
    }

    // Detailed view of a single appointment
    public static class AppointmentDetail {
        public final String appointmentId;
        public final String customerId;
        public final String doctorId;
        public final String staffId;
        public final String departmentId;
        public final String date;
        public final String time;
        public final int status;
        public final String notes;

        public AppointmentDetail(
                String appointmentId,
                String customerId,
                String doctorId,
                String staffId,
                String departmentId,
                String date,
                String time,
                int status,
                String notes
        ) {
            this.appointmentId = appointmentId;
            this.customerId = customerId;
            this.doctorId = doctorId;
            this.staffId = staffId;
            this.departmentId = departmentId;
            this.date = date;
            this.time = time;
            this.status = status;
            this.notes = notes;
        }
    }
}
