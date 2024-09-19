package src.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private List<String> medicalHistory;

    public Patient(int id, String name, int age, String gender, List<String> medicalHistory) {
        if (id <= 0 || age < 0) {
            throw new IllegalArgumentException("ID and age must be positive.");
        }
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.medicalHistory = new ArrayList<>(medicalHistory);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public List<String> getMedicalHistory() { return Collections.unmodifiableList(medicalHistory); }

    public void updateMedicalHistory(String newEntry) {
        medicalHistory.add(newEntry);
    }

    @Override
    public String toString() {
        return "Patient [ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Medical History: " + medicalHistory + "]";
    }
}
