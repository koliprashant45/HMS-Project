package src.interfaces;

import src.exceptions.InvalidInputException;

public interface Schedule {
    void scheduleAppointment(int patientId, int doctorId, String date) throws InvalidInputException;
}
