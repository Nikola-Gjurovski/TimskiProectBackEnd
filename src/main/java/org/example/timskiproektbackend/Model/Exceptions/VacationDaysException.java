package org.example.timskiproektbackend.Model.Exceptions;

public class VacationDaysException extends RuntimeException {
    public VacationDaysException(String message) {
        super(message);
    }
}