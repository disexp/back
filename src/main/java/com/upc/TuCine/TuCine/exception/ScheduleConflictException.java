package com.upc.TuCine.TuCine.exception;

public class ScheduleConflictException extends RuntimeException {
    public ScheduleConflictException(String message) {
        super(message);
    }
}