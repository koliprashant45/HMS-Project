package src.main;

import src.services.HealthMonitoringSystem;
import src.exceptions.InvalidInputException;

public class HealthMonitoringSystemMain {
    public static void main(String[] args) {
        HealthMonitoringSystem hms = new HealthMonitoringSystem();

        // Load data from files
        try {
            hms.readPatientsFromFile("data/Patients.csv");
            hms.readDoctorsFromFile("data/Doctors.csv");
            hms.readAppointmentsFromFile("data/Appointments.csv");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            return;
        }

        // Sort patients by age and display
        hms.sortPatientsByAge();
        System.out.println("Patients sorted by age:");
        hms.displayPatients();

        try {
            // Schedule a new appointment
            hms.scheduleAppointment(1, 2, "2024-10-01");

            // Retrieve medical history for patient
            hms.retrieveMedicalHistory(1);

            // Update patient vitals
            hms.updatePatientVitals(1, "Normal Blood Pressure");

            // Mark an appointment as completed
            hms.completeAppointment(1);

            // Display completed appointments
            hms.displayCompletedAppointments();

            // Sort appointments by date and display
            hms.sortAppointmentsByDate();

            // Display appointments for a doctor
            hms.displayAppointmentsForDoctor(1);

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
