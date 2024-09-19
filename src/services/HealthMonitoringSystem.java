package src.services;

import src.entities.Patient;
import src.entities.Doctor;
import src.entities.Cardiologist;
import src.entities.Paediatrician;
import src.entities.Appointment;
import src.entities.Status;
import src.interfaces.Schedule;
import src.exceptions.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HealthMonitoringSystem implements Schedule {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    public HealthMonitoringSystem() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    // Method to register a new patient
    public void registerNewPatient(int id, String name, int age, String gender, List<String> medicalHistory) {
        patients.add(new Patient(id, name, age, gender, medicalHistory));
        System.out.println("Patient " + name + " has been registered successfully.");
    }

    // Method to register a new doctor
    public void registerNewDoctor(int doctorId, String name, String specialization) {
        Doctor doctor;
        switch (specialization.toLowerCase()) {
            case "cardiologist":
                doctor = new Cardiologist(doctorId, name);
                break;
            case "paediatrician":
                doctor = new Paediatrician(doctorId, name);
                break;
            default:
                doctor = new Doctor(doctorId, name, specialization);
        }
        doctors.add(doctor);
        System.out.println("Doctor " + name + " has been registered successfully.");
    }

    // Method to read patients' data from a file
    public void readPatientsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String gender = data[3];
                List<String> medicalHistory = Arrays.asList(data[4].replace("[", "").replace("]", "").split(";"));
                patients.add(new Patient(id, name, age, gender, medicalHistory));
            }
        } catch (IOException e) {
            System.out.println("Error reading patients file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
    }

    // Method to read doctors' data from a file
    public void readDoctorsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String specialization = data[2];

                Doctor doctor;
                switch (specialization.toLowerCase()) {
                    case "cardiologist":
                        doctor = new Cardiologist(id, name);
                        break;
                    case "paediatrician":
                        doctor = new Paediatrician(id, name);
                        break;
                    default:
                        doctor = new Doctor(id, name, specialization);
                }
                doctors.add(doctor);
            }
        } catch (IOException e) {
            System.out.println("Error reading doctors file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
    }

    // Method to read appointments' data from a file
    public void readAppointmentsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int appointmentId = Integer.parseInt(data[0]);
                int patientId = Integer.parseInt(data[1]);
                int doctorId = Integer.parseInt(data[2]);
                String appointmentDate = data[3];
                Status status = Status.valueOf(data[4].toUpperCase());
                appointments.add(new Appointment(appointmentId, patientId, doctorId, appointmentDate, status));
            }
        } catch (IOException e) {
            System.out.println("Error reading appointments file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing status: " + e.getMessage());
        }
    }

    // Method to sort patients by age
    public void sortPatientsByAge() {
        patients.sort(Comparator.comparingInt(Patient::getAge));
    }

    // Method to sort appointments by date
    public void sortAppointmentsByDate() {
        appointments.sort(Comparator.comparing(Appointment::getAppointmentDate));
    }

    // Method to schedule an appointment
    @Override
    public void scheduleAppointment(int patientId, int doctorId, String date) throws InvalidInputException {
        Patient patient = getPatientById(patientId);
        Doctor doctor = getDoctorById(doctorId);
        if (patient == null || doctor == null) {
            throw new InvalidInputException("Invalid patient or doctor ID.");
        }
        appointments.add(new Appointment(appointments.size() + 1, patientId, doctorId, date, Status.BOOKED));
        System.out.println();
        System.out.println("Appointment scheduled for Patient ID: " + patientId + " with Doctor ID: " + doctorId);
    }

    // Method to retrieve a patient's medical history
    public void retrieveMedicalHistory(int patientId) throws InvalidInputException {
        Patient patient = getPatientById(patientId);
        if (patient == null) {
            throw new InvalidInputException("Invalid patient ID.");
        }
        System.out.println("Medical History for Patient ID " + patientId + ": " + patient.getMedicalHistory());
    }

    // Method to update patient's vitals
    public void updatePatientVitals(int patientId, String newVital) throws InvalidInputException {
        Patient patient = getPatientById(patientId);
        if (patient == null) {
            throw new InvalidInputException("Invalid patient ID.");
        }
        patient.updateMedicalHistory(newVital);
        System.out.println("Vitals updated for Patient ID " + patientId);
    }

    // Helper method to get a patient by ID
    private Patient getPatientById(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    // Helper method to get a doctor by ID
    private Doctor getDoctorById(int id) {
        return doctors.stream().filter(d -> d.getDoctorId() == id).findFirst().orElse(null);
    }

    // Method to display all patients
    public void displayPatients() {
        patients.forEach(System.out::println);
    }

    // Method to display all appointments for a specific doctor
    public void displayAppointmentsForDoctor(int doctorId) throws InvalidInputException {
        Doctor doctor = getDoctorById(doctorId);
        if (doctor == null) {
            throw new InvalidInputException("Invalid doctor ID.");
        }
        System.out.println("Appointments for Doctor ID " + doctorId + ":");
        appointments.stream()
                .filter(a -> a.getDoctorId() == doctorId)
                .forEach(System.out::println);
    }

    // Method to cancel an appointment by ID
    public void cancelAppointment(int appointmentId) throws InvalidInputException {
        Appointment appointment = appointments.stream()
                .filter(a -> a.getAppointmentId() == appointmentId)
                .findFirst()
                .orElse(null);
        if (appointment == null) {
            throw new InvalidInputException("Invalid appointment ID.");
        }
        appointment.setStatus(Status.CANCELED);
        System.out.println("Appointment ID " + appointmentId + " has been canceled.");
    }

    // Method to mark an appointment as completed by ID
    public void completeAppointment(int appointmentId) throws InvalidInputException {
        Appointment appointment = appointments.stream()
                .filter(a -> a.getAppointmentId() == appointmentId)
                .findFirst()
                .orElse(null);
        if (appointment == null) {
            throw new InvalidInputException("Invalid appointment ID.");
        }
        appointment.setStatus(Status.COMPLETED); // Set the status to COMPLETED
        System.out.println("Appointment ID " + appointmentId + " has been marked as completed.");
    }

    // Method to display all completed appointments
    public void displayCompletedAppointments() {
        System.out.println();
        System.out.println("Completed Appointments:");
        appointments.stream()
                .filter(a -> a.getStatus() == Status.COMPLETED)
                .forEach(System.out::println);
    }

}
