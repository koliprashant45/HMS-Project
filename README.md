
## Title Page

Health Monitoring System - Using OOPs Concepts

Submitted by: Prashant Koli
Date: 19/09/2024
## Introduction

This project is a Java-based Health Monitoring System that allows registering patients and doctors, scheduling and managing appointments, and sorting patients based on age. The system reads data from CSV files and demonstrates object-oriented principles like inheritance, interfaces, and exception handling.

## Code Explanation

1. **Patient Class:**
   This class models a patient with properties like ID, name, age, gender, and medical history. It contains methods to retrieve and update the patient's medical history.

2. **Doctor Class:**
   The Doctor class represents a medical professional with attributes such as doctor ID, name, and specialization. Specialized doctor types like Cardiologist and Paediatrician inherit from this class.

3. **Appointment Class:**
   This class represents an appointment between a patient and a doctor. It includes attributes like appointment ID, patient ID, doctor ID, date, and appointment status (BOOKED, CANCELED, or COMPLETED).

4. **HealthMonitoringSystem Class:**
   This is the main service class that handles patient and doctor registration, appointment scheduling, retrieving medical history, and sorting patients by age. It implements the `Schedule` interface, which defines the contract for scheduling appointments.

5. **Exception Handling:**
   The `InvalidInputException` is used to handle invalid patient or doctor IDs and other error cases.

## Assumptions

1. The input CSV files (`Patients.csv`, `Doctors.csv`, and `Appointments.csv`) follow a specific format and are placed in the `data` directory.
2. The date format is `yyyy-MM-dd`, and any changes to this format will result in parsing errors.
3. Vitals update adds a new entry to the patient's medical history rather than replacing it.
4. The system assumes unique IDs for patients and doctors.

## Output and Screenshots

### Output Screenshot 1: Sorting Patients by Age

![Screenshot of sorted patients](E:\Hein+Fricke\HMS-Project\Screenshot\Sorting.png)

The system sorted the patients in ascending order of age.

### Output Screenshot 2: Scheduled Appointments 

![Screenshot of scheduled appointments](E:\Hein+Fricke\HMS-Project\Screenshot\Scheduled+Retrieve.png)

An appointment was successfully scheduled for patient ID 1 with doctor ID 2.

## Conclusion

The Health Monitoring System effectively demonstrates Java OOP principles by handling dynamic patient registration, appointment scheduling, and data sorting. The implementation is flexible and can be extended to include additional functionalities like more medical specializations or complex patient data management.
